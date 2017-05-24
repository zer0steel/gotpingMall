package com.got.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.inject.Inject;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.got.enums.Level;
import com.got.mapper.files.FileMapper;
import com.got.util.FileUtil;
import com.got.vo.file.FileVO;
import com.got.vo.file.GoodsImageVO;

@Service
public class FileService {
	
	static Logger log = Logger.getLogger(FileService.class);
	@Inject private FileMapper fileMapper;
	
	public FileVO saveFileInTempPath(String path, MultipartFile uploadFile) {
		if(Objects.requireNonNull(uploadFile).isEmpty())
			throw new RuntimeException("업로드된 파일 내용물이 비어있음");
		FileVO f = FileUtil.saveFileInTempPath(path, uploadFile);
		try{
			fileMapper.insert(f);
		}catch(Exception e) {
			deleteFile(f);
			log.error(e);
			throw new RuntimeException("데이터베이스에 파일정보 저장이 실패하여 앞서 업로드된 파일 삭제 수행");
		}
		f.setSave_path(null);
		return f;
	}
	
	public List<FileVO> saveFileInTempPath(String path, List<MultipartFile> files) {
		List<FileVO> fileVOs = new ArrayList<>();
		files.forEach(file -> {
			FileVO f = saveFileInTempPath(path, file);
			fileVOs.add(f);
		});
		return fileVOs;
	}

	private void deleteFile(FileVO f) {
		boolean isDelete = FileUtil.deleteTempFile(f.getSave_name(), f.getSave_path());
		if(isDelete)
			return;
		else
			throw new RuntimeException("삭제 실패, 확인바람");
	}
	
	public List<GoodsImageVO> setupImages(String folderName, List<GoodsImageVO> tempImages) {
		Objects.requireNonNull(tempImages);
		return FileUtil.moveToSavePath(folderName, tempImages);
	}
}
