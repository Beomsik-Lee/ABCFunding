/**
 * 
 */
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
import com.hk.abcfund.model.service.ABCLoanServiceImpl;

/**
 * ������ ȭ���� DAO Ŭ����
 * @author 9age
 *
 */
@Repository
public class ABCAdminDaoImpl implements ABCAdminDao {
	private static final long serialVersionUID = -5750103480903548432L;

	@Autowired
	private SqlSession sqlSession;

	/** MyBatis ������ ���ӽ����̽� */
	private String nameSpace = "ABCAdmin.";
	
	private static final Logger logger = LoggerFactory
			.getLogger(ABCLoanDaoImpl.class);
	
	/**
	 * �ɻ���°� ����� ����Ʈ�� ��ȸ
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<ABCJudgeDto> getJudgeList() {
		return (List<ABCJudgeDto>)sqlSession.selectList(nameSpace+"getJudgeList");
	}

	/**
	 * �ɻ���°� ����� �ɻ���� �� ���� ��ȸ
	 * @param loanCode �ش� �ɻ���� �����û�ڵ�
	 */
	@Override
	public ABCJudgeDto getJudgeDetail(int loanCode) {
		return (ABCJudgeDto)sqlSession.selectOne(nameSpace+"getJudgeDetail", loanCode);
	}

	/**
	 * �ɻ��� ������ ����
	 * @param ABCJudgeResultDto �ɻ��� ������ ���� DTO ��ü
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
	 * �������Ǽ��� �������� ��ȸ
	 */
	@Override
	public ABCAdminLoanManageDto getAdminLoanInfo1() {
		logger.info("getAdminLoanInfo1() ȣ�� ����!" + new Date());
		return (ABCAdminLoanManageDto)sqlSession.selectOne(nameSpace+"getAdminLoanInfo1");
	}

	/**
	 * ���������ȯ�� ��ȸ
	 */
	@Override
	public ABCAdminLoanManageDto getAdminLoanInfo2() {
		logger.info("getAdminLoanInfo2() ȣ�� ����!" + new Date());
		return (ABCAdminLoanManageDto)sqlSession.selectOne(nameSpace+"getAdminLoanInfo2");
	}

	/**
	 * ��ȯ�Ϸ� �Ǽ� ��ȸ
	 */
	@Override
	public ABCAdminLoanManageDto getAdminLoanInfo3() {
		logger.info("getAdminLoanInfo3() ȣ�� ����!" + new Date());
		return (ABCAdminLoanManageDto)sqlSession.selectOne(nameSpace+"getAdminLoanInfo3");
	}

	/**
	 * ��ȯ���� �Ǽ�
	 */
	@Override
	public ABCAdminLoanManageDto getAdminLoanInfo4() {
		logger.info("getAdminLoanInfo4() ȣ�� ����!" + new Date());
		return (ABCAdminLoanManageDto)sqlSession.selectOne(nameSpace+"getAdminLoanInfo4");
	}
}
