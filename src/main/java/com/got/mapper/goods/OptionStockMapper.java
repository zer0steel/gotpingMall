package com.got.mapper.goods;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.got.vo.goods.OptionStockVO;

@Mapper
public interface OptionStockMapper {
	
	public List<OptionStockVO> selectListWithOS_no(List<OptionStockVO> list);

	@Select("SELECT * FROM option_stock WHERE os_no = #{os_no}")
	public OptionStockVO selectOne(Integer os_no);
}