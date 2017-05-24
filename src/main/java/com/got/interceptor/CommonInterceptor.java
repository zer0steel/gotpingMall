package com.got.interceptor;

import java.util.Objects;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.got.service.goods.CategoryService;

public class CommonInterceptor extends HandlerInterceptorAdapter {

	@Inject private CategoryService categoryService;

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		if(Objects.nonNull(modelAndView))
			modelAndView.addObject("categories", categoryService.getCategories());
		super.postHandle(request, response, handler, modelAndView);
	}
}
