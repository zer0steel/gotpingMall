package com.got.vo;

import java.sql.Timestamp;
import java.util.Objects;

public class MileageVO {
	private Integer m_no, o_no;
	private Timestamp m_date;
	private int curr_mileage, change_amount;
	private String reason;
	
	public MileageVO() {}
	public MileageVO(Integer m_no) {
		this.m_no = m_no;
	}
	
	public Integer getM_no() {
		return m_no;
	}
	public void setM_no(Integer m_no) {
		this.m_no = m_no;
	}
	public Integer getO_no() {
		return o_no;
	}
	public void setO_no(Integer o_no) {
		this.o_no = o_no;
	}
	public Timestamp getM_date() {
		return m_date;
	}
	public void setM_date(Timestamp m_date) {
		this.m_date = m_date;
	}
	public int getCurr_mileage() {
		return curr_mileage;
	}
	public void setCurr_mileage(int curr_mileage) {
		this.curr_mileage = curr_mileage;
	}
	public int getChange_amount() {
		return change_amount;
	}
	public void setChange_amount(int change_amount) {
		this.change_amount = change_amount;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public MileageVO setupNewMember() {
		Objects.requireNonNull(m_no);
		this.change_amount = 10000000;
		this.reason = "오픈기념 천만포인트 행사";
		return this;
	}
	
}
