package com.got.domain;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.got.controller.RsaController;
import com.got.dao.ShippingReceivingDao;
import com.got.service.MemberService;
import com.got.vo.MemberVO;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml",
		"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class ShippingReceivingTest {
	
	@Autowired ShippingReceivingDao dao;
	
	@Test
	public void selectTest() {
		System.out.println(dao.selectListWithG_no(15,5).get(2).getCategory());
		System.out.println(dao.selectListWithG_no(15,100).size());
		System.out.println(dao.selectListWithG_no(15).size());
	}
}
