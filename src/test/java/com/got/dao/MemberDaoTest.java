package com.got.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.got.dao.template.DaoTemplate;

//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = {
//		"file:src/main/webapp/WEB-INF/spring/root-context.xml", 
//		"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"
//})
public class MemberDaoTest {
	
	@Test
	public void getAllTest() {
		MemberDao dao = new MemberDao();
		System.out.println(dao.getAll().size());
	}
}

