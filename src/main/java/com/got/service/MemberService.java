package com.got.service;

import java.security.PrivateKey;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.got.dao.MemberDao;
import com.got.util.BCrypt;
import com.got.util.RSA;
import com.got.vo.MemberGradeVo;
import com.got.vo.MemberVo;

@Service
public class MemberService {

	@Autowired private MemberDao dao;
	
	/**
	 * 해당 컬럼의 동일한 값을 가지는 레코드의 숫자를 반환한다.
	 * @param column
	 * @param value
	 * @return
	 */
	public int getOverlapCount(String column, String value) {
		if(column.isEmpty() || value.isEmpty())
			throw new IllegalArgumentException();
		return dao.selectOneColumn(column, value).size();
	}
	
	/**
	 * 회원 등록
	 * @param m
	 * @return 
	 */
	public boolean joinMember(MemberVo m, PrivateKey privateKey) {
		m.setM_pwd(encryptWithBCrypt(m.getM_pwd(),privateKey));
		MemberGradeVo mg = new MemberGradeVo().setMg_reason("신규 가입");
		int insertedCount = dao.insertNewMember(m, mg);
		if(insertedCount == 1)
			return true;
		return false;
	}

	public Map<String, Object> login(String id, String pwd, PrivateKey privateKey) {
		if(id.isEmpty() || pwd.isEmpty()) 
			throw new IllegalArgumentException();
		MemberVo m = dao.selectOneWithM_Id(id);
		if(m == null)
			return new HashMap<String, Object>();
		else if( !m.isSelected() )
			return new HashMap<String, Object>();
		else {
			if(checkWithBCrypt(m.getM_pwd(), pwd, privateKey)) {
				Map<String, Object> loginMember = new HashMap<>();
				loginMember.put("m_id", m.getM_id());
				loginMember.put("m_grade", m.getGrade());
				return loginMember;
			}
			else
				return new HashMap<String, Object>();
		}
	}
	
	
	/**
	 * RSA로 암호화 되어있는 패스워드를 BCrypt로 암호화 되어있는 패스워드와 비교한다.
	 * @param RSAPwd
	 * @param privateKey
	 * @return
	 */
	private boolean checkWithBCrypt(String BCryptPwd,String RSAPwd,PrivateKey privateKey) {
		String pwd = RSA.decryptRsa(RSAPwd, privateKey);
		return BCrypt.checkpw(pwd, BCryptPwd);
	}
	
	/**
	 * RSA로 암호화 되어있는 패스워드를 BCrypt로 암호화한다.
	 * @param RSAPwd
	 * @param privateKey
	 * @return
	 */
	private String encryptWithBCrypt(String RSAPwd,PrivateKey privateKey) {
		String pwd = RSA.decryptRsa(RSAPwd, privateKey);
		String BCryptPwd = BCrypt.hashpw(pwd,BCrypt.gensalt(12));
		return BCryptPwd;
	}
}
