package com.hk.abcfund.model.dto;

import java.io.Serializable;

/**
 * @author 9age
 *
 */
public class ABCInvestChartDto implements Serializable {
	/** 투자코드 */
	private int investSeq;
	
	/** 투자금 */
	private int investMoney;
	
	/** 누적원금 */
	private int stackOrigin;
	
	/** 누적 이자금 */
	private int stackInterest;

	/**
	 * 
	 */
	public ABCInvestChartDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param investSeq
	 * @param investMoney
	 * @param stackOrigin
	 * @param stackInterest
	 */
	public ABCInvestChartDto(int investSeq, int investMoney, int stackOrigin, int stackInterest) {
		super();
		this.investSeq = investSeq;
		this.investMoney = investMoney;
		this.stackOrigin = stackOrigin;
		this.stackInterest = stackInterest;
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
	 * @return the stackOrigin
	 */
	public int getStackOrigin() {
		return stackOrigin;
	}

	/**
	 * @param stackOrigin the stackOrigin to set
	 */
	public void setStackOrigin(int stackOrigin) {
		this.stackOrigin = stackOrigin;
	}

	/**
	 * @return the stackInterest
	 */
	public int getStackInterest() {
		return stackInterest;
	}

	/**
	 * @param stackInterest the stackInterest to set
	 */
	public void setStackInterest(int stackInterest) {
		this.stackInterest = stackInterest;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ABCInvestChartDto [investSeq=" + investSeq + ", investMoney=" + investMoney + ", stackOrigin="
				+ stackOrigin + ", stackInterest=" + stackInterest + "]";
	}
	
	
}
