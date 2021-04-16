/**
 * 
 */
package com.hk.abcfund.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hk.abcfund.model.dto.ABCLoanDto;
import com.hk.abcfund.model.dto.ABCLoanSubDto;

/**
 * DAO implement for loan
 * @author 9age
 *
 */
@Repository
public class ABCLoanDaoImpl implements ABCLoanDao {
	@Autowired
	private SqlSession sqlSession;
	
	/** name space */
	private String nameSpace = "ABCLoan.";

	/**
	 * Add the loan
	 */
	@Override
	public void addLoan(ABCLoanDto ldto) {
		sqlSession.insert(nameSpace + "addLoan", ldto);
	}
	
	/**
	 * Add credit rating of member
	 */
	@Override
	public void addCreditRating(ABCLoanSubDto lsdto) {
		sqlSession.update(nameSpace + "addCreditRating", lsdto);
	}

	/**
	 * Get code of loan waiting audit
	 */
	@Override
	public int getLoanCode() {
		return (int) sqlSession.selectOne(nameSpace + "getLoanCode");
	}

	/**
	 * Insert audit waiting after request the loan
	 */
	@Override
	public void addJudge(int lcode) {
		sqlSession.insert(nameSpace + "addJudge",lcode);
	}

	@Override
	public List<ABCLoanDto> getLoanList() {
		return sqlSession.selectList(nameSpace+"getLoanList");
	}

	@Override
	public ABCLoanDto getLoan(int loanCode) {
		return (ABCLoanDto)sqlSession.selectOne(nameSpace + "getLoan", loanCode);
	}

	@Override
	public void investAfter(Map<String, Object> map) {
		sqlSession.update(nameSpace + "investAfter", map);
	}

	@Override
	public void updateProgress(ABCLoanDto loan) {
		sqlSession.update(nameSpace + "updateProgress", loan);
	}

	@Override
	public List<ABCLoanDto> getLoanListAll(String email) {
		return sqlSession.selectList(nameSpace + "getLoanListAll", email);
	}

	@Override
	public void deleteLoan(int loanCode) {
		sqlSession.delete(nameSpace + "deleteLoan", loanCode);
	}

	@Override
	public void fundComplete(ABCLoanDto loan) {
		sqlSession.update(nameSpace + "fundComplete", loan);
	}

	@Override
	public void updateRound(ABCLoanDto loan) {
		sqlSession.update(nameSpace + "updateRound", loan);
	}

	@Override
	public void repayComplete(ABCLoanDto loan) {
		sqlSession.update(nameSpace + "repayComplete", loan);
	}

	@Override
	public void extendLoanDate(ABCLoanDto loan) {
		sqlSession.update(nameSpace + "extendLoanDate", loan);
	}

	@Override
	public void nextRepayDate(ABCLoanDto loan) {
		sqlSession.update(nameSpace + "nextRepayDate",loan);
	}

	@Override
	public List<ABCLoanDto> getRemainPayment(String email) {
		return sqlSession.selectList(nameSpace + "getRemainPayment", email);
	}

	@Override
	public void updateEmailToDump(String email) {
		sqlSession.update(nameSpace + "updateEmailToDump", email);
	}

	@Override
	public List<ABCLoanDto> getLoanListByInvest(String email) {
		return sqlSession.selectList(nameSpace + "getLoanListByInvest", email);
	}

	@Override
	public int getPayingCount(String email) {
		return (Integer)sqlSession.selectOne(nameSpace + "getPayingCount", email);
	}

}