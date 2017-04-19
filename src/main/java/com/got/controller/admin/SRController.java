package com.got.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.got.enums.Page;
import com.got.service.GoodsService;
import com.got.service.SRService;
import com.got.util.CommonUtil;
import com.got.vo.ShippingReceivingVO;

@Controller
public class SRController {
	
	@Autowired private SRService s;
	@Autowired private GoodsService gs;
	
	@RequestMapping(value = "admin/goods/sr/insert.yo", method = RequestMethod.POST)
	public ModelAndView insertSubmit(ShippingReceivingVO sr) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("msg", s.addHistory(sr));
		mav.setViewName("redirect:/admin/goods/detail.yo?g_no=" + sr.getG_no());
		return mav;
	}
	
	@RequestMapping(value = "admin/goods/sr/list.yo")
	public ModelAndView list() {
		ModelAndView mav = new ModelAndView();
		mav.addObject("list", s.getAll());
		return Page.setAdminViewPage(mav, "goods/srList.jsp");
	}
}