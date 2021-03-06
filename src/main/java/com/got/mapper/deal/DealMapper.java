package com.got.mapper.deal;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.got.enums.DealCategory;
import com.got.vo.deal.DealVO;

@Repository
public interface DealMapper {
	
	@Select("SELECT * FROM deal WHERE category > 0 ORDER BY d_no DESC")
	public List<DealVO> selectAll();
	
	final String SELECT_LIST_WITH_OS_NO = "SELECT r.* FROM "
			+ "(SELECT DISTINCT(d.d_no), d.category, d.total_change_amount, d.regdate, d.detail "
			+ "FROM goods g, stock s, deal_detail dd, deal d "
			+ "WHERE g.g_no = s.g_no AND s.s_no = dd.s_no AND dd.d_no = d.d_no "
			+ "AND g.g_no = #{g_no } AND category != #{category.code } ORDER BY d.regdate DESC) r "
			+ "WHERE ROWNUM <= #{count }";
	
	/**
	 * @param g_no 상품 번호
	 * @param count 가져올 쿼리의 개수
	 * @param category 검색에서 제외시킬 카테고리
	 * @return
	 */
	@Select(SELECT_LIST_WITH_OS_NO)
	public List<DealVO> selectListWithOS_no(@Param("g_no") Integer g_no, @Param("count") int count, @Param("category") DealCategory category);
		
	@Select("SELECT * FROM deal d, deal_detail dd, stock s, goods g "
			+ "WHERE d.d_no = #{d_no} AND d.d_no = dd.d_no AND dd.s_no = s.s_no AND s.g_no = g.g_no")
	@ResultMap("dealAndDetailsMap")
	public DealVO selectOneWithDetails(Integer d_no);
	
	//------------------------------------------
	
	final String INSERT = "INSERT INTO deal(d_no, category, d_name, detail, total_change_amount, total_price, regdate) "
			+ "VALUES(#{d_no }, #{category.code }, #{d_name, jdbcType=VARCHAR }, #{detail, jdbcType=VARCHAR }, "
			+ "#{total_change_amount }, #{total_price }, sysdate)";
	
	@Insert(INSERT)
	@SelectKey(statement = "SELECT d_no.NEXTVAL FROM DUAL", keyProperty = "d_no", resultType = int.class, before = true)
	public int insert(DealVO dealVO);
	
	@Update("UPDATE deal SET category = #{category.code }, d_name = #{d_name, jdbcType=VARCHAR }, "
			+ "detail = #{detail, jdbcType=VARCHAR } WHERE d_no = #{d_no }")
	public int updateOne(DealVO dealVO);

}
