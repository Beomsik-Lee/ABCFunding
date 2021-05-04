package com.hk.abcfund.enums;

/**
 * Enumeration class of gender
 * @author Beom
 *
 */
public enum ABCGenderType {
	MALE("0", "Male"),
	FEMALE("1", "Female");
	
	private String code;
	private String name;
	
	ABCGenderType(String code, String name) {
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
		// Spring 3 does not support Java 8 lambdas
//		return Arrays.stream(ABCGenderType.values())
//				.filter(type -> type.getCode().equals(name))
//				.findAny()
//				.orElse(MALE)
//				.getCode();
		String code = "";
		for (ABCGenderType gender : ABCGenderType.values()) {
			if (gender.getName().equals(name)) {
				code = gender.getCode();
				break;
			}
		}
		
		return code;
	}
	
	public static String findName(String code) {
//		return Arrays.stream(ABCGenderType.values())
//				.filter(type -> type.getCode().equals(code))
//				.findAny()
//				.orElse(MALE)
//				.getName();
		String name = "";
		for (ABCGenderType gender : ABCGenderType.values()) {
			if (gender.getCode().equals(code)) {
				name = gender.getName();
				break;
			}
		}
		
		return name;
	}
}
