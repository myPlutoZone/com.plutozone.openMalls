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
 *				: [20240808132000][pluto#plutozone.com][CREATE: Initial Release]
 */
%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ page info="/WEB-INF/view/seller/sale/view.jsp" %>
<%@ taglib prefix="fmt"					uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/include/seller/header.jsp" %>
	<link rel="stylesheet" type="text/css" title="common stylesheet" href="/css/layoutSubmain.css" />
	<link rel="stylesheet" type="text/css" title="common stylesheet" href="/css/table.css" />
	<style></style>
	<script>
		/* [2024-10-30[pluto#plutozone.com][DELETE: 수정 페이지로 통합]
		function stop(value) {
			var frmMain = document.getElementById("frmMain");
			
			frmMain.action="/seller/sale/stop.web";
			frmMain.submit();
		}
		
		function re(value) {
			var frmMain = document.getElementById("frmMain");
			
			frmMain.action="/seller/sale/re.web";
			frmMain.submit();
		}
		
		function out(value) {
			var frmMain = document.getElementById("frmMain");
			
			frmMain.action="/seller/sale/out.web";
			frmMain.submit();
		}
		*/
		
		function modifyForm(value) {
			var frmMain = document.getElementById("frmMain");
			
			frmMain.action="/seller/sale/modifyForm.web";
			frmMain.submit();
		}
	</script>
</head>
<body>
<form id="frmMain" method="POST">
<input type="hidden" id="seq_sle"		name="seq_sle"			value="${saleDto.seq_sle}"/>
<input type="hidden" id="cd_state_sale"	name="cd_state_sale"	value="${saleDto.cd_state_sale}"/>
<div class="container">
	<header>
		<%@ include file="/include/seller/top.jsp" %>
	</header>
	<nav>
		<%@ include file="/include/seller/gnb.jsp" %>
	</nav>
	<section class="content">
		<article class="txtCenter">
			<table class="headLeft_01" style="width: 900px; margin-left: auto; margin-right: auto">
				<tr>
					<th style="width: 150px;" >판매명</th>
					<td>
						${saleDto.sle_nm}
					</td>
				</tr>
				<tr>
					<th>카테고리</th>
					<td>
						<select id="cd_where_ctg" name="cd_where_ctg" disabled>
							<option value="1"<c:if test="${saleDto.cd_where_ctg == '1'}"> selected</c:if>>도서</option>
							<option value="2"<c:if test="${saleDto.cd_where_ctg == '2'}"> selected</c:if>>가전 제품</option>
							<option value="3"<c:if test="${saleDto.cd_where_ctg == '3'}"> selected</c:if>>컴퓨터 용품</option>
						</select>
					</td>
				</tr>
				<tr>
					<th>설명</th>
					<td class="content">
						${saleDto.desces}
					</td>
				</tr>
				<tr>
					<th>판매 가격</th>
					<td>
						<fmt:formatNumber value="${saleDto.price_sale}" type="number" />원
					</td>
				</tr>
				<tr>
					<th>판매 상태</th>
					<td>
						<select id="cd_state_sale" name="cd_state_sale" disabled>
							<option value="1"<c:if test="${saleDto.cd_state_sale == '1'}"> selected</c:if>>판매중</option>
							<option value="2"<c:if test="${saleDto.cd_state_sale == '2'}"> selected</c:if>>판매 중지</option>
							<option value="3"<c:if test="${saleDto.cd_state_sale == '3'}"> selected</c:if>>반려</option>
							<option value="9"<c:if test="${saleDto.cd_state_sale == '9'}"> selected</c:if>>품절</option>
						</select>
					</td>
				</tr>
				<tr>
					<th>이미지(대표)</th>
					<td>
						<img src="/image/sale/${saleDto.img}" height="200"/>
					</td>
				</tr>
				<tr>
					<th>판매 시작일</th>
					<td>
						<input type="text" id="dt_sale_start" name="dt_sale_start" value="${saleDto.dt_sale_start}" readonly/>
					</td>
				</tr>
				<tr>
					<th>판매 종료일</th>
					<td>
						<input type="text" id="dt_sale_end" name="dt_sale_end" value="${saleDto.dt_sale_end}" readonly/>
					</td>
				</tr>
				<tr>
					<td colspan="2" style="text-align:center;padding-top: 10px;padding-bottom: 10px">
						<input type="button" value="수정" style="width:100px" onclick="javascript:modifyForm();" />
						 <input type="button" value="목록" style="width:100px" onclick="javascript:location.href='/seller/sale/list.web';"/>
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
