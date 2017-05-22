package com.got.mapper.goods;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.got.vo.ControlDetailVO;
import com.got.vo.goods.StockVO;

public interface StockMapper {
	
	final String SELECT_ONE = "SELECT * FROM stock WHERE s_no = #{s_no}";
	final String SELECT_ONE_AMOUNT_COL = "SELECT r.amount FROM (" + SELECT_ONE + ") r";
	@Select(SELECT_ONE)
	public StockVO selectOne(Integer s_no);
	
	@Select("SELECT * FROM stock WHERE g_no = #{g_no}")
	public List<StockVO> selectListWithG_no(Integer g_no);
	
	@Insert("INSERT INTO stock(s_no, combination, amount, extra_cost, g_no) " + 
			"VALUES(s_no.nextval, #{combination, jdbcType=VARCHAR}, #{amount}, #{extra_cost}, #{g_no})")
	public int insert(StockVO s);
	
	@Update("UPDATE stock SET amount = amount + #{change_amount} WHERE s_no = #{s_no}")
	public int updateStock(ControlDetailVO cd);
}