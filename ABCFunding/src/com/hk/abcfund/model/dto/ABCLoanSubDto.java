package com.hk.abcfund.model.dto;

import java.io.Serializable;

/**
 * 대출 신청시 신용정보와 이메일을 담기 위한 DTO 클래스
 * @author 9age
 *
 */
public class ABCLoanSubDto implements Serializable {
	private static final long serialVersionUID = 6522303323187086821L;

	/**
	 * 회원의 신용등급
	 */
	private int creditRating;
	
	/**
	 * 회원의 이메일
	 */
	private String email;

	/**
	 * 기본생성자
	 */
	public ABCLoanSubDto() {}

	/**
	 * @param creditRating
	 * @param email
	 */
	public ABCLoanSubDto(int creditRating, String email) {
		super();
		this.creditRating = creditRating;
		this.email = email;
	}

	/**
	 * @return the creditRating
	 */
	public int getCreditRating() {
		return creditRating;
	}

	/**
	 * @param creditRating the creditRating to set
	 */
	public void setCreditRating(int creditRating) {
		this.creditRating = creditRating;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return creditRating + ", " + email;
	}
	
}
