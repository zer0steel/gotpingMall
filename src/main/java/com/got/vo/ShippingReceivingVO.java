package com.got.vo;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.got.enums.HistoryCategory;

public class ShippingReceivingVO {
	private int sr_no, g_no, amount, price;
	private String detail;
	@JsonFormat(shape = Shape.STRING, pattern = "m월 dd일 hh시", timezone = "Asia/Seoul")
	private Timestamp regdate;
	private HistoryCategory category;
	
	public int getSr_no() {
		return sr_no;
	}
	public void setSr_no(int sr_no) {
		this.sr_no = sr_no;
	}
	public int getG_no() {
		return g_no;
	}
	public void setG_no(int g_no) {
		this.g_no = g_no;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
		if(this.category != null)
			modifyAmount();
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public Timestamp getRegdate() {
		return regdate;
	}
	public void setRegdate(Timestamp regdate) {
		this.regdate = regdate;
	}
	
	public int getCategory() {
		return category.getCode();
	}
	public HistoryCategory getEnumCategory() {
		return category;
	}
	public void setCategory(int code) {
		this.category = HistoryCategory.of(code);
		modifyAmount();
	}
	public void setEnumCategory(HistoryCategory category) {
		this.category = category;
		modifyAmount();
	}
	@Override
	public String toString() {
		return "ShippingReceivingVO [sr_no=" + sr_no + ", g_no=" + g_no + ", amount=" + amount + ", price=" + price
				+ ", detail=" + detail + ", regdate=" + regdate + ", category=" + category + "]";
	}
	
	private void modifyAmount() {
		if( this.category.isMinusStockCategory() ) 
			this.amount = Math.negateExact(this.amount);
	}
}
