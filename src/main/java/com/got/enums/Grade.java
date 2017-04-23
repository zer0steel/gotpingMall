package com.got.enums;

import java.util.HashMap;
import java.util.Map;

public enum Grade {
	UNAUTHORIZED(0,0),BRONZE(1, 100), SLIVER(2, 300), GOLD(3, 500), ADMIN(10, -1000);
	
	private final int grade;
	private final int requirePoint;
	
	private static final Map<Integer, Grade> map = new HashMap<>(values().length);
	
	static {
		for(Grade g : values()) 
			map.put(g.grade, g);
	}
	
	Grade(int grade, int requirePoint) {
		this.grade = grade;
		this.requirePoint = requirePoint;
	}
	
	public static Grade of(int grade) {
		Grade g = map.get(grade);
		if(g == null)
			throw new IllegalArgumentException("존재하지 않는 등급 번호 : " + grade);
		return g;
	}
	
	public int getCode() {
		return this.grade;
	}
	
	public int getRequirePoint() {
		return this.requirePoint;
	}
}
