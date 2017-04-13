package com.got.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.got.dao.CategoryDao;
import com.got.dao.GoodsDao;
import com.got.vo.CategoryVO;
import com.got.vo.GoodsVO;

@Service
public class CategoryService {
	
	@Autowired private CategoryDao dao;
	
	public List<CategoryVO> getAll() {
		return dao.selectAll();
	}
}
