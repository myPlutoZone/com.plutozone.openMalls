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
 * File			: SellerWeb.java
 * Notes		:
 * History		: [NO][Programmer][Description]
 *				: [20240731132822][pluto#plutozone.com][CREATE: Initial Release]
 */
package com.plutozone.seller.sellers.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.plutozone.seller.sellers.dto.SellerDto;
import com.plutozone.seller.sellers.service.SellerSrvc;
import com.plutozone.util.security.HSwithSHA;

/**
 * @version 1.0.0
 * @author pluto#plutozone.com
 * 
 * @since 2024-07-31
 * <p>DESCRIPTION:</p>
 * <p>IMPORTANT:</p>
 */
@Controller("com.plutozone.seller.sellers.controller.SellerWeb")
public class SellerWeb {
	
	/** Logger */
	private static Logger logger = LoggerFactory.getLogger(SellerWeb.class);
	
	@Inject
	SellerSrvc sellerSrvc;
	
	@RequestMapping(value = "/seller/sellers/registerForm.web")
	public ModelAndView registerForm(HttpServletRequest request, HttpServletResponse response) {
		
		ModelAndView mav = new ModelAndView("redirect:/error.web");
		
		try {
			
			mav.setViewName("seller/sellers/registerForm");
		}
		catch (Exception e) {
			logger.error("[" + this.getClass().getName() + ".registerForm()] " + e.getMessage(), e);
		}
		finally {}
		
		return mav;
	}
	
	/**
	 * @param request [요청 서블릿]
	 * @param response [응답 서블릿]
	 * @param managerDto [회원 빈]
	 * @return ModelAndView
	 * 
	 * @since 2024-07-31
	 * <p>DESCRIPTION: 판매자 회원 가입 처리</p>
	 * <p>IMPORTANT:</p>
	 * <p>EXAMPLE:</p>
	 */
	@RequestMapping(value = "/seller/sellers/registerProc.web", method = RequestMethod.POST)
	public ModelAndView registerProc(HttpServletRequest request, HttpServletResponse response, SellerDto sellerDto) {
		

		ModelAndView mav = new ModelAndView("redirect:/error.web");
		
		try {
			// 해쉬 암호화(SHA-256)
			sellerDto.setPasswd(HSwithSHA.encode(sellerDto.getPasswd()));
			//logger.debug("암호화 후(Passwd): " + sellerDto.getPasswd());
			
			boolean result = sellerSrvc.insert(sellerDto);
			
			if (result) logger.debug("가입 성공");
			else logger.debug("가입 실패");
			
			request.setAttribute("redirect"	, "/");
			
			mav.setViewName("forward:/servlet/result.web");
		}
		catch (Exception e) {
			logger.error("[" + this.getClass().getName() + ".registerProc()] " + e.getMessage(), e);
		}
		finally {}
		
		return mav;
	}
	
}
