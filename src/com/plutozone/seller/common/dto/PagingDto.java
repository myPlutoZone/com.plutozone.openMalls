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
 * File			: PagingDto.java
 * Notes		:
 * History		: [NO][Programmer][Description]
 *				: [20240808113925][pluto#plutozone.com][CREATE: Initial Release]
 */
package com.plutozone.seller.common.dto;

/**
 * @version 1.0.0
 * @author pluto#plutozone.com
 * 
 * @since 2024-08-08
 * <p>DESCRIPTION:</p>
 * <p>IMPORTANT:</p>
 */
public class PagingDto {
	
	private int totalLine			= 0;	// 전체 라인(행) 수
	private int totalPage			= 0;	// 전체 페이지 수 = 전체 라인(행) 수 / 페이징할 라인수
	private int linePerPage			= 10;	// 페이징할 라인수
	private int currentPage			= 1;	// 현재 페이지
	
	private String searchKey		= "";	// 검색키(예: 타이틀, 내용, 타이틀+내용)
	private String searchWord		= "";	// 검색어
	
	private int register			= 0;	// [등록자]
	private String flg_delete		= "";	// [공통] 삭제 여부
	private int cd_state_sale		= 0;	// [판매] 판매 상태 코드
	private String cd_state_prd		= "";	// [상품] 상품 상태 코드 (1:판매중, 2:판매 중지, 9:재고 소진)
	private String sle_desces		= "";	// [판매] 판매 상품 설명
	
	
	public String getSle_desces() {
		return sle_desces;
	}
	public void setSle_desces(String sle_desces) {
		this.sle_desces = sle_desces;
	}
	public String getCd_state_prd() {
		return cd_state_prd;
	}
	public void setCd_state_prd(String cd_state_prd) {
		this.cd_state_prd = cd_state_prd;
	}
	public String getFlg_delete() {
		return flg_delete;
	}
	public void setFlg_delete(String flg_delete) {
		this.flg_delete = flg_delete;
	}
	public int getCd_state_sale() {
		return cd_state_sale;
	}
	public void setCd_state_sale(int cd_state_sale) {
		this.cd_state_sale = cd_state_sale;
	}
	public int getRegister() {
		return register;
	}
	public void setRegister(int register) {
		this.register = register;
	}
	public int getTotalLine() {
		return totalLine;
	}
	public void setTotalLine(int totalLine) {
		this.totalLine = totalLine;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public int getLinePerPage() {
		return linePerPage;
	}
	public void setLinePerPage(int linePerPage) {
		this.linePerPage = linePerPage;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public String getSearchKey() {
		return searchKey;
	}
	public void setSearchKey(String searchKey) {
		this.searchKey = searchKey;
	}
	public String getSearchWord() {
		return searchWord;
	}
	public void setSearchWord(String searchWord) {
		this.searchWord = searchWord;
	}
}