package com.hk.abcfund.model.dto;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.hk.abcfund.util.ABCUtility;

/**
 * DTO for member
 * @author 9age
 *
 */
public class ABCMemberDto implements Serializable {
	/** email */
	private String email;
	/** 
	 * password
	 */
	private String pwd;
	
	/**
	 * name
	 */
	private String name;
	
	/**
	 * birth day by String
	 */
	private String birth;
	
	/**
	 * Year of birth day
	 */
	private String year;	
	
	/**
	 * gender
	 */
	private String gender;
	
	/**
	 * credit rating
	 */
	private int creditRating;
	
	/**
	 * number of loan
	 */
	private int loanCount;
	
	/**
	 * number of investment
	 */
	private int investCount;
	
	/**
	 * number of support
	 */
	private int supportCount;
	
	/**
	 * grade
	 */
	private int grade;
	
	/**
	 * auth code
	 */
	private String authCode = "1";

	/**
	 * The constructor that have all parameters
	 */
	public ABCMemberDto() {}

	/**
	 * 
	 * @param email
	 * @param email1
	 * @param email2
	 * @param pwd
	 * @param name
	 * @param birth
	 * @param year
	 * @param month
	 * @param day
	 * @param gender
	 * @param creditRating
	 * @param loanCount
	 * @param investCount
	 * @param grade
	 * @param supportCount
	 */
	public ABCMemberDto(String email, String pwd, String name, String birth, String gender, int creditRating, int loanCount, int investCount, int grade, int supportCount) {
		super();
		this.email = email;
		this.pwd = pwd;
		this.name = name;
		this.birth = birth;
		this.gender = gender;
		this.creditRating = creditRating;
		this.loanCount = loanCount;
		this.investCount = investCount;
		this.supportCount = supportCount;
		this.grade = grade;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	
	/**
	 * @return the pwd
	 */
	public String getPwd() {
		return pwd;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the birth
	 */
	public String getBirth() {
		return birth;
	}

	/**
	 * @return the gender
	 */
	public String getGender() {
		return gender;
	}

	/**
	 * @return the creditRating
	 */
	public int getCreditRating() {
		return creditRating;
	}

	/**
	 * @return the loanCount
	 */
	public int getLoanCount() {
		return loanCount;
	}

	/**
	 * @return the investCount
	 */
	public int getInvestCount() {
		return investCount;
	}

	/**
	 * @return the grade
	 */
	public int getGrade() {
		return grade;
	}

	/**
	 * @return the authCode
	 */
	public String getAuthCode() {
		return authCode;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @param pwd the pwd to set
	 */
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @param birth the birth to set
	 */
	public void setBirth(String birth) {
		this.birth = birth;
	}

	/**
	 * @param gender the gender to set
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}

	/**
	 * @param creditRating the creditRating to set
	 */
	public void setCreditRating(int creditRating) {
		this.creditRating = creditRating;
	}

	/**
	 * @param loanCount the loanCount to set
	 */
	public void setLoanCount(int loanCount) {
		this.loanCount = loanCount;
	}

	/**
	 * @param investCount the investCount to set
	 */
	public void setInvestCount(int investCount) {
		this.investCount = investCount;
	}

	/**
	 * @param grade the grade to set
	 */
	public void setGrade(int grade) {
		this.grade = grade;
	}
	
	/**
	 * @param authCode the authCode to set
	 */
	public void setAuthCode(String authCode) {
		this.authCode = authCode;
	}

	/**
	 * @return the supportCount
	 */
	public int getSupportCount() {
		return supportCount;
	}

	/**
	 * @param supportCount the supportCount to set
	 */
	public void setSupportCount(int supportCount) {
		this.supportCount = supportCount;
	}
	
	/**
	 * @return year of birth day
	 */
	public String getYear() {
		return year;
	}
	
	/**
	 * @param year year of birth day
	 */ 
	public void setYear(String year) {
		this.year = year;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ABCMemberDto [email=" + email + ", pwd=" + pwd + ", name="
				+ name + ", birth=" + birth + ", gender="
				+ gender + ", creditRating=" + creditRating + ", loanCount=" + loanCount + ", investCount="
				+ investCount + ", supportCount=" + supportCount + ", grade=" + grade + ", authCode=" + authCode + "]";
	}

	
	
	
}