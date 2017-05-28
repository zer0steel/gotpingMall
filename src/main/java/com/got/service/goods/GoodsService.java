package com.got.service.goods;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.got.enums.GoodsStatus;
import com.got.mapper.goods.GoodsMapper;
import com.got.mapper.goods.GoodsOptionMapper;
import com.got.mapper.goods.StockMapper;
import com.got.service.GoodsImageService;
import com.got.service.deal.DealService;
import com.got.vo.goods.GoodsVO;
import com.got.vo.goods.StockVO;

@Service
public class GoodsService {
	
	@Inject private DealService managmentService;
	
	@Inject private StockService stockService;
	@Inject private GoodsOptionService goodsOptionService;
	@Inject private GoodsImageService goodsImageService;
	
	@Inject private GoodsMapper goodsMapper;
	@Inject private StockMapper stockMapper;
	@Inject private GoodsOptionMapper goodsOptionMapper;
	
	public List<GoodsVO> getAll() {
		return goodsMapper.selectAll();
	}
	
	public List<GoodsVO> getWithC_no(Integer c_no) {
		Objects.requireNonNull(c_no);
		return goodsMapper.selectListWithC_no(c_no, GoodsStatus.FOR_SALE);
	}
	
	/**
	 * 최근 5개 내역과 함께 상품 상세 정보를 반환한다.
	 * @param goods_no
	 * @return goodsVO
	 */
	public GoodsVO detailAndSRHistory(Integer g_no) {
		GoodsVO g = detail(g_no);
		g.setHistory(managmentService.getRecentHistory(g_no));
		return g;
	}
	
	public GoodsVO detail(Integer g_no) {
		Objects.requireNonNull(g_no);
		GoodsVO g = goodsMapper.selectOne(g_no);
		g.setGoodsOptions(goodsOptionMapper.selectListWithG_no(g_no));
		g.setStocks(stockMapper.selectListWithG_no(g_no));
		return g;
	}
	
	@Transactional
	public void enroll(GoodsVO g) {
		g.setStatus(GoodsStatus.STAND_BY);
		goodsMapper.insert(g);
		Objects.nonNull(g.getG_no());
		g.setGoodsOptions(goodsOptionService.filteringEmptyArray(g.getGoodsOptions()));
		goodsOptionService.insertGoodsOption(g);
		stockService.insertStock(g);
		goodsImageService.insertGoodsImage(g);
	}
	
	/**
	 * @param g_no
	 * @param amount
	 * @return 재고 계산 처리된 goodsVO
	 */
	public GoodsVO updateStock(Integer g_no, int amount) {
		Objects.requireNonNull(g_no);
		GoodsVO g = goodsMapper.selectOne(g_no);
		g.updateStock(amount);
		return g;
	}

	public void update(GoodsVO g) {
		Objects.requireNonNull(g.getG_no());
		goodsMapper.updateOne(g);
	}
}