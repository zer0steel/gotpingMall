package com.got.enums;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

@JsonFormat(shape = Shape.OBJECT)
public enum DealCategory {
	CREATE_ORDER(0, "주문 생성"), WAREHOUSING(1, "입고"), EXISTING_STOCK(2, "기존재고"), SELL(3, "판매"),
	LOSS_DESTROYED(4, "분실 파손"), FREE(5,"무상 증정"), 
	OTHER_INCREASE(6, "기타 증가 사유"), OTHER_DECREASE(7, "기타 감소 사유");
	
	private int code;
	private String korName;

	private DealCategory(int code, String korName) {
		this.code = code;
		this.korName = korName;
	}

	public int getCode() {
		return code;
	}

	public String getKorName() {
		return korName;
	}
	
	public static DealCategory of(int code) {
		DealCategory c = map.get(code);
		if(c == null)
			throw new IllegalArgumentException("존재하지 않는 분류 코드 : " + code);
		return c;
	}
	
	public boolean isMinusStockCategory() {
		return (this == SELL || this == LOSS_DESTROYED || this == FREE || this == OTHER_DECREASE);
	}
	
	private static final Map<Integer, DealCategory> map = new HashMap<>();
	static {
		for(DealCategory c : values())
			map.put(c.code, c);
	}
}
