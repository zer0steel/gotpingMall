package com.got.service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Objects;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.got.enums.DealCategory;
import com.got.enums.MileageCategory;
import com.got.enums.PaymentStatus;
import com.got.mapper.deal.DealMapper;
import com.got.mapper.deal.OrderMapper;
import com.got.mapper.deal.PaymentMapper;
import com.got.service.deal.DealService;
import com.got.util.IamportUtil;
import com.got.vo.deal.DealVO;
import com.got.vo.deal.OrderVO;
import com.got.vo.deal.PaymentVO;
import com.got.vo.member.MemberVO;
import com.siot.IamportRestClient.IamportClient;
import com.siot.IamportRestClient.response.IamportResponse;
import com.siot.IamportRestClient.response.Payment;

@Service
public class PaymentService {
	@Inject private DealService dealService;
	@Inject private MileageService mileageService;
	
	@Inject private DealMapper dealMapper;
	@Inject private PaymentMapper paymentMapper;
	@Inject private OrderMapper orderMapper;
	
	@Transactional
	public void saveCheckout(PaymentVO pay, OrderVO order) {
		DealVO d = dealMapper.selectOneWithDetails(order.getD_no());
		if(pay.isViaImptCheckout()) {
			Payment imptPay = IamportUtil.getCheckoutData(pay.getImpt_id());
			try {
				validationCheck(imptPay, d, pay);
			} catch(IllegalArgumentException e) {
				// 환불절차 나중에 코딩
				throw e;
			}
			pay.setImptData(imptPay);
		}
		pay.setEnumStatus(PaymentStatus.COMPLETE);
		order.setDealVO(d);
		
		insertCheckout(pay, order);
		dealService.updateStock(d, DealCategory.SELL);
	}
	
	@Transactional
	private void insertCheckout(PaymentVO pay, OrderVO order) {
		paymentMapper.insert(pay, order.getD_no());
		orderMapper.insert(order);
		
		if(Objects.nonNull(order.getM_no()))
			mileageService.insertHistory(pay, order.getDetails(), order.getM_no());
	}

	private void validationCheck(Payment pay, DealVO deal, PaymentVO pVO) throws IllegalArgumentException{
		String d_no = pay.getCustomData().substring(1, pay.getCustomData().length() - 1);
		if(Integer.parseInt(d_no) != deal.getD_no())
			throw new IllegalArgumentException("거래 번호가 다름 | 아임포트 저장 거래번호 : " + d_no + " | 서버가 가지고 있는 거래번호 : " + deal.getD_no());
		else if(!pay.getAmount().equals(pVO.getPay_amount()))
			throw new IllegalArgumentException("결제 금액이 다름");
		else if(!pay.getAmount().add(pVO.getUse_mileage()).equals(deal.getTotal_price()))
			throw new IllegalArgumentException("결제금액과 마일리지 사용량을 합친 금액이 총 결제금액과 다름 | " + pVO.getUse_mileage() + "(마일리지) + " + pay.getAmount() + "(결제금액) = " + deal.getTotal_price() + "(총결제금액)");
	}
}
