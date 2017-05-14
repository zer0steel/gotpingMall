package com.got.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.got.dao.FileDao;
import com.got.enums.MenuLevel;
import com.got.util.CommonUtil;
import com.got.util.FileUtil;
import com.got.vo.CategoryVO;
import com.got.vo.FileVO;
import com.got.vo.GoodsImgVO;

@Service
public class FileService {
	
	@Autowired private FileDao dao;
	static Logger logger = Logger.getLogger(FileService.class);
	
	public FileVO saveFileInTempPath(String path, MultipartFile uploadFile) {
		FileVO f = FileUtil.saveFileInTempPath(path, uploadFile);
		try{
			dao.insert(f);
		}catch(Exception e) {
			deleteFile(f);
			logger.error(e);
			throw new RuntimeException("데이터베이스에 파일정보 저장이 실패하여 앞서 업로드된 파일 삭제 수행");
		}
		f.setSave_path(null);
		return f;
	}
	
	public List<FileVO> saveFileInTempPath(String path, List<MultipartFile> files) {
		List<FileVO> fileVOs = new ArrayList<>();
		files.forEach(file -> {
			FileVO f = FileUtil.saveFileInTempPath(path, file);
			try{
				dao.insert(f);
			}catch(Exception e) {
				deleteFile(f);
				logger.error(e);
				throw new RuntimeException("데이터베이스에 파일정보 저장이 실패하여 앞서 업로드된 파일 삭제 수행");
			}
			f.setSave_path(null);
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

	public List<GoodsImgVO> setupImages(Integer c_no, String[] fileInfoJSON) {
		Objects.requireNonNull(c_no);
		if(Objects.isNull(fileInfoJSON))
			return new ArrayList<>();
		
		String folderName = getSaveFolderName(c_no);
		List<GoodsImgVO> tempGoodsImgs = CommonUtil.getVO(fileInfoJSON, GoodsImgVO.class);
		return FileUtil.moveToSavePath(folderName, tempGoodsImgs);
	}
	
	private String getSaveFolderName(Integer g_no) {
		return MenuLevel.findBigCategory(g_no).getTitle();
	}
}
