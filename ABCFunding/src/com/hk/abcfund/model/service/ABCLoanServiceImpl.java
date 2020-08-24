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
		return ldao.getLoanList();
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
	@Scheduled(cron="0 0/1 * 1,5,10,15,20,25 * ?")
	//@Scheduled(cron="0/10 * * * * ?")
	public void checkRepay() {
		// 1. Get list of loan
		List<ABCLoanDto> loanList = ldao.getLoanList();
		logger.info("List of loan:\n"+loanList.toString());
		
		// 2. Check date of repayments
		for(ABCLoanDto loan : loanList){
			
			// If date of repayments is today and still repaying, then do the process of repayments
			if(ABCUtility.isSameDate(loan.getRequestDate()) &&
					!loan.getProgress().equals("상환완료")){
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
				
				// 대출상품의 계좌에서 투자자 및 관리자에게 상환
				// 해당 대출의 투자자 리스트 얻어오기
				// 
				List<ABCInvestDto> investList = 
						investDao.getInvestListByLoan(loan.getLoanCode());
				
				// 투자자에게 상환
				int totalRepay = monthlyPay;
				for(ABCInvestDto invest : investList){
					// 해당 투자자에게 상환해야 할 금액
					int repayMoney = ABCUtility.getRepayMoney(
							invest.getInvestMoney(), loan.getLoanDate(),0.06);
					logger.info("투자자 상환금: " + repayMoney);
					
					// 대출계좌의 예치금 차감
					if(totalRepay <= 0) break;	// 상환금액이 부족한 경우 
					map.put("loanCode", loan.getLoanCode());
					map.put("money", repayMoney);
					accountDao.withdrawForInvest(map);
					totalRepay -= repayMoney;
					logger.info("남은 예치금: " + totalRepay);
					
					// 투자자의 계좌에 입금
					map.put("email", invest.getEmail());
					map.put("money", repayMoney);
					accountDao.depositForRepay(map);
				}
				logger.info("투자자의 입금완료");
				
				// 관리자에게 상환
				accountDao.depositForAdmin(totalRepay);
				logger.info("관리자 입금 완료:" + totalRepay);
				
				// 해당 대출의 회차 수 증가
				loan.setRound(loan.getRound()+1);
				ldao.updateRound(loan);
				logger.info("대출의 회차 수 증가완료");
				
				
				/* 대출신청자의 대출내역 추가하기 */
				// 최근 대출내역 가져오기
				ABCLoanTransactionDto preLoanTran = 
					loanTranDao.getRecentTransaction(loan.getLoanCode());
				logger.info("최근 대출내역: " + preLoanTran);
				
				int stackRepayRate = 0;
				int stackRepayOrigin = 0;
				int collectRate = 0;
				float interestRate = loan.getInterestRate() / 100F;
				int balance = 0;
				
				// 누적상환원금 및 누적상환이자금 구하기
				// 첫 상환내역인 경우(null인 경우)
				if(preLoanTran == null) {
					logger.info("첫 상환내역입니다.");
					
					// 진행상황을 상환중으로 변경
					loan.setProgress("상환중");
					ldao.updateProgress(loan);
					logger.info("진행상황을 상환중으로 변경완료");
					
					// 누적금 계산
					stackRepayRate = (int)(ABCUtility.getInterest(loan.getLoanMoney(), interestRate));
					stackRepayOrigin = monthlyPay - stackRepayRate;
					logger.info("상환이자금: " + stackRepayRate);
					logger.info("상환원금: " + stackRepayOrigin);
				} else{ // 이전 내역을 참고하여 누적
					// 잔금구하기
					balance = loan.getLoanMoney() - preLoanTran.getStackRepayOrigin();
					stackRepayRate = (int)Math.round(ABCUtility.getInterest(balance, interestRate));
					stackRepayOrigin = (int)Math.round(
							ABCUtility.getEqualPrincipalPayment(loan.getLoanMoney(), 
																loan.getLoanDate(), 
																interestRate)
									- ABCUtility.getInterest(balance, interestRate));
					logger.info("상환이자금: " + stackRepayRate);
					logger.info("상환원금: " + stackRepayOrigin);
					
					// 누적금 계산
					stackRepayRate += preLoanTran.getStackRepayRate();
					stackRepayOrigin += preLoanTran.getStackRepayOrigin();
					
					logger.info("누적상환이자금: " + stackRepayRate);
					logger.info("누적상환원금: " + stackRepayOrigin);
					logger.info("대출내역 생성 중 잔금: " + balance);
				}
				
				// 회수율 구하기
				collectRate = (int)((double)stackRepayOrigin / loan.getLoanMoney() * 100);
				logger.info("회수율: " + collectRate);
				
				// 상환완료인지 확인
				// 완료이면 진행상황을 상환완료로 변경 및 해당 상품의 계좌 삭제
				if(stackRepayOrigin >= loan.getLoanMoney()){
					loan.setProgress("상환완료");
					ldao.updateProgress(loan);
					accountDao.deleteByLoan(loan.getLoanCode());
					logger.info("##상환완료작업 완료##");
				} else{ // 완료가 아니면 다음 달 상환일 잡기. 대출기간을 초과한 경우 연장
					String requestDate = ABCUtility.calcRepayDate(loan.getRepay());
					loan.setRequestDate(requestDate);
					if(loan.getRound() >= loan.getLoanDate()){ // 회차 수가 대출기간보다 큰 경우 
						// 대출기간 연장
						loan.setLoanDate(loan.getLoanDate()+1);
						ldao.extendLoanDate(loan);
						logger.info("대출기간 연장 완료");
					}
					ldao.nextRepayDate(loan);
					logger.info("다음 달 상환일 잡기 완료:"+requestDate);
				}
								
				// 대출내역 객체 생성
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
				
				// 대출내역 추가
				loanTranDao.addTransaction(loanTran);
				logger.info("대출내역 추가됨:" + loanTranDao.getRecentTransaction(loan.getLoanCode()));
				
				
				/* 대출투자자들의 투자내역 추가	 */
				for(ABCInvestDto invest : investList){
					// 최근 투자내역 가져오기
					ABCInvestTransactionDto preInvestTran = 
						investTranDao.getRecentTransaction(invest.getInvestSeq());
					logger.info("최근 투자내역: " + preInvestTran);
					
					// 금액 계산할 변수 초기화
					interestRate = 0.06F;
					int intendProfit = 0; // 예정수익금=>이자
					int stackCollect = 0; // 누적 회수금
					int origMoney = ABCUtility.getRepayMoney(
							invest.getInvestMoney(), loan.getLoanDate(), interestRate);
					logger.info("투자금에 따른 상환금: " + origMoney);
					
					// 첫 상환인 경우(null인 경우)
					if(preInvestTran == null){
						logger.info("첫 상환내역입니다.");
						
						intendProfit = (int)ABCUtility.getInterest(invest.getInvestMoney(), interestRate);
						stackCollect = origMoney - intendProfit;
						logger.info("예정수익금: " + intendProfit);
						logger.info("회수금: " + stackCollect);
					} else{
						balance = invest.getInvestMoney() - preInvestTran.getStackCollect();
						intendProfit = (int)Math.round(ABCUtility.getInterest(balance, interestRate));
						stackCollect = (int)Math.round(
								ABCUtility.getEqualPrincipalPayment(invest.getInvestMoney(), 
																	loan.getLoanDate(), 
																	interestRate)
								- ABCUtility.getInterest(balance, interestRate)
								);
						logger.info("예정수익금: " + intendProfit);
						logger.info("회수금: " + stackCollect);
						
						// 회수금 누적
						stackCollect += preInvestTran.getStackCollect();
						logger.info("누적회수금: " + stackCollect);
					}
					
					// 투자내역 생성 및 등록
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
					logger.info("투자내역 추가됨: " + investTranDao.getRecentTransaction(invest.getInvestSeq()));
				}
				logger.info("#####################################################################################################");
			} // if문
		} // for문
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
		return loanTranDao.getTransactionBySorted(email);
	}

	@Override
	public int getPayingCount(String email) {
		return ldao.getPayingCount(email);
	}
}