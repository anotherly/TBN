function currentDate(delim){ //DATE
	var now = new Date();
	var yyyy = now.getFullYear();
	var mm = now.getMonth() + 1;
	var dd = now.getDate();
	
	var yyyymmdd = getTimeFormat(yyyy) + delim + getTimeFormat(mm) + delim + getTimeFormat(dd);
	return yyyymmdd;
}

function yesturdate(delim){ //DATE
	var now = new Date(new Date().setDate(new Date().getDate() - 1));
	var yyyy = now.getFullYear();
	var mm = now.getMonth() + 1;
	var dd = now.getDate();
	
	var yyyymmdd = getTimeFormat(yyyy) + delim + getTimeFormat(mm) + delim + getTimeFormat(dd);
	return yyyymmdd;
}


function currentTimeSmall(){ //SMALL TIME
	var now = new Date();
	var h = now.getHours();
	var m = now.getMinutes();
	var s = now.getSeconds();
	 
	var clock = getTimeFormat((h > 12 ? h-12 : h)) 
		+ ":" + getTimeFormat(m)
		+ ":" + getTimeFormat(s) 
		+ " " + (h > 11 ? "PM" : "AM");
	return clock;
}

function currentTimeBig(){ //BIG TIME
	var startDay = new Date();
	
	var toHH = startDay.getHours() > 9? startDay.getHours():  "0" + startDay.getHours();
	var toMM = startDay.getMinutes() > 9? startDay.getMinutes():  "0" + startDay.getMinutes();
	var toSS = startDay.getSeconds() > 9? startDay.getSeconds():  "0" + startDay.getSeconds();
	
	return toHH + ":" + toMM + ":" + toSS;
}

function getTimeFormat(num){
	if(num > 9){
		return num;
	}else{
		return "0" + num; 
	}
}

function currentMonth(del){
	console.log("currentMonth");

	var now = new Date();
	console.log("now: " + now);
	var yyyy = now.getFullYear();
	console.log("yyyy: " + yyyy);
	var mm = now.getMonth() + 1;
	console.log("mm: " + mm);
	var yyyymm = getTimeFormat(yyyy) +del+ getTimeFormat(mm);
	console.log("yyyymm: " + yyyymm);

	return yyyymm;
}

function lastMonth(del){
	var date = new Date();
	var firstDayOfMonth = new Date( date.getFullYear(), date.getMonth() , 1 );
	var lastMonth = new Date ( firstDayOfMonth.setDate( firstDayOfMonth.getDate() - 1 ) );
	var year = lastMonth.getFullYear();
	var yyyymm = getTimeFormat(year) +del+ getTimeFormat(lastMonth.getMonth() + 1);
	return yyyymm;
}

function twoMonthAgo(del){
	var date = new Date();
	var firstDayOfLastMonth = new Date( date.getFullYear(), date.getMonth()-1 , 1 );
	var twoMonthAgo = new Date ( firstDayOfLastMonth.setDate( firstDayOfLastMonth.getDate() - 1 ) );
	var year = twoMonthAgo.getFullYear();
	var yyyymm = getTimeFormat(year) +del+ getTimeFormat(twoMonthAgo.getMonth() + 1);
	return yyyymm;
}






















