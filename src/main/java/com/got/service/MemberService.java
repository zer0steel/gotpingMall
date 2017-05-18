package com.got.service;

import java.security.PrivateKey;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.got.dao.MemberDao;
import com.got.enums.Grade;
import com.got.vo.MemberGradeVO;
import com.got.vo.MemberVO;

@Service
public class MemberService {

	@Autowired private MemberDao dao;
	
	/**
	 * 파라메터 컬럼을 기준으로 value값을 가지는 회원을 검색한다.
	 * @param column
	 * @param value
	 * @return 검색된 회원의 수
	 */
	public int getOverlapCount(String column, String value) {
		if(column.isEmpty() || value.isEmpty())
			throw new IllegalArgumentException();
		return dao.selectOneColumn(column, value).size();
	}
	
	public void join(MemberVO m, PrivateKey privateKey) {
		m.encyptPwd(privateKey);
		m.setEnumGrade(Grade.UNAUTHORIZED);
		
		MemberGradeVO mg = new MemberGradeVO();
		mg.setReason("신규 가입");
		
		dao.insertNewMember(m, mg);
	}

	public MemberVO login(String id, String pwd, PrivateKey privateKey) {
		if(privateKey.isDestroyed())
			throw new SecurityException("privateKey is destroyed");
		if(id.isEmpty() || pwd.isEmpty()) 
			throw new IllegalArgumentException("id empty is " + id.isEmpty() + " | " + "pwd empty is " + pwd.isEmpty());
		
		MemberVO m = dao.selectOneWithM_id(id);
		if(m == null)
			return new MemberVO();
		else {
			if(m.isEqualsPwd(pwd, privateKey))
				m.setLogin();
			return m;
		}
	}

	public List<MemberVO> getAll() {
		return dao.selectAll();
	}

	public MemberVO detail(Integer m_no) {
		Objects.requireNonNull(m_no);
		return dao.selectOneWithM_no(m_no);
	}
	
	public MemberVO detail(String m_id) {
		return dao.selectOneWithM_id(m_id);
	}
}