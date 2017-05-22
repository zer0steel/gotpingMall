package com.got.mapper.goods;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

import com.got.vo.goods.GoodsVO;

public interface GoodsMapper {
	
	@Select("SELECT * FROM goods g, category c WHERE g.c_no = c.c_no")
	public List<GoodsVO> selectAll();
	
	@Select("SELECT * FROM goods g, category c WHERE g.c_no = c.c_no AND g.g_no = #{g_no}")
	public GoodsVO selectOne(Integer g_no);
	
	final String INSERT = "INSERT INTO goods(g_no, c_no, name, detail, " +
		"purchase_price, sell_price, discount_rate, saving_mileage, status_code, is_deleted)" +
		"VALUES(#{g_no}, #{c_no}, #{name}, #{detail}, " +
		"#{purchase_price}, #{sell_price}, #{discount_rate}, #{saving_mileage}, #{status_code}, 'false')";
	
	@Insert(INSERT)
	@SelectKey(statement = "SELECT g_no.nextval FROM DUAL", keyProperty = "g_no", resultType = int.class, before = true)
	public int insert(GoodsVO g);
	
	final String UPDATE = "UPDATE goods SET c_no = #{c_no}, name = #{name}, detail = #{detail}, "
			+ "purchase_price = #{purchase_price}, sell_price = #{sell_price}, "
			+ "discount_rate = #{discount_rate}, saving_mileage = #{saving_mileage}, "
			+ "status_code = #{status_code} "
			+ "WHERE g_no = #{g_no}";
	
	@Update(UPDATE)
	public int updateOne(GoodsVO g);
	
	@Delete("DELETE FROM goods")
	public int deleteAll();
}