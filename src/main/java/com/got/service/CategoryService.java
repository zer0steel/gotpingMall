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
	 * 새로운 분류 등록한다.
	 * @param c
	 * @return 성공, 실패 메시지
	 */
	public String enroll(CategoryVO c) {
		validationCheck(c);
		return (dao.insertOne(c) == 1) ? 
				"등록하셨습니다." : "등록에 실패했습니다.";
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
	 * 해당 번호의 분류를 삭제한다
	 * @param c_no
	 * @return 성공, 실패 메시지
	 */
	public String delete(int c_no) {
		return (dao.deleteOne(c_no) == 1) ? 
				"삭제했습니다." : "삭제에 실패했습니다.";
	}

	/**
	 * 분류 내용을 바꾼다.
	 * @param c
	 * @return 성공, 실패 메시지
	 */
	public String update(CategoryVO c) {
		validationCheck(c);
		return (dao.updateOne(c) == 1) ? 
				"수정했습니다." : "수정에 실패했습니다.";
	}
	
	private void validationCheck(CategoryVO c) {
		if(c.getTitle().equals(""))
			throw new IllegalArgumentException("c.getTitle() is empty ");
		if(c.getMenu_level() == 0 && c.getParent_no() != 0)
			throw new IllegalArgumentException("대분류인데 부모번호를 가지고 있음.");
		if(c.getMenu_level() != 0 && c.getParent_no() == 0)
			throw new IllegalArgumentException("하위분류인데 부모번호가 없음.");
	}
}
