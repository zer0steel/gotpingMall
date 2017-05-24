package com.got.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.got.vo.MileageVO;

@Repository
public interface MileageMapper {
	
	final String SELECT_CURR_MILEAGE = "SELECT * FROM "
			+ "(SELECT curr_mileage FROM mileage WHERE m_no = #{m_no} ORDER BY m_date DESC) r "
			+ "WHERE ROWNUM = 1";
	final String INSERT = "INSERT INTO mileage(m_no, m_date, curr_mileage, change_amount, reason, p_no) "
			+ "VALUES(#{m_no }, sysdate, NVL((" + SELECT_CURR_MILEAGE + "), 0) + #{change_amount },"
			+ " #{change_amount }, #{reason}, #{p_no, jdbcType=VARCHAR})";
	/**
	 * m_no 는 pk이므로 반드시 포함
	 * @param m
	 */
	@Insert(INSERT)
	public int insert(MileageVO m);
	
	@Select(SELECT_CURR_MILEAGE)
	public MileageVO selectCurrMileage(Integer m_no);
}
