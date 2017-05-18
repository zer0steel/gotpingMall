package com.got.vo;

import java.sql.Timestamp;
import java.util.List;

public class OrderVO {
	private Integer o_no, m_no;
	private int total_price;
	private boolean pay;
	private Timestamp o_date;
	private String recipient, message, addr;
	
	private List<OrderDetailVO> details;
	private MemberVO member;
	
	@Override
	public String toString() {
		return "OrderVO [o_no=" + o_no + ", m_no=" + m_no + ", total_price=" + total_price + ", pay=" + pay
				+ ", o_date=" + o_date + ", recipient=" + recipient + ", message=" + message + ", addr=" + addr
				+ ", details=" + details + ", member=" + member + "]";
	}
	public List<OrderDetailVO> getDetails() {
		return details;
	}
	public void setDetails(List<OrderDetailVO> details) {
		this.details = details;
	}
	public MemberVO getMember() {
		return member;
	}
	public void setMember(MemberVO member) {
		this.member = member;
	}
	public Integer getO_no() {
		return o_no;
	}
	public void setO_no(Integer o_no) {
		this.o_no = o_no;
	}
	public Integer getM_no() {
		return m_no;
	}
	public void setM_no(Integer m_no) {
		this.m_no = m_no;
	}
	public int getTotal_price() {
		return total_price;
	}
	public void setTotal_price(int total_price) {
		this.total_price = total_price;
	}
	public boolean isPay() {
		return pay;
	}
	public void setPay(boolean pay) {
		this.pay = pay;
	}
	public Timestamp getO_date() {
		return o_date;
	}
	public void setO_date(Timestamp o_date) {
		this.o_date = o_date;
	}
	public String getRecipient() {
		return recipient;
	}
	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	
}
