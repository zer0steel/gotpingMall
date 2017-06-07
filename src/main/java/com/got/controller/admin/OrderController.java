package com.got.controller.admin;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.got.enums.OrderStatus;
import com.got.service.deal.PaymentService;
import com.got.util.ModelAndView;
import com.got.vo.SearchVO;

@Controller("adminOrderController")
public class OrderController {
	
	@Inject private PaymentService payService;
	
	@RequestMapping("order/newList.yo")
	public ModelAndView newList() {
		payService.getCheckoutList(OrderStatus.DELIVERY_READY);
		
		ModelAndView mav = new ModelAndView();
		return mav.setAdminViewPage("order/newList.jsp");
	}
	
	@RequestMapping("order/list.yo")
	public ModelAndView list() {
		ModelAndView mav = new ModelAndView();
		return mav.setAdminViewPage("order/list.jsp");
	}
}
