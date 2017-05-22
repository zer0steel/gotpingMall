package com.got.controller.admin;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.got.service.goods.GoodsManagmentService;
import com.got.util.ModelAndView;
import com.got.vo.goods.GoodsManagmentVO;

@Controller
public class GoodsManagmentController {
	
	@Inject private GoodsManagmentService managmentService;
	
	@RequestMapping(value = "admin/goods/insertStock.yo", method = RequestMethod.POST)
	public ModelAndView insertSubmit(Integer g_no, GoodsManagmentVO managmentVO) {
		ModelAndView mav = new ModelAndView();
		managmentService.addHistoryAndStocks(managmentVO);
		mav.setViewName("redirect:/admin/goods/detail.yo?g_no=" + g_no);
		return mav;
	}
}