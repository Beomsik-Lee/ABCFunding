package com.hk.abcfund.model.dto;

import java.io.Serializable;

/**
 * ���� ���� DTO
 * @author 9age
 *
 */
public class ABCInvestDto implements Serializable {
	/** ���� ��������ȣ */
	private int investSeq;
	
	/** �����ڸ� �ĺ��� �̸��� */
	private String email;
	
	/** ��� ��ǰ�� �����ߴ����� �ĺ��� �����ڵ� */
	private int loanCode;
	
	/** ���ڱݾ� */
	private int investMoney;

	/**
	 * �⺻ ������
	 */
	public ABCInvestDto() {	}

	/**
	 * ��ü �����͸� �޴� ������
	 * @param investSeq ���� ������
	 * @param email �̸���
	 * @param loanCode ���� �ڵ�
	 * @param investMoney ���ڱ�
	 */
	public ABCInvestDto(int investSeq, String email, int loanCode, int investMoney) {
		super();
		this.investSeq = investSeq;
		this.email = email;
		this.loanCode = loanCode;
		this.investMoney = investMoney;
	}
	
	/**
	 * ���ڽ������� ������ ��� �����͸� �޴� ������
	 * @param email ȸ�� �̸���
	 * @param loanCode ��ǰ�� �����ڵ�
	 * @param investMoney �������� ���ڱݾ�
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
