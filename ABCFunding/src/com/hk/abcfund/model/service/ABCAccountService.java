package com.hk.abcfund.model.service;

import java.util.Map;

import com.hk.abcfund.model.dto.ABCAccountDto;

/**
 * Account service interface
 * @author 9age
 */
public interface ABCAccountService {
	ABCAccountDto getAccount(String email);
	void accountForLoan(int loanCode);
	void depositAtMyInfo(Map<String, Object> map);
}