package com.got.test.select;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.got.mapper.VisitStatsMapper;
import com.got.service.VisitStatsService;

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
	
//	@Test
//	public void select() {
//		System.out.println(statsMapper.selectOne("2017.06.04"));
//	}
//	
	@Test
	public void updateCount() {
		statsService.updateCount();
	}
}
