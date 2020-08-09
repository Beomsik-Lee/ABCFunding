package com.hk.abcfund.model.dto;

import java.io.Serializable;

/**
 * @author 9age
 *
 */
public class ABCInvestTransactionDto implements Serializable {
	/** 투자내역을 식별하는 시퀀스 번호 */
	private int investTransactionSeq;
	
	/** 어떤 투자인지를 식별하는 투자번호 */
	private int investSeq;
	
	/**
	 * 예정 수익금
	 * 투자금 x 0.06
	 */
	private int intendProfit;
	
	/**
	 * 누적 회수금
	 * 총 회수금/대출기간x현회차수
	 */
	private int stackCollect;
	
	/** 대출의 진행상황 */
	private String progress;
	
	/**
	 * 누적 회수비율
	 * 누적 회수금 / 총 회수금
	 */
	private int collectRate;
	
	/** 대출의 회차 수 */
	private int round;

	/**
	 * 기본 생성자
	 */
	public ABCInvestTransactionDto() {}

	/**
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
