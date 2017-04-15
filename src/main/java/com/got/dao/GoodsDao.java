package com.got.dao;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.got.dao.template.DaoTemplate;
import com.got.vo.GoodsVO;

@Repository
public class GoodsDao {
	@Autowired private DaoTemplate dao;

	public int insert(GoodsVO g) {
		return dao.insert("g.insert", g);
	}

	public List<GoodsVO> selectAll() {
		return dao.selectList("g.selectAll");
	}
	
	public GoodsVO selectOneWithG_no(int g_no) {
		return dao.selectOne("g.selectOne", g_no);
	}
}