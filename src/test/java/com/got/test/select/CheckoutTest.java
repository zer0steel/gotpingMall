package com.got.test.select;

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

import com.got.helper.TestUtil;
import com.got.mapper.deal.PaymentMapper;
import com.got.service.PaymentService;
import com.got.vo.SearchVO;
import com.got.vo.deal.PaymentVO;
import com.siot.IamportRestClient.IamportClient;
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
	@Inject private PaymentMapper payMapper;

	@After
	public void printAfter() {
		System.out.println();
	}
	
	@Test
	public void dateFormat() {
		TestUtil.printMethod("dateFormat");
		Payment res = CLIENT.paymentByImpUid(test_uid).getResponse();
		Date paidAt = res.getPaidAt();
		System.out.println(paidAt);
		Timestamp timestamp = Timestamp.from(Instant.ofEpochMilli(paidAt.getTime()));
		System.out.println(timestamp);
	}
	
	@Test
	public void selectList() {
		TestUtil.printMethod("selectList");
		List<PaymentVO> list = paymentService.getCheckoutList(60, new SearchVO());
		list.stream().forEach(System.out::println);
	}
	
	@Test
	public void selectOne() {
		TestUtil.printMethod("selectOne");
		PaymentVO pay = paymentService.getCheckout("1496295435083");
		System.out.println(pay);
	}
	
	@Test
	public void search() {
		TestUtil.printMethod("search");
		SearchVO s = new SearchVO();
		s.setStartDate("2017.06.01");
		s.setEndDate("2017.06.01");
		List<PaymentVO> list = paymentService.getCheckoutList(60, s);
		list.stream().forEach(System.out::println);
	}
}
