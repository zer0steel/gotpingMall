package com.got.vo;

import java.util.List;

public class GoodsOptionVOTest extends OptionsVO {
	private Integer g_no;
	private List<Detail> details;
	
	@Override
	public String toString() {
		return "GoodsOptionVOTest [g_no=" + g_no + ", details=" + details + "]";
	}
	
	public Integer getG_no() {
		return g_no;
	}
	public void setG_no(Integer g_no) {
		this.g_no = g_no;
	}

	public List<Detail> getDetails() {
		return details;
	}
	public void setDetails(List<Detail> details) {
		this.details = details;
	}

	public static class Detail {
		String value;
		int extra_cost;
		
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
