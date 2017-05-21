package com.got.mapper.goods;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.got.vo.goods.GoodsOptionVO;

public interface GoodsOptionMapper {
	
	@Insert("INSERT INTO goods_option(g_no, o_no, value, extra_cost) VALUES(#{g_no}, #{o_no}, #{value}, #{extra_cost})")
	public int insert(GoodsOptionVO go);
	
	@Select("SELECT * FROM goods_option go, options o WHERE o.o_no = go.o_no AND go.g_no = #{g_no}")
	public List<GoodsOptionVO> selectListWithG_no(Integer g_no);
}
