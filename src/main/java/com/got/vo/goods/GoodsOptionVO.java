package com.got.vo.goods;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class GoodsOptionVO extends OptionVO {
	private Integer g_no;
	private String value = "", extra_cost = "";
	private List<Detail> details;
	private boolean isSetupExtraCost;
	private boolean isSetupValue;
	
	@Override
	public String toString() {
		return "GoodsOptionVO [g_no=" + g_no + ", value=" + value + ", extra_cost=" + extra_cost + ", details="
				+ details + "]";
	}
	
	public Integer getG_no() {
		return g_no;
	}
	public void setG_no(Integer g_no) {
		this.g_no = g_no;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
		this.isSetupValue = true;
		setupDetails();
	}

	public String getExtra_cost() {
		return extra_cost;
	}

	public void setExtra_cost(String extra_cost) {
		this.extra_cost = extra_cost;
		this.isSetupExtraCost = true;
		setupDetails();
	}
	
	public List<Detail> getDetails() {
		return details;
	}
	
	public void setDetails(List<Detail> details) {
		this.details = details;
	}
	
	private static final String SEPARATOR_CHAR = "/ "; 
	
	public void setupStrings() {
		for (Detail detail : details) {
			this.value += detail.getValue() + SEPARATOR_CHAR;
			this.extra_cost += detail.getExtra_cost() + SEPARATOR_CHAR;
		}
		this.value = this.value.substring(0, this.value.lastIndexOf(SEPARATOR_CHAR));
		this.extra_cost = this.extra_cost.substring(0, this.extra_cost.lastIndexOf(SEPARATOR_CHAR));
	}
	
	private void setupDetails() {
		if (this.isSetupExtraCost == false || this.isSetupValue == false)
			return;

		if (Objects.isNull(this.details))
			this.details = new ArrayList<>();

		String[] values = this.value.split(SEPARATOR_CHAR);
		String[] extraCosts = this.extra_cost.split(SEPARATOR_CHAR);
		for (int i = 0; i < values.length; i++) {
			Detail detail = new Detail();
			detail.setValue(values[i]);
			detail.setExtra_cost(Integer.parseInt(extraCosts[i]));
			this.details.add(detail);
		}
	}
	

	public static class Detail {
		String value;
		int extra_cost;
		
		public Detail() {}
		public Detail(String value, int extra_cost) {
			super();
			this.value = value;
			this.extra_cost = extra_cost;
		}

		@Override
		public String toString() {
			return "Detail [value=" + value + ", extra_cost=" + extra_cost + "]";
		}
		
		public String getValue() {
			return value;
		}
		
		public void setValue(String value) {
			this.value = value;
		}
		
		public int getExtra_cost() {
			return extra_cost;
		}
		
		public void setExtra_cost(int extra_cost) {
			this.extra_cost = extra_cost;
		}
	}
}
