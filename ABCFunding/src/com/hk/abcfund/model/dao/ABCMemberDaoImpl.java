package com.hk.abcfund.model.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hk.abcfund.model.dto.ABCMemberDto;
import com.hk.abcfund.model.dto.ABCMyInfoDto;
import com.hk.abcfund.model.dto.ABCMyLoanInfoDto;

/**
 * @author 9age
 *
 */
@Repository
public class ABCMemberDaoImpl implements ABCMemberDao {
	@Autowired
	private SqlSession sqlSession;
	
	/** MyBatis 회원 네임스페이스 */
	private String nameSpace = "ABCMember.";
	
	@Override
	public void addMemeber(ABCMemberDto dto) {
		sqlSession.insert(nameSpace + "addMember", dto);		
	}

	@Override
	public void doAuthCode(ABCMemberDto dto) {
		sqlSession.update(nameSpace + "doAuthCode", dto);
	}

	@Override
	public ABCMemberDto login(ABCMemberDto dto) {
		return (ABCMemberDto)(sqlSession.selectOne(nameSpace + "login", dto));
	}

	@Override
	public void changePwd(ABCMemberDto dto) {
		sqlSession.update(nameSpace + "changePwd", dto);
	}

	@Override
	public void changeAuthCode(ABCMemberDto dto) {
		sqlSession.update(nameSpace + "changeAuthCode", dto);
	}

	@Override
	public void changePwdOnAuth(ABCMemberDto dto) {
		sqlSession.update(nameSpace + "changePwdOnAuth", dto);
	}

	@Override
	public ABCMemberDto getMember(String email) {
		return (ABCMemberDto)sqlSession.selectOne(nameSpace + "getMember", email);
	}

	@Override
	public void incInvest(String email) {
		sqlSession.update(nameSpace + "incInvest", email);
	}

	@Override
	public void incLoan(String email) {
		sqlSession.update(nameSpace + "incLoan", email);
	}
	
	@Override
	public ABCMyInfoDto getMyInfo(String email) {
		return (ABCMyInfoDto)sqlSession.selectOne(nameSpace+"getMyInfo",email);
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<ABCMyLoanInfoDto> getMyLoanInfoList(String email) {
		return (ArrayList<ABCMyLoanInfoDto>)sqlSession.selectList(nameSpace+"getMyLoanInfoList", email);
	}

	@Override
	public void dropMember(String email) {
		sqlSession.delete(nameSpace + "dropMember", email);
	}

	@Override
	public List<ABCMemberDto> getMemberList() {
		return sqlSession.selectList(nameSpace + "getMemberList");
	}

}
