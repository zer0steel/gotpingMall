package com.got.dao;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.transaction.TransactionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.got.dao.template.DaoTemplate;
import com.got.enums.GoodsStatus;
import com.got.vo.CategoryVO;
import com.got.vo.GoodsImgVO;
import com.got.vo.GoodsVO;

@Repository
public class GoodsDao {
	@Autowired private DaoTemplate dao;

	public void insert(GoodsVO g) {
		dao.transactionTemplate(session -> {
			session.insert("g.insert", g);
			
			g.getGoodsOptions().forEach(vo -> {
				vo.setG_no(g.getG_no());
				session.insert("go.insertNewOptionValue", vo);
			});
		});
	}
	
	public void insertWithImg(GoodsVO g, List<GoodsImgVO> imgs) {
		dao.transactionTemplate(session -> {
			if(session.insert("g.insert", g) != 1)
				throw new TransactionException();
				
			imgs.forEach(vo -> {
				vo.setG_no(g.getG_no());
				if(session.update("f.updatePath", vo) == -1)
					throw new TransactionException();
				if(session.insert("gi.insert", vo) == -1)
					throw new TransactionException();
			});
			
			g.getGoodsOptions().forEach(vo -> {
				vo.setG_no(g.getG_no());
				session.insert("go.insertNewOptionValue", vo);
			});
		});
	}

	public List<GoodsVO> selectAll() {
		return dao.selectList("g.selectAll");
	}
	
	public GoodsVO selectOneWithG_no(int g_no) {
		return dao.selectOne("g.selectOne", g_no);
	}

	public void update(GoodsVO g) {
		dao.update("g.update", g);
	}

	public void deleteOne(int g_no) {
		dao.delete("g.deleteOne", g_no);
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
}