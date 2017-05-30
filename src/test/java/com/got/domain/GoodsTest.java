package com.got.domain;


import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.List;

import javax.inject.Inject;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.got.enums.GoodsStatus;
import com.got.mapper.goods.CategoryMapper;
import com.got.mapper.goods.GoodsMapper;
import com.got.mapper.goods.GoodsOptionMapper;
import com.got.mapper.goods.OptionMapper;
import com.got.mapper.goods.StockMapper;
import com.got.service.goods.CategoryService;
import com.got.service.goods.GoodsOptionService;
import com.got.service.goods.GoodsService;
import com.got.service.goods.StockService;
import com.got.vo.VOHelper;
import com.got.vo.goods.CategoryVO;
import com.got.vo.goods.GoodsOptionVO;
import com.got.vo.goods.GoodsVO;
import com.got.vo.goods.StockVO;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml",
		"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class GoodsTest {
	@Inject GoodsService gs;
	@Inject GoodsOptionService gos;
	@Inject CategoryService cs;
	@Inject StockService oss;
	
	@Inject CategoryMapper categoryMapper;
	@Inject OptionMapper optionMapper;
	@Inject GoodsMapper goodsMapper;
	@Inject GoodsOptionMapper goodsOptionMapper;
	@Inject StockMapper stockMapper;
	
	List<GoodsVO> list;
	List<GoodsOptionVO> goodsOptions;
	boolean isSetup = true;
	GoodsVO g;
	
	@Before
	public void setup() {
		list = gs.getAll();
		int size = goodsMapper.selectAll().size();
		goodsOptions = VOHelper.createGoodsOptions(
				optionMapper.selectListWithC_no(	categoryMapper.selectAll().get(0).getC_no()	)
				);
		if(isSetup)
			return;
		g = createGoods(categoryMapper.selectAll().get(0));
		System.out.println("상품 개수 : " + size++);
		g.setName("테스트-" + size);
		isSetup = true;
	}
	
	private GoodsVO createGoods(CategoryVO categoryVO) {
		GoodsVO g = new GoodsVO();
		g.setDetail("내용 테스트");
		g.setDiscount_rate(10);
		g.setSaving_mileage(1);
		g.setStatus(GoodsStatus.FOR_SALE);
		g.setC_no(categoryVO.getC_no());
		return g;
	}
	
	@Test
	public void selectOne() {
		System.out.println(list.get(0).getG_no());
	}
	
	@Test
	public void insert() {
		assertThat(goodsMapper.insert(g), is(1));
		goodsOptions.forEach(vo -> {
			vo.setG_no(g.getG_no());
			assertThat(goodsOptionMapper.insert(vo), is(1));
		});
		List<StockVO> list2 = oss.createOptionStocks(goodsOptions);
		list2.forEach(vo -> {
			vo.setG_no(g.getG_no());
			assertThat(stockMapper.insert(vo), is(1));
		});
	}
	
	@Test
	public void insertWithNoOption() {
		goodsOptions.clear();
//		gs.newenroll(g, goodsOptions, null);
	}
	
	@Test
	public void createGoodsOptionList() {
		System.out.println("createGoodsOptionList");
		goodsOptions.forEach(vo -> {
			System.out.println(vo);
		});
		List<StockVO> stocks = oss.createOptionStocks(goodsOptions);
		stocks.forEach(vo -> {
			System.out.println(vo);
		});
	}
}