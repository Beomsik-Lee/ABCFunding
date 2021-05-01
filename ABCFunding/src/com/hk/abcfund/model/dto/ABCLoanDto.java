package com.hk.abcfund.model.dto;

import java.io.Serializable;

import org.springframework.web.multipart.MultipartFile;

/**
 * DTO for loan
 * @author 9age
 *
 */
public class ABCLoanDto implements Serializable {
	private static final long serialVersionUID = 1L;

	/** loan code */
	private int loanCode;
	
	/** 
	 * progress of funding and repayments
	 */
	private String progress;
	
	/**
	 * invested money
	 */
	private int currentMoney;
	
	/** 
	 * number of joint
	 */
	private int jointCount;
	
	/**
	 * current rounds
	 */
	private int round;
	
	/**
	 * email of borrower
	 */
	private String email;
	
	/**
	 * name of borrower
	 */
	private String name;
	
	/**
	 * birth date of borrower
	 */
	private String birth;
	
	/**
	 * gender of borrower
	 */
	private String gender;
	
	/**
	 * type of employ
	 */
	private String employType;
	
	/**
	 * The company size
	 */
	private String scale;
	
	/**
	 * Term of employment
	 */
	private int serve;
	
	/**
	 * salary
	 */
	private int salary;
	
	/**
	 * rate of interest(8% fixed)
	 */
	private int interestRate;
	
	/**
	 * type of repayments
	 */
	private String repayType;
	
	/**
	 * type of loan
	 */
	private String loanType;
	
	/**
	 * A loan
	 */
	private int loanMoney;
	
	/**
	 * loan period
	 */
	private int loanDate;
	
	/**
	 * Desired repayments date
	 */
	private int repay;
	
	/**
	 * title of funding
	 */
	private String title;
	
	/**
	 * intro of funding
	 */
	private String intro;
	
	/**
	 * file name of introduction
	 */
	private String fname;
	
	/**
	 * uploaded file of introduction
	 */
	private MultipartFile uploadfile;
	
	/**
	 * Expire date
	 */
	private int expiryDate;
	
	/**
	 * Request date
	 */
	private String requestDate;

	/**
	 * Default constructor
	 */
	public ABCLoanDto() {}

	/**
	 * Constructor that have all parameters
	 * @param loanCode
	 * @param progress
	 * @param currentMoney
	 * @param jointCount
	 * @param round
	 * @param email
	 * @param name
	 * @param birth
	 * @param gender
	 * @param employType
	 * @param scale
	 * @param serve
	 * @param salary
	 * @param interestRate
	 * @param repayType
	 * @param loanType
	 * @param loanMoney
	 * @param loanDate
	 * @param repay
	 * @param title
	 * @param intro
	 * @param fname
	 * @param uploadfile
	 * @param expiryDate
	 * @param requestDate
	 */
	public ABCLoanDto(int loanCode, String progress, int currentMoney, int jointCount, int round, String email, String name,
			String birth, String gender, String employType, String scale, int serve, int salary, int interestRate,
			String repayType, String loanType, int loanMoney, int loanDate, int repay, String title, String intro,
			String fname, MultipartFile uploadfile, int expiryDate, String requestDate) {
		super();
		this.loanCode = loanCode;
		this.progress = progress;
		this.currentMoney = currentMoney;
		this.jointCount = jointCount;
		this.round = round;
		this.email = email;
		this.name = name;
		this.birth = birth;
		this.gender = gender;
		this.employType = employType;
		this.scale = scale;
		this.serve = serve;
		this.salary = salary;
		this.interestRate = interestRate;
		this.repayType = repayType;
		this.loanType = loanType;
		this.loanMoney = loanMoney;
		this.loanDate = loanDate;
		this.repay = repay;
		this.title = title;
		this.intro = intro;
		this.fname = fname;
		this.uploadfile = uploadfile;
		this.expiryDate = expiryDate;
		this.requestDate = requestDate;
	}
	
	

	/**
	 * Constructor that only initialize the loan code and progress
	 * @param loanCode
	 * @param progress
	 */
	public ABCLoanDto(int loanCode, String progress) {
		super();
		this.loanCode = loanCode;
		this.progress = progress;
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
	 * @return the currentMoney
	 */
	public int getCurrentMoney() {
		return currentMoney;
	}

	/**
	 * @param currentMoney the currentMoney to set
	 */
	public void setCurrentMoney(int currentMoney) {
		this.currentMoney = currentMoney;
	}

	/**
	 * @return the jointCount
	 */
	public int getJointCount() {
		return jointCount;
	}

	/**
	 * @param jointCount the jointCount to set
	 */
	public void setJointCount(int jointCount) {
		this.jointCount = jointCount;
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
	 * @return the employType
	 */
	public String getEmployType() {
		return employType;
	}

	/**
	 * @param employType the employType to set
	 */
	public void setEmployType(String employType) {
		this.employType = employType;
	}

	/**
	 * @return the scale
	 */
	public String getScale() {
		return scale;
	}

	/**
	 * @param scale the scale to set
	 */
	public void setScale(String scale) {
		this.scale = scale;
	}

	/**
	 * @return the serve
	 */
	public int getServe() {
		return serve;
	}

	/**
	 * @param serve the serve to set
	 */
	public void setServe(int serve) {
		this.serve = serve;
	}

	/**
	 * @return the salary
	 */
	public int getSalary() {
		return salary;
	}

	/**
	 * @param salary the salary to set
	 */
	public void setSalary(int salary) {
		this.salary = salary;
	}

	/**
	 * @return the interestRate
	 */
	public int getInterestRate() {
		return interestRate;
	}

	/**
	 * @param interestRate the interestRate to set
	 */
	public void setInterestRate(int interestRate) {
		this.interestRate = interestRate;
	}

	/**
	 * @return the repayType
	 */
	public String getRepayType() {
		return repayType;
	}

	/**
	 * @param repayType the repayType to set
	 */
	public void setRepayType(String repayType) {
		this.repayType = repayType;
	}

	/**
	 * @return the loanType
	 */
	public String getLoanType() {
		return loanType;
	}

	/**
	 * @param loanType the loanType to set
	 */
	public void setLoanType(String loanType) {
		this.loanType = loanType;
	}

	/**
	 * @return the loanMoney
	 */
	public int getLoanMoney() {
		return loanMoney;
	}

	/**
	 * @param loanMoney the loanMoney to set
	 */
	public void setLoanMoney(int loanMoney) {
		this.loanMoney = loanMoney;
	}

	/**
	 * @return the loanDate
	 */
	public int getLoanDate() {
		return loanDate;
	}

	/**
	 * @param loanDate the loanDate to set
	 */
	public void setLoanDate(int loanDate) {
		this.loanDate = loanDate;
	}

	/**
	 * @return the repay
	 */
	public int getRepay() {
		return repay;
	}

	/**
	 * @param repay the repay to set
	 */
	public void setRepay(int repay) {
		this.repay = repay;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the intro
	 */
	public String getIntro() {
		return intro;
	}

	/**
	 * @param intro the intro to set
	 */
	public void setIntro(String intro) {
		this.intro = intro;
	}

	/**
	 * @return the fname
	 */
	public String getFname() {
		return fname;
	}

	/**
	 * @param fname the fname to set
	 */
	public void setFname(String fname) {
		this.fname = fname;
	}
	
	/**
	 * @return the uploadfile
	 */
	public MultipartFile getUploadfile() {
		return uploadfile;
	}

	/**
	 * @param uploadfile the uploadfile to set
	 */
	public void setUploadfile(MultipartFile uploadfile) {
		this.uploadfile = uploadfile;
	}

	/**
	 * @return the expiryDate
	 */
	public int getExpiryDate() {
		return expiryDate;
	}

	/**
	 * @param expiryDate the expiryDate to set
	 */
	public void setExpiryDate(int expiryDate) {
		this.expiryDate = expiryDate;
	}
	
	

	/**
	 * @return the requestDate
	 */
	public String getRequestDate() {
		return requestDate;
	}

	/**
	 * @param requestDate the requestDate to set
	 */
	public void setRequestDate(String requestDate) {
		this.requestDate = requestDate;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ABCLoanDto [loanCode=" + loanCode + ", progress=" + progress + ", currentMoney=" + currentMoney
				+ ", jointCount=" + jointCount + ", round=" + round + ", email=" + email + ", name=" + name + ", birth="
				+ birth + ", gender=" + gender + ", employType=" + employType + ", scale=" + scale + ", serve=" + serve
				+ ", salary=" + salary + ", interestRate=" + interestRate + ", repayType=" + repayType + ", loanType="
				+ loanType + ", loanMoney=" + loanMoney + ", loanDate=" + loanDate + ", repay=" + repay + ", title="
				+ title + ", intro=" + intro + ", fname=" + fname + ", uploadfile=" + uploadfile + ", expiryDate="
				+ expiryDate + ", requestDate=" + requestDate + "]";
	}
	
	

}