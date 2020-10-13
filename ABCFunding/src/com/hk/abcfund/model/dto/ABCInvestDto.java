package com.hk.abcfund.model.dto;

import java.io.Serializable;

/**
 * DTO for investments
 * @author 9age
 *
 */
public class ABCInvestDto implements Serializable {
	private static final long serialVersionUID = 1L;

	/** Sequence of investment */
	private int investSeq;
	
	/** Email address */
	private String email;
	
	/** Loan code */
	private int loanCode;
	
	/** Investment fund */
	private int investMoney;

	/**
	 * Default Constructor
	 */
	public ABCInvestDto() {	}

	/**
	 * Constructor for whole variables
	 * @param investSeq
	 * @param email
	 * @param loanCode
	 * @param investMoney
	 */
	public ABCInvestDto(int investSeq, String email, int loanCode, int investMoney) {
		super();
		this.investSeq = investSeq;
		this.email = email;
		this.loanCode = loanCode;
		this.investMoney = investMoney;
	}
	
	/**
	 * Constructor for whole variables but sequence
	 * @param email 
	 * @param loanCode 
	 * @param investMoney 
	 */
	public ABCInvestDto(String email, int loanCode, int investMoney) {
		super();
		this.email = email;
		this.loanCode = loanCode;
		this.investMoney = investMoney;
	}

	/**
	 * @return the investSeq
	 */
	public int getInvestSeq() {
		return investSeq;
	}

	/**
	 * @param investSeq the investSeq to set
	 */
	public void setInvestSeq(int investSeq) {
		this.investSeq = investSeq;
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
	 * @return the investMoney
	 */
	public int getInvestMoney() {
		return investMoney;
	}

	/**
	 * @param investMoney the investMoney to set
	 */
	public void setInvestMoney(int investMoney) {
		this.investMoney = investMoney;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ABCInvestDto [investSeq=" + investSeq + ", email=" + email + ", loanCode=" + loanCode + ", investMoney="
				+ investMoney + "]";
	}
	
	
}