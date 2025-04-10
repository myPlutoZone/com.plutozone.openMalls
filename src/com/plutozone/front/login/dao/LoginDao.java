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
 * File			: LoginDao.java
 * Notes		:
 * History		: [NO][Programmer][Description]
 *				: [20240624162713][pluto#plutozone.com][CREATE: Initial Release]
 */
package com.plutozone.front.login.dao;

import org.springframework.stereotype.Repository;

import com.plutozone.front.common.dao.BaseDao;
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
@Repository("com.plutozone.front.login.dao.LoginDao")
public class LoginDao extends BaseDao {
	
	public int updateLast(MemberDto memberDto) {
		return sqlSessionFront.update("com.plutozone.front.mybatis.login.Login.updateLast", memberDto);
	}
	
	public MemberDto exist(LoginDto loginDto) {
		
		return sqlSessionFront.selectOne("com.plutozone.front.mybatis.login.Login.exist", loginDto);
		
		/*
		MemberDto memberDto = null;
		
		if (loginDto.getEmail().equals("SuuQ/+g30irB+C2JggShEvTTClVpsI+SXKFREQWuDnY=")) {
			memberDto = new MemberDto();
			memberDto.setEmail("SuuQ/+g30irB+C2JggShEvTTClVpsI+SXKFREQWuDnY=");
			memberDto.setPasswd("EF797C8118F02DFB649607DD5D3F8C7623048C9C063D532CC95C5ED7A898A64F");
			memberDto.setName("zUo2b47FiVVaEROIPSLMcg==");
		}
		else if (loginDto.getEmail().equals("ID#hotmal.com")) {
			memberDto = new MemberDto();
			memberDto.setEmail("ID#hotmal.com");
			memberDto.setPasswd("5678");
			memberDto.setName("홍길동");
		}
		else if (loginDto.getEmail().equals("ID#naver.com")) {
			memberDto = new MemberDto();
			memberDto.setEmail("ID#naver.com");
			memberDto.setPasswd("12345678");
			memberDto.setName("홍길동");
		}
		
		return memberDto;
		*/
	}
}
