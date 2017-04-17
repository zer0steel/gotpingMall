package com.got.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.got.dao.CategoryDao;
import com.got.enums.MenuLevel;
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
	 * @param c
	 * @return 성공, 실패 메시지
	 */
	public String enroll(CategoryVO c) {
		validationCheck(c);
		if(dao.insertOne(c) == 1) {
			MenuLevel.addCategory(c);
			return "등록되었습니다.";
		}
		return "등록에 실패했습니다.";
	}

	/**
	 * @param c_no
	 * @return 성공, 실패 메시지
	 */
	public String delete(int c_no) {
		if( MenuLevel.isExistingSubCategory(c_no) )
			return "하위 분류가 존재해서 삭제할수 없습니다.";
		if(dao.deleteOne(c_no) == 1) {
			MenuLevel.deleteCategory(c_no);
			return "삭제되었습니다.";
		}
		return "삭제 실패했습니다.";
	}

	/**
	 * @param c
	 * @return 성공, 실패 메시지
	 */
	public String update(CategoryVO c) {
		validationCheck(c);
		if(c.getC_no() == 0)
			throw new IllegalArgumentException("분류 수정해야 하는데 PK c_no 값이 0 이다.");
		if(dao.updateOne(c) == 1) {
			MenuLevel.updateCategory(c);
			return "수정했습니다.";
		}
		return "수정실패했습니다.";
	}
	
	/**
	 * ModelAndView에 분류레벨을 저장한다.
	 * @param mav
	 * @return big, middle, small 가 저장된 ModelAndView
	 */
	public ModelAndView setEnumsInMAV(ModelAndView mav) {
		setEnum();
		mav.addObject("big", MenuLevel.BIG);
		mav.addObject("middle", MenuLevel.MIDDLE);
		mav.addObject("small", MenuLevel.SMALL);
		return mav;
	}
	
	/**
	 * Enum class Menu_level에 분류레벨별로 상세분류들을 저장한다.
	 */
	private void setEnum() {
		if( !MenuLevel.isSetting() ) 
			MenuLevel.groupingCategories(dao.selectAll());
	}
	
	private void validationCheck(CategoryVO c) {
		if(c.getTitle().equals(""))
			throw new IllegalArgumentException("c.getTitle() is empty ");
		if(c.getMenuLevel() == MenuLevel.BIG && c.getParent_no() > 0)
			throw new IllegalArgumentException("최상위 분류인데 상위분류 번호를 가지고 있음. 객체 정보 : " + c);
		if(c.getMenuLevel() != MenuLevel.BIG && c.getParent_no() == 0)
			throw new IllegalArgumentException("최상위 분류가 아닌데 부모 번호가 존재하지 않음. 객체 정보 : " + c);
	}
}
