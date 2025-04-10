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
 * File			: SellerDao.java
 * Notes		:
 * History		: [NO][Programmer][Description]
 *				: [20240828144341][pluto#plutozone.com][CREATE: Initial Release]
 */
package com.plutozone.backoffice.users.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.plutozone.backoffice.center.dto.BoardDto;
import com.plutozone.backoffice.common.dao.BaseDao;
import com.plutozone.backoffice.common.dto.PagingDto;
import com.plutozone.backoffice.users.dto.SellerDto;

/**
 * @version 1.0.0
 * @author pluto#plutozone.com
 * 
 * @since 2024-08-28
 * <p>DESCRIPTION:</p>
 * <p>IMPORTANT:</p>
 */
@Repository("com.plutozone.backoffice.users.dao.SellerDao")
public class SellerDao extends BaseDao {
	
	public int update(SellerDto sellerDto) {
		return sqlSessionBackoffice.update("com.plutozone.backoffice.mybatis.users.Seller.update", sellerDto); 
	}
	
	public SellerDto select(SellerDto sellerDto) {
		return sqlSessionBackoffice.selectOne("com.plutozone.backoffice.mybatis.users.Seller.select", sellerDto); 
	}
	
	public int count(PagingDto pagingDto) {
		return sqlSessionBackoffice.selectOne("com.plutozone.backoffice.mybatis.users.Seller.count", pagingDto);
	}
	
	public List<BoardDto> list(PagingDto pagingDto) {
		return sqlSessionBackoffice.selectList("com.plutozone.backoffice.mybatis.users.Seller.list", pagingDto);
	}
}
