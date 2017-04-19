package com.got.vo;

import com.got.enums.MenuLevel;

public class CategoryVO {
	private int c_no, super_no;
	private String title;
	private MenuLevel menu_level;
	
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
	public int getSuper_no() {
		return super_no;
	}
	public void setSuper_no(int parent_no) {
		this.super_no = parent_no;
	}
	public int getMenu_level() {
		return menu_level.getCode();
	}
	public void setMenu_level(int menu_level) {
		this.menu_level = MenuLevel.of(menu_level);
	}
	public MenuLevel getMenuLevel() {
		return menu_level;
	}
	@Override
	public String toString() {
		return "CategoryVO [c_no=" + c_no + ", super_no=" + super_no + ", title=" + title + ", menu_level="
				+ menu_level + "]";
	}
}