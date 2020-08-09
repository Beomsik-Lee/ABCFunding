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
 * 대출 신청에 대한 DAO 클래스
 * @author 9age
 *
 */
@Repository
public class ABCLoanDaoImpl implements ABCLoanDao {
	@Autowired
	private SqlSession sqlSession;
	
	/** MyBatis 대출신청 네임스페이스 */
	private String nameSpace = "ABCLoan.";

	/**
	 * 대출 신청 정보 등록
	 */
	@Override
	public void addLoan(ABCLoanDto ldto) {
		sqlSession.insert(nameSpace + "addLoan", ldto);
	}
	
	/**
	 * 회원 신용등급 등록
	 */
	@Override
	public void addCreditRating(ABCLoanSubDto lsdto) {
		sqlSession.update(nameSpace + "addCreditRating", lsdto);
	}

	/**
	 * 심사 대기 등록을 위한 대출 코드 얻어오기
	 */
	@Override
	public int getLoanCode() {
		return (int) sqlSession.selectOne(nameSpace + "getLoanCode");
	}

	/**
	 * 대출 신청 후 심사 대기 등록
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
