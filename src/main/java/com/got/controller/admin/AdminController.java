package com.got.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.got.enums.Page;

@Controller
public class AdminController {
	
	@RequestMapping("admin.yo")
	public ModelAndView admin() {
		ModelAndView mav = new ModelAndView();
		return Page.setAdminViewPage(mav, "main/dashboard.jsp");
	}

}