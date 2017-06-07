package com.got.vo.goods;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.got.enums.GoodsStatus;
import com.got.vo.deal.DealVO;
import com.got.vo.file.GoodsImageVO;

public class GoodsVO extends CategoryVO {
	private Integer g_no;
	private BigDecimal purchase_price, sell_price, discount_price;
	private int stock;
	private double discount_rate, saving_mileage;
	private String name, detail;
	private GoodsImageVO mainImg;
	private List<GoodsImageVO> images;
	private List<DealVO> history;
	private List<GoodsOptionVO> goodsOptions = new ArrayList<>();
	private List<StockVO> stocks;
	private GoodsStatus status;
	
	@Override
	public String toString() {
		return "GoodsVO [g_no=" + g_no + ", purchase_price=" + purchase_price + ", sell_price=" + sell_price
				+ ", discount_price=" + discount_price + ", stock=" + stock + ", discount_rate=" + discount_rate
				+ ", saving_mileage=" + saving_mileage + ", name=" + name + ", detail=" + detail + ", mainImg="
				+ mainImg + ", images=" + images + ", history=" + history + ", goodsOptions=" + goodsOptions
				+ ", stocks=" + stocks + ", status=" + status + ", toString()=" + super.toString() + "]";
	}

	public GoodsVO() {}
	public GoodsVO(Integer g_no) {
		this.g_no = g_no;
		this.stocks = new ArrayList<>();
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

	public BigDecimal getPurchase_price() {
		return purchase_price;
	}

	public void setPurchase_price(BigDecimal purchase_price) {
		this.purchase_price = purchase_price;
	}

	public BigDecimal getSell_price() {
		return sell_price;
	}

	public void setSell_price(BigDecimal sell_price) {
		this.sell_price = sell_price;
	}

	public double getDiscount_rate() {
		return discount_rate;
	}

	public void setDiscount_rate(double discount_rate) {
		this.discount_rate = discount_rate;
	}

	public double getSaving_mileage() {
		return saving_mileage;
	}

	public void setSaving_mileage(double saving_mileage) {
		this.saving_mileage = saving_mileage;
	}

	public int getStatus_code() {
		if(Objects.isNull(status))
			return 0;
		return status.getCode();
	}

	public void setStatus_code(Integer status) {
		this.status = GoodsStatus.of(status);
	}

	public GoodsStatus getStatus() {
		return status;
	}

	public void setStatus(GoodsStatus status) {
		this.status = status;
	}

	public List<DealVO> getHistory() {
		return history;
	}

	public void setHistory(List<DealVO> history) {
		this.history = history;
	}

	public List<GoodsImageVO> getImages() {
		return images;
	}

	public void setImages(List<GoodsImageVO> images) {
		this.images = images;
	}

	public void updateStock(int amount) {
		this.stock += amount;
	}

	public GoodsImageVO getMainImg() {
		if(Objects.isNull(mainImg) && Objects.nonNull(images))
			for(GoodsImageVO img : images)
				if("main".equals(img.getLocation()))
					return img;
		
		return mainImg;
	}

	public void setMainImg(GoodsImageVO mainImg) {
		this.mainImg = mainImg;
	}

	public List<GoodsOptionVO> getGoodsOptions() {
		return goodsOptions;
	}

	public void setGoodsOptions(List<GoodsOptionVO> goodsOptions) {
		this.goodsOptions = goodsOptions;
	}

	public List<StockVO> getStocks() {
		return stocks;
	}

	public void setStocks(List<StockVO> stocks) {
		this.stocks = stocks;
		this.stock = stocks.stream().mapToInt(vo -> vo.getAmount()).sum();
	}
	
	public BigDecimal getRealPrice() {
		double rate = (100 - this.discount_rate) / 100;
		return this.sell_price.multiply(BigDecimal.valueOf(rate));
	}

	public void setExtraCost(List<StockVO> dbDataStocks) {
		for(int i = 0; i < dbDataStocks.size(); i++)
			this.stocks.get(i).setExtra_cost(dbDataStocks.get(i).getExtra_cost());
	}
}
