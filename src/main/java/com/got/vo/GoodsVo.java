package com.got.vo;

import java.util.List;

import com.got.enums.GoodsStatus;

public class GoodsVO extends CategoryVO {
	private int g_no, stock, purchase_price, sell_price, discount_rate, saving_mileage;
	private String name, detail;
	private GoodsStatus status;
	private List<ShippingReceivingVO> history;
	
	public int getG_no() {
		return g_no;
	}
	public void setG_no(int g_no) {
		this.g_no = g_no;
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
	public int getStatus_code() {
		return status.getCode();
	}
	public void setStatus_code(int status) {
		this.status = GoodsStatus.of(status);
	}
	public GoodsStatus getStatus() {
		return status;
	}
	public void setStatus(GoodsStatus status) {
		this.status = status;
	}
	public List<ShippingReceivingVO> getHistory() {
		return history;
	}
	public void setHistory(List<ShippingReceivingVO> history) {
		this.history = history;
	}
}
