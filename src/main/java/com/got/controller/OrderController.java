package com.got.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.util.List;
import java.util.Objects;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.got.service.MemberService;
import com.got.service.MileageService;
import com.got.service.deal.OrderService;
import com.got.service.deal.PaymentService;
import com.got.util.JSONUtil;
import com.got.util.ModelAndView;
import com.got.vo.MileageVO;
import com.got.vo.SearchVO;
import com.got.vo.deal.DealDetailVO;
import com.got.vo.deal.DealVO;
import com.got.vo.deal.OrderVO;
import com.got.vo.deal.PaymentVO;
import com.got.vo.list.DealDetailListContainer;
import com.got.vo.member.MemberVO;

@Controller
public class OrderController {
	
	@Inject private MemberService ms;
	@Inject private OrderService os;
	@Inject private MileageService mileage;
	@Inject private PaymentService payService;
	
	private Logger log = Logger.getLogger(OrderController.class);
	
	@RequestMapping("order/form.yo")
	public ModelAndView purchaseForm(
			HttpSession session, 
			@CookieValue(name = "buyList", required = false) String buyListJSON, 
			DealDetailListContainer container
			) throws UnsupportedEncodingException {
		log.info("---------------- purchaseForm() ----------------\n");
		
		List<DealDetailVO> list = confirmOrderDetail(container.getDetails(), buyListJSON);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("o", os.getBuyList(list));
		
		MemberVO m = (MemberVO)session.getAttribute("lm");
		if(Objects.nonNull(m))
			mav.addObject("m", ms.detail(m.getM_no()));
		
		return mav.setViewPage("order/form.jsp");
	}
	
	@RequestMapping(value = "order/mileageCheck.yo", method = RequestMethod.POST)
	public void mileageCheck(HttpServletResponse res, MileageVO m, BigDecimal total_price) throws IOException {
		log.info("---------------- mileageCheck() ----------------\n");
		log.debug(m);
		res.getWriter().print(mileage.checkMileage(m, total_price));
	}
	
	@RequestMapping(value = "order/successCheckout.yo", method = RequestMethod.POST)
	public ModelAndView successCheckout(OrderVO o, PaymentVO p, String d_name) {
		log.info("---------------- successCheckout() ----------------\n");
		log.debug(o); log.debug(p); log.debug(d_name);
		ModelAndView mav = new ModelAndView();
		mav.addObject("pay", payService.saveCheckout(p, o, d_name));
		return mav.setViewPage("order/completeCheckout.jsp");
	}
	
	@RequestMapping(value = "order/orderList.yo")
	public ModelAndView orderList(HttpSession session, SearchVO s) {
		log.info("---------------- orderList() ----------------\n");
		MemberVO m = (MemberVO)session.getAttribute("lm");
		if(Objects.isNull(s))
			s = new SearchVO();
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("search", s);
		mav.addObject("payList", payService.getCheckoutList(m.getM_no(), s));
		return mav.setViewPage("order/orderList.jsp");
	}
	
	@RequestMapping(value = "order/detail.yo")
	public ModelAndView detail(String order_uid) {
		log.info("---------------- detail() ----------------\n");
		ModelAndView mav = new ModelAndView();
		mav.addObject("pay", payService.getCheckout(order_uid));
		mav.setViewName("order/include/orderDetailTable");
		return mav;
	}
	
	@ResponseBody
	@RequestMapping(value = "order/checkOrder.yo")
	public boolean checkOrder(String email, String order_uid) {
		log.info("---------------- checkOrder() ----------------\n");
		return payService.checkOrder(email, order_uid);
	}
	
	@RequestMapping(value = "order/orderDetail.yo")
	public ModelAndView orderDetail(String order_uid) {
		log.info("---------------- orderDetail() ----------------\n");
		ModelAndView mav = new ModelAndView();
		mav.addObject("pay", payService.getCheckout(order_uid));
		mav.setViewPage("order/orderDetail.jsp");
		return mav;
	}
	
	private List<DealDetailVO> confirmOrderDetail(List<DealDetailVO> list, String buyListJSON) throws UnsupportedEncodingException {
		if(Objects.isNull(list)) {
			list = JSONUtil.getVOList( URLDecoder.decode( Objects.requireNonNull(buyListJSON), "UTF-8" ), DealDetailVO.class );
			log.debug("로그인페이지 경유해서 들어옴");
		}
		log.debug(list);
		return list;
	}
}
