package com.hk.abcfund.earth.util;

import java.io.Serializable;

public class EyeEarthParam implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -199083822579445726L;
	private int start;
	private int end;
	private int pageNumber=0;
	private int recordCountPerPage=10;
	
	public EyeEarthParam() {
	}
	public EyeEarthParam(int start, int end) {
		this.start = start;
		this.end = end;
	}
	
	public EyeEarthParam(int start, int end, int pageNumber, int recordCountPerPage) {
		this.start = start;
		this.end = end;
		this.pageNumber = pageNumber;
		this.recordCountPerPage = recordCountPerPage;
	}
	
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public int getEnd() {
		return end;
	}
	public void setEnd(int end) {
		this.end = end;
	}
	public int getPageNumber() {
		return pageNumber;
	}
	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}
	public int getRecordCountPerPage() {
		return recordCountPerPage;
	}
	public void setRecordCountPerPage(int recordCountPerPage) {
		this.recordCountPerPage = recordCountPerPage;
	}
	
	@Override
	public String toString() {
		return start + ", " + end + ", " + pageNumber + ", " + recordCountPerPage;
	}
	
}
