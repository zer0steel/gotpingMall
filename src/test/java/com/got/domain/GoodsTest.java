package com.got.domain;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.got.enums.GoodsStatus;
import com.got.enums.MenuLevel;
import com.got.service.CategoryService;
import com.got.service.GoodsService;
import com.got.util.CommonUtil;
import com.got.vo.CategoryVO;
import com.got.vo.GoodsVO;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml",
		"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class GoodsTest {
	@Autowired GoodsService gs;
	@Autowired CategoryService cs;
	
	@Test
	public void statusTest() {
		System.out.println(CommonUtil.convertToJSON(GoodsStatus.values()));
	}
	
	@Test
	public void getOneTest() {
		System.out.println("----------------getOneTest--------------------");
		GoodsVO g = gs.detail(117);
		double rate = g.getDiscount_rate();
		double rate2 = (100 - rate) / 100;
		System.out.println(g.getSell_price() * rate2);
		System.out.println("---------------- !getOneTest --------------------");
	}
	
	@Test
	public void getAllTest() {
		System.out.println(gs.getAll().size());
	}
	
	@Test
	public void getWithCategory() {
		System.out.println("----------------getWithCategory--------------------");
		CategoryVO c = new CategoryVO();
		MenuLevel.groupingCategories(cs.getAll());
		c.setC_no(80);
		System.out.println(c.getTitle() + " | " + c.getMenu_level());
		System.out.println();
		System.out.println("1 : " + gs.getWithCategory(c).size());
		System.out.println();
		System.out.println("메인 이미지 " + gs.getWithCategory(c).get(0).getMainImg());
		System.out.println("----------------! getWithCategory !--------------------");
	}
	
	
}