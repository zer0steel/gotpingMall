package com.got.domain;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.got.service.ImportService;
import com.got.service.PaymentService;
import com.got.vo.deal.OrderVO;
import com.got.vo.deal.PaymentVO;
import com.siot.IamportRestClient.IamportClient;
import com.siot.IamportRestClient.response.IamportResponse;
import com.siot.IamportRestClient.response.Payment;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml",
		"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class CheckoutTest {
	
	private static final String KEY = "6804401102834731";
	private static final String API = "T5NM4DtLvvg0zq8CGZV8MhiJzvdUAjAuoogt1JexevxOWFqCFlLsC3bbzp6aIGE75UA6EOETkcHgR7Tx";
	private static final IamportClient CLIENT = new IamportClient(KEY, API);
	
	String test_uid = "imp_721653301847";
	String test_uid2 = "imp_034941102107";
	
	@Inject private PaymentService paymentService;
	
	@Test
	public void test() {
		PaymentVO p = new PaymentVO();
		p.setImpt_id(test_uid2);
		p.setUse_mileage(BigDecimal.valueOf(44500));
		p.setPay_amount(BigDecimal.valueOf(500));
		
		OrderVO o = new OrderVO();
		o.setD_no(116);
		paymentService.saveCheckout(p, o);
	}
	
	@Test
	public void check() {
		Payment res = CLIENT.paymentByImpUid(test_uid).getResponse();
		System.out.println(res.getCardName());
		System.out.println(res.getPayMethod());
		System.out.println(res.getReceiptUrl());
		System.out.println(res.getStatus());

	}
	
	@Test
	public void dateFormat() {
		Payment res = CLIENT.paymentByImpUid(test_uid).getResponse();
		Date paidAt = res.getPaidAt();
		System.out.println(paidAt);
		Timestamp timestamp = Timestamp.from(Instant.ofEpochMilli(paidAt.getTime()));
		System.out.println(timestamp);
	}
	
}
