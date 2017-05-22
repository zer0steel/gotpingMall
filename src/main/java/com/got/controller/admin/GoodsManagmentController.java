package com.got.controller.admin;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.got.service.GoodsManagmentService;
import com.got.util.ModelAndView;
import com.got.vo.GoodsManagmentVO;
import com.got.vo.list.StockListContainer;

@Controller
public class GoodsManagmentController {
	
	@Inject private GoodsManagmentService s;
	
	@RequestMapping(value = "admin/goods/insertStock.yo", method = RequestMethod.POST)
	public ModelAndView insertSubmit(Integer g_no, GoodsManagmentVO managmentVO) {
		ModelAndView mav = new ModelAndView();
		s.addHistoryAndStocks(managmentVO);
		mav.setViewName("redirect:/admin/goods/detail.yo?g_no=" + g_no);
		return mav;
	}
	
	@RequestMapping(value = "admin/goods/sr/list.yo")
	public ModelAndView list() {
		ModelAndView mav = new ModelAndView();
		mav.addObject("list", s.getAll());
		return mav.setAdminViewPage("goods/srList.jsp");
	}
}