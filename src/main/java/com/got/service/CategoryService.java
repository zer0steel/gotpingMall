package com.got.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
	 * ���ο� �з� ����Ѵ�.
	 * @param c
	 * @return ����, ���� �޽���
	 */
	public String enroll(CategoryVO c) {
		validationCheck(c);
		return (dao.insertOne(c) == 1) ? 
				"����ϼ̽��ϴ�." : "��Ͽ� �����߽��ϴ�.";
	}

	public String getOneWithJSON(int c_no) {
		ObjectMapper mapper = new ObjectMapper();
		String jsonString = null;
		try {
			jsonString = mapper.writeValueAsString(dao.selectOne(c_no));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return jsonString;
	}

	/**
	 * �ش� ��ȣ�� �з��� �����Ѵ�
	 * @param c_no
	 * @return ����, ���� �޽���
	 */
	public String delete(int c_no) {
		return (dao.deleteOne(c_no) == 1) ? 
				"�����߽��ϴ�." : "������ �����߽��ϴ�.";
	}

	/**
	 * �з� ������ �ٲ۴�.
	 * @param c
	 * @return ����, ���� �޽���
	 */
	public String update(CategoryVO c) {
		validationCheck(c);
		return (dao.updateOne(c) == 1) ? 
				"�����߽��ϴ�." : "������ �����߽��ϴ�.";
	}
	
	private void validationCheck(CategoryVO c) {
		if(c.getTitle().equals(""))
			throw new IllegalArgumentException("c.getTitle() is empty ");
		if(c.getMenu_level() == 0 && c.getParent_no() != 0)
			throw new IllegalArgumentException("��з��ε� �θ��ȣ�� ������ ����.");
		if(c.getMenu_level() != 0 && c.getParent_no() == 0)
			throw new IllegalArgumentException("�����з��ε� �θ��ȣ�� ����.");
	}
}
