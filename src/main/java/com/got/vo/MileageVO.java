package com.got.vo;

import java.sql.Timestamp;
import java.util.Objects;

public class MileageVO {
	private Integer m_no;
	private Timestamp m_date;
	private int curr_mileage, change_amount;
	private String reason, p_no;
	
	@Override
	public String toString() {
		return "MileageVO [m_no=" + m_no + ", m_date=" + m_date + ", curr_mileage=" + curr_mileage + ", change_amount="
				+ change_amount + ", reason=" + reason + ", p_no=" + p_no + "]";
	}
	
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

	public String getP_no() {
		return p_no;
	}

	public void setP_no(String p_no) {
		this.p_no = p_no;
	}
	
	public MileageVO setupNewMember() {
		Objects.requireNonNull(m_no);
		this.change_amount = 10000000;
		this.reason = "오픈기념 천만포인트 행사";
		return this;
	}
}
