package com.got.vo.goods;

import java.util.ArrayList;
import java.util.List;

import com.got.enums.Level;

public class CategoryVO {
	private Integer c_no, super_no;
	private String title;
	private Level levels;
	private List<CategoryVO> sub = new ArrayList<>();
	private List<OptionVO> options;
	
	public CategoryVO() {}
	public CategoryVO(Integer c_no) {
		this.c_no = c_no;
	}
	public Integer getC_no() {
		return c_no;
	}
	public void setC_no(Integer c_no) {
		this.c_no = c_no;
	}
	public Integer getSuper_no() {
		return levels == Level.BIG ? null : super_no;
	}
	public void setSuper_no(Integer super_no) {
		this.super_no = super_no;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Level getLevels() {
		return levels;
	}
	public void setLevels(int levels) {
		this.levels = Level.of(levels);
	}
	public List<CategoryVO> getSub() {
		return sub;
	}
	public void setSub(List<CategoryVO> sub) {
		this.sub = sub;
	}
	public List<OptionVO> getOptions() {
		return options;
	}
	public void setOptions(List<OptionVO> options) {
		this.options = options;
	}
	public void addSub(CategoryVO c) {
		this.sub.add(c);
	}
	@Override
	public String toString() {
		return "CategoryVO [c_no=" + c_no + ", super_no=" + super_no + ", title=" + title + ", levels=" + levels
				+ ", sub=" + sub + ", options=" + options + "]";
	}
}