package com.got.mapper.goods;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;

import com.got.vo.GoodsManagmentVO;

public interface GoodsManagmentMapper {
	
	final String SELECT_LIST_WITH_OS_NO = "SELECT r.* FROM "
			+ "(SELECT DISTINCT(gc.gc_no), gc.category, gc.change_amount, gc.regdate, gc.detail "
			+ "FROM goods g, stock s, ctl_detail cd, goods_control gc "
			+ "WHERE g.g_no = s.g_no AND s.s_no = cd.s_no "
			+ "AND cd.gc_no = gc.gc_no AND g.g_no = #{g_no } ORDER BY gc.regdate DESC) r "
			+ "WHERE ROWNUM <= #{count }";
	
	@Select(SELECT_LIST_WITH_OS_NO)
	public List<GoodsManagmentVO> selectListWithOS_no(@Param("g_no") Integer g_no, @Param("count") int count);
	
//	@Select("SELECT * FROM goods g, shipping_receiving sr WHERE g.g_no = sr.g_no ORDER BY regdate DESC")
	public List<GoodsManagmentVO> selectAll();
		
	final String INSERT = "INSERT INTO goods_control(gc_no, category, detail, change_amount, total_price, regdate) "
			+ "VALUES(#{gc_no }, #{category }, #{detail }, #{change_amount }, #{total_price }, sysdate)";
	
	@Insert(INSERT)
	@SelectKey(statement = "SELECT gc_no.NEXTVAL FROM DUAL", keyProperty = "gc_no", resultType = int.class, before = true)
	public int insertOne(GoodsManagmentVO goodsManagmentVO);
	
	
}
