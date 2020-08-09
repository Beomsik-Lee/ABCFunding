package com.hk.abcfund.model.dto;

import java.io.Serializable;

/**
 * @author 9age
 *
 */
public class ABCMemberDto implements Serializable {
	/** 회원 이메일 */
	private String email;
	
	/** 회원 이메일 앞자리 */
	private String email1;
	
	/** 회원 이메일 뒷자리 */
	private String email2;
	
	/** 
	 * 회원 비밀번호
	 * 영문대소문자, 숫자, 특수문자 포함한 6~20자까지 
	 */
	private String pwd;
	
	/**
	 * 회원 이름
	 * 한글 6자까지
	 */
	private String name;
	
	/**
	 * 회원 생년월일
	 * 구분자없이 총 8자리
	 */
	private String birth;
	
	/** 회원 생년도 */
	private String year;
	
	/** 회원 생월 */
	private String month;
	
	/** 회원 생일 */
	private String day;
	
	/**
	 * 회원 성별
	 * 남자 또는 여자
	 */
	private String gender;
	
	/**
	 * 회원 신용등급
	 * 1~9까지
	 */
	private int creditRating;
	
	/**
	 * 대출건수
	 * 0이상의 정수
	 */
	private int loanCount;
	
	/**
	 * 투자건수
	 * 0이상의 정수
	 */
	private int investCount;
	
	/**
	 * 후원 건수
	 */
	private int supportCount;
	
	/**
	 * 회원 등급
	 * 일반회원은 1, 관리자는 2
	 * 기본값 1
	 */
	private int grade;
	
	/**
	 * 회원인증을 위한 이메일 인증코드
	 */
	private String authCode;

	/**
	 * 아무것도 없는 기본생성자
	 */
	public ABCMemberDto() {}

	/**
	 * @param email 전체 이메일
	 * @param email1 이메일 앞자리
	 * @param email2 이메일 뒷자리
	 * @param pwd 비밀번호
	 * @param name 이름
	 * @param birth 생년월일
	 * @param year 생년
	 * @param month 생월
	 * @param day 생일
	 * @param gender 성별
	 * @param creditRating 신용등급
	 * @param loanCount 대출건수
	 * @param investCount 투자건수
	 * @param supportCount 후원건수
	 * @param grade 회원등급
	 */
	public ABCMemberDto(String email, String email1, String email2, String pwd, String name, String birth, String year,
			String month, String day, String gender, int creditRating, int loanCount, int investCount, int grade, int supportCount) {
		super();
		this.email = email;
		this.email1 = email1;
		this.email2 = email2;
		this.pwd = pwd;
		this.name = name;
		this.birth = birth;
		this.year = year;
		this.month = month;
		this.day = day;
		this.gender = gender;
		this.creditRating = creditRating;
		this.loanCount = loanCount;
		this.investCount = investCount;
		this.supportCount = supportCount;
		this.grade = grade;
	}

	/**
	 * email1과 email2에 값이 있어야 한다.
	 * @return the email
	 */
	public String getEmail() {
		if(email==null)
			email = email1 + "@" + email2;
		return email;
	}

	/**
	 * @return the email1
	 */
	public String getEmail1() {
		return email1;
	}

	/**
	 * @return the email2
	 */
	public String getEmail2() {
		return email2;
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
	 * year, month, day에 값이 있어야 함.
	 * @return the birth
	 */
	public String getBirth() {
		if(birth==null)
		birth = year + month + day;
		return birth;
	}

	/**
	 * @return the year
	 */
	public String getYear() {
		return year;
	}

	/**
	 * @return the month
	 */
	public String getMonth() {
		return month;
	}

	/**
	 * @return the day
	 */
	public String getDay() {
		return day;
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
	 * @param email1 the email1 to set
	 */
	public void setEmail1(String email1) {
		this.email1 = email1;
	}

	/**
	 * @param email2 the email2 to set
	 */
	public void setEmail2(String email2) {
		this.email2 = email2;
	}

	/**
	 * @param year the year to set
	 */
	public void setYear(String year) {
		this.year = year;
	}

	/**
	 * @param month the month to set
	 */
	public void setMonth(String month) {
		this.month = month;
	}

	/**
	 * @param day the day to set
	 */
	public void setDay(String day) {
		this.day = day;
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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ABCMemberDto [email=" + email + ", email1=" + email1 + ", email2=" + email2 + ", pwd=" + pwd + ", name="
				+ name + ", birth=" + birth + ", year=" + year + ", month=" + month + ", day=" + day + ", gender="
				+ gender + ", creditRating=" + creditRating + ", loanCount=" + loanCount + ", investCount="
				+ investCount + ", supportCount=" + supportCount + ", grade=" + grade + ", authCode=" + authCode + "]";
	}

	
	
	
}