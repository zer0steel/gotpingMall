package com.got.mapper.goods;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;

import com.got.vo.goods.CategoryVO;

public interface CategoryMapper {
	
	@Insert("INSERT INTO category(c_no, super_no, title, menu_level) "
			+ "VALUES(#{c_no}, #{super_no}, #{title}, #{menu_level})")
	@SelectKey(statement = "SELECT c_no.nextval FROM DUAL", keyProperty = "c_no", resultType = int.class, before = true)
	public int insert(CategoryVO c);
	
	@Select("SELECT * FROM category")
	public List<CategoryVO> selectAll();
}
