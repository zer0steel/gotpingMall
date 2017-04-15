package com.got.vo;

import com.got.enums.Menu_level;

public class CategoryVO {
	private int c_no, parent_no;
	private String title;
	private boolean in_use;
	private Menu_level menu_level;
	
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
	public Menu_level getMenu_level() {
		return menu_level;
	}
	public void setMenu_level(int menu_level) {
		this.menu_level = Menu_level.of(menu_level);
	}
	public boolean isIn_use() {
		return in_use;
	}
	public void setIn_use(boolean in_use) {
		this.in_use = in_use;
	}
}