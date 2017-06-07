package com.got.service.deal;

import java.util.List;
import java.util.Locale.Category;

import javax.inject.Inject;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.got.enums.DealCategory;
import com.got.mapper.deal.DealDetailMapper;
import com.got.mapper.deal.DealMapper;
import com.got.mapper.goods.StockMapper;
import com.got.util.JSONUtil;
import com.got.vo.deal.DealDetailVO;
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
	
	@Transactional
	public Integer addHistoryAndStocks(DealVO dealVO) {
		dealMapper.insert(dealVO);
		List<DealDetailVO> details = dealVO.getDetails();
		if(details.size() == 1) {
			DealDetailVO detail = details.get(0);
			detail.setChange_amount(dealVO.getTotal_change_amount());
			insertAndUpdateStock(detail, dealVO);
		}
		else{
			int total_amount = 0;
			for(DealDetailVO detail : details) {
				total_amount += detail.getChange_amount();
				insertAndUpdateStock(detail, dealVO);
			}
			if(dealVO.getTotal_change_amount() != total_amount)
				throw new IllegalArgumentException("입고 수량이 서로 다름 : dealVO.total_change_amount = " + dealVO.getTotal_change_amount() + " || detail 합계 = " + total_amount);
		}
		
		return dealVO.getD_no();
	}
	
	@Transactional
	private void insertAndUpdateStock(DealDetailVO detail, DealVO dealVO) {
		detail.setup(dealVO);
		detail.setUnit_price(detail.getStock().getRealPrice());
		detailMapper.insert(detail.getStock().getS_no(), detail);
		stockMapper.updateStock(detail);
	}
	
	@Transactional
	public Integer addHistory(DealVO dealVO) {
		dealMapper.insert(dealVO);
		dealVO.getDetails().forEach(vo -> {
			vo.setD_no(dealVO.getD_no());
			vo.setUnit_price(vo.getStock().getRealPrice());
			detailMapper.insertNotUpdateStock(vo.getStock().getS_no(), vo);
		});
		return dealVO.getD_no();
	}
	
	public Integer insertNewDeal(OrderVO o) {
		DealVO deal = new DealVO();
		deal.setEnumCategory(DealCategory.CREATE_ORDER);
		deal.setTotal_change_amount(o.getTotal_change_amount());
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
		return dealMapper.selectListWithOS_no(g_no, DETAIL_GOODS_SHOW_HISTORY_COUNT, DealCategory.CREATE_ORDER);
	}
	
	public String getRecentHistoryWithJSON(int g_no) {
		return JSONUtil.convertToJSON(getRecentHistory(g_no));
	}
	
	@Transactional
	public DealVO updateStock(DealVO d, DealCategory category) {
		d.setEnumCategory(category);
		dealMapper.updateOne(d);
		d.getDetails().forEach(vo -> {
			log.debug(vo);
			vo.setup(d);
			detailMapper.updateStock(vo, vo.getStock().getS_no());
			stockMapper.updateStock(vo);
		});
		return d;
	}
}
