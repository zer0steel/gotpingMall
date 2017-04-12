package com.got.vo;

public class GoodsVo {
	private int g_no;
	private String category;
	private String name;
	private String detail;
	private int stock;
	private int purchase_price;
	private int sell_price;
	private int discount_rate;
	private int saving_mileage;
	private boolean show;
	
	public int getG_no() {
		return g_no;
	}
	public void setG_no(int g_no) {
		this.g_no = g_no;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public int getPurchase_price() {
		return purchase_price;
	}
	public void setPurchase_price(int purchase_price) {
		this.purchase_price = purchase_price;
	}
	public int getSell_price() {
		return sell_price;
	}
	public void setSell_price(int sell_price) {
		this.sell_price = sell_price;
	}
	public int getDiscount_rate() {
		return discount_rate;
	}
	public void setDiscount_rate(int discount_rate) {
		this.discount_rate = discount_rate;
	}
	public int getSaving_mileage() {
		return saving_mileage;
	}
	public void setSaving_mileage(int saving_mileage) {
		this.saving_mileage = saving_mileage;
	}
	public boolean isShow() {
		return show;
	}
	public void setShow(boolean show) {
		this.show = show;
	}
	@Override
	public String toString() {
		return "GoodsVo [g_no=" + g_no + ", category=" + category + ", name=" + name + ", detail=" + detail + ", stock="
				+ stock + ", purchase_price=" + purchase_price + ", sell_price=" + sell_price + ", discount_rate="
				+ discount_rate + ", saving_mileage=" + saving_mileage + ", show=" + show + "]";
	}
	
}
