package com.got.vo;

public class GoodsOptionVO extends OptionsVO {
	private int g_no;
	private String value, go_stock;
	public int getG_no() {
		return g_no;
	}
	public void setG_no(int g_no) {
		this.g_no = g_no;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getGo_stock() {
		return go_stock;
	}
	public void setGo_stock(String go_stock) {
		this.go_stock = go_stock;
	}
}
