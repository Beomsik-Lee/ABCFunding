package com.hk.abcfund.model.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hk.abcfund.model.dao.ABCAccountDao;
import com.hk.abcfund.model.dao.ABCLoanDao;
import com.hk.abcfund.model.dto.ABCAccountDto;
import com.hk.abcfund.model.dto.ABCLoanDto;
import com.hk.abcfund.util.ABCUtility;

/**
 * 계좌관련 서비스 클래스
 * @author 9age
 *
 */
@Service
public class ABCAccountServiceImpl implements ABCAccountService {
	/** 계좌DAO */
	@Autowired
	private ABCAccountDao accountDao;
	
	/** 대출 DAO */
	@Autowired
	private ABCLoanDao loanDao;
	
	/**
	 * 대출코드로 계정데이터 가져오기
	 * @param loanCode 참조할 대출코드
	 * @return 계정 데이터가 담긴 DTO
	 */
	@Override
	public ABCAccountDto getAccount(String email) {
		return accountDao.getAccount(email);
	}
	
	/**
	 * 승인받은 대출상품에 대한 가상계좌를 생성
	 * @param loanCode 대출코드
	 */
	@Override
	@Transactional
	public void accountForLoan(int loanCode) {
		// 여러 데이터를 담을 해쉬맵 생성
		Map<String, Object> map = new HashMap<String, Object>();
		
		// 1. 대출코드로 대출데이터 가져오기 및 제목 설정
		ABCLoanDto loan = loanDao.getLoan(loanCode);
		
		// 만약 대출상품(제목)이 12바이트를 넘는 경우 절삭
		String title = loan.getTitle();
		if(title.getBytes().length > 12) {
			title = title.substring(0, 6);
		}
		
		// 2. 가상계좌번호 생성
		String accountNo = ABCUtility.createVANumber();
		
		// 3. 맵에다 담기
		map.put("accountNo", accountNo);
		map.put("loanCode", loanCode);
		map.put("title", title);
		
		// 4. 계좌생성 요청
		accountDao.addForLoan(map);
	}

	@Override
	public void depositAtMyInfo(Map<String, Object> map) {
		accountDao.depositForRepay(map);
	}
}
