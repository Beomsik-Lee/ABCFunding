package com.hk.abcfund.model.dto;

import java.io.Serializable;

/**
 * 투자 관련 DTO
 * @author 9age
 *
 */
public class ABCInvestDto implements Serializable {
	/** 투자 시퀀스번호 */
	private int investSeq;
	
	/** 투자자를 식별할 이메일 */
	private String email;
	
	/** 어는 상품을 투자했는지를 식별할 대출코드 */
	private int loanCode;
	
	/** 투자금액 */
	private int investMoney;

	/**
	 * 기본 생성자
	 */
	public ABCInvestDto() {	}

	/**
	 * 전체 데이터를 받는 생성자
	 * @param investSeq 투자 시퀀스
	 * @param email 이메일
	 * @param loanCode 대출 코드
	 * @param investMoney 투자금
	 */
	public ABCInvestDto(int investSeq, String email, int loanCode, int investMoney) {
		super();
		this.investSeq = investSeq;
		this.email = email;
		this.loanCode = loanCode;
		this.investMoney = investMoney;
	}
	
	/**
	 * 투자시퀀스를 제외한 모든 데이터를 받는 생성자
	 * @param email 회원 이메일
	 * @param loanCode 상품의 대출코드
	 * @param investMoney 투자자의 투자금액
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
