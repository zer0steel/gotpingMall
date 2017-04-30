package com.got.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.got.dao.GoodsOptionDao;
import com.got.enums.HistoryCategory;
import com.got.vo.GoodsOptionVO;
import com.got.vo.OptionsVO;

@Service
public class GoodsOptionService {
	
	@Autowired private GoodsOptionDao dao;
	
	public void add(OptionsVO o) {
		if(o.getC_no() == 0)
			throw new IllegalArgumentException("분류번호가 할당되지 않았음.");
		if(o.getO_name() == null || (o.getO_name() != null && o.getO_name().equals("")))
			throw new IllegalArgumentException("분류이름이 지정되지 않았음");
		dao.insertNewOption(o);
	}

	public void delete(int o_no) {
		dao.delete(o_no);
	}

	public void update(OptionsVO o) {
		dao.update(o);
	}

	public List<OptionsVO> getWithG_no(int c_no) {
		return dao.selectListWithC_no(c_no);
	}

	public void addStocks(List<GoodsOptionVO> options, HistoryCategory historyCategory) {
		options.forEach(option -> {
			GoodsOptionVO vo = dao.selectOne(option.getG_no(), option.getO_no());
			vo.addStock(option, historyCategory.isMinusStockCategory());
			dao.updateStocks(vo);
		});
	}
}
