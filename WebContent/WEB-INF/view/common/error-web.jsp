<%
/**
 * YOU ARE STRICTLY PROHIBITED TO COPY, DISCLOSE, DISTRIBUTE, MODIFY OR USE THIS PROGRAM
 * IN PART OR AS A WHOLE WITHOUT THE PRIOR WRITTEN CONSENT OF PLUTOZONE.COM.
 * PLUTOZONE.COM OWNS THE INTELLECTUAL PROPERTY RIGHTS IN AND TO THIS PROGRAM.
 * COPYRIGHT (C) 2016 PLUTOZONE.COM ALL RIGHTS RESERVED.
 *
 * 하기 프로그램에 대한 저작권을 포함한 지적재산권은 plutozone.com에 있으며,
 * plutozone.com이 명시적으로 허용하지 않는 사용, 복사, 변경 및 제 3자에 의한 공개, 배포는 엄격히 금지되며
 * plutozone.com의 지적재산권 침해에 해당된다.
 * Copyright (C) 2016 plutozone.com All Rights Reserved.
 *
 *
 * Program		: com.plutozone.common
 * Description	:
 * Environment	: JRE 1.7 or more
 * File			: /WEB-INF/view/common/system-error-web.jsp
 * Notes		:
 * History		: [NO][Programmer][Description]
 *				: [20160104160200][pluto#plutozone.com][CREATE: Initial Release]
 */
%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ page info="/WEB-INF/view/common/error-web.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/include/common/header.jsp" %>
</head>
<body>
<div class="container">
	<header></header>
	<section class="content">
		<article>
			<div id="box_error">
				<p><img src="/image/icon_error.gif" /></p>
				<c:choose>
				<c:when test="${systemErrorDto.code == 'S404' or systemErrorDto.code == '404'}">
					<p>FILE NOT FOUND</p>
				</c:when>
				<c:otherwise>
					<p>SYSTEM ERROR</p>
				</c:otherwise>
				</c:choose>
				<!--
				<p>CODE: [${systemErrorDto.code}]</p>
				<p>CODE_DESC: [${systemErrorDto.code_desc}]</p>
				-->
				<p><a href="/" target="_top">[HOME]</a>&nbsp;<a href="javascript: history.back();">[BACK]</a></p>
			</div>
		</article>
	</section>
	<footer>
		<%@ include file="/include/common/footer.jsp" %>
	</footer>
</div>
</body>
</html>