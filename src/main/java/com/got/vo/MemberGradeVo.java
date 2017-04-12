package com.got.vo;

import java.security.Timestamp;

public class MemberGradeVo {
	
	private int m_no;
	private Timestamp mg_date;
	private int mg_grade;
	private int mg_point;
	private String mg_reason;
	
	public int getM_no() {
		return m_no;
	}
	public void setM_no(int m_no) {
		this.m_no = m_no;
	}
	public Timestamp getMg_date() {
		return mg_date;
	}
	public MemberGradeVo setMg_date(Timestamp mg_date) {
		this.mg_date = mg_date;
		return this;
	}
	public int getMg_grade() {
		return mg_grade;
	}
	public MemberGradeVo setMg_grade(int mg_grade) {
		this.mg_grade = mg_grade;
		return this;
	}
	public int getMg_point() {
		return mg_point;
	}
	public MemberGradeVo setMg_point(int mg_point) {
		this.mg_point = mg_point;
		return this;
	}
	public String getMg_reason() {
		return mg_reason;
	}
	public MemberGradeVo setMg_reason(String mg_reason) {
		this.mg_reason = mg_reason;
		return this;
	}
}
