package com.got.mapper.deal;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.got.enums.OrderStatus;
import com.got.vo.SearchVO;
import com.got.vo.deal.PaymentVO;

@Repository
public interface PaymentMapper {

	@Insert("INSERT INTO payment(p_no, d_no, order_uid, pay_amount, use_mileage, status, p_way, p_way_detail, pay_date, receipt_url) "
			+ "VALUES(#{p_no }, #{d_no }, #{vo.order_uid, jdbcType=VARCHAR }, #{vo.pay_amount }, #{vo.use_mileage, jdbcType=DECIMAL }, "
			+ "#{vo.status.code }, #{vo.p_way.code, jdbcType=VARCHAR }, #{vo.p_way_detail, jdbcType=VARCHAR }, "
			+ "NVL(#{vo.pay_date, jdbcType=TIMESTAMP }, sysdate), #{vo.receipt_url, jdbcType=VARCHAR } )")
	@SelectKey(statement = "SELECT p_no.NEXTVAL FROM DUAL", before = true, resultType = int.class, keyProperty = "p_no")
	public int insert(@Param("vo") PaymentVO paymentVO, @Param("d_no") Integer d_no);

	@Select("SELECT * FROM payment WHERE p_no = #{p_no }")
	public PaymentVO selectOneWithP_no(Integer p_no);
	
	@Select("SELECT * FROM payment p, deal d, deal_detail dd, stock s, goods g, goods_image gi, files f "
			+ "WHERE p.d_no = d.d_no AND d.d_no = dd.d_no AND dd.s_no = s.s_no AND s.g_no = g.g_no "
			+ "AND g.g_no = gi.g_no AND gi.f_no = f.f_no AND gi.location = 'main' "
			+ "AND order_uid = #{order_uid }")
	@ResultMap("payAndGoodsMap")
	public PaymentVO selectOneWith_Uid(String order_uid);
	
	@Select("SELECT * FROM payment p, orders o WHERE p.d_no = o.d_no AND order_uid = #{order_uid } AND buyer_email = #{buyer_email, jdbcType=VARCHAR }")
	public PaymentVO selectOneEmailAndUid(@Param("buyer_email") String email, @Param("order_uid") String order_uid);
	
	public List<PaymentVO> selectListM_no(@Param("m_no") Integer m_no, @Param("search") SearchVO s);

	@Update("UPDATE payment SET status = #{status.code } WHERE p_no = #{p_no }")
	public int updateStatus(@Param("p_no") Integer p_no, @Param("status") OrderStatus status);
}
