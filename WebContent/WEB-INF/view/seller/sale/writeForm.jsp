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
 *				: [20240808145500][pluto#plutozone.com][CREATE: Initial Release]
 */
%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ page info="/WEB-INF/view/seller/sale/writeForm.jsp" %>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/include/seller/header.jsp" %>
	<link rel="stylesheet" type="text/css" title="common stylesheet" href="/css/layoutSubmain.css" />
	<link rel="stylesheet" type="text/css" title="common stylesheet" href="/css/table.css" />
	<style></style>
	<link rel="stylesheet" href="http://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
	<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
	<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
	<script type="text/javascript" src="/js/package/tinymce/tinymce.min.js"></script>
	<script type="text/javascript" src="/js/package/tinymce.js"></script>
	<script>
		var countFile = 1;
		window.onload = function () {
			// HTML Editor
			tinymce.init({selector:'textarea'});
		}
		
		function removeFile(thiz){
			
			var inputIndex = $(thiz).attr("data-value");
			
			// fileName = eval("document.getElementById('files_" + inputIndex + "').value");
			if (inputIndex != 0) {
				$(thiz).remove();
				$("#files_" + inputIndex).remove();
				$("#previewFile .previewFile_" + inputIndex).remove();
				/*
				$(thiz).hide();
				eval("document.getElementById('files_" + inputIndex + "').value = ''");
				$("#previewFile .previewFile_" + inputIndex).hide();
				*/
			}
		}
		
		function previewFile(thiz){
			
			$("#previewFile").show();
			
			var inputIndex = $(thiz).attr("data-value");
			
			// multiple 일때 thiz.files.length는 2 이상 가능(이하 로직은 multiple is false일 때) + 파일 삭제 시 0
			if (thiz.files.length == 0) {
				$("#previewFile .previewFile_" + inputIndex).hide();
			}
			else if (thiz.files && thiz.files[0]) {
				// [2024-10-29][pluto#plutozone.com][TODO-확장: 최대 파일 사이즈는 서비스에서 설정]
				if (thiz.files[0].size > 5 * 1024 * 1024) {
					alert("첨부파일은 5MB 이내로 등록 가능합니다!");
					$(thiz).val("");
					$("#previewFile").hide();
				}
				else {
					var reader = new FileReader();
					reader.onload = function(e) {
						$("#previewFile .previewFile_" + inputIndex).attr("src", e.target.result);
						$("#previewFile .previewFile_" + inputIndex).show();
					};
					reader.readAsDataURL(thiz.files[0]);
					
					if (inputIndex != 0) {
						var removeFile = " <input type='button' value='삭제' data-value='" + inputIndex + "' style='width: 50px;' onclick='removeFile(this);' style='display: none;' />";
						$(thiz).after(removeFile);
					}
				}
			}
		}
		
		function addFile(thiz) {
			var inputFile = "<input type='file' id='files_"
									+ countFile + "' name='files["
									+ countFile + "]' onchange='previewFile(this);' data-value='"
									+ countFile + "' accept='image/png, image/jpeg, image/gif' />";
			$("#addFile").append(inputFile);
			
			var previewFile = "<img class='previewFile_"
									+ countFile + "' style='margin-top: 3px; border: 1px solid #F0F0F0; width: 50px; height: 50px;' />";
			$("#previewFile").append(previewFile);
			
			countFile++
		}
		
		$(function(){
			$("#dt_sale_start").datepicker({dateFormat:"yy-mm-dd"});
		})
		
		$(function(){
			$("#dt_sale_end").datepicker({dateFormat:"yy-mm-dd"});
		})
		
		function writeProc() {
			var frmMain = document.getElementById("frmMain");
			
			if (document.getElementById("sle_nm").value == ""
					|| document.getElementById("cd_where_ctg").value == "0"
					|| document.getElementById("price_sale").value == ""
					|| document.getElementById("dt_sale_start").value == ""
					|| document.getElementById("dt_sale_end").value == ""
					|| document.getElementById("cd_state_sale").value == "0"
					|| tinymce.activeEditor.getContent() == "") {
				alert("필수 항목을 입력하세요!");
				return;
			}
			document.getElementById("price_sale").value = document.getElementById("price_sale").value.replaceAll(",", "");
			
			frmMain.action="/seller/sale/writeProc.web";
			frmMain.submit();
		}
	</script>
</head>
<body>
<form id="frmMain" method="POST" enctype="multipart/form-data">
<input type="hidden" id="seq_sle"	name="seq_sle"	value="${saleDto.seq_sle}"/>
<div class="container">
	<header>
		<%@ include file="/include/seller/top.jsp" %>
	</header>
	<nav>
		<%@ include file="/include/seller/gnb.jsp" %>
	</nav>
	<section class="content">
		<article class="txtCenter">
			<table class="headLeft_01" style="width: 900px; margin-left: auto; margin-right: auto">
				<tr>
					<th style="width: 150px;">상품명</th>
					<td>
						<c:choose>
							<c:when test="${empty listPrd}">
								판매 가능한 상품이 없습니다.
							</c:when>
							<c:otherwise>
							<select style="width: 80%" id="seq_prd" name="seq_prd">
								<c:forEach items="${listPrd}" var="listPrd">
									<option value="${listPrd.seq_prd}">${listPrd.prd_nm}</option>
								</c:forEach>
							</select>
							</c:otherwise>
						</c:choose>
					</td>
				</tr>
				<tr>
					<th style="width: 150px;">판매명</th>
					<td>
						<input style="width: 80%" type="text" name="sle_nm" id="sle_nm" required/>
					</td>
				</tr>
				<tr>
					<th>카테고리</th>
					<td>
						<select id="cd_where_ctg" name="cd_where_ctg" required>
							<option value="1">도서</option>
							<option value="2">가전 제품</option>
							<option value="3">컴퓨터 용품</option>
						</select>
					</td>
				</tr>
				<tr>
					<th>설명</th>
					<td>
						<textarea name="desces" id="desces" style="width: 650px; height: 200px;" maxlength="1000" required></textarea>
					</td>
				</tr>
				<tr>
					<th>판매 가격</th>
					<td>
						<input type="text" id="price_sale" name="price_sale" style="width: 100px; text-align: right;" onkeyup="commaValue(this);" required/>원
					</td>
				</tr>
				<tr>
					<th>판매 상태</th>
					<td>
						<select name="cd_state_sale" id="cd_state_sale" required>
							<option value="1">판매중</option>
							<option value="2">판매 중지</option>
							<option value="9">품절</option>
						</select>
					</td>
				</tr>
				<tr>
					<th>이미지(대표는 필수)</th>
					<td>
						<!-- [2024-10-29][pluto#plutozone.com][TODO-확장: 파일 확장자는 서비스에서 설정] -->
						<div id="addFile">
							<input type="file" id="files_0" name="files[0]" onchange="previewFile(this);" data-value="0" accept="image/png, image/jpeg, image/gif" required />
						</div>
						<div>
							<input type="button" value="파일 추가" onClick="addFile(this);" style="margin-top: 3px; width: 100px;">
						</div>
						<div id="previewFile" style="margin-top: 3px; display: inline;">
							<img class="previewFile_0" style="margin-top: 3px; border: 1px solid #F0F0F0; width: 75px; height: 75px;">
						</div>
					</td>
				</tr>
				<tr>
					<th>판매 시작일</th>
					<td>
						<input type="text" id="dt_sale_start" name="dt_sale_start" required autocomplete="off" />
					</td>
				</tr>
				<tr>
					<th>판매 종료일</th>
					<td>
						<input type="text" id="dt_sale_end" name="dt_sale_end" required autocomplete="off" />
					</td>
				</tr>
				<tr>
					<td colspan="2" style="text-align:center;padding-top: 10px;padding-bottom: 10px">
						<input type="button" value="등록" style="width:100px" onclick="javascript:writeProc();"/>
						 <input type="button" value="목록" style="width:100px" onclick="javascript:location.href='/seller/sale/list.web';"/>
					</td>
				</tr>
			</table>
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