package com.got.test.select;

import java.util.List;

import javax.inject.Inject;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.got.enums.GoodsStatus;
import com.got.helper.TestUtil;
import com.got.mapper.goods.CategoryMapper;
import com.got.mapper.goods.GoodsMapper;
import com.got.service.goods.GoodsService;
import com.got.vo.goods.CategoryVO;
import com.got.vo.goods.GoodsVO;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml",
		"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class GoodsTest {
	@Inject GoodsService gs;
	
	@Inject GoodsMapper goodsMapper;
	@Inject CategoryMapper categoryMapper;
	
	List<GoodsVO> list;
	List<CategoryVO> categories;
	
	public static final String LINE = "--------------------------------";
	
	@Before
	public void setup() {
		list = goodsMapper.selectAll();
		categories = categoryMapper.selectAll();
	}
	
	@After
	public void print() {
		System.out.println();
	}
	
	@Test
	public void selectOne() {
		TestUtil.printMethod("selectOne");
		System.out.println(goodsMapper.selectOne(list.get(2).getG_no()));
	}
	
	@Test
	public void selectList() {
		TestUtil.printMethod("selectList");
		goodsMapper.selectListWithC_no(categories.get(0).getC_no(), GoodsStatus.FOR_SALE)
		.forEach(System.out::println);
	}
	
	@Test
	public void selectList2() {
		TestUtil.printMethod("selectList2");
		goodsMapper.selectListWeeklySellAmount()
		.forEach(System.out::println);
	}
}