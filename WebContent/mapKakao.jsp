<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ page info="/mapKakao.jsp" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title></title>
	<%@ include file="/security.jsp" %>
	<script type="text/javascript" src="https://dapi.kakao.com/v2/maps/sdk.js?appkey=<%=mapKakao_appkey%>"></script>
	<script>
		// 상기 appKey는 로컬 테스트용으므로 서버용으로 별도 발급 필요
		var map;
		
		window.onload = function () {
		
			var container = document.getElementById("map");
			var options = {
				center: new kakao.maps.LatLng(33.450701, 126.570667),
				level: 5
			};
			
			map = new kakao.maps.Map(container, options);
			
			currentLocation();
		}
		
		// [출처] https://itwithruilan.tistory.com/105
		function currentLocation() {
			// HTML5의 geolocation으로 사용할 수 있는지 확인
			if (navigator.geolocation) {
				
				// GeoLocation을 이용해서 접속 위치 확인
				navigator.geolocation.getCurrentPosition(function(position) {
					
					var lat = position.coords.latitude,		// 위도
						lon = position.coords.longitude;	// 경도
						
					var locPosition = new kakao.maps.LatLng(lat, lon);							// 마커가 표시될 위치를 geolocation으로 얻어온 좌표로 생성
					var message = "<div style='padding: 5px; font-size: 9pt;'>현위치</div>";	// 인포윈도우에 표시될 내용
					
					// 현재 접속 위치로 이동
					// map.setCenter(locPosition);
					
					// 1. 현재 위치의 마커와 인포 윈도우를 표시
					displayMarker("", locPosition, message);
					
					// 2. 한전아트센터(스포츠클럽)의 마커와 인포 인포 윈도우를 표시
					// 좌표 찾기: https://apis.map.kakao.com/web/sample/addMapClickEventWithMarker/
					lat = 37.48564239975204;
					lon = 127.02852139658732;
					locPosition = new kakao.maps.LatLng(lat, lon)
					message = "<div style='padding: 5px; font-size: 9pt;'>한전아트센터(스포츠클럽)</div>";
					displayMarker("artCenter", locPosition, message);
				});
			}
			// HTML5의 GeoLocation을 사용할 수 없을 때 마커 표시 위치와 인포윈도우 내용을 설정
			else {
				
				var locPosition = new kakao.maps.LatLng(37.4812845080678, 126.952713197762),
				message = '현재 위치를 알 수 없어 기본 위치로 이동합니다.'
				
				currentLatLon['lat'] = 33.450701
				currentLatLon['lon'] = 126.570667
				
				// 마커와 인포윈도우를 표시
				// displayMarker("", locPosition, message);
			}
			return true
		}
		
		// [출처] https://itwithruilan.tistory.com/105
		function displayMarker(image, locPosition, message) {
		
			if (image == "artCenter") {
				var imageSize	= new kakao.maps.Size(146, 22);
				var markerImage	= new kakao.maps.MarkerImage("/image/sportCenter.png", imageSize);
			}
			else {
				var imageSize	= new kakao.maps.Size(15, 15);
				var markerImage	= new kakao.maps.MarkerImage("/image/marker.png", imageSize);
			}
			
			// 마커 생성
			var marker = new kakao.maps.Marker({
				map: map, 
				position: locPosition, 
				image : markerImage, 
			});
			
			// 인포 윈도우에 표시할 내용
			var iwContent = message, iwRemoveable = true;
			
			// 인포 윈도우를 생성
			var infowindow = new kakao.maps.InfoWindow({
				content : iwContent,
				removable : iwRemoveable
			});
			
			// 인포 윈도우를 마커 위에 표시
			infowindow.open(map, marker);
			
			// 지도 중심 좌표를 접속 위치로 변경
			map.setCenter(locPosition);
		}
	</script>
</head>
<body>
	<div id="map" style="border: 1px solid gray; width: 50%; height: 400px;"></div>
</body>
</html>