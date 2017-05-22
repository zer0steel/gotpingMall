package com.got.vo;

import java.sql.Timestamp;
import java.util.List;

import com.got.enums.ManagementCategory;

public class GoodsManagmentVO {
	private Integer gc_no;
	private ManagementCategory category;
	private String detail;
	private int change_amount, total_price;
	private Timestamp regdate;
	private List<ControlDetailVO> details;
	
	@Override
	public String toString() {
		return "GoodsManagmentVO [gc_no=" + gc_no + ", category=" + category + ", detail=" + detail + ", change_amount="
				+ change_amount + ", total_price=" + total_price + ", regdate=" + regdate + ", details=" + details
				+ "]";
	}
	public List<ControlDetailVO> getDetails() {
		return details;
	}
	public void setDetails(List<ControlDetailVO> details) {
		this.details = details;
	}
	public Integer getGc_no() {
		return gc_no;
	}
	public void setGc_no(Integer gc_no) {
		this.gc_no = gc_no;
	}
	public int getCategory() {
		return category.getCode();
	}

	public ManagementCategory getEnumCategory() {
		return category;
	}

	public void setCategory(int code) {
		this.category = ManagementCategory.of(code);
	}

	public void setEnumCategory(ManagementCategory category) {
		this.category = category;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public int getChange_amount() {
		return change_amount;
	}
	public void setChange_amount(int change_amount) {
		this.change_amount = change_amount;
	}
	public int getTotal_price() {
		return total_price;
	}
	public void setTotal_price(int total_price) {
		this.total_price = total_price;
	}
	public Timestamp getRegdate() {
		return regdate;
	}
	public void setRegdate(Timestamp regdate) {
		this.regdate = regdate;
	}
}
