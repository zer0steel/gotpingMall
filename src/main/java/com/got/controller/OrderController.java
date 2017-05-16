package com.got.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;
import java.util.Objects;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;

import com.got.service.GoodsService;
import com.got.service.MemberService;
import com.got.util.CommonUtil;
import com.got.util.ModelAndView;
import com.got.vo.MemberVO;
import com.got.vo.goods.OptionStockVO;
import com.got.vo.list.OptionStockListContainer;

@Controller
public class OrderController {
	
	@Autowired GoodsService gs;
	@Autowired MemberService ms;
	
	private Logger log = Logger.getLogger(OrderController.class);
	
	@RequestMapping("order/form.yo")
	public ModelAndView purchaseForm(
			HttpSession session, 
			@CookieValue("buyList") String buyListJSON, 
			OptionStockListContainer container
			) throws UnsupportedEncodingException {
		log.debug(container.getList());
		log.debug(URLDecoder.decode(buyListJSON, "UTF-8"));
		
		List<OptionStockVO> list = container.getList();
		if(Objects.isNull(list))
			list = CommonUtil.getVOList(URLDecoder.decode(buyListJSON, "UTF-8"), OptionStockVO.class);
		
		MemberVO m = (MemberVO)session.getAttribute("lm");
		
		ModelAndView mav = new ModelAndView();
		mav.setNoSideFrame();
		mav.addObject("m", ms.detail(m.getM_no()));
		mav.addObject("goods", gs.getBuyList(list));
		return mav.setViewPage("order/form.jsp");
	}
}
