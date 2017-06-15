package com.got.controller.admin;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.got.service.VisitStatsService;
import com.got.util.JSONUtil;
import com.got.util.ModelAndView;
import com.got.vo.SearchVO;

@Controller
public class AdminController {
	
	@Inject private VisitStatsService vsService;
	
	@RequestMapping("admin.yo")
	public ModelAndView admin() {
		SearchVO searchVO = new SearchVO(1);
		ModelAndView mav = new ModelAndView();
		mav.addObject("period", searchVO);
		mav.addObject("visitStats", JSONUtil.convertToJSON(vsService.getDataByDate(searchVO)));
		return mav.setAdminViewPage("main/dashboard.jsp");
	}

}