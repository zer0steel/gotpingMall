package com.got.enums;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

@JsonFormat(shape = Shape.OBJECT)
public enum GoodsStatus {
	FOR_SALE("�Ǹ� ��", 1), STAND_BY("�Ǹ� �����", 2), NOT_IN_STOCK("��� ����", 3);
	
	private static final Map<Integer, GoodsStatus> map = new HashMap<>();
	static {
		for(GoodsStatus s : values())
			map.put(s.code, s);
	}
	private String status;
	private int code;
	
	private GoodsStatus(String status, int code) {
		this.status = status;
		this.code = code;
	}
	public String getKor() {
		return this.status;
	}
	
	public int getCode() {
		return this.code;
	}
	
	public static GoodsStatus of(int code) {
		GoodsStatus s = map.get(code);
		if(s == null)
			throw new IllegalArgumentException("�������� �ʴ� �ڵ�");
		return s;
	}
}
