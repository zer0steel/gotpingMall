package com.got.vo.goods;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class StockVO {
	private Integer s_no, g_no;
	private int amount;
	private String combination;
	private int extra_cost;
	private GoodsVO goods;

	@Override
	public String toString() {
		return "StockVO [s_no=" + s_no + ", g_no=" + g_no + ", amount=" + amount + ", combination=" + combination
				+ ", extra_cost=" + extra_cost + ", goods=" + goods + "]";
	}

	public Integer getS_no() {
		return s_no;
	}

	public void setS_no(Integer s_no) {
		this.s_no = s_no;
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

	public GoodsVO getGoods() {
		return goods;
	}

	public void setGoods(GoodsVO goods) {
		this.goods = goods;
	}
	
	public Integer getG_no() {
		return this.g_no;
	}
	
	public void setG_no(Integer g_no) {
		this.g_no = g_no;
	}
	
	@JsonIgnore
	public int getRealPrice() {
		return Objects.nonNull(this.goods) ?
				this.goods.getRealPrice() + this.extra_cost : 0;
	}
}
