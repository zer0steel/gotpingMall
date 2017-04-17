package com.got.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.got.dao.ShippingReceivingDao;
import com.got.util.CommonUtil;
import com.got.vo.ShippingReceivingVO;

@Service
public class SRService {
	
	@Autowired private ShippingReceivingDao dao;

	public boolean addHistory(ShippingReceivingVO sr) {
		validationCheck(sr);
		int insertedCount = dao.insertOneNewHistory(sr);
		if(insertedCount == 1) 
			return true;
		else if(insertedCount == ShippingReceivingDao.UPDATE_STOCK_FAIL) 
			return false;
		throw new RuntimeException("입출고내역 추가 실패. 객체 정보 : " + sr);
	}
	
	public static final int DETAIL_GOODS_SHOW_HISTORY_COUNT = 5;
	/**
	 * 최근 5개의 입출고내역을 가져온다.
	 * @param g_no
	 * @return
	 */
	public List<ShippingReceivingVO> getRecentHistory(int g_no) {
		return dao.selectListWithG_no(g_no, DETAIL_GOODS_SHOW_HISTORY_COUNT);
	}
	public String getRecentHistoryWithJSON(int g_no) {
		return CommonUtil.convertToJSON(getRecentHistory(g_no));
	}
	
	public void validationCheck(ShippingReceivingVO sr) {
		if(sr.getG_no() == 0)
			throw new IllegalArgumentException("상품 번호가 입력되지 않았음.");
	}
}
