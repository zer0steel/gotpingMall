package com.got.dao;


import java.util.List;

import org.apache.ibatis.transaction.TransactionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.got.dao.template.DaoTemplate;
import com.got.vo.GoodsImgVO;
import com.got.vo.GoodsVO;

@Repository
public class GoodsDao {
	@Autowired private DaoTemplate dao;

	public void insert(GoodsVO g) {
		dao.insert("g.insert", g);
	}
	
	public void insertWithImg(GoodsVO g, List<GoodsImgVO> imgs) {
		dao.transactionTemplate(session -> {
			if(session.insert("g.insert", g) != 1)
				throw new TransactionException();
				
			for(GoodsImgVO img : imgs) {
				img.setG_no(g.getG_no());
				if(session.update("f.updatePath", img) == -1)
					throw new TransactionException();
				if(session.insert("gi.insert", img) == -1)
					throw new TransactionException();
			}
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
}