<%
/**
 * YOU ARE STRICTLY PROHIBITED TO COPY, DISCLOSE, DISTRIBUTE, MODIFY OR USE THIS PROGRAM
 * IN PART OR AS A WHOLE WITHOUT THE PRIOR WRITTEN CONSENT OF PLUTOZONE.COM.
 * PLUTOZONE.COM OWNS THE INTELLECTUAL PROPERTY RIGHTS IN AND TO THIS PROGRAM.
 * COPYRIGHT (C) 2018 PLUTOZONE.COM ALL RIGHTS RESERVED.
 *
 * 하기 프로그램에 대한 저작권을 포함한 지적재산권은 plutozone.com에 있으며,
 * plutozone.com이 명시적으로 허용하지 않는 사용, 복사, 변경 및 제 3자에 의한 공개, 배포는 엄격히 금지되며
 * plutozone.com의 지적재산권 침해에 해당된다.
 * Copyright (C) 2018 plutozone.com All Rights Reserved.
 *
 *
 * Program		: com.plutozone.common
 * Description	:
 * Environment	: JRE 1.7 or more
 * File			: /WEB-INF/view/common/system-error-api.jsp
 * Notes		:
 * History		: [NO][Programmer][Description]
 *				: [20181128220700][pluto#plutozone.com][CREATE: Initial Release]
 */
%>
<%@ page language="java" contentType="application/json; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ page info="/WEB-INF/view/common/error-api.jsp" %>{"code":"${systemErrorDto.code}","message":"${systemErrorDto.code_desc}"}