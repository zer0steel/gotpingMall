package com.got.interceptor;

import java.util.Objects;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.got.enums.Grade;
import com.got.mapper.VisitStatsMapper;
import com.got.service.VisitStatsService;
import com.got.service.goods.CategoryService;
import com.got.vo.member.MemberVO;

public class AdminCheckInterceptor extends HandlerInterceptorAdapter {


	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		MemberVO m = (MemberVO)request.getSession().getAttribute("lm");
		if(Objects.nonNull(m) && m.getEnumGrade() == Grade.ADMIN)
			return true;
		else {
			request.getRequestDispatcher("front.yo").forward(request, response);
			return false;
		}
	}
}
