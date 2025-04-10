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
<%@ page info="/WEB-INF/view/backoffice/users/seller/list.jsp" %>
<%@ taglib prefix="c"					uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="plutozoneUtilTag"	uri="/WEB-INF/tld/com.plutozone.util.tld" %>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/include/console/header.jsp" %>
	<link rel="stylesheet" type="text/css" title="common stylesheet" href="/css/layoutSubmain.css" />
	<link rel="stylesheet" type="text/css" title="common stylesheet" href="/css/table.css" />
	<style></style>
	<script>
		function goPage(value) {
			
			var frmMain = document.getElementById("frmMain");
			
			frmMain.currentPage.setAttribute("value", value);
			frmMain.action="/console/users/seller/list.web";
			frmMain.submit();
		}
	</script>
</head>
<body>
<form id="frmMain" method="POST" action="/console/users/seller/list.web">
<input type="hidden" name="currentPage" id="currentPage" value="${paging.currentPage}" />
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
			<div class="brdSearchArea">
				<select name="cd_state">
					<option value=""<c:if test="${paging.cd_state == null}"> selected</c:if>>전체</option>
					<option value="0"<c:if test="${paging.cd_state == 0}"> selected</c:if>>대기</option>
					<option value="1"<c:if test="${paging.cd_state == 1}"> selected</c:if>>사용중</option>
					<option value="2"<c:if test="${paging.cd_state == 2}"> selected</c:if>>반려</option>
					<option value="8"<c:if test="${paging.cd_state == 8}"> selected</c:if>>자퇴</option>
					<option value="9"<c:if test="${paging.cd_state == 9}"> selected</c:if>>강퇴</option>
				</select>
				<select name="searchKey">
					<option value="sll_nm"<c:if test="${paging.searchKey == 'sll_nm'}"> selected</c:if>>판매자명</option>
					<option value="corp_nm"<c:if test="${paging.searchKey == 'corp_nm'}"> selected</c:if>>회사명(=판매처)</option>
					<option value="corp_num"<c:if test="${paging.searchKey == 'corp_num'}"> selected</c:if>>사업자등록번호</option>
				</select>
				<input type="text" name="searchWord" id="searchWord" value="${paging.searchWord}" /> <input type="submit" value="검색"/>
			</div>
			<div class="brdInfo">전체 ${paging.totalLine}개[${paging.currentPage}/${paging.totalPage} 페이지]</div>
			<table class="headTop_01" style="width: 900px; margin-left: auto; margin-right: auto">
				<tr>
					<th style="width: 5%">NO</th>
					<th style="width: 20%">판매자명(아이디)</th>
					<th>회사명(대표자명)</th>
					<th style="width: 10%">상태</th>
					<th style="width: 10%">등록일</th>
				</tr>
				<c:choose>
					<c:when test="${empty list}">
						<tr>
							<td colspan="5">등록된 판매자가 없습니다.</td>
						</tr>
					</c:when>
					<c:otherwise>
						<c:forEach items="${list}" var="list">
						<tr>
							<td>
								${list.rnum}
							</td>
							<td>
								<a href="/console/users/seller/modifyForm.web?seq_sll=${list.seq_sll}">
									${list.sll_nm}(${list.id})
								</a>
							</td>
							<td>
								${list.corp_nm}(${list.corp_ceo})
							</td>
							<td>
								${list.nm_state}
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
			<plutozoneUtilTag:page styleID="admin_text" currentPage="${paging.currentPage}" linePerPage="${paging.linePerPage}" totalLine="${paging.totalLine}" scriptFunction="goPage" />
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