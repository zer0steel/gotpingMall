package com.got.dao;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.got.dao.template.DaoTemplate;
import com.got.vo.MemberGradeVO;

@Repository
public class MemberGradeDao {
	@Autowired private DaoTemplate dao;
	
	public void insertMemberGrade(MemberGradeVO mg) {
		dao.insert("mg.insert", mg);
	}
}
