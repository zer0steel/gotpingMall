package com.got.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.got.dao.FileDao;
import com.got.dao.GoodsDao;
import com.got.dao.GoodsOptionDao;
import com.got.dao.OptionStockDao;
import com.got.enums.MenuLevel;
import com.got.vo.CategoryVO;
import com.got.vo.GoodsOptionVO;
import com.got.vo.GoodsVO;
import com.got.vo.OptionStockVO;

@Service
public class GoodsService {
	
	@Autowired private GoodsDao dao;
	@Autowired private FileDao fdao;
	@Autowired private GoodsOptionDao goDao;
	@Autowired private SRService srService;
	@Autowired private OptionStockDao osDao;
	
	@Autowired private OptionStockService oss;
	@Autowired private GoodsOptionService gos;
	@Autowired private FileService fs;

	public void enroll(GoodsVO g) {
		validationCheck(g);
		dao.insert(g);
	}
	
	public void enroll(GoodsVO g, List<GoodsOptionVO> list, String[] fileInfoJSON) {
		list = gos.filteringEmptyArray(list);
		g.setGoodsOptions(list);
		g.setOptionStocks(oss.createOptionStocks(list));
		g.setImages(fs.setupImages(g.getC_no(), fileInfoJSON));
		dao.insert(g);
	}

	public List<GoodsVO> getAll() {
		return dao.selectAll();
	}
	
	public List<GoodsVO> getList(List<OptionStockVO> list) {
		Map<Integer, GoodsVO> goodsMap = new HashMap<>();
		list.forEach(os -> {
			Integer g_no = os.getG_no();
			Objects.requireNonNull(g_no);
			GoodsVO g = goodsMap.get(g_no);
			if(Objects.isNull(g)) {
				g = new GoodsVO(g_no);
				goodsMap.put(g_no, g);
			}
			g.getOptionStocks().add(os);
		});
		return dao.selectList(goodsMap.values());
	}
	
	public GoodsVO detail(Integer g_no) {
		Objects.requireNonNull(g_no);
		
		GoodsVO g = dao.selectOne(g_no);
		g.setGoodsOptions(goDao.selectListWithG_no(g_no));
		g.setImages(fdao.selectGoodsImg(g_no));
		g.setOptionStocks(osDao.selectWithG_no(g_no));
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
	
	private void validationCheck(GoodsVO g) {
		Objects.requireNonNull(g.getC_no());
		Objects.requireNonNull(g.getName());
		if(g.getName().equals(""))
			throw new IllegalArgumentException("상품명이 없음 : " + g.getName());
	}

}