package com.hk.abcfund.model.dao;

import java.util.List;
import java.util.Map;

import com.hk.abcfund.model.dto.ABCLoanDto;
import com.hk.abcfund.model.dto.ABCLoanSubDto;

/**
 * 대출신청에 대한 DAO 인터페이스
 * @author 9age
 *
 */
public interface ABCLoanDao {
	void addLoan(ABCLoanDto ldto);
	void addCreditRating(ABCLoanSubDto lsdto);
	int getLoanCode();
	void addJudge(int lcode);
	List<ABCLoanDto> getLoanList();
	ABCLoanDto getLoan(int loanCode);
	void investAfter(Map<String, Object> map);
	void updateProgress(ABCLoanDto loan);
	List<ABCLoanDto> getLoanListAll(String email);
	void deleteLoan(int loanCode);
	void fundComplete(ABCLoanDto loan);
	void updateRound(ABCLoanDto loan);
	void repayComplete(ABCLoanDto loan);
	void extendLoanDate(ABCLoanDto loan);
	void nextRepayDate(ABCLoanDto loan);
	List<ABCLoanDto> getRemainPayment(String email);
	void updateEmailToDump(String email);
	List<ABCLoanDto> getLoanListByInvest(String email);
	int getPayingCount(String email);
}
