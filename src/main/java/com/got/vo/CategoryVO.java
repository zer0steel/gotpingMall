package com.got.vo;

import java.util.ArrayList;
import java.util.List;

import com.got.enums.MenuLevel;

public class CategoryVO {
	private int c_no, super_no;
	private String title;
	private MenuLevel menu_level;
	private List<CategoryVO> sub = new ArrayList<>();;
	
	public int getC_no() {
		return c_no;
	}
	public void setC_no(int c_no) {
		this.c_no = c_no;
		if(MenuLevel.isSetting())
			settingThis();
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
	public void setSuper_no(int super_no) {
		this.super_no = super_no;
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
	public void settingThis() {
		if( this.c_no > 0 ) {
			CategoryVO c = MenuLevel.getCategory(c_no);
			this.menu_level = c.menu_level;
			this.super_no = c.super_no;
			this.title = c.title;
		}
	}
	@Override
	public String toString() {
		return "CategoryVO [c_no=" + c_no + ", super_no=" + super_no + ", title=" + title + ", menu_level="
				+ menu_level + "]";
	}
	public List<CategoryVO> getSub() {
		return sub;
	}
	public void addSub(CategoryVO sub) {
		this.sub.add(sub);
	}
}