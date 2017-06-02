package com.got.mapper.goods;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.got.enums.GoodsStatus;
import com.got.vo.goods.GoodsVO;

@Repository
public interface GoodsMapper {
	final String INSERT = "INSERT INTO goods(g_no, c_no, name, detail, " +
			"purchase_price, sell_price, discount_rate, saving_mileage, status_code, is_deleted)" +
			"VALUES(#{g_no}, #{c_no}, #{name}, #{detail, jdbcType=VARCHAR}, " +
			"#{purchase_price}, #{sell_price}, #{discount_rate}, #{saving_mileage}, #{status_code}, 'false')";
	
	@Insert(INSERT)
	@SelectKey(statement = "SELECT g_no.nextval FROM DUAL", keyProperty = "g_no", resultType = int.class, before = true)
	public int insert(GoodsVO g);
	
	final String UPDATE = "UPDATE goods SET c_no = #{c_no}, name = #{name}, detail = #{detail, jdbcType=VARCHAR}, "
			+ "purchase_price = #{purchase_price}, sell_price = #{sell_price}, "
			+ "discount_rate = #{discount_rate}, saving_mileage = #{saving_mileage}, "
			+ "status_code = #{status_code} "
			+ "WHERE g_no = #{g_no}";
	
	@Update(UPDATE)
	public int updateOne(GoodsVO g);
	
	@Delete("DELETE FROM goods")
	public int deleteAll();
	
	@Select("SELECT * FROM goods g, category c WHERE g.c_no = c.c_no")
	public List<GoodsVO> selectAll();
	
	@Select("SELECT * FROM goods g, category c, files f, goods_image gi "
			+ "WHERE g.c_no = c.c_no AND f.f_no = gi.f_no AND g.g_no = gi.g_no AND g.g_no = #{g_no}")
	@ResultMap("goodsWithImgsMap")
	public GoodsVO selectOne(Integer g_no);
	
	@Select("SELECT * FROM goods g, category c WHERE g.c_no = c.c_no AND c.c_no = #{c_no} AND is_deleted = 'false'")
	public List<GoodsVO> selectListWithSmall(Integer c_no);
	
	
	final String SELECT_LIST_WITH_C_NO = "SELECT g.*, f.save_path, f.save_name "
			+ "FROM goods g, category c, files f, goods_Image gi "
			+ "WHERE g.g_no = gi.g_no AND f.f_no = gi.f_no AND  g.c_no = c.c_no AND is_deleted = 'false' "
			+ "AND gi.location = 'main' AND status_code = #{status.code } AND c.c_no IN ("
			+ "SELECT c_no FROM category WHERE super_no = #{c_no } OR c_no = #{c_no } OR c_no IN ("
			+ "SELECT sub.c_no FROM category super, category sub WHERE sub.levels = 3 AND sub.super_no = super.c_no AND super.super_no = #{c_no })	)";
	@Select(SELECT_LIST_WITH_C_NO)
	@ResultMap("goodsWithMainImgMap")
	public List<GoodsVO> selectListWithC_no(@Param("c_no") Integer c_no, @Param("status") GoodsStatus status);
	
}