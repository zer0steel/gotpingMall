package com.got.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.got.service.goods.CategoryService;
import com.got.service.goods.GoodsService;
import com.got.util.ModelAndView;

@Controller
public class GoodsController {
	
	@Inject private GoodsService goodsService;
	@Inject private CategoryService categoryService;
	
	@RequestMapping("goods/list.yo")
	public ModelAndView list(Integer c_no) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("goods", goodsService.getWithC_no(c_no));
		return mav.setViewPage("goods/list.jsp");
	}
	
	@RequestMapping("goods/detail.yo")
	public ModelAndView detail(Integer g_no) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("g", goodsService.detail(g_no));
		return mav.setViewPage("goods/detail.jsp");
	}
	
}