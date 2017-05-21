package com.got.vo.goods;

public class StockVO {
	private Integer s_no, g_no;
	private int amount;
	private String combination;
	private int extra_cost;
	
	@Override
	public String toString() {
		return "OptionStockVO [os_no=" + s_no + ", g_no=" + g_no + ", os_stock=" + amount + ", combination="
				+ combination + ", os_extra_cost=" + extra_cost + "]";
	}
	
	public Integer getOs_no() {
		return s_no;
	}
	public void setOs_no(Integer os_no) {
		this.s_no = os_no;
	}
	public int getOs_stock() {
		return amount;
	}
	public void setOs_stock(int os_stock) {
		this.amount = os_stock;
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
		return extra_cost;
	}
	public void setOs_extra_cost(int os_extra_cost) {
		this.extra_cost = os_extra_cost;
	}
}
