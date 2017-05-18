package com.got.vo;

import java.security.PrivateKey;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;

import com.got.enums.Grade;
import com.got.util.BCrypt;
import com.got.util.RSA;

public class MemberVO {
	private Integer m_no;
	private String id, name, pwd, email;
	private Timestamp join_date;
	private Grade grade;
	private boolean loginSuccess = false;
	
	private Address addr;
	private static final String SEPARATOR_CHAR = "/ ";
	
	@Override
	public String toString() {
		return "MemberVO [m_no=" + m_no + ", id=" + id + ", name=" + name + ", email=" + email
				+ ", join_date=" + join_date + ", grade=" + grade + ", loginSuccess=" + loginSuccess + ", addr=" + addr
				+ "]";
	}
	
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
		String[] str = new String[3];
		str[0] = addr.getPostCode();
		str[1] = addr.getBase();
		str[2] = addr.getExtra();
		return String.join(SEPARATOR_CHAR, str);
	}
	public void setAddr(String addr) {
		Iterator<String> iter = Arrays.asList(addr.split(SEPARATOR_CHAR)).iterator();
		this.addr = new Address();
		if(iter.hasNext())
			this.addr.setPostCode(iter.next());
		if(iter.hasNext())
			this.addr.setBase(iter.next());
		if(iter.hasNext())
			this.addr.setExtra(iter.next());
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
	
	public Address getAddress() {
		return this.addr;
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
	
	public class Address {
		String postCode;
		String base;
		String extra;
		
		@Override
		public String toString() {
			return "Address [postCode=" + postCode + ", base=" + base + ", extra=" + extra + "]";
		}

		public String getPostCode() {
			return postCode;
		}

		public void setPostCode(String postCode) {
			this.postCode = postCode;
		}

		public String getBase() {
			return base;
		}

		public void setBase(String base) {
			this.base = base;
		}

		public String getExtra() {
			return extra;
		}

		public void setExtra(String extra) {
			this.extra = extra;
		}
		
	}
}
