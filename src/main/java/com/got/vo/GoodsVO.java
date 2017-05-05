package com.got.vo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.got.enums.GoodsStatus;
import com.got.util.CommonUtil;

public class GoodsVO extends CategoryVO {
	private Integer g_no;
	private int stock, purchase_price, sell_price, saving_mileage, discount_price;
	private double discount_rate;
	private String name, detail;
	private GoodsStatus status;
	private GoodsImgVO mainImg;
	private List<GoodsImgVO> images;
	private List<ShippingReceivingVO> history;
	private List<GoodsOptionVO> goodsOptions;
	private List<OptionStockVO> optionStocks;

	public Integer getG_no() {
		return g_no;
	}

	public void setG_no(Integer g_no) {
		this.g_no = g_no;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public int getPurchase_price() {
		return purchase_price;
	}

	public void setPurchase_price(int purchase_price) {
		this.purchase_price = purchase_price;
	}

	public int getSell_price() {
		return sell_price;
	}

	public void setSell_price(int sell_price) {
		this.sell_price = sell_price;
		setDiscount_price();
	}

	public double getDiscount_rate() {
		return discount_rate;
	}

	public void setDiscount_rate(double discount_rate) {
		this.discount_rate = discount_rate;
		setDiscount_price();
	}

	public int getSaving_mileage() {
		return saving_mileage;
	}

	public void setSaving_mileage(int saving_mileage) {
		this.saving_mileage = saving_mileage;
	}

	public int getStatus_code() {
		return status.getCode();
	}

	public void setStatus_code(int status) {
		this.status = GoodsStatus.of(status);
	}

	public GoodsStatus getStatus() {
		return status;
	}

	public void setStatus(GoodsStatus status) {
		this.status = status;
	}

	public List<ShippingReceivingVO> getHistory() {
		return history;
	}

	public void setHistory(List<ShippingReceivingVO> history) {
		this.history = history;
	}

	public List<GoodsImgVO> getImages() {
		return images;
	}

	public void setImages(List<GoodsImgVO> images) {
		this.images = images;
	}

	public void updateStock(int amount) {
		this.stock += amount;
	}

	public GoodsImgVO getMainImg() {
		return mainImg;
	}

	public void setMainImg(GoodsImgVO mainImg) {
		this.mainImg = mainImg;
	}

	public List<GoodsOptionVO> getGoodsOptions() {
		return goodsOptions;
	}

	public void setGoodsOptions(List<GoodsOptionVO> goodsOptions) {
		this.goodsOptions = goodsOptions;
	}
	
	public void setGoodsOptions(String[] goodsOptionJSON) {
		if( Objects.nonNull(goodsOptionJSON) ) {
			setGoodsOptions(CommonUtil.getVO(goodsOptionJSON, GoodsOptionVO.class));
			createOptionStocks();
		}
	}

	public int getDiscount_price() {
		return discount_price;
	}

	public void setDiscount_price() {
		double rate = (100 - this.discount_rate) / 100;
		this.discount_price = (int)(this.sell_price * rate);
	}

	public List<OptionStockVO> getOptionStocks() {
		return optionStocks;
	}

	public void setOptionStocks(List<OptionStockVO> optionStocks) {
		this.optionStocks = optionStocks;
	}
	
	private void createOptionStocks() {
		this.optionStocks = new ArrayList<>();
		createCombinationString(this.goodsOptions, 0, new String[this.goodsOptions.size()]);
	}
	
	private void createCombinationString(List<GoodsOptionVO> goList, int index, String[] values) {
		GoodsOptionVO g = goList.get(index);
		if( Objects.isNull(g) )
			return;
		for(String value : g.getValues()) {
			values[index] = value;
			if(index < goList.size() - 1) {
				createCombinationString(goList, index + 1, values);
			}
			else {
				StringBuilder sb = new StringBuilder(values[0]);
				for (int i = 1; i < values.length; ++i) {
				    sb.append(" ").append(values[i]);
				}
				OptionStockVO o = new OptionStockVO();
				o.setCombination(sb.toString());
				o.setG_no(this.g_no);
				this.optionStocks.add(o);
			}
		}
	}

	@Override
	public String toString() {
		return "GoodsVO [g_no=" + g_no + ", stock=" + stock + ", purchase_price=" + purchase_price + ", sell_price="
				+ sell_price + ", saving_mileage=" + saving_mileage + ", discount_price=" + discount_price
				+ ", discount_rate=" + discount_rate + ", name=" + name + ", detail=" + detail + ", status=" + status
				+ ", mainImg=" + mainImg + ", images=" + images + ", history=" + history + ", goodsOptions="
				+ goodsOptions + ", optionStocks=" + optionStocks + "]";
	}
}
