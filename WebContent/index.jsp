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
 *				: [20240626130000][pluto#plutozone.com][CREATE: Initial Release]
 */
%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ page info="/index.jsp" %>
<%@ taglib prefix="c"	uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta http-equiv="content-style-type" content="text/css" />
	<meta http-equiv="content-script-type" content="text/javascript" />	
	<meta http-equiv="cache-control" content="no-cache" />
	<meta http-equiv="pragma" content="no-cache" />
	<meta http-equiv="expires" content="0" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>::: com.plutozone.openMalls :::</title>
	<link rel="shortcut icon" href="/image/favicon.ico" />
	<link rel="apple-touch-icon" href="/image/apple-touch-icon.png" />
	<link rel="stylesheet" type="text/css" title="common stylesheet" href="/css/common.css" />
	<link rel="stylesheet" type="text/css" title="common stylesheet" href="/css/layoutMain.css" />
	<script type="text/javascript" src="/js/common.js"></script>
	<script type="text/javascript" src="/js/util.js"></script>
	<script type="text/javascript" src="/js/jquery-3.7.1.min.js"></script>
	<style></style>
	<script>
		window.onload = function () {
			// alert(getNow());
		}
		
		function setCookie (cName, cValue, cDay) {
			
			var expire = new Date();
			
			expire.setDate(expire.getDate() + cDay);
			cookies = cName + "=" + escape(cValue) + "; path=/";
			
			if (typeof cDay != "undefined") cookies += "; expires=" + expire.toGMTString() + ";";
			//cookies = "test=cookie test, 쿠키 테스트; path=/; expires=20240706T05..."
			document.cookie = cookies;
		}
		
		function getCookie(cName) {
			
			cName = cName + '=';
			
			var cookieData	= document.cookie;
			//alert(cookieData);
			var start		= cookieData.indexOf(cName);
			//alert(start);
			var cValue		= '';
			
			if (start != -1) {
				start += cName.length;
				var end = cookieData.indexOf(';', start);
				if (end == -1) end = cookieData.length;
				cValue = cookieData.substring(start, end);
			}
			return unescape(cValue);
		}
	</script>
</head>
<body>
<form id="frmMain" method="POST">
<div class="container">
	<header>
		<a href="/"><img src="/image/logo.jpg" alt="로고" width="200px" /></a>
	</header>
	<nav>
	</nav>
	<section class="content">
		<nav></nav>
		<article class="txtCenter">
			<!--
			<input type="button" value="쿠키 생성" onclick="setCookie('test', 'cookie test, 쿠키 테스트', 1)"><br>
			<input type="button" value="쿠키 보기" onclick="alert(getCookie('test'))"><br>
			<input type="button" value="쿠키 삭제" onclick="setCookie('test', '', -1)"><br>
			-->
			<ul>
				<li><a href="/front/">[프론트]</a></li>
				<li><a href="/seller/">[판매자]</a></li>
				<li><a href="/console/">[백엔드]</a></li>
				<!--
				<li><a href="/backoffice/center/board/list.web?cd_bbs_type=1">[고객센터 at 백엔드]</a></li>
				-->
			</ul>
		</article>
		<aside></aside>
	</section>
	<footer>
		Copyright &copy; 2024 by <a href="./mapKaKao.html">PLUTOZONE.COM</a> All Rights Reserved
	</footer>
</div>
</form>
</body>
</html>