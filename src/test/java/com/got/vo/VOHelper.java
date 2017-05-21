package com.got.vo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.got.vo.goods.GoodsOptionVO;
import com.got.vo.goods.OptionVO;
import com.got.vo.goods.GoodsOptionVO.Detail;

public class VOHelper {
	
	public static List<GoodsOptionVO> createGoodsOptions(List<OptionVO> options) {
		List<GoodsOptionVO> goodsOptions = new ArrayList<>();
		options.forEach(vo -> {
			GoodsOptionVO go = new GoodsOptionVO();
			go.setO_no(vo.getO_no());
			go.setC_no(vo.getC_no());
			go.setO_name(vo.getO_name());
			List<Detail> details = Arrays.asList(
				new Detail(vo.getO_name() + "-옵션-01", 1000),
				new Detail(vo.getO_name() + "-옵션-02", 5000));
			go.setDetails(details);
			goodsOptions.add(go);
		});
		return goodsOptions;
	}
}
