package com.got.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.got.vo.MemberVo;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml",
		"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class MemberDaoTest {
	
	@Autowired MemberDao dao;
	
	@Test
	public void getAllTest() {
		System.out.println(dao.getAll().size());
	}
	
	@Test
	public void insertTest() {
		MemberVo m = new MemberVo();
		m.setM_id("jyc228");
		m.setM_pwd("123123");
		m.setM_addr("±¤Áø±¸");
		m.setM_email("dudcjf98@naver.com");
		m.setM_name("Àå¿µÃ¶");
	}
}

