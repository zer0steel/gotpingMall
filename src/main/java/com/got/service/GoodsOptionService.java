package com.got.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.got.dao.GoodsOptionDao;
import com.got.mapper.goods.GoodsOptionMapper;
import com.got.mapper.goods.StockMapper;
import com.got.util.CommonUtil;
import com.got.vo.goods.GoodsOptionVO;
import com.got.vo.goods.GoodsVO;
import com.got.vo.goods.OptionVO;

@Service
public class GoodsOptionService {
	
	@Inject private GoodsOptionDao dao;
	@Inject GoodsOptionMapper goodsOptionMapper;
	@Inject StockMapper stockMapper;
	
	public List<GoodsOptionVO> filteringEmptyArray(List<GoodsOptionVO> list) {
		return Arrays.asList(list.stream().filter(vo -> Objects.nonNull(vo.getDetails()) ).toArray(GoodsOptionVO[]::new));
	}
	
	public void insertGoodsOption(GoodsVO g) {
		filteringEmptyArray(g.getGoodsOptions()).forEach(vo -> {
			vo.setG_no(g.getG_no());
			vo.setupStrings();
			goodsOptionMapper.insert(vo);
		});
	}
	
	public void add(OptionVO o) {
		Objects.requireNonNull(o.getC_no());
		Objects.requireNonNull(o.getO_name());
		if(o.getO_name().equals(""))
			throw new IllegalArgumentException("분류이름이 지정되지 않았음");
		
		dao.insertNewOption(o);
	}

	public void delete(int o_no) {
		dao.delete(o_no);
	}

	public void update(OptionVO o) {
		dao.update(o);
	}

	public List<OptionVO> getWithG_no(int c_no) {
		return dao.selectListWithC_no(c_no);
	}
	
	public String getStocksJSON(Integer g_no) {
		Objects.requireNonNull(g_no);
		Map<String, Object> map = new HashMap<>();
		map.put("options", goodsOptionMapper.selectListWithG_no(g_no));
		map.put("stocks", stockMapper.selectListWithG_no(g_no));
		return CommonUtil.convertToJSON(map);
	}
}
