package com.got.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.got.util.CommonUtil;
import com.got.util.RSA;

@Controller
public class RsaController {

	@ResponseBody
	@RequestMapping("rsakey.yo")
	public String getRsaKey(HttpSession session) {
		Map<String, Object> rsaKey = RSA.generateKey();
		session.setAttribute(RSA.PRIVATE_KEY, rsaKey.get(RSA.PRIVATE_KEY));
		return CommonUtil.convertToJSON(rsaKey);
	}
}
