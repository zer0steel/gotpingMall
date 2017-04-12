package com.got.vo;

import java.security.Timestamp;

public class MemberGradeVo {
	
	private int m_no;
	private Timestamp mg_date;
	private int grade;
	private int point;
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
	public int getGrade() {
		return grade;
	}
	public void setGrade(int grade) {
		this.grade = grade;
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
}
