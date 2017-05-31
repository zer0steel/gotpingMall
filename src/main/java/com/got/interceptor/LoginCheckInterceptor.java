package com.got.interceptor;

import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.got.vo.member.MemberVO;

public class LoginCheckInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		MemberVO m = (MemberVO)request.getSession().getAttribute("lm");
		if(Objects.isNull(m)) {
			response.sendRedirect("");
		}
		return true;
	}
	
}
