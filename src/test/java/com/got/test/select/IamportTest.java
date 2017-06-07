package com.got.test.select;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.got.enums.OrderStatus;
import com.got.helper.TestUtil;
import com.got.mapper.deal.PaymentMapper;
import com.got.service.deal.PaymentService;
import com.got.vo.SearchVO;
import com.got.vo.deal.PaymentVO;
import com.siot.IamportRestClient.IamportClient;
import com.siot.IamportRestClient.response.Payment;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml",
		"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class IamportTest {
	
	private static final String KEY = "6804401102834731";
	private static final String API = "T5NM4DtLvvg0zq8CGZV8MhiJzvdUAjAuoogt1JexevxOWFqCFlLsC3bbzp6aIGE75UA6EOETkcHgR7Tx";
	private static final IamportClient CLIENT = new IamportClient(KEY, API);
	
	String test_uid = "imp_721653301847";
	String test_uid2 = "imp_034941102107";
	String test_uid3 = "imp_398630693061";
	
	@Inject private PaymentService paymentService;
	@Inject private PaymentMapper payMapper;

	@After
	public void printAfter() {
		System.out.println();
	}
	
	@Test
	public void payResult() {
		TestUtil.printMethod("payResult");
		Payment res = CLIENT.paymentByImpUid(test_uid3).getResponse();
		System.out.println(res.getAmount());
		System.out.println(res.getAmount().add(BigDecimal.ZERO).equals(BigDecimal.valueOf(1000)));
		System.out.println(new PaymentVO().getUse_mileage());
	}
	
}
