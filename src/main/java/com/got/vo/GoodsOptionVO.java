package com.got.vo;

import java.util.ArrayList;
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
		this.setValues(Arrays.asList(value.split(",")));
		checkStockString();
	}
	public String getGo_stock() {
		return go_stock;
	}
	public void setGo_stock(String go_stock) {
		this.go_stock = go_stock;
		this.setGo_stocks(Arrays.asList(go_stock.split(",")));
		checkStockString();
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
	@Override
	public String toString() {
		return super.toString() + "\nGoodsOptionVO [g_no=" + g_no + ", value=" + value + ", go_stock=" + go_stock + ", values=" + values
				+ ", go_stocks=" + go_stocks + "]";
	}
	
	private void checkStockString() {
		if("0".equals(this.go_stock) && (go_stocks.size() != values.size()) ) {
			String[] str = new String[values.size()];
			Arrays.fill(str, "0");
			go_stocks = Arrays.asList(str);
		}
	}
}
