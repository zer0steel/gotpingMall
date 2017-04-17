package com.got.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.got.enums.Page;
import com.got.service.CategoryService;
import com.got.vo.CategoryVO;

@Controller
public class CategoryController {
	
	@Autowired private CategoryService cs;
	
	private static final String REDIRECT_CATEGORY_PAGE = "redirect:/admin/goods/category.yo";
	
	@RequestMapping("admin/goods/category.yo")
	public ModelAndView category(String msg) {
		ModelAndView mav = new ModelAndView();
		cs.setEnumsInMAV(mav).addObject("msg", msg);
		return Page.setAdminViewPage(mav, "goods/category.jsp");
	}
	
	@RequestMapping(value = "admin/goods/category/insert.yo", method = RequestMethod.POST)
	public ModelAndView insertCategory(CategoryVO c) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("msg", cs.enroll(c));
		mav.setViewName(REDIRECT_CATEGORY_PAGE);
		return mav;
	}
	
	@RequestMapping(value = "admin/goods/category/delete.yo", method = RequestMethod.POST)
	public ModelAndView deteteCategory(int c_no) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("msg", cs.delete(c_no));
		mav.setViewName(REDIRECT_CATEGORY_PAGE);
		return mav;
	}
	
	@RequestMapping(value = "admin/goods/category/update.yo", method = RequestMethod.POST)
	public ModelAndView updateCategory(CategoryVO c) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("msg", cs.update(c));
		mav.setViewName(REDIRECT_CATEGORY_PAGE);
		return mav;
	}
	
	@ResponseBody
	@RequestMapping(
			value = "admin/goods/category/detail.yo", 
			method = RequestMethod.POST, 
			produces = "application/json; charset=UTF-8")
	public String detailCategory(int c_no) {
		return cs.getOneWithJSON(c_no);
	}
}
