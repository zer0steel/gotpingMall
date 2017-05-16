package com.got.domain;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.got.enums.HistoryCategory;
import com.got.service.SRService;
import com.got.vo.goods.ShippingReceivingVO;


@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml",
		"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class SRHistoryTest {
	@Autowired SRService s;
	
	@Test
	public void insertTest() {
		ShippingReceivingVO sr = new ShippingReceivingVO();
		sr.setEnumCategory(HistoryCategory.FREE);
		sr.setAmount(1);
		sr.setG_no(29);
		sr.setDetail("");
	}
}