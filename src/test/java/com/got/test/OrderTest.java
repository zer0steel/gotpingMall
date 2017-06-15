package com.got.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.got.enums.OrderStatus;
import com.got.mapper.deal.OrderMapper;
import com.got.mapper.deal.PaymentMapper;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml",
	"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class OrderTest {
//	62
//	61
//	60
//	59
//	58
//	57
//	53
//	52
	@Inject PaymentMapper payMapper;
	@Test
	public void updateStstus() {
		Integer[] data = {62,61,60,59,58,57,53,52};
		List<Integer> list = Arrays.asList(62,61,60,59,58,57,53,52);
		payMapper.updateStatus(data, OrderStatus.DELIVERY_COMPLETE);
	}
}
