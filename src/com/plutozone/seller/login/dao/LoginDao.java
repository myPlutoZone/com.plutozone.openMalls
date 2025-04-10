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
 *				: [20240731150247][pluto#plutozone.com][CREATE: Initial Release]
 */
package com.plutozone.seller.login.dao;

import org.springframework.stereotype.Repository;

import com.plutozone.seller.common.dao.BaseDao;
import com.plutozone.seller.login.dto.LoginDto;
import com.plutozone.seller.sellers.dto.SellerDto;

/**
 * @version 1.0.0
 * @author [kbs#>_<.co.kr]
 * 
 * @since 2024-07-31
 * <p>DESCRIPTION:</p>
 * <p>IMPORTANT:</p>
 */
@Repository("com.plutozone.seller.login.dao.LoginDao")
public class LoginDao extends BaseDao{
	
	public SellerDto exist(LoginDto loginDto) {
		return sqlSessionSeller.selectOne("com.plutozone.seller.mybatis.login.Login.exist", loginDto);
	}
}