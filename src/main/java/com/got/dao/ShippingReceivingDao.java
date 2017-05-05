package com.got.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.apache.ibatis.transaction.TransactionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.got.dao.template.DaoTemplate;
import com.got.vo.GoodsVO;
import com.got.vo.ShippingReceivingVO;

@Repository
public class ShippingReceivingDao {
	
	@Autowired private DaoTemplate dao;
	
	public List<ShippingReceivingVO> selectAll() {
		return dao.selectList("sr.selectAll");
	}
	
	public List<ShippingReceivingVO> selectListWithG_no(int g_no) {
		return selectListWithG_no(g_no, null);
	}
	
	public List<ShippingReceivingVO> selectListWithG_no(int g_no, Integer count) {
		Map<String, Integer> param = new HashMap<>();
		param.put("g_no", g_no);
		param.put("count", count);
		return dao.selectList("sr.selectWithG_no", param);
	}
	
	public static final int UPDATE_STOCK_FAIL = -2;
	
	public void insertHistoryAndStocks(ShippingReceivingVO sr, GoodsVO g) {
		dao.transactionTemplate(session -> {
			if(session.insert("sr.insertOne", sr) != 1)
				throw new TransactionException();
			
			if(session.update("g.updateStock", g) != 1)
				throw new TransactionException();
			
			System.out.println(g.getOptionStocks());
			if( Objects.nonNull(g.getOptionStocks()) ) {
				g.getOptionStocks().forEach(o -> {
					Objects.requireNonNull(o.getOs_no());
					if(o.getOs_stock() != 0)
						session.update("os.updateStock", o);
				});
			}
		});
	}
}