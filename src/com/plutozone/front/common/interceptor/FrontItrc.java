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
 * File			: FrontItrc.java
 * Notes		:
 * History		: [NO][Programmer][Description]
 *				: [20240806123516][pluto#plutozone.com][CREATE: Initial Release]
 */
package com.plutozone.front.common.interceptor;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.plutozone.front.common.component.SessionCmpn;

/**
 * @version 1.0.0
 * @author pluto#plutozone.com
 * 
 * @since 2024-08-06
 * <p>DESCRIPTION:</p>
 * <p>IMPORTANT:</p>
 */
public class FrontItrc extends HandlerInterceptorAdapter {
	
	/** Logger */
	private static Logger logger = LoggerFactory.getLogger(FrontItrc.class);
	
	@Inject
	SessionCmpn sessionCmpn;
	
	/**
	 * @param request [요청 서블릿]
	 * @param response [응답 서블릿]
	 * @param handler [핸들러]
	 * @throws IOException [IO 예외]
	 * @throws ServletException [서블릿 예외]
	 * @return boolean
	 * 
	 * @since 2017-06-30
	 * <p>DESCRIPTION: 컨트롤러 접근 전에 가로채기 위해 preHandle 오버라이드</p>
	 * <p>IMPORTANT: 파라미터는 데이터 타입 및 형식을 만족하여야 한다.</p>
	 * <p>EXAMPLE:</p>
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException, ServletException {
		
		try {
			if (!sessionCmpn.isSession(request)) {
				// response.sendRedirect(request.getContextPath() + "/reject.web");
				response.sendRedirect(request.getContextPath() + "/front/login/loginForm.web?url=" + request.getRequestURI());
				return false;
			}
		}
		catch (Exception e) {
			logger.error("[" + this.getClass().getName() + ".preHandle()] " + e.getMessage(), e);
		}
		
		return true;
	}
}
