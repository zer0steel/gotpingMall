package com.got.service.goods;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.got.mapper.goods.StockMapper;
import com.got.util.CommonUtil;
import com.got.vo.goods.GoodsOptionVO;
import com.got.vo.goods.GoodsVO;
import com.got.vo.goods.StockVO;
import com.got.vo.goods.GoodsOptionVO.Detail;

@Service
public class StockService {
	
	@Inject StockMapper stockMapper;
	
	public void insertStock(GoodsVO g) {
		createOptionStocks(g.getGoodsOptions()).forEach(vo -> {
			vo.setG_no(g.getG_no());
			stockMapper.insert(vo);
		});
	}

	public List<StockVO> getList(Integer g_no) {
		Objects.requireNonNull(g_no);
		return stockMapper.selectListWithG_no(g_no);
	}
	
	public String getJSONList(Integer g_no) {
		return CommonUtil.convertToJSON(getList(g_no));
	}
	
	public List<StockVO> createOptionStocks(List<GoodsOptionVO> list) {
		if(Objects.isNull(list))
			return createStockVO();
		if(list.isEmpty())
			return createStockVO();
		
		return createOptionStockArray(list, 0, new Detail[list.size()], new ArrayList<>());
	}
	
	private List<StockVO> createStockVO() {
		StockVO s = new StockVO();
		s.setCombination("기본옵션");
		s.setAmount(0);
		s.setExtra_cost(0);
		List<StockVO> list = new ArrayList<>();
		list.add(s);
		return list;
	}

	private List<StockVO> createOptionStockArray(List<GoodsOptionVO> goList, int index, Detail[] details, List<StockVO> returnList) {
		GoodsOptionVO g = goList.get(index);
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
				StockVO o = new StockVO();
				o.setCombination(sb.toString());
				o.setExtra_cost(extraCost);
				returnList.add(o);
			}
		}
		return returnList;
	}
}
