package com.got.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;

import com.got.controller.FileController;
import com.got.vo.FileVO;
import com.got.vo.GoodsImgVO;

public class FileUtil {
	
	private static final Logger log = Logger.getLogger(FileUtil.class);
	
	public static FileVO saveFileInTempPath(String path, MultipartFile uploadFile) {
		int save_name = (int)System.currentTimeMillis();
		try {
			FileOutputStream out = new FileOutputStream(path + "/" + save_name);
			out.write(uploadFile.getBytes());
			out.flush();
			out.close();
			
			FileVO f = new FileVO();
			f.setReal_name(uploadFile.getOriginalFilename());
			f.setSave_name(save_name);
			f.setSave_path(path);
			
			return f;
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	public static boolean deleteTempFile(int save_name, String path) {
		File file = new File(path + "/" + save_name);
		if(file.exists()) {
			return file.delete();
		}
		throw new IllegalArgumentException("파일 삭제 실패. save_name : " + save_name + " | path : " + path);
	}

	public static <T extends FileVO> ArrayList<T> moveToSavePath(String folderName, ArrayList<T> list) {
		String tempPath = FileController.tempPath;
		String savePath = tempPath.substring(0, tempPath.lastIndexOf("/")) + "/" + folderName;
		for(T fileVO : list) {
			File tempFile = new File(tempPath + "/" + fileVO.getSave_name());
			File saveFile = new File(savePath + "/" + fileVO.getSave_name());
			fileVO.setSave_path(saveFile.getAbsolutePath());
			if( !tempFile.renameTo(saveFile) ) {
				log.error("파일 위치 변경 실패");
				log.error("파일명 : " + tempFile.getName());
			}
		}
		return list;
	}
}
