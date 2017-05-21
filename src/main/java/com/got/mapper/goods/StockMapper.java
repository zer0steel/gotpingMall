package com.got.mapper.goods;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.got.vo.goods.StockVO;

public interface StockMapper {
	
	@Select("SELECT * FROM stock WHERE s_no = #{s_no}")
	public StockVO selectOne(Integer s_no);
	
	@Insert("INSERT INTO stock(s_no, combination, amount, extra_cost, g_no) " + 
		"VALUES(s_no.nextval, #{combination, jdbcType=VARCHAR}, #{amount}, #{extra_cost}, #{g_no})")
	public int insert(StockVO s);
	
	@Select("SELECT * FROM stock WHERE g_no = #{g_no}")
	public List<StockVO> selectListWithG_no(Integer g_no);
}