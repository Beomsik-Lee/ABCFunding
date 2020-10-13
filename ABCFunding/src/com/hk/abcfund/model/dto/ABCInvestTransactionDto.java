package com.hk.abcfund.model.dto;

import java.io.Serializable;

/**
 * DTO for investment history
 * @author 9age
 *
 */
public class ABCInvestTransactionDto implements Serializable {
	private static final long serialVersionUID = 1L;

	/** Sequence of investment history */
	private int investTransactionSeq;
	
	/** Sequence of investment */
	private int investSeq;
	
	/**
	 * Intend profit
	 * Investments x 0.06
	 */
	private int intendProfit;
	
	/**
	 * Stack recovery
	 * Total recovery / loan date X Current rounds
	 */
	private int stackCollect;
	
	/** Progress of loan */
	private String progress;
	
	/**
	 * Stack recovery rate
	 * Stack recovery / Total recovery
	 */
	private int collectRate;
	
	/** Rounds of loan */
	private int round;

	/**
	 * Default Constructor
	 */
	public ABCInvestTransactionDto() {}

	/**
	 * Constructor for whole variables
	 * @param investTransactionSeq
	 * @param investSeq
	 * @param intendProfit
	 * @param stackCollect
	 * @param progress
	 * @param collectRate
	 * @param round
	 */
	public ABCInvestTransactionDto(int investTransactionSeq, int investSeq, int intendProfit,
			int stackCollect, String progress, int collectRate, int round) {
		super();
		this.investTransactionSeq = investTransactionSeq;
		this.investSeq = investSeq;
		this.intendProfit = intendProfit;
		this.stackCollect = stackCollect;
		this.progress = progress;
		this.collectRate = collectRate;
		this.round = round;
	}

	/**
	 * @return the investTransactionSeq
	 */
	public int getInvestTransactionSeq() {
		return investTransactionSeq;
	}

	/**
	 * @param investTransactionSeq the investTransactionSeq to set
	 */
	public void setInvestTransactionSeq(int investTransactionSeq) {
		this.investTransactionSeq = investTransactionSeq;
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
	 * @return the intendProfit
	 */
	public int getIntendProfit() {
		return intendProfit;
	}

	/**
	 * @param intendProfit the intendProfit to set
	 */
	public void setIntendProfit(int intendProfit) {
		this.intendProfit = intendProfit;
	}

	/**
	 * @return the stackCollect
	 */
	public int getStackCollect() {
		return stackCollect;
	}

	/**
	 * @param stackCollect the stackCollect to set
	 */
	public void setStackCollect(int stackCollect) {
		this.stackCollect = stackCollect;
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
	 * @return the collectRate
	 */
	public int getCollectRate() {
		return collectRate;
	}

	/**
	 * @param collectRate the collectRate to set
	 */
	public void setCollectRate(int collectRate) {
		this.collectRate = collectRate;
	}

	/**
	 * @return the round
	 */
	public int getRound() {
		return round;
	}

	/**
	 * @param round the round to set
	 */
	public void setRound(int round) {
		this.round = round;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ABCInvestTransactionDto [investTransactionSeq=" + investTransactionSeq + ", investSeq=" + investSeq
				+ ", intendProfit=" + intendProfit + ", stackCollect=" + stackCollect
				+ ", progress=" + progress + ", collectRate=" + collectRate + ", round=" + round + "]";
	}
	
}