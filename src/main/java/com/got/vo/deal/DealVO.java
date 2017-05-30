package com.got.vo.deal;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

import com.got.enums.DealCategory;

public class DealVO {
	private Integer d_no;
	private DealCategory category;
	private String detail;
	private BigDecimal total_price;
	private int total_change_amount;
	private Timestamp regdate;
	private List<DealDetailVO> details;
	
	@Override
	public String toString() {
		return "DealVO [d_no=" + d_no + ", category=" + category + ", detail=" + detail + ", total_price=" + total_price
				+ ", total_change_amount=" + total_change_amount + ", regdate=" + regdate + ", details=" + details
				+ "]";
	}
	public List<DealDetailVO> getDetails() {
		return details;
	}
	public void setDetails(List<DealDetailVO> details) {
		this.details = details;
	}
	public Integer getD_no() {
		return d_no;
	}
	public void setD_no(Integer d_no) {
		this.d_no = d_no;
	}
	public int getCategory() {
		return category.getCode();
	}

	public DealCategory getEnumCategory() {
		return category;
	}

	public void setCategory(int code) {
		this.category = DealCategory.of(code);
	}

	public void setEnumCategory(DealCategory category) {
		this.category = category;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public BigDecimal getTotal_price() {
		return total_price;
	}
	public void setTotal_price(BigDecimal total_price) {
		this.total_price = total_price;
	}
	public Timestamp getRegdate() {
		return regdate;
	}
	public void setRegdate(Timestamp regdate) {
		this.regdate = regdate;
	}
	public int getTotal_change_amount() {
		return total_change_amount;
	}
	public void setTotal_change_amount(int total_change_amount) {
		this.total_change_amount = total_change_amount;
	}
}
