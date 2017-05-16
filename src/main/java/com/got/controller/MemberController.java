package com.got.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.PrivateKey;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.got.enums.Grade;
import com.got.service.GoodsService;
import com.got.service.MemberService;
import com.got.util.CommonUtil;
import com.got.util.ModelAndView;
import com.got.util.RSA;
import com.got.vo.MemberVO;
import com.got.vo.goods.GoodsVO;
import com.got.vo.list.OptionStockListContainer;

@Controller
public class MemberController {
	
	@Autowired private MemberService s;
	
	@RequestMapping("loginTest.yo")
	public ModelAndView loginTest(HttpSession session) {
		ModelAndView mav = new ModelAndView();
		MemberVO m = new MemberVO();
		m.setId("admin");
		m.setEnumGrade(Grade.ADMIN);
		session.setAttribute("lm", m);
		mav.setViewName("redirect:/front.yo");
		return mav;
	}
	
	@RequestMapping("login.yo")
	public ModelAndView loginForm() {
		ModelAndView mav = new ModelAndView();
		mav.setNoSideFrame();
		return mav.setViewPage("member/login.jsp");
	}
	
	@RequestMapping("purchaseLogin.yo")
	public ModelAndView purchaseLoginForm(HttpServletResponse res, OptionStockListContainer container) throws UnsupportedEncodingException {
		String jsonString = CommonUtil.convertToJSON(container.getList());
		Cookie c = new Cookie("buyList", URLEncoder.encode(jsonString, "UTF-8"));
		res.addCookie(c);
		
		ModelAndView mav = new ModelAndView();
		mav.setNoSideFrame();
		return mav.setViewPage("member/purchaseLogin.jsp");
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
		MemberVO m = s.login(id, pwd, privateKey);
		if(m.isLoginSuccess()) 
			session.setAttribute("lm", m);
		res.getWriter().print(m.isLoginSuccess());
	}
	
	@RequestMapping("logout.yo")
	public ModelAndView logout(HttpSession session) {
		session.invalidate();
		return new ModelAndView("redirect:/front.yo");
	}
	
	@RequestMapping("agreement.yo")
	public ModelAndView agreeForm() {
		ModelAndView mav = new ModelAndView();
		mav.setNoSideFrame();
		return mav.setViewPage("member/agreement.jsp");
	}
	
	@RequestMapping("join.yo")
	public ModelAndView joinForm() {
		ModelAndView mav = new ModelAndView();
		mav.setNoSideFrame();
		return mav.setViewPage("member/join.jsp");
	}
	
	@RequestMapping(value = "join.yo", method = RequestMethod.POST)
	public ModelAndView joinSubmit(HttpSession session,MemberVO m) {
		PrivateKey privateKey = (PrivateKey)session.getAttribute(RSA.PRIVATE_KEY);
		session.invalidate();
		
		s.join(m, privateKey);
		ModelAndView mav = new ModelAndView();
		return mav.setViewPage("member/joinComplete.jsp");
	}
	
	@ResponseBody
	@RequestMapping("overlapCheck.yo")
	public void overlapCheck(HttpServletResponse res,
			@RequestParam(defaultValue="") String column,
			@RequestParam(defaultValue="") String value) throws IOException {
		res.getWriter().print(s.getOverlapCount(column, value));
	}
}