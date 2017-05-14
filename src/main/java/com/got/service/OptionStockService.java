package com.got.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.got.dao.OptionStockDao;
import com.got.util.CommonUtil;
import com.got.vo.GoodsOptionVOTest;
import com.got.vo.GoodsOptionVOTest.Detail;
import com.got.vo.OptionStockVO;

@Service
public class OptionStockService {
	
	@Autowired private OptionStockDao dao;

	public List<OptionStockVO> getList(Integer g_no) {
		Objects.requireNonNull(g_no);
		return dao.selectWithG_no(g_no);
	}
	
	public String getJSONList(Integer g_no) {
		return CommonUtil.convertToJSON(getList(g_no));
	}
	
	public List<OptionStockVO> createOptionStocks(List<GoodsOptionVOTest> list) {
		list = filteringEmptyArray(list);
		return createOptionStockArray(list, 0, new Detail[list.size()], new ArrayList<>());
	}
	
	private List<GoodsOptionVOTest> filteringEmptyArray(List<GoodsOptionVOTest> list) {
		return Arrays.asList(list.stream().filter(vo -> Objects.nonNull(vo.getDetails()) ).toArray(GoodsOptionVOTest[]::new));
	}
	
	private List<OptionStockVO> createOptionStockArray(List<GoodsOptionVOTest> goList, int index, Detail[] details, List<OptionStockVO> returnList) {
		GoodsOptionVOTest g = goList.get(index);
		if( Objects.isNull(g) )
			return null;
		for(Detail detail : g.getDetails()) {
			details[index] = detail;
			if(index < goList.size() - 1) {
				createOptionStockArray(goList, index + 1, details, returnList);
			}
			else {
				StringBuilder sb = new StringBuilder(details[0].getValue());
				int extraCost = details[0].getExtra_cost();
				for (int i = 1; i < details.length; ++i) {
				    sb.append(" ").append(details[i].getValue());
				    extraCost += details[i].getExtra_cost();
				}
				OptionStockVO o = new OptionStockVO();
				o.setCombination(sb.toString());
				o.setOs_extra_cost(extraCost);
				returnList.add(o);
			}
		}
		return returnList;
	}
}
