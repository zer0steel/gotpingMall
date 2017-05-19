package com.got.dao;

import javax.inject.Inject;

import org.springframework.stereotype.Repository;

import com.got.dao.template.DaoTemplate;
import com.got.mapper.member.MemberMapper;
import com.got.vo.MemberGradeVO;
import com.got.vo.MemberVO;

@Repository
public class MemberDao {
	@Inject private DaoTemplate dao;
	
	public void insertNewMember(MemberVO m, MemberGradeVO mg) {
		dao.transactionTemplate(session -> {
			session.getMapper(MemberMapper.class).insert(m);
			mg.setM_no(m.getM_no());
			session.insert("mg.insert",mg);
		});
	}
}
