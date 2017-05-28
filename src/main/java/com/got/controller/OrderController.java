package com.got.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;
import java.util.Objects;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.got.service.MemberService;
import com.got.service.MileageService;
import com.got.service.deal.OrderService;
import com.got.util.CommonUtil;
import com.got.util.ModelAndView;
import com.got.vo.MileageVO;
import com.got.vo.deal.DealDetailVO;
import com.got.vo.deal.OrderVO;
import com.got.vo.deal.PaymentVO;
import com.got.vo.list.DealDetailListContainer;
import com.got.vo.member.MemberVO;

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
	
	private List<DealDetailVO> confirmOrderDetail(List<DealDetailVO> list, String buyListJSON) throws UnsupportedEncodingException {
		if(Objects.isNull(list)) {
			list = CommonUtil.getVOList(URLDecoder.decode(buyListJSON, "UTF-8"), DealDetailVO.class);
			log.debug("로그인페이지 경유해서 들어옴");
		}
		log.debug(list);
		return list;
	}
	
	@RequestMapping(value = "order/mileageCheck.yo", method = RequestMethod.POST)
	public void mileageCheck(HttpServletResponse res, MileageVO m, int total_price) throws IOException {
		log.info("---------------- mileageCheck() ----------------\n");
		log.debug(m);
		res.getWriter().print(mileage.checkMileage(m, total_price));
	}
	
	@ResponseBody
	@RequestMapping(value = "order/successCheckout.yo", method = RequestMethod.POST)
	public void successCheckout(OrderVO o, PaymentVO p) {
		log.info("---------------- successCheckout() ----------------\n");
		log.debug("\n" + o + " \n " + p);
		os.saveCheckoutInfo(p);
	}
}
