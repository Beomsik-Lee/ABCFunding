package com.hk.abcfund.model.dao;

import java.util.List;

import com.hk.abcfund.model.dto.ABCLoanDto;
import com.hk.abcfund.model.dto.ABCLoanTransactionDto;

/**
 * 대출내역DAO 인터페이스
 * @author 9age
 *
 */
public interface ABCLoanTransactionDao {
	ABCLoanTransactionDto getRecentTransaction(int loanCode);
	void addTransaction(ABCLoanTransactionDto loan);
	List<ABCLoanTransactionDto> getTransactionBySorted(String email);
}
