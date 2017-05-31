package com.got.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.got.service.goods.CategoryService;
import com.got.util.JSONUtil;

@Controller
public class CategoryController {
	
	@Inject private CategoryService categoryService;
	
	@ResponseBody
	@RequestMapping(value = "goods/category.yo", produces = "application/json; charset=UTF-8")
	public String getCategories() {
		return JSONUtil.convertToJSON(categoryService.getLevels());
	}
	
	@ResponseBody
	@RequestMapping(value = "goods/category/subList.yo", produces = "application/json; charset=UTF-8")
	public String getSubList(Integer c_no) {
		return JSONUtil.convertToJSON(categoryService.getSubList(c_no));
	}
}
