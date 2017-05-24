package com.got.service.goods;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.got.enums.Level;
import com.got.mapper.goods.CategoryMapper;
import com.got.mapper.goods.OptionMapper;
import com.got.util.CommonUtil;
import com.got.util.ModelAndView;
import com.got.vo.goods.CategoryVO;
import com.got.vo.goods.LevelVO;

@Service
public class CategoryService {
	
	@Inject private CategoryMapper categoryMapper;
	@Inject private OptionMapper optionMapper;
	
	public Map<Level, LevelVO> getLevels() {
		Map<Level, LevelVO> levels = new HashMap<>();
		levels.put(Level.BIG, createLevelVO(Level.BIG));
		levels.put(Level.MIDDLE, createLevelVO(Level.MIDDLE));
		levels.put(Level.SMALL, createLevelVO(Level.SMALL));
		return levels;
	}
	
	private LevelVO createLevelVO(Level level) {
		List<CategoryVO> categories = categoryMapper.selectListWithLevel(level.getCode());
		LevelVO levelVO = new LevelVO();
		levelVO.setCategories(categories);
		levelVO.setLevels(level);
		return levelVO;
	}
	
	public List<CategoryVO> getAll() {
		return categoryMapper.selectAll();
	}

	public List<CategoryVO> getSubList(Integer c_no) {
		Objects.requireNonNull(c_no);
		return categoryMapper.selectSubList(c_no);
	}
	
	public Map<Integer, CategoryVO> getCategories() {
		Map<Integer, CategoryVO> map = new HashMap<>();
		categoryMapper.selectListWithLevel(Level.BIG.getCode()).forEach(vo -> {
			map.put(vo.getC_no(), vo);
		});
		categoryMapper.selectListWithLevel(Level.MIDDLE.getCode()).forEach(vo -> {
			map.get(vo.getSuper_no()).addSub(vo);
		});
		return map;
	}
	
	public CategoryVO getCategoryWithOption(Integer c_no) {
		Objects.requireNonNull(c_no);
		CategoryVO c = categoryMapper.selectOne(c_no);
		c.setOptions(optionMapper.selectListWithC_no(c_no));
		return c;
	}
	
	public void enroll(CategoryVO c) {
		validationCheck(c);
		categoryMapper.insert(c);
	}

	public void deleteOne(Integer c_no) {
		Objects.requireNonNull(c_no);
		if(categoryMapper.selectSubList(c_no).size() > 0)
			return;
		categoryMapper.deleteOne(c_no);
	}
	
	public void update(CategoryVO c) {
		validationCheck(c);
		Objects.requireNonNull(c.getC_no());
		categoryMapper.updateOne(c);
	}
	
	private void validationCheck(CategoryVO c) {
		if(c.getLevels() != Level.BIG) {
			Objects.requireNonNull(c.getSuper_no(), "최상위 분류가 아닌데 상위 분류가 존재하지 않음.");
			if(c.getSuper_no() == 0)
				throw new IllegalArgumentException("최상위 분류가 아닌데 상위 분류가 존재하지 않음.");
		}
	}

}
