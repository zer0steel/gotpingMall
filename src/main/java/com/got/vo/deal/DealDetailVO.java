package com.got.vo.deal;

import java.math.BigDecimal;

import com.got.vo.goods.StockVO;

public class DealDetailVO {
	private Integer dd_no, d_no;
	private int change_amount, remain_stock;
	private BigDecimal unit_price;
	private StockVO stock;
	
	@Override
	public String toString() {
		return "DealDetailVO [dd_no=" + dd_no + ", d_no=" + d_no + ", change_amount=" + change_amount
				+ ", remain_stock=" + remain_stock + ", unit_price=" + unit_price + ", stock=" + stock + "]";
	}
	public Integer getDd_no() {
		return dd_no;
	}
	public void setDd_no(Integer cd_no) {
		this.dd_no = cd_no;
	}
	public Integer getD_no() {
		return d_no;
	}
	public void setD_no(Integer d_no) {
		this.d_no = d_no;
	}
	public int getChange_amount() {
		return change_amount;
	}
	public void setChange_amount(int change_amount) {
		this.change_amount = change_amount;
	}
	public int getRemain_stock() {
		return remain_stock;
	}
	public void setRemain_stock(int remain_stock) {
		this.remain_stock = remain_stock;
	}
	public void setup(DealVO dealVO) {
		this.d_no = dealVO.getD_no();
		if(dealVO.getEnumCategory().isMinusStockCategory())
			this.change_amount = Math.negateExact(this.change_amount);
	}
	public BigDecimal getUnit_price() {
		return unit_price;
	}
	public void setUnit_price(BigDecimal unit_price) {
		this.unit_price = unit_price;
	}
	public StockVO getStock() {
		return stock;
	}
	public void setStock(StockVO stock) {
		this.stock = stock;
	}
}
