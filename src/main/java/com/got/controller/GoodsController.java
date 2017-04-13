package com.got.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.got.enums.Page;
import com.got.service.CategoryService;
import com.got.service.GoodsService;
import com.got.vo.GoodsVO;

@Controller
public class GoodsController {
	
	@Autowired private GoodsService gs;
	@Autowired private CategoryService gcs;
	
	@RequestMapping(value = "admin/goods/insert.yo", method = RequestMethod.GET)
	public ModelAndView insertGoodsForm() {
		ModelAndView mav = new ModelAndView();
		mav.addObject("categorys", gcs.getAll());
		return Page.setAdminViewPage(mav, "goods/insert.jsp");
	}
	
	@ResponseBody
	@RequestMapping(value = "admin/goods/insert.yo", method = RequestMethod.POST)
	public void insertGoodsSubmit(HttpServletResponse res, GoodsVO g) throws IOException {
		res.getWriter().print(gs.enroll(g));
	}
}
