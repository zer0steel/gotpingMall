package com.got.service.deal;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.got.enums.DealCategory;
import com.got.enums.MileageCategory;
import com.got.enums.OrderStatus;
import com.got.mapper.deal.OrderMapper;
import com.got.mapper.deal.PaymentMapper;
import com.got.mapper.goods.GoodsMapper;
import com.got.mapper.goods.StockMapper;
import com.got.service.MileageService;
import com.got.vo.deal.DealDetailVO;
import com.got.vo.deal.DealVO;
import com.got.vo.deal.OrderVO;
import com.got.vo.deal.PaymentVO;
import com.got.vo.goods.StockVO;

@Service
public class OrderService {
	
	@Inject private DealService dealService;
	
	@Inject private GoodsMapper goodsMapper;
	@Inject private StockMapper stockMapper;
	
	@Transactional
	public OrderVO getBuyList(List<DealDetailVO> list) {
		OrderVO o = new OrderVO();
		list.forEach(vo -> {
			StockVO s = stockMapper.selectOne(vo.getStock().getS_no());
			s.setGoods(goodsMapper.selectOne(vo.getStock().getG_no()));
			
			int changeAmount = vo.getChange_amount();
			BigDecimal totalPrice = s.getRealPrice().multiply(BigDecimal.valueOf(changeAmount));
			o.addTotalPrice(totalPrice);
			o.addTotalAmount(changeAmount);
			
			vo.setStock(s);
			vo.setChange_amount(changeAmount);
		});
		o.setDetails(list);
		Integer d_no = dealService.insertNewDeal(o);
		o.setD_no(d_no);
		return o;
		//OrderDetailVO [change_amount=3, total_price=0, goods=null, toString()=StockVO [s_no=33, g_no=173, combination=1 3, extra_cost=0]]
	}

}
