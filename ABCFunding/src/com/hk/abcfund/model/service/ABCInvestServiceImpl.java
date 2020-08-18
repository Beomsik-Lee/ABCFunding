package com.hk.abcfund.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
		
		// If current money matches goal
		if(loan.getCurrentMoney() == loan.getLoanMoney()) {
			// Set request date to first repayments date
			String repayDate = ABCUtility.calcRepayDate(loan.getRepay());
			loan.setProgress("펀딩완료");
			loan.setRequestDate(repayDate);
			loanDao.fundComplete(loan);
			
			// Deposit to loan by loan code
			accountDao.depositForRequest(loanCode);
			
			// Withdraw at account by loan code
			accountDao.withdrawByLoan(loanCode);
		}
	}
	
	/**
	 * 해당 회원이 해당 상품에 투자했는지 여부를 확인하는 메서드
	 * @param email 식별할 회원의 이메일
	 * @param loanCode 식별할 상품의 대출코드
	 * @return 투자했으면 true, 아니면 false
	 */
	@Override
	public boolean isInvested(String email, int loanCode) {
		boolean isInvested = false;	// 투표여부를 저장하는 논리형 변수
		
		// 이메일과 대출코드를 저장할 해쉬맵 생성
		Map<String, Object> map = new HashMap<String, Object>();
		
		// 이메일과 대출코드를 해쉬맵에 담는다.
		map.put("email", email);
		map.put("loanCode", loanCode);
		
		// 해쉬맵을 이용하여 해당 대출에 해당 투자자가 있는지 확인해서 가져온다.
		ABCInvestDto invest = investDao.selectByEnL(map);
		
		// 만약 쿼리결과가 존재하여 객체가 널이 아닌 경우라면 이미 투자했다는 것이다.
		if(invest != null) isInvested = true;
		
		return isInvested;
	}
	
	/**
	 * 회원의 투자내역 리스트 가져오기
	 * @param email 회원의 이메일
	 */
	@Override
	public List<ABCInvestTransactionDto> getInvestTransaction(String email) {
		return investTranDao.getTransaction(email);
	}
	
	/**
	 * 회원의 투자금 리스트 가져오기
	 * @param email 회원의 이메일
	 * @return 내림차순으로 정렬된 투자 리스트
	 */
	@Override
	public List<ABCInvestDto> getInvestMoneyList(String email) {
		return investDao.getInvestMoneyList(email);
	}
	
	/**
	 * 회원의 투자 리스트 가져오기 
	 * @param email 회원의 이메일
	 */
	@Override
	public List<ABCInvestDto> getInvestList(String email) {
		return investDao.getInvestList(email);
	}
	
	/** 회원의 투자내역 리스트 가져오기
	 *	@param email 회원의 이메일 
	 */
	@Override
	public List<ABCInvestTransactionDto> getTransactionReserve(String email) {
		return investTranDao.getTransactionReserve(email);
	}

}