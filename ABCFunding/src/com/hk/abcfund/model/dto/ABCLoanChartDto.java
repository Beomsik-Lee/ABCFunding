/**
 * 
 */
package com.hk.abcfund.model.dto;

import java.io.Serializable;

/**
 * @author Beom
 *
 */
public class ABCLoanChartDto implements Serializable {
	/** 대출코드 */
	private int loanCode;
	
	/** 누적 원금 */
	private int stackRepayOrigin;
	
	/** 누적 이자금 */
	private int stackRepayRate;

	/**
	 * 
	 */
	public ABCLoanChartDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param loanCode
	 * @param stackRepayOrigin
	 * @param stackRepayRate
	 */
	public ABCLoanChartDto(int loanCode, int stackRepayOrigin, int stackRepayRate) {
		super();
		this.loanCode = loanCode;
		this.stackRepayOrigin = stackRepayOrigin;
		this.stackRepayRate = stackRepayRate;
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
	 * @return the stackRepayOrigin
	 */
	public int getStackRepayOrigin() {
		return stackRepayOrigin;
	}

	/**
	 * @param stackRepayOrigin the stackRepayOrigin to set
	 */
	public void setStackRepayOrigin(int stackRepayOrigin) {
		this.stackRepayOrigin = stackRepayOrigin;
	}

	/**
	 * @return the stackRepayRate
	 */
	public int getStackRepayRate() {
		return stackRepayRate;
	}

	/**
	 * @param stackRepayRate the stackRepayRate to set
	 */
	public void setStackRepayRate(int stackRepayRate) {
		this.stackRepayRate = stackRepayRate;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ABCLoanChartDto [loanCode=");
		builder.append(loanCode);
		builder.append(", stackRepayOrigin=");
		builder.append(stackRepayOrigin);
		builder.append(", stackRepayRate=");
		builder.append(stackRepayRate);
		builder.append("]");
		return builder.toString();
	}
	
	
}
