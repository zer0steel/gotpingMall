package com.got.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.got.dao.CategoryDao;
import com.got.enums.Menu_level;
import com.got.util.CommonUtil;
import com.got.vo.CategoryVO;

@Service
public class CategoryService {
	
	@Autowired private CategoryDao dao;
	
	public List<CategoryVO> getAll() {
		return dao.selectAll();
	}
	
	public String getOneWithJSON(int c_no) {
		return CommonUtil.convertToJSON(dao.selectOne(c_no));
	}
	
	/**
	 * 새로운 분류를 등록한다.
	 * @param c
	 * @return 성공, 실패 메시지
	 */
	public String enroll(CategoryVO c) {
		validationCheck(c);
		if(dao.insertOne(c) == 1) {
			Menu_level.addCategory(c);
			return "등록하셨습니다.";
		}
		return "등록에 실패했습니다.";
	}

	/**
	 * 해당 번호의 분류를 삭제한다
	 * @param c_no
	 * @return 성공, 실패 메시지
	 */
	public String delete(int c_no) {
		if(dao.deleteOne(c_no) == 1) {
			Menu_level.deleteCategory(c_no);
			return "삭제했습니다.";
		}
		return "삭제에 실패했습니다.";
	}

	/**
	 * 분류 내용을 바꾼다.
	 * @param c
	 * @return 성공, 실패 메시지
	 */
	public String update(CategoryVO c) {
		validationCheck(c);
		if(c.getC_no() == 0)
			throw new IllegalArgumentException("분류 업데이트에서 PK값인 c_no 가 값이 0임");
		if(dao.updateOne(c) == 1) {
			Menu_level.updateCategory(c);
			return "수정했습니다.";
		}
		return "수정에 실패했습니다.";
	}
	
	/**
	 * ModelAndView에 분류레벨들을 세팅한다.
	 * @param mav
	 * @return big, middle, small 값이 등록된 ModelAndView
	 */
	public ModelAndView setEnumsInMAV(ModelAndView mav) {
		setEnum();
		mav.addObject("big", Menu_level.BIG);
		mav.addObject("middle", Menu_level.MIDDLE);
		mav.addObject("small", Menu_level.SMALL);
		return mav;
	}
	
	private static boolean isSetting = false;
	/**
	 * Enum class Menu_level 에 데이터 베이스에 존재하는 분류들을 분류레벨에 맞춰서 분류한다.
	 */
	private void setEnum() {
		if(!isSetting) {
			Menu_level.groupingCategories(dao.selectAll());
			isSetting = true;
		}
	}
	
	private void validationCheck(CategoryVO c) {
		if(c.getTitle().equals(""))
			throw new IllegalArgumentException("c.getTitle() is empty ");
		if(c.getMenuLevel() == Menu_level.BIG && c.getParent_no() > 0)
			throw new IllegalArgumentException("대분류인데 부모번호를 가지고 있음.");
		if(c.getMenuLevel() != Menu_level.BIG && c.getParent_no() == 0)
			throw new IllegalArgumentException("하위분류인데 부모번호가 없음.");
	}
}
