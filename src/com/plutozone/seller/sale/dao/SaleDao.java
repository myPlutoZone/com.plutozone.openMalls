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
 * File			: SaleDao.java
 * Notes		:
 * History		: [NO][Programmer][Description]
 *				: [20240808104816][pluto#plutozone.com][CREATE: Initial Release]
 */
package com.plutozone.seller.sale.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.plutozone.seller.common.dao.BaseDao;
import com.plutozone.seller.common.dto.PagingDto;
import com.plutozone.seller.sale.dto.SaleDto;
import com.plutozone.seller.sale.dto.SaleImageDto;

/**
 * @version 1.0.0
 * @author pluto#plutozone.com
 * 
 * @since 2024-08-08
 * <p>DESCRIPTION:</p>
 * <p>IMPORTANT:</p>
 */
@Repository("com.plutozone.seller.sale.dao.SaleDao")
public class SaleDao extends BaseDao{
	
	/**
	 * @param fileDto [파일 빈]
	 * 
	 * @return int
	 * 
	 * @since 2024-10-30
	 * <p>DESCRIPTION:</p>
	 * <p>IMPORTANT:</p>
	 * <p>EXAMPLE:</p>
	 */
	public int insert(SaleImageDto saleImageDto) {
		return sqlSessionSeller.insert("com.plutozone.seller.mybatis.sale.Sale.insertImage", saleImageDto);
	}
	
	/**
	 * @return List<SaleDto>
	 * 
	 * @since 2024-08-18
	 * <p>DESCRIPTION:</p>
	 * <p>IMPORTANT:</p>
	 * <p>EXAMPLE:</p>
	 */
	public List<SaleDto> listPrd(SaleDto saleDto) {
		return sqlSessionSeller.selectList("com.plutozone.seller.mybatis.sale.Sale.listPrd", saleDto);
	}
	
	/**
	 * @return int
	 * 
	 * @since 2024-08-18
	 * <p>DESCRIPTION:</p>
	 * <p>IMPORTANT:</p>
	 * <p>EXAMPLE:</p>
	 */
	public int update(SaleDto saleDto) {
		return sqlSessionSeller.update("com.plutozone.seller.mybatis.sale.Sale.update", saleDto);
	}
	
	/**
	 * @return int
	 * 
	 * @since 2024-07-10
	 * <p>DESCRIPTION:</p>
	 * <p>IMPORTANT:</p>
	 * <p>EXAMPLE:</p>
	 */
	public int insert(SaleDto saleDto) {
		return sqlSessionSeller.insert("com.plutozone.seller.mybatis.sale.Sale.insert", saleDto);
	}
	
	/**
	 * @return SaleDto
	 * 
	 * @since 2024-08-08
	 * <p>DESCRIPTION:</p>
	 * <p>IMPORTANT:</p>
	 * <p>EXAMPLE:</p>
	 */
	public SaleDto select(SaleDto saleDto) {
		return sqlSessionSeller.selectOne("com.plutozone.seller.mybatis.sale.Sale.select", saleDto);
	}
	
	public int count(PagingDto pagingDto) {
		return sqlSessionSeller.selectOne("com.plutozone.seller.mybatis.sale.Sale.count", pagingDto);
	}
	
	public int sequence() {
		return sqlSessionSeller.selectOne("com.plutozone.seller.mybatis.sale.Sale.sequence");
	}
	
	/**
	 * @return List<SaleDto>
	 * 
	 * @since 2024-08-08
	 * <p>DESCRIPTION:</p>
	 * <p>IMPORTANT:</p>
	 * <p>EXAMPLE:</p>
	 */
	public List<SaleDto> list(PagingDto pagingDto) {
		return sqlSessionSeller.selectList("com.plutozone.seller.mybatis.sale.Sale.list", pagingDto);
	}
}