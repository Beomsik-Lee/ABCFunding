package com.hk.abcfund.model.dto;

import java.io.Serializable;

/**
 * DTO for virtual account
 * @author 9age
 */
public class ABCAccountDto implements Serializable {
	private static final long serialVersionUID = -3828036421861465715L;
	
	/** 
	 * Account number
	 */
	private String accountNo;
	
	/**
	 * email
	 */
	private String email;
	
	/**
	 * balance
	 */
	private int balance;
	
	/**
	 * Name
	 */
	private String accountHolder;
	
	/**
	 * Loan code
	 */
	private int loanCode;

	/** Default Constructor */
	public ABCAccountDto() {}

	/**
	 * Constructor for whole data
	 * @param accountNo Account Number
	 * @param email Email
	 * @param balance Balance
	 * @param accountHolder Name
	 * @param loanCode Loan Code
	 */
	public ABCAccountDto(String accountNo, String email, int balance, String accountHolder, int loanCode) {
		this(accountNo, email, accountHolder);
		this.balance = balance;
		this.loanCode = loanCode;
	}
	
	/**
	 * Constructor for whole data but balance and loan code
	 * @param accountNo
	 * @param email
	 * @param accountHolder
	 */
	public ABCAccountDto(String accountNo, String email, String accountHolder) {
		super();
		this.accountNo = accountNo;
		this.email = email;
		this.accountHolder = accountHolder;
	}

	/**
	 * @return the accountNo
	 */
	public String getAccountNo() {
		return accountNo;
	}

	/**
	 * @param accountNo the accountNo to set
	 */
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
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
	 * @return the balance
	 */
	public int getBalance() {
		return balance;
	}

	/**
	 * @param balance the balance to set
	 */
	public void setBalance(int balance) {
		this.balance = balance;
	}

	/**
	 * @return the accountHolder
	 */
	public String getAccountHolder() {
		return accountHolder;
	}

	/**
	 * @param accountHolder the accountHolder to set
	 */
	public void setAccountHolder(String accountHolder) {
		this.accountHolder = accountHolder;
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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ABCAccountDto [accountNo=" + accountNo + ", email=" + email + ", balance=" + balance
				+ ", accountHolder=" + accountHolder + ", loanCode=" + loanCode + "]";
	}

	
}