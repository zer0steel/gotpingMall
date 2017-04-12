package com.got.vo;

import java.security.PrivateKey;

import com.got.enums.Grade;
import com.got.util.BCrypt;
import com.got.util.RSA;

/**
 * @author Jang
 *
 */
public class MemberVo {
	private int m_no;
	private String id;
	private String name;
	private String pwd;
	private String email;
	private String addr;
	
	private boolean isLoginSuccess = false;
	private Grade grade;
	
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
	/*직접 제작 메서드*/
	public Grade getGrade() {
		return grade;
	}
	public void setGrade(int grade) {
		this.grade = Grade.of(grade);
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
	
	/**
	 * rsa로 암호화된 패스워드를 복호화한뒤 BCrypt로 암호화 되어있는 기존의 패스워드와 비교한다.
	 * @param rsaPwd rsa로 암호화된 패스워드
	 * @param privateKey rsa privateKey
	 * @return 둘다 같을경우 true
	 */
	public boolean isEqualsPwd(String rsaPwd, PrivateKey privateKey) {
		if(this.pwd == null || rsaPwd == null)
			throw new NullPointerException("pwd null is " + (pwd == null) + " | " + "rsaPwd null is " + (rsaPwd == null));
		else if(this.pwd.isEmpty() || rsaPwd.isEmpty())
			throw new IllegalArgumentException("pwd empty is " + pwd.isEmpty() + " | " + "rsaPwd empty is " + rsaPwd.isEmpty());
		String pwd = RSA.decryptRsa(rsaPwd, privateKey);
		return BCrypt.checkpw(pwd, this.pwd);
	}
}
