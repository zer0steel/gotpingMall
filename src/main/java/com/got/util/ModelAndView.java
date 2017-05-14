package com.got.util;

public class ModelAndView extends org.springframework.web.servlet.ModelAndView {
	
	private static final String TEMPLATE = "template";
	private static final String ADMIN_TEMPLATE = "admin/template";
	
	public ModelAndView(String viewName) {
		super(viewName);
	}

	public ModelAndView() {
		super();
	}

	public ModelAndView setNoSideFrame() {
		super.addObject("noSideFrame", true);
		return this;
	}
	
	public ModelAndView setViewPage(String viewPage) {
		super.addObject("viewPage", viewPage);
		super.setViewName(TEMPLATE);
		return this;
	}
	
	public ModelAndView setAdminViewPage(String viewPage) {
		super.addObject("viewPage", viewPage);
		super.setViewName(ADMIN_TEMPLATE);
		return this;
	}
}
