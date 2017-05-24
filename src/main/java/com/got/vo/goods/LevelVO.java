package com.got.vo.goods;

import java.util.ArrayList;
import java.util.List;

import com.got.enums.Level;

public class LevelVO {
	
	private Level levels;
	private List<CategoryVO> categories = new ArrayList<>();
	
	public Level getLevels() {
		return levels;
	}
	public void setLevels(Level levels) {
		this.levels = levels;
	}
	public List<CategoryVO> getCategories() {
		return categories;
	}
	public void setCategories(List<CategoryVO> categories) {
		this.categories = categories;
	}
	public void addCategory(CategoryVO c) {
		this.categories.add(c);
	}
}
