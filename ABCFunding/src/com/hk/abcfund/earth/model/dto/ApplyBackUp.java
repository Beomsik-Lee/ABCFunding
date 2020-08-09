package com.hk.abcfund.earth.model.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * @author cs
 *
 */
public class ApplyBackUp implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1930619027080603266L;
	/**
	 * 
	 */
	/**
	 * �Ŀ� ��� ��ȣ 
	 */
	private int earthno;
	/**
	 * �Ŀ�, �ۼ����̸�
	 */
	private String name;
	/**
	 * �Ŀ�, �Խù� ���
	 */
	private int pw;
	/**
	 * �Ŀ�, �ۼ��� �ڵ��� ��ȣ
	 */
	private String phone;
	/**
	 * �Ŀ� ����
	 */
	private String title;
	/**
	 * �Ŀ�, �Ŀ�����
	 */
	private String purpose;
	/**
	 * �Ŀ�, �������(���������)
	 */
	private String condition;
	/**
	 * �Ŀ�, ���� ���
	 */
	private String content;
	/**
	 * �Ŀ�, �Ŀ�������
	 */
	private String earths;
	/**
	 *  �Ŀ�, ������
	 */
	private String earthe;
	/**
	 * �Ŀ�, ��ǥ�ο�
	 */
	private String targetno;
	/**
	 * �Ŀ�, ��ǥ�ݾ�
	 */
	private String targetsum;
	
	/**
	 *  �⺻ ������
	 */
	public ApplyBackUp() {
	}
	
	/**
	 * ��ü ������
	 */
	public ApplyBackUp(int earthno, String name, int pw, String phone, String title, String purpose, String condition,
			String content, String earths, String earthe, String targetno, String targetsum) {
		super();
		this.earthno = earthno;
		this.name = name;
		this.pw = pw;
		this.phone = phone;
		this.title = title;
		this.purpose = purpose;
		this.condition = condition;
		this.content = content;
		this.earths = earths;
		this.earthe = earthe;
		this.targetno = targetno;
		this.targetsum = targetsum;
	}
	/**
	 * �ʼ������� ������
	 */	

	public ApplyBackUp(int earthno, String name, int pw, String phone, String title, String purpose, String condition,
			String content, String earths, String earthe) {
		super();
		this.earthno = earthno;
		this.name = name;
		this.pw = pw;
		this.phone = phone;
		this.title = title;
		this.purpose = purpose;
		this.condition = condition;
		this.content = content;
		this.earths = earths;
		this.earthe = earthe;
	}

	public int getEarthno() {
		return earthno;
	}

	public void setEarthno(int earthno) {
		this.earthno = earthno;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPw() {
		return pw;
	}

	public void setPw(int pw) {
		this.pw = pw;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPurpose() {
		return purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getEarths() {
		return earths;
	}

	public void setEarths(String earths) {
		this.earths = earths;
	}

	public String getEarthe() {
		return earthe;
	}

	public void setEarthe(String earthe) {
		this.earthe = earthe;
	}

	public String getTargetno() {
		return targetno;
	}

	public void setTargetno(String targetno) {
		this.targetno = targetno;
	}

	public String getTargetsum() {
		return targetsum;
	}

	public void setTargetsum(String targetsum) {
		this.targetsum = targetsum;
	}

	@Override
	public String toString() {
		return "ApplyBackUp [earthno=" + earthno + ", name=" + name + ", pw=" + pw + ", phone=" + phone + ", title="
				+ title + ", purpose=" + purpose + ", condition=" + condition + ", content=" + content + ", earths="
				+ earths + ", earthe=" + earthe + ", targetno=" + targetno + ", targetsum=" + targetsum + "]";
	}
	
	
	
}