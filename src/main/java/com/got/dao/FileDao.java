package com.got.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.got.dao.template.DaoTemplate;
import com.got.vo.FileVO;

@Repository
public class FileDao {

	@Autowired private DaoTemplate dao;
	public void insert(FileVO f) {
		dao.insert("f.insert", f);
	}
	public void delete(int f_no) {
		dao.delete("f.delete", f_no);
	}
}
