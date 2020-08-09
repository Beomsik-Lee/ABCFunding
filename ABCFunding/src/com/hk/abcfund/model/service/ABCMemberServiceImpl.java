package com.hk.abcfund.model.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hk.abcfund.controller.ABCMemberController;
import com.hk.abcfund.model.dao.ABCAccountDao;
import com.hk.abcfund.model.dao.ABCAdminDao;
import com.hk.abcfund.model.dao.ABCInvestDao;
import com.hk.abcfund.model.dao.ABCLoanDao;
import com.hk.abcfund.model.dao.ABCMemberDao;
import com.hk.abcfund.model.dto.ABCAccountDto;
import com.hk.abcfund.model.dto.ABCInvestDto;
import com.hk.abcfund.model.dto.ABCLoanDto;
import com.hk.abcfund.model.dto.ABCMemberDto;
import com.hk.abcfund.model.dto.ABCMyInfoDto;
import com.hk.abcfund.model.dto.ABCMyLoanInfoDto;
import com.hk.abcfund.util.ABCUtility;

/**
 * 회원 및 계좌 서비스 클래스
 * @author 9age
 *
 */
@Service
public class ABCMemberServiceImpl implements ABCMemberService {
	/** 회원의 DAO */
	@Autowired
	private ABCMemberDao dao;
	
	/** 계정의 DAO */
	@Autowired
	private ABCAccountDao accDao;
	
	/** 대출의 DAO */
	@Autowired
	private ABCLoanDao loanDao;
	
	/** 투자의 DAO */
	@Autowired
	private ABCInvestDao investDao;
	
	/** 관리자 DAO */
	@Autowired
	private ABCAdminDao adminDao;
	
	/** log4j 로깅  */
	private static final Logger logger = LoggerFactory
			.getLogger(ABCMemberController.class);
	
	/**
	 * 회원 등록 메서드
	 * 회원 등록과 동시에 가상계좌도 발급
	 * @param dto 회원 객체
	 */
	@Override
	public void addMemeber(ABCMemberDto dto) {
		// 해당 회원 신용등급 생성
		int creditRating = Integer.parseInt(ABCUtility.randomNumber(1));
		if(creditRating == 0) creditRating = 9;
		dto.setCreditRating(creditRating);
		
		// 회원 등록
		dao.addMemeber(dto);
		
		// 가상계좌 등록
		ABCAccountDto accDto = 
			new ABCAccountDto(ABCUtility.createVANumber(), dto.getEmail(), dto.getName());		
		accDao.addAccount(accDto);
	}
	
	/**
	 * 회원 등급을 미인증에서 일반회원으로 변경하는 메서드
	 * @param dto 회원 객체
	 */
	@Override
	public void doAuthMember(ABCMemberDto dto) {
		dao.doAuthCode(dto);
	}
	
	/**
	 * 로그인하는 메서드
	 * @param dto 회원 객체
	 * @return 로그인 성공시 true
	 */
	@Override
	public ABCMemberDto login(ABCMemberDto dto) {
		return dao.login(dto);
	}
	
	/**
	 * 내 정보에서 비밀번호 변경할 때 호출하는 메서드
	 * @param dto 회원 객체
	 */
	@Override
	public void changePwd(ABCMemberDto dto) {
		dao.changePwd(dto);
	}
	
	/**
	 * 회원의 인증코드를 변경하는 메서드.
	 * 비밀번호 찾기로 이메일 인증하려는 경우 사용
	 * @param dto 회원 객체
	 */
	@Override
	public void changeAuthCode(ABCMemberDto dto) {
		dao.changeAuthCode(dto);
	}
	
	/**
	 * 이메일 인증으로 비밀번호를 변경할 때 사용하는 메서드
	 * @param dto 회원 객체
	 */
	@Override
	public void changePwdOnAuth(ABCMemberDto dto) {
		dao.changePwdOnAuth(dto);
	}
	
	/** 나의 정보 얻어오는 메서드 */
	@Override
	public ABCMyInfoDto getMyInfo(String email) {
		return dao.getMyInfo(email);
	}
	
	/** 나의 정보리스트를 얻어오는 메서드 */
	@Override
	public ArrayList<ABCMyLoanInfoDto> getMyLoanInfoList(String email) {
		return dao.getMyLoanInfoList(email);
	}
	
	/** 
	 * 회원탈퇴 요청하는 메서드 
	 * @param member 회원의 DTO
	 */
	@Override
	@Transactional
	public void dropMember(String email) {
		// 대출 데이터의 이메일을 덤프로 변경
		loanDao.updateEmailToDump(email);
		
		// 가상계좌 삭제
		accDao.deleteAccount(email);
		
		// 회원 삭제
		dao.dropMember(email);
	}
	
	/**
	 * 관리자와 더미를 제외한 모든 회원리스트 가져오는 메서드
	 * @return 회원 리스트
	 */
	@Override
	public List<ABCMemberDto> getMemberList() {
		return dao.getMemberList();
	}

	@Override
	public ABCMemberDto getMember(String email) {
		return dao.getMember(email);
	}
}
