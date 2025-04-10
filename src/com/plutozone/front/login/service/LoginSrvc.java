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
 * File			: LoginSrvc.java
 * Notes		:
 * History		: [NO][Programmer][Description]
 *				: [20240624162606][pluto#plutozone.com][CREATE: Initial Release]
 */
package com.plutozone.front.login.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.plutozone.front.login.dao.LoginDao;
import com.plutozone.front.login.dto.LoginDto;
import com.plutozone.front.member.dto.MemberDto;

/**
 * @version 1.0.0
 * @author pluto#plutozone.com
 * 
 * @since 2024-06-24
 * <p>DESCRIPTION:</p>
 * <p>IMPORTANT:</p>
 */
@Service("com.plutozone.front.login.service.LoginSrvc")
public class LoginSrvc {
	
	@Inject
	LoginDao loginDao;
	
	@Transactional("txFront")
	public boolean updateLast(MemberDto memberDto) {
		
		int result = loginDao.updateLast(memberDto);
		
		if (result == 1) return true;
		else {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return false;
		}
	}
	
	/**
	 * @param loginDto [로그인 빈]
	 * @return MemberDto [회원 빈]
	 * 
	 * @since 2024-06-18
	 * <p>DESCRIPTION: 회원이 입력한 이메일(아이디)과 암호를 DAO에 전달하여 등록된 회원 정보(MemberDto)를 반환</p>
	 * <p>IMPORTANT:</p>
	 * <p>EXAMPLE:</p>
	 */
	public MemberDto exist(LoginDto loginDto) {
		return loginDao.exist(loginDto);
	}
}