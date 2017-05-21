package com.got.domain;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import javax.inject.Inject;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.got.dao.CategoryDao;
import com.got.mapper.goods.CategoryMapper;
import com.got.mapper.goods.OptionMapper;
import com.got.service.CategoryService;
import com.got.service.GoodsOptionService;
import com.got.vo.goods.CategoryVO;
import com.got.vo.goods.OptionVO;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml",
		"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class OptionTest {
	
	@Inject CategoryMapper categoryMapper;
	@Inject OptionMapper optionMapper;
	
	OptionVO o1;
	OptionVO o2;
	CategoryVO c;
	boolean isSetup = true;
	
	@Before
	public void setup() {
		c = categoryMapper.selectAll().get(0);
		if(isSetup)
			return;
		o1 = new OptionVO();
		o1.setC_no(c.getC_no());
		o1.setO_name("색상");
		insert(o1);
		
		o2 = new OptionVO();
		o2.setC_no(c.getC_no());
		o2.setO_name("사이즈");
		isSetup = true;
		insert(o1);
	}
	
	private void insert(OptionVO o) {
		assertThat(optionMapper.insert(o), is(1));
	}
	
	@Test
	public void selectWithC_no() {
		System.out.println(optionMapper.selectListWithC_no(c.getC_no()));
	}
}
