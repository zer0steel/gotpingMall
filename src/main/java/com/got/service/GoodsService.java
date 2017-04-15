package com.got.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.got.dao.GoodsDao;
import com.got.dao.ShippingReceivingDao;
import com.got.enums.GoodsStatus;
import com.got.util.CommonUtil;
import com.got.vo.GoodsVO;

@Service
public class GoodsService {
	
	@Autowired private GoodsDao dao;
	@Autowired private ShippingReceivingDao srDao;

	public int enroll(GoodsVO g) {
		g.setStatus(GoodsStatus.STAND_BY);
		return dao.insert(g);
	}

	public List<GoodsVO> getAll() {
		return dao.selectAll();
	}

	private static final int DETAIL_GOODS_SHOW_HISTORY_COUNT = 5;
	/**
	 * 상품 상세정보를 최근 5개 거래내역과 함께 가져온다
	 * @param goods_no
	 * @return json 으로 표현한 상품 정보
	 */
	public String detailAndSRHistory(int goods_no) {
		GoodsVO g = dao.selectOneWithG_no(goods_no);
		g.setHistory(srDao.selectListWithG_no(goods_no, DETAIL_GOODS_SHOW_HISTORY_COUNT));
		return CommonUtil.convertToJSON(g);
	}
}