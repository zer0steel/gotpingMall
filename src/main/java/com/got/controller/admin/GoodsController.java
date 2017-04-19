package com.got.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.got.enums.GoodsStatus;
import com.got.enums.HistoryCategory;
import com.got.enums.Page;
import com.got.service.CategoryService;
import com.got.service.GoodsService;
import com.got.util.CommonUtil;
import com.got.vo.GoodsImgVO;
import com.got.vo.GoodsVO;

@Controller
public class GoodsController {
	
	@Autowired private GoodsService gs;
	@Autowired private CategoryService cs;
	
	@RequestMapping(value = "admin/goods/insert.yo", method = RequestMethod.GET)
	public ModelAndView insertGoodsForm() {
		ModelAndView mav = cs.setEnumsInMAV(new ModelAndView());
		return Page.setAdminViewPage(mav, "goods/insert.jsp");
	}
	
	@RequestMapping(value = "admin/goods/insert.yo", method = RequestMethod.POST)
	public ModelAndView insertGoodsSubmit(GoodsVO g, String[] fileInfo) {
		gs.enroll(g, fileInfo);
		return new ModelAndView("redirect:/admin/goods/insert.yo");
	}
	
	@RequestMapping("admin/goods/list.yo")
	public ModelAndView listGoods() {
		ModelAndView mav = cs.setEnumsInMAV(new ModelAndView());
		mav.addObject("status", GoodsStatus.values());
		mav.addObject("goods", gs.getAll());
		return Page.setAdminViewPage(mav, "goods/list.jsp");
	}
	
	@ResponseBody
	@RequestMapping("admin/goods/detail.yo")
	public ModelAndView detailGoods(int g_no, String msg) {
		ModelAndView mav = new ModelAndView();
		cs.setEnumsInMAV(mav).addObject("g",gs.detailAndSRHistory(g_no));
		mav.addObject("status", GoodsStatus.values());
		mav.addObject("hc", HistoryCategory.values());
		mav.addObject("msg", msg);
		return Page.setAdminViewPage(mav, "goods/detail.jsp");
	}
	
	@RequestMapping(value = "admin/goods/update.yo", method = RequestMethod.POST)
	public ModelAndView updateGoods(GoodsVO g) {
		gs.update(g);
		return new ModelAndView("redirect:/admin/goods/detail.yo?g_no=" + g.getG_no());
	}
	
	@RequestMapping("admin/goods/delete.yo")
	public ModelAndView deleteGoods(int g_no) {
		gs.delete(g_no);
		return new ModelAndView("redirect:/admin/goods/list.yo");
	}
}