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
		this.addObject("noSideFrame", true);
		return this;
	}
	
	public ModelAndView setViewPage(String viewPage) {
		this.addObject("viewPage", viewPage);
		this.setViewName(TEMPLATE);
		return this;
	}
	
	public ModelAndView setAdminViewPage(String viewPage) {
		this.addObject("viewPage", viewPage);
		this.setViewName(ADMIN_TEMPLATE);
		return this;
	}
}
