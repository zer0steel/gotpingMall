package com.got.controller;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.got.mapper.deal.DealMapper;
import com.got.service.goods.CategoryService;
import com.got.service.goods.GoodsService;
import com.got.util.ModelAndView;
import com.got.vo.deal.DealVO;

@Controller
public class MainController {
	
	@Inject private DealMapper mapper;
	@Inject private GoodsService goodsService;
	
	@RequestMapping("front.yo")
	public ModelAndView front() {
		ModelAndView mav = new ModelAndView();
		mav.addObject("goods", goodsService.getWeeklyBest());
		return mav.setViewPage("front.jsp");
	}
	
	@RequestMapping("test.yo")
	public ModelAndView test() {
		ModelAndView mav = new ModelAndView();
		DealVO dealVO = mapper.selectOneWithDetails(135);
		System.out.println(dealVO);
		mav.addObject("deal", dealVO);
		return mav.setViewPage("test.jsp");
	}
}