package com.got.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CommonUtil {
	
	private static Logger log = Logger.getLogger(CommonUtil.class);
	
	public static String convertToJSON(Object obj) {
		ObjectMapper mapper = new ObjectMapper();
		String jsonString = null;
		try {
			jsonString = mapper.writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			log.error(e);
		}
		return jsonString;
	}
	
	public static <T> List<T> getVO(String[] jsonString, Class<T> VOclazz) {
		List<T> voList;
		if( isConvertableJSON(jsonString) ) 
			voList = convertVO(VOclazz, jsonString);
		else {
			String json = String.join(",", jsonString);
			voList = convertVO(VOclazz, json);
		}
		return voList;
	}
	
	private static <T> List<T> convertVO(Class<T> VOclazz, String...jsonString) {
		ObjectMapper mapper = new ObjectMapper();
		List<T> voList = new ArrayList<>();
		try {
			for(String json : jsonString) {
				voList.add(mapper.readValue(json, VOclazz));
			}
		} catch (JsonMappingException e) {
			log.error(e);
			throw new RuntimeException();
		} catch (IOException e) {
			log.error(e);
			throw new RuntimeException();
		}
		return voList;
	}
	
	private static final int NOT_EXIST_IDX = -1;
	private static boolean isConvertableJSON(String[] jsonString) {
		int idx = jsonString[0].lastIndexOf("}");
		return idx > NOT_EXIST_IDX;
	}
}
