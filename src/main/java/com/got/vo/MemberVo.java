package com.got.vo;

import java.security.PrivateKey;
import java.util.List;

import com.got.enums.Grade;
import com.got.util.BCrypt;
import com.got.util.RSA;

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
	
	private boolean isLoginSuccess = false;
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
	
	/*직접 제작 메서드*/
	public Grade getM_grade() {
		return grade;
	}
	public void setGrade(Grade grade) {
		this.grade = grade;
	}
	public void setM_grade(int grade) {
		this.grade = Grade.of(grade);
	}
	
	public void setLogin() {
		this.isLoginSuccess = true;
		this.m_name = null;
		this.m_pwd = null;
		this.m_addr = null;
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
		if(this.m_pwd == null || rsaPwd == null)
			throw new NullPointerException("m_pwd null is " + (m_pwd == null) + " | " + "rsaPwd null is " + (rsaPwd == null));
		else if(this.m_pwd.isEmpty() || rsaPwd.isEmpty())
			throw new IllegalArgumentException("m_pwd empty is " + m_pwd.isEmpty() + " | " + "rsaPwd empty is " + rsaPwd.isEmpty());
		String pwd = RSA.decryptRsa(rsaPwd, privateKey);
		return BCrypt.checkpw(pwd, this.m_pwd);
	}
}
