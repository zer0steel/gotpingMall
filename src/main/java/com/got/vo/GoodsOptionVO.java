package com.got.vo;

import java.util.Arrays;
import java.util.List;

public class GoodsOptionVO extends OptionsVO {
	private int g_no;
	private String value, go_stock;
	private List<String> values;
	private List<String> go_stocks;
	
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
		this.setValues(Arrays.asList(value.split("/")));
	}
	public String getGo_stock() {
		return go_stock;
	}
	public void setGo_stock(String go_stock) {
		this.go_stock = go_stock;
		this.setGo_stocks(Arrays.asList(go_stock.split("/")));
	}
	public List<String> getValues() {
		return values;
	}
	public void setValues(List<String> values) {
		this.values = values;
	}
	public List<String> getGo_stocks() {
		return go_stocks;
	}
	public void setGo_stocks(List<String> go_stocks) {
		this.go_stocks = go_stocks;
	}
}
