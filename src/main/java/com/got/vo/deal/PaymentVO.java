package com.got.vo.deal;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

import com.got.enums.MileageCategory;
import com.got.enums.PaymentStatus;
import com.got.vo.MileageVO;
import com.siot.IamportRestClient.response.Payment;

public class PaymentVO {

	private Integer p_no, o_no;
	private String p_way, p_way_detail, impt_id, receipt_url;
	private BigDecimal pay_amount = BigDecimal.ZERO, use_mileage = BigDecimal.ZERO;
	private Timestamp pay_date;
	private OrderVO order;
	private PaymentStatus status;
	private Mileage mileage;
	
	public Integer getP_no() {
		return p_no;
	}
	public void setP_no(Integer p_no) {
		this.p_no = p_no;
	}
	public PaymentStatus getStatus() {
		return status;
	}
	public void setImptStatus(String imptStatus) {
		this.status = PaymentStatus.of(imptStatus);
	}
	public void setStatus(int code) {
		this.status = PaymentStatus.of(code);
	}
	public void setEnumStatus(PaymentStatus status) {
		this.status = status;
	}
	public String getP_way() {
		return p_way;
	}
	public void setP_way(String p_way) {
		this.p_way = p_way;
	}
	public String getP_way_detail() {
		return p_way_detail;
	}
	public void setP_way_detail(String p_way_detail) {
		this.p_way_detail = p_way_detail;
	}
	public Integer getO_no() {
		return o_no;
	}
	public void setO_no(Integer o_no) {
		this.o_no = o_no;
	}
	public BigDecimal getPay_amount() {
		return pay_amount;
	}
	public void setPay_amount(BigDecimal pay_amount) {
		this.pay_amount = pay_amount;
	}
	public BigDecimal getUse_mileage() {
		return use_mileage;
	}
	public void setUse_mileage(BigDecimal use_mileage) {
		this.use_mileage = use_mileage;
	}
	public OrderVO getOrder() {
		return order;
	}
	public void setOrder(OrderVO order) {
		this.order = order;
	}
	public String getImpt_id() {
		return impt_id;
	}
	public void setImpt_id(String impt_id) {
		this.impt_id = impt_id;
	}
	public Timestamp getPay_date() {
		return pay_date;
	}
	public void setPay_date(Timestamp pay_date) {
		this.pay_date = pay_date;
	}
	public String getReceipt_url() {
		return receipt_url;
	}
	public void setReceipt_url(String receipt_url) {
		this.receipt_url = receipt_url;
	}
	@Override
	public String toString() {
		return "PaymentVO [p_no=" + p_no + ", o_no=" + o_no + ", p_way=" + p_way + ", p_way_detail=" + p_way_detail
				+ ", impt_id=" + impt_id + ", pay_amount=" + pay_amount + ", use_mileage=" + use_mileage + ", pay_date="
				+ pay_date + ", order=" + order + ", status=" + status + ", mileage=" + mileage + "]";
	}
	
	public MileageVO getMileage(MileageCategory category) {
		if(category == MileageCategory.SAVE) {
			return this.mileage.getSave();
		}
		else {
			return this.mileage.getUse();
		}
	}
	
	public void setMileage(MileageVO mileageVO) {
		if(mileageVO.getCategory() == MileageCategory.SAVE) {
			mileage.setSave(mileageVO);
		}
		else {
			mileage.setUse(mileageVO);
		}
	}
	
	public void setImptData(Payment payment) {
		this.receipt_url = payment.getReceiptUrl();
		this.p_way_detail = payment.getCardName();
		this.p_way = payment.getPayMethod();
		this.pay_date = Timestamp.from(Instant.ofEpochMilli(payment.getPaidAt().getTime()));
	}
	
	public boolean isUseMileage() {
		return this.use_mileage.intValue() > 0;
	}
	
	public boolean isViaImptCheckout() {
		return Objects.nonNull(this.impt_id);
	}

	private class Mileage {
		MileageVO save;
		MileageVO use;
		public MileageVO getSave() {
			return save;
		}
		public void setSave(MileageVO save) {
			this.save = save;
		}
		public MileageVO getUse() {
			return use;
		}
		public void setUse(MileageVO use) {
			this.use = use;
		}
	}
}
