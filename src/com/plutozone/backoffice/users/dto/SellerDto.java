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
 * File			: SellerDto.java
 * Notes		:
 * History		: [NO][Programmer][Description]
 *				: [20240828144349][pluto#plutozone.com][CREATE: Initial Release]
 */
package com.plutozone.backoffice.users.dto;

/**
 * @version 1.0.0
 * @author pluto#plutozone.com
 * 
 * @since 2024-08-28
 * <p>DESCRIPTION:</p>
 * <p>IMPORTANT:</p>
 */
public class SellerDto {
	
	private int rnum				= 0;
	private int seq_sll				= 0;
	private String id				= "";
	private String passwd			= "";
	private Integer cd_state		= null;
	private String sll_nm			= "";
	private String email			= "";
	private String phone			= "";
	private String corp_num			= "";
	private String corp_nm			= "";
	private String corp_birthday	= "";
	private String corp_ceo			= "";
	private String post				= "";
	private String addr1			= "";
	private String addr2			= "";
	private String intro			= "";
	private String dt_reg 			= "";
	private int register			= 0;
	private String dt_upt			= "";
	private int updater				= 0;
	
	private String nm_state			= "";
	
	
	public String getNm_state() {
		return nm_state;
	}
	public void setNm_state(String nm_state) {
		this.nm_state = nm_state;
	}
	public int getRnum() {
		return rnum;
	}
	public void setRnum(int rnum) {
		this.rnum = rnum;
	}
	public int getSeq_sll() {
		return seq_sll;
	}
	public void setSeq_sll(int seq_sll) {
		this.seq_sll = seq_sll;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public Integer getCd_state() {
		return cd_state;
	}
	public void setCd_state(Integer cd_state) {
		this.cd_state = cd_state;
	}
	public String getSll_nm() {
		return sll_nm;
	}
	public void setSll_nm(String sll_nm) {
		this.sll_nm = sll_nm;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getCorp_num() {
		return corp_num;
	}
	public void setCorp_num(String corp_num) {
		this.corp_num = corp_num;
	}
	public String getCorp_nm() {
		return corp_nm;
	}
	public void setCorp_nm(String corp_nm) {
		this.corp_nm = corp_nm;
	}
	public String getCorp_birthday() {
		return corp_birthday;
	}
	public void setCorp_birthday(String corp_birthday) {
		this.corp_birthday = corp_birthday;
	}
	public String getCorp_ceo() {
		return corp_ceo;
	}
	public void setCorp_ceo(String corp_ceo) {
		this.corp_ceo = corp_ceo;
	}
	public String getPost() {
		return post;
	}
	public void setPost(String post) {
		this.post = post;
	}
	public String getAddr1() {
		return addr1;
	}
	public void setAddr1(String addr1) {
		this.addr1 = addr1;
	}
	public String getAddr2() {
		return addr2;
	}
	public void setAddr2(String addr2) {
		this.addr2 = addr2;
	}
	public String getIntro() {
		return intro;
	}
	public void setIntro(String intro) {
		this.intro = intro;
	}
	public String getDt_reg() {
		return dt_reg;
	}
	public void setDt_reg(String dt_reg) {
		this.dt_reg = dt_reg;
	}
	public int getRegister() {
		return register;
	}
	public void setRegister(int register) {
		this.register = register;
	}
	public String getDt_upt() {
		return dt_upt;
	}
	public void setDt_upt(String dt_upt) {
		this.dt_upt = dt_upt;
	}
	public int getUpdater() {
		return updater;
	}
	public void setUpdater(int updater) {
		this.updater = updater;
	}
}