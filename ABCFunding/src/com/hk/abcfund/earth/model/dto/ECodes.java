package com.hk.abcfund.earth.model.dto;

import java.io.Serializable;

/**
 * @author cs
 *
 */
public class ECodes implements Serializable {

	private static final long serialVersionUID = 433139821647004270L;
	/**
	 * 처리상태코드
	 */
	private int code;
	/**
	 * 처리상태이름
	 */
	private String name;
	public ECodes() {
	}
	public ECodes(int code, String name) {
		this.code = code;
		this.name = name;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "ECodes [code=" + code + ", name=" + name + "]";
	}
	
}