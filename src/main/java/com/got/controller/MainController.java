package com.got.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.got.service.goods.CategoryService;
import com.got.util.ModelAndView;

@Controller
public class MainController {
	
	@Autowired CategoryService cs;
	
	@RequestMapping("front.yo")
	public ModelAndView front() {
		ModelAndView mav = new ModelAndView();
		cs.setEnumsInMAV(mav);
		return mav.setViewPage("front.jsp");
	}
}