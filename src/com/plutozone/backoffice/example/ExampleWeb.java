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
 * File			: ExampleController.java
 * Notes		:
 * History		: [NO][Programmer][Description]
 *				: [20241022164152][pluto#plutozone.com][CREATE: Initial Release]
 */
package com.plutozone.backoffice.example;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @version 1.0.0
 * @author pluto#plutozone.com
 * 
 * @since 2024-10-22
 * <p>DESCRIPTION:</p>
 * <p>IMPORTANT:</p>
 */
@Controller("com.plutozone.backoffice.example.ExampleWeb")
public class ExampleWeb {
	
	/** Logger */
	private static Logger logger = LoggerFactory.getLogger(ExampleWeb.class);
	
	@Inject
	ExampleSrvc exampleSrvc;
	/**
	 * @param request [요청 서블릿]
	 * @param response [응답 서블릿]
	 * @return ModelAndView
	 * 
	 * @since 2024-07-26
	 * <p>DESCRIPTION:</p>
	 * <p>IMPORTANT:</p>
	 * <p>EXAMPLE:</p>
	 */
	@RequestMapping(value = "/console/example/myBatisForeach.web")
	public ModelAndView myBatisForeach(HttpServletRequest request, HttpServletResponse response, String url) {
		
		ModelAndView mav = new ModelAndView("redirect:/error.web");
		
		try {
			
			ExamplesDto examplesDto = new ExamplesDto();
			
			int[] nums = {1, 2, 3, 4};
			examplesDto.setNums(nums);
			
			List<ExampleDto> list = exampleSrvc.myBatisForeach(examplesDto);
			
			for (int loop = 0; loop < list.size(); loop++) {
				System.out.println(list.get(loop).getNum() + " : " + list.get(loop).getName());
			}
			
			request.setAttribute("script", "alert('로그로 확인하세요.')");
			request.setAttribute("redirect"	, "/console/");
			
			mav.setViewName("forward:/servlet/result.web");
		}
		catch (Exception e) {
			logger.error("[" + this.getClass().getName() + ".myBatisForeach()] " + e.getMessage(), e);
		}
		finally {}
		
		return mav;
	}
}