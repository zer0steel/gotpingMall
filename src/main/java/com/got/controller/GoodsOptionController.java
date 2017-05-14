package com.got.controller;


import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.got.service.GoodsOptionService;
import com.got.service.OptionStockService;

@Controller
public class GoodsOptionController {
	
	private static Logger log = Logger.getLogger(GoodsOptionController.class);
	
	@Autowired private OptionStockService oss;
	@Autowired private GoodsOptionService gos;
	
	@ResponseBody
	@RequestMapping(value = "goods/option/stock.yo", produces = "application/json; charset=UTF-8")
	public String getOptinalStocks(Integer g_no) {
		return gos.getOptionalStocksJSON(g_no);
	}
}
