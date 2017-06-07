package com.got.test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;

import javax.inject.Inject;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.got.enums.Grade;
import com.got.enums.MileageCategory;
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
	
//	@Test
	public void createAdmin() {
		MemberVO m = new MemberVO();
		m.setId("admin");
		m.setEnumGrade(Grade.ADMIN);
		m.setName("장영철");
		m.setEmail("dudcjf98@naver.com");
		m.setAddr("05099/ 서울 광진구 자양동 606-10/ 5층");
		m.setPwd(BCrypt.hashpw("1234", BCrypt.gensalt(12)));
		MemberVO m2 = memberMapper.selectOneWithId(m.getId());
		if(m2 == null) {
			memberMapper.insert(m);
			m2 = m;
		}
		else
			System.out.println("이미 만들어놧음");
		
		MileageVO mileage = new MileageVO();
		mileage.setM_no(m2.getM_no());
		mileage.setChange_amount(BigDecimal.valueOf(10000000));
		mileage.setEnumCategory(MileageCategory.SAVE);
		mileage.setReason("오픈기념 천만포인트 행사");
		mileageMapper.insert(mileage);
	}

//	@Test
//	public void insertMileage() {
//		MemberVO m = memberMapper.selectOneWithId("admin");
//		MileageVO mileage = new MileageVO();
//		mileage.setM_no(m.getM_no());
//		mileage.setReason("테스트");
//		mileage.setChange_amount(BigDecimal.valueOf(-700));
//		mileage.setEnumCategory(MileageCategory.USE);
//		mileageMapper.insert(mileage);
//	}
	
	@Test
	public void mileageTest() {
		BigDecimal[] goodsPrice = {BigDecimal.valueOf(26000), BigDecimal.valueOf(7000), BigDecimal.valueOf(9000)};
		BigDecimal totalPrice = Arrays.asList(goodsPrice).stream().reduce(BigDecimal.ZERO, BigDecimal::add);
		System.out.println("총 가격 : " + totalPrice);
		double[] savingMileage = {0.10, 0.05, 0.01};
		BigDecimal payAmount2 = BigDecimal.ZERO;
		BigDecimal payAmount = BigDecimal.valueOf(10000);
		for(int i = 0; i < goodsPrice.length; i++) {
			BigDecimal percent = goodsPrice[i].divide(totalPrice, 2, RoundingMode.HALF_UP);
			BigDecimal unitPrice = payAmount.multiply(percent);
			BigDecimal mileageAmount = unitPrice.multiply(BigDecimal.valueOf(savingMileage[i]));
			System.out.println("실제 상품에 지불된 금액 : " + unitPrice);
			System.out.println(mileageAmount);
		}
	}
//	
//	@Test
//	public void txTest() {
//		MemberVO m = new MemberVO();
//		m.setId("admin666");
//		m.setEnumGrade(Grade.ADMIN);
//		m.setName("장영철");
//		m.setEmail("test@test.yo");
//		m.setPwd("1234");
//		ms.join(m, null);
//	}
}