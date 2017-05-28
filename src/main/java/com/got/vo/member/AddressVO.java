package com.got.vo.member;

import java.util.Arrays;
import java.util.Iterator;

public class AddressVO {
	private String postCode;
	private String base;
	private String extra;
	
	@Override
	public String toString() {
		return "Address [postCode=" + postCode + ", base=" + base + ", extra=" + extra + "]";
	}

	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	public String getBase() {
		return base;
	}

	public void setBase(String base) {
		this.base = base;
	}

	public String getExtra() {
		return extra;
	}

	public void setExtra(String extra) {
		this.extra = extra;
	}
	
	private static final String SEPARATOR_CHAR = "/ ";
	public String getAddr() {
		String[] str = new String[3];
		str[0] = this.postCode;
		str[1] = this.base;
		str[2] = this.extra;
		return String.join(SEPARATOR_CHAR, str);
	}
	
	public void setAddr(String addr) {
		Iterator<String> iter = Arrays.asList(addr.split(SEPARATOR_CHAR)).iterator();
		if(iter.hasNext())
			this.postCode = iter.next();
		if(iter.hasNext())
			this.base = iter.next();
		if(iter.hasNext())
			this.extra = iter.next();
	}
}
