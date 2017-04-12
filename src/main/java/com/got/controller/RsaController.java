package com.got.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.got.util.RSA;

@Controller
public class RsaController {

	@ResponseBody
	@RequestMapping("rsakey.yo")
	public String getRsaKey(HttpSession session) throws JsonProcessingException {
		Map<String, Object> rsaKey = RSA.generateKey();
		session.setAttribute(RSA.PRIVATE_KEY, rsaKey.get(RSA.PRIVATE_KEY));
		return getPulickeyJSON(rsaKey);
	}
	
	private String getPulickeyJSON(Map<String, Object> rsaKey) throws JsonProcessingException {
		rsaKey.remove(RSA.PRIVATE_KEY);
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(rsaKey);
	}
}
