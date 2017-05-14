package com.got.vo;

import java.util.Arrays;
import java.util.List;

public class GoodsOptionVO extends OptionsVO {
	private Integer g_no;
	private String value, extra_cost;
	private List<String> values, extra_costs;
	private int optionCount;
	private List<GoodsOptionVO> list;
	
	public String getExtra_cost() {
		return extra_cost;
	}
	public void setExtra_cost(String extra_cost) {
		this.extra_cost = extra_cost;
		this.setExtra_costs(Arrays.asList(extra_cost.split("/")));
	}
	public List<String> getExtra_costs() {
		return extra_costs;
	}
	public void setExtra_costs(List<String> extra_costs) {
		this.extra_costs = extra_costs;
		this.optionCount = extra_costs.size();
		this.extra_cost = String.join("/", extra_costs);
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
		this.setValues(Arrays.asList(value.split("/")));
	}
	public List<String> getValues() {
		return values;
	}
	public void setValues(List<String> values) {
		this.values = values;
		this.optionCount = values.size();
		this.value = String.join("/", values);
	}
	@Override
	public String toString() {
		return "GoodsOptionVO [g_no=" + g_no + ", value=" + value + ", extra_cost=" + extra_cost + ", values=" + values
				+ ", extra_costs=" + extra_costs + ", optionCount=" + optionCount + "]";
	}
	public List<GoodsOptionVO> getList() {
		return list;
	}
	public void setList(List<GoodsOptionVO> list) {
		this.list = list;
	}
}
