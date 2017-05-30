package com.got.vo;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Objects;

import com.got.enums.MileageCategory;
import com.got.vo.deal.PaymentVO;

public class MileageVO {
	private Integer m_no, p_no;
	private Timestamp m_date;
	private BigDecimal curr_mileage, change_amount;
	private String reason;
	private MileageCategory category;
	
	@Override
	public String toString() {
		return "MileageVO [m_no=" + m_no + ", m_date=" + m_date + ", curr_mileage=" + curr_mileage + ", change_amount="
				+ change_amount + ", reason=" + reason + ", p_no=" + p_no + "]";
	}
	
	public MileageVO() {}
	public MileageVO(Integer m_no) {
		this.m_no = m_no;
	}
	
	public Integer getM_no() {
		return m_no;
	}
	public void setM_no(Integer m_no) {
		this.m_no = m_no;
	}
	public Timestamp getM_date() {
		return m_date;
	}
	public void setM_date(Timestamp m_date) {
		this.m_date = m_date;
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
