package com.got.service.deal;

import java.util.List;

import javax.inject.Inject;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.got.enums.DealCategory;
import com.got.mapper.deal.DealDetailMapper;
import com.got.mapper.deal.DealMapper;
import com.got.mapper.goods.StockMapper;
import com.got.util.CommonUtil;
import com.got.vo.deal.DealVO;
import com.got.vo.deal.OrderVO;

@Service
public class DealService {
	
	@Inject private DealMapper dealMapper;
	@Inject private DealDetailMapper detailMapper;
	@Inject private StockMapper stockMapper;
	
	private Logger log = Logger.getLogger(DealService.class);
	
	public List<DealVO> getAll() {
		return dealMapper.selectAll();
	}
	
	/**
	 * @param dealVO
	 * @return d_no primary ket
	 */
	@Transactional
	public Integer addHistoryAndStocks(DealVO dealVO) {
		dealMapper.insert(dealVO);
		dealVO.getDetails().forEach(vo -> {
			vo.setup(dealVO);
			detailMapper.insert(vo.getStock().getS_no(), vo);
			stockMapper.updateStock(vo);
		});
		return dealVO.getD_no();
	}
	
	@Transactional
	public Integer addHistory(DealVO dealVO) {
		dealMapper.insert(dealVO);
		dealVO.getDetails().forEach(vo -> {
			vo.setup(dealVO);
			detailMapper.insertNotUpdateStock(vo.getStock().getS_no(), vo);
		});
		return dealVO.getD_no();
	}
	
	public Integer insertNewDeal(OrderVO o) {
		DealVO deal = new DealVO();
		deal.setEnumCategory(DealCategory.CREATE_ORDER);
		deal.setChange_amount(o.getChange_amount());
		deal.setTotal_price(o.getTotal_price());
		deal.setDetails(o.getDetails());
		return addHistory(deal);
	}
	
	public static final int DETAIL_GOODS_SHOW_HISTORY_COUNT = 5;
	/**
	 * 최근 5개의 입출고내역을 가져온다.
	 * @param g_no
	 * @return
	 */
	public List<DealVO> getRecentHistory(Integer g_no) {
		return dealMapper.selectListWithOS_no(g_no, DETAIL_GOODS_SHOW_HISTORY_COUNT);
	}
	
	public String getRecentHistoryWithJSON(int g_no) {
		return CommonUtil.convertToJSON(getRecentHistory(g_no));
	}
}
