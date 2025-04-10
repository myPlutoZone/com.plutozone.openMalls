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
 *				: [20241010125900][pluto#plutozone.com][INSERT: Payment Interface(거래번호 기반) and order() is Changed orderOld()]
 */
%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ page info="/WEB-INF/view/front/buy/writeForm.jsp" %>
<%@ taglib prefix="c"		uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt"		uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/include/front/header.jsp" %>
	<link rel="stylesheet" type="text/css" title="common stylesheet" href="/css/layoutSubmain.css" />
	<link rel="stylesheet" type="text/css" title="common stylesheet" href="/css/table.css" />
	<style></style>
	<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
	<script type="text/javascript">
		<c:if test="${empty sessionScope.SEQ_MBR}">
		var isLogin = false;
		</c:if>
		
		<c:if test="${not empty sessionScope.SEQ_MBR}">
		var isLogin = true;
		</c:if>
		
		<c:if test="${flgMobile == 'Y'}">
			$(document).ready(function() {
				$("#btnPay").on("click", function() {
					
					if (!isLogin) {
						alert("로그인이 필요합니다!");
						return;
					}
					
					$.ajax({
						url: "/front/interface/payup/orderOld.json",
						type: "POST",
						dataType: "json",
						data: $("#frmMain").serialize(),
						success: function(data) {
							$("input[name=ordr_idxx]").val(data.ordr_idxx);
							$("input[name=good_name]").val(data.good_name);
							$("input[name=good_mny]").val(data.good_mny);
							$("input[name=buyr_name]").val(data.buyr_name);
							$("input[name=site_cd]").val(data.site_cd);
							
							//alert(data.pay_url);
							// $("form[name=form]").action.val(data.pay_url);
							//alert(data.Ret_URL);
							$("input[name=Ret_URL]").val(data.Ret_URL);
							//alert(data.approval_key);
							$("input[name=approval_key]").val(data.approval_key);
							//alert(data.AppUrl);
							$("input[name=AppUrl]").val(data.AppUrl);
							
							document.form.submit();
							//alert("submit");
						},
						error: function(data) {
							alert("[ERROR]결제 연동!");
						}
					});
				});
			});
		</c:if>
		
		<c:if test="${flgMobile != 'Y'}">
			$(document).ready(function() {
				$("#btnPay").on("click", function() {
					
					if (!isLogin) {
						alert("로그인이 필요합니다!");
						return;
					}
					
					$.ajax({
						url: "/front/interface/payup/orderOld.json",
						type: "POST",
						dataType: "json",
						data: $("#frmMain").serialize(),
						success: function(data) {
							//console.log(data);
							$("input[name=ordr_idxx]").val(data.ordr_idxx);
							$("input[name=good_name]").val(data.good_name);
							$("input[name=good_mny]").val(data.good_mny);
							$("input[name=buyr_name]").val(data.buyr_name);
							$("input[name=site_cd]").val(data.site_cd);
							
							jsf__pay();
						},
						error: function(data) {
							alert("[ERROR]결제 연동!");
						}
					});
				});
			});
		</c:if>
		
		function writeProc() {
			
			if (!isLogin) {
				alert("로그인이 필요합니다!");
				return;
			}
			
			var frmMain = document.getElementById("frmMain");
			frmMain.action = "/front/buy/writeProc.web";
			frmMain.submit();
		}
		
		function setBasketIframe() {
			
			if (!isLogin) {
				alert("로그인이 필요합니다!");
				return;
			}
			
			// 화면의 구매할 상품 정보(수량 포함)를 서버로 전송
			var seq_sle	= document.getElementById("seq_sle").value;		// 판매 상품 일련번호
			var seq_prd	= document.getElementById("seq_prd").value;		// 상품 일련번호
			var seq_sll	= document.getElementById("seq_sll").value;		// 판매자 일련번호
			var sle_nm	= document.getElementById("sle_nm").value;		// 판매 상품명
			var price	= document.getElementById("price").value;		// 판매 상품 가격
			var count	= document.getElementById("count").value;		// 구매 수량
			var img		= document.getElementById("img").src;			// 판매 상품 이미지
			
			var item = seq_sle + "|" + seq_prd + "|" + seq_sll + "|" + sle_nm + "|" + price + "|" + count + "|"	+ img;
			document.getElementById("item").value = item;
			
			var frmMain = document.getElementById("frmMain");
			frmMain.action = "/front/basket/setBasketIframe.web";
			frmMain.target = "frmBlank";
			frmMain.submit();
		}
		
		function setBasketSession() {
			
			if (!isLogin) {
				alert("로그인이 필요합니다!");
				return;
			}
			
			// 화면의 구매할 상품 정보(수량 포함)를 서버로 전송
			var seq_sle	= document.getElementById("seq_sle").value;		// 판매 상품 일련번호
			var seq_prd	= document.getElementById("seq_prd").value;		// 상품 일련번호
			var seq_sll	= document.getElementById("seq_sll").value;		// 판매자 일련번호
			var sle_nm	= document.getElementById("sle_nm").value;		// 판매 상품명
			var price	= document.getElementById("price").value;		// 판매 상품 가격
			var count	= document.getElementById("count").value;		// 구매 수량
			var img		= document.getElementById("img").src;			// 판매 상품 이미지
			
			var item = seq_sle + "|" + seq_prd + "|" + seq_sll + "|" + sle_nm + "|" + price + "|" + count + "|"	+ img;
			document.getElementById("item").value = item;
			
			var frmMain = document.getElementById("frmMain");
			frmMain.action = "/front/basket/setBasketSession.web";
			frmMain.target = "frmBlank";
			frmMain.submit();
		}
		
		function setBasketCookie() {
			
			if (!isLogin) {
				alert("로그인이 필요합니다!");
				return;
			}
			
			var seq_sle	= document.getElementById("buyList[0].seq_sle").value;		// 판매 상품 일련번호
			var seq_prd	= document.getElementById("buyList[0].seq_prd").value;		// 상품 일련번호
			var seq_sll	= document.getElementById("buyList[0].seq_sll").value;		// 판매자 일련번호
			var sle_nm	= document.getElementById("buyList[0].sle_nm").value;		// 판매 상품명
			var price	= document.getElementById("buyList[0].price").value;		// 판매 상품 가격
			var count	= document.getElementById("buyList[0].count").value;		// 구매 수량
			var img		= document.getElementById("img").src;						// 판매 상품 이미지
			
			var item = seq_sle + "|" + seq_prd + "|" + seq_sll + "|" + sle_nm + "|" + price + "|" + count + "|"	+ img;
			//alert(item);
			
			// 쿠키에 구매 정보를 저장
			insertBasket(item);
			
			if (confirm("장바구니로 이동하시겠습니까?")) {
				location.href = "/front/basket/main.web";
			}
			else {
				location.href = "/front/sale/";
			}
			
		}
		
		function insertBasket(item) {
			// 이미 저장된 값을 쿠키에서 가져오기
			var existItems = getCookie("productBasket");
			
			// 쿠키값을 저장할 기간(일), null일 경우는 브라우저 닫을 시 삭제됨
			var expire = null;
			
			if (existItems) {
				//var itemArray = items.split(',');
				setCookie("productBasket", existItems + "," + item, expire);
				//alert(itemArray.length);
			}
			else {
				setCookie("productBasket", item, expire);
			}
			//alert("[저장된 정보]\n" + items);
		}
	</script>
</head>
<body>
<form id="frmMain" method="POST">
<div class="container">
	<input type="hidden" id="item" name="item" />
	<!-- [2024-10-10][pluto#plutozone.com][DELETE: 단일 상품만 구매 시]
	<input type="hidden" id="seq_sle"	value="${saleDto.seq_sle}" />
	<input type="hidden" id="seq_prd"	value="${saleDto.seq_prd}" />
	<input type="hidden" id="seq_sll"	value="${saleDto.seq_sll}" />
	<input type="hidden" id="sle_nm"	value="${saleDto.sle_nm}" />
	<input type="hidden" id="price"		value="${saleDto.price_sale}" />
	-->
	<input type="hidden" name="buyList[0].seq_sle" id="buyList[0].seq_sle" value="${saleDto.seq_sle}" />
	<input type="hidden" name="buyList[0].seq_prd" id="buyList[0].seq_prd" value="${saleDto.seq_prd}" />
	<input type="hidden" name="buyList[0].seq_sll" id="buyList[0].seq_sll"	value="${saleDto.seq_sll}" />
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
		<article>
			<table class="headLeft_01" style="margin-left: auto; margin-right: auto;">
				<tr>
					<th style="width: 200px;">판매명</th>
					<td>
						${saleDto.sle_nm}
						<input type="hidden" name="buyList[0].sle_nm" id="buyList[0].sle_nm" value="${saleDto.sle_nm}" />
					</td>
				</tr>
				<tr>
					<th>설명</th>
					<td>
						${saleDto.desces}
					</td>
				</tr>
				<tr>
					<th>판매 가격</th>
					<td>
						<fmt:formatNumber value="${saleDto.price_sale}" type="number" />원
						<input type="hidden" name="buyList[0].price" id="buyList[0].price" value="${saleDto.price_sale}" />
					</td>
				</tr>
				<tr>
					<th>이미지</th>
					<td>
						<img id="img" src="/image/sale/${saleDto.img}" height="250px" alt="판매상품 이미지" style="cursor: pointer" onclick="window.open(this.src)" />
					</td>
				</tr>
				<tr>
					<th>판매처</th>
					<td>
						${saleDto.corp_nm}
					</td>
				</tr>
				<tr>
					<th>구매 수량</th>
					<td>
						<input type="text" name="buyList[0].count" id="buyList[0].count" size="3" value="1" />
					</td>
				</tr>
				<tr>
					<td colspan="2" style="text-align:center;padding-top:10px;padding-bottom:10px">
						<input type="button" value="구매" style="width:100px" onclick="javascript:writeProc();"/>
						 <input type="button" value="구매+결제(거래 번호-가격/다중/앱 제외)" style="width:400px" id="btnPay" />
						<!--
						<input id="btnPay" type="button" value="구매" style="width:15%" />
						--> 
						<!-- "JavaScript + Cookie"를 사용하여 장바구니를 구현할 경우 -->
						<input type="button" value="장바구니" style="width:100px" onclick="javascript:setBasketCookie();"/>
						<!-- "Session + iFrame"를 사용하여 장바구니를 구현할 경우
						<input type="button" value="장바구니" style="width:100px" onclick="javascript:setBasketSession();"/>
						 -->
						<!-- "Database + iFrame"를 사용하여 장바구를 구현할 경우
						<input type="button" value="장바구니" style="width:100px" onclick="javascript:setBasketIframe();"/>
						-->
						<!-- [추후] "Database + jQuery"를 사용하여 장바구니를 구현할 경우
						<input type="button" value="장바구니" style="width:100px" onclick="javascript:setBasketJquery();"/>
						-->
					</td>
				</tr>
			</table>
		</article>
		<aside></aside>
	</section>
	<footer>
		<%@ include file="/include/front/footer.jsp" %>
	</footer>
</div>
</form>
<iframe name="frmBlank" id="frmBlank" width="0" height="0"></iframe>
<%
// [2024-10-10][pluto#plutozone.com][INSERT: Payup 연동 규격서를 기반으로 한 결제 연동 예제]
%>
<c:if test="${flgMobile == 'Y'}">
	<script type="text/javascript" src="https://testpay.kcp.co.kr/plugin/payplus_web.jsp"></script>
	<%
	// <script type="text/javascript" src="https://pay.kcp.co.kr/plugin/payplus_web.jsp"></script>
	%>
	<form name="form" action="https://testsmpay.kcp.co.kr/pay/mobileGW.kcp" method="post" accept-charset="euc-kr">
		<input type="hidden" name="ordr_idxx" value="">
		<input type="hidden" name="good_name" value="">
		<input type="hidden" name="good_mny" value="">
		<input type="hidden" name="buyr_name" value="">
		<input type="hidden" name="site_cd" value="">
		
		<input type="hidden" name="Ret_URL" value="">
		<input type="hidden" name="approval_key" value="">
		
		<input type="hidden" name="req_tx" value="pay">
		<input type="hidden" name="pay_method" value="CARD">
		<input type="hidden" name="currency" value="410">
		<input type="hidden" name="escw_used" value="N">
		<input type="hidden" name="AppUrl" value="testApp://testURL">
	</form>
</c:if>

<c:if test="${flgMobile != 'Y'}">
	<script type="text/javascript">
		function m_Completepayment(FormOrJson, closeEvent) {
			var frm = document.kcp_order_info;
			GetField(frm, FormOrJson);
			//console.log(frm);
			if (frm.res_cd.value == "0000") {
				frm.action = "/front/interface/payup/payOld.web";
				frm.submit();
				closeEvent();
			}
			else {
				alert("[" + frm.res_cd.value + "] " + frm.res_msg.value);
				closeEvent();
			}
		}
		
		function jsf__pay() {
			try {
				var form = document.kcp_order_info;
				KCP_Pay_Execute(form);
			}
			catch (e) {	}
		}
	</script>
	<script type="text/javascript" src="https://testpay.kcp.co.kr/plugin/payplus_web.jsp"></script>
	<form name="kcp_order_info" method="post" accept-charset="utf-8">
		<input type="hidden" name="ordr_idxx" value="">
		<input type="hidden" name="good_name" value="">
		<input type="hidden" name="good_mny" value="">
		<input type="hidden" name="buyr_name" value="">
		<input type="hidden" name="site_cd" value="">
		
		<input type="hidden" name="req_tx" value="pay">
		<input type="hidden" name="pay_method" value="100000000000">
		<input type="hidden" name="site_name" value="payup" />
		<!-- <input type="hidden" name="kakaopay_direct" value="Y"> -->
		
		<input type="hidden" name="res_cd" value="" />
		<input type="hidden" name="res_msg" value="" />
		<input type="hidden" name="enc_info" value="" />
		<input type="hidden" name="enc_data" value="" />
		<input type="hidden" name="ret_pay_method" value="" />
		<input type="hidden" name="tran_cd" value="" />
		<input type="hidden" name="use_pay_method" value="" />
		<input type="hidden" name="buyr_mail" value="">
		<input type="hidden" name="ordr_chk" value="" />
		<input type="hidden" name="good_expr" value="0">
		<input type="hidden" name="module_type" value="01" />
		<input type="hidden" name="currency" value="WON" />
	</form>
</c:if>
</body>
</html>