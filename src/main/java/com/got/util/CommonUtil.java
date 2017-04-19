package com.got.util;

import java.io.IOException;
import java.util.ArrayList;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

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
	
	public static <T> ArrayList<T> getVO(String[] jsonStrings, Class<T> VOclazz) {
		ArrayList<T> list = new ArrayList<>();
		ObjectMapper mapper = new ObjectMapper();
		for(String json : jsonStrings) {
			try {
				list.add(mapper.readValue(json, VOclazz));
			} catch (JsonMappingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return list;
	}
}
