package com.got.vo;

import java.security.PrivateKey;
import java.sql.Timestamp;

import com.got.enums.Grade;
import com.got.util.BCrypt;
import com.got.util.RSA;

public class MemberVO {
	private int m_no;
	private String id, name, pwd, email, addr;
	private Timestamp join_date;
	private Grade grade;
	private boolean isLoginSuccess = false;
	
	public int getM_no() {
		return m_no;
	}
	public void setM_no(int m_no) {
		this.m_no = m_no;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public Grade getGrade() {
		return grade;
	}
	public void setGrade(int grade) {
		this.grade = Grade.of(grade);
	}
	public void setEnumGrade(Grade grade) {
		this.grade = grade;
	}
	
	public void setLogin() {
		this.isLoginSuccess = true;
		this.name = null;
		this.pwd = null;
		this.addr = null;
	}
	
	public boolean isLogin() {
		return this.isLoginSuccess;
	}
	
	public boolean isEqualsPwd(String rsaPwd, PrivateKey privateKey) {
		if(this.pwd == null || rsaPwd == null)
			throw new NullPointerException("pwd null is " + (pwd == null) + " | " + "rsaPwd null is " + (rsaPwd == null));
		else if(this.pwd.isEmpty() || rsaPwd.isEmpty())
			throw new IllegalArgumentException("pwd empty is " + pwd.isEmpty() + " | " + "rsaPwd empty is " + rsaPwd.isEmpty());
		String pwd = RSA.decryptRsa(rsaPwd, privateKey);
		return BCrypt.checkpw(pwd, this.pwd);
	}
	public void encyptPwd(PrivateKey privateKey) {
		String pwd = RSA.decryptRsa(this.pwd, privateKey);
		this.pwd = BCrypt.hashpw(pwd,BCrypt.gensalt(12));
	}
	public Timestamp getJoin_date() {
		return join_date;
	}
	public void setJoin_date(Timestamp join_date) {
		this.join_date = join_date;
	}
	
}
