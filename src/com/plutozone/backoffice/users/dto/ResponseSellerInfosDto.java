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
 * File			: ResponseSellerInfosDto.java
 * Notes		:
 * History		: [NO][Programmer][Description]
 *				: [20240829115120][pluto#plutozone.com][CREATE: Initial Release]
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
public class ResponseSellerInfosDto {
	
	
	/*
	 {
    "request_cnt": 1,
    "status_code": "OK",
    "data": [
        {
            "b_no": "1234567890",
            "valid": "02",
            "valid_msg": "확인할 수 없습니다.",
            "request_param": {
                "b_no": "1234567890",
                "start_dt": "20240101",
                "p_nm": "이석미"
            }
        }
    ]
}
	*/
	private int request_cnt;
	private int valid_cnt;
	private String status_code;				// 상태 코드(OK 등)
	private ResponseSellerInfoDto[] data;
	
	
	public int getRequest_cnt() {
		return request_cnt;
	}
	public void setRequest_cnt(int request_cnt) {
		this.request_cnt = request_cnt;
	}
	public int getValid_cnt() {
		return valid_cnt;
	}
	public void setValid_cnt(int valid_cnt) {
		this.valid_cnt = valid_cnt;
	}
	public String getStatus_code() {
		return status_code;
	}
	public void setStatus_code(String status_code) {
		this.status_code = status_code;
	}
	public ResponseSellerInfoDto[] getData() {
		return data;
	}
	public void setData(ResponseSellerInfoDto[] data) {
		this.data = data;
	}
}