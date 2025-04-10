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
 *				: [20240801165000][pluto#plutozone.com][CREATE: Initial Release]
 */
%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ page info="/WEB-INF/view/front/myPage/modifyForm.jsp" %>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/include/front/header.jsp" %>
	<link rel="stylesheet" type="text/css" title="common stylesheet" href="/css/layoutSubmain.css" />
	<link rel="stylesheet" type="text/css" title="common stylesheet" href="/css/table.css" />
	<script type="text/javascript" src="/js/jquery-3.7.1.min.js"></script>
	<style></style>
	<script type="text/javascript">
		function checkModify() {
			
			var passwd = $("#passwd").val();	// 현재 비밀번호
			
			var confirmPasswd	= $("#confirmPasswd").val();
			var confirmPasswd_	= $("#confirmPasswd_").val();
			
			// 현재 비밀번호가 비어 있는지 확인
			if (!passwd) {
				alert("현재 비밀번호를 입력해주세요!");
				return;
			}
			
			// 신규 비밀번호가 비어 있는지 확인
			if (!confirmPasswd) {
				alert("신규 비밀번호를 입력해주세요!");
				return;
			}
			
			// 비밀번호 확인이 비어 있는지 확인
			if (!confirmPasswd_) {
				alert("신규 비밀번호 확인을 입력해주세요!");
				return;
			}
			
			// 신규 비밀번호와 비밀번호 확인이 일치하는지 확인
			if (confirmPasswd != confirmPasswd_) {
				alert("신규 비밀번호와 비밀번호 확인이 일치하지 않습니다!");
				return;
			}
			
			// 비밀번호가 규칙에 맞는지 확인 (예: 최소 8자, 대문자 포함)
			if (confirmPasswd.length < 8) {
				alert("신규 비밀번호는 최소 8자 이상이어야 합니다!");
				return;
			}
			
			// 폼을 제출
			$("#frmMain").submit();
		}
	</script>
</head>
<body>
<form id="frmMain" method="POST"  action="/front/member/passwd/modifyProc.web">
<div class="container">
	<header>
		<%@ include file="/include/front/top.jsp" %>
	</header>
	<nav>
		<%@ include file="/include/front/gnb.jsp" %>
	</nav>
	<section class="content">
		<nav>
			<%@ include file="/include/front/lnbMyPage.jsp" %>
		</nav>
		<article class="txtCenter">
			(*) 표시는 필수 입력 사항입니다.
			<table class="headLeft_01" style="width: 500px; margin-left: auto; margin-right: auto">
				<tr>
					<th>현재 비밀번호(*)</th>
					<td><input type="password" id="passwd" name="passwd" value="12345678!a" /></td>
				</tr>
				<tr>
					<th>신규 비밀번호(*)</th>
					<td><input type="password" id="confirmPasswd" name="confirmPasswd" value="123456789!a" /></td>
				</tr>
				<tr>
					<th>신규 비밀번호 확인(*)</th>
					<td><input type="password" id="confirmPasswd_" name="confirmPasswd_" value="123456789!a" /></td>
				</tr>
				<tr>
					<td colspan="2" style="text-align:center;padding-top: 10px;padding-bottom: 10px">
						<input type="reset" value="다시 쓰기" style="width:100px"/>
						 <input type="button" value="수정 하기" style="width:100px" onClick="checkModify();"/>
					</td>
				</tr>
			</table>
		</article>
		<aside></aside>
	</section>
	<footer>
		<%@ include file="/include/front/footer.jsp" %>
	</footer>
</div>
</form>
</body>
</html>