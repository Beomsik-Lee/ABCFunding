package com.hk.abcfund.model.dto;

import java.io.Serializable;

/**
 * DTO for investment management
 * @author 9age
 *
 */
public class ABCInvestManageDto implements Serializable {
	private static final long serialVersionUID = 1L;

	/** Sequence of investment */
	private int investSeq;
	
	/** Investment fund */
	private int investMoney;
	
	/** Stack profit */
	private int stackProfit;
	
	/** Progress */
	private String progress;

	/**
	 * Default Constructor
	 */
	public ABCInvestManageDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * Constructor for whole variables
	 * @param investSeq
	 * @param investMoney
	 * @param stackProfit
	 * @param progress
	 */
	public ABCInvestManageDto(int investSeq, int investMoney, int stackProfit, String progress) {
		super();
		this.investSeq = investSeq;
		this.investMoney = investMoney;
		this.stackProfit = stackProfit;
		this.progress = progress;
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

	/**
	 * @return the stackProfit
	 */
	public int getStackProfit() {
		return stackProfit;
	}

	/**
	 * @param stackProfit the stackProfit to set
	 */
	public void setStackProfit(int stackProfit) {
		this.stackProfit = stackProfit;
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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ABCInvestManageDto [investSeq=");
		builder.append(investSeq);
		builder.append(", investMoney=");
		builder.append(investMoney);
		builder.append(", stackProfit=");
		builder.append(stackProfit);
		builder.append(", progress=");
		builder.append(progress);
		builder.append("]");
		return builder.toString();
	}
	
	
}