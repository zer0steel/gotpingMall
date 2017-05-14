package com.got.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.got.dao.GoodsOptionDao;
import com.got.dao.OptionStockDao;
import com.got.util.CommonUtil;
import com.got.vo.GoodsOptionVO;
import com.got.vo.GoodsVO;
import com.got.vo.OptionsVO;

@Service
public class GoodsOptionService {
	
	@Autowired private GoodsOptionDao dao;
	@Autowired private OptionStockDao osDao;
	
	public List<GoodsOptionVO> filteringEmptyArray(List<GoodsOptionVO> list) {
		return Arrays.asList(list.stream().filter(vo -> Objects.nonNull(vo.getDetails()) ).toArray(GoodsOptionVO[]::new));
	}
	
	public void add(OptionsVO o) {
		Objects.requireNonNull(o.getC_no());
		Objects.requireNonNull(o.getO_name());
		if(o.getO_name().equals(""))
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
	
	public String getOptionalStocksJSON(Integer g_no) {
		Objects.requireNonNull(g_no);
		Map<String, Object> map = new HashMap<>();
		map.put("options", dao.selectListWithG_no(g_no));
		map.put("stocks", osDao.selectWithG_no(g_no));
		System.out.println(CommonUtil.convertToJSON(map));
		return CommonUtil.convertToJSON(map);
	}
}
