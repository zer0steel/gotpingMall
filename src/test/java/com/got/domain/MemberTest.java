package com.got.domain;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.session.SqlSession;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.got.enums.Grade;
import com.got.mapper.member.MemberMapper;
import com.got.service.MemberService;
import com.got.util.BCrypt;
import com.got.util.MybatisUtil;
import com.got.vo.MemberVO;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml",
		"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class MemberTest {
	
	@Autowired MemberService ms;
	
	@Test
	public void createAdmin() {
		MybatisUtil.getFactroy().getConfiguration().addMapper(MemberMapperTest.class);
		SqlSession session = MybatisUtil.openSession();
		
		MemberVO m = new MemberVO();
		m.setId("admin");
		m.setEnumGrade(Grade.ADMIN);
		m.setName("장영철");
		m.setEmail("test@test.yo");
		m.setAddr("서울 광진구");
		m.setPwd(BCrypt.hashpw("1234", BCrypt.gensalt(12)));
		
		MemberVO m2 = session.selectOne("selectOneWithM_id", m.getId());
		if(m2 == null)
			session.getMapper(MemberMapperTest.class).insert(m);
		session.close();
	}
	
}

interface MemberMapperTest {
	
	final String INSERT = "INSERT INTO member VALUES(m_no.nextval, #{id}, #{name}, #{pwd}, #{email}, #{addr}, sysdate, #{grade})";
	
	@Insert(INSERT)
	public void insert(MemberVO m);
}
