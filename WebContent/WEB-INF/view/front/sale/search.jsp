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
<%@ page info="/WEB-INF/view/front/sale/list.jsp" %>
<%@ taglib prefix="c"		uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn"		uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt"		uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/include/front/header.jsp" %>
	<link rel="stylesheet" type="text/css" title="common stylesheet" href="/css/layoutSubmain.css" />
	<link rel="stylesheet" type="text/css" title="common stylesheet" href="/css/table.css" />
	<style>
		.thumbnail{
			display:inline-block;
			margin:5px;
			width:100px;
			height:100px;
			background:gray no-repeat center / cover;
		}
		.thumbnail.round{
			border-radius: 10%;
		}
		.thumbnail.circle{
			border-radius: 100%;
		}
	</style>
	<script>
	function goWriteForm(value) {
			
			// document.getElementById("currentPage").remove();
			// document.getElementById("searchKey").remove();
			// document.getElementById("searchWord").remove();
			
			var frmMain = document.getElementById("frmMain");
			
			document.getElementById("seq_sle").value = value;
			
			frmMain.action="/front/buy/writeForm.web";
			frmMain.submit();
		}
	</script>
</head>
<body>
<form id="frmMain" method="POST">
<input type="hidden" name="seq_sle" id="seq_sle" />
<div class="container">
	<header>
		<%@ include file="/include/front/top.jsp" %>
	</header>
	<nav>
		<%@ include file="/include/front/gnb.jsp" %>
	</nav>
	<section class="content">
		<nav>
			<%@ include file="/include/front/lnbSale.jsp" %>
		</nav>
		<article class="txtCenter">
			<div class="brdSearchArea">
				<select>
					<option>상품명</option>
				</select>
				<input type="text" name="searchWord" id="searchWord" value="" /> <input type="submit" value="검색"/>
			</div>
			<div class="brdInfo">전체 ?개</div>
			<table class="headTop_01" style="width: 900px; margin-left: auto; margin-right: auto">
				<tr>
					<th style="width: 5%">NO</th>
					<th style="width: 15%">이미지</th>
					<th>상품명</th>
					<th style="width: 10%">가격</th>
				</tr>
				<c:choose>
					<c:when test="${empty list}">
						<tr>
							<td colspan="4">검색된 상품이 없습니다.</td>
						</tr>
					</c:when>
					<c:otherwise>
						<c:forEach items="${list}" var="list">
						<tr>
							<td>
								${list.rnum}
							</td>
							<td>
								<a href="javascript:goWriteForm(${list.seq_sle});">
									<span class="thumbnail round" style="background-image:url('/image/sale/${fn:replace(list.img, '\\', '/')}')"></span>
								</a>
							</td>
							<td style="text-align: left">
								<a href="javascript:goWriteForm(${list.seq_sle});">
									${list.sle_nm}
								</a>
							</td>
							<td>
							<fmt:formatNumber value="${list.price_sale}" type="number" />원
							</td>
						</tr>
						</c:forEach>
					</c:otherwise>
				</c:choose>
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