package com.got.vo;

import com.got.enums.MenuLevel;

public class CategoryVO {
	private int c_no, parent_no;
	private String title;
	private boolean in_use;
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
	public int getParent_no() {
		return parent_no;
	}
	public void setParent_no(int parent_no) {
		this.parent_no = parent_no;
	}
	public boolean isIn_use() {
		return in_use;
	}
	public void setIn_use(boolean in_use) {
		this.in_use = in_use;
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
		return "CategoryVO [c_no=" + c_no + ", parent_no=" + parent_no + ", title=" + title + ", in_use=" + in_use
				+ ", menu_level=" + menu_level + "]";
	}
}