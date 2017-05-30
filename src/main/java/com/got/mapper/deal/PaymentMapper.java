package com.got.mapper.deal;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectKey;
import org.springframework.stereotype.Repository;

import com.got.vo.deal.PaymentVO;

@Repository
public interface PaymentMapper {
	
	@Insert("INSERT INTO payment(p_no, d_no, impt_id, pay_amount, use_mileage, status, p_way, p_way_detail, pay_date) "
			+ "VALUES(#{p_no }, #{d_no }, #{vo.impt_id, jdbcType=VARCHAR }, #{vo.pay_amount }, #{vo.use_mileage }, "
			+ "#{vo.status.code }, #{vo.p_way,jdbcType=VARCHAR }, #{vo.p_way_detail, jdbcType=VARCHAR }, #{vo.pay_date })")
	@SelectKey(statement = "SELECT p_no.NEXTVAL FROM DUAL", before = true, resultType = int.class, keyProperty = "p_no")
	public int insert(@Param("vo") PaymentVO paymentVO, @Param("d_no") Integer d_no);
}
