package com.got.enums;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public enum MileageCategory {
	USE(1, "사용"), SAVE(2, "적립");
	
	private int code;
	private String kor;
	private static final Map<Integer, MileageCategory> map = new HashMap<>();

	static {
		for(MileageCategory c : values())
			map.put(c.code, c);
	}
	
	public static MileageCategory of(int code) {
		MileageCategory mc = map.get(code);
		return Objects.requireNonNull(mc);
	}
	
	private MileageCategory(int code, String kor) {
		this.code = code;
		this.kor = kor;
	}

	public int getCode() {
		return code;
	}

	public String getKorName() {
		return kor;
	}
	
	public boolean isMinusCategory() {
		return this == USE;
	}
}
