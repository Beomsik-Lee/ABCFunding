/**
 * 
 */
package com.hk.abcfund.model.service;

import java.util.List;

import com.hk.abcfund.model.dto.ABCLoanDto;
import com.hk.abcfund.model.dto.ABCLoanTransactionDto;

/**
 * 대출신청에 대한 서비스 인터페이스
 * @author 9age
 *
 */
public interface ABCLoanService {
	void addLoan(ABCLoanDto ldto);
	List<ABCLoanDto> getLoanList();
	ABCLoanDto getLoan(int loanCode);
	List<ABCLoanDto> getLoanListAll(String email);
	void loanCancel(int loanCode);
	void checkRepay();
	List<ABCLoanDto> getRemainPayment(String email);
	List<ABCLoanDto> getLoanListByInvest(String email);
	List<ABCLoanTransactionDto> getTransactionBySorted(String email);
	int getPayingCount(String email);
	
}
