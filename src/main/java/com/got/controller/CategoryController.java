package com.got.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.got.enums.Page;
import com.got.service.CategoryService;
import com.got.service.GoodsService;
import com.got.vo.CategoryVO;
import com.got.vo.GoodsVO;

@Controller
public class CategoryController {
	
	@Autowired private CategoryService gcs;
	
	@RequestMapping("admin/goods/category.yo")
	public ModelAndView category() {
		ModelAndView mav = new ModelAndView();
		mav.addObject("categories", gcs.getAll());
		return Page.setAdminViewPage(mav, "goods/category.jsp");
	}
	
	@RequestMapping(value = "admin/goods/insertCategory.yo", method = RequestMethod.POST)
	public ModelAndView insertCategorySubmit(CategoryVO c) {
		int re = gcs.enroll(c);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:/admin/goods/category.yo");
		return mav;
	}
}
