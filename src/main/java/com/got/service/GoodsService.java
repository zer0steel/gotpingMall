package com.got.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

	/**
	 * 최근 5개 내역과 함께 상품 상세 정보를 반환한다.
	 * @param goods_no
	 * @return goodsVO
	 */
	public GoodsVO detailAndSRHistory(int goods_no) {
		GoodsVO g = dao.selectOneWithG_no(goods_no);
		g.setHistory(srDao.selectListWithG_no(goods_no, SRService.DETAIL_GOODS_SHOW_HISTORY_COUNT));
		return g;
	}
	
	
	/**
	 * 공사중
	 * @param g_no
	 * @param amount
	 * @return
	 */
	public String updateStock(int g_no, int amount) {
		GoodsVO g = dao.selectOneWithG_no(g_no);
		if(g.updateStock(amount)) {
			return null;
		}
		else 
			return "재고 값이 음수가 되어 수정할 수 없습니다.";
	}

	public void update(GoodsVO g) {
		if(g.getG_no() == 0)
			throw new IllegalArgumentException("PK값 g_no 가 0임");
		if(dao.update(g) == 1)
			return;
		throw new RuntimeException("수정 실패");
	}

	public void delete(int g_no) {
		if(g_no == 0)
			throw new IllegalArgumentException("PK값 g_no 가 0임");
		if(dao.deleteOne(g_no) == 1) {
			return;
		}
		throw new RuntimeException("삭제 실패");
	}
}