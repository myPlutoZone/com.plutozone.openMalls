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
 * File			: ProductSrvc.java
 * Notes		:
 * History		: [NO][Programmer][Description]
 *				: [20240808130207][pluto#plutozone.com][CREATE: Initial Release]
 */
package com.plutozone.seller.product.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.plutozone.seller.common.dto.PagingDto;
import com.plutozone.seller.common.dto.PagingListDto;
import com.plutozone.seller.product.dao.ProductDao;
import com.plutozone.seller.product.dto.ProductDto;

/**
 * @version 1.0.0
 * @author pluto#plutozone.com
 * 
 * @since 2024-08-08
 * <p>DESCRIPTION:</p>
 * <p>IMPORTANT:</p>
 */
@Service("com.plutozone.seller.product.service.ProductSrvc")
public class ProductSrvc {
	
	@Inject
	ProductDao productDao;
	
	@Transactional("txSeller")
	public boolean update(ProductDto productDto) {
		
		int result = productDao.update(productDto);
		
		if (result == 1) return true;
		else {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return false;
		}
	}
	
	@Transactional("txSeller")
	public boolean insert(ProductDto productDto) {
		
		productDto.setSeq_prd(productDao.sequence());
		
		int result = productDao.insert(productDto);
		
		if (result == 1) return true;
		else {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return false;
		}
	}
	
	public PagingListDto list(PagingDto pagingDto) {
		
		PagingListDto pagingListDto = new PagingListDto();
		
		int totalLine = productDao.count(pagingDto);
		int totalPage = (int)Math.ceil((double)totalLine / (double)pagingDto.getLinePerPage());
		pagingDto.setTotalLine(totalLine);
		pagingDto.setTotalPage(totalPage);
		if (totalPage == 0) pagingDto.setCurrentPage(1);
		
		pagingListDto.setPaging(pagingDto);
		pagingListDto.setList(productDao.list(pagingDto));
		
		return pagingListDto;
	}
	
	/**
	 * @param productDto [판매자 상품 빈]
	 * @return ProductDto
	 * 
	 * @since 2024-08-08
	 * <p>DESCRIPTION: 판매자 상품현황 보기</p>
	 * <p>IMPORTANT:</p>
	 * <p>EXAMPLE:</p>
	 */
	public ProductDto select(ProductDto productDto) {
		return productDao.select(productDto);
	}
	
	/**
	 * @param productDto [게시판 빈]
	 * @return boolean
	 * 
	 * @since 2024-08-08
	 * <p>DESCRIPTION: 판매자 상품 삭제</p>
	 * <p>IMPORTANT:</p>
	 * <p>EXAMPLE:</p>
	 */
	@Transactional("txSeller")
	public boolean deleteFlag(ProductDto productDto) {
		
		int result = productDao.deleteFlag(productDto);
		
		if (result == 1) return true;
		else {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return false;
		}
	}
}