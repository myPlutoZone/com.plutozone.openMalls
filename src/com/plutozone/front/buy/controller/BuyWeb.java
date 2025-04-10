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
 * File			: BuyWeb.java
 * Notes		:
 * History		: [NO][Programmer][Description]
 *				: [20240801162340][pluto#plutozone.com][CREATE: Initial Release]
 *				: [20241010125200][pluto#plutozone.com][UPDATE: writeForm() for Interface]
 */
package com.plutozone.front.buy.controller;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


import com.plutozone.front.buy.dto.BuyDetailDto;
import com.plutozone.front.buy.dto.BuyDetailListDto;
import com.plutozone.front.buy.dto.BuyDto;
import com.plutozone.front.buy.dto.BuyMasterDto;
import com.plutozone.front.buy.service.BuySrvc;
import com.plutozone.front.center.controller.BoardWeb;
import com.plutozone.front.common.Common;
import com.plutozone.front.sale.dto.SaleDto;
import com.plutozone.front.sale.service.SaleSrvc;
import com.plutozone.util.servlet.Request;

/**
 * @version 1.0.0
 * @author pluto#plutozone.com
 * 
 * @since 2024-08-08
 * <p>DESCRIPTION:</p>
 * <p>IMPORTANT:</p>
 */
@Controller("com.plutozone.front.buy.controller.BuyWeb")
public class BuyWeb extends Common {
	
	/** Logger */
	private static Logger logger = LoggerFactory.getLogger(BoardWeb.class);
	
	@Inject
	SaleSrvc saleSrvc;
	
	@Inject
	BuySrvc buySrvc;
	
	/*
	@RequestMapping(value = "/front/buy/view.web")
	public ModelAndView view(HttpServletRequest request	, HttpServletResponse response, BuyMasterDto buyMasterDto) {
		
		ModelAndView mav = new ModelAndView("redirect:/error.web");
		
		try {
			buyMasterDto.setRegister(Integer.parseInt(getSession(request, "SEQ_MBR")));
			
			List<BuyDetailDto> list = buySrvc.select(buyMasterDto);
			
			mav.addObject("list", list);
			mav.setViewName("/front/buy/view");
		}
		catch (Exception e) {
			logger.error("[" + this.getClass().getName() + ".view()] " + e.getMessage(), e);
		}
		finally {}
		
		return mav;
	}
	
	@RequestMapping(value = "/front/buy/list.web")
	public ModelAndView list(HttpServletRequest request	, HttpServletResponse response, PagingDto pagingDto){
		
		ModelAndView mav = new ModelAndView("redirect:/error.web");
		
		try {
			
			pagingDto.setRegister(Integer.parseInt(getSession(request, "SEQ_MBR")));
			
			PagingListDto pagingListDto = buySrvc.list(pagingDto);
			mav.addObject("paging"	, pagingListDto.getPaging());
			mav.addObject("list"	, pagingListDto.getList());
			
			mav.setViewName("/front/buy/list");
		}
		catch (Exception e) {
			logger.error("[" + this.getClass().getName() + ".list()] " + e.getMessage(), e);
		}
		finally {}
		
		return mav;
	}
	*/
	
	@RequestMapping(value = "/front/buy/writeProc.web")
	public ModelAndView writeProc(HttpServletRequest request, HttpServletResponse response, BuyDetailListDto buyDetailListDto) {
		
		ModelAndView mav = new ModelAndView("redirect:/error.web");
		
		try {
			//logger.debug("" + buyDetailListDto.getBuyList().size());
			
			String finalSleName = "";	// 마지막 판매 상품명
			
			int totalCount = 0;			// 총 갯수
			int totalPrice = 0;			// 총 가격
			
			ArrayList<BuyDetailDto> listBuyDetailDto = new ArrayList<BuyDetailDto>();
			
			if (buyDetailListDto.getBuyList() != null) {
				for (int loop = 0; loop < buyDetailListDto.getBuyList().size(); loop++) {
					
					if (buyDetailListDto.getBuyList().get(loop).getCount() >= 1) {
						
						//logger.debug(loop + " : seq_prd(" + buyDetailListDto.getBuyList().get(loop).getSeq_prd() + ")" + " + count(" + buyDetailListDto.getBuyList().get(loop).getCount() + ")");
						
						// 갯수가 1개 이상인 상품
						listBuyDetailDto.add(buyDetailListDto.getBuyList().get(loop));
						
						// 전체 상품 갯수 및 금액 그리고 구매명
						totalCount += buyDetailListDto.getBuyList().get(loop).getCount();
						totalPrice += buyDetailListDto.getBuyList().get(loop).getCount() * buyDetailListDto.getBuyList().get(loop).getPrice();
						finalSleName = buyDetailListDto.getBuyList().get(loop).getSle_nm();
					}
				}
			}
			//logger.debug("count=" + listBuyDetailDto.size());
			
			// 선택된 상품이 1개 이상을 경우만 구매 실행
			if (listBuyDetailDto.size() > 0) {
				
				// 마스터 설정
				BuyMasterDto buyMasterDto = new BuyMasterDto();
				buyMasterDto.setSeq_mbr(Integer.parseInt(getSession(request, "SEQ_MBR")));
				buyMasterDto.setBuy_info(finalSleName + "-포함(총 개수: " + totalCount + ")");
				buyMasterDto.setBuy_count(totalCount);
				buyMasterDto.setBuy_price(totalPrice);
				buyMasterDto.setRegister(Integer.parseInt(getSession(request, "SEQ_MBR")));
				
				if (buySrvc.insertByDealNum(buyMasterDto, listBuyDetailDto, "[INF]NO PAYMENT")) {
					request.setAttribute("script"	, "alert('추후 결제 페이지로 이동 예정');");
					request.setAttribute("redirect"	, "/front/main/main.web");
				}
				else {
					request.setAttribute("script"	, "alert('구매에 실패했습니다! 잠시 후에 이용 바랍니다!');");
					request.setAttribute("redirect"	, "/front/main/main.web");
				}
			}
			else {
				request.setAttribute("script"	, "alert('선택된 상품이 없습니다!');");
				request.setAttribute("redirect"	, "/");
			}
			
			request.setAttribute("redirect"	, "/front/");
			mav.setViewName("forward:/servlet/result.web");
		}
		catch (Exception e) {
			logger.error("[" + this.getClass().getName() + ".writeProc()] " + e.getMessage(), e);
		}
		finally {}
		
		return mav;
	}
	
	@RequestMapping(value = "/front/buy/writeForm.web")
	public ModelAndView writeForm(HttpServletRequest request, HttpServletResponse response, SaleDto saleDto) {
		
		ModelAndView mav = new ModelAndView("redirect:/error.web");
		
		try {
			String flgMobile	= "N";
			if (Request.isDevice(request, "mobile")) flgMobile = "Y";
			
			SaleDto _saleDto	= saleSrvc.select(saleDto);
			
			mav.addObject("saleDto"		, _saleDto);
			mav.addObject("flgMobile"	, flgMobile);
			
			// 신규 결제 연동(flgMobile 불필요)
			// mav.setViewName("/front/buy/writeForm");
			
			// 기존 결제 연동(flgMobile 필요)
			mav.setViewName("/front/buy/writeForm.old");
		}
		catch (Exception e) {
			logger.error("[" + this.getClass().getName() + ".writeForm()] " + e.getMessage(), e);
		}
		finally {}
		
		return mav;
	}
	
	/**
	 * @param request [요청 서블릿]
	 * @param response [응답 서블릿]
	 * @param boardDto [게시판 빈]
	 * @return ModelAndView
	 * 
	 * @since 2024-08-01
	 * <p>DESCRIPTION: 고객센터 목록</p>
	 * <p>IMPORTANT:</p>
	 * <p>EXAMPLE:</p>
	 */
	@RequestMapping(value = "/front/buy/history.web")
	public ModelAndView history(HttpServletRequest request, HttpServletResponse response, BuyDto buyDto) {

		ModelAndView mav = new ModelAndView("redirect:/error.web");
		
		try {
			
			buyDto.setSeq_mbr(Integer.parseInt(getSession(request, "SEQ_MBR")));
			
			// 구매 목록
			List<BuyDto> list = buySrvc.list(buyDto);
			
			// 총 구매금액
			String total_price = buySrvc.selectTotal(buyDto);
			
			mav.addObject("list", list);
			mav.addObject("total_price", total_price);
			
			mav.setViewName("front/buy/history");
			
		}
		catch (Exception e) {
			logger.error("[" + this.getClass().getName() + ".history()] " + e.getMessage(), e);
		}
		finally {}
		
		return mav;
	}
}
