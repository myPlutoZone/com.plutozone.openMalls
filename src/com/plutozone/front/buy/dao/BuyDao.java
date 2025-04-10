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
 * File			: BuyDao.java
 * Notes		:
 * History		: [NO][Programmer][Description]
 *				: [20240801163155][pluto#plutozone.com][CREATE: Initial Release]
 */
package com.plutozone.front.buy.dao;

import java.util.List;

import org.springframework.stereotype.Repository;


import com.plutozone.front.buy.dto.BuyDetailDto;
import com.plutozone.front.buy.dto.BuyDto;
import com.plutozone.front.buy.dto.BuyMasterDto;
import com.plutozone.front.common.dao.BaseDao;
import com.plutozone.front.common.dto.PagingDto;

/**
 * @version 1.0.0
 * @author pluto#plutozone.com
 * 
 * @since 2024-08-08
 * <p>DESCRIPTION:</p>
 * <p>IMPORTANT:</p>
 */
@Repository("com.plutozone.front.buy.dao.BuyDao")
public class BuyDao extends BaseDao {
	
	public int update(BuyMasterDto buyMasterDto) {
		return sqlSessionFront.update("com.plutozone.front.mybatis.buy.BuyMaster.update", buyMasterDto);
	}
	
	public int insertMaster(BuyMasterDto buyMasterDto) {
		return sqlSessionFront.insert("com.plutozone.front.mybatis.buy.BuyMaster.insert", buyMasterDto);
	}
	
	public int insertDetail(BuyDetailDto buyDetailDto) {
		return sqlSessionFront.insert("com.plutozone.front.mybatis.buy.BuyDetail.insert", buyDetailDto);
	}
	
	public int count(PagingDto pagingDto) {
		return sqlSessionFront.selectOne("com.plutozone.front.mybatis.buy.BuyMaster.count", pagingDto);
	}
	
	public int sequenceDetail() {
		return sqlSessionFront.selectOne("com.plutozone.front.mybatis.buy.BuyDetail.sequence");
	}
	
	public int sequenceMaster() {
		return sqlSessionFront.selectOne("com.plutozone.front.mybatis.buy.BuyMaster.sequence");
	}
	
	public List<BuyMasterDto> list(PagingDto pagingDto) {
		return sqlSessionFront.selectList("com.plutozone.front.mybatis.buy.BuyMaster.list", pagingDto);
	}
	
	public List<BuyDetailDto> select(BuyMasterDto buyMasterDto) {
		return sqlSessionFront.selectList("com.plutozone.front.mybatis.buy.BuyDetail.select", buyMasterDto);
	}
	
	/**
	 * @return String
	 * 
	 * @since 2024-07-04
	 * <p>DESCRIPTION: 마이페이지 구매이력 총 구매 금액</p>
	 * <p>IMPORTANT:</p>
	 * <p>EXAMPLE:</p>
	 */
	public String selectTotal(BuyDto buyDto) {
		return sqlSessionFront.selectOne("com.plutozone.front.mybatis.buy.Buy.selectTotal", buyDto);
	}
	
	/**
	 * @return List<BuyDto>
	 * 
	 * @since 2024-08-01
	 * <p>DESCRIPTION: 마이페이지 구매이력 목록</p>
	 * <p>IMPORTANT:</p>
	 * <p>EXAMPLE:</p>
	 */
	public List<BuyDto> list(BuyDto buyDto) {
		return sqlSessionFront.selectList("com.plutozone.front.mybatis.buy.Buy.list", buyDto);
	}
}
