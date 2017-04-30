package com.got.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.got.service.FileService;
import com.got.util.CommonUtil;
import com.got.vo.FileVO;

@Controller
public class FileController {
	
	public static String tempPath;
	public static final String SIMPLE_SAVE_PATH = "resources/upload/temp";
	
	@Autowired private FileService s;
	
	@ResponseBody
	@RequestMapping(value = "file/upload.yo", method = RequestMethod.POST)
	public String uploadFile(HttpSession session, MultipartFile file) {
		if(file == null)
			throw new NullPointerException("파일 업로드 실패");
		if(file.isEmpty())
			throw new NullPointerException("NULL은 아니지만 파일 업로드 실패");
		tempPath = session.getServletContext().getRealPath(SIMPLE_SAVE_PATH);
		FileVO f = s.saveFileInTempPath(tempPath, file);
		return CommonUtil.convertToJSON(f);
	}
	
	@ResponseBody
	@RequestMapping(value = "file/uploadTest.yo", method = RequestMethod.POST)
	public String uploadFileTest(HttpSession session, List<MultipartFile> files) {
		if(files == null)
			throw new NullPointerException("파일 업로드 실패");
		if(files.isEmpty())
			throw new NullPointerException("NULL은 아니지만 파일 업로드 실패");
		
		tempPath = session.getServletContext().getRealPath(SIMPLE_SAVE_PATH);
		List<FileVO> fileVOs = s.saveFileInTempPath(tempPath, files);
		return CommonUtil.convertToJSON(fileVOs);
	}
}