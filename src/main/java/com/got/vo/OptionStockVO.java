package com.got.vo;

public class OptionStockVO {
	private int os_no, os_stock, g_no;
	private String combination;
	public int getOs_no() {
		return os_no;
	}
	public void setOs_no(int os_no) {
		this.os_no = os_no;
	}
	public int getOs_stock() {
		return os_stock;
	}
	public void setOs_stock(int os_stock) {
		this.os_stock = os_stock;
	}
	public int getG_no() {
		return g_no;
	}
	public void setG_no(int g_no) {
		this.g_no = g_no;
	}
	public String getCombination() {
		return combination;
	}
	public void setCombination(String combination) {
		this.combination = combination;
	}
	@Override
	public String toString() {
		return "OptionStockVO [os_no=" + os_no + ", os_stock=" + os_stock + ", g_no=" + g_no + ", combination="
				+ combination + "]";
	}
}
