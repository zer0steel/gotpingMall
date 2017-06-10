package com.got.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.got.enums.OrderStatus;
import com.got.vo.deal.PaymentVO;

@Repository
public interface ViewMapper {
	
	@Select("SELECT * FROM order_v WHERE status IN(4, 10) ORDER BY pay_date DESC")
	@ResultMap("com.got.mapper.deal.PaymentMapper.payWithOrderMap")
	public List<PaymentVO> selectListNewOrder();
	
	public List<PaymentVO> selectListOrder(List<Integer> status);
}
