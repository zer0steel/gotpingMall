package com.got.service;


import java.util.List;


import org.springframework.stereotype.Service;

import com.got.vo.OrderDetailVO;
import com.got.vo.OrderVO;
import com.got.vo.PaymentVO;

@Service
public class OrderService {
	
	public OrderVO getBuyList(List<OrderDetailVO> list) {
		return null;
//		OrderVO o = new OrderVO();
//		dao.transactionTemplate(session -> {
//			list.forEach(vo -> {
//				Integer g_no = vo.getG_no();
//				GoodsVO g = session.getMapper(GoodsMapper.class).selectOne(g_no);
//				g.setImages(session.getMapper(GoodsImageMapper.class).selectList(g_no));
//				vo.setStock(session.getMapper(StockMapper.class).selectOne(vo.getS_no()));
//				vo.setGoods(g);
//				int totalPrice = (g.getRealPrice() + vo.getExtra_cost()) * vo.getAmount();
//				vo.setTotal_price(totalPrice);
//				o.setTotal_price(o.getTotal_price() + totalPrice);
//			});
//		});
//		o.setDetails(list);
//		return o;
	}

	public void saveCheckoutInfo(PaymentVO p) {
	}
}
