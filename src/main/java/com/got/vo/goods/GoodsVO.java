package com.got.vo.goods;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.got.enums.GoodsStatus;
import com.got.vo.GoodsImgVO;

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
	
	public GoodsVO() {
	}
	
	public GoodsVO(Integer g_no) {
		this.g_no = g_no;
		this.optionStocks = new ArrayList<>();
	}

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
		if( Objects.nonNull(images) )
			for(GoodsImgVO img : images)
				if("main".equals(img.getLocation())) {
					this.mainImg = img;
					break;
				}
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

	@Override
	public String toString() {
		return "GoodsVO [g_no=" + g_no + ", stock=" + stock + ", purchase_price=" + purchase_price + ", sell_price="
				+ sell_price + ", saving_mileage=" + saving_mileage + ", discount_price=" + discount_price
				+ ", discount_rate=" + discount_rate + ", name=" + name + ", detail=" + detail + ", status=" + status
				+ ", mainImg=" + mainImg + ", images=" + images + ", history=" + history + ", goodsOptions="
				+ goodsOptions + ", optionStocks=" + optionStocks + "]";
	}

	public void setExtraCost(List<OptionStockVO> dbDataStocks) {
		for(int i = 0; i < dbDataStocks.size(); i++)
			this.optionStocks.get(i).setOs_extra_cost(dbDataStocks.get(i).getOs_extra_cost());
	}
}
