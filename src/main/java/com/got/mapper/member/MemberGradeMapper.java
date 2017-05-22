package com.got.mapper.member;

import org.apache.ibatis.annotations.Insert;

import com.got.vo.member.MemberGradeVO;

public interface MemberGradeMapper {
	
	@Insert("INSERT INTO member_grade(m_no, mg_date, mg_grade, point, reason)" + 
		"VALUES(${m_no}, sysdate, #{mg_grade}, #{point}, #{reason})")
	public int insert(MemberGradeVO mg);
}
