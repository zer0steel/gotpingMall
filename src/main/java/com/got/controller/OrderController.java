package com.got.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;
import java.util.Objects;

import javax.inject.Inject;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.got.service.GoodsService;
import com.got.service.MemberService;
import com.got.service.MileageService;
import com.got.service.OrderService;
import com.got.util.CommonUtil;
import com.got.util.ModelAndView;
import com.got.vo.MemberVO;
import com.got.vo.MileageVO;
import com.got.vo.OrderDetailVO;
import com.got.vo.PaymentVO;
import com.got.vo.list.OrderDetailListContainer;

@Controller
public class OrderController {
	
	@Inject private MemberService ms;
	@Inject private OrderService os;
	@Inject private MileageService mileage;
	
	private Logger log = Logger.getLogger(OrderController.class);
	
	@RequestMapping("order/form.yo")
	public ModelAndView purchaseForm(
			HttpSession session, 
			@CookieValue(name = "buyList", required = false) String buyListJSON, 
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
	
	@RequestMapping(value = "order/mileageCheck.yo", method = RequestMethod.POST)
	public void mileageCheck(HttpServletResponse res, MileageVO m, int total_price) throws IOException {
		log.info("---------------- mileageCheck() ----------------\n");
		log.debug(m);
		res.getWriter().print(mileage.checkMileage(m, total_price));
	}
	
	@RequestMapping(value = "order/successCheckout.yo", method = RequestMethod.POST)
	public void successCheckout(PaymentVO p) {
		log.info("---------------- successCheckout() ----------------\n");
		log.debug(p);
		os.saveCheckoutInfo(p);
	}
}
