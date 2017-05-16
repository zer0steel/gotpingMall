package com.got.vo;

public class OrderDetailVO {
	private Integer od_no, g_no, o_no;
	private int amount, total_price;
	
	public Integer getOd_no() {
		return od_no;
	}
	public void setOd_no(Integer od_no) {
		this.od_no = od_no;
	}
	public Integer getG_no() {
		return g_no;
	}
	public void setG_no(Integer g_no) {
		this.g_no = g_no;
	}
	public Integer getO_no() {
		return o_no;
	}
	public void setO_no(Integer o_no) {
		this.o_no = o_no;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public int getTotal_price() {
		return total_price;
	}
	public void setTotal_price(int total_price) {
		this.total_price = total_price;
	}
}
