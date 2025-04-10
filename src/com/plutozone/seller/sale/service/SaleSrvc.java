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
 * File			: SaleService.java
 * Notes		:
 * History		: [NO][Programmer][Description]
 *				: [20240808104826][pluto#plutozone.com][CREATE: Initial Release]
 */
package com.plutozone.seller.sale.service;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.plutozone.common.dto.FileDto;
import com.plutozone.seller.common.dto.PagingDto;
import com.plutozone.seller.common.dto.PagingListDto;
import com.plutozone.seller.sale.dao.SaleDao;
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
@Service("com.plutozone.seller.sale.service.SaleSrvc")
public class SaleSrvc {
	
	/** Logger */
	private static Logger logger = LoggerFactory.getLogger(SaleSrvc.class);
	
	@Inject
	SaleDao saleDao;
	
	/**
	 * @return List<SaleDto>
	 * 
	 * @since 2024-08-08
	 * <p>DESCRIPTION:</p>
	 * <p>IMPORTANT:</p>
	 * <p>EXAMPLE:</p>
	 */
	public List<SaleDto> listPrd(SaleDto saleDto) {
		return saleDao.listPrd(saleDto);
	}
	
	/**
	 * @return boolean
	 * 
	 * @since 2024-08-08
	 * <p>DESCRIPTION:</p>
	 * <p>IMPORTANT:</p>
	 * <p>EXAMPLE:</p>
	 */
	@Transactional("txSeller")
	public boolean update(SaleDto saleDto) {
		
		int result = saleDao.update(saleDto);
		
		if (result == 1) return true;
		else {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return false;
		}
	}
	
	/**
	 * @param saleDto
	 * @param listFileDto
	 * 
	 * @return boolean
	 * 
	 * @since 2024-08-08
	 * <p>DESCRIPTION:</p>
	 * <p>IMPORTANT:</p>
	 * <p>EXAMPLE:</p>
	 */
	@Transactional("txSeller")
	public boolean insert(SaleDto saleDto, List<FileDto> listFileDto) {
		
		// 신규 글 번호(seq_bbs)
		saleDto.setSeq_sle(saleDao.sequence());
		// 판매하고자 하는 상품 일련번호
		saleDto.setSeq_prd(saleDto.getSeq_prd());
		
		int result = saleDao.insert(saleDto);
		
		// 판매 상품 이미지들(대표 포함)
		SaleImageDto saleImageDto = null;
		
		for (FileDto fileDto : listFileDto) {
			
			// 사이즈가 0 이상인 경우만
			if (fileDto.getFileSize() > 0) {
				
				saleImageDto = new SaleImageDto();
				saleImageDto.setSeq_sle(saleDto.getSeq_sle());
				saleImageDto.setImg(fileDto.getFileNameSave());
				saleImageDto.setRegister(saleDto.getRegister());
				
				result += saleDao.insert(saleImageDto);
			}
			else {
				logger.debug("fileDto.getFileNameOriginal() = "	+ fileDto.getFileNameOriginal());
				logger.debug("fileDto.getFileNameSave() = "		+ fileDto.getFileNameSave());
				logger.debug("fileDto.getFileSize() = "			+ fileDto.getFileSize());
			}
		}
		
		if (result == 1 + listFileDto.size()) return true;
		else {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return false;
		}
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
		return saleDao.select(saleDto);
	}
	
	/**
	 * @return List<SaleDto>
	 * 
	 * @since 2024-08-08
	 * <p>DESCRIPTION:</p>
	 * <p>IMPORTANT:</p>
	 * <p>EXAMPLE:</p>
	 */
	public PagingListDto list(PagingDto pagingDto) {
		
		PagingListDto pagingListDto = new PagingListDto();
		
		// 전체 라인(행) 수
		int totalLine = saleDao.count(pagingDto);
		// 전체 페이지 수 = 전체 라인(행) 수 / 페이징할 라인수
		int totalPage = (int) Math.ceil((double)totalLine / (double)pagingDto.getLinePerPage());
		pagingDto.setTotalLine(totalLine);
		pagingDto.setTotalPage(totalPage);
		if (totalPage == 0) pagingDto.setCurrentPage(1);
		
		pagingListDto.setPaging(pagingDto);
		pagingListDto.setList(saleDao.list(pagingDto));
		
		return pagingListDto;
	}
}