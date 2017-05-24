package com.got.vo;

import com.got.vo.goods.GoodsVO;
import com.got.vo.goods.StockVO;

public class OrderDetailVO extends StockVO {
	private Integer od_no, o_no;
	private int change_amount, total_price;
	private GoodsVO goods;
	
	@Override
	public String toString() {
		return "OrderDetailVO [od_no=" + od_no + ", o_no=" + o_no + ", change_amount=" + change_amount
				+ ", total_price=" + total_price + ", goods=" + goods + ", toString()=" + super.toString() + "]";
	}
	
	
	public Integer getOd_no() {
		return od_no;
	}
	
	public void setOd_no(Integer od_no) {
		this.od_no = od_no;
	}
	
	public Integer getO_no() {
		return o_no;
	}
	
	public void setO_no(Integer o_no) {
		this.o_no = o_no;
	}
	
	public int getTotal_price() {
		return total_price;
	}
	
	public void setTotal_price(int total_price) {
		this.total_price = total_price;
	}

	public GoodsVO getGoods() {
		return goods;
	}

	public void setGoods(GoodsVO goods) {
		this.goods = goods;
	}

	public int getChange_amount() {
		return change_amount;
	}

	public void setChange_amount(int change_amount) {
		this.change_amount = change_amount;
	}

	public void setStock(StockVO s) {
		
	}
	
}
