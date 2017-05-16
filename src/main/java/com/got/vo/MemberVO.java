package com.got.vo;

import java.security.PrivateKey;
import java.sql.Timestamp;
import java.util.Objects;

import com.got.enums.Grade;
import com.got.util.BCrypt;
import com.got.util.RSA;

public class MemberVO {
	private Integer m_no;
	private String id, name, pwd, email, addr;
	private Timestamp join_date;
	private Grade grade;
	private boolean loginSuccess = false;
	
	public Integer getM_no() {
		return m_no;
	}
	public void setM_no(Integer m_no) {
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
	public int getGrade() {
		return grade.getCode();
	}
	public Grade getEnumGrade() {
		return grade;
	}
	public void setGrade(int grade) {
		this.grade = Grade.of(grade);
	}
	public void setEnumGrade(Grade grade) {
		this.grade = grade;
	}
	public Timestamp getJoin_date() {
		return join_date;
	}
	public void setJoin_date(Timestamp join_date) {
		this.join_date = join_date;
	}
	
	public void setLogin() {
		this.loginSuccess = true;
		this.name = null;
		this.pwd = null;
		this.addr = null;
	}
	
	public boolean isLoginSuccess() {
		return this.loginSuccess;
	}
	
	public boolean isEqualsPwd(String rsaPwd, PrivateKey privateKey) {
		Objects.requireNonNull(rsaPwd);
		Objects.requireNonNull(privateKey);
		if(this.pwd.isEmpty() || rsaPwd.isEmpty())
			throw new IllegalArgumentException("pwd empty is " + pwd.isEmpty() + " | " + "rsaPwd empty is " + rsaPwd.isEmpty());
		String pwd = RSA.decryptRsa(rsaPwd, privateKey);
		return BCrypt.checkpw(pwd, this.pwd);
	}
	public void encyptPwd(PrivateKey privateKey) {
		Objects.requireNonNull(privateKey);
		String pwd = RSA.decryptRsa(this.pwd, privateKey);
		this.pwd = BCrypt.hashpw(pwd,BCrypt.gensalt(12));
	}
	
}
