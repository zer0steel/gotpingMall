package com.got.enums;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

@JsonFormat(shape = Shape.OBJECT)
public enum Menu_level {
	BIG(1, "��з�"), MIDDLE(2, "�ߺз�"), SMALL(3, "�Һз�");
	
	private static final Map<Integer, Menu_level> map = new HashMap<>();
	static {
		for(Menu_level m : values())
			map.put(m.menu_level, m);
	}
	
	private String kor_name;
	private int menu_level;

	private Menu_level(int menu_level, String kor_name) {
		this.menu_level = menu_level;
		this.kor_name = kor_name;
	}
	
	public String getKorName() {
		return this.kor_name;
	}
	
	public int getCode() {
		return this.menu_level;
	}
	
	public static Menu_level of(int menu_level) {
		Menu_level m = map.get(menu_level);
		if(m == null)
			throw new IllegalArgumentException("�������� �ʴ� �з� �ڵ� : " + menu_level);
		return m;
	}
}
