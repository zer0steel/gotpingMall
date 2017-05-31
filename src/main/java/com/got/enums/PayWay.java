package com.got.enums;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public enum PayWay {

	CARD(1, "카드", "card"), NON_DEPOSIT(2, "무통장 입금", "trans"), 
	PHONE(3, "핸드폰", "phone"), ALL_MILEAGE(4, "전액 마일리지", "allMileage");
	
	private int code;
	private String kor, imptMethod;
	private static final Map<Integer, PayWay> MAP = new HashMap<>();
	
	static {
		for(PayWay way : values())
			MAP.put(way.getCode(), way);
	}
	
	private PayWay(int code, String kor, String imptMethod) {
		this.code = code;
		this.kor = kor;
		this.imptMethod = imptMethod;
	}

	public int getCode() {
		return code;
	}

	public String getKor() {
		return kor;
	}
	
	public String getImptMethod() {
		return imptMethod;
	}
	
	public static PayWay of(Integer code) {
		PayWay payWay = MAP.get(code);
		return Objects.requireNonNull(payWay);
	}
	
	public static PayWay of(String imptMethod) {
		for(PayWay pay : values()) {
			if(imptMethod.equals(pay.getImptMethod()))
				return pay;
		}
		throw new IllegalArgumentException("지원하지 않는 결제방식 : " + imptMethod);
	}
	
}
