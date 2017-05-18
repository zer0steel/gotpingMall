package com.got.mapper.files;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.got.vo.GoodsImgVO;

@Mapper
public interface GoodsImageMapper {
	
	final String SELECT_LIST = "SELECT * FROM goods_image gi, files f "
			+ "WHERE g_no = #{g_no} "
			+ "AND gi.f_no = f.f_no";
	@Select(SELECT_LIST)
	public List<GoodsImgVO> selectList(Integer g_no);
}
