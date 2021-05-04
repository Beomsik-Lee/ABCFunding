package com.hk.abcfund.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hk.abcfund.enums.ABCProgressType;
import com.hk.abcfund.model.dao.ABCAccountDao;
import com.hk.abcfund.model.dao.ABCAdminDao;
import com.hk.abcfund.model.dao.ABCInvestDao;
import com.hk.abcfund.model.dao.ABCInvestTransactionDao;
import com.hk.abcfund.model.dao.ABCLoanDao;
import com.hk.abcfund.model.dao.ABCMemberDao;
import com.hk.abcfund.model.dto.ABCInvestDto;
import com.hk.abcfund.model.dto.ABCInvestTransactionDto;
import com.hk.abcfund.model.dto.ABCJudgeResultDto;
import com.hk.abcfund.model.dto.ABCLoanDto;
import com.hk.abcfund.model.dto.ABCMemberDto;
import com.hk.abcfund.util.ABCUtility;

/**
 * Controller for investment
 * @author 9age
 *
 */
@Service
public class ABCInvestServiceImpl implements ABCInvestService {
	/** Member DAO */
	@Autowired
	private ABCMemberDao memberDao;
	
	/** Loan DAO */
	@Autowired
	private ABCLoanDao loanDao;
	
	/** Examine DAO */
	@Autowired
	private ABCAdminDao adminDao;
	
	/** Account DAO */
	@Autowired
	private ABCAccountDao accountDao;
	
	/** Investment DAO */
	@Autowired
	private ABCInvestDao investDao;
	
	/** Investment detail DAO */
	@Autowired
	private ABCInvestTransactionDao investTranDao;
	
	/**
	 * Set result of DAO to DTO 
	 * @param member A DTO of member
	 * @param loan A DTO of loan
	 * @param judge A DTO of examine
	 */
	@Override
	@Transactional
	public void getInvestDetail(List<Object> list, int loanCode) {
		// 1. Get loan data by loan code
		ABCLoanDto loan = loanDao.getLoan(loanCode);
		
		// 2. Get member data by email
		ABCMemberDto member = memberDao.getMember(loan.getEmail());
		
		// 3. Get examine data by loan code
		ABCJudgeResultDto judge = adminDao.getJudge(loanCode);
		
		// Add to list
		list.add(loan);
		list.add(member);
		list.add(judge);
	}
	
	/**
	 * Processing request for investment
	 * @param email email of investor
	 * @param loanCode loan code
	 * @param investMoney investments
	 */
	@Override
	@Transactional
	public void investRequest(String email, int loanCode, int investMoney) {
		// Add parameter to hash map
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("email", email);
		map.put("loanCode", loanCode);
		map.put("investMoney", investMoney);
		
		// 1. Withdraw at account
		accountDao.withdraw(map);
		
		// 2. Deposit to loans
		accountDao.deposit(map);
		
		// 3. Increase number of investment
		memberDao.incInvest(email);
		
		// 4. Change information of loan
		loanDao.investAfter(map);
		
		// 5. Add a record of investment
		investDao.addInvest(new ABCInvestDto(email, loanCode, investMoney));
		
	}
	
	/**
	 * Check if fund is complete
	 * @param loanCode loan code
	 */
	@Override
	@Transactional
	public void checkComplete(int loanCode) {
		// Get a loan by loan code
		ABCLoanDto loan = loanDao.getLoan(loanCode);
		
		// If current money have reached the goal
		if(loan.getCurrentMoney() == loan.getLoanMoney()) {
			// Set request date to first date of repayments
			String repayDate = ABCUtility.calcRepayDate(loan.getRepay());
			loan.setProgress(ABCProgressType.REPAYING.getCode());
			loan.setRequestDate(repayDate);
			loanDao.fundComplete(loan);
			
			// Deposit to loan by loan code
			accountDao.depositForRequest(loanCode);
			
			// Withdraw at account by loan code
			accountDao.withdrawByLoan(loanCode);
		}
	}
	
	/**
	 * Check member's investments
	 * @param email A email
	 * @param loanCode loan code
	 * @return Return true if invested
	 */
	@Override
	public boolean isInvested(String email, int loanCode) {
		// A variable that check if invested
		boolean isInvested = false;
		
		// A hash map to store email and loan code
		Map<String, Object> map = new HashMap<String, Object>();
		
		// put the email and loan code to map
		map.put("email", email);
		map.put("loanCode", loanCode);
		
		// Check if already invested that loan
		ABCInvestDto invest = investDao.selectByEnL(map);
		
		// If a DTO of investment is not null, then the investor has already invested the loan
		if(invest != null) isInvested = true;
		
		return isInvested;
	}
	
	/**
	 * Get list of investment detail
	 * @param email A members's email
	 */
	@Override
	public List<ABCInvestTransactionDto> getInvestTransaction(String email) {
		List<ABCInvestTransactionDto> investTran = investTranDao.getTransaction(email);
		
		// Set progress
		for(ABCInvestTransactionDto tran : investTran) {
			tran.setProgress(ABCProgressType.findName(tran.getProgress()));
		}
		
		return investTran;
	}
	
	/**
	 * Get list of amount of investments
	 * @param email A member's email
	 * @return Return list of investment sorted by descend
	 */
	@Override
	public List<ABCInvestDto> getInvestMoneyList(String email) {
		return investDao.getInvestMoneyList(email);
	}
	
	/**
	 * Get list of investments
	 * @param email A member's email
	 */
	@Override
	public List<ABCInvestDto> getInvestList(String email) {
		return investDao.getInvestList(email);
	}
	
	/** Get list of investment detail
	 *	@param email A member's email
	 */
	@Override
	public List<ABCInvestTransactionDto> getTransactionReserve(String email) {
		return investTranDao.getTransactionReserve(email);
	}

}