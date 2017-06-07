package com.got.controller.admin;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.got.service.deal.DealService;
import com.got.util.ModelAndView;
import com.got.vo.deal.DealVO;

@Controller
public class DealController {
	
	@Inject private DealService dealService;
	
	@RequestMapping(value = "admin/goods/insertStock.yo", method = RequestMethod.POST)
	public ModelAndView insertSubmit(Integer g_no, DealVO deal) {
		ModelAndView mav = new ModelAndView();
		dealService.addHistoryAndStocks(deal);
		mav.setViewName("redirect:/admin/goods/detail.yo?g_no=" + g_no);
		return mav;
	}
	
	@RequestMapping(value = "admin/deal/list.yo")
	public ModelAndView list() {
		ModelAndView mav = new ModelAndView();
		mav.addObject("list", dealService.getAll());
		return mav.setAdminViewPage("deal/list.jsp");
	}
}