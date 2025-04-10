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
 * File			: BasketWeb.java
 * Notes		:
 * History		: [NO][Programmer][Description]
 *				: [20240826123632][pluto#plutozone.com][CREATE: Initial Release]
 */
package com.plutozone.front.basket.controller;

//import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.plutozone.front.basket.dto.BasketDto;
import com.plutozone.front.basket.service.BasketSrvc;
import com.plutozone.front.common.Common;

/**
 * @version 1.0.0
 * @author pluto#plutozone.com
 * 
 * @since 2024-08-26
 * <p>DESCRIPTION:</p>
 * <p>IMPORTANT:</p>
 */
@Controller("com.plutozone.front.basket.controller.BasketWeb")
public class BasketWeb extends Common {
	
	/** Logger */
	private static Logger logger = LoggerFactory.getLogger(BasketWeb.class);
	
	@Inject
	BasketSrvc basketSrvc;
	
	
	/**
	 * @param request [요청 서블릿]
	 * @param response [응답 서블릿]
	 * @return ModelAndView
	 * 
	 * @since 2024-08-27
	 * <p>DESCRIPTION:</p>
	 * <p>IMPORTANT:</p>
	 * <p>EXAMPLE:</p>
	 */
	@RequestMapping(value = "/front/basket/setBasketIframe.web")
	public ModelAndView setBasketIframe(HttpServletRequest request, HttpServletResponse response
			, String item) {
		
		ModelAndView mav = new ModelAndView("redirect:/error.web");
		
		try {
			logger.debug("item=" + item);
			
			String[] arrBasket = item.split("\\|");
			logger.debug("arrBasket.length=" + arrBasket.length);
			
			BasketDto basketDto = new BasketDto();
			
			basketDto.setSeq_mbr(Integer.parseInt(getSession(request, "SEQ_MBR")));
			basketDto.setSeq_sle(Integer.parseInt(arrBasket[0]));
			basketDto.setSeq_prd(Integer.parseInt(arrBasket[1]));
			basketDto.setSeq_sll(Integer.parseInt(arrBasket[2]));
			basketDto.setSle_nm(arrBasket[3]);
			basketDto.setPrice(Integer.parseInt(arrBasket[4]));
			basketDto.setCount(Integer.parseInt(arrBasket[5]));
			basketDto.setImg(arrBasket[6]);
			
			if (basketSrvc.insert(basketDto)) {
				request.setAttribute("script", "alert('장바구니에 저장되었습니다.');");
			}
			else {
				request.setAttribute("script", "alert('시스템 관리자에게 문의하세요!');");
				
			}
			mav.setViewName("forward:/servlet/result.web");
		}
		catch (Exception e) {
			logger.error("[" + this.getClass().getName() + ".setBasketIframe()] " + e.getMessage(), e);
		}
		finally {}
		
		return mav;
	}
	
	/**
	 * @param request [요청 서블릿]
	 * @param response [응답 서블릿]
	 * @return ModelAndView
	 * 
	 * @since 2024-08-27
	 * <p>DESCRIPTION:</p>
	 * <p>IMPORTANT:</p>
	 * <p>EXAMPLE:</p>
	 */
	@RequestMapping(value = "/front/basket/setBasketSession.web")
	public ModelAndView setBasketSession(HttpServletRequest request, HttpServletResponse response
			, String item) {
		
		ModelAndView mav = new ModelAndView("redirect:/error.web");
		
		try {
			
			HttpSession session = request.getSession(false);
			
			// 장바구니가 비어 있을 경우
			if (session.getAttribute("ITEM") == null) {
				session.setAttribute("ITEM", item);
			}
			// 장바구니에 상품을 있을 경우 구분자로 계속 추가
			else {
				session.setAttribute("ITEM", session.getAttribute("ITEM") + "," + item);
			}
			logger.debug(session.getAttribute("ITEM") + "");
			
			request.setAttribute("script", "alert('장바구니에 저장되었습니다.');");
			mav.setViewName("forward:/servlet/result.web");
		}
		catch (Exception e) {
			logger.error("[" + this.getClass().getName() + ".setBasketSession()] " + e.getMessage(), e);
		}
		finally {}
		
		return mav;
	}
	
	/**
	 * @param request [요청 서블릿]
	 * @param response [응답 서블릿]
	 * @return ModelAndView
	 * 
	 * @since 2024-08-26
	 * <p>DESCRIPTION:</p>
	 * <p>IMPORTANT:</p>
	 * <p>EXAMPLE:</p>
	 */
	@RequestMapping(value = "/front/basket/index.web")
	public ModelAndView index(HttpServletRequest request, HttpServletResponse response) {
		
		ModelAndView mav = new ModelAndView("redirect:/error.web");
		
		try {
			/* Database + iFrame
			List<BasketDto> list = basketSrvc.listing(Integer.parseInt(getSession(request, "SEQ_MBR")));
			
			String item = "";
			
			for (int loop = 0; loop < list.size(); loop++) {
				item += list.get(loop).getSeq_sle()
						+ "|" + list.get(loop).getSeq_prd()
						+ "|" + list.get(loop).getSeq_sll()
						+ "|" + list.get(loop).getSle_nm()
						+ "|" + list.get(loop).getPrice()
						+ "|" + list.get(loop).getCount()
						+ "|" + list.get(loop).getImg();
				
				if (list.size() > 1 && loop < list.size() - 1) item += ",";
				
			}
			logger.debug(item);
			mav.addObject("item", item);
			*/
			
			/* Session + iFrame */
			HttpSession session = request.getSession(false);
			mav.addObject("item", session.getAttribute("ITEM"));
			
			mav.setViewName("front/basket/index");
		}
		catch (Exception e) {
			logger.error("[" + this.getClass().getName() + ".index()] " + e.getMessage(), e);
		}
		finally {}
		
		return mav;
	}
	
	/**
	 * @param request [요청 서블릿]
	 * @param response [응답 서블릿]
	 * @return ModelAndView
	 * 
	 * @since 2024-08-26
	 * <p>DESCRIPTION:</p>
	 * <p>IMPORTANT:</p>
	 * <p>EXAMPLE:</p>
	 */
	@RequestMapping(value = "/front/basket/main.web")
	public ModelAndView main(HttpServletRequest request, HttpServletResponse response) {
		
		ModelAndView mav = new ModelAndView("redirect:/error.web");
		
		try {
			/* Database + iFrame
			List<BasketDto> list = basketSrvc.listing(Integer.parseInt(getSession(request, "SEQ_MBR")));
			
			String item = "";
			
			for (int loop = 0; loop < list.size(); loop++) {
				item += list.get(loop).getSeq_sle()
						+ "|" + list.get(loop).getSeq_prd()
						+ "|" + list.get(loop).getSeq_sll()
						+ "|" + list.get(loop).getSle_nm()
						+ "|" + list.get(loop).getPrice()
						+ "|" + list.get(loop).getCount()
						+ "|" + list.get(loop).getImg();
				
				if (list.size() > 1 && loop < list.size() - 1) item += ",";
				
			}
			logger.debug(item);
			mav.addObject("item", item);
			*/
			
			/* Session + iFrame */
			HttpSession session = request.getSession(false);
			mav.addObject("item", session.getAttribute("ITEM"));
			
			mav.setViewName("front/basket/index");
		}
		catch (Exception e) {
			logger.error("[" + this.getClass().getName() + ".main()] " + e.getMessage(), e);
		}
		finally {}
		
		return mav;
	}
}