package com.got.mapper.deal;

import org.apache.ibatis.annotations.Insert;
import org.springframework.stereotype.Repository;

import com.got.vo.deal.OrderVO;

@Repository
public interface OrderMapper {
	
	@Insert("INSERT INTO orders(d_no, m_no, buyer, buyer_email, recipient, addr, message)"
			+ "VALUES(#{d_no}, #{m_no, jdbcType=INTEGER }, #{buyer }, #{buyer_email }, "
			+ "#{recipient }, #{address.addr }, #{message, jdbcType=VARCHAR } )")
	public int insert(OrderVO o);
}
