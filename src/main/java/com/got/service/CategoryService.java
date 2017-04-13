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

	/**
	 * ���ο� ī�װ��� ����Ѵ�.
	 * @param c
	 * @return
	 */
	public int enroll(CategoryVO c) {
		if(c.getTitle().equals(""))
			throw new IllegalArgumentException("c.getTitle() is empty ");
		if(c.getStep() == 0 && c.getParent_no() != 0)
			throw new IllegalArgumentException("��з��ε� �θ��ȣ�� ������ ����.");
		if(c.getStep() != 0 && c.getParent_no() == 0)
			throw new IllegalArgumentException("�����з��ε� �θ��ȣ�� ����.");
		return dao.insertOne(c);
	}
}
