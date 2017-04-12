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
public class AdminController {
	
	@RequestMapping("admin.yo")
	public ModelAndView admin() {
		ModelAndView mav = new ModelAndView();
		return Page.setAdminViewPage(mav, "main/dashboard.jsp");
	}
	
	@RequestMapping("admin/goods/list.yo")
	public ModelAndView goodsList() {
		ModelAndView mav = new ModelAndView();
		return Page.setAdminViewPage(mav, "goods/list.jsp");
	}
}