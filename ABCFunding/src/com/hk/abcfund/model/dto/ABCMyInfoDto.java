/**
 * 
 */
package com.hk.abcfund.model.dto;

import java.io.Serializable;

/**
 * 내 정보의 기본 정보를 담아올 DTO 클래스
 * @author 9age
 *
 */
public class ABCMyInfoDto implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2072620493179912927L;
	
	private String name;
	private String gender;
	private String birth;
	private int creditRating;
	
	private String accountNo;
	private int balance;
	
	/**
	 * 기본생성자 
	 */
	public ABCMyInfoDto() {}

	/**
	 * @param name
	 * @param gender
	 * @param birth
	 * @param creditRating
	 * @param accountNo
	 * @param balance
	 */
	public ABCMyInfoDto(String name, String gender, String birth, int creditRating, String accountNo, int balance) {
		super();
		this.name = name;
		this.gender = gender;
		this.birth = birth;
		this.creditRating = creditRating;
		this.accountNo = accountNo;
		this.balance = balance;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the gender
	 */
	public String getGender() {
		return gender;
	}

	/**
	 * @param gender the gender to set
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}

	/**
	 * @return the birth
	 */
	public String getBirth() {
		return birth;
	}

	/**
	 * @param birth the birth to set
	 */
	public void setBirth(String birth) {
		this.birth = birth;
	}

	/**
	 * @return the creditRating
	 */
	public int getCreditRating() {
		return creditRating;
	}

	/**
	 * @param creditRating the creditRating to set
	 */
	public void setCreditRating(int creditRating) {
		this.creditRating = creditRating;
	}

	/**
	 * @return the accountNo
	 */
	public String getAccountNo() {
		return accountNo;
	}

	/**
	 * @param accountNo the accountNo to set
	 */
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	/**
	 * @return the balance
	 */
	public int getBalance() {
		return balance;
	}

	/**
	 * @param balance the balance to set
	 */
	public void setBalance(int balance) {
		this.balance = balance;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return name + ", " + gender + ", " + birth + ", " + creditRating + ", " + accountNo + ", " + balance;
	}

	
	
}
