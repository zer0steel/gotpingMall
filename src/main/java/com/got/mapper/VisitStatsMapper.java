package com.got.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.got.vo.SearchVO;
import com.got.vo.VisitStatsVO;

@Repository
public interface VisitStatsMapper {
	
	@Insert("INSERT INTO visit_stats VALUES(TO_CHAR(sysdate, 'yyyy.MM.dd'), 0)")
	public int insert();
	
	@Update("UPDATE visit_stats SET count = count + 1 WHERE day = TO_CHAR(sysdate, 'yyyy.MM.dd')")
	public int updateCount();
	
	@Select("SELECT * FROM visit_stats WHERE day = #{day }")
	public VisitStatsVO selectOne(String day);
	
	@Select("SELECT * FROM visit_stats WHERE day = TO_CHAR(sysdate, 'yyyy.MM.dd')")
	public VisitStatsVO selectOneToday();
	
	@Select("SELECT * FROM visit_stats WHERE day BETWEEN #{startDate } AND #{endDate }")
	public List<VisitStatsVO> selectListPeriod(SearchVO s);
}
