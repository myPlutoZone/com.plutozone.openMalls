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
 *				: [20240828144331][pluto#plutozone.com][CREATE: Initial Release]
 */
package com.plutozone.backoffice.users.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.type.TypeReference;
import com.plutozone.Security;
import com.plutozone.backoffice.common.Common;
import com.plutozone.backoffice.common.dto.PagingDto;
import com.plutozone.backoffice.common.dto.PagingListDto;
import com.plutozone.backoffice.users.dto.RequestSellerInfoDto;
import com.plutozone.backoffice.users.dto.RequestSellerInfosDto;
import com.plutozone.backoffice.users.dto.ResponseSellerInfosDto;
import com.plutozone.backoffice.users.dto.SellerDto;
import com.plutozone.backoffice.users.service.SellerSrvc;
import com.plutozone.common.interfaces.JsonItrf;

/**
 * @version 1.0.0
 * @author pluto#plutozone.com
 * 
 * @since 2024-08-28
 * <p>DESCRIPTION:</p>
 * <p>IMPORTANT:</p>
 */
@Controller("com.plutozone.backoffice.users.controller.SellerWeb")
public class SellerWeb extends Common {
	
	/** Logger */
	private static Logger logger = LoggerFactory.getLogger(SellerWeb.class);
	
	@Inject
	SellerSrvc sellerSrvc;
	
	@RequestMapping(value = "/console/users/seller/list.web")
	public ModelAndView list(HttpServletRequest request, HttpServletResponse response, PagingDto pagingDto) {
		
		ModelAndView mav = new ModelAndView("redirect:/error.web");
		
		try {
			
			PagingListDto pagingListDto = sellerSrvc.list(pagingDto);
			
			mav.addObject("paging"	, pagingListDto.getPaging());
			mav.addObject("list"	, pagingListDto.getList());
			
			mav.setViewName("backoffice/users/seller/list");
			
		}
		catch (Exception e) {
			logger.error("[" + this.getClass().getName() + ".list()] " + e.getMessage(), e);
		}
		finally {}
		
		return mav;
	}
	
	@RequestMapping(value = "/console/users/seller/modifyForm.web")
	public ModelAndView modifyForm(HttpServletRequest request, HttpServletResponse response, SellerDto sellerDto) {
		
		ModelAndView mav	= new ModelAndView("redirect:/error.web");
		boolean isOK		= false;
		
		try {
			
			SellerDto _sellerDto = sellerSrvc.select(sellerDto);
			
			// 대기중(cd_state = 0)일 경우 사업자 정보 확인(휴업 등)
			if (_sellerDto.getCd_state() == 0) {
				
				/** 공공DB(국세청 사업자 정보: https://www.data.go.kr/data/15081808/openapi.do) 요청과 응답 */
				RequestSellerInfoDto requestSellerInfoDto = new RequestSellerInfoDto();
				requestSellerInfoDto.setB_no(_sellerDto.getCorp_num().replace("-", ""));	// 사업자 등록 번호
				requestSellerInfoDto.setStart_dt(_sellerDto.getCorp_birthday());			// 개업년월일
				requestSellerInfoDto.setP_nm(_sellerDto.getCorp_ceo());						// 대표자
				
				RequestSellerInfoDto[] arrRequestSellerInfoDto = new RequestSellerInfoDto[1];
				arrRequestSellerInfoDto[0] = requestSellerInfoDto;
				
				RequestSellerInfosDto requestSellerInfosDto = new RequestSellerInfosDto();
				requestSellerInfosDto.setBusinesses(arrRequestSellerInfoDto);
				
				ResponseSellerInfosDto responseSellerInfosDto = (ResponseSellerInfosDto) JsonItrf.connectPost(requestSellerInfosDto
						, new TypeReference<ResponseSellerInfosDto>() {}
						, "http://api.odcloud.kr/api/nts-businessman/v1/validate?serviceKey=" + Security.openDB_serviceKey + "&returnType=JSON");
				
				/** 사업자 정보 및 조회자 정보를 로깅 */
				logger.info("---------[SELLER.INTERFACE]---------");
				logger.info("SEQ_MNG=" + Integer.parseInt(getSession(request, "SEQ_MNG")));
				logger.info("SEQ_SLL=" + _sellerDto.getSeq_sll());
				logger.info("CORP_NUM=" + _sellerDto.getCorp_num().replace("-", ""));
				logger.info("CORP_BIR=" + _sellerDto.getCorp_birthday());
				logger.info("CORP_CEO=" + _sellerDto.getCorp_ceo());
				logger.info("VALID=" + responseSellerInfosDto.getData()[0].getValid());
				if (responseSellerInfosDto.getData()[0].getValid().equals("01"))
				logger.info("RESUL=" + responseSellerInfosDto.getData()[0].getStatus().getB_stt_cd());
				logger.info("------------------------------------");
				
				// 사업자 등록 번호 등이 정상적인 경우
				if (responseSellerInfosDto.getData()[0].getValid().equals("01")) {
					// 01(계속사업자) or 02(휴업) or 03(폐업) 등
					if (responseSellerInfosDto.getData()[0].getStatus().getB_stt_cd().equals("01")) {
						isOK = true;
					}
				}
			}
			//logger.debug("사업자" + isOK);
			
			mav.addObject("isOK", isOK);
			mav.addObject("sellerDto", _sellerDto);
			
			mav.setViewName("backoffice/users/seller/modifyForm");
		}
		catch (Exception e) {
			logger.error("[" + this.getClass().getName() + ".modifyForm()] " + e.getMessage(), e);
		}
		finally {}
		
		return mav;
	}
	
	@RequestMapping(value = "/console/users/seller/modifyProc.web")
	public ModelAndView modifyProc(HttpServletRequest request, HttpServletResponse response, SellerDto sellerDto) {
		
		ModelAndView mav = new ModelAndView("redirect:/error.web");
		
		try {
			sellerDto.setUpdater(Integer.parseInt(getSession(request, "SEQ_MNG")));
			
			if (sellerSrvc.update(sellerDto)) {
				request.setAttribute("script"	, "alert('적용되었습니다.');");
				request.setAttribute("redirect"	, "/console/users/seller/list.web");
			}
			else {
				request.setAttribute("script"	, "alert('시스템 관리자에게 문의하세요!');");
				request.setAttribute("redirect"	, "/");
			}
			mav.setViewName("forward:/servlet/result.web");
		}
		catch (Exception e) {
			logger.error("[" + this.getClass().getName() + ".modifyProc()] " + e.getMessage(), e);
		}
		finally {}
		
		return mav;
	}
}