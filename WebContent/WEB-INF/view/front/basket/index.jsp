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
 *				: [20240626130000][pluto#plutozone.com][CREATE: Initial Release]
 */
%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ page info="/WEB-INF/view/front/basket/index.jsp" %>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/include/front/header.jsp" %>
	<link rel="stylesheet" type="text/css" title="common stylesheet" href="/css/layoutMain.css" />
	<link rel="stylesheet" type="text/css" title="common stylesheet" href="/css/table.css" />
	<style></style>
	<script>
		function writeProc() {
			/* JavaScript + Cookie */
			// [2024-08-26][pluto#plutozone.com][TODO-개선: 쿠키 정보(장바구니) 삭제(정상적으로 구매 및 결제된 경우만)]
			document.cookie = "productBasket=; path=/; expires=Sat, 01 Jan 1972 00:00:00 GMT";
			
			/* Session + iFrame
			[2024-08-27][pluto#plutozone.com][TODO-개선: 세션 정보(장바구니) 삭제 필요(정상적으로 구매 및 결제된 경우)]
			*/
			
			/* Database + iFrame
			[2024-08-27][pluto#plutozone.com][TODO-개선: 데이터베이스 정보(장바구니) 삭제 처리 필요(정상적으로 구매 및 결제된 경우)]
			*/
			
			var frmMain = document.getElementById("frmMain");
			frmMain.action = "/front/buy/writeProc.web";
			frmMain.submit();
		}
		
		/*
		[2024-08-26][pluto#plutozone.com][TODO-개선: 장바구니 by JavaScript + Cookies]
		@ 합계(수량과 금액) 표시
		@ 삭제 버튼(합계 포함)
		@ 수량 변경(합계 포함)
		*/
		window.onload = function () {
			
			/* Session/Database + iFrame
			var items = "${item}";
			
			if (items != "") {
				var itemArray	= items.split(",");
				
				var table = document.getElementById("productBasket");
				
				if (itemArray.length > 0) table.deleteRow(-1);
				
				for (loop = 0; loop < itemArray.length; loop++) {
					
					//alert(itemArray[loop]);
					
					var item = itemArray[loop].split("|");
					newRow = table.insertRow();
					
					newCell1 = newRow.insertCell(0);			
					newCell2 = newRow.insertCell(1);			// [0] 판매 상품 일련번호(seq_sle)
					newCell3 = newRow.insertCell(2);			// [1] 상품 일련번호(seq_prd)
					newCell4 = newRow.insertCell(3);			// [2] 판매자 일련번호(seq_sll)
					newCell5 = newRow.insertCell(4);			// [3] 판매 상품명(sle_nm)
					// [4] 판매 상품 가격(price)
					// [5] 구매 수량(count)
					// [6] 판매 상품 이미지(img)
					
					newCell1.innerText = loop + 1;
					newCell2.innerText = item[3]
					// newCell3.innerText = item[4].replace(/\B(?<!\.\d*)(?=(\d{3})+(?!\d))/g, ",");	// 가격
					newCell3.innerText = item[4];
					newCell4.innerHTML = "<img src='" + item[6] + "' height='100px' />"
									+ "<input type='hidden' name='buyList[" + loop + "].seq_sle' id='buyList[" + loop + "].seq_sle' value='" + item[0] + "'/>"
									+ "<input type='hidden' name='buyList[" + loop + "].seq_prd' id='buyList[" + loop + "].seq_prd' value='" + item[1] + "'/>"
									+ "<input type='hidden' name='buyList[" + loop + "].sle_nm'  id='buyList[" + loop + "].sle_nm'  value='" + item[3] + "'/>"
									+ "<input type='hidden' name='buyList[" + loop + "].price'   id='buyList[" + loop + "].price'   value='" + item[4] + "'/>";
									
					// newCell5.innerText = item[5].replace(/\B(?<!\.\d*)(?=(\d{3})+(?!\d))/g, ",");	// 수량
					newCell5.innerHTML = "<input type='text' name='buyList[" + loop + "].count' id='buyList[" + loop + "].count' value='" + item[5] + "'/>";
				}
			}
			 */
			 
			/* JavaScript + Cookie */
			var items		= getCookie("productBasket");
			var itemArray	= items.split(",");
			
			var table = document.getElementById("productBasket");
			
			if (itemArray.length > 0) table.deleteRow(-1);
			
			for (loop = 0; loop < itemArray.length; loop++) {
				
				//alert(itemArray[loop]);
				
				var item = itemArray[loop].split("|");
				newRow = table.insertRow();
				
				newCell1 = newRow.insertCell(0);			
				newCell2 = newRow.insertCell(1);			// [0] 판매 상품 일련번호(seq_sle)
				newCell3 = newRow.insertCell(2);			// [1] 상품 일련번호(seq_prd)
				newCell4 = newRow.insertCell(3);			// [2] 판매자 일련번호(seq_sll)
				newCell5 = newRow.insertCell(4);			// [3] 판매 상품명(sle_nm)
				// [4] 판매 상품 가격(price)
				// [5] 구매 수량(count)
				// [6] 판매 상품 이미지(img)
				
				newCell1.innerText = loop + 1;
				newCell2.innerText = item[3]
				// newCell3.innerText = item[4].replace(/\B(?<!\.\d*)(?=(\d{3})+(?!\d))/g, ",");	// 가격
				newCell3.innerText = item[4];
				newCell4.innerHTML = "<img src='" + item[6] + "' height='100px' />"
								+ "<input type='hidden' name='buyList[" + loop + "].seq_sle' id='buyList[" + loop + "].seq_sle' value='" + item[0] + "'/>"
								+ "<input type='hidden' name='buyList[" + loop + "].seq_prd' id='buyList[" + loop + "].seq_prd' value='" + item[1] + "'/>"
								+ "<input type='hidden' name='buyList[" + loop + "].sle_nm'  id='buyList[" + loop + "].sle_nm'  value='" + item[3] + "'/>"
								+ "<input type='hidden' name='buyList[" + loop + "].price'   id='buyList[" + loop + "].price'   value='" + item[4] + "'/>";
								
				// newCell5.innerText = item[5].replace(/\B(?<!\.\d*)(?=(\d{3})+(?!\d))/g, ",");	// 수량
				newCell5.innerHTML = "<input type='text' name='buyList[" + loop + "].count' id='buyList[" + loop + "].count' value='" + item[5] + "'/>";
			}
		}
	</script>
</head>
<body>
<form id="frmMain" method="POST">
<div class="container">
	<header>
		<%@ include file="/include/front/top.jsp" %>
	</header>
	<nav>
		<%@ include file="/include/front/gnb.jsp" %>
	</nav>
	<section class="content">
		<nav></nav>
		<article class="txtCenter">
			<table id="productBasket" class="headTop_01" style="width: 900px; margin-left: auto; margin-right: auto;">
			<thead>
				<tr>
					<th style="width: 5%">NO</th>
					<th>판매명</th>
					<th>가격</th>
					<th>이미지</th>
					<th>수량</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td colspan="5">
						장바구니에 저장된 정보가 없습니다!
					</td>
				</tr>
			</tbody>
			</table>
			<br/>
			<input type="button" value="구매" style="width:100px" onclick="javascript:writeProc();"/>
			<br/>
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