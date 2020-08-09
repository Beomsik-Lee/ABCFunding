package com.hk.abcfund.model.service;

import java.util.Map;

import com.hk.abcfund.model.dto.ABCAccountDto;

/**
 * 계좌 관련 서비스 인터페이스
 * @author 9age
 */
public interface ABCAccountService {
	ABCAccountDto getAccount(String email);
	void accountForLoan(int loanCode);
	void depositAtMyInfo(Map<String, Object> map);
}
