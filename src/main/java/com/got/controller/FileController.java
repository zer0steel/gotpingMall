package com.got.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.got.service.FileService;

@Controller
public class FileController {
	
	@Autowired private FileService s;
	
	@ResponseBody
	@RequestMapping(value = "file/upload.yo", method = RequestMethod.POST)
	public void uploadFile(HttpSession session, MultipartFile file) {
		if(file == null)
			throw new NullPointerException("파일 업로드 실패");
		if(file.isEmpty())
			throw new NullPointerException("NULL은 아니지만 파일 업로드 실패");
		String tempPath = session.getServletContext().getRealPath("resources/upload/temp");
		s.saveFileInTempPath(tempPath, file);
	}
}