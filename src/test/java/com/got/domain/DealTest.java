package com.got.domain;


import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.got.controller.RsaController;
import com.got.mapper.deal.DealDetailMapper;
import com.got.mapper.deal.DealMapper;
import com.got.mapper.goods.StockMapper;
import com.got.service.MemberService;
import com.got.vo.deal.DealVO;
import com.got.vo.member.MemberVO;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml",
		"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class DealTest {
	
	@Inject StockMapper stockMapper;
	@Inject DealDetailMapper detailMapper;
	@Inject DealMapper dealMapper;
	
	@Test
	public void update() {
		System.out.println(dealMapper.selectOneWithDetails(61));
//		DealVO d = new DealVO();
//		d.setD_no(20);
//		d.setCategory(2);
//		d.setDetail("ddd");
//		dealMapper.updateOne(d);
	}
}
