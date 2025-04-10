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
<%@ page info="/WEB-INF/view/seller/product/list.jsp" %>
<%@ taglib prefix="c"					uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt"					uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="plutozoneUtilTag"	uri="/WEB-INF/tld/com.plutozone.util.tld" %>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/include/seller/header.jsp" %>
	<link rel="stylesheet" type="text/css" title="common stylesheet" href="/css/layoutSubmain.css" />
	<link rel="stylesheet" type="text/css" title="common stylesheet" href="/css/table.css" />
	<style></style>
	<script>
		function goPages(value) {
			
			var frmMain = document.getElementById("frmMain");
			
			frmMain.currentPage.setAttribute("value", value);
			frmMain.action="/seller/product/list.web";
			frmMain.submit();
		}
	</script>
</head>
<body>
<form id="frmMain" method="POST">
<input type="hidden" name="currentPage" id="currentPage" value="${paging.currentPage}" />
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
			<div class="brdSearchArea">
				<!-- [2024-10-14][pluto#plutozone.com][TODO-확장: 삭제된 목록은 판매 여부 상관없이 모두 볼 수 있도록] -->
				<input type="checkbox" id="flg_delete" name="flg_delete" value="N"<c:if test="${paging.flg_delete == 'N'}"> checked</c:if>/><label for="flg_delete">삭제</label>
				<select name="cd_state_prd">
					<option value="1"<c:if test="${paging.cd_state_prd == 1}">selected</c:if>>판매중</option>
					<option value="2"<c:if test="${paging.cd_state_prd == 2}">selected</c:if>>판매중지</option>
					<option value="3"<c:if test="${paging.cd_state_prd == 3}">selected</c:if>>반려</option>
					<option value="9"<c:if test="${paging.cd_state_prd == 9}">selected</c:if>>재고소진</option>
				</select>
				<select name="searchKey">
					<option value="prd_nm">상품명</option>
				</select>
				<input type="text" name="searchWord" id="searchWord" value="${paging.searchWord}" /> <input type="submit" value="검색"/>
			</div>
			<div class="brdInfo">전체 ${paging.totalLine}개[${paging.currentPage}/${paging.totalPage} 페이지]</div>
			<table class="headTop_01" style="width: 900px; margin-left: auto; margin-right: auto">
				<tr>
					<th style="width: 5%">NO</th>
					<th>상품명</th>
					<th style="width: 10%">카테고리</th>
					<th style="width: 10%">원가</th>
					<th style="width: 7%">재고</th>
					<th style="width: 10%">등록일</th>
					
				</tr>
				<c:choose>
					<c:when test="${empty list}">
						<tr>
							<td colspan="6">등록된 상품이 없습니다.</td>
						</tr>
					</c:when>
					<c:otherwise>
						<c:forEach items="${list}" var="list">
						<tr>
							<td>
								${list.rnum}
							</td>
							<td style="text-align: left">
								<a href="/seller/product/view.web?seq_prd=${list.seq_prd}">${list.prd_nm} </a>
							</td>
							<td>
								<c:if test="${list.cd_ctg == '01'}">총류</c:if>
								<c:if test="${list.cd_ctg == '02'}">철학</c:if>
								<c:if test="${list.cd_ctg == '03'}">종교</c:if>
								<c:if test="${list.cd_ctg == '04'}">사회</c:if>
								<c:if test="${list.cd_ctg == '05'}">자연</c:if>
								<c:if test="${list.cd_ctg == '06'}">기술</c:if>
								<c:if test="${list.cd_ctg == '07'}">예술</c:if>
								<c:if test="${list.cd_ctg == '08'}">언어</c:if>
								<c:if test="${list.cd_ctg == '09'}">문학</c:if>
							</td>
							<td>
								<fmt:formatNumber value="${list.price_cost}" type="number" />
							</td>
							<td>
								<fmt:formatNumber value="${list.count_stock}" type="number" />
							</td>
							<td>
								${list.dt_reg}
							</td>
						</tr>
						</c:forEach>
					</c:otherwise>
				</c:choose>
			</table>
			<br/>
			<plutozoneUtilTag:page styleID="front_image" currentPage="${paging.currentPage}" linePerPage="${paging.linePerPage}" totalLine="${paging.totalLine}" scriptFunction="goPages" />
			<br/>
			<a href="/seller/product/writeForm.web" class="btnBasic">등록</a>
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