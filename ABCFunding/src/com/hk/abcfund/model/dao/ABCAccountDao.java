package com.hk.abcfund.model.dao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataIntegrityViolationException;

import com.hk.abcfund.model.dto.ABCAccountDto;
import com.hk.abcfund.model.dto.ABCInvestDto;

/**
 * 계좌 DAO 인터페이스
 * @author 9age
 *
 */
public interface ABCAccountDao {
	void addAccount(ABCAccountDto dto);
	ABCAccountDto getAccount(String email);
	void withdraw(Map<String, Object> map);
	void deposit(Map<String, Object> map);
	void addForLoan(Map<String, Object> map);
	void updateForPayback(List<ABCInvestDto> list);
	void deleteByLoan(int loanCode);
	void depositForRequest(int loanCode);
	void withdrawByLoan(int loanCode);
	void depositForRepay(Map<String, Object> map);
	void depositForAdmin(int money);
	void withdrawForInvest(Map<String, Object> map);
	void deleteAccount(String email);
	int getBalance(String email);
}
