package com.got.controller.admin;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.got.service.GoodsOptionService;
import com.got.util.CommonUtil;
import com.got.vo.OptionsVO;

@Controller("adminGoodsOptionController")
public class GoodsOptionController {
	
	private static Logger log = Logger.getLogger(GoodsOptionController.class);
	
	@Autowired private GoodsOptionService gos;
	
	@RequestMapping(value = "admin/goods/option/insert.yo", method = RequestMethod.POST)
	public ModelAndView insertNewOption(OptionsVO o) {
		log.info(o);
		gos.add(o);
		return new ModelAndView(CategoryController.REDIRECT_CATEGORY_PAGE);
	}
	
	@RequestMapping(value = "admin/goods/option/delete.yo", method = RequestMethod.POST)
	public ModelAndView deleteOption(int o_no) {
		gos.delete(o_no);
		return new ModelAndView(CategoryController.REDIRECT_CATEGORY_PAGE);
	}
	
	@RequestMapping(value = "admin/goods/option/update.yo", method = RequestMethod.POST)
	public ModelAndView updateOption(OptionsVO o) {
		gos.update(o);
		return new ModelAndView(CategoryController.REDIRECT_CATEGORY_PAGE);
	}
	
	@ResponseBody
	@RequestMapping(value = "admin/goods/option/get.yo", produces = "application/json; charset=UTF-8")
	public String get(int c_no) {
		return CommonUtil.convertToJSON(gos.getWithG_no(c_no));
	}
}
