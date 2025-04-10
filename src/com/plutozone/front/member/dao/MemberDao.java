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
 * File			: MemberDao.java
 * Notes		:
 * History		: [NO][Programmer][Description]
 *				: [20240625100333][pluto#plutozone.com][CREATE: Initial Release]
 */
package com.plutozone.front.member.dao;

import org.springframework.stereotype.Repository;

import com.plutozone.front.common.dao.BaseDao;
import com.plutozone.front.member.dto.MemberDto;

/**
 * @version 1.0.0
 * @author one4027one#plutozone.com
 * 
 * @since 2024-08-01
 * <p>DESCRIPTION:</p>
 * <p>IMPORTANT:</p>
 */
@Repository("com.plutozone.front.member.dao.MemberDao")
public class MemberDao extends BaseDao {
	
	public int updateState(MemberDto memberDto) {
		return sqlSessionFront.update("com.plutozone.front.mybatis.member.Member.updateState", memberDto);
	}
	
	public int selectDuplicate(MemberDto memberDto) {
		return sqlSessionFront.selectOne("com.plutozone.front.mybatis.member.Member.selectDuplicate", memberDto);
	}
	
	public int sequence() {
		return sqlSessionFront.selectOne("com.plutozone.front.mybatis.member.Member.sequence");
	}
	
	public int insertMaster(MemberDto memberDto) {
		return sqlSessionFront.insert("com.plutozone.front.mybatis.member.Member.insertMaster", memberDto);
	}
	
	public int insertDetail(MemberDto memberDto) {
		return sqlSessionFront.insert("com.plutozone.front.mybatis.member.Member.insertDetail", memberDto);
	}
	
	public int updateMaster(MemberDto memberDto) {
		return sqlSessionFront.update("com.plutozone.front.mybatis.member.Member.updateMaster", memberDto);
	}
	
	public int updateDetail(MemberDto memberDto) {
		return sqlSessionFront.update("com.plutozone.front.mybatis.member.Member.updateDetail", memberDto);
	}
		
	public MemberDto select(MemberDto memberDto) {
		return sqlSessionFront.selectOne("com.plutozone.front.mybatis.member.Member.select", memberDto);
	}
	
	public MemberDto selectPasswd(MemberDto memberDto) {
		return sqlSessionFront.selectOne("com.plutozone.front.mybatis.member.Member.selectPasswd", memberDto);
	}
	
	public int updatePasswd(MemberDto memberDto) {
		return sqlSessionFront.update("com.plutozone.front.mybatis.member.Member.updatePasswd", memberDto);
	}
}