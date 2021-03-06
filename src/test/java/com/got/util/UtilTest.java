package com.got.util;


import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.got.enums.Level;
import com.got.util.JSONUtil;
import com.got.vo.file.GoodsImageVO;
import com.got.vo.goods.CategoryVO;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml",
		"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class UtilTest {
	@Test
	public void json() {
		JSONUtil util = new JSONUtil();
		String[] json = {"{\"f_no\":180","\"save_name\":-1984378105","\"real_name\":\"2013_ans.zip\"","\"location\":\"main\"}"};
		String[] json2 = {"{\"f_no\":180,\"save_name\":-1984378105,\"real_name\":\"2013_ans.zip\",\"location\":\"main\"},",
				"{\"f_no\":181,\"save_name\":-1984376599,\"real_name\":\"dogSound.jpeg\",\"location\":\"main\"},",
				"{\"f_no\":181,\"save_name\":-1984376599,\"real_name\":\"dogSound.jpeg\",\"location\":\"main\"},",
				"{\"f_no\":181,\"save_name\":-1984376599,\"real_name\":\"dogSound.jpeg\",\"location\":\"main\"},",
				"{\"f_no\":181,\"save_name\":-1984376599,\"real_name\":\"dogSound.jpeg\",\"location\":\"main\"},",
				"{\"f_no\":182,\"save_name\":-1984375109,\"real_name\":\"car1.jpg\",\"location\":\"main\"}"};
		List<GoodsImageVO> list = JSONUtil.getVO(json, GoodsImageVO.class);
		list.stream().forEach(vo -> System.out.println(vo));
		System.out.println("=----------------------------=");
		List<GoodsImageVO> list2 = JSONUtil.getVO(json2, GoodsImageVO.class);
		list2.stream().forEach(vo -> System.out.println(vo));
	}
	
	@Test
	public void path() {
		String path = "D:/develop/workspace/gotpingmall/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/gotpingMall/resources/upload/캐릭터";
//		System.out.println(FileUtil.simpleSavePath(path));
//		테스트 완료
//		테스트한 메서드명 : FileUtil.simpleSavePath(String path);
	}
}
