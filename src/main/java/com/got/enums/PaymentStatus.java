package com.got.enums;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public enum PaymentStatus {
	
	PENDING_DEPOSIT(1, "결제 대기중", "ready"), ORDER_CANCLE(2, "주문 취소", "cancelled"), 
	REFUND(3, "환불", "refund"), COMPLETE(4, "결제 완료", "paid");
	
	private int code;
	private String kor, imptStatus;
	private static Map<Integer, PaymentStatus> map = new HashMap<>();
	static {
		for(PaymentStatus ps : values())
			map.put(ps.getCode(), ps);
	}
	
	PaymentStatus(int code, String kor, String imptStatus) {
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
	
	public static PaymentStatus of(String imptStatus) {
		for(PaymentStatus ps : values()) {
			if(ps.getImptStatus().equals(imptStatus)) {
				return ps;
			}
		}
		throw new IllegalArgumentException(imptStatus);
	}
	
	public static PaymentStatus of(int code) {
		PaymentStatus ps = map.get(code);
		return Objects.requireNonNull(ps);
	}
}
