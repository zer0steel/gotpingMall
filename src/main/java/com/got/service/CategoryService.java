package com.got.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.got.dao.CategoryDao;
import com.got.dao.GoodsOptionDao;
import com.got.enums.MenuLevel;
import com.got.util.CommonUtil;
import com.got.vo.CategoryVO;

@Service
public class CategoryService {
	
	@Autowired private CategoryDao dao;
	@Autowired private GoodsOptionDao goDao;
	
	public List<CategoryVO> getAll() {
		return dao.selectAll();
	}
	
	public String getOneWithJSON(int c_no) {
		CategoryVO c = dao.selectOne(c_no);
		c.setOptions(goDao.selectListWithC_no(c_no));
		return CommonUtil.convertToJSON(c);
	}
	public void enroll(CategoryVO c) {
		validationCheck(c);
		MenuLevel.insertSetting();
		dao.insertOne(c);
	}

	public String delete(int c_no) {
		if(dao.selectSub(c_no).size() > 0) 
			return "하위분류가 존재하여 삭제할 수 없습니다.";
		dao.deleteOne(c_no);
		return "삭제되었습니다.";
	}
	
	public String update(CategoryVO c) {
		validationCheck(c);
		if(c.getC_no() == 0)
			throw new IllegalArgumentException("분류 수정해야 하는데 PK c_no 값이 0");
		dao.updateOne(c);
		return "수정했습니다.";
	}
	
	/**
	 * ModelAndView에 분류레벨을 저장한다.
	 * @param mav
	 * @return big, middle, small 이 저장된 ModelAndView
	 */
	public ModelAndView setEnumsInMAV(ModelAndView mav) {
		MenuLevel.groupingCategories(dao.selectAll());
		mav.addObject("big", MenuLevel.BIG);
		mav.addObject("middle", MenuLevel.MIDDLE);
		mav.addObject("small", MenuLevel.SMALL);
		return mav;
	}
	
	private void validationCheck(CategoryVO c) {
		if(c.getMenu_level() < MenuLevel.BIG.getCode() || c.getMenu_level() > MenuLevel.SMALL.getCode())
			throw new IllegalArgumentException("1보다 작거나 3보다 큰 분류레벨 코드가 들어왔음.");
		if(c.getSuper_no()< 0)
			throw new IllegalArgumentException("부모 분류 번호값이 음수");
		if(c.getTitle().equals(""))
			throw new IllegalArgumentException("c.getTitle() is empty ");
		if(c.getMenuLevel() == MenuLevel.BIG && c.getSuper_no() > 0)
			throw new IllegalArgumentException("최상위 분류인데 상위분류 번호를 가지고 있음. 객체 정보 : " + c);
		if(c.getMenuLevel() != MenuLevel.BIG && c.getSuper_no() == 0)
			throw new IllegalArgumentException("최상위 분류가 아닌데 부모 번호가 존재하지 않음. 객체 정보 : " + c);
	}
}
