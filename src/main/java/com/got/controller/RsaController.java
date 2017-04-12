package com.got.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.got.util.RSA;
import com.got.vo.RsaVo;

@Controller
public class RsaController {

	@ResponseBody
	@RequestMapping("rsakey.yo")
	public String getRsaKey(HttpSession session) throws JsonProcessingException {
		RsaVo r = RSA.generateKey();
		session.setAttribute(RSA.PRIVATE_KEY, r.getPrivateKey());
		return getPulickeyJSON(r);
	}
	
	private String getPulickeyJSON(RsaVo r) throws JsonProcessingException {
		r.setPrivateKey(null);
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(r);
	}
}
