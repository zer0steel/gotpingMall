package com.got.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.got.enums.Page;
import com.got.service.GoodsService;
import com.got.vo.GoodsVo;

@Controller
public class GoodsController {
	
	@Autowired private GoodsService gs;
	
	@RequestMapping(value = "admin/goods/insert.yo", method = RequestMethod.GET)
	public ModelAndView insertGoodsForm() {
		ModelAndView mav = new ModelAndView();
		return Page.setAdminViewPage(mav, "goods/insert.jsp");
	}
	
	@ResponseBody
	@RequestMapping(value = "admin/goods/insert.yo", method = RequestMethod.POST)
	public void insertGoodsSubmit(GoodsVo g) {
		boolean b = gs.enroll(g);
	}
}
