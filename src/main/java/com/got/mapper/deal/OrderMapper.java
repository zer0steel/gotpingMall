package com.got.mapper.deal;

import org.apache.ibatis.annotations.Insert;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderMapper {
	
	@Insert("")
	public int insert();
}
