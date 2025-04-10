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
 * File			: SellersSrvc.java
 * Notes		:
 * History		: [NO][Programmer][Description]
 *				: [20240731123657][pluto#plutozone.com][CREATE: Initial Release]
 */
package com.plutozone.seller.sellers.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.plutozone.seller.sellers.dao.SellerDao;
import com.plutozone.seller.sellers.dto.SellerDto;

/**
 * @version 1.0.0
 * @author pluto#plutozone.com
 * 
 * @since 2024-07-31
 * <p>DESCRIPTION:</p>
 * <p>IMPORTANT:</p>
 */
@Service("com.plutozone.seller.sellers.service.SellerSrvc")
public class SellerSrvc {
	
	@Inject
	SellerDao sellerdao;
	
	@Transactional("txSeller")
	public boolean insert(SellerDto sellerDto) {

		// 신규 판매자 번호(seq_sll)
		sellerDto.setSeq_sll(sellerdao.sequence());
		sellerDto.setRegister(sellerDto.getSeq_sll());
		
		if (sellerdao.insert(sellerDto) == 1) return true;
		else {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return false;
		}
	}
}