package com.hk.abcfund.model.dao;

import java.util.List;

import com.hk.abcfund.model.dto.ABCLoanTransactionDto;

/**
 * DAO interface for transaction of loan
 * @author 9age
 *
 */
public interface ABCLoanTransactionDao {
	ABCLoanTransactionDto getRecentTransaction(int loanCode);
	void addTransaction(ABCLoanTransactionDto loan);
	List<ABCLoanTransactionDto> getTransactionBySorted(String email);
}
