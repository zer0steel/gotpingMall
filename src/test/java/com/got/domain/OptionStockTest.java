package com.got.domain;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.got.dao.CategoryDao;
import com.got.service.CategoryService;
import com.got.service.GoodsOptionService;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml",
		"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class OptionStockTest {
	
	@Autowired GoodsOptionService gos;
	
	@Test
	public void selectOne() {
		System.out.println(gos.getOptionalStocksJSON(122));
	}
}
