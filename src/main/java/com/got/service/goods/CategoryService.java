package com.got.service.goods;

import java.util.List;
import java.util.Objects;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.got.enums.MenuLevel;
import com.got.mapper.goods.CategoryMapper;
import com.got.mapper.goods.OptionMapper;
import com.got.util.CommonUtil;
import com.got.vo.goods.CategoryVO;

@Service
public class CategoryService {
	
	@Inject private CategoryMapper categoryMapper;
	@Inject private OptionMapper optionMapper;
	
	public List<CategoryVO> getAll() {
		return categoryMapper.selectAll();
	}
	
	public String getCategoryToJSON(Integer c_no) {
		CategoryVO c = categoryMapper.selectOne(c_no);
		c.setOptions(optionMapper.selectListWithC_no(c_no));
		return CommonUtil.convertToJSON(c);
	}
	
	public void enroll(CategoryVO c) {
		validationCheck(c);
		MenuLevel.insertSetting();
		categoryMapper.insert(c);
	}

	public void delete(Integer c_no) {
		Objects.requireNonNull(c_no);
		categoryMapper.deleteOne(c_no);
	}
	
	public void update(CategoryVO c) {
		validationCheck(c);
		Objects.requireNonNull(c.getC_no());
		categoryMapper.updateOne(c);
	}
	
	/**
	 * ModelAndView에 분류레벨을 저장한다.
	 * @param mav
	 * @return big, middle, small 이 저장된 ModelAndView
	 */
	public com.got.util.ModelAndView setEnumsInMAV(com.got.util.ModelAndView mav) {
		MenuLevel.groupingCategories(categoryMapper.selectAll());
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
