package com.got.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	
	public int insertOneNewHistory(ShippingReceivingVO sr) {
		return dao.transactionTemplate(session -> {
			GoodsVO g = session.selectOne("g.selectOne",sr.getG_no());
			if(g.updateStock(sr)) {
				int insertedCount = session.insert("sr.insertOne", sr);
				if(insertedCount == 1) {
					int updateCount = session.update("g.updateStock", g);
					if(updateCount == 1)
						return updateCount;
					throw new TransactionException("상품 재고 업데이트 실패.");
				}
				throw new TransactionException("입출고내역 등록 실패.");
			}
			return UPDATE_STOCK_FAIL;
		});
	}
}