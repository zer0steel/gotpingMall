package com.got.vo.deal;

import java.util.Objects;

import com.got.vo.member.AddressVO;

public class OrderVO extends DealVO {
	private Integer m_no;
	private String recipient, message;
	private AddressVO addr;
	@Override
	public String toString() {
		return "OrderVO [m_no=" + m_no + ", recipient=" + recipient + ", message=" + message + ", addr=" + addr
				+ ", toString()=" + super.toString() + "]";
	}
	public Integer getM_no() {
		return m_no;
	}
	public void setM_no(Integer m_no) {
		this.m_no = m_no;
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
		if(Objects.nonNull(this.addr))
			return this.addr.getAddr();
		return new String();
	}
	public void setAddr(String addr) {
		this.addr = new AddressVO();
		this.addr.setAddr(addr);
	}
	public AddressVO getAddress() {
		return this.addr;
	}
}
