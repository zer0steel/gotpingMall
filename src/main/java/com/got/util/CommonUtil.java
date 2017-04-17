package com.got.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.got.vo.FileVO;

public class CommonUtil {
	
	public static String convertToJSON(Object obj) {
		ObjectMapper mapper = new ObjectMapper();
		String jsonString = null;
		try {
			jsonString = mapper.writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return jsonString;
	}
	
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
}
