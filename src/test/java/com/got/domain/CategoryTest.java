package com.got.domain;

import javax.inject.Inject;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.got.mapper.goods.CategoryMapper;
import com.got.service.goods.CategoryService;
import com.got.vo.goods.CategoryVO;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml",
		"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class CategoryTest {
	
	@Autowired CategoryService s;
	@Inject CategoryMapper categoryMapper;
	
	CategoryVO c;
	boolean isSetup = false;
	
	@Before
	public void setup() {
		if(isSetup)
			return;
		c = new CategoryVO();
		c.setTitle("대분류 테스트");
		c.setMenu_level(1);
		isSetup = true;
	}
	
	@Test
	public void insert() {
		categoryMapper.insert(c);
	}
}
