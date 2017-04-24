package com.got.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.got.enums.Page;
import com.got.service.CategoryService;

@Controller
public class MainController {
	
	@Autowired CategoryService cs;
	
	@RequestMapping("front.yo")
	public ModelAndView front() {
		ModelAndView mav = new ModelAndView();
		cs.setEnumsInMAV(mav);
		return Page.setViewPage(mav, "front.jsp");
	}
}