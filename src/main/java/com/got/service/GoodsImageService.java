package com.got.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.got.mapper.files.FileMapper;
import com.got.mapper.files.GoodsImageMapper;
import com.got.mapper.goods.CategoryMapper;
import com.got.vo.goods.GoodsVO;

@Service
public class GoodsImageService {
	
	@Inject private FileService fs;
	@Inject private GoodsImageMapper goodsImageMapper;
	@Inject private FileMapper fileMapper;
	@Inject private CategoryMapper categoryMapper;
	
	public void insertGoodsImage(GoodsVO g) {
		//대분류명을 저장폴더명으로 한다.
		String folderName = categoryMapper.selectBigWithC_no(g.getC_no()).getTitle();
		fs.setupImages(folderName, g.getImages()).forEach(vo -> {
			vo.setG_no(g.getG_no());
			goodsImageMapper.insert(vo);
			fileMapper.updatePath(vo);
		});
	}
}
