package com.hk.abcfund.model.dto;

import java.io.Serializable;

/**
 * 회원가입시 자동으로 주어지는 가상계좌의 클래스
 * @author 9age
 */
public class ABCAccountDto implements Serializable {
	private static final long serialVersionUID = -3828036421861465715L;
	
	/** 
	 * 가상계좌번호
	 * 숫자3-숫자6숫자3 형식의 총 14자 
	 */
	private String accountNo;
	
	/**
	 * 누구의 계정인지를 식별할 회원의 이메일
	 */
	private String email;
	
	/**
	 * 예치금(잔액)
	 */
	private int balance;
	
	/**
	 * 예금주(회원 이름)
	 */
	private String accountHolder;
	
	/**
	 * 대출코드
	 */
	private int loanCode;

	/** 기본 생성자 */
	public ABCAccountDto() {}

	/**
	 * 전체 데이터를 받는 생성자
	 * @param accountNo 가상계좌번호
	 * @param email 회원의 이메일
	 * @param balance 예치금
	 * @param accountHolder 회원의 이름
	 * @param loanCode 대출코드
	 */
	public ABCAccountDto(String accountNo, String email, int balance, String accountHolder, int loanCode) {
		this(accountNo, email, accountHolder);
		this.balance = balance;
		this.loanCode = loanCode;
	}
	
	/**
	 * 예치금과 대출코드를 제외한 모든 데이터를 받는 생성자
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
