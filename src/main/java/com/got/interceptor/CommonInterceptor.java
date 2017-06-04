package com.got.interceptor;

import java.util.Objects;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.got.mapper.VisitStatsMapper;
import com.got.service.VisitStatsService;
import com.got.service.goods.CategoryService;

public class CommonInterceptor extends HandlerInterceptorAdapter {

	@Inject private CategoryService categoryService;
	@Inject private VisitStatsService statsService;
	
	private static final String LOCAL_HOST = "0:0:0:0:0:0:0:1";

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = request.getSession();
		Object newVisitor = session.getAttribute("new");
		if(Objects.nonNull(newVisitor)) {
			session.removeAttribute("new");
			if(!LOCAL_HOST.equals(request.getRemoteAddr()))
				statsService.updateCount();
		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		if(Objects.nonNull(modelAndView))
			modelAndView.addObject("categories", categoryService.getCategories());
		super.postHandle(request, response, handler, modelAndView);
	}
}
