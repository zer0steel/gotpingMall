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

	public int insert(GoodsVO g) {
		return dao.insert("g.insert", g);
	}
	
	public int insertWithImg(GoodsVO g, List<GoodsImgVO> imgs) {
		return dao.transactionTemplate(session -> {
			int insertedCount = session.insert("g.insert", g);
			if(insertedCount == 1) {
				for(GoodsImgVO img : imgs) {
					img.setG_no(g.getG_no());
					if(session.insert("gi.insert", img) == -1)
						throw new TransactionException();
					insertedCount += 1;
				}
				return insertedCount;
			}
			else
				throw new TransactionException();
		});
	}

	public List<GoodsVO> selectAll() {
		return dao.selectList("g.selectAll");
	}
	
	public GoodsVO selectOneWithG_no(int g_no) {
		return dao.selectOne("g.selectOne", g_no);
	}

	public int update(GoodsVO g) {
		return dao.update("g.update", g);
	}

	public int deleteOne(int g_no) {
		return dao.delete("g.deleteOne", g_no);
	}
}