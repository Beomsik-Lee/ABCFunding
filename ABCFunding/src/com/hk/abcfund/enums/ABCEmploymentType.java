package com.hk.abcfund.enums;

/**
 * Enumeration class of employment tpye
 * @author Beom
 *
 */
public enum ABCEmploymentType {
	REGUALR("0", "Regular"),
	NON_REGULAR("1", "Non-regular"),
	PART_TIME_JOB("2", "Part time job"),
	NOT_EMPLOYED("3", "Not employed");
	
	private String code;
	private String name;
	
	ABCEmploymentType(String code, String name) {
		this.code = code;
		this.name = name;
	}
	
	public String getCode() {
		return code;
	}
	
	public String getName() {
		return name;
	}
	
	public static String findCode(String name) {
		String code = "";
		for (ABCEmploymentType gender : ABCEmploymentType.values()) {
			if (gender.getName().equals(name)) {
				code = gender.getCode();
				break;
			}
		}
		
		return code;
	}
	
	public static String findName(String code) {
		String name = "";
		for (ABCEmploymentType gender : ABCEmploymentType.values()) {
			if (gender.getCode().equals(code)) {
				name = gender.getName();
				break;
			}
		}
		
		return name;
	}
}
