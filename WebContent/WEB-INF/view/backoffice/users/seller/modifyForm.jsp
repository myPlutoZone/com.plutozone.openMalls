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
 *				: [20240702172500][pluto#plutozone.com][CREATE: Initial Release]
 */
%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ page info="/WEB-INF/view/backoffice/users/seller/modifyForm.jsp" %>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/include/console/header.jsp" %>
	<link rel="stylesheet" type="text/css" title="common stylesheet" href="/css/layoutSubmain.css" />
	<link rel="stylesheet" type="text/css" title="common stylesheet" href="/css/table.css" />
	<style></style>
	<script type="text/javascript" src="/js/package/tinymce/tinymce.min.js"></script>
	<script type="text/javascript" src="/js/package/tinymce.js"></script>
	<script>
		function modifyProc() {
			var frmMain = document.getElementById("frmMain");
			
			frmMain.action="/console/users/seller/modifyProc.web";
			frmMain.submit();
		}
	</script>
</head>
<body>
<form id="frmMain" method="POST">
<input type="hidden" id="seq_sll" name="seq_sll" value="${sellerDto.seq_sll}" />
<div class="container">
	<header>
		<%@ include file="/include/console/top.jsp" %>
	</header>
	<nav>
		<%@ include file="/include/console/gnb.jsp" %>
	</nav>
	<section class="content">
		<nav>
			<%@ include file="/include/console/lnbUsers.jsp" %>
		</nav>
		<article class="txtCenter">
			<table class="headLeft_01" style="width: 900px; margin-left: auto; margin-right: auto">
				<tr>
					<th style="width: 200px;">아이디 / 상태(*)</th>
					<td>
						${sellerDto.id} / 
						<select name="cd_state" style="background:#F0F0F0">
							<option value=""<c:if test="${sellerDto.cd_state == null}"> selected</c:if>>전체</option>
							<option value="0"<c:if test="${sellerDto.cd_state == 0}"> selected</c:if>>대기</option>
							<option value="1"<c:if test="${sellerDto.cd_state == 1}"> selected</c:if>>사용중</option>
							<option value="2"<c:if test="${sellerDto.cd_state == 2}"> selected</c:if>>반려</option>
							<option value="8"<c:if test="${sellerDto.cd_state == 8}"> selected</c:if>>자퇴</option>
							<option value="9"<c:if test="${sellerDto.cd_state == 9}"> selected</c:if>>강퇴</option>
						</select>
					</td>
				</tr>
				<tr>
					<th>성명</th>
					<td>
						${sellerDto.sll_nm}
					</td>
				</tr>
				<tr>
					<th>이메일</th>
					<td>
						${sellerDto.email}
					</td>
				</tr>
				<tr>
					<th>연락처</th>
					<td>
						${sellerDto.phone}
					</td>
				</tr>
				<tr>
					<th>회사명</th>
					<td>
						${sellerDto.corp_nm}
					</td>
				</tr>
				<tr>
					<th>사업자등록번호 / 개업년월일</th>
					<td>
						${sellerDto.corp_num} / ${sellerDto.corp_birthday} 
						<c:if test="${sellerDto.cd_state == 0}">
							<c:if test="${isOK == true}"><span style="color:blue">[정상 사업자]</span></c:if>
							<c:if test="${isOK == false}"><span style="color:red">[비정상 사업자 또는 휴면 또는 페업 등]</span></c:if>
						</c:if>
					</td>
				</tr>
				<tr>
					<th>대표자명</th>
					<td>
						${sellerDto.corp_ceo}
					</td>
				</tr>
				<tr>
					<th>주소</th>
					<td>
						${sellerDto.post} ${sellerDto.addr1} ${sellerDto.addr2}
				</tr>
				<tr>
					<th>회사소개</th>
					<td>
						${sellerDto.intro}
					</td>
				</tr>
				<tr>
					<th>등록 일시</th>
					<td>
						${sellerDto.dt_reg}
					</td>
				</tr>
				<tr>
					<td colspan="2" style="text-align:center;padding-top: 10px;padding-bottom: 10px">
						<input type="button" value="적용" style="width:100px" onclick="javascript:modifyProc();" />
						 <input type="button" value="취소" style="width:100px" onclick="location.href='/console/users/seller/list.web';"/>
					</td>
				</tr>
			</table>
		</article>
		<aside></aside>
	</section>
	<footer>
		<%@ include file="/include/console/footer.jsp" %>
	</footer>
</div>
</form>
</body>
</html>