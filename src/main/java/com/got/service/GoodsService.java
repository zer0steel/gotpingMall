package com.got.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.got.dao.FileDao;
import com.got.dao.GoodsDao;
import com.got.dao.GoodsOptionDao;
import com.got.dao.OptionStockDao;
import com.got.enums.GoodsStatus;
import com.got.enums.MenuLevel;
import com.got.mapper.files.GoodsImageMapper;
import com.got.mapper.goods.GoodsMapper;
import com.got.mapper.goods.GoodsOptionMapper;
import com.got.mapper.goods.StockMapper;
import com.got.vo.goods.CategoryVO;
import com.got.vo.goods.GoodsVO;
import com.got.vo.goods.StockVO;

@Service
public class GoodsService {
	
	@Inject private GoodsDao dao;
	@Inject private FileDao fdao;
	@Inject private GoodsOptionDao goDao;
	@Inject private SRService srService;
	@Inject private OptionStockDao osDao;
	
	@Inject private StockService stockService;
	@Inject private GoodsOptionService goodsOptionService;
	@Inject private GoodsImageService goodsImageService;
	
	@Inject private GoodsMapper goodsMapper;
	@Inject private StockMapper stockMapper;
	@Inject private GoodsImageMapper goodsImageMapper;
	@Inject private GoodsOptionMapper goodsOptionMapper;
	
	@Transactional
	public void enroll(GoodsVO g) {
		g.setStatus(GoodsStatus.STAND_BY);
		goodsMapper.insert(g);
		Objects.nonNull(g.getG_no());
		goodsOptionService.insertGoodsOption(g);
		stockService.insertStock(g);
		goodsImageService.insertGoodsImage(g);
	}
	
	public List<GoodsVO> getAll() {
		return goodsMapper.selectAll();
	}
	
	public List<GoodsVO> getBuyList(List<StockVO> list) {
		Map<Integer, GoodsVO> goodsMap = new HashMap<>();
		list.forEach(os -> {
			Integer g_no = os.getG_no();
			Objects.requireNonNull(g_no);
			GoodsVO g = goodsMap.get(g_no);
			if(Objects.isNull(g)) {
				g = new GoodsVO(g_no);
				goodsMap.put(g_no, g);
			}
			g.getStocks().add(os);
		});
		return dao.selectList(goodsMap.values());
	}
	
	public GoodsVO detail(Integer g_no) {
		Objects.requireNonNull(g_no);
		GoodsVO g = goodsMapper.selectOne(g_no);
		g.setGoodsOptions(goodsOptionMapper.selectListWithG_no(g_no));
		g.setImages(goodsImageMapper.selectList(g_no));
		g.setStocks(stockMapper.selectListWithG_no(g_no));
		return g;
	}

	public List<GoodsVO> getWithCategory(CategoryVO c) {
		Objects.requireNonNull(c.getC_no());
		
		if(c.getMenuLevel() == MenuLevel.SMALL)
			return dao.selectListWithSmall(c);
		else if(c.getMenuLevel() == MenuLevel.MIDDLE)
			return dao.selectListWithMiddle(c.getC_no());
		
		throw new IllegalArgumentException("아직 대분류는 지원 안함");
	}

	/**
	 * 최근 5개 내역과 함께 상품 상세 정보를 반환한다.
	 * @param goods_no
	 * @return goodsVO
	 */
	public GoodsVO detailAndSRHistory(Integer g_no) {
		GoodsVO g = detail(g_no);
		g.setHistory(srService.getRecentHistory(g_no));
		return g;
	}
	
	
	/**
	 * @param g_no
	 * @param amount
	 * @return 재고 계산 처리된 goodsVO
	 */
	public GoodsVO updateStock(Integer g_no, int amount) {
		Objects.requireNonNull(g_no);
		GoodsVO g = dao.selectOne(g_no);
		g.updateStock(amount);
		return g;
	}

	public void update(GoodsVO g) {
		Objects.requireNonNull(g.getG_no());
		dao.update(g);
	}
}