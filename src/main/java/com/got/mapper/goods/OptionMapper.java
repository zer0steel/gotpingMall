package com.got.mapper.goods;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.got.vo.goods.OptionVO;

@Repository
public interface OptionMapper {
	
	@Select("SELECT * FROM options WHERE c_no = #{c_no}")
	public List<OptionVO> selectListWithC_no(Integer c_no);
	
	@Insert("INSERT INTO options(o_no, o_name, c_no) VALUES(o_no.nextval, #{o_name}, #{c_no})")
	public int insert(OptionVO o);
	
	@Delete("DELETE FROM options WHERE o_no = #{o_no}")
	public int deleteOne(Integer o_no);
	
	@Update("UPDATE options SET o_name = #{o_name} WHERE o_no = #{o_no}")
	public int updateOne(OptionVO o);
}
