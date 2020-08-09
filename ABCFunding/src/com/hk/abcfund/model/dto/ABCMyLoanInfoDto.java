/**
 * 
 */
package com.hk.abcfund.model.dto;

import java.io.Serializable;

/**
 * @author 9age
 *
 */
public class ABCMyLoanInfoDto implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 725294898092999866L;

	/**
	 * 대출 신청인의 이메일
	 */
	private String email;
	
	/** 대출상품을 식별할 대출코드 */
	private int loanCode;
	
	/** 대출의 진행상태 
	 * 펀딩진행중,펀딩완료,상환중,상환완료
	 * 한글로 최대6자
	 */
	private String progress;
	
	/**
	 * 대출 금리
	 * 8% 고정 금리
	 */
	private int interestRate;
	
	/**
	 * 상환방식
	 * 원리금균등분할상환 방식으로 고정
	 */
	private String repayType;
	
	
	/**
	 * 대출구분
	 * 개인신용대출로 고정
	 */
	private String loanType;
	
	/**
	 * 대출신청금
	 * 단위는 원
	 */
	private int loanMoney;
	
	/**
	 * 대출기간
	 * 입력가능값 : 1~12,18,24
	 * 단위는 개월수 
	 */
	private int loanDate;
	
	/**
	 * 희망상환일
	 * 입력가능 값 : 1,5,10,15,20,25
	 * 단위는 일
	 */
	private int repay;
	
	/**
	 * 펀딩신청 기간
	 * 입력가능 값 : 1~7
	 * 단위는 일
	 */
	private int expiryDate;
	
	/**
	 * 대출 잔액
	 * 현재 남아있는 상환해야할 대출 잔액
	 */
	private int balance;
	
	/**
	 * 대출 심사 결과
	 * 대출 신청건에 대한 심사결과를 담을 변수
	 */
	private int result;

	/**
	 * 기본 생성자
	 */
	public ABCMyLoanInfoDto() {}

	/**
	 * 전체 변수를 가지는 기본 생성자
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
