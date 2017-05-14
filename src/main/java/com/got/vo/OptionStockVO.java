package com.got.vo;

public class OptionStockVO {
	private Integer os_no, g_no;
	private int os_stock;
	private String combination;
	private int os_extra_cost;
	@Override
	public String toString() {
		return "OptionStockVO [os_no=" + os_no + ", g_no=" + g_no + ", os_stock=" + os_stock + ", combination="
				+ combination + ", os_extra_cost=" + os_extra_cost + "]";
	}
	
	public Integer getOs_no() {
		return os_no;
	}
	public void setOs_no(Integer os_no) {
		this.os_no = os_no;
	}
	public int getOs_stock() {
		return os_stock;
	}
	public void setOs_stock(int os_stock) {
		this.os_stock = os_stock;
	}
	public Integer getG_no() {
		return g_no;
	}
	public void setG_no(Integer g_no) {
		this.g_no = g_no;
	}
	public String getCombination() {
		return combination;
	}
	public void setCombination(String combination) {
		this.combination = combination;
	}
	public int getOs_extra_cost() {
		return os_extra_cost;
	}
	public void setOs_extra_cost(int os_extra_cost) {
		this.os_extra_cost = os_extra_cost;
	}
}
