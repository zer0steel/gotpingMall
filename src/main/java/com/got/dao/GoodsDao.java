package com.got.dao;


import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.apache.ibatis.transaction.TransactionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.got.dao.template.DaoTemplate;
import com.got.enums.GoodsStatus;
import com.got.vo.goods.CategoryVO;
import com.got.vo.goods.GoodsVO;
import com.got.vo.goods.StockVO;

@Repository
public class GoodsDao {
	@Autowired private DaoTemplate dao;
	
	public void update(GoodsVO g) {
		dao.update("g.update", g);
	}

	public void deleteOne(int g_no) {
		dao.delete("g.deleteOne", g_no);
	}

	public List<GoodsVO> selectAll() {
		return dao.selectList("g.selectAll");
	}
	
	public List<GoodsVO> selectListWithSmall(CategoryVO c) {
		return dao.selectList("g.selectListWithSmall",c);
	}
	
	public List<GoodsVO> selectListWithMiddle(int c_no) {
		Map<String, Object> param = new HashMap<>();
		param.put("c_no", c_no);
		param.put("status_code", GoodsStatus.FOR_SALE.getCode());
		return dao.selectList("g.selectListWithMiddle",param);
	}
	
	public List<GoodsVO> selectList(Collection<GoodsVO> collection) {
		List<GoodsVO> list = new ArrayList<>();
		dao.transactionTemplate(session -> {
			collection.forEach(vo -> {
				GoodsVO g = session.selectOne("g.selectOne", vo.getG_no());
				g.setImages(session.selectList("f.selectGoodsImg",vo.getG_no()));
				g.setStocks(vo.getStocks());
				List<StockVO> options = session.selectList("os.selectListWithOS_no", vo.getStocks());
				g.setExtraCost(options);
				list.add(g);
			});
		});
		return list;
	}
	
	public GoodsVO selectOne(int g_no) {
		return dao.selectOne("g.selectOne", g_no);
	}
}