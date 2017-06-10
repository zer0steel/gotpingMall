package com.got.test.select;


import javax.inject.Inject;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.got.helper.TestUtil;
import com.got.mapper.CommonsMapper;
import com.got.mapper.deal.DealDetailMapper;
import com.got.mapper.deal.DealMapper;
import com.got.mapper.goods.StockMapper;
import com.got.vo.goods.GoodsVO;
import com.got.vo.member.MemberVO;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml",
		"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class common {
	
	@Inject CommonsMapper commonsMapper;
	
	@Test
	public void test() {
	}
}
