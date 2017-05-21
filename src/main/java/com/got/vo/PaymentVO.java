package com.got.vo;

import java.sql.Timestamp;

public class PaymentVO {

	private String p_no, status, p_way, p_way_detail;
	private Integer o_no;
	private int price, use_mileage;
	private Timestamp regdate;
	
	public String getP_no() {
		return p_no;
	}
	public void setP_no(String p_no) {
		this.p_no = p_no;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getP_way() {
		return p_way;
	}
	public void setP_way(String p_way) {
		this.p_way = p_way;
	}
	public String getP_way_detail() {
		return p_way_detail;
	}
	public void setP_way_detail(String p_way_detail) {
		this.p_way_detail = p_way_detail;
	}
	public Integer getO_no() {
		return o_no;
	}
	public void setO_no(Integer o_no) {
		this.o_no = o_no;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getUse_mileage() {
		return use_mileage;
	}
	public void setUse_mileage(int use_mileage) {
		this.use_mileage = use_mileage;
	}
	public Timestamp getRegdate() {
		return regdate;
	}
	public void setRegdate(Timestamp regdate) {
		this.regdate = regdate;
	}
	
}
