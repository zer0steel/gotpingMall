package com.got.vo.goods;

import java.util.List;

public class StockVO {
	private Integer s_no, g_no;
	private int amount;
	private String combination;
	private int extra_cost;

	@Override
	public String toString() {
		return "StockVO [s_no=" + s_no + ", g_no=" + g_no + ", amount=" + amount + ", combination=" + combination
				+ ", extra_cost=" + extra_cost + "]";
	}

	public Integer getS_no() {
		return s_no;
	}

	public void setS_no(Integer s_no) {
		this.s_no = s_no;
	}

	public Integer getG_no() {
		return g_no;
	}

	public void setG_no(Integer g_no) {
		this.g_no = g_no;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getCombination() {
		return combination;
	}

	public void setCombination(String combination) {
		this.combination = combination;
	}

	public int getExtra_cost() {
		return extra_cost;
	}

	public void setExtra_cost(int extra_cost) {
		this.extra_cost = extra_cost;
	}
}
