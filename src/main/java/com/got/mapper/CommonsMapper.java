package com.got.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface CommonsMapper {
	
	@Delete("DELETE FROM ${tableName}")
	public int deleteAll(@Param("tableName") String tableName);
	
}
