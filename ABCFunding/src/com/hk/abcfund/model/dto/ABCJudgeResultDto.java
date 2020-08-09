/**
 * 
 */
package com.hk.abcfund.model.dto;

import java.io.Serializable;

/**
 * 심사결과를 담을 DTO 클래스
 * @author 9age
 *
 */
public class ABCJudgeResultDto implements Serializable {
	private int loanCode;
	private String email;
	private String commentary;
	private int result;
	
	/**
	 * 기본 생성자 
	 */
	public ABCJudgeResultDto() {}

	/**
	 * 전체 변수를 포함한 생성자
	 * @param loanCode 대출코드
	 * @param email 회원의 이메일
	 * @param commentary 대출신청건의 심사평
	 * @param result 대출신청건의 심사결과
	 */
	public ABCJudgeResultDto(int loanCode, String email, String commentary, int result) {
		super();
		this.loanCode = loanCode;
		this.email = email;
		this.commentary = commentary;
		this.result = result;
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
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the commentary
	 */
	public String getCommentary() {
		return commentary;
	}

	/**
	 * @param commentary the commentary to set
	 */
	public void setCommentary(String commentary) {
		this.commentary = commentary;
	}

	/**
	 * @return the result
	 */
	public int getResult() {
		return result;
	}

	/**
	 * @param result the result to set
	 */
	public void setResult(int result) {
		this.result = result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return loanCode + ", " + email + ", " + commentary + ", " + result;
	}
	
	
}
