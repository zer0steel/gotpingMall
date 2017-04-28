package com.got.vo;

import java.util.Arrays;
import java.util.List;

public class GoodsOptionVO extends OptionsVO {
	private int g_no;
	private String value, go_stock, extra_cost;
	private List<String> values, go_stocks, extra_costs;
	private boolean required;
	
	public String getExtra_cost() {
		return extra_cost;
	}
	public void setExtra_cost(String extra_cost) {
		this.extra_cost = extra_cost;
		this.setExtra_costs(Arrays.asList(extra_cost.split("/")));
	}
	public List<String> getExtra_costs() {
		return extra_costs;
	}
	public void setExtra_costs(List<String> extra_costs) {
		this.extra_costs = extra_costs;
	}
	public boolean isRequired() {
		return required;
	}
	public void setRequired(boolean required) {
		this.required = required;
	}
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
	@Override
	public String toString() {
		return "GoodsOptionVO [g_no=" + g_no + ", value=" + value + ", go_stock=" + go_stock + ", extra_cost="
				+ extra_cost + ", values=" + values + ", go_stocks=" + go_stocks + ", extra_costs=" + extra_costs
				+ ", required=" + required + "]";
	}
}
