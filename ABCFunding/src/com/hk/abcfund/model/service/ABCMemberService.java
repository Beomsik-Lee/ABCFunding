package com.hk.abcfund.model.service;

import java.security.PrivateKey;
import java.util.ArrayList;
import java.util.List;

import com.hk.abcfund.model.dto.ABCMemberDto;
import com.hk.abcfund.model.dto.ABCMyInfoDto;
import com.hk.abcfund.model.dto.ABCMyLoanInfoDto;

/**
 * Service interface for membership
 * @author 9age
 *
 */
public interface ABCMemberService {
	void addMemeber(ABCMemberDto dto, PrivateKey privateKey);
	void doAuthMember(ABCMemberDto dto);
	ABCMemberDto login(ABCMemberDto dto, PrivateKey privateKey);
	void changePwd(ABCMemberDto dto);
	void changeAuthCode(ABCMemberDto dto);
	void changePwdOnAuth(ABCMemberDto dto);
	ABCMyInfoDto getMyInfo(String email);
	ArrayList<ABCMyLoanInfoDto> getMyLoanInfoList(String email);
	void dropMember(String email);
	List<ABCMemberDto> getMemberList();
	ABCMemberDto getMember(String email);
}