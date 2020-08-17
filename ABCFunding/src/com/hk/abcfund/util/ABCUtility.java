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
	 * Append year, month and date to birth date
	 * @param birth To append
	 * @return Appended birth date
	 */
	public static String getFullBirth(String birth){
		// parsing birth date
		String[] parsed = parseBirth(birth);
		
		// Append string
		StringBuilder append = new StringBuilder();
		append.append(parsed[0]);
		append.append("Year ");
		append.append(parsed[1]);
		append.append("Month ");
		append.append(parsed[2]);
		append.append("Date");
		
		return append.toString();
	}
	
	/**
	 * Create virtual account number
	 * @return Generated account number
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
	 * Check if fund is over
	 * @param requestDate date of loan request(yyyy-mm-dd)
	 * @param expiryDate funding days
	 * @return Return true if funding has expired
	 */
	public static boolean isExpired(String requestDate, int expiryDate){
		boolean isExp = false;
		
		// Get current date
		Date currentDate = new Date();
		
		// Get date of loan request
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date request = null;
		try {
			request = dateFormat.parse(requestDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		// Calculate the difference between two dates 
		long diff = currentDate.getTime() - request.getTime();
		diff /= 24 * 60 * 60 * 1000;
		
		// If the difference bigger than expired date, then funding is over.
		if(diff > expiryDate) isExp = true;
		
		return isExp;
	}
	
	/**
	 * Get repayments date of next month
	 * @param repay repayments date
	 * @return Next month's repayments date
	 */
	public static String calcRepayDate(int repay) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());		// Get current date
		cal.add(Calendar.MONTH, 1);		// Set next month
		cal.set(Calendar.DATE, repay);	// Set date to repayments date
		
		return formatter.format(cal.getTime());
	}
	
	/**
	 * Check if given date is current date 
	 * @param date the date string formatted by yyyy-MM-dd
	 * @return Return true if current date
	 */
	public static boolean isSameDate(String date){
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String today = formatter.format(new Date());
		
		return today.equals(date);
	}
	
	/**
	 * Get repayments by level payments on capital and interest
	 * @param money investment or loan
	 * @param loanDate loan date
	 * @param loanrate loan interest
	 * @return repayments
	 */
	public static int getRepayMoney(int money, int loanDate ,double loanrate){
		return (int)Math.round(getEqualPrincipalPayment(money, loanDate, loanrate));
	}
	
	/**
	 * Return repayments as double type
	 * @param money investment or loan
	 * @param loanDate loan date
	 * @param loanrate loan interest
	 * @return repayments as double type
	 */
	public static double getEqualPrincipalPayment(int money, int loanDate ,double loanrate){
		return ((money * (loanrate / 12)) * 
				(Math.pow(1 + loanrate / 12, loanDate))) / 
				(Math.pow(1 + loanrate / 12, loanDate) - 1);
	}
	
	/**
	 * Calculate interest
	 * @param balance balance
	 * @param interestRate interest rate
	 * @return Return interest
	 */
	public static double getInterest(int balance, float interestRate){
		return balance * interestRate / 12;
	}
}