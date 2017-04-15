package com.got.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.got.dao.template.DaoTemplate;
import com.got.vo.ShippingReceivingVO;

@Repository
public class ShippingReceivingDao {
	
	@Autowired private DaoTemplate dao;
	
	public List<ShippingReceivingVO> selectAll() {
		return dao.selectList("sr.selectAll");
	}
	
	public List<ShippingReceivingVO> selectListWithG_no(int g_no) {
		return selectListWithG_no(g_no, null);
	}
	
	public List<ShippingReceivingVO> selectListWithG_no(int g_no, Integer count) {
		Map<String, Integer> param = new HashMap<>();
		param.put("g_no", g_no);
		param.put("count", count);
		return dao.selectList("sr.selectWithG_no", param);
	}
}