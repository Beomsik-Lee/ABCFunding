package com.hk.abcfund.model.dto;

import java.io.Serializable;

/**
 * @author 9age
 *
 */
public class ABCLoanTransactionDto implements Serializable {
	/** 대출내역을 식별할 시퀀스 번호 */
	private int loanSeq;
	
	/** 어떤 대출상품인지를 식별할 대출코드 */
	private int loanCode;
	
	/** 누적상환이자금 */
	private int stackRepayRate;
	
	/** 누적상환원금 */
	private int stackRepayOrigin;
	
	/** 대출의 진행상황 */
	private String progress;
	
	/**
	 * 회수 비율
	 * (누적상환금 / 총상환금)으로 계산
	 */
	private int collectRate;
	
	/** 대출의 회차 수 */
	private int round;

	/**
	 * 기본생성자
	 */
	public ABCLoanTransactionDto() {}

	/**
	 * @param loanSeq
	 * @param loanCode
	 * @param stackRepayRate
	 * @param stackRepayOrigin
	 * @param progress
	 * @param collectRate
	 * @param round
	 */
	public ABCLoanTransactionDto(int loanSeq, int loanCode, int stackRepayRate, int stackRepayOrigin, String progress,
			int collectRate, int round) {
		super();
		this.loanSeq = loanSeq;
		this.loanCode = loanCode;
		this.stackRepayRate = stackRepayRate;
		this.stackRepayOrigin = stackRepayOrigin;
		this.progress = progress;
		this.collectRate = collectRate;
		this.round = round;
	}

	/**
	 * @return the loanSeq
	 */
	public int getLoanSeq() {
		return loanSeq;
	}

	/**
	 * @param loanSeq the loanSeq to set
	 */
	public void setLoanSeq(int loanSeq) {
		this.loanSeq = loanSeq;
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
		return "ABCLoanTransactionDto [loanSeq=" + loanSeq + ", loanCode=" + loanCode + ", stackRepayRate="
				+ stackRepayRate + ", stackRepayOrigin=" + stackRepayOrigin + ", progress=" + progress
				+ ", collectRate=" + collectRate + ", round=" + round + "]";
	}
	
}
