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
<%@ page info="/WEB-INF/view/seller/product/writeForm.jsp" %>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/include/seller/header.jsp" %>
	<link rel="stylesheet" type="text/css" title="common stylesheet" href="/css/layoutSubmain.css" />
	<link rel="stylesheet" type="text/css" title="common stylesheet" href="/css/table.css" />
	<style></style>
	<script type="text/javascript" src="/js/package/tinymce/tinymce.min.js"></script>
	<script type="text/javascript" src="/js/package/tinymce.js"></script>
	<script>
		/*
		window.onload = function () {
			// HTML Editor
			tinymce.init({selector:'textarea'});
		}
		*/
		function writeProc() {
			var frmMain = document.getElementById("frmMain");
			
			if (document.getElementById("prd_nm").value == ""
					|| document.getElementById("cd_ctg").value == ""
					|| document.getElementById("price_cost").value == ""
					|| document.getElementById("count_stock").value == "") {
				alert("필수 항목을 입력하세요!");
				return;
			}
			if(document.getElementById("price_cost").value <= 0) {
				alert("원가를 0원 이상 입력하세요!")
				return;
			}
			if(document.getElementById("count_stock").value < 1) {
				alert("재고 수량을 1개 이상 입력하세요!")
				return;
			}
			frmMain.action="/seller/product/writeProc.web";
			frmMain.submit();
		}
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
			<%@ include file="/include/seller/lnbProduct.jsp" %>
		</nav>
		<article class="txtCenter">
			<table class="headLeft_01" style="width: 900px; margin-left: auto; margin-right: auto">
				<tr>
					<th style="width: 150px;">상품명(*)</th>
					<td>
						<input type="text" id="prd_nm" name="prd_nm" style="width: 700px;" required />
					</td>
				</tr>
				<tr>
					<th>상품 상태(*)</th>
					<td>
						<select name="cd_state_prd" id="cd_state_prd">
							<option value="1">판매중</option>
							<option value="2">판매중지</option>
							<option value="9">재고소진</option>
						</select>
					</td>
				</tr>
				<tr>
					<th>카테고리(*)</th>
					<td>
						<select id="cd_ctg" name="cd_ctg" required>
							<option value="">선택</option>
							<option value="01">총류</option>
							<option value="02">철학</option>
							<option value="03">종교</option>
							<option value="04">사회</option>
							<option value="05">자연</option>
							<option value="06">기술</option>
							<option value="07">예술</option>
							<option value="08">언어</option>
							<option value="09">문학</option>
						</select>
					</td>
				</tr>
				<tr>
					<th>상품 원가(*)</th>
					<td><input type="text" name="price_cost" id="price_cost" maxlength="11" style="text-align:right;" required /> 원</td>
				</tr>
				<tr>
					<th>재고 수량(*)</th>
					<td><input type="text" name="count_stock" id="count_stock" style="text-align:right;" required /> 개</td>
				</tr>
				<tr>
					<td colspan="2" style="text-align:center;padding-top: 10px;padding-bottom: 10px">
						<input type="button" value="등록" style="width:100px" onclick="javascript:writeProc();" />
						 <input type="button" value="목록" style="width:100px" onclick="javascript:location.href='/seller/product/list.web';" />
					</td>
				</tr>
			</table>
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