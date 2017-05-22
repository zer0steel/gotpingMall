package com.got.mapper.goods;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

import com.got.vo.goods.CategoryVO;

public interface CategoryMapper {
	
	@Insert("INSERT INTO category(c_no, super_no, title, menu_level) "
			+ "VALUES(#{c_no}, #{super_no}, #{title}, #{menu_level})")
	@SelectKey(statement = "SELECT c_no.nextval FROM DUAL", keyProperty = "c_no", resultType = int.class, before = true)
	public int insert(CategoryVO c);
	
	@Delete("DELETE category WHERE c_no = #{c_no}")
	public int deleteOne(Integer c_no);
	
	@Update("UPDATE category SET super_no = #{super_no}, title = #{title}, menu_level = #{menu_level} WHERE c_no = #{c_no}")
	public int updateOne(CategoryVO c);
	
	@Select("SELECT * FROM category")
	public List<CategoryVO> selectAll();

	@Select("SELECT * FROM category WHERE c_no = #{c_no }")
	public CategoryVO selectOne(Integer c_no);
}
