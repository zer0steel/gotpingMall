package com.got.domain;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.List;

import javax.inject.Inject;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.got.mapper.goods.CategoryMapper;
import com.got.mapper.goods.GoodsMapper;
import com.got.mapper.goods.GoodsOptionMapper;
import com.got.mapper.goods.OptionMapper;
import com.got.service.goods.CategoryService;
import com.got.service.goods.GoodsOptionService;
import com.got.service.goods.StockService;
import com.got.vo.VOHelper;
import com.got.vo.goods.CategoryVO;
import com.got.vo.goods.GoodsOptionVO;
import com.got.vo.goods.GoodsOptionVO.Detail;
import com.got.vo.goods.GoodsVO;
import com.got.vo.goods.OptionVO;
import com.got.vo.goods.StockVO;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml",
		"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class GoodsOptionTest {
	
	@Inject CategoryMapper categoryMapper;
	@Inject GoodsMapper goodsMapper;
	@Inject OptionMapper optionMapper;
	@Inject GoodsOptionMapper goodsOptionMapper;
	
	@Inject GoodsOptionService gos;
	@Inject StockService ss;
	
	@Test
	public void insert() {
		List<OptionVO> options = optionMapper.selectListWithC_no(categoryMapper.selectAll().get(0).getC_no());
		List<GoodsOptionVO> goodsOptions = VOHelper.createGoodsOptions(options);
		List<StockVO> stocks = ss.createOptionStocks(goodsOptions);
		System.out.println(stocks);
	}
}
