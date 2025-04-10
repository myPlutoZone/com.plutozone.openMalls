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
 *				: [20240802100000][pluto#plutozone.com][CREATE: Initial Release]
 */
%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ page info="/WEB-INF/view/backoffice/users/member/modifyForm.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/include/console/header.jsp" %>
	<link rel="stylesheet" type="text/css" title="common stylesheet" href="/css/layoutSubmain.css" />
	<link rel="stylesheet" type="text/css" title="common stylesheet" href="/css/table.css" />
	<script type="text/javascript" src="/js/console.js"></script>
	<style></style>
	<script></script>
</head>
<body>
<form id="frmMain" method="POST" action="/console/users/member/modifyProc.web">
<input type="hidden" name="seq_mbr" value="${memberDto.seq_mbr}"/>
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
			(*) 표시는 필수 입력 사항입니다.
			<table class="headLeft_01" style="width: 900px; margin-left: auto; margin-right: auto">
				<tr>
					<th style="width: 150px;">성명</th>
					<td>
						${memberDto.mbr_nm}
					</td>
					<th style="width: 150px;border-left:3px solid #369;">성별</th>
					<td>
					<c:choose>
						<c:when test="${memberDto.gender == 'M'}">남</c:when>
						<c:otherwise>여</c:otherwise>
					</c:choose>
					</td>
				</tr>
				<tr>
					<th style="width: 150px;">IP</th>
					<td>
						${memberDto.last_ip}
					</td>
					<th style="width: 150px;border-left:3px solid #369;">마지막 접속 일시</th>
					<td>
						${memberDto.last_dt}
					</td>
				</tr>
				<tr>
					<th>이메일(아이디)</th>
					<td colspan="3">
						${memberDto.email} / 
						<select name="cd_state" style="background:#F0F0F0">
							<option value="0"<c:if test="${memberDto.cd_state == 0}"> selected</c:if>>대기</option>
							<option value="1"<c:if test="${memberDto.cd_state == 1}"> selected</c:if>>사용중</option>
							<option value="2"<c:if test="${memberDto.cd_state == 2}"> selected</c:if>>반려</option>
							<option value="8"<c:if test="${memberDto.cd_state == 8}"> selected</c:if>>자퇴</option>
							<option value="9"<c:if test="${memberDto.cd_state == 9}"> selected</c:if>>강퇴</option>
						</select>
					</td>
				</tr>
				<tr>
					<th>연락처</th>
					<td colspan="3">
						${fn:split(memberDto.phone, '-')[0]} - ${fn:split(memberDto.phone, '-')[1]} - ${fn:split(memberDto.phone, '-')[2]}
					</td>
				</tr>
				<tr>
					<th>주소</th>
					<td colspan="3">
						${memberDto.post} ${memberDto.addr1} ${memberDto.addr2}
					</td>
				</tr>
				<tr>
					<th>취미</th>
					<td colspan="3">
						독서<input type="checkbox"<c:if test="${memberDto.hobbys.substring(0, 1) == 'Y'}"> checked</c:if>/>
						운동<input type="checkbox"<c:if test="${memberDto.hobbys.substring(1, 2) == 'Y'}"> checked</c:if>/>
						영화<input type="checkbox"<c:if test="${memberDto.hobbys.substring(2, 3) == 'Y'}"> checked</c:if>/>
					</td>
				</tr>
				<tr>
					<th>마케팅 수신 동의</th>
					<td colspan="3">
						SMS <input type="checkbox"<c:if test="${memberDto.flg_sms == 'Y'}"> checked</c:if>/>
						Email <input type="checkbox"<c:if test="${memberDto.flg_email == 'Y'}"> checked</c:if>/>
					</td>
				</tr>
				<tr>
					<th>소개</th>
					<td colspan="3">
						${memberDto.intro}
					</td>
				</tr>
				<tr>
					<td colspan="4" style="text-align:center; padding-top: 10px;padding-bottom: 10px">
						<input type="submit" value="수정 하기" style="width:100px" />
					</td>
				</tr>
			</table>
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