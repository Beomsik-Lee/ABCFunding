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
 * ȸ�� �� ���� ���� Ŭ����
 * @author 9age
 *
 */
@Service
public class ABCMemberServiceImpl implements ABCMemberService {
	/** ȸ���� DAO */
	@Autowired
	private ABCMemberDao dao;
	
	/** ������ DAO */
	@Autowired
	private ABCAccountDao accDao;
	
	/** ������ DAO */
	@Autowired
	private ABCLoanDao loanDao;
	
	/** ������ DAO */
	@Autowired
	private ABCInvestDao investDao;
	
	/** ������ DAO */
	@Autowired
	private ABCAdminDao adminDao;
	
	/** log4j �α�  */
	private static final Logger logger = LoggerFactory
			.getLogger(ABCMemberController.class);
	
	/**
	 * ȸ�� ��� �޼���
	 * ȸ�� ��ϰ� ���ÿ� ������µ� �߱�
	 * @param dto ȸ�� ��ü
	 */
	@Override
	public void addMemeber(ABCMemberDto dto) {
		// �ش� ȸ�� �ſ��� ����
		int creditRating = Integer.parseInt(ABCUtility.randomNumber(1));
		if(creditRating == 0) creditRating = 9;
		dto.setCreditRating(creditRating);
		
		// ȸ�� ���
		dao.addMemeber(dto);
		
		// ������� ���
		ABCAccountDto accDto = 
			new ABCAccountDto(ABCUtility.createVANumber(), dto.getEmail(), dto.getName());		
		accDao.addAccount(accDto);
	}
	
	/**
	 * ȸ�� ����� ���������� �Ϲ�ȸ������ �����ϴ� �޼���
	 * @param dto ȸ�� ��ü
	 */
	@Override
	public void doAuthMember(ABCMemberDto dto) {
		dao.doAuthCode(dto);
	}
	
	/**
	 * �α����ϴ� �޼���
	 * @param dto ȸ�� ��ü
	 * @return �α��� ������ true
	 */
	@Override
	public ABCMemberDto login(ABCMemberDto dto) {
		return dao.login(dto);
	}
	
	/**
	 * �� �������� ��й�ȣ ������ �� ȣ���ϴ� �޼���
	 * @param dto ȸ�� ��ü
	 */
	@Override
	public void changePwd(ABCMemberDto dto) {
		dao.changePwd(dto);
	}
	
	/**
	 * ȸ���� �����ڵ带 �����ϴ� �޼���.
	 * ��й�ȣ ã��� �̸��� �����Ϸ��� ��� ���
	 * @param dto ȸ�� ��ü
	 */
	@Override
	public void changeAuthCode(ABCMemberDto dto) {
		dao.changeAuthCode(dto);
	}
	
	/**
	 * �̸��� �������� ��й�ȣ�� ������ �� ����ϴ� �޼���
	 * @param dto ȸ�� ��ü
	 */
	@Override
	public void changePwdOnAuth(ABCMemberDto dto) {
		dao.changePwdOnAuth(dto);
	}
	
	/** ���� ���� ������ �޼��� */
	@Override
	public ABCMyInfoDto getMyInfo(String email) {
		return dao.getMyInfo(email);
	}
	
	/** ���� ��������Ʈ�� ������ �޼��� */
	@Override
	public ArrayList<ABCMyLoanInfoDto> getMyLoanInfoList(String email) {
		return dao.getMyLoanInfoList(email);
	}
	
	/** 
	 * ȸ��Ż�� ��û�ϴ� �޼��� 
	 * @param member ȸ���� DTO
	 */
	@Override
	@Transactional
	public void dropMember(String email) {
		// ���� �������� �̸����� ������ ����
		loanDao.updateEmailToDump(email);
		
		// ������� ����
		accDao.deleteAccount(email);
		
		// ȸ�� ����
		dao.dropMember(email);
	}
	
	/**
	 * �����ڿ� ���̸� ������ ��� ȸ������Ʈ �������� �޼���
	 * @return ȸ�� ����Ʈ
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
