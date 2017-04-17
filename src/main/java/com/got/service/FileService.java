package com.got.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.got.dao.FileDao;
import com.got.util.CommonUtil;
import com.got.vo.FileVO;

@Service
public class FileService {
	
	@Autowired private FileDao dao;
	
	public void saveFileInTempPath(String path, MultipartFile uploadFile) {
		FileVO f = CommonUtil.saveFileInTempPath(path, uploadFile);
		try{
			if(dao.insert(f) == 1)
				return;
		}catch(Exception e) {
			deleteFile(f);
			System.err.println(e);
			throw new RuntimeException("데이터베이스에 파일정보 저장이 실패하여 앞서 업로드된 파일 삭제 수행");
		}
		
		
		
	}

	private void deleteFile(FileVO f) {
		boolean isDelete = CommonUtil.deleteTempFile(f.getSave_name(), f.getSave_path());
		if(isDelete)
			return;
		else
			throw new RuntimeException("삭제 실패, 확인바람");
	}
}
