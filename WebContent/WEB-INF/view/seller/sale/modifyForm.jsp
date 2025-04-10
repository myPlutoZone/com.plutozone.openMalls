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
 *				: [20240808145500][pluto#plutozone.com][CREATE: Initial Release]
 */
%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ page info="/WEB-INF/view/seller/sale/modifyForm.jsp" %>
<%@ taglib prefix="fmt"					uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/include/seller/header.jsp" %>
	<link rel="stylesheet" type="text/css" title="common stylesheet" href="/css/layoutSubmain.css" />
	<link rel="stylesheet" type="text/css" title="common stylesheet" href="/css/table.css" />
	<style></style>
	<link rel="stylesheet" href="http://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
	<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
	<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
	<script type="text/javascript" src="/js/package/tinymce/tinymce.min.js"></script>
	<script type="text/javascript" src="/js/package/tinymce.js"></script>
	<script>
		window.onload = function () {
			// HTML Editor
			tinymce.init({selector:'textarea'});
		}
		
		$(function(){
			$('#dt_sale_start').datepicker({dateFormat:'yy-mm-dd'});
		})
		
				$(function(){
			$('#dt_sale_end').datepicker({dateFormat:'yy-mm-dd'});
		})
		
		function modifyProc(value) {
			var frmMain = document.getElementById("frmMain");
			
			if (document.getElementById("sle_nm").value == ""
					|| document.getElementById("cd_where_ctg").value == "0"
					|| document.getElementById("price_sale").value == ""
					|| document.getElementById("dt_sale_start").value == ""
					|| document.getElementById("dt_sale_end").value == ""
					|| document.getElementById("cd_state_sale").value == "0"
					|| tinymce.activeEditor.getContent() == "") {
				alert("필수 항목을 입력하세요!");
				return;
			}
			document.getElementById("price_sale").value = document.getElementById("price_sale").value.replaceAll(",", "");
			
			frmMain.action="/seller/sale/modifyProc.web";
			frmMain.submit();
		}
	</script>
</head>
<body>
<form id="frmMain" method="POST" >
<input type="hidden" id="seq_sle"	name="seq_sle"	value="${saleDto.seq_sle}"/>
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
					<th style="width: 150px;">판매명</th>
					<td>
						<input style="width: 80%;" type="text" name="sle_nm" id="sle_nm" value="${saleDto.sle_nm}" required/>
					</td>
				</tr>
				<tr>
					<th>카테고리</th>
					<td>
						<select id="cd_where_ctg" name="cd_where_ctg" required>
							<option value="1"<c:if test="${saleDto.cd_where_ctg == '1'}"> selected</c:if>>도서</option>
							<option value="2"<c:if test="${saleDto.cd_where_ctg == '2'}"> selected</c:if>>가전 제품</option>
							<option value="3"<c:if test="${saleDto.cd_where_ctg == '3'}"> selected</c:if>>컴퓨터 용품</option>
						</select>
					</td>
				</tr>
				<tr>
					<th>설명</th>
					<td>
						<textarea required name="desces" id="desces" style="width: 650px;height:200px;" maxlength="1000">${saleDto.desces}</textarea>
					</td>
				</tr>
				<tr>
					<th>판매 가격</th>
					<td>
						<input type="text" id="price_sale" name="price_sale" value="<fmt:formatNumber value="${saleDto.price_sale}" type="number" />" style="width:100px; text-align:right" onkeyup="commaValue(this);" required/>원
					</td>
				</tr>
				<tr>
					<th>판매 상태</th>
					<td>
						<select id="cd_state_sale" name="cd_state_sale" required>
							<option value="1"<c:if test="${saleDto.cd_state_sale == '1'}"> selected</c:if>>판매중</option>
							<option value="2"<c:if test="${saleDto.cd_state_sale == '2'}"> selected</c:if>>판매 중지</option>
							<option value="3"<c:if test="${saleDto.cd_state_sale == '3'}"> selected</c:if>>반려</option>
							<option value="9"<c:if test="${saleDto.cd_state_sale == '9'}"> selected</c:if>>품절</option>
						</select>
					</td>
				</tr>
				<tr>
					<th>첨부 파일</th>
					<td>
						<input type="file" id="fileOrig" name="fileOrig" />
					</td>
				</tr>
				<tr>
					<th>판매 시작일</th>
					<td>
						<input type="text" id="dt_sale_start" name="dt_sale_start" value="${saleDto.dt_sale_start}" required/>
					</td>
				</tr>
				<tr>
					<th>판매 종료일</th>
					<td>
						<input type="text" id="dt_sale_end" name="dt_sale_end" value="${saleDto.dt_sale_end}" required/>
					</td>
				</tr>
				<tr>
					<td colspan="2" style="text-align:center;padding-top: 10px;padding-bottom: 10px">
						<input type="button" value="등록" style="width:100px" onclick="javascript:modifyProc();" />
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