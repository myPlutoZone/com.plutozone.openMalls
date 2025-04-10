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
 *				: [20240808110900][pluto#plutozone.com][CREATE: Initial Release]
 */
%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ page info="/WEB-INF/view/seller/sale/list.jsp" %>
<%@ taglib prefix="c"					uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt"					uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn"					uri="http://java.sun.com/jsp/jstl/functions" %>
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
		frmMain.action="/seller/sale/list.web";
		frmMain.submit();
	}
	</script>
</head>
<body>
<form id="frmMain" method="POST">
<input type="hidden" name="currentPage" id="currentPage" value="${paging.currentPage}" />
<input type="hidden" name="sle_desces" id="sle_desces" value="${paging.sle_desces}" />
<div class="container">
	<header>
		<%@ include file="/include/seller/top.jsp" %>
	</header>
	<nav>
		<%@ include file="/include/seller/gnb.jsp" %>
	</nav>
	<section class="content">
		<article class="txtCenter">
			<div class="brdSearchArea">
				<select name="searchKey">
					<option value="sle_nm">상품명</option>
					<option value="sle_desces">상품설명</option>
					<option value="sle_desces_and_nm">상품명 및 설명</option>
				</select>
				<select name="cd_state_sale" id="cd_state_sale">
					<option value="0"<c:if test="${paging.cd_state_sale == 0}">selected</c:if>>판매상태</option>
					<option value="1"<c:if test="${paging.cd_state_sale == 1}">selected</c:if>>판매중</option>
					<option value="2"<c:if test="${paging.cd_state_sale == 2}">selected</c:if>>판매중지</option>
					<option value="3"<c:if test="${paging.cd_state_sale == 3}">selected</c:if>>반려</option>
					<option value="9"<c:if test="${paging.cd_state_sale == 9}">selected</c:if>>품절</option>
				</select>
				<input type="text" name="searchWord" id="searchWord" value="${paging.searchWord}"/><input type="submit" value="검색"/>
			</div>
			<div class="brdInfo">전체 ${paging.totalLine}개[${paging.currentPage}/${paging.totalPage} 페이지]</div>
			<table class="headTop_01" style="width: 900px; margin-left: auto; margin-right: auto">
				<tr>
					<th style="width: 5%">NO</th>
					<th style="width: 60%">판매명</th>
					<th style="width: 10%">상태</th>
					<th style="width: 15%">가격</th>
					<th style="width: 10%">등록일</th>
				</tr>
				<c:choose>
					<c:when test="${empty list}">
						<tr>
							<td colspan="5">등록된 상품이 없습니다.</td>
						</tr>
					</c:when>
					<c:otherwise>
						<c:forEach items="${list}" var="list">
						<tr>
							<td class="txtCenter">
								${list.rnum}
							</td>
							<td class="txtLeft">
								<a href="/seller/sale/view.web?seq_sle=${list.seq_sle}">
									<plutozoneUtilTag:substring text="${fn:escapeXml(list.sle_nm)}" length="40" />
								</a>
							</td>
							<td class="txtCenter">
								<c:choose>
									<c:when test="${list.cd_state_sale == '1'}">
										판매중
									</c:when>
									<c:when test="${list.cd_state_sale == '2'}">
										판매중지
									</c:when>
									<c:when test="${list.cd_state_sale == '3'}">
										반려
									</c:when>
									<c:when test="${list.cd_state_sale == '9'}">
										품절
									</c:when>
								</c:choose>
							</td>
							<td class="txtRight">
								<fmt:formatNumber value="${list.price_sale}" type="number" />원
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
			<plutozoneUtilTag:page styleID="admin_text" currentPage="${paging.currentPage}" linePerPage="${paging.linePerPage}" totalLine="${paging.totalLine}" scriptFunction="goPages"/>
			<br/>
			<a href="/seller/sale/writeForm.web" class="btnBasic">판매 등록</a>
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