package com.got.vo;

import com.got.enums.Category;

public class GoodsVo {
	private int g_no;
	private Category g_category;
	private String g_title;
	private String g_detail;
	private int g_count;
	private int g_price;
	private int g_mileage;
	
	public int getG_no() {
		return g_no;
	}
	public void setG_no(int g_no) {
		this.g_no = g_no;
	}
	public Category getG_category() {
		return g_category;
	}
	public void setG_category(String g_category) {
		this.g_category = Category.of(g_category);
	}
	public String getG_title() {
		return g_title;
	}
	public void setG_title(String g_title) {
		this.g_title = g_title;
	}
	public String getG_detail() {
		return g_detail;
	}
	public void setG_detail(String g_detail) {
		this.g_detail = g_detail;
	}
	public int getG_count() {
		return g_count;
	}
	public void setG_count(int g_count) {
		this.g_count = g_count;
	}
	public int getG_price() {
		return g_price;
	}
	public void setG_price(int g_price) {
		this.g_price = g_price;
	}
	public int getG_mileage() {
		return g_mileage;
	}
	public void setG_mileage(int g_mileage) {
		this.g_mileage = g_mileage;
	}
}
