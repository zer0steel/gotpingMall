package com.got.mapper.files;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.got.vo.GoodsImageVO;

public interface GoodsImageMapper {
	
	@Select("SELECT * FROM goods_image gi, files f " + 
			"WHERE gi.f_no = f.f_no AND g_no = #{g_no}")
	public List<GoodsImageVO> selectList(Integer g_no);
	
	@Insert("INSERT INTO goods_image(g_no, f_no, location) VALUES(#{g_no}, #{f_no}, #{location})")
	public int insert(GoodsImageVO goodsImgVO);
}
