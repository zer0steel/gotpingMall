package com.got.test.select;


import java.util.Arrays;

import javax.inject.Inject;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.got.enums.OrderStatus;
import com.got.helper.TestUtil;
import com.got.mapper.CommonsMapper;
import com.got.mapper.ViewMapper;
import com.got.mapper.deal.DealDetailMapper;
import com.got.mapper.deal.DealMapper;
import com.got.mapper.goods.StockMapper;
import com.got.service.deal.PaymentService;
import com.got.vo.goods.GoodsVO;
import com.got.vo.member.MemberVO;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml",
		"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class commonAndViewTest {
	
	@Inject CommonsMapper commonsMapper;
	@Inject ViewMapper viewMapper;
	@Inject PaymentService ps;
	
	@Test
	public void select() {
		TestUtil.printMethod("select");
		ps.getCheckoutList().forEach(System.out::println);;
	}
	
	@Test
	public void select2() {
		TestUtil.printMethod("select2");
		ps.getCheckoutList(OrderStatus.DELIVERY_READY).forEach(System.out::println);;
	}
}
