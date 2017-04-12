package com.got.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.got.dao.MemberDao;
import com.got.enums.Page;

@Controller
public class MainController {

	@Autowired private MemberDao dao;
	
	@RequestMapping("front.yo")
	public ModelAndView front() {
		ModelAndView mav = new ModelAndView();
		return Page.setViewPage(mav, "front.jsp");
	}
}
