package com.got.controller.admin;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.got.enums.Page;
import com.got.service.GoodsOptionService;
import com.got.service.GoodsService;
import com.got.service.SRService;
import com.got.util.CommonUtil;
import com.got.vo.GoodsOptionVO;
import com.got.vo.ShippingReceivingVO;

@Controller
public class SRController {
	
	@Autowired private SRService s;
	@Autowired private GoodsService gs;
	@Autowired private GoodsOptionService gos;
	
	@RequestMapping(value = "admin/goods/sr/insert.yo", method = RequestMethod.POST)
	public ModelAndView insertSubmit(ShippingReceivingVO sr, String[] goodsOptions) {
		if(goodsOptions != null) {
			List<GoodsOptionVO> options = CommonUtil.getVO(goodsOptions, GoodsOptionVO.class);
			gos.addStocks(options, sr.getEnumCategory());
		}
		s.addHistory(sr);
		ModelAndView mav = new ModelAndView();
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