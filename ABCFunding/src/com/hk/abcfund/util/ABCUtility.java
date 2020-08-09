package com.hk.abcfund.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import com.hk.abcfund.model.dto.ABCLoanDto;

/**
 * ABC �ݵ��� ����ϴ� ��ƿ Ŭ����
 * ������� �ʰ� Ŭ����(static) �޼��常�� ���
 * @author 9age
 *
 */
public final class ABCUtility {
	/**
	 * ������ �ڸ�����ŭ ������ �����ϴ� �޼���
	 * @param count ������ ������ �ڸ���
	 * @return ������ ����
	 */
	public static String randomNumber(int count) {
		// ���� ������
		Random random = 
			new Random((long)(Math.random()*System.currentTimeMillis()));
		
		// ���� ���̱�
		StringBuilder randNum = new StringBuilder();
		for(int idx = 0; idx < count; idx++) {
			randNum.append(random.nextInt(10));
		}
		
		return randNum.toString();
	}
	
	/**
	 * ������Ϻ��� �Ľ��ϴ� �޼���
	 * @param birth �Ľ��� �������(8�ڸ�)
	 * @return �Ľ��� ����,����,����
	 */
	public static String[] parseBirth(String birth){
		// �Ľ��� ��,��,���� ���� �迭
		String[] parsed = new String[3];
		parsed[0] = birth.substring(0, 4);
		parsed[1] = birth.substring(4, 6);
		parsed[2] = birth.substring(6);
		
		return parsed;
	}
	
	/**
	 * ������Ͽ��� ���̸� ���ϴ� �޼���
	 * @param birth �Ľ��� �������
	 * @return ����
	 */
	public static int getAge(String birth){
		// ������� �Ľ�
		String[] parsed = parseBirth(birth);
		
		// ����
		int birthYear = Integer.parseInt(parsed[0]);
		
		// ���� ����
		Calendar cal = Calendar.getInstance();
		int currentYear = cal.get(Calendar.YEAR);
		
		return currentYear - birthYear + 1;
	}
	
	/**
	 * ������ ��,��,���� �ٿ��ִ� �޼���
	 * @param birth ���� �������
	 * @return ���� �������
	 */
	public static String getFullBirth(String birth){
		// ������� �Ľ�
		String[] parsed = parseBirth(birth);
		
		// ��,��,�� ���̱�
		StringBuilder append = new StringBuilder();
		append.append(parsed[0]);
		append.append("�� ");
		append.append(parsed[1]);
		append.append("�� ");
		append.append(parsed[2]);
		append.append("��");
		
		return append.toString();
	}
	
	/**
	 * ������¹�ȣ�� �����ϴ� �޼���
	 * @return ������ ������¹�ȣ
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
	 * �ݵ��Ⱓ�� �������� Ȯ���ϴ� �޼���
	 * @param requestDate �����û��(yyyy-mm-dd)
	 * @param expiryDate �ݵ��ϼ�
	 * @return �ݵ��Ⱓ�� �������� true
	 */
	public static boolean isExpired(String requestDate, int expiryDate){
		boolean isExp = false;
		
		// ���� ��¥ ���ϱ�
		Date currentDate = new Date();
		
		// �����û�� ���ϱ�
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date request = null;
		try {
			request = dateFormat.parse(requestDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		// �� ��¥�� ���� ���ϱ�
		long diff = currentDate.getTime() - request.getTime();
		diff /= 24 * 60 * 60 * 1000;
		
		// ������ �ݵ��ϼ��� �Ѿ����� �ݵ��Ⱓ�� ���� ��
		if(diff > expiryDate) isExp = true;
		
		return isExp;
	}
	
	/**
	 * ���� �� ��ȯ���� ��ȯ�ϴ� �޼���
	 * @param repay �����ȯ��
	 * @return ���� �� ��ȯ��¥
	 */
	public static String calcRepayDate(int repay) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());		// ���� ��¥�� ���Ѵ�.
		cal.add(Calendar.MONTH, 1);		// ���� ��¥�� ���� �޷� �����.
		cal.set(Calendar.DATE, repay);	// ���ڸ� ����Ϸ� �ٲ۴�.
		
		return formatter.format(cal.getTime());
	}
	
	/**
	 * �־��� ��¥�� �������� Ȯ���ϴ� �޼���
	 * @param date yyyy-MM-dd ������ ���ڿ�
	 * @return �����̸� true ��ȯ
	 */
	public static boolean isSameDate(String date){
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String today = formatter.format(new Date());
		
		return today.equals(date);
	}
	
	/**
	 * �����ݱյ��ȯ����� �̿��Ͽ� ����/��ȯ���� ���ϴ� �޼ҵ�
	 * @param money ����/�����
	 * @param loanDate ����Ⱓ
	 * @param loanrate ������
	 * @return ��ȯ��
	 */
	public static int getRepayMoney(int money, int loanDate ,double loanrate){
		return (int)Math.round(getEqualPrincipalPayment(money, loanDate, loanrate));
	}
	
	/**
	 * �����ݱյ��ȯ����� �ε��Ҽ� �״�� ��ȯ�ϴ� �޼���
	 * @param money ����/�����
	 * @param loanDate ����Ⱓ
	 * @param loanrate ������
	 * @return �Ǽ��� ǥ���� ��ȯ��
	 */
	public static double getEqualPrincipalPayment(int money, int loanDate ,double loanrate){
		return ((money * (loanrate / 12)) * 
				(Math.pow(1 + loanrate / 12, loanDate))) / 
				(Math.pow(1 + loanrate / 12, loanDate) - 1);
	}
	
	/**
	 * ���ڸ� ����Ͽ� �ε��Ҽ� �״�� ��ȯ�ϴ� �޼���
	 * @param balance �ܱ� �Ǵ� ����
	 * @param interstRate ������. �Ҽ� ��°�ڸ����� ǥ��
	 * @return ����
	 */
	public static double getInterest(int balance, float interestRate){
		return balance * interestRate / 12;
	}
}
