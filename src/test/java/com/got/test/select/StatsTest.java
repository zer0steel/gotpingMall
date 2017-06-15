package com.got.test.select;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.got.mapper.VisitStatsMapper;
import com.got.service.VisitStatsService;
import com.got.vo.SearchVO;
import com.got.vo.VisitStatsVO;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml",
		"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class StatsTest {
	
	@Inject VisitStatsMapper statsMapper;
	@Inject VisitStatsService statsService;
	
//	@Test
//	public void insert() {
//		statsMapper.insert();
//	}
//	
	@Test
	public void select() {
		SearchVO s = new SearchVO(1);
		List<VisitStatsVO> list2 = new ArrayList<>();
//		LocalDate startDate = LocalDate.parse(s.getStartDate(), DateTimeFormatter.ISO_LOCAL_DATE);
//		for(int i = 0; i < list.size(); i++) {
//			LocalDate time = list.get(i).getDay().toLocalDateTime().toLocalDate();
//			System.out.println(time);
//			while(!time.isEqual(startDate)) {
//				VisitStatsVO statsVO = new VisitStatsVO();
//				statsVO.setDay(Timestamp.valueOf(startDate.atStartOfDay()));
//				list2.add(statsVO);
//				startDate = startDate.plusDays(1);
//			}
//			list2.add(list.get(i));
//			System.out.println(startDate);
//			startDate = startDate.plusDays(1);
//		};
	}
	
//	@Test
//	public void updateCount() {
//		statsService.updateCount();
//	}
}
