package com.hk.abcfund.earth.model.dto;

import java.io.Serializable;

/**
 * @author cs
 *
 */
public class SponsorDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5872326225063799265L;

	private int sponsorno;
	private String sponsorid;
	private int code;
	
	public SponsorDto() {
	}

	public SponsorDto(int sponsorno, String sponsorid, int code) {
		this.sponsorno = sponsorno;
		this.sponsorid = sponsorid;
		this.code = code;
	}

	public int getSponsorno() {
		return sponsorno;
	}

	public void setSponsorno(int sponsorno) {
		this.sponsorno = sponsorno;
	}

	public String getSponsorid() {
		return sponsorid;
	}

	public void setSponsorid(String sponsorid) {
		this.sponsorid = sponsorid;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	@Override
	public String toString() {
		return "Sponsor [sponsorno=" + sponsorno + ", sponsorid=" + sponsorid + ", code=" + code + "]";
	}
	
	
	
}