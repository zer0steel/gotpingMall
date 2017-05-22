package com.got.vo.goods;

public class ControlDetailVO extends StockVO {
	private Integer cd_no, gc_no;
	private int change_amount, remain_stock, price;
	
	@Override
	public String toString() {
		return "ControlDetailVO [cd_no=" + cd_no + ", gc_no=" + gc_no + ", change_amount=" + change_amount
				+ ", remain_stock=" + remain_stock + "]";
	}
	public Integer getCd_no() {
		return cd_no;
	}
	public void setCd_no(Integer cd_no) {
		this.cd_no = cd_no;
	}
	public Integer getGc_no() {
		return gc_no;
	}
	public void setGc_no(Integer gc_no) {
		this.gc_no = gc_no;
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
	public void setup(GoodsManagmentVO managmentVO) {
		this.gc_no = managmentVO.getGc_no();
		if(managmentVO.getEnumCategory().isMinusStockCategory())
			this.change_amount = Math.negateExact(this.change_amount);
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
}
