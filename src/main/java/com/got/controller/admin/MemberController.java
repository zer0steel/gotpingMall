package com.got.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.got.enums.Page;
import com.got.service.MemberService;

@Controller("adminMemberController")
public class MemberController {
	
	@Autowired private MemberService s;
	
	@RequestMapping("admin/member/list.yo")
	public ModelAndView list() {
		ModelAndView mav = new ModelAndView();
//		mav.addObject("list", s.getAll());
		return Page.setAdminViewPage(mav, "member/list.jsp");
	}
}
