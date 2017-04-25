package com.got.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.got.dao.template.DaoTemplate;
import com.got.vo.OptionVO;

@Repository
public class GoodsOptionDao {
	
	@Autowired private DaoTemplate dao;

	public void insertNewOption(OptionVO o) {
		dao.insert("go.insertNewOption", o);
	}

}
