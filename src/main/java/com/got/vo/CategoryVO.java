package com.got.vo;

public class CategoryVO {
	private int c_no, parent_no, step;
	private String title;
	private boolean in_use;
	
	public int getC_no() {
		return c_no;
	}
	public void setC_no(int c_no) {
		this.c_no = c_no;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getParent_no() {
		return parent_no;
	}
	public void setParent_no(int parent_no) {
		this.parent_no = parent_no;
	}
	public int getStep() {
		return step;
	}
	public void setStep(int step) {
		if(step > 2 || step < 0)
			throw new IllegalArgumentException("분류 범위는 0 ~ 2까지. input value : " + step);
		this.step = step;
	}
	public boolean isIn_use() {
		return in_use;
	}
	public void setIn_use(boolean in_use) {
		this.in_use = in_use;
	}
}