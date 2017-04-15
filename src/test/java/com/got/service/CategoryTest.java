package com.got.service;


import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.got.enums.Menu_level;
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
	
	@Before
	public void setup() {
		c1 = new CategoryVO();
		c1.setIn_use(false);
		c1.setParent_no(0);
		c1.setMenu_level(1);
		c1.setTitle("슬리퍼");
		
		c2 = new CategoryVO();
		c2.setIn_use(true);
		c2.setParent_no(3);
		c2.setMenu_level(1);
		c2.setTitle("");
		
		c3 = new CategoryVO();
		c3.setIn_use(false);
		c3.setParent_no(0);
		c3.setMenu_level(1);
		c3.setTitle("슬리퍼");
	}
	
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
		System.out.println(Menu_level.BIG.getCategories().size());
		System.out.println(Menu_level.MIDDLE.getCategories().size());
		System.out.println(Menu_level.SMALL.getCategories().size());
	}
}
