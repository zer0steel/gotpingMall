package com.got.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.got.vo.MileageVO;

@Repository
public interface MileageMapper {
	
	final String SELECT_CURR_MILEAGE = "SELECT * FROM "
			+ "(SELECT curr_mileage FROM mileage WHERE m_no = #{m_no} ORDER BY regdate DESC) r "
			+ "WHERE ROWNUM = 1";
	
	final String INSERT = "INSERT INTO mileage(m_pk, m_no, regdate, curr_mileage, change_amount, reason, category, p_no) "
			+ "VALUES(#{m_pk }, #{m_no }, sysdate, NVL((" + SELECT_CURR_MILEAGE + "), 0) + #{change_amount },"
			+ " #{change_amount }, #{reason, jdbcType=VARCHAR}, #{category.code },#{p_no, jdbcType=VARCHAR})";
	
	@Select(SELECT_CURR_MILEAGE)
	public MileageVO selectCurrMileage(Integer m_no);
	
	@Insert(INSERT)
	@SelectKey(statement = "SELECT mileage_seq.NEXTVAL FROM DUAL", before = true, resultType = int.class, keyProperty = "m_pk")
	public int insert(MileageVO m);
}
