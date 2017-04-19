package com.got.enums;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.got.vo.CategoryVO;

/**
 * 각 분류 레벨 별로 자신에게 속해있는 분류가 저장되어 있는 map이 존재함.<br>
 * jsp에서 사용할때 맵에서 꺼내써야 하기 때문에 key 값은 ${변수명.key } , value 값은 ${변수명.value } 로 가져다 쓸 수 있다.
 * @author Jang
 *
 */
@JsonFormat(shape = Shape.OBJECT)
public enum MenuLevel {
	BIG(1, "대분류", new HashMap<Integer, CategoryVO>()), 
	MIDDLE(2, "중분류", new HashMap<Integer, CategoryVO>()), 
	SMALL(3, "소분류", new HashMap<Integer, CategoryVO>());
	
	private static final Map<Integer, MenuLevel> map = new HashMap<>();
	static {
		for(MenuLevel m : values())
			map.put(m.menu_level, m);
	}
	
	@JsonIgnore
	private Map<Integer, CategoryVO> categories;
	private String korName;
	private int menu_level;

	private MenuLevel(int menu_level, String korName, Map<Integer, CategoryVO> categories) {
		this.menu_level = menu_level;
		this.korName = korName;
		this.categories = categories;
	}
	
	public String getKorName() 						
	{	return this.korName;	}
	public int getCode() 							
	{	return this.menu_level;	}
	public Map<Integer, CategoryVO> getCategories() 
	{	return this.categories;	}
	
	/**
	 * 분류가 나뉘어져있지 않은 모든 분류 list를 분류레벨에 맞게 해당 enum의 map에 넣는다.
	 * @param categories 분류가 나뉘어져 있지 않는 분류들
	 */
	public static void groupingCategories(List<CategoryVO> categories) {
		for(CategoryVO c : categories) 
			c.getMenuLevel().categories.put(c.getC_no(), c);
	}
	
	public static MenuLevel of(int menu_level) {
		MenuLevel m = map.get(menu_level);
		if(m == null)
			throw new IllegalArgumentException("존재하지 않는 분류 레벨 : " + menu_level);
		return m;
	}
}
