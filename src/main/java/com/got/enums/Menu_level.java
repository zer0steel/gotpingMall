package com.got.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.got.dao.CategoryDao;
import com.got.vo.CategoryVO;

@JsonFormat(shape = Shape.OBJECT)
public enum Menu_level {
	BIG(1, "��з�", new HashMap<Integer, CategoryVO>()), 
	MIDDLE(2, "�ߺз�", new HashMap<Integer, CategoryVO>()), 
	SMALL(3, "�Һз�", new HashMap<Integer, CategoryVO>());
	
	private static final Map<Integer, Menu_level> map = new HashMap<>();
	static {
		for(Menu_level m : values())
			map.put(m.menu_level, m);
	}
	
	@JsonIgnore
	private Map<Integer, CategoryVO> categories;
	private String kor_name;
	private int menu_level;

	private Menu_level(int menu_level, String kor_name, Map<Integer, CategoryVO> categories) {
		this.menu_level = menu_level;
		this.kor_name = kor_name;
		this.categories = categories;
	}
	
	public String getKorName() 						
	{	return this.kor_name;	}
	public int getCode() 							
	{	return this.menu_level;	}
	public Map<Integer, CategoryVO> getCategories() 
	{	return this.categories;	}
	
	public static void addCategory(CategoryVO c) {
		if(c.getC_no() == 0)
			throw new IllegalArgumentException("PK���� c_no ���� 0��");
		c.getMenuLevel().categories.put(c.getC_no(), c);
	}
	
	public static void groupingCategories(List<CategoryVO> categories) {
		for(CategoryVO c : categories) 
			// �з��� �Ҽӵ� �з������� �ش� �з������� �߰��Ѵ�. 
			c.getMenuLevel().categories.put(c.getC_no(), c);
	}
	
	public static Menu_level of(int menu_level) {
		Menu_level m = map.get(menu_level);
		if(m == null)
			throw new IllegalArgumentException("�������� �ʴ� �з� �ڵ� : " + menu_level);
		return m;
	}

	public static void deleteCategory(int c_no) {
		for(Menu_level lvl : values())
			if(deleteCategory(lvl, c_no)) 
				return;
		throw new IllegalArgumentException("enum �ȿ� �ִ� map�� �����ϴ� �з���ȣ�� �ƴ� : " + c_no);
	}
	
	private static boolean deleteCategory(Menu_level lvl, int c_no) {
		if(lvl.categories.get(c_no) != null) {
			lvl.categories.remove(c_no);
			return true;
		}
		return false;
	}

	public static void updateCategory(CategoryVO c) {
		for(Menu_level lvl : values())
			if(updateCategory(lvl, c))
				return;
		throw new IllegalArgumentException("enum �ȿ� �ִ� map�� �����ϴ� �з���ȣ�� �ƴ� : " + c.getC_no());
	}
	
	private static boolean updateCategory(Menu_level lvl, CategoryVO c) {
		if(lvl.categories.get(c.getC_no()) != null) {
			lvl.categories.remove(c.getC_no());
			lvl.categories.put(c.getC_no(), c);
			return true;
		}
		return false;
	}
}
