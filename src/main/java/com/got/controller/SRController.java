package com.got.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.got.service.GoodsService;
import com.got.service.SRService;
import com.got.util.CommonUtil;
import com.got.vo.ShippingReceivingVO;

@Controller
public class SRController {
	
	@Autowired private SRService s;
	@Autowired private GoodsService gs;
	
	@ResponseBody
	@RequestMapping(
		value = "admin/goods/sr/insert.yo", 
		method = RequestMethod.POST,
		produces = "application/json; charset=UTF-8")
	public String insertSubmit(ShippingReceivingVO sr) {
		if( !s.addHistory(sr) )
			throw new RuntimeException();
		return CommonUtil.convertToJSON(gs.detailAndSRHistory(sr.getG_no()));
	}
}