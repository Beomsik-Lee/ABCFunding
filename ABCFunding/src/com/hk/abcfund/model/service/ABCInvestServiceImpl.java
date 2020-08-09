package com.hk.abcfund.model.service;

import java.sql.SQLIntegrityConstraintViolationException;
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
 * 투자 관련 서비스 구현 클래스
 * @author 9age
 *
 */
@Service
public class ABCInvestServiceImpl implements ABCInvestService {
	/** 회원 DAO */
	@Autowired
	private ABCMemberDao memberDao;
	
	/** 대출 DAO */
	@Autowired
	private ABCLoanDao loanDao;
	
	/** 심사 DAO */
	@Autowired
	private ABCAdminDao adminDao;
	
	/** 계정 DAO */
	@Autowired
	private ABCAccountDao accountDao;
	
	/** 투자 DAO */
	@Autowired
	private ABCInvestDao investDao;
	
	/** 투자내역 DAO */
	@Autowired
	private ABCInvestTransactionDao investTranDao;
	
	/**
	 * 입력 받은 각각의 DTO에 DAO의 결과를 넣습니다.
	 * @param member 회원 DTO
	 * @param loan 대출 DTO
	 * @param judge 심사 DTO
	 */
	@Override
	@Transactional
	public void getInvestDetail(List<Object> list, int loanCode) {
		// 1. 대출코드를 이용하여 대출데이터를 가져온다.
		ABCLoanDto loan = loanDao.getLoan(loanCode);
		
		// 2. 가져온 대출데이터에서 해당 이메일을 가진 회원의 데이터를 가져온다.
		ABCMemberDto member = memberDao.getMember(loan.getEmail());
		
		// 3. 대출코드를 이용하여 심사데이터를 가져온다.
		ABCJudgeResultDto judge = adminDao.getJudge(loanCode);
		
		// 가져온 객체들을 리스트에 담아둔다.
		list.add(loan);
		list.add(member);
		list.add(judge);
	}
	
	/**
	 * 투자가 이루어지는 메서드
	 * @param email 투자자를 식별할 이메일
	 * @param loanCode 대출상품을 식별할 대출코드
	 * @param investMoney 투자금
	 */
	@Override
	@Transactional
	public void investRequest(String email, int loanCode, int investMoney) {
		// 매개변수를 해쉬맵에 저장
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("email", email);
		map.put("loanCode", loanCode);
		map.put("investMoney", investMoney);
		
		// 1. 내 돈이 계좌에서 차감
		try{
			accountDao.withdraw(map);
		} catch(Exception e){
			// 프론트 엔드에서 체크된 부분이라 필요없다.
		}
		
		// 2. 대출상품의 계좌로 입금
		accountDao.deposit(map);
		
		// 3. 투자자의 회원 데이터에서 투자건수 증가
		memberDao.incInvest(email);
		
		// 4. 대출상품 정보 변경
		loanDao.investAfter(map);
		
		// 5. 투자 레코드 추가
		investDao.addInvest(new ABCInvestDto(email, loanCode, investMoney));
		
	}
	
	/**
	 * 펀딩이 완료되었는지 확인하는 메서드
	 * @param loanCode 펀딩완료를 확인할 대출의 대출코드
	 * @return 완료여부
	 */
	@Override
	@Transactional
	public void checkComplete(int loanCode) {
		// 해당 대출코드의 대출 데이터 가져오기
		ABCLoanDto loan = loanDao.getLoan(loanCode);
		
		// 현재금액이 목표금액과 일치하는 경우
		if(loan.getCurrentMoney() == loan.getLoanMoney()) {
			// requestDate를 첫 상환날짜로 바꾼다.
			String repayDate = ABCUtility.calcRepayDate(loan.getRepay());
			loan.setProgress("펀딩완료");
			loan.setRequestDate(repayDate);
			loanDao.fundComplete(loan);
			
			// 대출신청자에게 신청금 전달
			accountDao.depositForRequest(loanCode);
			
			// 해당 대출의 가상계좌에서 잔액 초기화
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
