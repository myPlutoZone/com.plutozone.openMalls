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
 * File			: ProductWeb.java
 * Notes		:
 * History		: [NO][Programmer][Description]
 *				: [20240801153607][pluto#plutozone.com][CREATE: Initial Release]
 */
package com.plutozone.seller.product.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.plutozone.seller.common.Common;
import com.plutozone.seller.common.dto.PagingListDto;
import com.plutozone.seller.common.dto.PagingDto;
import com.plutozone.seller.product.dto.ProductDto;
import com.plutozone.seller.product.service.ProductSrvc;

/**
 * @version 1.0.0
 * @author pluto#plutozone.com
 * 
 * @since 2024-08-01
 * <p>DESCRIPTION:</p>
 * <p>IMPORTANT:</p>
 */
@Controller("com.plutozone.seller.product.controller.ProductWeb")
public class ProductWeb extends Common {
	
	/** Logger */
	private static Logger logger = LoggerFactory.getLogger(ProductWeb.class);
	
	@Inject
	ProductSrvc productSrvc;
	
	/**
	 * @param request [요청 서블릿]
	 * @param response [응답 서블릿]
	 * @return ModelAndView
	 * 
	 * @since 2024-08-08
	 * <p>DESCRIPTION: 판매자 상품 삭제</p>
	 * <p>IMPORTANT:</p>
	 * <p>EXAMPLE:</p>
	 */
	@RequestMapping(value = "/seller/product/remove.web")
	public ModelAndView remove(HttpServletRequest request	, HttpServletResponse response, ProductDto productDto){

		ModelAndView mav = new ModelAndView("redirect:/error.web");
		
		logger.debug("productDto.seq_prd: " + productDto.getSeq_prd());
		
		try {
			productDto.setUpdater(Integer.parseInt(getSession(request, "SEQ_SLL")));
			
			if (productSrvc.deleteFlag(productDto)) {
				request.setAttribute("script"	, "alert('삭제되었습니다.');");
				request.setAttribute("redirect"	, "/seller/product/list.web");
			}
			else {
				request.setAttribute("script"	, "alert('시스템 관리자에게 문의하세요!');");
				request.setAttribute("redirect"	, "/");
			}
			
			mav.setViewName("forward:/servlet/result.web");
		}
		catch (Exception e) {
			logger.error("[" + this.getClass().getName() + ".remove()] " + e.getMessage(), e);
		}
		finally {}
		
		return mav;
	}
	
	/**
	 * @param request [요청 서블릿]
	 * @param response [응답 서블릿]
	 * @return ModelAndView
	 * 
	 * @since 2024-08-08
	 * <p>DESCRIPTION: 판매자 상품 수정 처리</p>
	 * <p>IMPORTANT:</p>
	 * <p>EXAMPLE:</p>
	 */
	@RequestMapping(value = "/seller/product/modifyProc.web")
	public ModelAndView modifyProc(HttpServletRequest request, HttpServletResponse response, ProductDto productDto){

		ModelAndView mav = new ModelAndView("redirect:/error.web");
		
		try {
			productDto.setRegister(Integer.parseInt(getSession(request, "SEQ_SLL")));
			
			if (productSrvc.update(productDto)) {
				request.setAttribute("script"	, "alert('수정되었습니다.');");
				request.setAttribute("redirect"	, "/seller/product/list.web");
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
	
	/**
	 * @param request [요청 서블릿]
	 * @param response [응답 서블릿]
	 * @return ModelAndView
	 * 
	 * @since 2024-08-08
	 * <p>DESCRIPTION: 판매자 상품 수정</p>
	 * <p>IMPORTANT:</p>
	 * <p>EXAMPLE:</p>
	 */
	@RequestMapping(value = "/seller/product/modifyForm.web")
	public ModelAndView modifyForm(HttpServletRequest request	, HttpServletResponse response, ProductDto productDto){

		ModelAndView mav = new ModelAndView("redirect:/error.web");
		
		try {
			ProductDto _productDto = productSrvc.select(productDto);
			
			mav.addObject("productDto", _productDto);
			mav.setViewName("seller/product/modifyForm");
		}
		catch (Exception e) {
			logger.error("[" + this.getClass().getName() + ".modifyForm()] " + e.getMessage(), e);
		}
		finally {}
		
		return mav;
	}
	
	/**
	 * @param request [요청 서블릿]
	 * @param response [응답 서블릿]
	 * @return ModelAndView
	 * 
	 * @since 2024-08-08
	 * <p>DESCRIPTION: 판매자 상품현황 보기</p>
	 * <p>IMPORTANT:</p>
	 * <p>EXAMPLE:</p>
	 */
	@RequestMapping(value = "/seller/product/view.web")
	public ModelAndView view(HttpServletRequest request	, HttpServletResponse response, ProductDto productDto){

		ModelAndView mav = new ModelAndView("redirect:/error.web");
		
		try {
			ProductDto _productDto = productSrvc.select(productDto);
			
			mav.addObject("productDto", _productDto);
			mav.setViewName("seller/product/view");
		}
		catch (Exception e) {
			logger.error("[" + this.getClass().getName() + ".view()] " + e.getMessage(), e);
		}
		finally {}
		
		return mav;
	}
	
	/**
	 * @param request [요청 서블릿]
	 * @param response [응답 서블릿]
	 * @return ModelAndView
	 * 
	 * @since 2024-08-08
	 * <p>DESCRIPTION: 판매자 상품 등록</p>
	 * <p>IMPORTANT:</p>
	 * <p>EXAMPLE:</p>
	 */
	@RequestMapping(value = "/seller/product/writeProc.web")
	public ModelAndView writeProc(HttpServletRequest request, HttpServletResponse response, ProductDto productDto){

		ModelAndView mav = new ModelAndView("redirect:/error.web");
		
		try {
			productDto.setRegister(Integer.parseInt(getSession(request, "SEQ_SLL")));
			
			if (productSrvc.insert(productDto)) {
				request.setAttribute("script"	, "alert('등록되었습니다.');");
				request.setAttribute("redirect"	, "/seller/product/list.web");
			}
			else {
				request.setAttribute("script"	, "alert('시스템 관리자에게 문의하세요!');");
				request.setAttribute("redirect"	, "/");
			}
			mav.setViewName("forward:/servlet/result.web");
		}
		catch (Exception e) {
			logger.error("[" + this.getClass().getName() + ".writeProc()] " + e.getMessage(), e);
		}
		finally {}
		
		return mav;
	}
	
	/**
	 * @param request [요청 서블릿]
	 * @param response [응답 서블릿]
	 * @return ModelAndView
	 * 
	 * @since 2024-08-08
	 * <p>DESCRIPTION: 판매자 상품 등록</p>
	 * <p>IMPORTANT:</p>
	 * <p>EXAMPLE:</p>
	 */
	@RequestMapping(value = "/seller/product/writeForm.web")
	public ModelAndView writeForm(HttpServletRequest request	, HttpServletResponse response, ProductDto productDto){

		ModelAndView mav = new ModelAndView("redirect:/error.web");
		
		try {
			productDto.setRegister(Integer.parseInt(getSession(request, "SEQ_SLL")));
			
			mav.setViewName("seller/product/writeForm");
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
	 * @return ModelAndView
	 * 
	 * @since 2024-08-08
	 * <p>DESCRIPTION: 판매자 상품 리스트</p>
	 * <p>IMPORTANT:</p>
	 * <p>EXAMPLE:</p>
	 */
	@RequestMapping(value = "/seller/product/list.web")
	public ModelAndView list(HttpServletRequest request, HttpServletResponse response, PagingDto pagingDto){
		
		ModelAndView mav = new ModelAndView("redirect:/error.web");
		
		try {
			pagingDto.setRegister(Integer.parseInt(getSession(request, "SEQ_SLL")));
			
			PagingListDto pagingListDto = productSrvc.list(pagingDto);
			mav.addObject("paging"	, pagingListDto.getPaging());
			mav.addObject("list"	, pagingListDto.getList());
			
			mav.setViewName("/seller/product/list");
		}
		catch (Exception e) {
			logger.error("[" + this.getClass().getName() + ".list()] " + e.getMessage(), e);
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
	@RequestMapping(value = "/seller/product/index.web")
	public ModelAndView index(HttpServletRequest request, HttpServletResponse response) {
		
		ModelAndView mav = new ModelAndView("redirect:/error.web");
		
		try {
			mav.setViewName("seller/product/index");
		}
		catch (Exception e) {
			logger.error("[" + this.getClass().getName() + ".index()] " + e.getMessage(), e);
		}
		finally {}
		
		return mav;
	}
}
