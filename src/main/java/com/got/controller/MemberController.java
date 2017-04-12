package com.got.controller;

import java.io.IOException;
import java.security.PrivateKey;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.got.enums.Page;
import com.got.service.MemberService;
import com.got.util.RSA;
import com.got.vo.MemberVo;

@Controller
public class MemberController {
	
	@Autowired private MemberService s;
	
	@RequestMapping("login.yo")
	public ModelAndView loginForm() {
		ModelAndView mav = new ModelAndView();
		return Page.setViewPage(mav, "member/login.jsp");
	}
	
	@ResponseBody
	@RequestMapping(value = "login.yo", method = RequestMethod.POST)
	public void loginSubmit(
			HttpServletResponse res,
			HttpSession session, 
			@RequestParam(defaultValue = "") String id,
			@RequestParam(defaultValue = "") String pwd) throws IOException {
		PrivateKey privateKey = (PrivateKey)session.getAttribute(RSA.PRIVATE_KEY);
		session.removeAttribute(RSA.PRIVATE_KEY);
		MemberVo m = s.login(id, pwd, privateKey);
		if(m.isLogin()) 
			session.setAttribute("lm", m);
		res.getWriter().print(m.isLogin());
	}
	
	@RequestMapping("logout.yo")
	public ModelAndView logout(HttpSession session) {
		session.invalidate();
		ModelAndView mav = new ModelAndView();
		return Page.setViewPage(mav, "front.jsp");
	}
	
	@RequestMapping("agreement.yo")
	public ModelAndView agreeForm() {
		ModelAndView mav = new ModelAndView();
		return Page.setViewPage(mav, "member/agreement.jsp");
	}
	
	@RequestMapping("join.yo")
	public ModelAndView joinForm() {
		ModelAndView mav = new ModelAndView();
		return Page.setViewPage(mav, "member/join.jsp");
	}
	
	@RequestMapping(value = "join.yo", method = RequestMethod.POST)
	public ModelAndView joinSubmit(HttpSession session,MemberVo m) {
		ModelAndView mav = new ModelAndView();
		PrivateKey privateKey = (PrivateKey)session.getAttribute(RSA.PRIVATE_KEY);
		session.invalidate();
		if(s.joinMember(m, privateKey))
			return Page.setViewPage(mav, "member/joinComplete.jsp");
		else
			return Page.setViewPage(mav, "member/joinError.jsp");
	}
	
	@ResponseBody
	@RequestMapping("overlapCheck.yo")
	public void overlapCheck(HttpServletResponse res,
			@RequestParam(defaultValue="") String column,
			@RequestParam(defaultValue="") String value) throws IOException {
		res.getWriter().print(s.getOverlapCount(column, value));
	}
}