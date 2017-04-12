package com.got.service;

import java.security.PrivateKey;

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
	 * �ش� �÷��� ������ ���� ������ ���ڵ��� ���ڸ� ��ȯ�Ѵ�.
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
	 * ȸ�� ���
	 * @param m
	 * @return 
	 */
	public boolean joinMember(MemberVo m, PrivateKey privateKey) {
		m.setM_pwd(encryptWithBCrypt(m.getM_pwd(),privateKey));
		MemberGradeVo mg = new MemberGradeVo().setMg_reason("�ű� ����");
		int insertedCount = dao.insertNewMember(m, mg);
		if(insertedCount == 1)
			return true;
		return false;
	}

	public MemberVo login(String id, String pwd, PrivateKey privateKey) {
		if(privateKey.isDestroyed())
			throw new SecurityException("privateKey is destroyed");
		if(id.isEmpty() || pwd.isEmpty()) 
			throw new IllegalArgumentException("id empty is " + id.isEmpty() + " | " + "pwd empty is " + pwd.isEmpty());
		
		MemberVo m = dao.selectOneWithM_Id(id);
		if(m == null)
			return m;
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
	 * RSA�� ��ȣȭ �Ǿ��ִ� �н����带 BCrypt�� ��ȣȭ�Ѵ�.
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
