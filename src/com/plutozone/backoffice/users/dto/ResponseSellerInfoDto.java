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
 * File			: ResponseSellerInfoDto.java
 * Notes		:
 * History		: [NO][Programmer][Description]
 *				: [20240829115647][pluto#plutozone.com][CREATE: Initial Release]
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
public class ResponseSellerInfoDto {
	
	private String b_no;				// 사업자 등록 번호
	private String valid;
	private ResponseSellerInfoParamDto request_param;
	private ResponseSellerInfoStatusDto status;
	
	
	public String getB_no() {
		return b_no;
	}
	public void setB_no(String b_no) {
		this.b_no = b_no;
	}
	public String getValid() {
		return valid;
	}
	public void setValid(String valid) {
		this.valid = valid;
	}
	public ResponseSellerInfoParamDto getRequest_param() {
		return request_param;
	}
	public void setRequest_param(ResponseSellerInfoParamDto request_param) {
		this.request_param = request_param;
	}
	public ResponseSellerInfoStatusDto getStatus() {
		return status;
	}
	public void setStatus(ResponseSellerInfoStatusDto status) {
		this.status = status;
	}
}