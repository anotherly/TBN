function functionKeyEvent(key){
	if(key == 112) shortCutF1();
	if(key == 113) shortCutF2();
	if(key == 114) shortCutF3();
	
	if(key == 117) shortCutF6();
	if(key == 118) shortCutF7();
	if(key == 119) shortCutF8();
	
	if(key == 120) shortCutF9();
	if(key == 121) shortCutF10();
	if(key == 122) shortCutF11();
}

function shortCutF1(){
	$("#REPORTMEAN_TYPE").val("4").prop("selected", true);
}

function shortCutF2(){
	$("#REPORTMEAN_TYPE").val("1").prop("selected", true);
}

function shortCutF3(){
	if(trafficFlag){
		//parent.map.removeOver~~이런식으로 고치기
		map.removeOverlayMapTypeId(kakao.maps.MapTypeId.TRAFFIC);
		trafficFlag = false;
	} else {
		map.addOverlayMapTypeId(kakao.maps.MapTypeId.TRAFFIC);
		trafficFlag = true;
	}
}

function shortCutF6(){
	var checkbox = $("input:checkbox[id=FLAG_EMERGENCY]");
	var checked = checkbox.is(":checked");
	(checked ? checkbox.prop("checked", false) : checkbox.prop("checked", true));
}

function shortCutF7(){
	var checkbox = $("input:checkbox[id=FLAG_REQUEST]");
	var checked = checkbox.is(":checked");
	(checked ? checkbox.prop("checked", false) : checkbox.prop("checked", true));
}

function shortCutF8(){
	var checkbox = $("input:checkbox[id=INFORMER_NONE]");
	var checked = checkbox.is(":checked");
	(checked ? checkbox.prop("checked", false) : checkbox.prop("checked", true));
}

function shortCutF9(){
	saveReceipt();
}

function shortCutF10(){
	var selectBox = $("#SEARCHTYPE3");
	selectBox.val("2").prop("selected", true);
	$("#SEARCH_TEXT3").focus();
	$("#SEARCH_TEXT3").val("");
}

function shortCutF11(){
	var selectBox = $("#SEARCHTYPE3");
	selectBox.val("1").prop("selected", true);
	$("#SEARCH_TEXT3").focus();
	$("#SEARCH_TEXT3").val("");
}
