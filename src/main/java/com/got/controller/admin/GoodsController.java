package com.got.controller.admin;


import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.got.enums.GoodsStatus;
import com.got.enums.DealCategory;
import com.got.service.goods.CategoryService;
import com.got.service.goods.GoodsService;
import com.got.util.CommonUtil;
import com.got.util.ModelAndView;
import com.got.vo.goods.GoodsVO;

@Controller("adminGoodsController")
public class GoodsController {
	
	private static Logger log = Logger.getLogger(GoodsController.class);
	
	@Autowired private GoodsService gs;
	@Autowired private CategoryService cs;
	
	@RequestMapping(value = "admin/goods/insert.yo", method = RequestMethod.GET)
	public ModelAndView insertGoodsForm() {
		ModelAndView mav = new ModelAndView();
		return mav.setAdminViewPage("goods/insert.jsp");
	}
	
	@RequestMapping(value = "admin/goods/insert.yo", method = RequestMethod.POST)
	public ModelAndView insertGoodsSubmit(GoodsVO g) {
		gs.enroll(g);
		log.debug(g);
		return new ModelAndView("redirect:/admin/goods/insert.yo");
	}
	
	@RequestMapping("admin/goods/list.yo")
	public ModelAndView listGoods() {
		ModelAndView mav = new ModelAndView();
		mav.addObject("status", GoodsStatus.values());
		mav.addObject("goods", gs.getAll());
		return mav.setAdminViewPage("goods/list.jsp");
	}
	
	@RequestMapping("admin/goods/detail.yo")
	public ModelAndView detailGoods(Integer g_no) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("g",gs.detailAndDealHistory(g_no))
		.addObject("status", GoodsStatus.values())
		.addObject("hc", DealCategory.values());
		return mav.setAdminViewPage("goods/detail.jsp");
	}
	
	@RequestMapping(value = "admin/goods/update.yo", method = RequestMethod.POST)
	public ModelAndView updateGoods(GoodsVO g) {
		log.info(CommonUtil.getInfoString("updateGoods"));
		log.debug(g);
		gs.update(g);
		return new ModelAndView("redirect:/admin/goods/detail.yo?g_no=" + g.getG_no());
	}
	
	@RequestMapping("admin/goods/delete.yo")
	public ModelAndView deleteGoods(Integer g_no) {
		log.info("차후 만들 예정");
		return new ModelAndView("redirect:/admin/goods/list.yo");
	}
}
