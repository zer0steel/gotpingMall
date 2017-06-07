package com.got.enums;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public enum OrderStatus {
	
	PENDING_DEPOSIT(1, "결제 대기중", "ready"), ORDER_CANCLE(2, "주문 취소", "cancelled"), 
	REFUND(3, "환불", "refund"), CHECKOUT_COMPLETE(4, "결제 완료", "paid"),
	DELIVERY_READY(10, "배송 준비중", ""),DELIVERY(11, "배송중", ""), DELIVERY_COMPLETE(12, "배송 완료", "");
	
	private int code;
	private String kor, imptStatus;
	private static Map<Integer, OrderStatus> map = new HashMap<>();
	static {
		for(OrderStatus ps : values())
			map.put(ps.getCode(), ps);
	}
	
	OrderStatus(int code, String kor, String imptStatus) {
		this.code = code;
		this.kor = kor;
		this.imptStatus = imptStatus;
	}
	
	public int getCode() {
		return this.code;
	}
	
	public String getKor() {
		return this.kor;
	}
	
	public String getImptStatus() {
		return this.imptStatus;
	}
	
	public static OrderStatus of(String imptStatus) {
		for(OrderStatus ps : values()) {
			if(ps.getImptStatus().equals(imptStatus)) {
				return ps;
			}
		}
		throw new IllegalArgumentException(imptStatus);
	}
	
	public static OrderStatus of(int code) {
		OrderStatus ps = map.get(code);
		return Objects.requireNonNull(ps);
	}
}
