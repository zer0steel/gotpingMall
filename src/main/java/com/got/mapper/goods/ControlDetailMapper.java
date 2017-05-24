package com.got.mapper.goods;

import org.apache.ibatis.annotations.Insert;
import org.springframework.stereotype.Repository;

import com.got.vo.goods.ControlDetailVO;

@Repository
public interface ControlDetailMapper {
	
	final String INSERT = "INSERT INTO ctl_detail(cd_no, change_amount, remain_stock, price, gc_no, s_no) "
			+ "VALUES(cd_no.NEXTVAL, #{change_amount }, (" + StockMapper.SELECT_ONE_AMOUNT_COL + ") + "
			+ "#{change_amount }, #{price }, #{gc_no }, #{s_no })";
	
	@Insert(INSERT)
	public int insert(ControlDetailVO cd);
}
