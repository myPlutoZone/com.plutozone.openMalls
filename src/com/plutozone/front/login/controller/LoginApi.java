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
 * File			: LoginApi.java
 * Notes		:
 * History		: [NO][Programmer][Description]
 *				: [20240820173018][pluto#plutozone.com][CREATE: Initial Release]
 */
package com.plutozone.front.login.controller;

import java.util.Properties;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.plutozone.front.common.Common;
import com.plutozone.front.login.dto.LoginDto;
import com.plutozone.front.login.service.LoginSrvc;
import com.plutozone.front.member.dto.MemberDto;
import com.plutozone.util.security.HSwithSHA;
import com.plutozone.util.security.SKwithAES;

/**
 * @version 1.0.0
 * @author pluto#plutozone.com
 * 
 * @since 2024-08-20
 * <p>DESCRIPTION:</p>
 * <p>IMPORTANT:</p>
 */
@Controller("com.plutozone.front.login.controller.LoginApi")
public class LoginApi extends Common {
	
	/** Logger */
	private static Logger logger = LoggerFactory.getLogger(LoginApi.class);
	
	@Autowired
	Properties staticProperties;
	
	@Inject
	LoginSrvc loginSrvc;
	
	/**
	 * @param memberDto [회원 빈(Bean)]
	 * @return boolean
	 * 
	 * @since 2024-08-20
	 * <p>DESCRIPTION: 로그인 처리</p>
	 * <p>IMPORTANT: 파라미터는 데이터 타입 및 형식을 만족하여야 한다.</p>
	 * <p>EXAMPLE:<br>
	 * - headers	: 요청 헤더를 확인<br>
	 * - consumes	: application/json 형태로 요청 확인<br>
	 * - produces	: application/json 형태로 응답 확인<br>
	 * - @RequestMapping(value = requestMappingURL, method = RequestMethod.GET)<br>
	 * - @RequestMapping(value = requestMappingURL, method = RequestMethod.POST, headers = "content-type=application/x-www-form-urlencoded")<br>
	 * </p>
	 */
	@RequestMapping(value = "/front/login/loginProc.api", method = RequestMethod.POST, headers = {"content-type=application/json; charset=UTF-8", "accept=application/json"}, consumes="application/json; charset=UTF-8", produces="application/json; charset=UTF-8")
	public @ResponseBody MemberDto loginProc(@RequestBody final LoginDto loginDto) {
		
		MemberDto memberDto = null;
		
		try {
			
			logger.debug("[INPUT] email=" + loginDto.getEmail());
			logger.debug("[INPUT] passwd=" + loginDto.getPasswd());
			
			// 대칭키 암호화(AES-256)
			String staticKey	= staticProperties.getProperty("front.enc.user.aes256.key", "[UNDEFINED]");
			SKwithAES aes		= new SKwithAES(staticKey);
			
			loginDto.setEmail(aes.encode(loginDto.getEmail()));
			
			memberDto = loginSrvc.exist(loginDto);
			
			if (memberDto != null
					&& HSwithSHA.encode(loginDto.getPasswd()).equals(memberDto.getPasswd())) {
				
				memberDto.setEmail(aes.decode(memberDto.getEmail()));
				memberDto.setMbr_nm(aes.decode(memberDto.getMbr_nm()));
				
				logger.debug("[OUTPUT] email=" + memberDto.getEmail());
				logger.debug("[OUTPUT] mbr_nm=" + memberDto.getMbr_nm());
			}
			else {
				logger.debug("[OUTPUT] 회원 정보 없음");
				memberDto = null;
			}
		}
		catch (Exception e) {
			logger.error("[" + this.getClass().getName() + ".loginProc()] " + e.getMessage(), e);
		}
		finally {}
		
		return memberDto;
	}
}