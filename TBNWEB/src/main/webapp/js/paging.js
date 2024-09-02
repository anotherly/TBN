function getListByScrollPaging(crtPage, date, totalPage){
	var url="";
	var data;
	
	if(tabMenu === "receivedStatusSearch" || tabMenu === "fullReceivedHistorySearch"){
		url = "/receipt/searchFullStatus.do?SEARCH_TAB="+tabMenu+"&startRow="+crtPage;
		data = generateSearchVO(tabMenu);
		
	} else if(tabMenu === "divTodayList"){
		url = "/broadcast/selectBroadcastList.do";
		data = {"RECEIPT_DAY":date, "startRow":crtPage, "AREA_CODE":lgnArea};
		
	} else if(tabMenu === "searchBroadcastList"){
		url = "/broadcast/searchBroadcastList.do";
		data = generateBoradcastSearchVO();
		
	} else if(tabMenu === "receivedStatus" || tabMenu === "fullReceivedHistory"){
		url = "/receipt/receivedStatusList.do";
		data = {"RECEIPT_DAY" : date, "startRow" : crtPage, "AREA_CODE":opener.lgnArea};
	}
	
	if(crtPage <= totalPage){
		$.ajax({
			type: "POST", 
			url: url,
			data: data,
			success: function(result) {
				$("#broadcastList").append(result);
			},
			error: function(result) {
				alert("에러가 발생하였습니다.");
			}
		});
	}
}