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
<%@ page info="/WEB-INF/view/front/member/registerForm.jsp" %>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/include/front/header.jsp" %>
	<link rel="stylesheet" type="text/css" title="common stylesheet" href="/css/layoutMain.css" />
	<link rel="stylesheet" type="text/css" title="common stylesheet" href="/css/table.css" />
	<script type="text/javascript" src="/js/front.js"></script>
	<script type="text/javascript" src="/js/jquery-3.7.1.min.js"></script>
	<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
	<style></style>
	<script>
		// 이메일 중복 여부
		var isDuplicate = true;
		
		$(function() {
			
			var $frm = $("#frmMain");
			
			/*
			$("#btnConfirm").on("click", function(e) {
				
				var myData = {email: $("#email").val()};
				
				$.ajax({
					type: "POST",
					url: "/front/member/checkEmail.json",
					dataType: "json",
					contentType: "application/json; charset=UTF-8",
					data: JSON.stringify(myData),
					success:function(res) {
						if (res == true) {
							alert("정상적으로 " + $("#email").val()
									+ "로 인증 URL이 전송되었습니다.\n반드시 가입 후 10분 이내에 인증 URL을 클릭하셔야 정상적으로 서비스를 이용할 수 있습니다.");
									// + "로 인증 URL이 전송되었습니다.\n반드시 가입 후 인증 URL을 클릭하셔야 정상적으로 서비스를 이용할 수 있습니다.");
						}
						else {
							alert("인증 이메일이 발송이 실패되었습니다!\n시스템 관리자에게 문의하세요!");
						}
					}
				});
				
			});
			*/
			
			$("#btnId").on("click", function(e) {
				
				// 이메일이 7자리 이하 또는 @가 없으면
				if ($("#email").val().length <=7 || $("#email").val().indexOf("@") <= 0) {
					alert("이메일/아이디를(@ 포함) 8자리 이상으로 입력하세요!");
					return false;
				}
				
				// var myData = $frm.serialize();
				// var myData = "email=" + $("#email").val();
				
				// var myData = {email: "ID@gmail.com", passwd: "123456"};
				var myData = {email: $("#email").val()};
				//alert(JSON.stringify(myData));
				
				/*
				var myData = "{\"email\": \"ID@gmail.com\", \"passwd\": \"12345678\"}";
				alert(myData);
				*/
				
				$.ajax({
					type: "POST",
					// async: false,
					url: "/front/member/checkDuplicate.json",
					dataType: "json",
					contentType: "application/json; charset=UTF-8",
					data: JSON.stringify(myData),
					success:function(res) {
						// alert(JSON.stringify(res));
						// var jsonData = JSON.parse(res);
						// 중복이 안 될 경우
						if (res != true) {
							isDuplicate = false;
							$("#email").attr("readonly",true);
							alert($("#email").val() + "는 사용 가능하며 변경할 수 없습니다.");
							// $("#btnConfirm").attr("disabled", false);
						}
						else {
							alert($("#email").val() + "는 중복됩니다! 다른 이메일을 입력하세요!");
							$("#email").val("");
							$("#email").focus();
						}
					}
				});
			});
		});
	</script>
</head>
<body>
<form id="frmMain" method="POST">
<input type="hidden" name="phone" id="phone" />
<input type="hidden" name="hobbys" id="hobbys" />
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
			(*) 표시는 필수 입력 사항입니다.
			<table class="headLeft_01" style="width: 900px; margin-left: auto; margin-right: auto">
				<tr>
					<th style="width: 150px;">서비스 약관</th>
					<td>
						<input type="checkbox" id="term_1" name="term_1" value="Y" required /> [필수]이용 약관
						<input type="checkbox" id="term_2" name="term_2" value="Y" /> [선택]마케팅 수신 동의
						<input type="checkbox" id="term_3" name="term_3" value="Y"  /> [선택]제 3자 개인 정보 제공 동의
					</td>
				</tr>
			</table>
			<table class="headLeft_01" style="width: 900px; margin-left: auto; margin-right: auto">
				<tr>
					<th style="width: 150px;">이메일(아이디*)</th>
					<td>
						<input value="ID@gmail.com" type="text" id="email" name="email" required />
						 <input type="button" value="중복 찾기" style="width:100px" id="btnId" />
						 <input type="hidden" value="인증 하기" style="width:100px" id="btnConfirm" disabled />
					</td>
				</tr>
				<tr>
					<th>비밀번호(*)</th>
					<td><input value="12345678!a" type="password" id="passwd" name="passwd" required /></td>
				</tr>
				<tr>
					<th>비밀번호 확인(*)</th>
					<td><input value="12345678!a" type="password" id="passwd_" name="passwd_" required /></td>
				</tr>
				<tr>
					<th>성명(*)</th>
					<td>
						<input value="홍길동" type="text" id="mbr_nm" name="mbr_nm" required />
						 <input type="radio" name="gender" value="M" checked /> 남
						 <input type="radio" name="gender" value="F" /> 여
					</td>
				</tr>
				<tr>
					<th>연락처(*)</th>
					<td>
						<input value="010" type="text" id="phone1" name="phone1" maxlength="3"     style="text-align:center;width:30px" required oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');" />
						 - <input value="1234" type="text" id="phone2" name="phone2" maxlength="4" style="text-align:center;width:40px" required oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');" />
						 - <input value="5678" type="text" id="phone3" name="phone3" maxlength="4" style="text-align:center;width:40px" required oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');" />
					</td>
				</tr>
				<tr>
					<th>주소(*)</th>
					<td>
						<input type="text" maxlength="5" style="text-align:center;width:50px;background-color:#F0F0F0" id="post" name="post" required readonly />
						 도로명 <input type="text" size="40" required id="addr1" name="addr1" style="background-color:#F0F0F0" readonly />
						 <input type="hidden" id="jibunAdd" />
						 <input type="hidden" id="extraAddress" />
						 <span id="guide" style="color:#999; display:none"></span>
						 상세 <input value="주소2" type="text" placeholder="상세 주소" required id="addr2" name="addr2" />
						 <input type="button" value="우편번호 찾기" style="width:100px" onClick="execDaumPostcode();" /></td>
				</tr>
				<tr>
					<th>취미</th>
					<td>
						독서<input  type="checkbox" name="hobby" id="hobby1" />
						 운동<input type="checkbox" name="hobby" id="hobby2" />
						 영화<input type="checkbox" name="hobby" id="hobby3" />
					</td>
				</tr>
				<tr>
					<th>가입 인사</th>
					<td>
						<textarea name="intro" cols="30" rows="3" placeholder="가입인사를 입력해주세요"></textarea>
					</td>
				</tr>
				<tr>
					<th>마케팅 수신 동의</th>
					<td>
						SMS <input type="checkbox" name="flg_sms" value="Y" />
						 Email <input type="checkbox" name="flg_email" value="Y" /></td>
				</tr>
				<tr>
					<td colspan="2" style="text-align:center;padding-top: 10px;padding-bottom: 10px">
						<input type="reset" value="다시 쓰기" style="width:100px"/>
						 <input type="button" value="가입 하기" style="width:100px" onClick="checkRegister();"/>
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
</body>
</html>