package com.got.service;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.got.dao.OptionStockDao;
import com.got.util.CommonUtil;
import com.got.vo.OptionStockVO;

@Service
public class OptionStockService {
	
	@Autowired private OptionStockDao dao;
}
