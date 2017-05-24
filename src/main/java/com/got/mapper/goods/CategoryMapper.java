package com.got.mapper.goods;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.got.vo.goods.CategoryVO;

@Repository
public interface CategoryMapper {
	
	@Insert("INSERT INTO category(c_no, super_no, title, levels) "
			+ "VALUES(#{c_no }, #{super_no, jdbcType=INTEGER }, #{title }, #{levels.code })")
	@SelectKey(statement = "SELECT c_no.nextval FROM DUAL", keyProperty = "c_no", resultType = int.class, before = true)
	public int insert(CategoryVO c);
	
	@Delete("DELETE category WHERE c_no = #{c_no}")
	public int deleteOne(Integer c_no);
	
	@Update("UPDATE category SET super_no = #{super_no}, title = #{title}, levels = #{levels } WHERE c_no = #{c_no}")
	public int updateOne(CategoryVO c);
	
	@Select("SELECT * FROM category")
	public List<CategoryVO> selectAll();
	
	@Select("SELECT * FROM category WHERE levels = #{level }")
	public List<CategoryVO> selectListWithLevel(int level);

	@Select("SELECT * FROM category WHERE c_no = #{c_no }")
	public CategoryVO selectOne(Integer c_no);
	
	final String SELECT_SUB = "SELECT * FROM category WHERE super_no = #{c_no }";
	@Select(SELECT_SUB + " UNION ALL SELECT c.* FROM category c, (" + SELECT_SUB + ") r WHERE c.super_no = r.c_no")
	public List<CategoryVO> selectSubList(Integer c_no);
	
	
	final String SELECT_BIG = "SELECT c.* FROM category c, "
			+ "(SELECT c_super.* FROM category c, category c_super "
			+ "WHERE NVL(c.super_no, c.c_no) = c_super.c_no AND c.c_no = #{c_no }) r "
			+ "WHERE NVL(r.super_no, r.c_no) = c.c_no";
	@Select(SELECT_BIG)
	public CategoryVO selectBigWithC_no(Integer c_no);
}
