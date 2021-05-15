package com.hk.abcfund.model.service;

import java.security.PrivateKey;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hk.abcfund.enums.ABCGenderType;
import com.hk.abcfund.model.dao.ABCAccountDao;
import com.hk.abcfund.model.dao.ABCLoanDao;
import com.hk.abcfund.model.dao.ABCMemberDao;
import com.hk.abcfund.model.dto.ABCAccountDto;
import com.hk.abcfund.model.dto.ABCMemberDto;
import com.hk.abcfund.model.dto.ABCMyInfoDto;
import com.hk.abcfund.model.dto.ABCMyLoanInfoDto;
import com.hk.abcfund.util.ABCUtility;
import com.hk.abcfund.util.RSAUtility;

/**
 * Service for membership
 * @author 9age
 *
 */
@Service
public class ABCMemberServiceImpl implements ABCMemberService {
	/** DAO of membership */
	@Autowired
	private ABCMemberDao dao;
	
	/** DAO of account */
	@Autowired
	private ABCAccountDao accDao;
	
	/** DAO of loan */
	@Autowired
	private ABCLoanDao loanDao;
	
	/** Password encoder */
    @Autowired
    private PasswordEncoder passwordEncoder;
	
	/**
	 * Add a member and create a virtual account
	 * @param dto A DTO of member
	 */
	@Override
	public void addMemeber(ABCMemberDto dto, PrivateKey privateKey) {
		// Get email and password from DTO
    	String email = dto.getEmail();
    	String pwd = dto.getPwd();
    	
    	// Decrypt email and password with RSA and set those to DTO
    	dto.setEmail(RSAUtility.decryptRSAbyBase64(email, privateKey));
    	dto.setPwd(RSAUtility.decryptRSAbyBase64(pwd, privateKey));
    	
    	// Encode the password
    	String encodedPwd = passwordEncoder.encode(dto.getPwd());
    	dto.setPwd(encodedPwd);
		
		// Add a member through DAO
		dao.addMemeber(dto);
		
		// Add an virtual account
		ABCAccountDto accDto = 
			new ABCAccountDto(ABCUtility.createVANumber(), dto.getEmail(), dto.getName());		
		accDao.addAccount(accDto);
	}
	
	/**
	 * Change member's grade non-approval to normal
	 * @param dto A DTO of a member
	 */
	@Override
	public void doAuthMember(ABCMemberDto dto) {
		dao.doAuthCode(dto);
	}
	
	/**
	 * Decrypt the password by RSA and encrypt by BCrypt. Then try to login.
	 * @param dto A DTO of a member
	 * @return Return DTO object that has null if cannot login
	 */
	@Override
	public ABCMemberDto login(ABCMemberDto dto, PrivateKey privateKey) {
		// Get email and password from DTO
    	String email = dto.getEmail();
    	String pwd = dto.getPwd();
    	
    	// Decrypt email and password with RSA and set those to DTO
    	dto.setEmail(RSAUtility.decryptRSAbyBase64(email, privateKey));
    	dto.setPwd(RSAUtility.decryptRSAbyBase64(pwd, privateKey));
    	
    	// Get member data
    	ABCMemberDto member = dao.login(dto);
    	
    	// Make sure passwords match
    	if (passwordEncoder.matches(dto.getPwd(), member.getPwd())) {
    		member.setPwd(null);
    	} else {
    		member = null;
    	}
		
		return member; 
	}
	
	/**
	 * Change the password
	 * @param dto A DTO of member
	 */
	@Override
	public void changePwd(ABCMemberDto dto) {
		dao.changePwd(dto);
	}
	
	/**
	 * Change the authentication code for find a password
	 * @param dto A DTO of a member
	 */
	@Override
	public void changeAuthCode(ABCMemberDto dto) {
		dao.changeAuthCode(dto);
	}
	
	/**
	 * Change password by email authentication
	 * @param dto A DTO of a member
	 */
	@Override
	public void changePwdOnAuth(ABCMemberDto dto) {
		dao.changePwdOnAuth(dto);
	}
	
	/**
	 * Get the member's information
	 * @param email A email of the member
	 */
	@Override
	public ABCMyInfoDto getMyInfo(String email) {
		ABCMyInfoDto info = dao.getMyInfo(email); 
		
		// Set gender
		info.setGender(ABCGenderType.findName(info.getGender()));
		
		return info;
	}
	
	/**
	 * Get loan list of the member
	 * @param email A email of the member
	 */
	@Override
	public ArrayList<ABCMyLoanInfoDto> getMyLoanInfoList(String email) {
		return dao.getMyLoanInfoList(email);
	}
	
	/** 
	 * Request for member's withdrawal
	 * @param email A email of the member
	 */
	@Override
	@Transactional
	public void dropMember(String email) {
		// Update the email to dummy
		loanDao.updateEmailToDump(email);
		
		// Delete the account by email
		accDao.deleteAccount(email);
		
		// Withdraw the member by email
		dao.dropMember(email);
	}
	
	/**
	 * Get list of all member but administrator and dummy
	 * @return List of member DTO
	 */
	@Override
	public List<ABCMemberDto> getMemberList() {
		return dao.getMemberList();
	}
	
	/**
	 * Get a member DTO by email
	 * @param email A email of member
	 */
	@Override
	public ABCMemberDto getMember(String email) {
		return dao.getMember(email);
	}
}