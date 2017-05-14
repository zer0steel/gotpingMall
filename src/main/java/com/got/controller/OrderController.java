package com.got.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.got.service.GoodsService;
import com.got.util.ModelAndView;
import com.got.vo.OptionStockVO;

@Controller
public class OrderController {
	
	@Autowired GoodsService gs;
	
	@RequestMapping("order/form.yo")
	public ModelAndView purchaseForm(OptionStockVO osList) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("goods", gs.getList(osList.getList()));
		mav.setNoSideFrame();
		return mav.setViewPage("order/form.jsp");
	}
}