package com.got.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.got.dao.template.DaoTemplate;
import com.got.vo.FileVO;
import com.got.vo.GoodsImageVO;

@Repository
public class FileDao {

	@Autowired private DaoTemplate dao;
	public void insert(FileVO f) {
		dao.insert("f.insert", f);
	}
	public void delete(int f_no) {
		dao.delete("f.delete", f_no);
	}
	public List<GoodsImageVO> selectGoodsImg(int g_no) {
		return dao.selectList("f.selectGoodsImg", g_no);
	}
}
