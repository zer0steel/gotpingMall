package com.got.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;
import java.util.Objects;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;

import com.got.service.GoodsService;
import com.got.service.MemberService;
import com.got.service.OrderService;
import com.got.util.CommonUtil;
import com.got.util.ModelAndView;
import com.got.vo.MemberVO;
import com.got.vo.OrderDetailVO;
import com.got.vo.list.OrderDetailListContainer;

@Controller
public class OrderController {
	
	@Autowired private GoodsService gs;
	@Autowired private MemberService ms;
	@Autowired private OrderService os;
	
	private Logger log = Logger.getLogger(OrderController.class);
	
	@RequestMapping("order/form.yo")
	public ModelAndView purchaseForm(
			HttpSession session, 
			@CookieValue("buyList") String buyListJSON, 
			OrderDetailListContainer container
			) throws UnsupportedEncodingException {
		log.info("---------------- purchaseForm() ----------------\n");
		
		List<OrderDetailVO> list = container.getList();
		if(Objects.isNull(list)) {
			list = CommonUtil.getVOList(URLDecoder.decode(buyListJSON, "UTF-8"), OrderDetailVO.class);
			log.debug("로그인페이지 경유해서 들어옴");
		}
		log.debug(list);
		
		ModelAndView mav = new ModelAndView();
		MemberVO m = (MemberVO)session.getAttribute("lm");
		if(Objects.nonNull(m))
			mav.addObject("m", ms.detail(m.getM_no()));
		
		log.debug(os.getBuyList(list));
		mav.setNoSideFrame();
		mav.addObject("o", os.getBuyList(list));
		return mav.setViewPage("order/form.jsp");
	}
	
	@RequestMapping("order/checkout.yo")
	public ModelAndView checkout() {
		ModelAndView mav = new ModelAndView();
		return mav.setViewPage("order/checkout.jsp");
	}
}
