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
import com.got.vo.CategoryVO;
import com.got.vo.GoodsVO;

@Repository
public class GoodsDao {
	@Autowired private DaoTemplate dao;

	public void insert(GoodsVO g) {
		g.setStatus(GoodsStatus.STAND_BY);
		dao.transactionTemplate(session -> {
			session.insert("g.insert", g);
			
			if( Objects.nonNull(g.getGoodsOptions()) )
				g.getGoodsOptions().forEach(vo -> {
					vo.setG_no(g.getG_no());
					session.insert("go.insertNewOptionValue", vo);
				});
			
			if( Objects.nonNull(g.getImages()) )
				g.getImages().forEach(vo -> {
					vo.setG_no(g.getG_no());
					if(session.update("f.updatePath", vo) != 1)
						throw new TransactionException();
					if(session.insert("gi.insert", vo) != 1)
						throw new TransactionException();
				});
			
			if( Objects.nonNull(g.getOptionStocks()) )
				g.getOptionStocks().forEach(vo -> {
					vo.setG_no(g.getG_no());
					if(session.insert("os.insert", vo) != 1)
						throw new TransactionException();
				});
		});
	}
	
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
				g.setOptionStocks(vo.getOptionStocks());
				list.add(g);
			});
		});
		return list;
	}
	
	public GoodsVO selectOne(int g_no) {
		return dao.selectOne("g.selectOne", g_no);
	}
}