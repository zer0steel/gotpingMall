package com.got.service;

import static org.junit.Assert.assertNull;

import java.util.List;
import java.util.Objects;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.got.dao.FileDao;
import com.got.dao.GoodsDao;
import com.got.dao.GoodsOptionDao;
import com.got.enums.GoodsStatus;
import com.got.enums.MenuLevel;
import com.got.util.CommonUtil;
import com.got.util.FileUtil;
import com.got.vo.CategoryVO;
import com.got.vo.GoodsImgVO;
import com.got.vo.GoodsVO;

@Service
public class GoodsService {
	
	@Autowired private GoodsDao dao;
	@Autowired private FileDao fdao;
	@Autowired private GoodsOptionDao goDao;
	@Autowired private SRService srService;

	public void enroll(GoodsVO g) {
		validationCheck(g);
		
		g.setStatus(GoodsStatus.STAND_BY);
		dao.insert(g);
	}
	
	public void enrollWithImg(GoodsVO g, String[] fileInfo) {
		validationCheck(g);
		Objects.requireNonNull(fileInfo);
		
		g.setStatus(GoodsStatus.STAND_BY);
		
		List<GoodsImgVO> goodsImgs = CommonUtil.getVO(fileInfo, GoodsImgVO.class);
		CategoryVO c = MenuLevel.findBigCategory(g.getC_no());
		g.setImages(FileUtil.moveToSavePath(c.getTitle(), goodsImgs));
		dao.insert(g);
	}


	public List<GoodsVO> getAll() {
		return dao.selectAll();
	}

	public GoodsVO detail(Integer g_no) {
		Objects.requireNonNull(g_no);
		
		GoodsVO g = dao.selectOne(g_no);
		g.setGoodsOptions(goDao.selectListWithG_no(g_no));
		g.setImages(fdao.selectGoodsImg(g_no));
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