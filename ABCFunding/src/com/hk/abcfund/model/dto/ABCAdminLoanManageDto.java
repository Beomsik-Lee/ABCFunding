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
	/**
	 * Number of stack loans
	 */
	private int stackLoanNum;
	
	/**
	 * Amount of stack loans
	 */
	private int stackLoanMoney;
	
	/**
	 * Repayments of stack loans
	 */
	private int stackLoanRepayMoney;
	
	/**
	 * Number of loan ends
	 */
	private int loanEndNum;
	
	/**
	 * Number of middle loan
	 */
	private int loanMiddleNum;
	
	/**
	 * Default Constructor
	 */
	public ABCAdminLoanManageDto() {}

	
	/**
	 * Constructor for whole variables
	 * @param stackLoanNum
	 * @param stackLoanMoney
	 * @param stackLoanRepayMoney
	 * @param loanEndNum
	 * @param loanMiddleNum
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