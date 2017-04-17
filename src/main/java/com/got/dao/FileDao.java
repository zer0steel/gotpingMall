package com.got.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.got.dao.template.DaoTemplate;
import com.got.vo.FileVO;

@Repository
public class FileDao {

	@Autowired private DaoTemplate dao;
	public int insert(FileVO f) {
		return dao.insert("f.insert", f);
	}
	public int delete(int f_no) {
		return dao.delete("f.delete", f_no);
	}
}
