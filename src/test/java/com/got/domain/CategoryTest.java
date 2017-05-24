package com.got.domain;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Map;

import javax.inject.Inject;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.got.enums.GoodsStatus;
import com.got.enums.Level;
import com.got.mapper.goods.CategoryMapper;
import com.got.mapper.goods.GoodsMapper;
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
	@Inject GoodsMapper goodsMapper;
	
	CategoryVO c;
	Map<Integer, CategoryVO> map;
	@Before
	public void setup() {
		map = s.getCategories();
	}
	
	@Test
	public void getCategories() {
		System.out.println(s.getCategories());
	}
	
	@Test
	public void getGoodsWithCategory() {
		map.values().forEach(vo -> {
			assertThat(categoryMapper.selectOne(vo.getC_no()).getLevels(), is(Level.BIG));
			System.out.println("결과");
			System.out.println(goodsMapper.selectListWithC_no(vo.getC_no(),GoodsStatus.FOR_SALE));
		});
	}
}
