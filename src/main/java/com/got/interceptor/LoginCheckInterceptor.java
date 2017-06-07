package com.got.interceptor;

import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.got.vo.member.MemberVO;

public class LoginCheckInterceptor extends HandlerInterceptorAdapter {
	
	private static final Map<String, String> MAP = new HashMap<>();
	static {
		MAP.put("/controller/order/orderList.yo", "/login.yo?uri=orderList.yo&jspPage=member/orderListLogin.jsp");
		MAP.put("/controller/order/form.yo", "/purchaseLogin.yo");
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		MemberVO m = (MemberVO)request.getSession().getAttribute("lm");
		if(Objects.isNull(m)) {
			if(Objects.nonNull(request.getParameter("noMember")))
				return true;
			
			request.getRequestDispatcher(MAP.get(request.getRequestURI())).forward(request, response);
			return false;
		}
		return true;
	}
	
}
