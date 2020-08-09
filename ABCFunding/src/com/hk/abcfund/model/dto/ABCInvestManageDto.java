package com.hk.abcfund.model.dto;

import java.io.Serializable;

/**
 * @author 9age
 *
 */
public class ABCInvestManageDto implements Serializable {
	/** 투자번호 */
	private int investSeq;
	
	/** 투자금 */
	private int investMoney;
	
	/** 누적 수익금 */
	private int stackProfit;
	
	/** 진행상황 */
	private String progress;

	/**
	 * 
	 */
	public ABCInvestManageDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
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
