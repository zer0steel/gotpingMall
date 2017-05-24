package com.got.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.got.service.goods.CategoryService;
import com.got.util.CommonUtil;
import com.got.util.ModelAndView;
import com.got.vo.goods.CategoryVO;

@Controller("adminCategoryController")
public class CategoryController {
	
	@Autowired private CategoryService cs;
	
	public static final String REDIRECT_CATEGORY_PAGE = "redirect:/admin/goods/category.yo";
	
	@RequestMapping("admin/goods/category.yo")
	public ModelAndView category() {
		ModelAndView mav = new ModelAndView();
		return mav.setAdminViewPage("goods/category.jsp");
	}
	
	@RequestMapping(value = "admin/goods/category/insert.yo", method = RequestMethod.POST)
	public ModelAndView insertCategory(CategoryVO c) {
		cs.enroll(c);
		return new ModelAndView(REDIRECT_CATEGORY_PAGE);
	}
	
	@RequestMapping(value = "admin/goods/category/delete.yo", method = RequestMethod.POST)
	public ModelAndView deteteCategory(Integer c_no) {
		cs.deleteOne(c_no);
		return new ModelAndView(REDIRECT_CATEGORY_PAGE);
	}
	
	@RequestMapping(value = "admin/goods/category/update.yo", method = RequestMethod.POST)
	public ModelAndView updateCategory(CategoryVO c) {
		ModelAndView mav = new ModelAndView();
		cs.update(c);
		mav.setViewName(REDIRECT_CATEGORY_PAGE);
		return mav;
	}
	
	@ResponseBody
	@RequestMapping(
			value = "admin/goods/category/detail.yo", 
			method = RequestMethod.POST, 
			produces = "application/json; charset=UTF-8")
	public String detailCategory(Integer c_no) {
		return CommonUtil.convertToJSON(cs.getCategoryWithOption(c_no));
	}
}
