package com.got.mapper.deal;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.got.mapper.goods.StockMapper;
import com.got.vo.deal.DealDetailVO;


@Repository
public interface DealDetailMapper {
	
	final String INSERT = "INSERT INTO deal_detail(dd_no, change_amount, remain_stock, unit_price, d_no, s_no) "
			+ "VALUES(dd_no.NEXTVAL, #{vo.change_amount }, (" + StockMapper.SELECT_ONE_AMOUNT + ") + "
			+ "#{vo.change_amount }, #{vo.unit_price }, #{vo.d_no }, #{s_no })";
	
	@Insert(INSERT)
	public int insert(@Param("s_no") Integer s_no, @Param("vo") DealDetailVO dd);
	
	final String INSERT_NOT_UPDATE_STOCK = "INSERT INTO deal_detail(dd_no, change_amount, remain_stock, unit_price, d_no, s_no) "
			+ "VALUES(dd_no.NEXTVAL, #{vo.change_amount }, (" + StockMapper.SELECT_ONE_AMOUNT + "), "
			+ "#{vo.unit_price }, #{vo.d_no }, #{s_no })";
	@Insert(INSERT_NOT_UPDATE_STOCK)
	public int insertNotUpdateStock(@Param("s_no") Integer s_no, @Param("vo") DealDetailVO dd);
	
	@Update("UPDATE deal_detail SET remain_stock = (" + StockMapper.SELECT_ONE_AMOUNT + ") + change_amount WHERE dd_no = #{dd_no }")
	public int updateStock(@Param("dd_no") Integer dd_no, @Param("s_no") Integer s_no);
}
