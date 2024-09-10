function initPD(){
	//clickMenu('tabs2');
	tabMenu="divTodayList";
	autoLoading = false;
	isPause = false;
	onSearch = false;
	changeAutoLoadingFlag();
}

function initCaster(){
	//clickMenu('tabs2');
	tabMenu="divTodayList";
	autoLoading = false;
	isPause = false;
	onSearch = false;
	changeAutoLoadingFlag();
}

//전달사항에 기록
function writeToText(id){
	if(event.button == 2){
		var sendTextContent=$("#broad_message").val();
		$('#divMessage').click();
		var content = $("#contents"+id).text();
		var time = $("#time"+id).text();
		console.log("time: " + time);
		var informer = $("#informer_name"+id).text();
		var reportMean = $("#informer_type"+id).text();
		var reportType = $("#report_type"+id).text();
		
		var str = content+"\n"+time+" "+informer+" "+reportMean+" "+"\<"+reportType+"\>\n\n";
		sendTextContent += str;
		sendTextContent.replace(/\n/g, "<br/>");
		$("#broad_message").val(sendTextContent);
		//checkId="";
		$("#stdTab2").trigger("click");
	}
	
}

function clearText(){
	if(childwin != null && childwin != undefined){
		childwin.clearText_child();
	}
	$("#broad_message").val("");
	sendTextContent = "";
}

function setTabMenu(menu){
	tabMenu = menu;
	fnReload();
}

function childWinOpenCheck(){
	if(childwin == null || childwin.closed){
		openChildWin();
	} else {
		childwin.focus();
	}
}

function openChildWin(){
	var url = "/producer/pdPopUp.do";
	var windowName = "PD전달사항";
	var popupW = 1040; // 팝업 넓이
	var popupH = 660; // 팝업 높이
	var left = (document.body.offsetWidth/2) - (popupW/2);
		left += window.screenLeft;
	var top = (document.body.offsetHeight/2) - (popupH/2);
	
	childwin = window.open(url, windowName, 'width='+ screen.width
								+ ', height='+ screen.height 
								+ ', left=' + left
								+ ', top=' + top
								+ ', fullscreen=yes, toolbar=no, location=no, status=no, menubar=no, scrollbars=no, resizable=no');
}

//날짜,시간 - 방송페이지
function get_current_time(){ 
	var clock = currentTimeSmall();
	var yyyymmdd = currentDate("-");

	$('#'+tabMenu+'time').html(clock);
	$('#'+tabMenu+'date').html(yyyymmdd);
}

//체크박스 - 전체해제
function getCheckReset(){
	checkId="";

	var chk_listArr = $("input[id='broadcast_check']");
    for (var i=0; i < chk_listArr.length; i++) {
        chk_listArr[i].checked = this.checked;
    }
    
    //해제 체크박스 클릭 시 자동로딩 체크가 안되있다면
    if(!($("#divTodayListautoLoading").is(":checked"))){
    	console.log("해제 체크박스 클릭 시 자동로딩 체크가 안되있다면->자동로딩 시작");
		pauseFlagTo(true);
	}
    
}

function autoLoading(autoLoadingCheckBox){
	if(autoLoadingCheckBox.checked){
		$('#'+tabMenu+'reloadTime').attr('disabled',false);
	} else{
		$('#'+tabMenu+'reloadTime').attr('disabled',true);
	}
}

function check_CheckIdList(){
	var idList = checkId.split(",");
	for(i=0; i<idList.length; i++){
		console.log(idList[i]);
		var checkBox = $("#list_id" + idList[i] + " #broadcast_check");
		checkBox.prop("checked", true);
	}
}

//방송 버튼 클릭시 방송
function doBroadcast(user){
	console.log("방송 시 체크리스트 : "+chkList);
	//권한체크
	/*if(user != '3' && user != '4'){
		alert("권한이 없습니다.");
		return;
	}*/
	//자동로딩 제어
	if(isPause){
		pauseFlagTo(false);
	}
	//체크한 목록 갯수 체크
	var checkCount = $("input[name=broadcast_check]:checked").length;
	if(checkCount == 0){
		alert("방송할 목록을 선택해 주세요.");
	} else{
		//removeAllFromStudio();
		$('#divBroadList').click();
		
		var report_type_list = new Array();
		var time_list = new Array();
		var contentsList = new Array();
		var informer_type_list = new Array();
		var informer_name_list = new Array();
		var color_list = new Array();
		var background_list = new Array();		
		var idList = checkId.split(",");
		idList.splice(idList.length-1, 1);//공백제거
		//선택목록이 4개보다 많을경우 처리
		var forCnt;
		if(idList.length>4){
			forCnt=4
			for (var i = idList.length-1; i > 3; i--) {
				idList.splice(i, 1);
			}
		}else{
			forCnt = idList.length
		}
		checkId=idList;
		
		for(i=0; i<forCnt; i++){
			if(idList[i] != "" && idList[i] != " " && idList[i] != undefined){
				report_type_list[i]=$('#report_type'+idList[i]).text();
				color_list[i]=$('#report_type'+idList[i]).css("color");
				background_list[i]=$('#report_type'+idList[i]).css("background");
				if($('#broad_time'+idList[i]).val()==''){//방송시간 x
					
					var today = new Date();   

					var hours = ('0' + today.getHours()).slice(-2); 
					var minutes = ('0' + today.getMinutes()).slice(-2);
					var seconds = ('0' + today.getSeconds()).slice(-2); 

					var timeString = hours  + ':' + minutes;
					
					time_list[i]=$('#time'+idList[i]).text()+'<br/>'+timeString;
				}else{
					time_list[i]=$('#time'+idList[i]).text();
				}
				contentsList[i]=$('#contents'+idList[i]).text();
				informer_type_list[i]=$('#informer_type'+idList[i]).text();
				informer_name_list[i]=$('#informer_name'+idList[i]).text();
				var tmpId = 'list_id'+idList[i];
				//체크유지 배열에서 id값 삭제
				chkList = chkList.filter((element) => element !== tmpId);
			}
		}
		
		//$('#divBroadList').click();
		appendToStudio(contentsList, informer_type_list, report_type_list, informer_name_list,time_list,color_list,background_list);

		//자식창 스튜디오에 추가
		if(user == '3'){
			console.log("childwin: " + childwin);
			console.log(typeof(childwin.appendToStudio_child)=="function");
			childwin.appendToStudio_child(contentsList, informer_type_list, report_type_list, informer_name_list,time_list,color_list,background_list);
			childwin.focus();
		}
		
		//원래 방송상태 Flag 변경
		updateBroadTime();
		updateBroadFlagTo(user);
		//10.05 임시 버그해결
		currentPage = 1;
		var url = generateBoradcastSearchURL(currentPage);
		$("#TodayList").scrollTop(0);
		$('#broadcastList').load(url);
		checkId="";
		$('#todayAllcheck').prop("checked", false);
	}
	//전달사항 창으로 되있다면 스튜디오 창으로 이동
	$("#stdTab1").trigger("click");
}

//더블클릭 시 단일 방송
function dbcBroad (user,tagId){
	
	$("#stdioList li").each(function(i,list){
	   //console.log(i+" / "+list);
	    //빈칸 찾기
	    if($(this).children(0).text()==""){
	    	blkArea=i;
	    	flArea=0;
	    }
	});
	
	/*if(){
		blkArea=i;
	}*/
	
	console.log("더블클릭 시 단일 방송");
	$('#divBroadList').click();
	
	var report_type_list = new Array();
	var time_list = new Array();
	var contentsList = new Array();
	var informer_type_list = new Array();
	var informer_name_list = new Array();
	var color_list = new Array();
	var background_list = new Array();		
	var idList = checkId.split(",");
	
	for(i=0; i<1; i++){
		if(tagId != "" && tagId!= " " && tagId!= undefined){
			report_type_list[i]=$('#report_type'+tagId).text();
			color_list[i]=$('#report_type'+tagId).css("color");
			background_list[i]=$('#report_type'+tagId).css("background");
			time_list[i]=$('#time'+tagId).text();
			contentsList[i]=$('#contents'+tagId).text();
			informer_type_list[i]=$('#informer_type'+tagId).text();
			informer_name_list[i]=$('#informer_name'+tagId).text();
		}
	}
	
	//$('#divBroadList').click();
	
	//빈칸이 있을경우-빈칸에 채움
	//꽉찼을경우
	
	appendToStudio(contentsList, informer_type_list, report_type_list, informer_name_list,time_list,color_list,background_list);

	//자식창 스튜디오에 추가
	if(user == '3'){
		console.log("childwin: " + childwin);
		console.log(typeof(childwin.appendToStudio_child)=="function");
		childwin.appendToStudio_child(contentsList, informer_type_list, report_type_list, informer_name_list,time_list,color_list,background_list);
		childwin.focus();
	}
	
	//원래 방송상태 Flag 변경
	
	var resultTotal = ajaxMethod("/broadcast/updateBroadTime.ajax", {"idList": tagId}).result;

	var FLAG_BROD;
	
	//화면 안불러오고 아이콘 변경
	if(user == '3'){
		FLAG_BROD = "Y";
		$('#list_id'+tagId).find('img').attr("src","../images/ico_pd2.png");
	} else if(user == '4') {
		FLAG_BROD = "C";
		$('#list_id'+tagId).find('img').attr("src","../images/ico_caster2.png");
	} else {
		FLAG_BROD = "Y";
		$('#list_id'+tagId).find('img').attr("src","../images/ico_pd2.png");
		console.log("Wrong user flag");
	}
	
	var tagList=[tagId];
	
	var resultTotal = ajaxMethod("/broadcast/updateBroadTime.ajax", {"idList": tagList}).result;
	var result = ajaxMethod("/broadcast/updateBroadFlagTo.ajax", {"idList": tagList, "FLAG_BROD": FLAG_BROD}).result;
	
	//전달사항 창으로 되있다면 스튜디오 창으로 이동
	$("#stdTab1").trigger("click");
	
	//10.05 임시 버그해결
	/*currentPage = 1;
	var url = generateBoradcastSearchURL(currentPage);
	$("#TodayList").scrollTop(0);
	$('#broadcastList').load(url);
	$('#todayAllcheck').prop("checked", false);*/
}


function appendToStudio(contentList, informerTypeList, reportTypeList, informerNameList,timeList,colorList,backgroundList){
	console.log("appendToStudio 실행1");
	removeAllFromStudio();
	for(i=0; i<contentList.length; i++){
		$('#report_type'+i).text(reportTypeList[i]);
		$('#report_type'+i).css("color",colorList[i]);
		$('#report_type'+i).css("background",backgroundList[i]);
		$('#time'+i).append(timeList[i]);
		$('#contents'+i).text(contentList[i]);
		$('#informer_type'+i).text(informerTypeList[i]);
		$('#informer_name'+i).text(informerNameList[i]);
	}
}

//스튜디오 - 개별삭제
function removeEachFromStudio(num){
	if(childwin != null && childwin != undefined){
		childwin.removeEachFromStudio_child(num);
	}
	
	$('#number'+num).text("");
	$('#informer_type'+num).text("");
	$('#report_type'+num).text("");
	$('#report_type'+num).css("color","#fff");
	$('#report_type'+num).css("background","#fff");
	$('#time'+num).text("");
	$('#informer_name'+num).text("");
	$('#informer_tel'+num).text("");
	$('#contents'+num).text("");
	//console.log("childwin: " + parent.childwin);
}

//스튜디오 - 전체삭제
function removeAllFromStudio(){
	//console.log("childwin: " + parent.childwin);
	for(i=0;i<4;i++){
		removeEachFromStudio(i);
	}
}

function updateBroadTime(){
	var resultTotal = ajaxMethod("/broadcast/updateBroadTime.ajax", {"idList": checkId}).result;
}

function updateBroadFlagTo(user){
	var FLAG_BROD;
	
	if(user == '3'){
		FLAG_BROD = "Y";
	} else if(user == '4') {
		FLAG_BROD = "C";
	} else {
		FLAG_BROD = "Y";
		console.log("Wrong user flag");
	}
	var result = ajaxMethod("/broadcast/updateBroadFlagTo.ajax", {"idList": checkId, "FLAG_BROD": FLAG_BROD}).result;
}

//function cancleBroadcastToC(id){
//	if(authCode != "3"){
//		alert("권한이 없습니다.");
//		return;
//	}
//	var doubleCheck = confirm("방송을 취소하시겠습니까?");
//	if(doubleCheck){
//		var result = ajaxMethod("/broadcast/updateBroadFlagTo.ajax", {"idList": id, "FLAG_BROD": "C"}).result;
//		console.log("result: " + result);
//
//		if(result == 1){
//			var url = generateBoradcastSearchURL(currentPage);
//			$("#TodayList").scrollTop(0);
//			$('#broadcastList').load(url);
//			$('#todayAllcheck').prop("checked", false);
//		} else{
//			alert("Error: 잠시 후 다시 시도 해 주세요.");
//		}
//	}else{
//		return;
//	}
//}

function cancleBroadcastToN(id){
	/*if(authCode != "4" && authCode != "3"){
		alert("권한이 없습니다.");
		return;
	}*/
	var doubleCheck = confirm("방송을 취소하시겠습니까?");
	if(doubleCheck){
		var result = ajaxMethod("/broadcast/updateBroadFlagTo.ajax", {"idList": id, "FLAG_BROD": "N"}).result;
		console.log("result: " + result);
		
		if(result >= 1){
			var url = generateBoradcastSearchURL(currentPage);
			$("#TodayList").scrollTop(0);
			$('#broadcastList').load(url);
			$('#todayAllcheck').prop("checked", false);
		} else{
			alert("Error: 잠시 후 다시 시도 해 주세요.");
		}
	}else{
		return;
	}
}



//전달사항 - 보내기
function sendMessageToCaster(){
	console.log("자식창 여부: " + typeof(childwin));
	console.log("자식창 여부: " + childwin.closed);
	
	if(parent.childwin == null || parent.childwin.closed){
		parent.childwin = window.open('/producer/pdPopUp.do'
										,'studio'
										,'width=1080 height=680 toolbar=no status=no directories=no scrollbars=0 location=no resizable=no menubar=no screenX=500 left=1500 screenY=10 top=10');
	} else {
		parent.childwin.focus();
	}
	var msgFromPD = document.getElementById("broad_message").value;
	console.log("msgFromPD: " + msgFromPD);
	parent.childwin.getMsgFromPD(msgFromPD);
}



function getMsgFromPD(msgFromPD){
	console.log("getMsgFromPD함수 실행");
	$('#broad_message').val(msgFromPD);
}

function removeMsgFromPD(){
	document.getElementById("broad_message").value = "";
}

function getImsPopUp(){
	var url = "/producer/broadIms.do";
	var windowName = "TBN한국교통방송";
	var popupW = 1600; // 팝업 넓이
	var popupH = 850; // 팝업 높이
	var left = (document.body.offsetWidth/2) - (popupW/2);
		left += window.screenLeft;
	var top = (document.body.offsetHeight/2) - (popupH/2);

	popObj = window
			.open(
				url,
				windowName,
					'width='+ popupW
				+ ', height='+ popupH
				+ ', left=' + left
				+ ', top=' + top
				+ ', toolbar=no, location=no, status=no, menubar=no, scrollbars=no, resizable=no');

}

function fnReload(str){
	currentPage = 1;
	if(tabMenu=="divTodayList"){
		$('#broadcastList').load('/broadcast/selectBroadcastList.do'
					, {"RECEIPT_DAY":todaysDate, "startRow":currentPage, "AREA_CODE":lgnArea});
	} else if(tabMenu == "searchBroadcastList"){
		searchBroadcastList();
	} else if(tabMenu == "imsList"){
		//$('#broadcastList').load('/broadcast/selectImsList.do');
		//$('#broadcastList').load('/broadcast/selectImsList.do');
	}
}

//PD&캐스터 화면에서 검색 버튼 클릭시
function searchBroadcastList(){
	if(autoLoading){
		pauseFlagTo(true);
		onSearch = true;
	}
	currentPage = 1;
	
	var searchVO = generateBoradcastSearchVO();
	var totalAjax = ajaxMethod("/braodcast/countSearchBroadcastList.ajax", searchVO);
	totalPage = totalAjax.totalPage;
	$("#resultListTotal_broad span").text(totalAjax.totalSize);
	
	if(totalAjax.totalSize == 0){
		$("#broadcastList").load("/receipt/noResult.do");
	} else{
		var url = generateBoradcastSearchURL(currentPage);
		$("#TodayList").scrollTop(0);
		$("#broadcastList").load(url);
	}
	tabMenu = "searchBroadcastList";
}

function generateBoradcastSearchURL(crtPage){
	
	todaysDate = currentDate("");
	
	var REPORT_TYPE = $("#REPORT_TYPE").val();
	var REPORT_TYPE2 = $("#REPORT_TYPE2").val();
	var AREA_CODE = $("#AREA_CODE").val();
	var AREA_CODE = lgnArea;
	if($("#AREA_CODE") != undefined){
		AREA_CODE = $("#AREA_CODE").val();
	}
	var	AREA_CODE_SUB = '';
	var INDIVIDUAL_TYPE = $("#INDIVIDUAL_TYPE").val();
	var	FLAG_BROD = $("#FLAG_BROD").val();
	var CONTENT = $("#CONTENT").val();
	var FLAG_IMPORTANT = $("#FLAG_IMPORTANT").val();
	//var RECEIPT_DAY = "20211006";
	
	var url = "/broadcast/searchBroadcastList.do?REPORT_TYPE="+REPORT_TYPE
												+'&RECEIPT_DAY='+todaysDate
												+'&REPORT_TYPE2='+REPORT_TYPE2
												+'&AREA_CODE='+AREA_CODE
												+'&AREA_CODE_SUB='+AREA_CODE_SUB
												+'&INDIVIDUAL_TYPE='+INDIVIDUAL_TYPE
												+'&FLAG_BROD='+FLAG_BROD
												+'&CONTENT='+CONTENT
												+'&FLAG_IMPORTANT='+FLAG_IMPORTANT
												+'&startRow='+crtPage;
	return url;
}

function generateBoradcastSearchVO(){
	var AREA_CODE = lgnArea;
	var REPORT_TYPE = $("#REPORT_TYPE").val();
	var REPORT_TYPE2 = $("#REPORT_TYPE2").val();
	if($("#AREA_CODE") != undefined){
		AREA_CODE = $("#AREA_CODE").val();
	}
	var	AREA_CODE_SUB = '';
	var INDIVIDUAL_TYPE = $("#INDIVIDUAL_TYPE").val();
	var	FLAG_BROD = $("#FLAG_BROD").val();
	var FLAG_IMPORTANT = $("#FLAG_IMPORTANT").val();
	var CONTENT = $("#CONTENT").val();
	//var RECEIPT_DAY = "20211006";
	
	var searchVO = {
			"REPORT_TYPE":REPORT_TYPE
			, "REPORT_TYPE2":REPORT_TYPE2
			, "RECEIPT_DAY":todaysDate
			, "AREA_CODE":AREA_CODE
			, "AREA_CODE_SUB":AREA_CODE_SUB
			, "INDIVIDUAL_TYPE":INDIVIDUAL_TYPE
			, "FLAG_BROD":FLAG_BROD
			, "FLAG_IMPORTANT":FLAG_IMPORTANT
			, "CONTENT":CONTENT
			, "startRow":currentPage
	};
	return searchVO;
}















