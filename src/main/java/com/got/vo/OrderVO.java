package com.got.vo;

import java.sql.Timestamp;
import java.util.List;

import com.got.enums.ManagementCategory;

public class OrderVO {
	private Integer o_no;
	private ManagementCategory category;
	private int total_price, change_amount, remain_stock;
	private Timestamp regdate;
	
	private List<OrderDetailVO> details;

	public Integer getO_no() {
		return o_no;
	}

	public void setO_no(Integer o_no) {
		this.o_no = o_no;
	}

	public ManagementCategory getCategory() {
		return category;
	}

	public void setCategory(ManagementCategory category) {
		this.category = category;
	}

	public int getTotal_price() {
		return total_price;
	}

	public void setTotal_price(int total_price) {
		this.total_price = total_price;
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

	public Timestamp getRegdate() {
		return regdate;
	}

	public void setRegdate(Timestamp regdate) {
		this.regdate = regdate;
	}

	public List<OrderDetailVO> getDetails() {
		return details;
	}

	public void setDetails(List<OrderDetailVO> details) {
		this.details = details;
	}

	@Override
	public String toString() {
		return "OrderVO [o_no=" + o_no + ", category=" + category + ", total_price=" + total_price + ", change_amount="
				+ change_amount + ", remain_stock=" + remain_stock + ", regdate=" + regdate + ", details=" + details
				+ "]";
	}
	
}
