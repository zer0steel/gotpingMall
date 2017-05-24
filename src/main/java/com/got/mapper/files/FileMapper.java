package com.got.mapper.files;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.got.vo.file.FileVO;

@Repository
public interface FileMapper {
	
	@Insert("INSERT INTO files(f_no, save_name, real_name, regdate, save_path) " + 
		"VALUES(#{f_no}, #{save_name}, #{real_name}, sysdate, #{save_path})")
	@SelectKey(statement = "SELECT f_no.nextval FROM DUAL", keyProperty = "f_no", resultType = int.class, before = true)
	public int insert(FileVO f);
	
	@Update("UPDATE files SET save_path = #{save_path} WHERE f_no = #{f_no}")
	public int updatePath(FileVO f);
	
	@Delete("DELETE files WHERE f_no = #{f_no}")
	public int deleteOne(Integer f_no);
}
