package com.got.mapper.goods;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.got.vo.deal.DealDetailVO;
import com.got.vo.goods.StockVO;

@Repository
public interface StockMapper {
	
	final String SELECT_ONE = "SELECT * FROM stock WHERE s_no = #{s_no}";
	final String SELECT_ONE_AMOUNT = "SELECT amount FROM stock WHERE s_no = #{s_no}";
	
	@Select(SELECT_ONE)
	public StockVO selectOne(Integer s_no);
	
//	final String SELECT_ONE_EXTENDS_GOODS = "SELECT s.extra_cost, s.combination, g.purchase_price, g.sell_price, "
//			+ "g.discount_rate, g.name, gi.location, f.save_path, f.save_name "
//			+ "FROM stock s, goods g, goods_image gi, files f "
//			+ "WHERE s.s_no = #{s_no } AND s.g_no = g.g_no AND g.g_no = gi.g_no AND gi.f_no = f.f_no";
//	@Select(SELECT_ONE_EXTENDS_GOODS)
//	public StockVO selectOneExtendsGoods(Integer s_no);
	
	@Select("SELECT * FROM stock WHERE g_no = #{g_no}")
	public List<StockVO> selectListWithG_no(Integer g_no);
	
	@Insert("INSERT INTO stock(s_no, combination, amount, extra_cost, g_no) " + 
			"VALUES(s_no.nextval, #{combination, jdbcType=VARCHAR}, #{amount}, #{extra_cost}, #{g_no})")
	public int insert(StockVO s);
	
	@Update("UPDATE stock SET amount = amount + #{change_amount} WHERE s_no = #{stock.s_no}")
	public int updateStock(DealDetailVO cd);
}