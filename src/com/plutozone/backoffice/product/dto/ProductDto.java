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
 * File			: ProductDto.java
 * Notes		:
 * History		: [NO][Programmer][Description]
 *				: [20240617161046][pluto#plutozone.com][CREATE: Initial Release]
 */
package com.plutozone.backoffice.product.dto;

/**
 * @version 1.0.0
 * @author pluto#plutozone.com
 * 
 * @since 2024-06-17
 * <p>DESCRIPTION:</p>
 * <p>IMPORTANT:</p>
 */
public class ProductDto {
	
	private String name			= "";		// 이름(한글)
	private String desc			= "";		// 설명
	private String category		= "";		// 카테고리
	private int priceCost		= 0;		// 구매 원가(원화 기준)
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public int getPriceCost() {
		return priceCost;
	}
	public void setPriceCost(int priceCost) {
		this.priceCost = priceCost;
	}
}