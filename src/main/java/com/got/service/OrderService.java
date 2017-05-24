package com.got.service;


import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.got.mapper.files.GoodsImageMapper;
import com.got.mapper.goods.GoodsMapper;
import com.got.mapper.goods.StockMapper;
import com.got.vo.OrderDetailVO;
import com.got.vo.OrderVO;
import com.got.vo.PaymentVO;
import com.got.vo.goods.GoodsVO;
import com.got.vo.goods.StockVO;

@Service
public class OrderService {
	
	@Inject GoodsMapper goodsMapper;
	@Inject GoodsImageMapper imageMapper;
	@Inject StockMapper stockMapper;
	
	@Transactional
	public OrderVO getBuyList(List<OrderDetailVO> list) {
		OrderVO o = new OrderVO();
		list.forEach(vo -> {
			System.out.println(vo);
			Integer g_no = vo.getG_no();
			GoodsVO g = goodsMapper.selectOne(g_no);
			g.setImages(imageMapper.selectList(g_no));
			StockVO s = stockMapper.selectOne(vo.getS_no());
			int totalPrice = (g.getRealPrice() + s.getExtra_cost()) * vo.getChange_amount();
			vo.setTotal_price(totalPrice);
			o.setTotal_price(o.getTotal_price() + totalPrice);
		});
		o.setDetails(list);
		return o;
		//OrderDetailVO [change_amount=3, total_price=0, goods=null, toString()=StockVO [s_no=33, g_no=173, combination=1 3, extra_cost=0]]
	}

	public void saveCheckoutInfo(PaymentVO p) {
	}
}
