function startAutoLoading(){
	console.log("startAutoLoading");
	var area_code;
		tabMenu === "divTodayList" || tabMenu === "searchBroadcastList"  
			? area_code = lgnArea 
			: area_code = opener.lgnArea;
	var todaysDate = currentDate("");
	currentPage = 1;
	var totalSize;
	var url = "";
	if(area_code=='080'){
		area_code=''
	}
	
	//220905 검색어 입력한거 있다면 자동로딩 시 검색 로직 타도록 수정
	//pd 캐스터 화면
	if(tabMenu === "divTodayList" || tabMenu === "searchBroadcastList"){
		
		if(	
				$("#REPORT_TYPE").val()==''
				&&$("#REPORT_TYPE2").val()==''
				&&$("#INDIVIDUAL_TYPE").val()==''
				&&$("#FLAG_BROD").val()==''
				&&$("#FLAG_IMPORTANT").val()==''
				&&$("#AREA_CODE").val()==area_code
				&&$("#CONTENT").val()==''
			){
/////////////////없으면 오토로딩
			url = "/broadcast/selectBroadcastList.do";
			
			var totalAjax = ajaxMethod("/broadcast/countBroadcastList.ajax", {"RECEIPT_DAY" : todaysDate, "startRow" : currentPage, "AREA_CODE":lgnArea});
			
			totalSize = totalAjax.totalSize;
			totalSize == 0 ? 
					$("#broadcastList").load("/receipt/noResult.do") 
					: $("#broadcastList").load(url, {"RECEIPT_DAY" : todaysDate, "startRow" : currentPage, "AREA_CODE":lgnArea});
/////////////////			
		}else{
//////////////////검색조건이 있다면 검색으로
			searchBroadcastList();
			$(divTodayListautoLoading).trigger("click");
		}
	//제보접수 화면			
	} else {
		if(
				  $("#REPORT_TYPE").val()==''
				&&$("#REPORT_TYPE2").val()==''
				&&$("#REPORTMEAN_TYPE").val()==''
				&&$("#FLAG_SEND").val()==''
				&&$("#FLAG_BROD").val()==''
				&&$("#FLAG_IMPORTANT").val()==''
				&&$("#AREA_CODE").val()==area_code
				//내용이 and or 로 2칸으로 변경됬기때문에 이거 바꿔줘야함...ㅡㅡ
				&&$("#CONTENT1").val()==''
				&&$("#CONTENT2").val()==''
				
		){
//////////////////////없으면 오토로딩
			url = "/receipt/receivedStatusList.do";

			var totalAjax = ajaxMethod("/receipt/getTotalPage.ajax", {"RECEIPT_DAY" : todaysDate, "startRow" : currentPage, "AREA_CODE":opener.lgnArea});
			
			totalSize = totalAjax.totalSize;
			totalSize == 0 ? 
					$("#broadcastList").load("/receipt/noResult.do") 
					: $("#broadcastList").load(url, {"RECEIPT_DAY" : todaysDate, "startRow" : currentPage, "AREA_CODE":opener.lgnArea});
////////////////////////
			
		}else{
			//검색조건이 있다면 검색으로
			//fullReceivedHistoryautoLoading
			if(tabMenu=="receivedStatus"){
				searchFullStatus('receivedStatusSearch');
				$(receivedStatusautoLoading).trigger("click");
			}
		}
	}
	
	get_current_time();
	$("#resultListTotal span").text(totalSize);
}

function changeAutoLoadingFlag(){
	console.log("changeAutoLoadingFlag");

	var menu = getTabMenuForAutoLoading();
	if(autoLoading){
		autoLoading = false;
		document.getElementById(menu + "reloadTime").disabled = true;
		clearInterval(autoLoadingFunction);
	} else{
		autoLoading = true;
		document.getElementById(menu + "reloadTime").disabled = false;
		var selectBox = document.getElementById(menu+"reloadTime");
		interval = selectBox.options[selectBox.selectedIndex].value;
		autoLoadingFunction = setInterval(() => startAutoLoading(), interval*1000);
	}
}

function getTabMenuForAutoLoading(){
	console.log("getTabMenuForAutoLoading");

	var menu;
	if(tabMenu === "receivedStatusSearch" || tabMenu === "receivedStatus"){
		menu = "receivedStatus";
	} else if(tabMenu === "fullReceivedHistorySearch" || tabMenu === "fullReceivedHistory"){
		menu = "fullReceivedHistory";
	} else if(tabMenu === "divTodayList" || tabMenu === "searchBroadcastList"){
		menu = "divTodayList";
	} else{
		menu = "imsFromUtic";
	}
	return menu;
}

function changeIntervalTime(selectBox){
	console.log("changeIntervalTime");

	interval = selectBox.options[selectBox.selectedIndex].value;
	if(autoLoading){
		clearInterval(autoLoadingFunction);
		autoLoadingFunction = setInterval(() => startAutoLoading(), interval*100);
	}
}

function changeUIByAutoLoadingFlag(){
	console.log("changeUIByAutoLoadingFlag");

	var menu = getTabMenuForAutoLoading();
	if(autoLoading){
		document.getElementById(menu+"autoLoading").checked = true;
		document.getElementById(menu+"reloadTime").disabled = false;
	} else{
		document.getElementById(menu+"autoLoading").checked = false;
		document.getElementById(menu+"reloadTime").disabled = true;
	}
}

function pauseFlagTo(flag){
	console.log("pauseFlagTo");

	isPause = flag;
	changeAutoLoadingFlag();
	changeUIByAutoLoadingFlag();
}