package com.got.service;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.got.dao.ShippingReceivingDao;
import com.got.util.CommonUtil;
import com.got.vo.goods.GoodsVO;
import com.got.vo.goods.OptionStockVO;
import com.got.vo.goods.ShippingReceivingVO;

@Service
public class SRService {
	
	@Autowired private ShippingReceivingDao dao;
	
	@Autowired private GoodsService gs;
	
	public List<ShippingReceivingVO> getAll() {
		return dao.selectAll();
	}

	public void addHistoryAndStocks(ShippingReceivingVO sr, String[] optionStocksJSON) {
		GoodsVO g = gs.updateStock(sr.getG_no(), sr.getAmount());
		sr.setChange_stock(g.getStock());
		
		if( Objects.nonNull(optionStocksJSON) ) 
			g.setOptionStocks(CommonUtil.getVO(optionStocksJSON, OptionStockVO.class));
		
		dao.insertHistoryAndStocks(sr, g);
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
}
