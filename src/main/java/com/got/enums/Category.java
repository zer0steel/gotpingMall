package com.got.enums;

import java.util.HashMap;
import java.util.Map;

public enum Category {
	HAT("����");
	
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
			throw new IllegalArgumentException("�������� �ʴ� ī�װ� : " + kor_name);
		return c;
	}
}
