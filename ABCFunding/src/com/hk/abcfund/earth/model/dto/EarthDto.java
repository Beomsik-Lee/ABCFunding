package com.hk.abcfund.earth.model.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * @author cs
 *
 */
public class EarthDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1815942459997228156L;
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
	 * �Ŀ�, �޼��ο�
	 */
	private int resultno;
	/**
	 * �Ŀ�, �޼� �ݾ� 
	 */
	private int resultsum;
	/**
	 * �Ŀ�, �޼� ����
	 */
	private String result;
	/**
	 * �Ŀ�, �Ŀ��ڹ�ȣ
	 */
	private int sponsorno;
	/**
	 * �Ŀ�, ó�������ڵ�
	 */
	private int code;
	
	/**
	 *  �⺻ ������
	 */
	public EarthDto() {
	}
	/**
	 * ��ü ������
	 */
	public EarthDto(int earthno, String name, int pw, String phone, String title, String purpose, String condition,
			String content, String earths, String earthe, String targetno, String targetsum, int resultno, int resultsum,
			String result, int sponsorno, int code) {
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
		this.resultno = resultno;
		this.resultsum = resultsum;
		this.result = result;
		this.sponsorno = sponsorno;
		this.code = code;
	}
	
	/**
	 * �ʼ� ������ ������ 
	 */
	
	public EarthDto(int earthno, String name, int pw, String phone, String title, String purpose, String condition,
			String content, String earths, String earthe, String result, int code) {
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
		this.result = result;
		this.code = code;
	}
	
	
	/**
	 * �ʼ� + ������ ��ȣ ������
	 */
	public EarthDto(int earthno, String name, int pw, String phone, String title, String purpose, String condition,
			String content, String earths, String earthe, String result, int sponsorno, int code) {
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
		this.result = result;
		this.sponsorno = sponsorno;
		this.code = code;
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
	public int getResultno() {
		return resultno;
	}
	public void setResultno(int resultno) {
		this.resultno = resultno;
	}
	public int getResultsum() {
		return resultsum;
	}
	public void setResultsum(int resultsum) {
		this.resultsum = resultsum;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public int getSponsorno() {
		return sponsorno;
	}
	public void setSponsorno(int sponsorno) {
		this.sponsorno = sponsorno;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	@Override
	public String toString() {
		return "EarthDto [earthno=" + earthno + ", name=" + name + ", pw=" + pw + ", phone=" + phone + ", title="
				+ title + ", purpose=" + purpose + ", condition=" + condition + ", content=" + content + ", earths="
				+ earths + ", earthe=" + earthe + ", targetno=" + targetno + ", targetsum=" + targetsum + ", resultno="
				+ resultno + ", resultsum=" + resultsum + ", result=" + result + ", sponsorno=" + sponsorno + ", code="
				+ code + "]";
	}
	
	
	
	
}