package com.got.controller.admin;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.got.service.GoodsOptionService;
import com.got.vo.OptionVO;

@Controller
public class GoodsOptionController {
	
	private static Logger log = Logger.getLogger(GoodsOptionController.class);
	
	@Autowired private GoodsOptionService gos;
	
	@RequestMapping(value = "admin/goods/option/insert.yo", method = RequestMethod.POST)
	public ModelAndView insertNewOption(OptionVO o) {
		log.info(o);
		gos.add(o);
		
		return new ModelAndView(CategoryController.REDIRECT_CATEGORY_PAGE);
	}
}
