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
 *				: [20240730145700][pluto#plutozone.com][CREATE: Initial Release]
 */
%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ page info="/WEB-INF/view/backoffice/statistics/index.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/include/console/header.jsp" %>
	<link rel="stylesheet" type="text/css" title="common stylesheet" href="/css/layoutSubmain.css" />
	<style></style>
	<!-- Google Charts 라이브러리 로드 -->
	<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
	<script>
		// Google Charts 라이브러리 로드 완료 후 실행
		google.charts.load('current', {'packages':['corechart']});
		google.charts.setOnLoadCallback(drawCharts);
		
		function drawCharts() {
			
			// 차트 1 - 세로 막대 그래프
			/*
			var data1 = google.visualization.arrayToDataTable([
				['도시', '인구'],
				['서울', 9720846],
				['부산', 3404423],
				['인천', 2922274],
				['대구', 2459744],
				['대전', 1539470]
			]);
			
			var options1 = {
				title: '대한민국 주요 도시의 인구',
				width: 400,
				height: 300
			};
			
			var chart1 = new google.visualization.ColumnChart(document.getElementById('chart_div1'));
			chart1.draw(data1, options1);
			*/
			// 차트 2 - 파이 차트
			var data2 = google.visualization.arrayToDataTable([
				['지역', '회원수'],
				<c:choose>
					<c:when test="${empty listMemberRegion}">
						/* 데이터 없음*/
					</c:when>
					<c:otherwise>
						/* [2024-08-13][pluto#plutozone.com][TODO-개선: 마지막 열에서는 ',' 제거해야 함] */
						<c:forEach items="${listMemberRegion}" var="list">
							['${list.region}', ${list.sumRegion}],
						</c:forEach>
					</c:otherwise>
				</c:choose>
				/*
				['서울', 30],
				['경기', 50],
				['인천', 20],
				['부산', 40],
				['제주', 0]
				*/
			]);
			
			var options2 = {
				title: '지역별 회원 비율(수)',
				width: 500,
				height: 400
			};
			
			var chart2 = new google.visualization.PieChart(document.getElementById('chart_div2'));
			chart2.draw(data2, options2);
			/*
			var data2 = google.visualization.arrayToDataTable([
				['과일', '판매량'],
				['사과', 30],
				['바나나', 50],
				['딸기', 20],
				['오렌지', 40]
			]);
			
			var options2 = {
				title: '과일 판매 비율',
				width: 400,
				height: 300
			};
			
			var chart2 = new google.visualization.PieChart(document.getElementById('chart_div2'));
			chart2.draw(data2, options2);
			*/
			
			// 차트 3 - 라인 차트
			/*
			var data3 = google.visualization.arrayToDataTable([
				['연도', '매출', '비용'],
				['2019', 1000, 600],
				['2020', 1200, 700],
				['2021', 1400, 800],
				['2022', 1600, 900]
			]);
			
			var options3 = {
				title: '매출과 비용 추이',
				width: 400,
				height: 300
			};
			
			var chart3 = new google.visualization.LineChart(document.getElementById('chart_div3'));
			chart3.draw(data3, options3);
			*/
			
			// 차트 4 - 가로 막대 그래프
			/*
			var data4 = google.visualization.arrayToDataTable([
				['도시', '인구'],
				['서울', 9720846],
				['부산', 3404423],
				['인천', 2922274],
				['대구', 2459744],
				['대전', 1539470]
			]);
			
			var options4 = {
				title: '대한민국 주요 도시의 인구',
				width: 400,
				height: 300,
				hAxis: { title: '인구' }
			};
			
			var chart4 = new google.visualization.BarChart(document.getElementById('chart_div4'));
			chart4.draw(data4, options4);
			*/
			
			// 차트 5 - 영역 차트
			/*
			var data5 = google.visualization.arrayToDataTable([
				['연도', '매출', '비용'],
				['2019', 1000, 600],
				['2020', 1200, 700],
				['2021', 1400, 800],
				['2022', 1600, 900]
			]);
			
			var options5 = {
				title: '매출과 비용 추이',
				width: 400,
				height: 300,
				isStacked: true
			};
			
			var chart5 = new google.visualization.AreaChart(document.getElementById('chart_div5'));
			chart5.draw(data2, options5);
			*/
			
			// [2023-08-23][pluto#plutozone.com][TODO-확장: 상품 원가와 판매 가격을 통해 매출 통계]
			// 차트 6 - 스택된 세로 막대 그래프
			var data6 = google.visualization.arrayToDataTable([
				['월', '매출', '원가', '이익'],
				['3', 35952, 33600, 2352],
				['4', 96354, 95400, 954]
			]);
			
			var options6 = {
				title: '[예정]매출과 원가를 통한 판매 이익 추이',
				width: 500,
				height: 400,
				isStacked: true
			};
			
			var chart6 = new google.visualization.ColumnChart(document.getElementById('chart_div6'));
			chart6.draw(data6, options6);
			
			// 차트 7 - 산점도 그래프
			/*
			var data7 = google.visualization.arrayToDataTable([
				['키', '몸무게'],
				[165, 68],
				[170, 75],
				[175, 80],
				[180, 88],
				[185, 95]
			]);
			
			var options7 = {
				title: '키와 몸무게의 관계',
				width: 400,
				height: 300,
				hAxis: { title: '키(cm)' },
				vAxis: { title: '몸무게(kg)' }
			};
			
			var chart7 = new google.visualization.ScatterChart(document.getElementById('chart_div7'));
			chart7.draw(data7, options7);
			*/
		}
	</script>
</head>
<body>
<form id="frmMain" method="POST">
<div class="container">
	<header>
		<%@ include file="/include/console/top.jsp" %>
	</header>
	<nav>
		<%@ include file="/include/console/gnb.jsp" %>
	</nav>
	<section class="content">
		<nav>
			<%@ include file="/include/console/lnbStatistics.jsp" %>
		</nav>
		<article class="txtCenter">
			<!-- 그래프를 그릴 요소들 -->
			<div id="chart_div1" style="float: left;"></div>
			<div id="chart_div2" style="float: left;"></div>
			<div id="chart_div3" style="float: left;"></div>
			<div id="chart_div4" style="float: left;"></div>
			<div id="chart_div5" style="float: left;"></div>
			<div id="chart_div6" style="float: left;"></div>
			<div id="chart_div7" style="float: left;"></div>
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