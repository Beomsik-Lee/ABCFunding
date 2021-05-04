package com.hk.abcfund.enums;

/**
 * Enumeration class of employment tpye
 * @author Beom
 *
 */
public enum ABCEnterpriseSizeType {
	LARGE_ENTERPRISE("0", "Large enterprise"),
	MEDIUM_SIZED_ENTERPRISE("1", "Medium-sized enterprise"),
	SMALL_SIZED_ENTERPRISE("2", "Small-sized enterprise");
	
	private String code;
	private String name;
	
	ABCEnterpriseSizeType(String code, String name) {
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
		for (ABCEnterpriseSizeType gender : ABCEnterpriseSizeType.values()) {
			if (gender.getName().equals(name)) {
				code = gender.getCode();
				break;
			}
		}
		
		return code;
	}
	
	public static String findName(String code) {
		String name = "";
		for (ABCEnterpriseSizeType gender : ABCEnterpriseSizeType.values()) {
			if (gender.getCode().equals(code)) {
				name = gender.getName();
				break;
			}
		}
		
		return name;
	}
}
