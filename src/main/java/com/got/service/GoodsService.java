package com.got.service;

import java.util.List;

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
	
	private static Logger log = Logger.getLogger(GoodsService.class);
	
	@Autowired private GoodsDao dao;
	@Autowired private FileDao fdao;
	@Autowired private GoodsOptionDao goDao;
	@Autowired private SRService srService;

	public void enroll(GoodsVO g) {
		log.info(g.toString());
		validationCheck(g);
		
		g.setStatus(GoodsStatus.STAND_BY);
		dao.insert(g);
	}
	
	public void enrollWithImg(GoodsVO g, String[] fileInfo) {
		log.info(g.toString());
		validationCheck(g);
		
		g.setStatus(GoodsStatus.STAND_BY);
		
		List<GoodsImgVO> goodsImgs = CommonUtil.getVO(fileInfo, GoodsImgVO.class);
		CategoryVO c = MenuLevel.findBigCategory(g.getC_no());
		g.setImages(FileUtil.moveToSavePath(c.getTitle(), goodsImgs));
		dao.insert(g);
	}

	private void validationCheck(GoodsVO g) {
		if(g.getC_no() < 0)
			throw new IllegalArgumentException("분류번호가 지정안됨.");
		if(g.getName() == null || (g.getName() != null && g.getName().equals(""))) {
			throw new IllegalArgumentException("상품명이 없음 : " + g.getName());
		}
		
	}

	public List<GoodsVO> getAll() {
		return dao.selectAll();
	}

	/**
	 * 최근 5개 내역과 함께 상품 상세 정보를 반환한다.
	 * @param goods_no
	 * @return goodsVO
	 */
	public GoodsVO detailAndSRHistory(int g_no) {
		GoodsVO g = dao.selectOne(g_no);
		g.setGoodsOptions(goDao.selectListWithG_no(g_no));
		g.setImages(fdao.selectGoodsImg(g_no));
		g.setHistory(srService.getRecentHistory(g_no));
		log.info(g);
		return g;
	}
	
	
	/**
	 * @param g_no
	 * @param amount
	 * @return 재고 계산 처리된 goodsVO
	 */
	public GoodsVO updateStock(int g_no, int amount) {
		GoodsVO g = dao.selectOne(g_no);
		g.updateStock(amount);
		return g;
	}

	public void update(GoodsVO g) {
		if(g.getG_no() == 0)
			throw new IllegalArgumentException("PK값 g_no 가 0임");
		
		dao.update(g);
	}

	public void delete(int g_no) {
		if(g_no == 0)
			throw new IllegalArgumentException("PK값 g_no 가 0임");
		
		dao.deleteOne(g_no);
	}

	public List<GoodsVO> getWithCategory(CategoryVO c) {
		if(c.getC_no() == 0)
			throw new IllegalArgumentException("분류번호가 0");
		
		if(c.getMenuLevel() == MenuLevel.SMALL)
			return dao.selectListWithSmall(c);
		else if(c.getMenuLevel() == MenuLevel.MIDDLE)
			return dao.selectListWithMiddle(c.getC_no());
		
		throw new IllegalArgumentException("아직 대분류는 지원 안함");
	}

	public GoodsVO detail(int g_no) {
		GoodsVO g = dao.selectOne(g_no);
		g.setImages(fdao.selectGoodsImg(g.getG_no()));
		return g;
	}
}