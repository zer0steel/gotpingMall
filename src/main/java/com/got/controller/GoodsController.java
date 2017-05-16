package com.got.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.got.service.CategoryService;
import com.got.service.GoodsService;
import com.got.util.ModelAndView;
import com.got.vo.goods.CategoryVO;

@Controller
public class GoodsController {
	
	@Autowired private GoodsService gs;
	@Autowired private CategoryService cs;
	
	@RequestMapping("goods/list.yo")
	public ModelAndView list(CategoryVO c) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("goods", gs.getWithCategory(c));
		cs.setEnumsInMAV(mav);
		return mav.setViewPage("goods/list.jsp");
	}
	
	@RequestMapping("goods/detail.yo")
	public ModelAndView detail(Integer g_no) {
		ModelAndView mav = new ModelAndView();
		cs.setEnumsInMAV(mav)
		.addObject("g", gs.detail(g_no));
		return mav.setViewPage("goods/detail.jsp");
	}
	
}