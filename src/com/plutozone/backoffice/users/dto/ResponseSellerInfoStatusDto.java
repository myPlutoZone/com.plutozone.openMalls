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
 * File			: ResponseSellerInfoStatusDto.java
 * Notes		:
 * History		: [NO][Programmer][Description]
 *				: [20240829154658][pluto#plutozone.com][CREATE: Initial Release]
 */
package com.plutozone.backoffice.users.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @version 1.0.0
 * @author pluto#plutozone.com
 * 
 * @since 2024-08-29
 * <p>DESCRIPTION:</p>
 * <p>IMPORTANT:</p>
 */
@JsonIgnoreProperties(ignoreUnknown=true)
public class ResponseSellerInfoStatusDto {
	
	private String b_no;				// 사업자 등록 번호
	private String b_stt;				// 계속사업자 or 휴업 or 폐업
	private String b_stt_cd;			// 01(계속사업자) or 02(휴업) or 03(폐업)
	private String tax_type;
	private String tax_type_cd;
	private String end_dt;				// 페업자일 경우 폐업 년월일
	private String utcc_yn;
	private String tax_type_change_dt;
	private String invoice_apply_dt;
	private String rbf_tax_type;
	private String rbf_tax_type_cd;
	
	
	public String getB_no() {
		return b_no;
	}
	public void setB_no(String b_no) {
		this.b_no = b_no;
	}
	public String getB_stt() {
		return b_stt;
	}
	public void setB_stt(String b_stt) {
		this.b_stt = b_stt;
	}
	public String getB_stt_cd() {
		return b_stt_cd;
	}
	public void setB_stt_cd(String b_stt_cd) {
		this.b_stt_cd = b_stt_cd;
	}
	public String getTax_type() {
		return tax_type;
	}
	public void setTax_type(String tax_type) {
		this.tax_type = tax_type;
	}
	public String getTax_type_cd() {
		return tax_type_cd;
	}
	public void setTax_type_cd(String tax_type_cd) {
		this.tax_type_cd = tax_type_cd;
	}
	public String getEnd_dt() {
		return end_dt;
	}
	public void setEnd_dt(String end_dt) {
		this.end_dt = end_dt;
	}
	public String getUtcc_yn() {
		return utcc_yn;
	}
	public void setUtcc_yn(String utcc_yn) {
		this.utcc_yn = utcc_yn;
	}
	public String getTax_type_change_dt() {
		return tax_type_change_dt;
	}
	public void setTax_type_change_dt(String tax_type_change_dt) {
		this.tax_type_change_dt = tax_type_change_dt;
	}
	public String getInvoice_apply_dt() {
		return invoice_apply_dt;
	}
	public void setInvoice_apply_dt(String invoice_apply_dt) {
		this.invoice_apply_dt = invoice_apply_dt;
	}
	public String getRbf_tax_type() {
		return rbf_tax_type;
	}
	public void setRbf_tax_type(String rbf_tax_type) {
		this.rbf_tax_type = rbf_tax_type;
	}
	public String getRbf_tax_type_cd() {
		return rbf_tax_type_cd;
	}
	public void setRbf_tax_type_cd(String rbf_tax_type_cd) {
		this.rbf_tax_type_cd = rbf_tax_type_cd;
	}
}