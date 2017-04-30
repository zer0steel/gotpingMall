package com.got.dao;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.got.dao.template.DaoTemplate;
import com.got.enums.HistoryCategory;
import com.got.vo.GoodsOptionVO;
import com.got.vo.OptionsVO;

@Repository
public class GoodsOptionDao {
	
	@Autowired private DaoTemplate dao;

	public void insertNewOption(OptionsVO o) {
		dao.insert("go.insertNewOption", o);
	}

	public List<OptionsVO> selectListWithC_no(int c_no) {
		return dao.selectList("go.selectListWithC_no", c_no);
	}
	
	public List<GoodsOptionVO> selectListWithG_no(int g_no) {
		return dao.selectList("go.selectListWithG_no", g_no);
	}

	public void delete(int o_no) {
		dao.delete("go.delete", o_no);
	}

	public void update(OptionsVO o) {
		dao.update("go.update", o);
	}
	
	public GoodsOptionVO selectOne(int g_no, int o_no) {
		Map<String, Integer> param = new HashMap<>();
		param.put("g_no", g_no);
		param.put("o_no", o_no);
		return dao.selectOne("go.selectOne", param);
	}
	
	public void updateStocks(GoodsOptionVO options) {
		dao.update("go.updateStock", options);
	}
}
