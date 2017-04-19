package com.got.service;


import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.got.enums.MenuLevel;
import com.got.util.CommonUtil;
import com.got.vo.CategoryVO;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml",
		"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class CategoryTest {
	@Autowired CategoryService cs;
	CategoryVO c1;
	CategoryVO c2;
	CategoryVO c3;
	
	
//	@Test
//	public void getAllTest() {
//		List<CategoryVO> categorys = cs.getAll();
//		System.out.println(categorys.size());
//	}
//	
//	@Test
//	public void insertErrorTest() {
//		cs.enroll(c1);
//		cs.enroll(c2);
//		cs.enroll(c3);
//	}
//	
//	@Test
//	public void getOneTest() {
//		System.out.println(cs.getOneWithJSON(10));
//	}
	
	@Test
	public void enumTest() {
		System.out.println(MenuLevel.BIG.getCategories().size());
		System.out.println(MenuLevel.MIDDLE.getCategories().size());
		System.out.println(MenuLevel.SMALL.getCategories().size());
	}
}
