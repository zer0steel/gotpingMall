package com.got.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.got.dao.template.DaoTemplate;
import com.got.mapper.files.GoodsImageMapper;
import com.got.mapper.goods.GoodsMapper;
import com.got.mapper.goods.StockMapper;
import com.got.vo.OrderDetailVO;
import com.got.vo.OrderVO;
import com.got.vo.goods.GoodsVO;

@Repository
public class OrderDao {
	
	@Autowired private DaoTemplate dao;
	
	public OrderVO createOrderVO(List<OrderDetailVO> list) {
		OrderVO o = new OrderVO();
		dao.transactionTemplate(session -> {
			list.forEach(vo -> {
				Integer g_no = vo.getG_no();
				GoodsVO g = session.getMapper(GoodsMapper.class).selectOne(g_no);
				g.setImages(session.getMapper(GoodsImageMapper.class).selectList(g_no));
				vo.setStock(session.getMapper(StockMapper.class).selectOne(vo.getS_no()));
				vo.setGoods(g);
				int totalPrice = (g.getRealPrice() + vo.getExtra_cost()) * vo.getAmount();
				vo.setTotal_price(totalPrice);
				o.setTotal_price(o.getTotal_price() + totalPrice);
			});
		});
		o.setDetails(list);
		return o;
	}

}
