package com.got.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.got.util.ModelAndView;

@Controller
public class AdminController {
	
	@RequestMapping("admin.yo")
	public ModelAndView admin() {
		ModelAndView mav = new ModelAndView();
		return mav.setAdminViewPage("main/dashboard.jsp");
	}

}