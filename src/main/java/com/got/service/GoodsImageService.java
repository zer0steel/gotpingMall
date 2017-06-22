package com.got.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.got.mapper.files.FileMapper;
import com.got.mapper.files.GoodsImageMapper;
import com.got.mapper.goods.CategoryMapper;
import com.got.util.FileUtil;
import com.got.vo.file.GoodsImageVO;
import com.got.vo.goods.GoodsVO;

@Service
public class GoodsImageService {
	
	@Inject private GoodsImageMapper goodsImageMapper;
	@Inject private FileMapper fileMapper;
	
	public void insertGoodsImage(List<GoodsImageVO> images, Integer g_no) {
		images.forEach(vo -> {
			FileUtil.moveToSavePath(vo.getLocation(), vo);
			vo.setG_no(g_no);
			goodsImageMapper.insert(vo);
			fileMapper.updatePath(vo);
		});
	}
}
