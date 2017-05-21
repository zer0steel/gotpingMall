package com.got.service;


import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.got.dao.OrderDao;
import com.got.vo.OrderDetailVO;
import com.got.vo.OrderVO;
import com.got.vo.PaymentVO;

@Service
public class OrderService {
	
	@Inject private OrderDao dao;

	public OrderVO getBuyList(List<OrderDetailVO> list) {
		return dao.createOrderVO(list);
	}

	public void saveCheckoutInfo(PaymentVO p) {
	}
}
