package com.got.service;

import java.security.PrivateKey;
import java.util.List;
import java.util.Objects;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.got.dao.MemberDao;
import com.got.enums.Grade;
import com.got.mapper.member.MemberGradeMapper;
import com.got.mapper.member.MemberMapper;
import com.got.mapper.member.MileageMapper;
import com.got.vo.MemberGradeVO;
import com.got.vo.MemberVO;
import com.got.vo.MileageVO;

@Service
public class MemberService {

	@Inject private MemberMapper memberMapper;
	@Inject private MemberGradeMapper gradeMapper;
	@Inject private MileageMapper mileageMapper;
	
	/**
	 * 파라메터 컬럼을 기준으로 value값을 가지는 회원을 검색한다.
	 * @param column
	 * @param value
	 * @return 검색된 회원의 수
	 */
	public int getOverlapCount(String column, String value) {
		if(column.isEmpty() || value.isEmpty())
			throw new IllegalArgumentException();
		return memberMapper.selectListWithColumn(column, value).size();
	}
	
	@Transactional
	public void join(MemberVO m, PrivateKey privateKey) {
		m.encyptPwd(privateKey);
		m.setEnumGrade(Grade.UNAUTHORIZED);
		memberMapper.insert(m);
		Objects.requireNonNull(m.getM_no());
		gradeMapper.insert(new MemberGradeVO(m.getM_no()).setupNewMember());
		mileageMapper.insert(new MileageVO(m.getM_no()).setupNewMember());
	}

	public MemberVO login(String id, String pwd, PrivateKey privateKey) {
		if(privateKey.isDestroyed())
			throw new SecurityException("privateKey is destroyed");
		if(id.isEmpty() || pwd.isEmpty()) 
			throw new IllegalArgumentException("id empty is " + id.isEmpty() + " | " + "pwd empty is " + pwd.isEmpty());
		MemberVO m = memberMapper.selectOneWithId(id);
		if(Objects.isNull(m))
			return new MemberVO();
		else {
			if(m.isEqualsPwd(pwd, privateKey))
				m.setLogin();
			return m;
		}
	}

	public List<MemberVO> getAll() {
		return memberMapper.selectAll();
	}

	public MemberVO detail(Integer m_no) {
		Objects.requireNonNull(m_no);
		MemberVO m = memberMapper.selectOneWithM_no(m_no);
		m.setMileage(mileageMapper.selectCurrMileage(m_no).getCurr_mileage());
		return m;
	}
	
	public MemberVO detail(String m_id) {
		return memberMapper.selectOneWithId(m_id);
	}
}