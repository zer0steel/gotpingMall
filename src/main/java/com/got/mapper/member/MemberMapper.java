package com.got.mapper.member;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;

import com.got.vo.MemberVO;

@Mapper
public interface MemberMapper {
	
	@Select("SELECT m_no, id, email, grade FROM member")
	public List<MemberVO> selectAll();
	
	@Select("SELECT * FROM member WHERE ${column} = #{value}")
	public List<MemberVO> selectList(String column, String value);
	
	@Select("SELECT * FROM member WHERE id = #{id}")
	public MemberVO selectOne(String id);
	
	@Select("SELECT * FROM member WHERE m_no = #{m_no}")
	public MemberVO selectOne(Integer m_no);
	
	final String INSERT = "INSERT INTO member(m_no, id, pwd, name, email, addr, grade, join_date)" + 
			"VALUES(#{m_no}, #{id}, #{pwd}, #{name}, #{email}, #{addr}, #{grade}, sysdate)";
	@SelectKey(statement = "SELECT m_no.nextval FROM DUAL", keyProperty = "m_no", resultType = Integer.class, before = true)
	@Insert(INSERT)
	public void insert(MemberVO m);
}
