package com.hk.abcfund.model.dto;

import java.io.Serializable;

/**
 * DTO for Investment chart
 * @author 9age
 *
 */
public class ABCInvestChartDto implements Serializable {
	private static final long serialVersionUID = 1L;

	/** Sequence of investment */
	private int investSeq;
	
	/** Amount of investments */
	private int investMoney;
	
	/** Stack of principal */
	private int stackOrigin;
	
	/** Stack of interest */
	private int stackInterest;

	/**
	 * Default Constructor
	 */
	public ABCInvestChartDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * Constructor for whole variables
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