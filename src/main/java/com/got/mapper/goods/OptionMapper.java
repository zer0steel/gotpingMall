package com.got.mapper.goods;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.got.vo.goods.OptionVO;

public interface OptionMapper {
	
	@Insert("INSERT INTO options(o_no, o_name, c_no) VALUES(o_no.nextval, #{o_name}, #{c_no})")
	public int insert(OptionVO o);
	
	@Select("SELECT * FROM options WHERE c_no = #{c_no}")
	public List<OptionVO> selectListWithC_no(Integer c_no);
}
