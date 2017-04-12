package com.got.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.got.dao.GoodsDao;
import com.got.vo.GoodsVo;

@Service
public class GoodsService {
	
	@Autowired private GoodsDao dao;

	public boolean enroll(GoodsVo g) {
		return false;
	}
}
