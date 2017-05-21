package com.got.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.got.mapper.files.FileMapper;
import com.got.mapper.files.GoodsImageMapper;
import com.got.vo.goods.GoodsVO;

@Service
public class GoodsImageService {
	
	@Inject private FileService fs;
	@Inject private GoodsImageMapper goodsImageMapper;
	@Inject private FileMapper fileMapper;
	
	public void insertGoodsImage(GoodsVO g) {
		fs.setupImages(g.getC_no(), g.getImages()).forEach(vo -> {
			vo.setG_no(g.getG_no());
			goodsImageMapper.insert(vo);
			fileMapper.updatePath(vo);
		});
	}
}
