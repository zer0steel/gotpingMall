package com.got.mapper.deal;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.springframework.stereotype.Repository;

import com.got.vo.deal.DealVO;

@Repository
public interface DealMapper {
	
	final String SELECT_LIST_WITH_OS_NO = "SELECT r.* FROM "
			+ "(SELECT DISTINCT(d.d_no), d.category, d.change_amount, d.regdate, d.detail "
			+ "FROM goods g, stock s, deal_detail dd, deal d "
			+ "WHERE g.g_no = s.g_no AND s.s_no = dd.s_no "
			+ "AND dd.d_no = d.d_no AND g.g_no = #{g_no } ORDER BY d.regdate DESC) r "
			+ "WHERE ROWNUM <= #{count }";
	
	@Select(SELECT_LIST_WITH_OS_NO)
	public List<DealVO> selectListWithOS_no(@Param("g_no") Integer g_no, @Param("count") int count);
	
	@Select("SELECT * FROM goods g, deal d WHERE g.g_no = d.g_no ORDER BY regdate DESC")
	public List<DealVO> selectAll();
		
	final String INSERT = "INSERT INTO deal(d_no, category, detail, change_amount, total_price, regdate) "
			+ "VALUES(#{d_no }, #{category }, #{detail, jdbcType=VARCHAR }, #{change_amount }, #{total_price }, sysdate)";
	
	@Insert(INSERT)
	@SelectKey(statement = "SELECT d_no.NEXTVAL FROM DUAL", keyProperty = "d_no", resultType = int.class, before = true)
	public int insert(DealVO dealVO);
	
	
}
