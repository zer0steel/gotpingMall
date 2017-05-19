package com.got.vo;

import java.security.Timestamp;
import java.util.Objects;

public class MemberGradeVO {
	private Integer m_no;
	private int mg_grade, point;
	private Timestamp mg_date;
	private String reason;
	
	public MemberGradeVO() {}
	public MemberGradeVO(Integer m_no) {
		this.m_no = m_no;
	}
	
	public Integer getM_no() {
		return m_no;
	}
	public void setM_no(Integer m_no) {
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
	public MemberGradeVO setupNewMember() {
		Objects.requireNonNull(m_no);
		this.reason = "신규 가입";
		return this;
	}
}
