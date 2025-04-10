<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ page info="/WEB-INF/view/front/buy/list.jsp" %>
<%@ taglib prefix="fmt"					uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c"					uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="plutozoneUtilTag"	uri="/WEB-INF/tld/com.plutozone.util.tld" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" type="text/css" title="common stylesheet" href="/css/layoutSubmain.css" />
	<link rel="stylesheet" type="text/css" title="common stylesheet" href="/css/table.css" />
	<%@ include file="/include/front/header5th.jsp" %>
	<style></style>
	<script type="text/javascript" src="/js/common.js"></script>
	<script type="text/javascript" src="/js/front.js"></script>
	<script>
		function goView(value) {
			
			var frmMain = document.getElementById("frmMain");
			
			frmMain.seq_buy_mst.setAttribute("value", value);
			frmMain.action = "/front/buy/view.web";
			frmMain.submit();
		}
		
		function goPage(value) {
			
			var frmMain = document.getElementById("frmMain");
			
			frmMain.currentPage.setAttribute("value", value);
			frmMain.action = "/front/buy/list.web";
			frmMain.submit();
		}
	</script>
</head>
<body>
<div class="container">
<form id="frmMain" method="POST" action="/front/buy/list.web">
	<input type="hidden" name="seq_buy_mst" id="seq_buy_mst" />
	<input type="hidden" name="currentPage" value="${paging.currentPage}" />
	<header>
		<%@ include file="/include/front/top5th.jsp" %>
	</header>
		<nav>
			<%@ include file="/include/front/lnb5th.jsp" %>
		</nav>
<section class="content">
			<article class="txtCenter">
			<div class="brdSearchArea">
				<select name="searchKey">
					<option value="buy_info"<c:if test="${paging.searchKey == 'buy_info'}"> selected</c:if>>구매명</option>
					<option value="prd_nm"<c:if test="${paging.searchKey eq 'prd_nm'}"> selected</c:if>>상품명</option>
				</select>
				<input type="text" name="searchWord" value="${paging.searchWord}" /> <input type="submit" value="검색"/>
			</div>
			<div class="brdInfo">Total: ${paging.totalLine}[${paging.currentPage}/${paging.totalPage} 페이지]</div>
			<table class="type_01" style="margin-left: auto; margin-right: auto;">
				<thead>
					<tr>
						<th style="width: 5%">NO</th>
						<th>구매명</th>
						<th>총 가격(수량)</th>
						<th>결제 상태</th>
						<th>배송 상태</th>
						<th>판매처</th>
						<th>등록일</th>
					</tr>
				</thead>
				<tbody>
				<c:choose>
					<c:when test="${empty list}">
						<tr>
							<td colspan="6">구매한 상품이 없습니다.</td>
						</tr>
					</c:when>
					<c:otherwise>
					<c:forEach items="${list}" var="list">
						<tr style="cursor: pointer;<c:if test="${list.cd_state_delivery != 'Y'}"> color: black</c:if>" onclick="javascript:goView(${list.seq_buy_mst});">
							<td>
								list.rnum
							</td>
							<td style="text-align: left">
								${list.buy_info}
							</td>
							<td>
								<fmt:formatNumber value="${list.buy_price}" type="number" />(<fmt:formatNumber value="${list.buy_count}" type="number" />)
							</td>
							<td>
								list.state_pay_nm
							</td>
							<td>
								list.state_delivery_nm
							</td>
							<td>
								list.corp_nm
							</td>
							<td>
								${list.dt_reg}
							</td>
						</tr>
						</c:forEach>
					</c:otherwise>
					</c:choose>
				</tbody>
			</table>
			<br />
			<plutozoneUtilTag:page styleID="front_image" currentPage="${paging.currentPage}" linePerPage="${paging.linePerPage}" totalLine="${paging.totalLine}" scriptFunction="goPage" />
			<br />
			<a href="/front/buy/searchForm.web" class="btnBasic"> 구매 </a>
		</article>
		<aside></aside>
	</section>
	<footer>
		<%@ include file="/include/front/footer5th.jsp" %>
	</footer>
</form>
</div>
</body>
</html>