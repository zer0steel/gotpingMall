package com.got.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.transaction.TransactionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.got.dao.template.DaoTemplate;
import com.got.vo.MemberGradeVo;
import com.got.vo.MemberVo;

@Repository
public class MemberDao {
	@Autowired private DaoTemplate dao;
	
	public List<MemberVo> getAll() {
		return dao.selectList("m.selectAll");
	}

	public List<MemberVo> selectOneColumn(String column, String value) {
		Map<String, String> params = new HashMap<>();
		params.put("column", column);
		params.put("value", value);
		return dao.selectList("m.selectOneColumn", params);
	}

	public int insertMember(MemberVo m) {
		return dao.insert("m.insert", m);
	}
	
	public int insertNewMember(MemberVo m, MemberGradeVo mg) {
		return dao.transactionTemplate(session -> {
			int insertedCount = session.insert("m.insert",m);
			if(insertedCount == 1) {
				mg.setM_no(m.getM_no());
				return session.insert("mg.insert",mg);
			}
			else
				throw new TransactionException();
		});
	}

	public MemberVo selectOneWithM_Id(String m_id) {
		return dao.selectOne("m.selectOneWithM_id", m_id);
	}
}
