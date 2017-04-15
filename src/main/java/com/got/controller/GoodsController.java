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
	@Autowired private CategoryService cs;
	
	@RequestMapping(value = "admin/goods/insert.yo", method = RequestMethod.GET)
	public ModelAndView insertGoodsForm() {
		ModelAndView mav = new ModelAndView();
		mav.addObject("categories", cs.getAll());
		return Page.setAdminViewPage(mav, "goods/insert.jsp");
	}
	
	@ResponseBody
	@RequestMapping(value = "admin/goods/insert.yo", method = RequestMethod.POST)
	public void insertGoodsSubmit(HttpServletResponse res, GoodsVO g) throws IOException {
		res.getWriter().print(gs.enroll(g));
	}
	
	@RequestMapping("admin/goods/list.yo")
	public ModelAndView goodsList() {
		ModelAndView mav = new ModelAndView();
		mav.addObject("categories", cs.getAll());
		mav.addObject("goods", gs.getAll());
		return Page.setAdminViewPage(mav, "goods/list.jsp");
	}
	
	@ResponseBody
	@RequestMapping(value = "admin/goods/detail.yo", produces = "application/json; charset=UTF-8")
	public String detailGoods(int g_no) throws IOException {
		return gs.detailAndSRHistory(g_no);
	}
}
