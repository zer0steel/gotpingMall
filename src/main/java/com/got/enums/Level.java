package com.got.enums;

import java.util.HashMap;

import java.util.Map;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

@JsonFormat(shape = Shape.OBJECT)
public enum Level {
	BIG(1, "대분류"), 
	MIDDLE(2, "중분류"), 
	SMALL(3, "소분류");
	
	private static final Map<Integer, Level> map = new HashMap<>();
	static {
		for(Level m : values())
			map.put(m.levels, m);
	}
	
	private String korName;
	private int levels;
	
	private Level(int levels, String korName) {
		this.levels = levels;
		this.korName = korName;
	}

	public String getKorName() {
		return this.korName;
	}

	public int getCode() {
		return this.levels;
	}
	
	public static Level of(int levels) {
		Level m = map.get(levels);
		return Objects.requireNonNull(m);
	}
}
