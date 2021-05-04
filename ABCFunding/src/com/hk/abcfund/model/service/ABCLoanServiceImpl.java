package com.hk.abcfund.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hk.abcfund.enums.ABCProgressType;
import com.hk.abcfund.model.dao.ABCAccountDao;
import com.hk.abcfund.model.dao.ABCAdminDao;
import com.hk.abcfund.model.dao.ABCInvestDao;
import com.hk.abcfund.model.dao.ABCInvestTransactionDao;
import com.hk.abcfund.model.dao.ABCLoanDao;
import com.hk.abcfund.model.dao.ABCLoanTransactionDao;
import com.hk.abcfund.model.dao.ABCMemberDao;
import com.hk.abcfund.model.dto.ABCInvestDto;
import com.hk.abcfund.model.dto.ABCInvestTransactionDto;
import com.hk.abcfund.model.dto.ABCLoanDto;
import com.hk.abcfund.model.dto.ABCLoanTransactionDto;
import com.hk.abcfund.util.ABCUtility;

/**
 * Service controller for loan
 * @author 9age
 *
 */
@Service
public class ABCLoanServiceImpl implements ABCLoanService {
	/** slf4j Logger */
	private static final Logger logger = LoggerFactory
			.getLogger(ABCLoanServiceImpl.class);

	/** DAO of loan */
	@Autowired
	private ABCLoanDao ldao;
	
	/** DAO of member */
	@Autowired
	private ABCMemberDao memberDao;
	
	/** DAO of investment */
	@Autowired
	private ABCInvestDao investDao;
	
	/** DAO of administrator */
	@Autowired
	private ABCAdminDao adminDao;
	
	/** DAO of account */
	@Autowired
	private ABCAccountDao accountDao;
	
	/** DAO of loan detail */
	@Autowired
	private ABCLoanTransactionDao loanTranDao;
	
	/** DAO of investment detail */
	@Autowired
	private ABCInvestTransactionDao investTranDao;
	
	/**
	 * Accept application of loan
	 * @param ldto A DTO of loan data
	 */
	@Override
	@Transactional
	public void addLoan(ABCLoanDto ldto) {
		// Add loan by DAO
		ldao.addLoan(ldto);
		
		// Increase count of loan by email
		memberDao.incLoan(ldto.getEmail());
		
		// Get loan code and add to examination
		int lcode = ldao.getLoanCode();
		ldao.addJudge(lcode);
		
	}

	/**
	 * Get list of loans
	 * @return A list of loan DTO
	 */
	@Override
	public List<ABCLoanDto> getLoanList() {
		List<ABCLoanDto> loanList = ldao.getLoanList();
		
		// Set progress
		for (ABCLoanDto loan : loanList) {
			loan.setProgress(ABCProgressType.findName(loan.getProgress()));
		}
		
		return loanList;
	}
	
	/**
	 * Get a loan by loan code
	 * @return A DTO of loan
	 */
	@Override
	public ABCLoanDto getLoan(int loanCode) {
		return ldao.getLoan(loanCode);
	}

	/**
	 * Get all list of loans by email
	 * @return A list of loan DTO 
	 */
	@Override
	public List<ABCLoanDto> getLoanListAll(String email) {
		return ldao.getLoanListAll(email);
	}
	
	/**
	 * Cancel the loan.
	 * Non-approval loan will delete immediately
	 * and loan funding repay the investments and deleted
	 * @param loanCode A loan code
	 */
	@Override
	@Transactional
	public void loanCancel(int loanCode) {
		// 1. Repay the investments
		// Get list of investment by loan code
		List<ABCInvestDto> list = investDao.getInvestListByLoan(loanCode);
		
		// If the loan has investor, then repay and delete the loan
		if(!list.isEmpty()) {
			accountDao.updateForPayback(list);
			investDao.deleteByLoan(loanCode);
		}
		
		// 2. Delete the account by loan code
		accountDao.deleteByLoan(loanCode);
		
		// 3. Delete the examination by loan code
		adminDao.deleteJudgeByLoan(loanCode);
		
		// 4. Delete the loan by loan code
		ldao.deleteLoan(loanCode);
	}
	
	/**
	 * Check the repayments for every minutes and processing the repayments.
	 * This method uses for batch too.
	 */
	@Override
	@Transactional
	@Scheduled(cron="0 */1 * 1,5,10,15,20,25 * ?")
//	@Scheduled(cron="*/10 * * * * *")
	public void checkRepay() {
		// 1. Get list of loan
		List<ABCLoanDto> loanList = ldao.getLoanList();
		logger.info("List of loan:\n"+loanList.toString());
		
		// 2. Check date of repayments
		for(ABCLoanDto loan : loanList){
			
			// If date of repayments is today and still repaying, then do the process of repayments
			if(ABCUtility.isSameDate(loan.getRequestDate()) &&
					!loan.getProgress().equals(ABCProgressType.REPAYED.getCode())){
				logger.info("Loan progressing: " + loan);
				
				// Create hash map
				Map<String, Object>map = new HashMap<String, Object>();
				
				// Get repayments
				int monthlyPay = ABCUtility.getRepayMoney(
					loan.getLoanMoney(),
					loan.getLoanDate(),
					loan.getInterestRate() / 100.0
				);
				logger.info("Repayments: " + monthlyPay);
				
				// Check balance of loan applicant
				int balanceOfLoan = accountDao.getBalance(loan.getEmail());
				logger.info("Balance of loan applicant : " + balanceOfLoan);
				
				// If balance is lack, then skip this loan
				if(balanceOfLoan <= 0){
					logger.info("Skip for lack of balance");
					continue;
				}
				
				// Withdraw repayments of loan applicant to a loan  
				map.put("email", loan.getEmail());
				map.put("investMoney", monthlyPay);
				accountDao.withdraw(map);
				logger.info("Withdraw of repayments of loan applicant has complete");
				
				// Deposit to the loan
				map.put("loanCode", loan.getLoanCode());
				map.put("investMoney", monthlyPay);
				accountDao.deposit(map);
				logger.info("Deposit to the loan has complete");
				
				// Repay from account of loans to investor and administrator
				// Get list of investment
				List<ABCInvestDto> investList = 
						investDao.getInvestListByLoan(loan.getLoanCode());
				
				// Repay to investor
				int totalRepay = monthlyPay;
				for(ABCInvestDto invest : investList){
					// Amount of repayments for investor
					int repayMoney = ABCUtility.getRepayMoney(
							invest.getInvestMoney(), loan.getLoanDate(), 0.06);
					logger.info("Repayments: " + repayMoney);
					
					// Withdraw for investor
					if(totalRepay <= 0) break;	// If repayments lack, then skip this loan
					map.put("loanCode", loan.getLoanCode());
					map.put("money", repayMoney);
					accountDao.withdrawForInvest(map);
					totalRepay -= repayMoney;
					logger.info("To repayments: " + totalRepay);
					
					// Deposit the repayments to investor
					map.put("email", invest.getEmail());
					map.put("money", repayMoney);
					accountDao.depositForRepay(map);
				}
				logger.info("Deposit to investor has completed");
				
				// Remove Administrator Functions
				// Repay to administrator
//				accountDao.depositForAdmin(totalRepay);
//				logger.info("Deposit to administrator has completed:" + totalRepay);
				
				// Increase the rounds of the loan 
				loan.setRound(loan.getRound() + 1);
				ldao.updateRound(loan);
				logger.info("Increase the rounds of the loan has completed");
				
				
				/* Add the loan detail */
				// Get recent detail of loan
				ABCLoanTransactionDto preLoanTran = 
					loanTranDao.getRecentTransaction(loan.getLoanCode());
				logger.info("Recent detail of loan: " + preLoanTran);
				
				int stackRepayRate = 0;
				int stackRepayOrigin = 0;
				int collectRate = 0;
				float interestRate = loan.getInterestRate() / 100F;
				int balance = 0;
				
				// Get stack repayments and stack interest
				// If first repay(detail is null)
				if(preLoanTran == null) {
					logger.info("It's first repayments");
					
					// Change progression to repaying
					loan.setProgress(ABCProgressType.REPAYING.getCode());
					ldao.updateProgress(loan);
					logger.info("Changed the progression to repaying");
					
					// Calculate the stack repayments
					stackRepayRate = (int)(ABCUtility.getInterest(loan.getLoanMoney(), interestRate));
					stackRepayOrigin = monthlyPay - stackRepayRate;
					logger.info("Stack interest: " + stackRepayRate);
					logger.info("Stack repayments: " + stackRepayOrigin);
				} else{ // Stack repayments from loan detail
					// Calculate the balance
					balance = loan.getLoanMoney() - preLoanTran.getStackRepayOrigin();
					stackRepayRate = (int)Math.round(ABCUtility.getInterest(balance, interestRate));
					stackRepayOrigin = (int)Math.round(
							ABCUtility.getEqualPrincipalPayment(loan.getLoanMoney(), 
																loan.getLoanDate(), 
																interestRate)
									- ABCUtility.getInterest(balance, interestRate));
					logger.info("Stack interest: " + stackRepayRate);
					logger.info("Stack repayments: " + stackRepayOrigin);
					
					// Calculate the repayments
					stackRepayRate += preLoanTran.getStackRepayRate();
					stackRepayOrigin += preLoanTran.getStackRepayOrigin();
					
					logger.info("Stack interest: " + stackRepayRate);
					logger.info("Stack repayments: " + stackRepayOrigin);
					logger.info("A balance while write the detail: " + balance);
				}
				
				// Get collect rate
				collectRate = (int)((double)stackRepayOrigin / loan.getLoanMoney() * 100);
				logger.info("Collect rate: " + collectRate);
				
				// Check if complete the repayments
				// If complete, change the progression to complete and delete the account
				if(stackRepayOrigin >= loan.getLoanMoney()){
					loan.setProgress(ABCProgressType.REPAYED.getCode());
					ldao.updateProgress(loan);
					accountDao.deleteByLoan(loan.getLoanCode());
					logger.info("##Complete the repayment##");
				} else{
					// If not complete, get next repayment's month.
					String requestDate = ABCUtility.calcRepayDate(loan.getRepay());
					loan.setRequestDate(requestDate);
					
					// If rounds over the date of loan
					if(loan.getRound() >= loan.getLoanDate()){ 
						loan.setLoanDate(loan.getLoanDate()+1); // Delay the date of loan
						ldao.extendLoanDate(loan);
						logger.info("Delay the date of loan has completed");
					}
					ldao.nextRepayDate(loan);
					logger.info("Get next repayment's month:"+requestDate);
				}
								
				// Create the object of loan detail
				ABCLoanTransactionDto loanTran = 
					new ABCLoanTransactionDto(
						0,					// loanSeq
						loan.getLoanCode(), // loanCode
						stackRepayRate,		// stackRepayRate
						stackRepayOrigin,	// stackReapyOrigin
						loan.getProgress(),	// progress
						collectRate,		// collectRate
						loan.getRound()		// round
					);
				
				// Add a loan detail
				loanTranDao.addTransaction(loanTran);
				logger.info("A loan detail added:" + loanTranDao.getRecentTransaction(loan.getLoanCode()));
				
				
				/* Add investment detail */
				for(ABCInvestDto invest : investList){
					// Get list of recent detail of investment
					ABCInvestTransactionDto preInvestTran = 
						investTranDao.getRecentTransaction(invest.getInvestSeq());
					logger.info("Recent detail of investment: " + preInvestTran);
					
					// Declare the variable 
					interestRate = 0.06F;
					int intendProfit = 0; // intend profit
					int stackCollect = 0; // stack collection
					int origMoney = ABCUtility.getRepayMoney(
							invest.getInvestMoney(), loan.getLoanDate(), interestRate);
					logger.info("Repayments: " + origMoney);
					
					// If first repay(investment detail is null)
					if(preInvestTran == null){
						logger.info("First detail of investment");
						
						intendProfit = (int)ABCUtility.getInterest(invest.getInvestMoney(), interestRate);
						stackCollect = origMoney - intendProfit;
						logger.info("Intend profit: " + intendProfit);
						logger.info("Stack collection: " + stackCollect);
					} else{
						// Get repayments from investment detail
						balance = invest.getInvestMoney() - preInvestTran.getStackCollect();
						intendProfit = (int)Math.round(ABCUtility.getInterest(balance, interestRate));
						stackCollect = (int)Math.round(
								ABCUtility.getEqualPrincipalPayment(invest.getInvestMoney(), 
																	loan.getLoanDate(), 
																	interestRate)
								- ABCUtility.getInterest(balance, interestRate)
								);
						logger.info("Intend profit: " + intendProfit);
						logger.info("Stack collection: " + stackCollect);
						
						// stack collection
						stackCollect += preInvestTran.getStackCollect();
						logger.info("Stack collection: " + stackCollect);
					}
					
					// Create the object of investment detail
					ABCInvestTransactionDto investTran = 
						new ABCInvestTransactionDto(
							0,						// investTransactionSeq
							invest.getInvestSeq(),	// investSeq
							intendProfit,			// intendProfit
							stackCollect,			// stackCollect
							loan.getProgress(),		// progress
							collectRate,			// collectRate
							loan.getRound()			// round
						);
					investTranDao.addTransaction(investTran);
					logger.info("A investment detail added: " + investTranDao.getRecentTransaction(invest.getInvestSeq()));
				}
				logger.info("#####################################################################################################");
			}
		}
	}

	@Override
	public List<ABCLoanDto> getRemainPayment(String email) {
		return ldao.getRemainPayment(email);
	}

	@Override
	public List<ABCLoanDto> getLoanListByInvest(String email) {
		return ldao.getLoanListByInvest(email);
	}

	@Override
	public List<ABCLoanTransactionDto> getTransactionBySorted(String email) {
		List<ABCLoanTransactionDto> loanTran = loanTranDao.getTransactionBySorted(email); 
		
		// Set progress
		for(ABCLoanTransactionDto tran : loanTran) {
			tran.setProgress(ABCProgressType.findName(tran.getProgress()));
		}
		
		return loanTran;
	}

	@Override
	public int getPayingCount(String email) {
		return ldao.getPayingCount(email);
	}
}