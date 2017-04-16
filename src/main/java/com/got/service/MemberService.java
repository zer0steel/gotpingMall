package com.got.service;

import java.security.PrivateKey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.got.dao.MemberDao;
import com.got.util.BCrypt;
import com.got.util.RSA;
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
	
	public boolean join(MemberVO m, PrivateKey privateKey) {
		m.setPwd(encryptWithBCrypt(m.getPwd(),privateKey));
		MemberGradeVO mg = new MemberGradeVO();
		mg.setReason("�ű� ����");
		int insertedCount = dao.insertNewMember(m, mg);
		if(insertedCount == 1)
			return true;
		return false;
	}

	public MemberVO login(String id, String pwd, PrivateKey privateKey) {
		if(privateKey.isDestroyed())
			throw new SecurityException("privateKey is destroyed");
		if(id.isEmpty() || pwd.isEmpty()) 
			throw new IllegalArgumentException("id empty is " + id.isEmpty() + " | " + "pwd empty is " + pwd.isEmpty());
		
		MemberVO m = dao.selectOneWithM_Id(id);
		if(m == null)
			return new MemberVO();
		else {
			if(m.isEqualsPwd(pwd, privateKey)) {
				m.setLogin();
				return m;
			}
			else
				return m;
		}
	}
	
	/**
	 * RSA암호화된 비밀번호를 BCrypt로 암호화한다.
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
