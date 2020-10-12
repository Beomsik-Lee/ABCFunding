/**
 * 
 */
package com.hk.abcfund.model.dto;

/**
 * DTO for loan management from administrator
 * @author 9age
 *
 */
public class ABCAdminLoanManageDto {
	
	private int stackLoanNum;
	private int stackLoanMoney;
	private int stackLoanRepayMoney;
	private int loanEndNum;
	private int loanMiddleNum;
	
	/**
	 * 기본생성자 
	 */
	public ABCAdminLoanManageDto() {}

	
	/**
	 * 전체 변수를 갖는 생성자
	 * @param stackLoanNum 누적대출실행건
	 * @param stackLoanMoney 누적대출실행금
	 * @param stackLoanRepayMoney 누적대출상환금
	 * @param loanEndNum 상환완료건수
	 * @param loanMiddleNum 상환중인건수
	 */
	public ABCAdminLoanManageDto(int stackLoanNum, int stackLoanMoney, int stackLoanRepayMoney, int loanEndNum,
			int loanMiddleNum) {
		super();
		this.stackLoanNum = stackLoanNum;
		this.stackLoanMoney = stackLoanMoney;
		this.stackLoanRepayMoney = stackLoanRepayMoney;
		this.loanEndNum = loanEndNum;
		this.loanMiddleNum = loanMiddleNum;
	}


	/**
	 * @return the stackLoanNum
	 */
	public int getStackLoanNum() {
		return stackLoanNum;
	}


	/**
	 * @param stackLoanNum the stackLoanNum to set
	 */
	public void setStackLoanNum(int stackLoanNum) {
		this.stackLoanNum = stackLoanNum;
	}


	/**
	 * @return the stackLoanMoney
	 */
	public int getStackLoanMoney() {
		return stackLoanMoney;
	}


	/**
	 * @param stackLoanMoney the stackLoanMoney to set
	 */
	public void setStackLoanMoney(int stackLoanMoney) {
		this.stackLoanMoney = stackLoanMoney;
	}


	/**
	 * @return the stackLoanRepayMoney
	 */
	public int getStackLoanRepayMoney() {
		return stackLoanRepayMoney;
	}


	/**
	 * @param stackLoanRepayMoney the stackLoanRepayMoney to set
	 */
	public void setStackLoanRepayMoney(int stackLoanRepayMoney) {
		this.stackLoanRepayMoney = stackLoanRepayMoney;
	}


	/**
	 * @return the loanEndNum
	 */
	public int getLoanEndNum() {
		return loanEndNum;
	}


	/**
	 * @param loanEndNum the loanEndNum to set
	 */
	public void setLoanEndNum(int loanEndNum) {
		this.loanEndNum = loanEndNum;
	}


	/**
	 * @return the loanMiddleNum
	 */
	public int getLoanMiddleNum() {
		return loanMiddleNum;
	}


	/**
	 * @param loanMiddleNum the loanMiddleNum to set
	 */
	public void setLoanMiddleNum(int loanMiddleNum) {
		this.loanMiddleNum = loanMiddleNum;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return stackLoanNum + ", " + stackLoanMoney + ", " + stackLoanRepayMoney + ", " + loanEndNum + ", "
				+ loanMiddleNum;
	}
	
	
	
}