package com.got.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.got.dao.template.DaoTemplate;
import com.got.vo.CategoryVO;

@Repository
public class CategoryDao {

	@Autowired private DaoTemplate dao;
	
	public List<CategoryVO> selectAll() {
		return dao.selectList("c.selectAll");
	}

	public int insertOne(CategoryVO c) {
		return dao.insert("c.insert", c);
	}

	public CategoryVO selectOne(int c_no) {
		return dao.selectOne("c.selectOne", c_no);
	}

	public int deleteOne(int c_no) {
		return dao.delete("c.deleteOne", c_no);
	}

	public int updateOne(CategoryVO c) {
		return dao.update("c.updateOne", c);
	}

	public List<CategoryVO> selectSub(int c_no) {
		return dao.selectList("c.sub", c_no);
	}
}
