package com.got.vo;

import java.util.List;

import com.got.enums.Grade;

/**
 * @author Jang
 *
 */
public class MemberVo {
	private int m_no;
	private String m_id;
	private String m_name;
	private String m_pwd;
	private String m_email;
	private String m_addr;
	
	private Grade grade;
	private List<MemberGradeVo> mg_history;
	
	public int getM_no() {
		return m_no;
	}
	public void setM_no(int m_no) {
		this.m_no = m_no;
	}
	public String getM_id() {
		return m_id;
	}
	public void setM_id(String m_id) {
		this.m_id = m_id;
	}
	public String getM_name() {
		return m_name;
	}
	public void setM_name(String m_name) {
		this.m_name = m_name;
	}
	public String getM_pwd() {
		return m_pwd;
	}
	public void setM_pwd(String m_pwd) {
		this.m_pwd = m_pwd;
	}
	public String getM_email() {
		return m_email;
	}
	public void setM_email(String m_email) {
		this.m_email = m_email;
	}
	public String getM_addr() {
		return m_addr;
	}
	public void setM_addr(String m_addr) {
		this.m_addr = m_addr;
	}
	
	public void addHistory(int point, String reason) {
		MemberGradeVo mg = new MemberGradeVo();
		mg.setMg_point(point).setMg_reason(reason);
		mg_history.add(mg);
	}
	
	public void addHistory(MemberGradeVo mg) {
		mg_history.add(mg);
	}
	
	public Grade getGrade() {
		return grade;
	}
	public void setGrade(Grade grade) {
		this.grade = grade;
	}
	public void setM_grade(int grade) {
		this.grade = Grade.of(grade);
	}
	
	public boolean isSelected() {
		if(this.m_id == null)
			return false;
		else if(this.m_id.equals(""))
			return false;
		else
			return true;
	}
}
