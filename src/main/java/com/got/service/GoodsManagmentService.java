package com.got.service;

import java.util.List;

import javax.inject.Inject;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.got.mapper.goods.ControlDetailMapper;
import com.got.mapper.goods.GoodsManagmentMapper;
import com.got.mapper.goods.StockMapper;
import com.got.util.CommonUtil;
import com.got.vo.GoodsManagmentVO;

@Service
public class GoodsManagmentService {
	
	@Inject private GoodsManagmentMapper managmentMapper;
	@Inject private ControlDetailMapper detailMapper;
	@Inject private StockMapper stockMapper;
	
	private Logger log = Logger.getLogger(GoodsManagmentService.class);
	
	public List<GoodsManagmentVO> getAll() {
		return managmentMapper.selectAll();
	}
	
	@Transactional
	public void addHistoryAndStocks(GoodsManagmentVO managmentVO) {
		managmentMapper.insertOne(managmentVO);
		managmentVO.getDetails().forEach(vo -> {
			vo.setup(managmentVO);
			detailMapper.insert(vo);
			stockMapper.updateStock(vo);
		});
	}
	
	public static final int DETAIL_GOODS_SHOW_HISTORY_COUNT = 5;
	/**
	 * 최근 5개의 입출고내역을 가져온다.
	 * @param g_no
	 * @return
	 */
	public List<GoodsManagmentVO> getRecentHistory(Integer g_no) {
		return managmentMapper.selectListWithOS_no(g_no, DETAIL_GOODS_SHOW_HISTORY_COUNT);
	}
	
	public String getRecentHistoryWithJSON(int g_no) {
		return CommonUtil.convertToJSON(getRecentHistory(g_no));
	}
}
