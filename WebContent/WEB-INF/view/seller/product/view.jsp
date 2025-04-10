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
 *				: [20240808142000][pluto#plutozone.com][CREATE: Initial Release]
 */
%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ page info="/WEB-INF/view/seller/product/view.jsp" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/include/seller/header.jsp" %>
	<link rel="stylesheet" type="text/css" title="common stylesheet" href="/css/layoutSubmain.css" />
	<link rel="stylesheet" type="text/css" title="common stylesheet" href="/css/table.css" />
	<style></style>
	<script>
		function modifyForm() {
			var frmMain = document.getElementById("frmMain");
			
			frmMain.action = "/seller/product/modifyForm.web";
			frmMain.submit();
		}
	</script>
</head>
<body>
<form id="frmMain" method="POST">
<input type="hidden" name="seq_prd" value="${productDto.seq_prd}" />
<input type="hidden" name="cd_state_prd" value="${productDto.cd_state_prd}" />
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
					<th style="width: 150px;">상품명</th>
					<td>
						${productDto.prd_nm}
					</td>
				</tr>
				<c:if test="${productDto.flg_delete == 'N'}">
					<tr>
						<th>상품 상태</th>
						<td>
							<c:if test="${productDto.cd_state_prd == '1'}">판매중</c:if>
							<c:if test="${productDto.cd_state_prd == '2'}">판매중지</c:if>
							<c:if test="${productDto.cd_state_prd == '3'}">반려</c:if>
							<c:if test="${productDto.cd_state_prd == '9'}">재고소진</c:if>
						</td>
					</tr>
				</c:if>
				<tr>
					<th>카테고리</th>
					<td>
						<c:if test="${productDto.cd_ctg == '01'}">총류</c:if>
						<c:if test="${productDto.cd_ctg == '02'}">철학</c:if>
						<c:if test="${productDto.cd_ctg == '03'}">종교</c:if>
						<c:if test="${productDto.cd_ctg == '04'}">사회</c:if>
						<c:if test="${productDto.cd_ctg == '05'}">자연</c:if>
						<c:if test="${productDto.cd_ctg == '06'}">기술</c:if>
						<c:if test="${productDto.cd_ctg == '07'}">예술</c:if>
						<c:if test="${productDto.cd_ctg == '08'}">언어</c:if>
						<c:if test="${productDto.cd_ctg == '09'}">문학</c:if>
					</td>
				</tr>
				<tr>
					<th>상품 원가</th>
					<td>
						<fmt:formatNumber value="${productDto.price_cost}" type="number" /> 원
					</td>
				</tr>
				<tr>
					<th>재고 수량(*)</th>
					<td>
						${productDto.count_stock} 개
					</td>
				</tr>
				<tr>
					<th>등록일</th>
					<td>
						${productDto.dt_reg}
					</td>
				</tr>
				<tr>
					<th>수정일</th>
					<td>
						${productDto.dt_upt}
					</td>
				</tr>
				<tr>
					<td colspan="2" style="text-align:center;padding-top: 10px;padding-bottom: 10px">
						<c:if test="${productDto.flg_delete == 'N'}">
							<input type="button" value="삭제" style="width:100px" onclick="javascript:location.href='/seller/product/remove.web?seq_prd=${productDto.seq_prd}';" />
							<input type="button" value="수정" style="width:100px" onclick="javascript:modifyForm();" />
						</c:if>
						<!-- [2024-10-14][pluto#plutozone.com][TODO-개선: 리스트로 이동할 때 검색 내역 기억] -->
						<input type="button" value="목록" style="width:100px" onclick="javascript:location.href='/seller/product/list.web'"/>
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