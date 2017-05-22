package com.got.vo.list;

import java.util.List;

import com.got.vo.goods.StockVO;

public class StockListContainer {
	
	private List<StockVO> stocks;

	public List<StockVO> getStocks() {
		return stocks;
	}

	public void setStocks(List<StockVO> stocks) {
		this.stocks = stocks;
	}
}
