package com.got.domain;

import javax.inject.Inject;

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
import com.got.mapper.member.MileageMapper;
import com.got.service.MemberService;
import com.got.util.BCrypt;
import com.got.util.MybatisUtil;
import com.got.vo.MemberVO;
import com.got.vo.MileageVO;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml",
		"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class MemberTest {
	
	@Inject MemberService ms;
	@Inject MemberMapper memberMapper;
	@Inject MileageMapper mileageMapper;
	
	@Before
	public void createAdmin() {
		MemberVO m = new MemberVO();
		m.setId("admin");
		m.setEnumGrade(Grade.ADMIN);
		m.setName("장영철");
		m.setEmail("test@test.yo");
		m.setPwd(BCrypt.hashpw("1234", BCrypt.gensalt(12)));
		MemberVO m2 = memberMapper.selectOneWithId(m.getId());
		if(m2 == null) {
			memberMapper.insert(m);
			MileageVO mileage = new MileageVO();
			mileage.setM_no(m.getM_no());
			mileage.setChange_amount(10000000);
			mileage.setReason("오픈기념 천만포인트 행사");
			mileageMapper.insert(mileage);
		}
		else
			System.out.println("이미 만들어놧음");
	}

	@Test
	public void insertMileage() {
		SqlSession session = MybatisUtil.openSession();
		MemberMapper mapper = session.getMapper(MemberMapper.class);
		MemberVO m = mapper.selectOneWithId("admin");
		MileageVO mileage = new MileageVO();
		mileage.setM_no(m.getM_no());
		mileage.setReason("테스트");
		mileage.setChange_amount(-700);
		session.getMapper(MileageMapper.class).insert(mileage);
		session.close();
	}
	
	@Test
	public void txTest() {
		MemberVO m = new MemberVO();
		m.setId("admin666");
		m.setEnumGrade(Grade.ADMIN);
		m.setName("장영철");
		m.setEmail("test@test.yo");
		m.setPwd("1234");
		ms.join(m, null);
	}
	
	
}