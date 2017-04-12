package com.got.dao;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.got.dao.template.DaoTemplate;
import com.got.vo.MemberGradeVo;

@Repository
public class GoodsDao {
	@Autowired private DaoTemplate dao;
	
}
