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
 * 관리자 화면의 DAO 클래스
 * @author 9age
 *
 */
@Repository
public class ABCAdminDaoImpl implements ABCAdminDao {
	private static final long serialVersionUID = -5750103480903548432L;

	@Autowired
	private SqlSession sqlSession;

	/** MyBatis 관리자 네임스페이스 */
	private String nameSpace = "ABCAdmin.";
	
	private static final Logger logger = LoggerFactory
			.getLogger(ABCLoanDaoImpl.class);
	
	/**
	 * 심사상태가 대기인 리스트를 조회
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<ABCJudgeDto> getJudgeList() {
		return (List<ABCJudgeDto>)sqlSession.selectList(nameSpace+"getJudgeList");
	}

	/**
	 * 심사상태가 대기인 심사건의 상세 내용 조회
	 * @param loanCode 해당 심사건의 대출신청코드
	 */
	@Override
	public ABCJudgeDto getJudgeDetail(int loanCode) {
		return (ABCJudgeDto)sqlSession.selectOne(nameSpace+"getJudgeDetail", loanCode);
	}

	/**
	 * 심사결과 정보를 갱신
	 * @param ABCJudgeResultDto 심사결과 정보를 담은 DTO 객체
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
	 * 대출실행건수와 대출실행금 조회
	 */
	@Override
	public ABCAdminLoanManageDto getAdminLoanInfo1() {
		logger.info("getAdminLoanInfo1() 호출 성공!" + new Date());
		return (ABCAdminLoanManageDto)sqlSession.selectOne(nameSpace+"getAdminLoanInfo1");
	}

	/**
	 * 누적대출상환금 조회
	 */
	@Override
	public ABCAdminLoanManageDto getAdminLoanInfo2() {
		logger.info("getAdminLoanInfo2() 호출 성공!" + new Date());
		return (ABCAdminLoanManageDto)sqlSession.selectOne(nameSpace+"getAdminLoanInfo2");
	}

	/**
	 * 상환완료 건수 조회
	 */
	@Override
	public ABCAdminLoanManageDto getAdminLoanInfo3() {
		logger.info("getAdminLoanInfo3() 호출 성공!" + new Date());
		return (ABCAdminLoanManageDto)sqlSession.selectOne(nameSpace+"getAdminLoanInfo3");
	}

	/**
	 * 상환중인 건수
	 */
	@Override
	public ABCAdminLoanManageDto getAdminLoanInfo4() {
		logger.info("getAdminLoanInfo4() 호출 성공!" + new Date());
		return (ABCAdminLoanManageDto)sqlSession.selectOne(nameSpace+"getAdminLoanInfo4");
	}
}
