/**
 * 
 */
package com.hk.abcfund.model.dto;

import java.io.Serializable;

/**
 * DTO for loan info
 * @author 9age
 *
 */
public class ABCMyLoanInfoDto implements Serializable {
	
	private static final long serialVersionUID = 725294898092999866L;

	/**
	 * email
	 */
	private String email;
	
	/** loan code */
	private int loanCode;
	
	/** 
	 * progress of loan
	 */
	private String progress;
	
	/**
	 * interest rate
	 */
	private int interestRate;
	
	/**
	 * type of repayments
	 */
	private String repayType;
	
	
	/**
	 * type of loan
	 */
	private String loanType;
	
	/**
	 * A loan
	 */
	private int loanMoney;
	
	/**
	 * date of loan 
	 */
	private int loanDate;
	
	/**
	 * Desired repayments date
	 */
	private int repay;
	
	/**
	 * Expire date
	 */
	private int expiryDate;
	
	/**
	 * balance
	 */
	private int balance;
	
	/**
	 * result for loan
	 */
	private int result;

	/**
	 * Default constructor
	 */
	public ABCMyLoanInfoDto() {}

	/**
	 * The constructor that have all parameters
	 * @param email
	 * @param loanCode
	 * @param progress
	 * @param interestRate
	 * @param repayType
	 * @param loanType
	 * @param loanMoney
	 * @param loanDate
	 * @param repay
	 * @param expiryDate
	 * @param balance
	 * @param result
	 */
	public ABCMyLoanInfoDto(String email, int loanCode, String progress, int interestRate, String repayType,
			String loanType, int loanMoney, int loanDate, int repay, int expiryDate, int balance, int result) {
		super();
		this.email = email;
		this.loanCode = loanCode;
		this.progress = progress;
		this.interestRate = interestRate;
		this.repayType = repayType;
		this.loanType = loanType;
		this.loanMoney = loanMoney;
		this.loanDate = loanDate;
		this.repay = repay;
		this.expiryDate = expiryDate;
		this.balance = balance;
		this.result = result;
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
	 * @return the progress
	 */
	public String getProgress() {
		return progress;
	}

	/**
	 * @param progress the progress to set
	 */
	public void setProgress(String progress) {
		this.progress = progress;
	}

	/**
	 * @return the interestRate
	 */
	public int getInterestRate() {
		return interestRate;
	}

	/**
	 * @param interestRate the interestRate to set
	 */
	public void setInterestRate(int interestRate) {
		this.interestRate = interestRate;
	}

	/**
	 * @return the repayType
	 */
	public String getRepayType() {
		return repayType;
	}

	/**
	 * @param repayType the repayType to set
	 */
	public void setRepayType(String repayType) {
		this.repayType = repayType;
	}

	/**
	 * @return the loanType
	 */
	public String getLoanType() {
		return loanType;
	}

	/**
	 * @param loanType the loanType to set
	 */
	public void setLoanType(String loanType) {
		this.loanType = loanType;
	}

	/**
	 * @return the loanMoney
	 */
	public int getLoanMoney() {
		return loanMoney;
	}

	/**
	 * @param loanMoney the loanMoney to set
	 */
	public void setLoanMoney(int loanMoney) {
		this.loanMoney = loanMoney;
	}

	/**
	 * @return the loanDate
	 */
	public int getLoanDate() {
		return loanDate;
	}

	/**
	 * @param loanDate the loanDate to set
	 */
	public void setLoanDate(int loanDate) {
		this.loanDate = loanDate;
	}

	/**
	 * @return the repay
	 */
	public int getRepay() {
		return repay;
	}

	/**
	 * @param repay the repay to set
	 */
	public void setRepay(int repay) {
		this.repay = repay;
	}

	/**
	 * @return the expiryDate
	 */
	public int getExpiryDate() {
		return expiryDate;
	}

	/**
	 * @param expiryDate the expiryDate to set
	 */
	public void setExpiryDate(int expiryDate) {
		this.expiryDate = expiryDate;
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

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return email + ", " + loanCode + ", " + progress + ", " + interestRate + ", " + repayType + ", " + loanType
				+ ", " + loanMoney + ", " + loanDate + ", " + repay + ", " + expiryDate + ", " + balance + ", "
				+ result;
	}

}