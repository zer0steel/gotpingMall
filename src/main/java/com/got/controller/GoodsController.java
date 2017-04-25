package com.got.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.got.enums.Page;
import com.got.service.CategoryService;
import com.got.service.GoodsService;
import com.got.vo.CategoryVO;

@Controller
public class GoodsController {
	
	@Autowired private GoodsService gs;
	@Autowired CategoryService cs;
	
	@RequestMapping("goods/list.yo")
	public ModelAndView list(CategoryVO c) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("goods", gs.getWithCategory(c));
		cs.setEnumsInMAV(mav);
		return Page.setViewPage(mav, "goods/list.jsp");
	}
	
	@RequestMapping("goods/detail.yo")
	public ModelAndView list(int g_no) {
		ModelAndView mav = new ModelAndView();
		cs.setEnumsInMAV(mav)
		.addObject("g", gs.detail(g_no));
		return Page.setViewPage(mav, "goods/detail.jsp");
	}
}