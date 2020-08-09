package com.hk.abcfund.model.dto;

import java.io.Serializable;

import org.springframework.web.multipart.MultipartFile;

/**
 * 대출신청 정보를 담아오는 DTO 클래스
 * @author 9age
 *
 */
public class ABCLoanDto implements Serializable {
	/** 대출상품을 식별할 대출코드 */
	private int loanCode;
	
	/** 대출의 진행상태 
	 * 펀딩진행중,펀딩완료,상환중,상환완료
	 * 한글로 최대6자
	 */
	private String progress;
	
	/**
	 * 현재 투자된 금액
	 * 0이상의 정수 
	 */
	private int currentMoney;
	
	/** 
	 * 현재 참여한 투자자 수
	 * 0이상의 정수
	 */
	private int jointCount;
	
	/**
	 * 현재 회차 수
	 * 0이상의 정수
	 */
	private int round;
	
	/**
	 * 대출 신청인의 이메일
	 */
	private String email;
	
	/**
	 * 대출 신청인의 이름
	 */
	private String name;
	
	/**
	 * 대출 신청인의 생년월일
	 */
	private String birth;
	
	/**
	 * 대출 신청인의 성별
	 */
	private String gender;
	
	/**
	 * 고용형태
	 * 정규직,비정규직,아르바이트,무직
	 * 한글 최대 6자
	 */
	private String employType;
	
	/**
	 * 회사규모
	 * 대기업,중견기업,중소기업,스타트업
	 * 한글 최대 4자
	 */
	private String scale;
	
	/**
	 * 재직기간
	 * 0이상의 2자리 정수
	 */
	private int serve;
	
	/**
	 * 연봉
	 * 단위는 만원
	 * 0이상의 7자리 정수
	 */
	private int salary;
	
	/**
	 * 대출 금리
	 * 8% 고정 금리
	 */
	private int interestRate;
	
	/**
	 * 상환방식
	 * 원리금균등분할상확 방식으로 고정
	 */
	private String repayType;
	
	/**
	 * 대출구분
	 * 개인신용대출로 고정
	 */
	private String loanType;
	
	/**
	 * 대출신청금
	 * 단위는 원
	 */
	private int loanMoney;
	
	/**
	 * 대출기간
	 * 입력가능값 : 1~12,18,24
	 * 단위는 개월수 
	 */
	private int loanDate;
	
	/**
	 * 희망상환일
	 * 입력가능 값 : 1,5,10,15,20,25
	 * 단위는 일
	 */
	private int repay;
	
	/**
	 * 소개글의 제목
	 */
	private String title;
	
	/**
	 * 소개글의 내용
	 */
	private String intro;
	
	/**
	 * 소개글의 파일 이름
	 */
	private String fname;
	
	/**
	 * 소개글의 파일
	 */
	private MultipartFile uploadfile;
	
	/**
	 * 펀딩신청 기간
	 * 입력가능 값 : 1~7
	 * 단위는 일
	 */
	private int expiryDate;
	
	/**
	 * 펀딩신청일
	 * 형식 : yyyy-mm-dd
	 */
	private String requestDate;

	/**
	 * 기본 생성자
	 */
	public ABCLoanDto() {}

	/**
	 * 모든 파라미터를 가지는 생성자
	 * @param loanCode 대출코드
	 * @param progress 진행상태
	 * @param currentMoney 현재까지 모집된 금액
	 * @param jointCount 현재까지 참여한 인원수
	 * @param round 현재 상환 회차수
	 * @param eamil 대출 신청인의 이메일
	 * @param name 대출 신청자의 이름
	 * @param birth 대출 신청자의 생년월일
	 * @param gender 대출 신청자의 성별
	 * @param employType 고용형태
	 * @param scale 회사규모
	 * @param serve 재직기간
	 * @param salary 연봉
	 * @param interestRate 대출금리
	 * @param repayType 대출상환방식(원리금균등분할)
	 * @param loanType 대출구분(개인신용대출)
	 * @param loanMoney 대출신청금
	 * @param loanDate 대출신청기간
	 * @param repay 희망상환일
	 * @param title 소개글의 제목
	 * @param intro 소개글의 내용
	 * @param fname 소개글의 파일 이름
	 * @param uploadfile 소개글의 파일
	 * @param expiryDate 펀딩모집기간
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
	 * 대출코드와 진행상황만을 초기화하는 생성자
	 * @param loanCode 대출코드
	 * @param progress 진행상황
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
