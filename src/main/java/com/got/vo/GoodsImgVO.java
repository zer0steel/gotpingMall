package com.got.vo;

public class GoodsImgVO extends FileVO{
	
	private int g_no;
	private String location;
	public int getG_no() {
		return g_no;
	}
	public void setG_no(int g_no) {
		this.g_no = g_no;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	@Override
	public String toString() {
		return super.toString() + "\nGoodsImgVO [g_no=" + g_no + ", location=" + location + "]";
	}
}
