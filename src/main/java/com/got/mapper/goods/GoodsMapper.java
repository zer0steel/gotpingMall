package com.got.mapper.goods;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.got.vo.goods.GoodsVO;

@Mapper
public interface GoodsMapper {
	
	@Select("SELECT * FROM goods g, category c WHERE g.c_no = c.c_no AND g.g_no = #{g_no}")
	public GoodsVO selectOne(Integer g_no);
}