package com.got.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.got.enums.Page;

@Controller
public class MainController {
	
	@RequestMapping("front.yo")
	public ModelAndView front() {
		ModelAndView mav = new ModelAndView();
		return Page.setViewPage(mav, "front.jsp");
	}
}