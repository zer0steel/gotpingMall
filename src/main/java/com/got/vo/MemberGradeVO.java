package com.got.vo;

import java.security.Timestamp;

public class MemberGradeVO {
	
	private int m_no, mg_grade, point;
	private Timestamp mg_date;
	private String reason;
	
	public int getM_no() {
		return m_no;
	}
	public void setM_no(int m_no) {
		this.m_no = m_no;
	}
	public Timestamp getMg_date() {
		return mg_date;
	}
	public void setMg_date(Timestamp mg_date) {
		this.mg_date = mg_date;
	}
	public int getPoint() {
		return point;
	}
	public void setPoint(int point) {
		this.point = point;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public int getMg_grade() {
		return mg_grade;
	}
	public void setMg_grade(int mg_grade) {
		this.mg_grade = mg_grade;
	}
}
