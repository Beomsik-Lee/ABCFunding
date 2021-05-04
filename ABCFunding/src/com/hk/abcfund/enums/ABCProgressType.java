package com.hk.abcfund.enums;

/**
 * Enumeration class of employment tpye
 * @author Beom
 *
 */
public enum ABCProgressType {
	ACCEPTED("0", "Accepted"),
	REPAYING("1", "Repaying"),
	REPAYED("2", "Repayed");
	
	private String code;
	private String name;
	
	ABCProgressType(String code, String name) {
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
		for (ABCProgressType gender : ABCProgressType.values()) {
			if (gender.getName().equals(name)) {
				code = gender.getCode();
				break;
			}
		}
		
		return code;
	}
	
	public static String findName(String code) {
		String name = "";
		for (ABCProgressType gender : ABCProgressType.values()) {
			if (gender.getCode().equals(code)) {
				name = gender.getName();
				break;
			}
		}
		
		return name;
	}
}
