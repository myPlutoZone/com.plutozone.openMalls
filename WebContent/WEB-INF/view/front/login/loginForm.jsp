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
<%@ page info="/WEB-INF/view/front/login/loginForm.jsp" %>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/include/front/header.jsp" %>
	<link rel="stylesheet" type="text/css" title="common stylesheet" href="/css/layoutMain.css" />
	<link rel="stylesheet" type="text/css" title="common stylesheet" href="/css/table.css" />
	<script type="text/javascript" src="/js/front.js"></script>
	<script type="text/javascript" src="/js/jquery-3.7.1.min.js"></script>
	<style></style>
	<script>
		window.onload = function () {
			if ("${url}") {
				alert("로그인이 필요합니다.");
			}
		}
		
		function checkForm() {
			
			var frmMain = document.getElementById("frmMain");
			/*
			alert("이메일(아이디)=" + document.getElementById("email").value + "\n"
					+ "비밀번호=" + document.getElementById("passwd").value);
			*/
			/*
			alert("이메일(아이디)=" + document.frmMain.email.value + "\n"
					+ "비밀번호=" + document.frmMain.passwd.value);
			*/
			// if (document.getElementById("email").value == "") {
			if (document.getElementById("email").value.length < 6
					|| document.getElementById("email").value.length > 32) {
				alert("이메일(아이디)를 6 ~ 16자 이내로 입력하세요!");
				document.getElementById("email").focus();
				// document.getElementById("email").select();
				return;
			}
			
			if (document.getElementById("passwd").value.length < 8
					|| document.getElementById("passwd").value.length > 16) {
				alert("비밀번호를 8 ~ 16자 이내로 입력하세요!");
				document.getElementById("passwd").focus();
				return;
			}
			
			frmMain.action = "/front/login/loginProc.web";
			frmMain.submit();
		}
		/*
		$(function() {
			var $frm = $("#frmMain");
			
			$frm.on("submit", function(e) {
				
				// 이메일과 비밀번호가 7자리 이하 또는 @가 없으면
				if ($("#email").val().length <=7
						|| $("#passwd").val().length <=7
						|| $("#email").val().indexOf("@") <= 0) {
					alert("이메일/아이디(@ 포함)와 비밀번호는 8자리 이상을 입력하세요!");
					return false;
				}
				
				e.preventDefault();
				var myData = $frm.serialize();
				//alert(myData);
				
				$.ajax({
					type: "POST",
					url: $frm.attr("action"),
					data: myData,
					success:function(res) {
						
						if (res) {
							var jsonData = JSON.parse(res);
							//alert(jsonData);
							
							var message = "";
							//alert("[" + jsonData.name + "]");
							
							if (jsonData.name != "") {
								message = jsonData.name
										+ "(" + jsonData.email
										+ ")" + "님 반갑습니다.";
							}
							else {
								message = jsonData.email
										+ ", 너 누구냐?";
							}
							
							$(".container").html(message);
						}
					}
				});
			});
		});
		*/
	</script>
</head>
<body>
<form id="frmMain" name="frmMain" method="POST">
<!-- <form id="frmMain" method="POST" action="/loginProc.jsp"> -->
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
			<table class="headLeft_01" style="width: 320px; margin-left: auto; margin-right: auto">
				<tr>
					<th>이메일(아이디)</th>
					<td><input type="text" id="email" name="email" value="ID@gmail.com" maxlength="32" required /></td>
				</tr>
				<tr>
					<th>비밀번호</th>
					<td><input type="password" id="passwd" name="passwd" value="12345678!a" maxlength="16" required /></td>
				</tr>
				<tr>
					<td colspan="2" style="text-align:center;border-bottom:0px;padding-top: 10px"><input type="button" value="로그인" style="width:100px" onClick="checkForm();"/></td>
				</tr>
				<tr>
					<td colspan="2" style="text-align:center;border-top:0px;"><a href="#">[아이디/비밀번호 찾기]</a> <a href="/front/member/registerForm.web">[회원 가입]</a></td>
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
</body>
</html>