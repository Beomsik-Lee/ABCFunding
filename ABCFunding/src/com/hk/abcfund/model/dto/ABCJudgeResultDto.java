/**
 * 
 */
package com.hk.abcfund.model.dto;

import java.io.Serializable;

/**
 * DTO for examine result
 * @author 9age
 *
 */
public class ABCJudgeResultDto implements Serializable {
	private static final long serialVersionUID = 1L;
	
	
	/**
	 * Loan code
	 */
	private int loanCode;
	
	/**
	 * Email address
	 */
	private String email;
	
	/**
	 * Commentary
	 */
	private String commentary;
	private int result;
	
	/**
	 * Default Constructor
	 */
	public ABCJudgeResultDto() {}

	/**
	 * Constructor for whole variables
	 * @param loanCode
	 * @param email
	 * @param commentary
	 * @param result
	 */
	public ABCJudgeResultDto(int loanCode, String email, String commentary, int result) {
		super();
		this.loanCode = loanCode;
		this.email = email;
		this.commentary = commentary;
		this.result = result;
	}

	/**
	 * @return the loanCode
	 */
	public int getLoanCode() {
		return loanCode;
	}

	/**
	 * @param loanCode the loanCode to set
	 */
	public void setLoanCode(int loanCode) {
		this.loanCode = loanCode;
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

	/**
	 * @return the commentary
	 */
	public String getCommentary() {
		return commentary;
	}

	/**
	 * @param commentary the commentary to set
	 */
	public void setCommentary(String commentary) {
		this.commentary = commentary;
	}

	/**
	 * @return the result
	 */
	public int getResult() {
		return result;
	}

	/**
	 * @param result the result to set
	 */
	public void setResult(int result) {
		this.result = result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return loanCode + ", " + email + ", " + commentary + ", " + result;
	}
	
	
}