package com.got.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.got.service.FileService;
import com.got.util.JSONUtil;
import com.got.vo.file.FileVO;

@Controller
public class FileController {
	
	public static String tempPath;
	public static final String SIMPLE_SAVE_PATH = "resources/upload/temp";
	
	@Inject private FileService s;
	
	@ResponseBody
	@RequestMapping(value = "file/upload.yo", method = RequestMethod.POST)
	public String uploadFile(HttpSession session, MultipartFile file) {
		tempPath = session.getServletContext().getRealPath(SIMPLE_SAVE_PATH);
		FileVO f = s.saveFileInTempPath(tempPath, file);
		return JSONUtil.convertToJSON(f);
	}
}