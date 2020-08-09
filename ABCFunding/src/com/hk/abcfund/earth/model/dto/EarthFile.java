package com.hk.abcfund.earth.model.dto;

import java.io.Serializable;

import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author cs
 *
 */
public class EarthFile implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7615756431082951753L;
	
	private int earthno;
	/**
	 * �Ŀ� ��û�̹��� ����
	 */
	private String applyname;
	private String applypath;
	private String applysavename;
	/**
	 * �Ŀ� ����̹��� ���� 
	 */
	private String reginame;
	private String regipath;
	private String regisavename;
	/**
	 *  �Ŀ� ���� �̹��� ����
	 */
	private String resultname;
	private String resultpath;
	private String resultsavename;
	
	/**
	 *  ���� �⺻������ 
	 */
	public EarthFile() {
	}


	/**
	 *  ���� ��ü ������
	 */
	
	public EarthFile(int earthno, String applyname, String applypath, String applysavename, String reginame,
			String regipath, String regisavename, String resultname, String resultpath, String resultsavename) {
		this.earthno = earthno;
		this.applyname = applyname;
		this.applypath = applypath;
		this.applysavename = applysavename;
		this.reginame = reginame;
		this.regipath = regipath;
		this.regisavename = regisavename;
		this.resultname = resultname;
		this.resultpath = resultpath;
		this.resultsavename = resultsavename;
	}
	
	/**
	 *  �Ŀ� ��û ������ 
	 */	
	
	public EarthFile(int earthno, String applyname, String applypath, String applysavename) {
		this.earthno = earthno;
		this.applyname = applyname;
		this.applypath = applypath;
		this.applysavename = applysavename;
	}
	/**
	 *  �Ŀ� ��û, ��� ������ 
	 */
	
	public EarthFile(int earthno, String applyname, String applypath, String applysavename, String reginame,
			String regipath) {
		super();
		this.earthno = earthno;
		this.applyname = applyname;
		this.applypath = applypath;
		this.applysavename = applysavename;
		this.reginame = reginame;
		this.regipath = regipath;
	}

	public int getEarthno() {
		return earthno;
	}

	public void setEarthno(int earthno) {
		this.earthno = earthno;
	}

	public String getApplyname() {
		return applyname;
	}

	public void setApplyname(String applyname) {
		this.applyname = applyname;
	}

	public String getApplypath() {
		return applypath;
	}

	public void setApplypath(String applypath) {
		this.applypath = applypath;
	}

	public String getApplysavename() {
		return applysavename;
	}

	public void setApplysavename(String applysavename) {
		this.applysavename = applysavename;
	}

	public String getReginame() {
		return reginame;
	}

	public void setReginame(String reginame) {
		this.reginame = reginame;
	}

	public String getRegipath() {
		return regipath;
	}

	public void setRegipath(String regipath) {
		this.regipath = regipath;
	}

	public String getRegisavename() {
		return regisavename;
	}

	public void setRegisavename(String regisavename) {
		this.regisavename = regisavename;
	}

	public String getResultname() {
		return resultname;
	}

	public void setResultname(String resultname) {
		this.resultname = resultname;
	}

	public String getResultpath() {
		return resultpath;
	}

	public void setResultpath(String resultpath) {
		this.resultpath = resultpath;
	}

	public String getResultsavename() {
		return resultsavename;
	}

	public void setResultsavename(String resultsavename) {
		this.resultsavename = resultsavename;
	}

	@Override
	public String toString() {
		return "EarthFile [earthno=" + earthno + ", applyname=" + applyname + ", applypath=" + applypath
				+ ", applysavename=" + applysavename + ", reginame=" + reginame + ", regipath=" + regipath
				+ ", regisavename=" + regisavename + ", resultname=" + resultname + ", resultpath=" + resultpath
				+ ", resultsavename=" + resultsavename + "]";
	}
	
	
}