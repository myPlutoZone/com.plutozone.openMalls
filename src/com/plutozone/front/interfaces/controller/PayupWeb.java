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
 * File			: PayupWeb.java
 * Notes		:
 * History		: [NO][Programmer][Description]
 *				: [20241010154752][pluto#plutozone.com][CREATE: Initial Release]
 *				: [20240207164634][pluto#plutozone.com][REPORT: From com.payup.pay.controller.PayController Old(BASE ON DEAL NUM)]
 *				: [20241018152000][pluto#plutozone.com][REPORT: From com.payup.pay.controller.PayController New(BASE ON ORDER NUM) and Old is changed]
 */
package com.plutozone.front.interfaces.controller;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.plutozone.Security;
import com.plutozone.front.buy.dto.BuyDetailDto;
import com.plutozone.front.buy.dto.BuyDetailListDto;
import com.plutozone.front.buy.dto.BuyMasterDto;
import com.plutozone.front.buy.service.BuySrvc;
import com.plutozone.front.common.Common;
import com.plutozone.front.interfaces.component.PayupCmpn;
import com.plutozone.util.Datetime;
import com.plutozone.util.servlet.Request;

/**
 * @version 1.0.0
 * @author pluto#plutozone.com
 * 
 * @since 2024-10-10
 * <p>DESCRIPTION:</p>
 * <p>IMPORTANT:
 * - [2024-10-14][pluto#plutozone.com][TODO-개선: 가격, 다중 상품 및 AppUrl 적용 필요]
 * - [2024-10-23][pluto#plutozone.com][REPORT: 모바일을 위해 receive() 및 receiveOld() 서버 설정 필요]
 * </p>
 */
@Controller("com.plutozone.front.interfaces.controller.PayupWeb")
public class PayupWeb extends Common {
	
	/** Logger */
	private static Logger logger = LoggerFactory.getLogger(PayupWeb.class);
	
	@Autowired
	Properties staticProperties;
	
	@Inject
	private PayupCmpn payupCmpn;
	
	@Inject
	private BuySrvc buySrvc;
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/front/interface/payup/receive.api")
	public ModelAndView receive(@RequestParam Map<String, String> param, HttpServletRequest request) throws NoSuchAlgorithmException {
		
		ModelAndView mav = new ModelAndView();
		
		try {
			
			boolean isResult	= false;
			String num_order	= getSession(request, "NUM_ORDER");
			String amount		= getSession(request, "AMOUNT");
			String num_deal		= param.get("transactionId");
			
			/** 뭐야, 이건! 달랑 transactionId만 주면 어떻게 하라고^^; */
			if (num_order != null && num_deal != null) {
				
				String token = "";
				
				// 토큰 발행
				String url = "https://standard.testpayup.co.kr/auth/v1/accessToken";
				Map<String,String> map = new HashMap<String, String>();
				
				map.put("merchantId", Security.payup_merchantId);
				map.put("apiKey", Security.payup_key);
				Map<String,Object> apiResult = payupCmpn.JsonApi(request, url, map);
				logger.debug("-----------------------------------------------------");
				logger.debug("토큰 발행 응답 = " + apiResult.toString());
				logger.debug("-----------------------------------------------------");
				
				// 토큰 성공
				if (apiResult.get("messageCode").equals("0000")) {
					
					Map<String, Object> data = (Map<String, Object>) apiResult.get("data");
					token = (String) data.get("accessToken");
					
					// 결제 승인
					String authUrl = "https://standard.testpayup.co.kr/api/v1/payment";
					Map<String,String> authMap = new HashMap<String, String>();
					authMap.put("transactionId"	, num_deal);
					authMap.put("orderNumber"	, num_order);
					authMap.put("amount"		, amount);
					authMap.put("merchantId"	, Security.payup_merchantId);
					Map<String,Object> authApiResult = payupCmpn.JsonApi(request, authUrl, authMap, token);
					logger.debug("-----------------------------------------------------");
					logger.debug("결제 승인 응답 : " + authApiResult.toString());
					logger.debug("-----------------------------------------------------");
					
					data = (Map<String, Object>) authApiResult.get("data");
					// 결제 성공
					if ("0000".equals(data.get("responseCode"))) {
						
							isResult = buySrvc.updateByOrderNum(num_order, num_deal, Integer.parseInt(getSession(request, "SEQ_MBR")), "Y");
							request.setAttribute("script"	, "alert('정상적으로 결제되었습니다. 구매해 주셔서 감사합니다.');");
							request.setAttribute("redirect"	, "/");
					}
					// 결제 실패
					else {
						isResult = buySrvc.updateByOrderNum(num_order, num_deal, Integer.parseInt(getSession(request, "SEQ_MBR")), "N");
						
						request.setAttribute("script"	, "alert('[" + data.get("responseCode") + "]결제 처리에 실패하였습니다. 고객센터(=Payup)로 문의바랍니다!');");
						request.setAttribute("redirect"	, "/");
					}
					
					// 결제 결과에 대한 업데이트 실패 시
					if (!isResult) {
						request.setAttribute("script"	, "alert('[P001]결제 처리에 실패하였습니다. 고객센터로 문의바랍니다!');");
						request.setAttribute("redirect"	, "/");
					}
				}
				// 토큰 실패
				else {
					request.setAttribute("script"	, "alert('[" + apiResult.get("messageCode") + "]결제가 실패(토큰)되었습니다. 고객센터(=Payup)로 문의바랍니다!');");
					request.setAttribute("redirect"	, "/");
				}
			}
			else {
				logger.error("[" + this.getClass().getName() + ".receive()] num_order + num_deal = " + num_order + " + " + num_deal);
				isResult = buySrvc.updateByOrderNum(num_order, num_deal, Integer.parseInt(getSession(request, "SEQ_MBR")), "N");
				
				request.setAttribute("script"	, "alert('[P000]결제가 실패되었습니다. 고객센터(=Payup)로 문의바랍니다!');");
				request.setAttribute("redirect"	, "/");
			}
			mav.setViewName("forward:/servlet/result.web");
		}
		catch (Exception e) {
			request.setAttribute("script"	, "alert('[P000]결제 처리에 실패하였습니다. 고객센터로 문의바랍니다!');");
			request.setAttribute("redirect"	, "/");
			mav.setViewName("forward:/servlet/result.web");
			logger.error("[" + this.getClass().getName() + ".receive()] " + e.getMessage(), e);
		}
		finally {
			// 주문 번호 초기화
			HttpSession session = request.getSession(false);
			session.setAttribute("NUM_ORDER", null);
		}
		
		return mav;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/front/interface/payup/pay.json")
	public @ResponseBody Map<String, Object> pay(@RequestBody Map<String,String> param, HttpServletRequest request) throws NoSuchAlgorithmException {
		
		Map<String, Object> returnMap = new HashMap<>();
		
		try {
			
			//System.out.println(param.toString());
			String transactionId	= param.get("transactionId");
			String merchantId		= param.get("merchantId");
			String orderNumber		= param.get("orderNumber");
			String amount			= param.get("amount");
			String token			= "";
			
			// 토큰 발행
			String url = "https://standard.testpayup.co.kr/auth/v1/accessToken";
			Map<String,String> map = new HashMap<String, String>();
			
			map.put("merchantId", Security.payup_merchantId);
			map.put("apiKey", Security.payup_key);
			Map<String,Object> apiResult = payupCmpn.JsonApi(request, url, map);
			logger.debug("-----------------------------------------------------");
			logger.debug("토큰 발행 응답 = " + apiResult.toString());
			logger.debug("-----------------------------------------------------");
			
			if (apiResult.get("messageCode").equals("0000")) {
				
				Map<String, Object> data = (Map<String, Object>) apiResult.get("data");
				token = (String) data.get("accessToken");
				
				// 결제 승인
				String authUrl = "https://standard.testpayup.co.kr/api/v1/payment";
				Map<String,String> authMap = new HashMap<String, String>();
				authMap.put("transactionId"	, transactionId);
				authMap.put("orderNumber"	, orderNumber);
				authMap.put("amount"		, amount);
				authMap.put("merchantId"	, merchantId);
				Map<String,Object> authApiResult = payupCmpn.JsonApi(request, authUrl, authMap, token);
				logger.debug("-----------------------------------------------------");
				logger.debug("결제 승인 응답 : " + authApiResult.toString());
				logger.debug("-----------------------------------------------------");
				
				String num_deal		= transactionId;
				boolean isResult	= true;
				
				data = (Map<String, Object>) authApiResult.get("data");
				if ("0000".equals(data.get("responseCode"))) {
					
					isResult = buySrvc.updateByOrderNum(orderNumber, num_deal, Integer.parseInt(getSession(request, "SEQ_MBR")), "Y");
					
					returnMap.put("responseCode", "0000");
					returnMap.put("responseMsg", "정상적으로 결제되었습니다. 구매해 주셔서 감사합니다.");
				}
				else {
					isResult = buySrvc.updateByOrderNum(orderNumber, num_deal, Integer.parseInt(getSession(request, "SEQ_MBR")), "N");
					
					returnMap.put("responseCode", data.get("responseCode"));
					returnMap.put("responseMsg", "결제가 실패되었습니다. 고객센터(=Payup)로 문의바랍니다!");
				}
				
				// 결제 결과에 대한 업데이트 실패 시
				if (!isResult) {
					returnMap.put("responseCode", "P001");
					returnMap.put("responseMsg", "결제 처리에 실패하였습니다. 고객센터로 문의바랍니다!");
				}
			}
			else {
				returnMap.put("responseCode", apiResult.get("messageCode"));
				returnMap.put("responseMsg", "결제가 실패(토큰)되었습니다. 고객센터(=Payup)로 문의바랍니다!");
			}
		}
		catch (Exception e) {
			returnMap.put("responseCode", "P999");
			returnMap.put("responseMsg", "결제 처리에 실패하였습니다. 고객센터로 문의바랍니다!");
			logger.error("[" + this.getClass().getName() + ".pay()] " + e.getMessage(), e);
		}
		finally {
			// 주문 번호 초기화
			HttpSession session = request.getSession(false);
			session.setAttribute("NUM_ORDER", null);
		}
		
		return returnMap;
	}
	
	@RequestMapping(value = "/front/interface/payup/order.json", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> order(@RequestParam Map<String, String> param, HttpServletRequest request, BuyDetailListDto buyDetailListDto) throws NoSuchAlgorithmException {
		
		Map<String,Object> returnMap = new HashMap<>();
		
		try {
			
			returnMap.put("responseCode", "0000");
			
			if ("0000".equals(returnMap.get("responseCode"))) {
				
				if (buyDetailListDto.getBuyList() != null) {
					
					// 주문 번호
					String num_order = "HM-MOON-" + Datetime.getNow("yyyyMMddHHmmss") + ((int)(Math.random() * 8999) + 1000);
					
					// 마스터 설정
					BuyMasterDto buyMasterDto = new BuyMasterDto();
					buyMasterDto.setSeq_mbr(Integer.parseInt(getSession(request, "SEQ_MBR")));
					buyMasterDto.setSeq_sll(buyDetailListDto.getBuyList().get(0).getRegister());
					buyMasterDto.setBuy_info(buyDetailListDto.getBuyList().get(0).getSle_nm() + "(수량: " + buyDetailListDto.getBuyList().get(0).getCount() + ")");
					buyMasterDto.setBuy_count(buyDetailListDto.getBuyList().get(0).getCount());
					buyMasterDto.setBuy_price(buyDetailListDto.getBuyList().get(0).getPrice() * buyDetailListDto.getBuyList().get(0).getCount());
					buyMasterDto.setRegister(Integer.parseInt(getSession(request, "SEQ_MBR")));
					
					int seq_buy_mst = buySrvc.insertByOrderNum(buyMasterDto, (ArrayList<BuyDetailDto>) buyDetailListDto.getBuyList(), num_order); 
					
					if (seq_buy_mst > 0) {
						
						returnMap.put("merchantId"		, Security.payup_merchantId);
						returnMap.put("itemName"		, buyDetailListDto.getBuyList().get(0).getSle_nm());
						returnMap.put("amount"			, "100");
						returnMap.put("userName"		, getSession(request, "NAME"));
						returnMap.put("orderNumber"		, num_order);
						
						HttpSession session = request.getSession(false);
						session.setAttribute("NUM_ORDER"	, num_order);
						session.setAttribute("AMOUNT"		, "100");
						
						String serverUrl = "";
						if (staticProperties.getProperty("common.mode", "[UNDEFINED]").equalsIgnoreCase("LOCAL")) {
							serverUrl = "http://119.71.96.251:"
										+ staticProperties.getProperty("common.server.port", "[UNDEFINED]");
						}
						else if (staticProperties.getProperty("common.mode", "[UNDEFINED]").equalsIgnoreCase("DEVELOPMENT")) {
							 // moon(8080), jupiter(8081), saturn(8082), uranus(8083)
							serverUrl = "http://moon.plutozone.com";
						}
						else {
							serverUrl = "[UNDEFINED]";
						}
						
						/** 모바일에서는 pay()가 아닌 receive()로 응답을 처리 */
						if (Request.isDevice(request, "mobile")) {
							returnMap.put("returnUrl", serverUrl + "/front/interface/payup/receive.api");
						}
						else returnMap.put("returnUrl", "");
					}
					else {
						// 구매 정보 저장 실패
						returnMap.put("responseCode", "B001");
						returnMap.put("responseMsg", "구매 정보 저장 실패");
					}
				}
				else {
					// 상품 정보 부재
					returnMap.put("responseCode", "B002");
					returnMap.put("responseMsg", "구매 정보 부재");
				}
			}
			else {
				// 연동 에러
				returnMap.put("responseCode", returnMap.get("responseCode"));
				returnMap.put("responseMsg", "구매 등록 처리 실패(=Payup)");
				logger.error("[" + this.getClass().getName() + ".order().RES.FAILURE] " + returnMap.toString());
			}
		}
		catch (Exception e) {
			// 알 수 없는 에러
			returnMap.put("responseCode", "B000");
			returnMap.put("responseMsg", "구매 등록 처리 실패");
			logger.error("[" + this.getClass().getName() + ".order()] " + e.getMessage(), e);
		}
		finally {}
		
		return returnMap;
	}
	
	@RequestMapping(value = "/front/interface/payup/receiveOld.api")
	public ModelAndView receiveOld(@RequestParam Map<String, String> param, HttpServletRequest request) throws NoSuchAlgorithmException {
		
		ModelAndView mav = new ModelAndView();
		
		try {
			
			// logger.info("[" + this.getClass().getName() + ".receiveOld().RES1st] " + param.toString());
			if ("0000".equals(param.get("res_cd"))) {
				
				String res_cd		= param.get("res_cd");
				String ordr_idxx	= param.get("ordr_idxx");
				String res_msg		= param.get("res_msg");
				String enc_data		= param.get("enc_data");
				String enc_info		= param.get("enc_info");
				String tran_cd		= param.get("tran_cd");
				String buyr_mail	= param.get("buyr_mail");
				
				String url = "https://api.testpayup.co.kr/ap/api/payment/" + ordr_idxx + "/pay";
				Map<String,String> apiMap = new HashMap<>();		
				apiMap.put("res_cd",res_cd);
				apiMap.put("ordr_idxx",ordr_idxx);
				apiMap.put("res_msg",res_msg);
				apiMap.put("enc_data",enc_data);
				apiMap.put("enc_info",enc_info);
				apiMap.put("tran_cd",tran_cd);
				apiMap.put("buyr_mail",buyr_mail);
				
				Map<String,Object> apiResult = new HashMap<>();
				apiResult = payupCmpn.JsonApi(request, url, apiMap);
				
				// logger.info("통신 결과[" + this.getClass().getName() + ".receiveOld().RES2nd] " + apiResult.toString());
				
				/** 페이업 거래번호 */
				String num_deal = (String) apiResult.get("transactionId");
				boolean isResult = true;
				
				if ("0000".equals(apiResult.get("responseCode"))) {
					
					// logger.info("[" + this.getClass().getName() + ".receiveOld().RES2nd.SUCCESS] " + apiResult.toString());
					isResult = buySrvc.updateByDealNum(num_deal, Integer.parseInt(getSession(request, "SEQ_MBR")), "Y");
					
					request.setAttribute("script"	, "alert('정상적으로 결제되었습니다. 구매해 주셔서 감사합니다.');");
					request.setAttribute("redirect"	, "/");
					
				}
				else {
					logger.error("[" + this.getClass().getName() + ".receiveOld().RES2nd.FAILURE] " + apiResult.toString());
					isResult = buySrvc.updateByDealNum(num_deal, Integer.parseInt(getSession(request, "SEQ_MBR")), "N");
					
					request.setAttribute("script"	, "alert('[" + apiResult.get("responseCode") + "]결제가 실패되었습니다. 고객센터(=Payup)로 문의바랍니다!');");
					request.setAttribute("redirect"	, "/");
				}
				
				// 결제 결과에 대한 업데이트 실패 시
				if (!isResult) {
					request.setAttribute("script"	, "alert('[P001]결제 처리에 실패하였습니다. 고객센터로 문의바랍니다!');");
					request.setAttribute("redirect"	, "/");
				}
			}
			else {
				logger.error("[" + this.getClass().getName() + ".receiveOld().RES1st.FAILURE] " + param.toString());
				request.setAttribute("script"	, "alert('[" + param.get("res_cd") + "]결제 처리에 실패하였습니다. 고객센터(=Payup)로 문의바랍니다!');");
				request.setAttribute("redirect"	, "/");
			}
			
			mav.setViewName("forward:/servlet/result.web");
		}
		catch (Exception e) {
			request.setAttribute("script"	, "alert('[P000]결제 처리에 실패하였습니다. 고객센터로 문의바랍니다!');");
			request.setAttribute("redirect"	, "/");
			mav.setViewName("forward:/servlet/result.web");
			logger.error("[" + this.getClass().getName() + ".receiveOld()] " + e.getMessage(), e);
		}
		finally {}
		
		return mav;
	}
	
	@RequestMapping(value = "/front/interface/payup/payOld.web")
	public ModelAndView payOld(@RequestParam Map<String, String> param, HttpServletRequest request) throws NoSuchAlgorithmException {
		
		ModelAndView mav = new ModelAndView();
		
		try {
			logger.info("화면에서 넘어온값[" + this.getClass().getName() + ".payOld().REQ] " + param.toString());
			
			String res_cd		= param.get("res_cd");
			String ordr_idxx	= param.get("ordr_idxx");
			String res_msg		= param.get("res_msg");
			String enc_data		= param.get("enc_data");
			String enc_info		= param.get("enc_info");
			String tran_cd		= param.get("tran_cd");
			String buyr_mail	= param.get("buyr_mail");
			
			String url = "https://api.testpayup.co.kr/ap/api/payment/" + ordr_idxx + "/pay";
			Map<String,String> apiMap = new HashMap<>();		
			apiMap.put("res_cd",res_cd);
			apiMap.put("ordr_idxx",ordr_idxx);
			apiMap.put("res_msg",res_msg);
			apiMap.put("enc_data",enc_data);
			apiMap.put("enc_info",enc_info);
			apiMap.put("tran_cd",tran_cd);
			apiMap.put("buyr_mail",buyr_mail);
			
			Map<String,Object> apiResult = new HashMap<>();
			apiResult = payupCmpn.JsonApi(request, url, apiMap);
			
			logger.info("통신 결과[" + this.getClass().getName() + ".payOld().RES] " + apiResult.toString());
			
			/** 페이업 거래번호 */
			String num_deal = (String) apiResult.get("transactionId");
			boolean isResult = true;
			
			if ("0000".equals(apiResult.get("responseCode"))) {
				
				// logger.info("[" + this.getClass().getName() + ".payOld().RES.SUCCESS] " + apiResult.toString());
				isResult = buySrvc.updateByDealNum(num_deal, Integer.parseInt(getSession(request, "SEQ_MBR")), "Y");
				
				request.setAttribute("script"	, "alert('정상적으로 결제되었습니다. 구매해 주셔서 감사합니다.');");
				request.setAttribute("redirect"	, "/");
				/*
				mav.setViewName("pay/pay_s");
				mav.addObject("responseMsg"		,apiResult.get("responseMsg"));
				mav.addObject("cardName"		,apiResult.get("cardName"));
				mav.addObject("transactionId"	,apiResult.get("transactionId"));
				*/
			}
			else {
				logger.error("[" + this.getClass().getName() + ".payProcess().RES.FAILURE] " + apiResult.toString());
				isResult = buySrvc.updateByDealNum(num_deal, Integer.parseInt(getSession(request, "SEQ_MBR")), "N");
				
				request.setAttribute("script"	, "alert('[" + apiResult.get("responseCode") + "]결제가 실패되었습니다. 고객센터(=Payup)로 문의바랍니다!');");
				request.setAttribute("redirect"	, "/");
				
				/*
				mav.setViewName("pay/pay_f");
				mav.addObject("responseMsg"		,apiResult.get("responseMsg"));
				*/
			}
			
			// 결제 결과에 대한 업데이트 실패 시
			if (!isResult) {
				request.setAttribute("script"	, "alert('[P001]결제 처리에 실패하였습니다. 고객센터로 문의바랍니다!');");
				request.setAttribute("redirect"	, "/");
			}
			
			mav.setViewName("forward:/servlet/result.web");
			
		}
		catch (Exception e) {
			request.setAttribute("script"	, "alert('[P000]결제 처리에 실패하였습니다. 고객센터로 문의바랍니다!');");
			request.setAttribute("redirect"	, "/");
			mav.setViewName("forward:/servlet/result.web");
			logger.error("[" + this.getClass().getName() + ".payOld()] " + e.getMessage(), e);
		}
		finally {}
		
		return mav;	
	}
	
	// [2024-02-08][pluto#plutozone.com][REPORT: jQuery 확인 필요 for @RequestMapping(value = "/front/interface/payup/order.json", method = RequestMethod.POST, headers = {"content-type=application/json; charset=UTF-8", "accept=application/json"}, consumes="application/json; charset=UTF-8", produces="application/json; charset=UTF-8") ]
	@RequestMapping(value = "/front/interface/payup/orderOld.json", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> orderOld(@RequestParam Map<String, String> param, HttpServletRequest request, BuyDetailListDto buyDetailListDto) throws NoSuchAlgorithmException {
		
		Map<String,Object> returnMap = new HashMap<>();
		
		try {
			// logger.info("[" + this.getClass().getName() + ".orderOld().REQ] " + param.toString());
			
			String yyyyMMddHHmmss = Datetime.getNow("yyyyMMddHHmmss");
			
			String merchantId			= Security.payup_merchantId;
			String key					= Security.payup_key;
			String orderNumber			= "HM-" + yyyyMMddHHmmss;
			String amount				= "100";
			// String amount			= Integer.toString(Integer.parseInt(param.get("buyList[0].price")) * Integer.parseInt(param.get("buyList[0].count")));
																			// 금액: param.get("amount");
			String quota				= "0";								// 할부: param.get("quota")
			String itemName				= param.get("buyList[0].sle_nm");	// 판매 상품명: param.get("itemName");
			String userName				= getSession(request, "NAME");		// 구매자명 param.get("userName");
			String userAgent			= "WP";
			String returnUrl			= "returnUrl";
			String signature			= "";	//아래에서 생성
			String timestamp			= yyyyMMddHHmmss;	
			
			signature = payupCmpn.getSHA256Hash(merchantId + "|" + orderNumber + "|" + amount + "|" + key + "|" + timestamp);
			
			String url = "https://api.testpayup.co.kr/ap/api/payment/" + merchantId + "/order";
			Map<String,String> apiMap = new HashMap<>();
			apiMap.put("orderNumber"	,orderNumber);
			apiMap.put("amount"			,amount);
			apiMap.put("itemName"		,itemName);
			apiMap.put("userName"		,userName);
			apiMap.put("signature"		,signature);
			apiMap.put("timestamp"		,timestamp);
			
			String serverUrl = "";
			if (staticProperties.getProperty("common.mode", "[UNDEFINED]").equalsIgnoreCase("LOCAL")) {
				serverUrl = "http://119.71.96.251:"
							+ staticProperties.getProperty("common.server.port", "[UNDEFINED]");
			}
			else if (staticProperties.getProperty("common.mode", "[UNDEFINED]").equalsIgnoreCase("DEVELOPMENT")) {
				 // moon(8080), jupiter(8081), saturn(8082), uranus(8083)
				serverUrl = "http://moon.plutozone.com";
			}
			else {
				serverUrl = "[UNDEFINED]";
			}
			
			if (Request.isDevice(request, "mobile")) {
				apiMap.put("auth_return", serverUrl + "/front/interface/payup/receiveOld.api");
			}
			else {
				apiMap.put("userAgent", userAgent);
				apiMap.put("returnUrl", returnUrl);
			}
			
			apiMap.put("quota",quota);
			apiMap.put("bypassValue","himediaValue");
			
			returnMap = payupCmpn.JsonApi(request, url, apiMap);
			
			// logger.info("통신 결과[" + this.getClass().getName() + ".orderOld().RES] " + returnMap.toString());
			
			if ("0000".equals(returnMap.get("responseCode"))) {
				// logger.info("[" + this.getClass().getName() + ".orderOld().RES.SUCCESS] " + returnMap.toString());
				
				if (buyDetailListDto.getBuyList() != null) {
					
					/** 페이업 거래번호 */
					String num_deal = (String) returnMap.get("ordr_idxx");
					
					// 마스터 설정
					BuyMasterDto buyMasterDto = new BuyMasterDto();
					buyMasterDto.setSeq_mbr(Integer.parseInt(getSession(request, "SEQ_MBR")));
					buyMasterDto.setSeq_sll(buyDetailListDto.getBuyList().get(0).getRegister());
					buyMasterDto.setBuy_info(buyDetailListDto.getBuyList().get(0).getSle_nm() + "(수량: " + buyDetailListDto.getBuyList().get(0).getCount() + ")");
					buyMasterDto.setBuy_count(buyDetailListDto.getBuyList().get(0).getCount());
					buyMasterDto.setBuy_price(buyDetailListDto.getBuyList().get(0).getPrice() * buyDetailListDto.getBuyList().get(0).getCount());
					buyMasterDto.setRegister(Integer.parseInt(getSession(request, "SEQ_MBR")));
					
					if (!buySrvc.insertByDealNum(buyMasterDto, (ArrayList<BuyDetailDto>) buyDetailListDto.getBuyList(), num_deal)) {
						// 구매 정보 저장 에러
						returnMap.put("responseCode", "B001");
						returnMap.put("responseMsg", "구매 정보 저장 실패");
					}
				}
				else {
					// 상품 정보 부재 에러
					returnMap.put("responseCode", "B002");
					returnMap.put("responseMsg", "구매 정보 부재");
				}
			}
			else {
				// 연동 에러
				returnMap.put("responseCode", returnMap.get("responseCode"));
				returnMap.put("responseMsg", "구매 등록 처리 실패(=Payup)");
				logger.error("[" + this.getClass().getName() + ".orderOld().RES.FAILURE] " + returnMap.toString());
			}
		}
		catch (Exception e) {
			// 알 수 없는 에러
			returnMap.put("responseCode", "B000");
			returnMap.put("responseMsg", "구매 등록 처리 실패");
			logger.error("[" + this.getClass().getName() + ".orderOld()] " + e.getMessage(), e);
		}
		finally {}
		
		return returnMap;
	}
}