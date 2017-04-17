package com.got.enums;

import org.springframework.web.servlet.ModelAndView;

public enum Page {
	TEMPLAGE("template"), ADMIN_TEMPLATE("admin/template");
	
	private String page;
	
	Page(String page) {
		this.page = page;
	}
	
	public static ModelAndView setNoSideFrame(ModelAndView mav) {
		mav.addObject("noSideFrame", true);
		return mav;
	}
	
	public static ModelAndView setViewPage(ModelAndView mav,String viewPage) {
		mav.addObject("viewPage", viewPage);
		mav.setViewName(TEMPLAGE.page);
		return mav;
	}
	
	public static ModelAndView setAdminViewPage(ModelAndView mav,String viewPage) {
		mav.addObject("viewPage", viewPage);
		mav.setViewName(ADMIN_TEMPLATE.page);
		return mav;
	}
}
