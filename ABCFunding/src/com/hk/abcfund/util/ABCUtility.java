package com.hk.abcfund.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

/**
 * Utility class for ABC Funding
 * Use only static member and method
 * @author 9age
 *
 */
public final class ABCUtility {
	/**
	 * Generates random number by a specified number of digits
	 * @param count A specified number of digits
	 * @return Generated random number
	 */
	public static String randomNumber(int count) {
		// Declare and initiate Random object
		Random random = 
			new Random((long)(Math.random()*System.currentTimeMillis()));
		
		// Generates random number
		StringBuilder randNum = new StringBuilder();
		for(int idx = 0; idx < count; idx++) {
			randNum.append(random.nextInt(10));
		}
		
		return randNum.toString();
	}
	
	/**
	 * Separate birth date string to array of year, month and date
	 * @param birth To parsing(8 digit)
	 * @return parsed array
	 */
	public static String[] parseBirth(String birth){
		// 파싱한 연,월,일을 담을 배열
		String[] parsed = new String[3];
		parsed[0] = birth.substring(0, 4);
		parsed[1] = birth.substring(4, 6);
		parsed[2] = birth.substring(6);
		
		return parsed;
	}
	
	/**
	 * Calculate age from birth date
	 * @param birth To calculate birth date
	 * @return calculated age
	 */
	public static int getAge(String birth){
		// parsing birth date
		String[] parsed = parseBirth(birth);
		
		// get year
		int birthYear = Integer.parseInt(parsed[0]);
		
		// current year
		Calendar cal = Calendar.getInstance();
		int currentYear = cal.get(Calendar.YEAR);
		
		return currentYear - birthYear + 1;
	}
	
	/**
	 * 각각에 연,월,일을 붙여주는 메서드
	 * @param birth 붙일 생년월일
	 * @return 붙인 생년월일
	 */
	public static String getFullBirth(String birth){
		// 생년월일 파싱
		String[] parsed = parseBirth(birth);
		
		// 연,월,일 붙이기
		StringBuilder append = new StringBuilder();
		append.append(parsed[0]);
		append.append("년 ");
		append.append(parsed[1]);
		append.append("월 ");
		append.append(parsed[2]);
		append.append("일");
		
		return append.toString();
	}
	
	/**
	 * 가상계좌번호를 생성하는 메서드
	 * @return 생성된 가상계좌번호
	 */
	public static String createVANumber() {
		StringBuilder accountNo = new StringBuilder();
		accountNo.append(randomNumber(3));
		accountNo.append("-");
		accountNo.append(randomNumber(6));
		accountNo.append("-");
		accountNo.append(randomNumber(3));
		
		return accountNo.toString();
	}
	
	/**
	 * 펀딩기간이 지났는지 확인하는 메서드
	 * @param requestDate 대출신청일(yyyy-mm-dd)
	 * @param expiryDate 펀딩일수
	 * @return 펀딩기간이 지났으면 true
	 */
	public static boolean isExpired(String requestDate, int expiryDate){
		boolean isExp = false;
		
		// 현재 날짜 구하기
		Date currentDate = new Date();
		
		// 대출신청일 구하기
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date request = null;
		try {
			request = dateFormat.parse(requestDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		// 두 날짜의 차이 구하기
		long diff = currentDate.getTime() - request.getTime();
		diff /= 24 * 60 * 60 * 1000;
		
		// 차일이 펀딩일수를 넘었으면 펀딩기간이 지난 것
		if(diff > expiryDate) isExp = true;
		
		return isExp;
	}
	
	/**
	 * 다음 달 상환일을 반환하는 메서드
	 * @param repay 희망상환일
	 * @return 다음 달 상환날짜
	 */
	public static String calcRepayDate(int repay) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());		// 현재 날짜를 구한다.
		cal.add(Calendar.MONTH, 1);		// 현재 날짜에 다음 달로 맞춘다.
		cal.set(Calendar.DATE, repay);	// 일자를 희망일로 바꾼다.
		
		return formatter.format(cal.getTime());
	}
	
	/**
	 * 주어진 날짜가 오늘인지 확인하는 메서드
	 * @param date yyyy-MM-dd 형태의 문자열
	 * @return 오늘이면 true 반환
	 */
	public static boolean isSameDate(String date){
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String today = formatter.format(new Date());
		
		return today.equals(date);
	}
	
	/**
	 * 원리금균등상환방식을 이용하여 납입/상환금을 구하는 메소드
	 * @param money 투자/대출금
	 * @param loanDate 대출기간
	 * @param loanrate 이자율
	 * @return 상환금
	 */
	public static int getRepayMoney(int money, int loanDate ,double loanrate){
		return (int)Math.round(getEqualPrincipalPayment(money, loanDate, loanrate));
	}
	
	/**
	 * 원리금균등상환방식을 부동소수 그대로 반환하는 메서드
	 * @param money 투자/대출금
	 * @param loanDate 대출기간
	 * @param loanrate 이자율
	 * @return 실수로 표현된 상환금
	 */
	public static double getEqualPrincipalPayment(int money, int loanDate ,double loanrate){
		return ((money * (loanrate / 12)) * 
				(Math.pow(1 + loanrate / 12, loanDate))) / 
				(Math.pow(1 + loanrate / 12, loanDate) - 1);
	}
	
	/**
	 * 이자를 계산하여 부동소수 그대로 반환하는 메서드
	 * @param balance 잔금 또는 원금
	 * @param interstRate 이자율. 소수 둘째자리까지 표현
	 * @return 이자
	 */
	public static double getInterest(int balance, float interestRate){
		return balance * interestRate / 12;
	}
}