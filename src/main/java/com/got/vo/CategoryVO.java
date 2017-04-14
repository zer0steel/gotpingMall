package com.got.vo;

public class CategoryVO {
	public static final int BIG = 1, MIDDLE = 2, SMALL = 3;
	private int c_no, parent_no, menu_level;
	private String title, menu_levelName;
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
	public int getMenu_level() {
		return menu_level;
	}
	public void setMenu_level(int menu_level) {
		if(menu_level > SMALL || menu_level < BIG)
			throw new IllegalArgumentException("분류 범위는 1 ~ 3까지. input value : " + menu_level);
		this.menu_level = menu_level;
		switch (menu_level) {
			case BIG: this.menu_levelName = "대분류"; break;
			case MIDDLE: this.menu_levelName = "중분류"; break;
			case SMALL: this.menu_levelName = "소분류"; break;
		}
	}
	public boolean isIn_use() {
		return in_use;
	}
	public void setIn_use(boolean in_use) {
		this.in_use = in_use;
	}
	
	public String getmenu_levelName() {
		return this.menu_levelName;
	}
	
	public int getBIG() {
		return CategoryVO.BIG;
	}
	
	public int getMIDDLE() {
		return CategoryVO.MIDDLE;
	}
	
	public int getSMALL() {
		return CategoryVO.SMALL;
	}
}