package com.got.vo.deal;

import java.math.BigDecimal;
import java.util.Objects;

import com.got.vo.member.AddressVO;

public class OrderVO extends DealVO {
	private Integer m_no;
	private String recipient, message, buyer, buyer_email;
	private AddressVO addr;
	@Override
	public String toString() {
		return "OrderVO [m_no=" + m_no + ", recipient=" + recipient + ", message=" + message + ", buyer=" + buyer
				+ ", buyer_email=" + buyer_email + ", addr=" + addr
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
	public String getBuyer() {
		return buyer;
	}
	public void setBuyer(String buyer) {
		this.buyer = buyer;
	}
	public String getBuyer_email() {
		return buyer_email;
	}
	public void setBuyer_email(String buyer_email) {
		this.buyer_email = buyer_email;
	}
	public void setAddr(AddressVO addr) {
		this.addr = addr;
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
	public void setAddress(AddressVO address) {
		this.addr = address;
	}
	public AddressVO getAddress() {
		return this.addr;
	}
	public void setDealVO(DealVO d) {
		super.setTotal_price(d.getTotal_price());
	}
	
	public void addTotalPrice(BigDecimal price) {
		super.setTotal_price(super.getTotal_price().add(price));
	}
	public void addTotalAmount(int changeAmount) {
		super.setTotal_change_amount(super.getTotal_change_amount() + changeAmount);
	}
}
