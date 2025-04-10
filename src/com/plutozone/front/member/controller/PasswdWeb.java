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
 * File			: PasswdWeb.java
 * Notes		:
 * History		: [NO][Programmer][Description]
 *				: [20240801164940][pluto#plutozone.com][CREATE: Initial Release]
 */
package com.plutozone.front.member.controller;




import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.servlet.ModelAndView;


import com.plutozone.front.common.Common;

import com.plutozone.front.member.dto.MemberDto;
import com.plutozone.front.member.service.MemberSrvc;
import com.plutozone.util.security.HSwithSHA;


/**
 * @version 1.0.0
 * @author pluto#plutozone.com
 * 
 * @since 2024-08-01
 * <p>DESCRIPTION:</p>
 * <p>IMPORTANT:</p>
 */
@Controller("com.plutozone.front.member.controller.PasswdWeb")
public class PasswdWeb extends Common {
	
	/** Logger */
	private static Logger logger = LoggerFactory.getLogger(PasswdWeb.class);

	@Inject
	MemberSrvc memberSrvc;
	
	/**
	 * @param request [요청 서블릿]
	 * @param response [응답 서블릿]
	 * @param boardDto [게시판 빈]
	 * @return ModelAndView
	 * 
	 * @since 2024-08-01
	 * <p>DESCRIPTION: 고객센터 수정 처리</p>
	 * <p>IMPORTANT:</p>
	 * <p>EXAMPLE:</p>
	 */
	@RequestMapping(value = "/front/member/passwd/modifyProc.web")
	public ModelAndView modifyProc(HttpServletRequest request, HttpServletResponse response, MemberDto memberDto, String confirmPasswd) {
		
		ModelAndView mav = new ModelAndView("redirect:/error.web");
		
		try {
			
			int seq_mbr = Integer.parseInt(getSession(request, "SEQ_MBR"));
			
			memberDto.setSeq_mbr(seq_mbr);
			memberDto.setUpdater(seq_mbr);
			
			// [2024-08-02][pluto#plutozone.com][TODO-개선: 기존 비밀번호 확인 추가 필요]
			// 1. Service > DAO > XML을 거쳐서 기본 비밀번호를 가져 와서
			// 2. 입력받은 비밀번호를 암호화(해쉬)해서 비교하여
			// 3. 같으면...하기 실행 아니면 비밀번호가 틀리다고 메시징!!!
			
			// 입력받은 비밀번호(암호화 포함)
			String passwdEncoded_ = HSwithSHA.encode(memberDto.getPasswd());
			
			// 암호화된 비밀번호
			String passwdEncoded = memberSrvc.selectPasswd(memberDto).getPasswd();
			
			// 입력받은 비밀번호 = 암호화된 비밀번호
			if (passwdEncoded_.equals(passwdEncoded)) {
				
				// 신규 비밀번호 암호화
				memberDto.setPasswd(HSwithSHA.encode(confirmPasswd));
				
				if (memberSrvc.updatePasswd(memberDto)){
					request.setAttribute("script"	, "alert('신규 비밀번호가 입력되었습니다.');");
					request.setAttribute("redirect"	, "/front/myPage/");
				}
				else {
					request.setAttribute("script"	, "alert('시스템 관리자에게 문의하세요!');");
					request.setAttribute("redirect"	, "/");
				}
			}
			else {
				request.setAttribute("script"	, "alert('현재 비밀번호가 틀립니다!');");
				request.setAttribute("redirect"	, "/front/myPage/");
			}
			mav.setViewName("forward:/servlet/result.web");
		}
		catch (Exception e) {
			logger.error("[" + this.getClass().getName() + ".modifyProc()] " + e.getMessage(), e);
		}
		finally {}
		
		return mav;
	}
	
	/**
	 * @param request [요청 서블릿]
	 * @param response [응답 서블릿]
	 * @return ModelAndView
	 * 
	 * @since 2024-08-01
	 * <p>DESCRIPTION:</p>
	 * <p>IMPORTANT:</p>
	 * <p>EXAMPLE:</p>
	 */
	@RequestMapping(value = "/front/member/passwd/modifyForm.web")
	public ModelAndView modifyForm(HttpServletRequest request, HttpServletResponse response) {
		
		ModelAndView mav = new ModelAndView("redirect:/error.web");
		
		try {
			mav.setViewName("front/member/passwd/modifyForm");
		}
		catch (Exception e) {
			logger.error("[" + this.getClass().getName() + ".modifyForm()] " + e.getMessage(), e);
		}
		finally {}
		
		return mav;
	}

}