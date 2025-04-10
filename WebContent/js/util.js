function getTwoDigits(num) {
			return num.toString().padStart(2, "0");
		}
		
function getNow() {
	const date = new Date();
	return (
		[date.getFullYear()
			, getTwoDigits(date.getMonth() + 1)
			, getTwoDigits(date.getDate())].join("-")
		+ " " +
		[getTwoDigits(date.getHours())
			, getTwoDigits(date.getMinutes())
			, getTwoDigits(date.getSeconds())].join(":")
	);
}

// alert(getNow());
// 2024-06-24 14