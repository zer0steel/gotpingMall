package com.got.vo.deal;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

import com.got.enums.MileageCategory;
import com.got.enums.PayWay;
import com.got.enums.PaymentStatus;
import com.got.vo.MileageVO;
import com.siot.IamportRestClient.response.Payment;

public class PaymentVO {

	private Integer p_no, o_no;
	private String p_way_detail, order_uid, receipt_url;
	private BigDecimal pay_amount = BigDecimal.ZERO, use_mileage = BigDecimal.ZERO;
	private Timestamp pay_date;
	
	private OrderVO order;
	private Mileage mileage;
	private PaymentStatus status;
	private PayWay p_way;
	
	public Integer getP_no() {
		return p_no;
	}
	public void setP_no(Integer p_no) {
		this.p_no = p_no;
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
	public String getOrder_uid() {
		return order_uid;
	}
	public void setOrder_uid(String order_uid) {
		this.order_uid = order_uid;
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
	public PayWay getP_way() {
		if(Objects.isNull(p_way) && isPayFullMileage()) {
			p_way = PayWay.ALL_MILEAGE;
		}
		return p_way;
	}
	public void setP_way(int code) {
		this.p_way = PayWay.of(code);
	}
	public void setEnumP_way(PayWay p_way) {
		this.p_way = p_way;
	}
	private boolean isPayFullMileage() {
		return this.order.getTotal_price().subtract(this.use_mileage).intValue() == 0;
	}
	
	@Override
	public String toString() {
		return "PaymentVO [p_no=" + p_no + ", o_no=" + o_no + ", p_way=" + p_way + ", p_way_detail=" + p_way_detail
				+ ", impt_id=" + order_uid + ", pay_amount=" + pay_amount + ", use_mileage=" + use_mileage + ", pay_date="
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
		this.p_way = PayWay.of(payment.getPayMethod());
		this.pay_date = Timestamp.from(Instant.ofEpochMilli(payment.getPaidAt().getTime()));
	}
	
	public boolean isUseMileage() {
		return this.use_mileage.intValue() > 0;
	}
	
	public boolean isViaImptCheckout() {
		return Objects.nonNull(this.order_uid);
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
