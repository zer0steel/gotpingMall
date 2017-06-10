package com.got.controller.admin;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.got.enums.OrderStatus;
import com.got.service.deal.PaymentService;
import com.got.util.ModelAndView;
import com.got.vo.SearchVO;
import com.got.vo.deal.PaymentVO;

@Controller("adminOrderController")
public class OrderController {
	
	@Inject private PaymentService payService;
	
	@RequestMapping("admin/order/newList.yo")
	public ModelAndView newList() {
		ModelAndView mav = new ModelAndView();
		mav.addObject("pay", payService.getNewOrders())
		.addObject("status", OrderStatus.values());
		return mav.setAdminViewPage("order/newList.jsp");
	}
	
	@RequestMapping("admin/order/list.yo")
	public ModelAndView list() {
		ModelAndView mav = new ModelAndView();
		mav.addObject("pay", payService.getCheckoutList())
		.addObject("status", OrderStatus.values());
		return mav.setAdminViewPage("order/list.jsp");
	}
	
	@ResponseBody
	@RequestMapping(value = "admin/order/updateStatus.yo", method = RequestMethod.POST)
	public boolean updateStatus(PaymentVO pay) {
		return payService.updateStatus(pay.getP_no(), pay.getStatus());
	}
}
