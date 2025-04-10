<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ page info="/WEB-INF/view/front/buy/view.jsp" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/include/front/header.jsp" %>
	<script>
		function cancel() {
			if (confirm("구매를 취소하시겠습니까?")) {
				alert("TODO-개선: 배송이 완료된 경우 취소 불가 처리할 것 + 취소 로직 추가할 것");
				return;
				var frmMain = document.getElementById("frmMain");
				frmMain.action = "/front/buy/cancel.web";
				frmMain.submit();
			}
		}
	</script>
</head>
<body>
<div class="container">
<form id="frmMain" method="POST">
	<input type="hidden" name="seq_buy_mst" value="${list[0].seq_buy_mst}" />
	<header>
		<%@ include file="/include/front/top.jsp" %>
	</header>
	<section class="content">
		<nav>
			<%@ include file="/include/front/buy/lnb.jsp" %>
		</nav>
		<article>
			<table class="type_01" style="margin-left: auto; margin-right: auto;">
				<thead>
					<tr>
						<th style="width: 5%">NO</th>
						<th>판매 이미지</th>
						<th>판매명 및 설명</th>
						<th>가격</th>
						<th>수량</th>
					</tr>
				</thead>
				<tbody>
				<c:choose>
					<c:when test="${empty list}">
						<tr>
							<td colspan="5">등록된 상품이 없습니다.</td>
						</tr>
					</c:when>
					<c:otherwise>
					<c:forEach items="${list}" var="list"  varStatus="status">
					<tr>
						<td>
							${status.count}
						</td>
						<td>
							<div class="thumbnail round" style="background-image:url('/image/sale/${list.img}')"></div>
						</td>
						<td>
							${list.sle_nm}
							<br />
							${list.desces}
						</td>
						<td>
							<fmt:formatNumber value="${list.price}" type="number" />
						</td>
						<td>
							<fmt:formatNumber value="${list.count}" type="number" />
						</td>
					</tr>
					</c:forEach>
					</c:otherwise>
				</c:choose>
					<tr>
						<td colspan="3">
							${list[0].dt_reg}에 구매한 총 가격(수량)
						</td>
						<td>
						*
						</td>
						<td>
						*
						</td>
					</tr>
				</tbody>
			</table>
			<br />
			<a href="javascript:cancel();" class="btnBasic"> 취소 </a>
			 <a href="/front/buy/list.web" class="btnBasic"> 목록 </a>
		</article>
		<aside></aside>
	</section>
	<footer>
		<%@ include file="/include/front/footer.jsp" %>
	</footer>
</form>
</div>
</body>
</html>