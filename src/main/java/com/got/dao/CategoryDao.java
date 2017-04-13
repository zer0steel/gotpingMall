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
		return dao.selectList("gc.selectAll");
	}

}
