package com.hk.abcfund.model.dto;

import java.io.Serializable;

/**
 * DTO for credit rating and email
 * @author 9age
 *
 */
public class ABCLoanSubDto implements Serializable {
	private static final long serialVersionUID = 6522303323187086821L;

	/**
	 * credit rating
	 */
	private int creditRating;
	
	/**
	 * email
	 */
	private String email;

	/**
	 * Default constructor
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