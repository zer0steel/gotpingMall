package com.got.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.got.dao.GoodsOptionDao;
import com.got.vo.OptionVO;

@Service
public class GoodsOptionService {
	
	@Autowired private GoodsOptionDao dao;
	
	public void add(OptionVO o) {
		if(o.getC_no() == 0)
			throw new IllegalArgumentException("분류번호가 할당되지 않았음.");
		if(o.getO_name() == null || (o.getO_name() != null && o.getO_name().equals("")))
			throw new IllegalArgumentException("분류이름이 지정되지 않았음");
		dao.insertNewOption(o);
	}
}
