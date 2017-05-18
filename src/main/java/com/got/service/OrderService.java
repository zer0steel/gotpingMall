package com.got.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.got.dao.OrderDao;
import com.got.vo.OrderDetailVO;
import com.got.vo.OrderVO;

@Service
public class OrderService {
	
	@Autowired private OrderDao dao;

	public OrderVO getBuyList(List<OrderDetailVO> list) {
		return dao.createOrderVO(list);
	}
}
