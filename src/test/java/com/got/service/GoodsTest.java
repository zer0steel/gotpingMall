package com.got.service;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.got.enums.GoodsStatus;
import com.got.util.CommonUtil;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml",
		"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class GoodsTest {
	@Autowired GoodsService gs;
	
	@Test
	public void statusTest() {
		System.out.println(CommonUtil.convertToJSON(GoodsStatus.values()));
	}
	
	@Test
	public void getOneTest() {
		System.out.println(gs.detailAndSRHistory(13));
	}
}