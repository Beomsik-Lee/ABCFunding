package com.hk.abcfund.model.dao;

import java.util.ArrayList;
import java.util.List;

import com.hk.abcfund.model.dto.ABCMemberDto;
import com.hk.abcfund.model.dto.ABCMyInfoDto;
import com.hk.abcfund.model.dto.ABCMyLoanInfoDto;

/**
 * DAO interface for member
 * @author Beom
 *
 */
public interface ABCMemberDao {
	void addMemeber(ABCMemberDto dto);
	void doAuthCode(ABCMemberDto dto);
	ABCMemberDto login(ABCMemberDto dto);
	void changePwd(ABCMemberDto dto);
	void changeAuthCode(ABCMemberDto dto);
	void changePwdOnAuth(ABCMemberDto dto);
	ABCMemberDto getMember(String email);
	void incInvest(String email);
	void incLoan(String email);
	ABCMyInfoDto getMyInfo(String email);
	ArrayList<ABCMyLoanInfoDto> getMyLoanInfoList(String email);
	void dropMember(String email);
	List<ABCMemberDto> getMemberList();
}
