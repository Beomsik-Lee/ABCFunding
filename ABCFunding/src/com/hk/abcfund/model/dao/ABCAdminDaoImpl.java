package com.hk.abcfund.model.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hk.abcfund.model.dto.ABCAdminLoanManageDto;
import com.hk.abcfund.model.dto.ABCJudgeDto;
import com.hk.abcfund.model.dto.ABCJudgeResultDto;

/**
 * DAO implements for administrator
 * @author 9age
 *
 */
@Repository
public class ABCAdminDaoImpl implements ABCAdminDao {
	private static final long serialVersionUID = -5750103480903548432L;

	@Autowired
	private SqlSession sqlSession;

	/** name space of administrator */
	private String nameSpace = "ABCAdmin.";
	
	private static final Logger logger = LoggerFactory
			.getLogger(ABCLoanDaoImpl.class);
	
	/**
	 * Search for list that waiting for audit
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<ABCJudgeDto> getJudgeList() {
		return (List<ABCJudgeDto>)sqlSession.selectList(nameSpace+"getJudgeList");
	}

	/**
	 * Search for detail of waiting audit
	 * @param loanCode loan code
	 */
	@Override
	public ABCJudgeDto getJudgeDetail(int loanCode) {
		return (ABCJudgeDto)sqlSession.selectOne(nameSpace+"getJudgeDetail", loanCode);
	}

	/**
	 * Update the result of audit
	 * @param ABCJudgeResultDto A DTO for result of audit
	 */
	@Override
	public void addJudgeResult(ABCJudgeResultDto jrdto) {
		sqlSession.update(nameSpace+"addJudgeResult", jrdto);
	}

	@Override
	public ABCJudgeResultDto getJudge(int loanCode) {
		return (ABCJudgeResultDto)sqlSession.selectOne(nameSpace + "getJudge", loanCode);
	}

	@Override
	public void deleteJudgeByLoan(int loanCode) {
		sqlSession.delete(nameSpace + "deleteJudgeByLoan", loanCode);
	}
	
	/**
	 * Search for number of loans
	 */
	@Override
	public ABCAdminLoanManageDto getAdminLoanInfo1() {
		logger.info("getAdminLoanInfo1() Called!" + new Date());
		return (ABCAdminLoanManageDto)sqlSession.selectOne(nameSpace+"getAdminLoanInfo1");
	}

	/**
	 * Search for accumulated repayments of loan
	 */
	@Override
	public ABCAdminLoanManageDto getAdminLoanInfo2() {
		logger.info("getAdminLoanInfo2() Called!" + new Date());
		return (ABCAdminLoanManageDto)sqlSession.selectOne(nameSpace+"getAdminLoanInfo2");
	}

	/**
	 * Search for complement of repayments
	 */
	@Override
	public ABCAdminLoanManageDto getAdminLoanInfo3() {
		logger.info("getAdminLoanInfo3() Called!" + new Date());
		return (ABCAdminLoanManageDto)sqlSession.selectOne(nameSpace+"getAdminLoanInfo3");
	}

	/**
	 * Search for number of repaying
	 */
	@Override
	public ABCAdminLoanManageDto getAdminLoanInfo4() {
		logger.info("getAdminLoanInfo4() Called!" + new Date());
		return (ABCAdminLoanManageDto)sqlSession.selectOne(nameSpace+"getAdminLoanInfo4");
	}
}