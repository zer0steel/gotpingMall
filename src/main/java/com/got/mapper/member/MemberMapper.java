package com.got.mapper.member;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;

import com.got.vo.member.MemberVO;

/**
 * pk : m_no <br>
 * @author Jang
 */
public interface MemberMapper {
	
	@Select("SELECT m_no, id, email, grade FROM member")
	public List<MemberVO> selectAll();
	
	@Select("SELECT * FROM member WHERE ${column} = #{value}")
	public List<MemberVO> selectListWithColumn(@Param("column") String column,@Param("value") String value);
	
	@Select("SELECT * FROM member WHERE id = #{id}")
	public MemberVO selectOneWithId(String id);
	
	@Select("SELECT * FROM member WHERE m_no = #{m_no}")
	public MemberVO selectOneWithM_no(Integer m_no);
	
	final String INSERT = "INSERT INTO member(m_no, id, pwd, name, email, addr, grade, join_date)" + 
			"VALUES(#{m_no}, #{id}, #{pwd}, #{name}, #{email}, #{addr}, #{grade}, sysdate)";
	@Insert(INSERT)
	@SelectKey(statement = "SELECT m_no.nextval FROM DUAL", keyProperty = "m_no", resultType = int.class, before = true)
	public int insert(MemberVO m);
}
