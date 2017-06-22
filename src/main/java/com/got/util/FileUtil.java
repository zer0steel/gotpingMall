package com.got.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;

import com.got.controller.FileController;
import com.got.enums.Level;
import com.got.vo.file.FileVO;

public class FileUtil {
	
	private static final Logger log = Logger.getLogger(FileUtil.class);
	
	public static FileVO saveFileInTempPath(String path, MultipartFile file) {
		checkFolder(path);
		File saveFile = createFile(path);
		
		try {
			FileOutputStream out = new FileOutputStream(saveFile);
			out.write(file.getBytes());
			out.flush();
			out.close();
			
			FileVO fVO = new FileVO();
			fVO.setReal_name(file.getOriginalFilename());
			fVO.setSave_name(Integer.parseInt(saveFile.getName()));
			fVO.setSave_path( simpleSavePath(path) );
			
			return fVO;
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

	public static <T extends FileVO> T moveToSavePath(String folderName, T file) {
		String tempPath = FileController.tempPath;
		String savePath = tempPath.substring(0, tempPath.lastIndexOf(File.separator) + 1) + folderName;
		
		checkFolder(savePath);
		
		File tempFile = new File(tempPath + File.separator + file.getSave_name());
		File saveFile = new File(savePath + File.separator + file.getSave_name());
		if( !tempFile.renameTo(saveFile) ) {
			log.fatal("파일 위치 변경 실패");
			log.fatal("파일명 : " + tempFile.getName());
			throw new RuntimeException();
		}
		
		file.setSave_path(simpleSavePath(savePath));
		return file;
	}
	
	private static String simpleSavePath(String path) {
		int index = path.indexOf("\\", path.lastIndexOf("gotpingMall")) + 1;
		if(index == 0)
			index = path.indexOf("/", path.lastIndexOf("gotpingMall")) + 1;
		return path.substring(index, path.length()).replace("\\", "/");
	}
	
	private static void checkFolder(String path) {
		File dir = new File(path);
		if(!dir.exists())
			dir.mkdirs();
	}
	
	private static File createFile(String path) {
		while( true ) {
			int save_name = (int)System.currentTimeMillis();
			File f = new File(path + File.separator + save_name);
			if( !f.exists() )
				return f;
		}
	}
}
