package com.got.vo;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Objects;

import com.got.enums.MileageCategory;
import com.got.vo.deal.PaymentVO;

public class MileageVO {
	private Integer m_no, p_no, m_pk;
	private Timestamp regdate;
	private BigDecimal curr_mileage, change_amount;
	private String reason;
	private MileageCategory category;
	
	@Override
	public String toString() {
		return "MileageVO [m_no=" + m_no + ", p_no=" + p_no + ", m_pk=" + m_pk + ", regdate=" + regdate
				+ ", curr_mileage=" + curr_mileage + ", change_amount=" + change_amount + ", reason=" + reason
				+ ", category=" + category + "]";
	}
	
	public MileageVO() {}
	public MileageVO(Integer m_no) {
		this.m_no = m_no;
	}
	public Integer getM_pk() {
		return m_pk;
	}
	public void setM_pk(Integer m_pk) {
		this.m_pk = m_pk;
	}
	public Timestamp getRegdate() {
		return regdate;
	}
	public void setRegdate(Timestamp regdate) {
		this.regdate = regdate;
	}
	public Integer getM_no() {
		return m_no;
	}
	public void setM_no(Integer m_no) {
		this.m_no = m_no;
	}
	public BigDecimal getCurr_mileage() {
		return curr_mileage;
	}
	public void setCurr_mileage(BigDecimal curr_mileage) {
		this.curr_mileage = curr_mileage;
	}
	public BigDecimal getChange_amount() {
		if(Objects.isNull(this.category))
			return this.change_amount;
		
		return this.category.isMinusCategory() ? 
			this.change_amount.negate() :
			this.change_amount;
	}
	public void setChange_amount(BigDecimal change_amount) {
		this.change_amount = change_amount;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public Integer getP_no() {
		return p_no;
	}
	public void setP_no(Integer p_no) {
		this.p_no = p_no;
	}
	
	public MileageCategory getCategory() {
		return category;
	}

	public void setCategory(int code) {
		this.category = MileageCategory.of(code);
	}
	
	public void setEnumCategory(MileageCategory category) {
		this.category = category;
	}
	
	public MileageVO setupNewMember() {
		Objects.requireNonNull(m_no);
		this.category = MileageCategory.SAVE;
		this.change_amount = BigDecimal.valueOf(10000000);
		this.reason = "오픈기념 천만포인트 행사";
		return this;
	}
}
