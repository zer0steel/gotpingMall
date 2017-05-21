package com.got.mapper.goods;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.got.vo.goods.ShippingReceivingVO;

public interface SRMapper {
	
	final String SELECT_LIST_WITH_OS_NO = "SELECT r.* FROM ("
			+ "SELECT sr.sr_no, sr.category, sr.change_amount, sr.regdate, sr.detail, s.combination "
			+ "FROM goods g, stock s, shipping_receiving sr "
			+ "WHERE g.g_no = s.g_no AND s.os_no = sr.os_no AND g.g_no = #{g_no} "
			+ "ORDER BY regdate DESC"
			+ ") r WHERE ROWNUM <= #{count}";
	
	@Select(SELECT_LIST_WITH_OS_NO)
	public List<ShippingReceivingVO> selectListWithOS_no(@Param("g_no") Integer g_no, @Param("count") int count);
}
