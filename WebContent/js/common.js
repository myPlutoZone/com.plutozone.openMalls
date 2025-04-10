// String("), Char('), Number, Boolean(true, false), Undefined

function commaValue(object){
	var value		= object.value;
	value			= value.replaceAll(",", "");
	value			= value.replace(/\B(?=(\d{3})+(?!\d))/g, ",");
	object.value	= value;
}

/**
 * @author pluto#plutozone.com
 * @since 2024-08-26
 *
 * <p>DESCRIPTION:
 * <p>IMPORTANT:</p>
 */
function setCookie(cookieName, value, days) {
	// 설정 일수만큼 현재시간에 만료값으로 지정
	var exdate = new Date();
	exdate.setDate(exdate.getDate() + days);
	
	var cookieValue = escape(value) + "; path=/" + ((days == null) ? '' : '; expires=' + exdate.toUTCString());
	document.cookie = cookieName + '=' + cookieValue;
}

/**
 * @author pluto#plutozone.com
 * @since 2024-08-26
 *
 * <p>DESCRIPTION:
 * <p>IMPORTANT:</p>
 */
function getCookie(cookieName) {
	var x, y;
	var val = document.cookie.split(';');
	
	for (var i = 0; i < val.length; i++) {
		x = val[i].substr(0, val[i].indexOf('='));
		y = val[i].substr(val[i].indexOf('=') + 1);
		// 앞과 뒤의 공백 제거하기
		x = x.replace(/^\s+|\s+$/g, '');
		
		if (x == cookieName) {
			// unescape로 디코딩 후 값 리턴
			return unescape(y);
		}
	}
}