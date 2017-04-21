package com.got.enums;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

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
	
	private static boolean setting = false;

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
	public static boolean isSetting() {
		return MenuLevel.setting;
	}
	
	/**
	 * categoryVO에서 c_no가 할당될때 분류가 세팅되어 있으면 자동적으로 해당 VO는 관련 정보들로 세팅되게 되어있음.<br>
	 * 세팅을 false로 안바꾸면 새로운 분류가 추가될때 시퀀스에서 새로운 번호를 할당하면 
	 * 해당 분류 레벨에서 존재하지 않는 새로운 번호라서 에러가 뜬다.<br>
	 * 그걸 회피하려면 미리 이 메서드를 실행시켜야함.
	 */
	public static void insertSetting() {
		MenuLevel.setting = false;
	}
	
	/**
	 * 분류가 나뉘어져있지 않은 모든 분류 list를 분류레벨에 맞게 해당 enum의 map에 넣는다.
	 * @param categories 분류가 나뉘어져 있지 않는 분류들
	 */
	public static void groupingCategories(List<CategoryVO> categories) {
		MenuLevel.BIG.categories.clear();
		MenuLevel.MIDDLE.categories.clear();
		MenuLevel.SMALL.categories.clear();
		
		for(CategoryVO c : categories) 
			c.getMenuLevel().categories.put(c.getC_no(), c);
		setting = true;
	}
	
	public static MenuLevel of(int menu_level) {
		MenuLevel m = map.get(menu_level);
		if(m == null)
			throw new IllegalArgumentException("존재하지 않는 분류 레벨 : " + menu_level);
		return m;
	}

	public static CategoryVO getCategory(int c_no) {
		for(MenuLevel lvl : values()) {
			CategoryVO c = lvl.categories.get(c_no);
			if( c != null )
				return c;
		}
		throw new IllegalArgumentException("존재하지 않는 분류번호 : " + c_no);
	}

	/**
	 * 해당 분류번호가 가지고 있는 최상위의 분류 레벨을 가져온다.
	 * @param c_no
	 * @return
	 */
	public static CategoryVO findBigCategory(int c_no) {
		CategoryVO c = MenuLevel.getCategory(c_no);
		if(c.getMenuLevel() == MenuLevel.BIG) 
			return c;
		else if(c.getMenuLevel() == MenuLevel.MIDDLE)
			return MenuLevel.getCategory(c.getSuper_no());
		else {
			CategoryVO middle = MenuLevel.getCategory(c.getSuper_no());
			return MenuLevel.getCategory(middle.getSuper_no());
		}
	}
}
