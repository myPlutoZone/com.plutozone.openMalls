<%
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
 * File			:
 * Notes		:
 * History		: [NO][Programmer][Description]
 *				: [20240730145700][pluto#plutozone.com][CREATE: Initial Release]
 */
%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ page info="/WEB-INF/view/seller/sale/index.jsp" %>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/include/seller/header.jsp" %>
	<link rel="stylesheet" type="text/css" title="common stylesheet" href="/css/layoutSubmain.css" />
	<style></style>
	<script>
	</script>
</head>
<body>
<form id="frmMain" method="POST">
<div class="container">
	<header>
		<%@ include file="/include/seller/top.jsp" %>
	</header>
	<nav>
		<%@ include file="/include/seller/gnb.jsp" %>
	</nav>
	<section class="content">
		<nav>
			<ul>
				<!--
				<li><a href="#">- 개인정보 변경</a></li>
				<li><a href="#">- 비밀번호 변경</a></li>
				-->
			</ul>
		</nav>
		<article class="txtCenter">
			여기는 "판매" 페이지입니다.
		</article>
		<aside></aside>
	</section>
	<footer>
		<%@ include file="/include/seller/footer.jsp" %>
	</footer>
</div>
</form>
</body>
</html>