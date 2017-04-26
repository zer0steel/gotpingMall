package com.got.vo;

public class OptionsVO {
	private int o_no, c_no;
	private String o_name;
	public int getO_no() {
		return o_no;
	}
	public void setO_no(int o_no) {
		this.o_no = o_no;
	}
	public int getC_no() {
		return c_no;
	}
	public void setC_no(int c_no) {
		this.c_no = c_no;
	}
	public String getO_name() {
		return o_name;
	}
	public void setO_name(String o_name) {
		this.o_name = o_name;
	}
	@Override
	public String toString() {
		return "\nOptionVO [o_no=" + o_no + ", c_no=" + c_no + ", o_name=" + o_name + "]";
	}
}
