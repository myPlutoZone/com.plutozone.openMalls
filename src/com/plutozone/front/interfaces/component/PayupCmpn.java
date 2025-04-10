/**
 * YOU ARE STRICTLY PROHIBITED TO COPY, DISCLOSE, DISTRIBUTE, MODIFY OR USE THIS PROGRAM
 * IN PART OR AS A WHOLE WITHOUT THE PRIOR WRITTEN CONSENT OF PLUTOZONE.COM.
 * PLUTOZONE.COM OWNS THE INTELLECTUAL PROPERTY RIGHTS IN AND TO THIS PROGRAM.
 * COPYRIGHT (C) 2024 PLUTOZONE.COM ALL RIGHTS RESERVED.
 *
 * 하기 프로그램에 대한 저작권을 포함한 지적재산권은 plutozone.com에 있으며,
 * plutozone.com이 명시적으로 허용하지 않는 사용, 복사, 변경 및 제 3자에 의한 공개, 배포는 엄격히 금지되며
 * plutozone.com의 지적재산권 침해에 해당된다.
 * Copyright (C) 2024 plutozone.com All Rights Reserved.
 *
 *
 * Program		: com.plutozone.openMalls
 * Description	:
 * Environment	: JRE 1.7 or more
 * File			: PayupCmpn.java
 * Notes		:
 * History		: [NO][Programmer][Description]
 *				: [20241010171537][pluto#plutozone.com][CREATE: Initial Release]
 *				: [20240208120800][pluto#plutozone.com][REPORT: From com.payup.pay.service.ApiService]
 *				: [20241018170000][pluto#plutozone.com][REPORT: From com.payup.pay.service.ApiService New(BASE ON TOKEN)]
 */
package com.plutozone.front.interfaces.component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * @version 1.0.0
 * @author pluto#plutozone.com
 * 
 * @since 2024-02-08
 * <p>DESCRIPTION:</p>
 * <p>IMPORTANT:</p>
 */
@Component("com.plutozone.front.interfaces.component.PayupCmpn")
public class PayupCmpn {
	
	/** Logger */
	private static Logger logger = LoggerFactory.getLogger(PayupCmpn.class);
	
	@SuppressWarnings("unchecked")
	public Map<String,Object> JsonApi(HttpServletRequest requests, String url, Map<String, String> map, String Authorization) {
		
		Map<String,Object> returnMap = new HashMap<>();
		
		OkHttpClient client = new OkHttpClient();
		
		ObjectMapper objectMapper = new ObjectMapper();
		
		String jsonBody = "";
		try {
			jsonBody = objectMapper.writeValueAsString(map);
		}
		catch (JsonProcessingException e) {
			logger.error("[" + this.getClass().getName() + ".JsonApi().1st] " + e.getMessage(), e);
		}
		
		MediaType JSON = MediaType.get("application/json; charset=utf-8");
		RequestBody requestBody = RequestBody.create(jsonBody, JSON);
		
		Request request = null;
		
		String userAgent = "PC";
		
		if (com.plutozone.util.servlet.Request.isDevice(requests, "mobile")) {
			
			if (requests.getHeader("User-Agent").indexOf("iPhone") > -1) {
				userAgent = "iPhone";
			}
			else if (requests.getHeader("User-Agent").indexOf("Android") > -1) {
				userAgent = "Linux Android";
			}
			
			request = new Request.Builder()
					.url(url)
					.addHeader("User-Agent", userAgent)
					.addHeader("Authorization", Authorization)
					.post(requestBody)
					.build();
		}
		else {
			request = new Request.Builder()
					.url(url)
					.addHeader("Authorization", Authorization)
					.post(requestBody)
					.build();
		}
		
		//logger.debug("[" + this.getClass().getName() + ".JsonApi()] userAgent=" + userAgent);
		
		try {
			// [2024-11-11][pluto#plutozone.com][REPORT: 30초(연결/읽기/쓰기)]
			client.newBuilder().setConnectTimeout$okhttp(30000);
			client.newBuilder().setReadTimeout$okhttp(30000);
			client.newBuilder().setWriteTimeout$okhttp(30000);
			
			Response response = client.newCall(request).execute();
			returnMap = objectMapper.readValue(response.body().string(), Map.class);
		}
		catch (IOException e) {
				logger.error("[" + this.getClass().getName() + ".JsonApi().2nd] " + e.getMessage(), e);
		}
		
		return returnMap;
	}
	
	@SuppressWarnings("unchecked")
	public Map<String,Object> JsonApi(HttpServletRequest requests, String url, Map<String, String> map) {
		
		Map<String,Object> returnMap = new HashMap<>();
		
		OkHttpClient client = new OkHttpClient();
		
		ObjectMapper objectMapper = new ObjectMapper();
		
		String jsonBody = "";
		try {
			jsonBody = objectMapper.writeValueAsString(map);
		}
		catch (JsonProcessingException e) {
			logger.error("[" + this.getClass().getName() + ".JsonApi().1st] " + e.getMessage(), e);
		}
		
		MediaType JSON = MediaType.get("application/json; charset=utf-8");
		RequestBody requestBody = RequestBody.create(jsonBody, JSON);
		
		Request request = null;
		
		String userAgent = "PC";
		
		if (com.plutozone.util.servlet.Request.isDevice(requests, "mobile")) {
			
			if (requests.getHeader("User-Agent").indexOf("iPhone") > -1) {
				userAgent = "iPhone";
			}
			else if (requests.getHeader("User-Agent").indexOf("Android") > -1) {
				userAgent = "Linux Android";
			}
			
			request = new Request.Builder()
					.url(url)
					.addHeader("User-Agent", userAgent)
					.post(requestBody)
					.build();
		}
		else {
			request = new Request.Builder()
					.url(url)
					.post(requestBody)
					.build();
		}
		
		//logger.debug("[" + this.getClass().getName() + ".JsonApi()] userAgent=" + userAgent);
		
		try {
			// [2024-11-11][pluto#plutozone.com][REPORT: 30초(연결/읽기/쓰기)]
			client.newBuilder().setConnectTimeout$okhttp(30000);
			client.newBuilder().setReadTimeout$okhttp(30000);
			client.newBuilder().setWriteTimeout$okhttp(30000);
			
			Response response = client.newCall(request).execute();
			returnMap = objectMapper.readValue(response.body().string(), Map.class);
		}
		catch (IOException e) {
				logger.error("[" + this.getClass().getName() + ".JsonApi().2nd] " + e.getMessage(), e);
		}
		
		return returnMap;
	}
	
	public String getSHA256Hash(String input) throws NoSuchAlgorithmException {
		
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		
		byte[] bytes = md.digest(input.getBytes(StandardCharsets.UTF_8));
		
		StringBuilder hexStringBuilder = new StringBuilder();
		
		for (byte b : bytes) {
			String hex = String.format("%02x", b);
			hexStringBuilder.append(hex);
		}
		
		return hexStringBuilder.toString();
	}
}