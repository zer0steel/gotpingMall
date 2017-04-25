package com.got.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.got.dao.template.DaoTemplate;
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

	public void deleteOne(int o_no) {
		dao.delete("go.deleteOne", o_no);
	}

}
