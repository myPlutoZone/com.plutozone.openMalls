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
 * File			: ManagerSrvc.java
 * Notes		:
 * History		: [NO][Programmer][Description]
 *				: [20240726173519][pluto#plutozone.com][CREATE: Initial Release]
 */
package com.plutozone.backoffice.users.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.plutozone.backoffice.users.dao.ManagerDao;
import com.plutozone.backoffice.users.dto.ManagerDto;

/**
 * @version 1.0.0
 * @author pluto#plutozone.com
 * 
 * @since 2024-07-26
 * <p>DESCRIPTION:</p>
 * <p>IMPORTANT:</p>
 */
@Service("com.plutozone.backoffice.users.service.ManagerSrvc")
public class ManagerSrvc {
	
	@Inject
	ManagerDao managerDao;
	
	@Transactional("txBackoffice")
	public boolean insert(ManagerDto managerDto) {
		
		// 신규 관리자 번호(seq_mng)
		managerDto.setSeq_mng(managerDao.sequence());
		managerDto.setRegister(managerDto.getSeq_mng());
		
		if (managerDao.insert(managerDto) == 1) return true;
		else {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return false;
		}
	}
}