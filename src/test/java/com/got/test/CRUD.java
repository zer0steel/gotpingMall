package com.got.test;


import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.got.mapper.CommonsMapper;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml",
		"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class CRUD {
	@Inject CommonsMapper mapper;
	
	@Test
	public void delete() {
		mapper.deleteAll("mileage");
		mapper.deleteAll("payment");
		mapper.deleteAll("orders");
//		mapper.deleteAll("member_grade");
//		mapper.deleteAll("member");
	}
}