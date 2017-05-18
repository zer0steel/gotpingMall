package com.got.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.transaction.TransactionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.got.dao.template.DaoTemplate;
import com.got.dao.template.MapperCallback;
import com.got.mapper.member.MemberMapper;
import com.got.vo.MemberGradeVO;
import com.got.vo.MemberVO;

@Repository
public class MemberDao {
	@Autowired private DaoTemplate dao;
	
	public List<MemberVO> selectOneColumn(String column, String value) {
		Map<String, String> params = new HashMap<>();
		params.put("column", column);
		params.put("value", value);
		return dao.selectList("m.selectOneColumn", params);
	}
	
	public void insertNewMember(MemberVO m, MemberGradeVO mg) {
		dao.transactionTemplate(session -> {
			if(session.insert("m.insert",m) != 1) 
				throw new TransactionException();
			
			mg.setM_no(m.getM_no());
			session.insert("mg.insert",mg);
		});
	}

	public MemberVO selectOneWithM_id(String m_id) {
		return dao.selectOne("m.selectOneWithM_id", m_id);
	}
	
	public MemberVO selectOneWithM_no(Integer m_no) {
		return dao.selectOne("m.selectOneWithM_no", m_no);
	}

	public List<MemberVO> selectAll() {
		return dao.selectList("m.selectAll");
	}
}
