package com.got.domain;

import javax.inject.Inject;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.got.enums.Grade;
import com.got.mapper.MileageMapper;
import com.got.mapper.member.MemberMapper;
import com.got.service.MemberService;
import com.got.util.BCrypt;
import com.got.vo.MileageVO;
import com.got.vo.member.MemberVO;

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
		MemberVO m = memberMapper.selectOneWithId("admin");
		MileageVO mileage = new MileageVO();
		mileage.setM_no(m.getM_no());
		mileage.setReason("테스트");
		mileage.setChange_amount(-700);
		mileageMapper.insert(mileage);
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