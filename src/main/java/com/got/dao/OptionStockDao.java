package com.got.dao;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.got.dao.template.DaoTemplate;
import com.got.vo.OptionStockVO;

@Repository
public class OptionStockDao {
	
	@Autowired private DaoTemplate dao;

	public List<OptionStockVO> selectWithG_no(Integer g_no) {
		return dao.selectList("os.selectWithG_no", g_no);
	}
}
