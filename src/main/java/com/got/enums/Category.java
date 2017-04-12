package com.got.enums;

import java.util.HashMap;
import java.util.Map;

public enum Category {
	HAT("모자");
	
	private String kor_name;
	
	private static final Map<String, Category> map = new HashMap<>(values().length + 1);
	
	static {
		for(Category c : values())
			map.put(c.kor_name, c);
	}
	
	Category(String kor_name) {
		this.kor_name = kor_name;
	}
	
	public static Category of(String kor_name) {
		Category c = map.get(kor_name);
		if(c == null)
			throw new IllegalArgumentException("존재하지 않는 카테고리 : " + kor_name);
		return c;
	}
}
