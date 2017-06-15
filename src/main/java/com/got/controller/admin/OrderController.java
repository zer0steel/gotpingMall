package com.got.controller.admin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
	public boolean updateStatus(@RequestParam("p_no[]") Integer[] p_no, int statusCode) {
		return payService.updateStatus(p_no, OrderStatus.of(statusCode));
	}
}
