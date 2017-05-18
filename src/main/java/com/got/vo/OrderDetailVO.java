package com.got.vo;

import com.got.vo.goods.GoodsVO;
import com.got.vo.goods.OptionStockVO;

public class OrderDetailVO {
	private Integer od_no, o_no, g_no;
	private int amount, total_price;
	private GoodsVO goods;
	private OptionStockVO optionStock;
	
	@Override
	public String toString() {
		return "OrderDetailVO [od_no=" + od_no + ", o_no=" + o_no + ", g_no=" + g_no + ", amount=" + amount
				+ ", total_price=" + total_price + ", goods=" + goods + ", optionStock=" + optionStock + "]";
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
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
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

	public Integer getG_no() {
		return g_no;
	}

	public void setG_no(Integer g_no) {
		this.g_no = g_no;
	}
	public OptionStockVO getOptionStock() {
		return optionStock;
	}
	public void setOptionStock(OptionStockVO optionStock) {
		this.optionStock = optionStock;
	}
}
