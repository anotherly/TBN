
// 접수리스트 리로드
function fnReload(str){
	if(str=="auto"){
		clearTimeout(ID);
		ID=setTimeout("fnReload()", $('#'+tabMenu+'reloadTime').val()*1000);
	}else{
		if(tabMenu=="divTodayList"){
			goTodaySearch();
		}else if(tabMenu=="divIncidentList"){
			goIncidentSearch();
		}else if(tabMenu=="divCCTVList"){
			goArterySearch();
		}
		if($("input[name='"+tabMenu+"autoLoading']:checked").val()=='on'){
			clearTimeout(ID);
			ID=setTimeout("fnReload()", $('#'+tabMenu+'reloadTime').val()*1000);
		}
	}
}

//방송할 리스트 체크
function getCheckList(id){
	if(id.checked){
		if(checkCount == 4){
			alert("한번에 4개만 방송할수 있습니다.");
			id.checked = false;
		}else{
			checkId = checkId+id.value+",";	
			checkCount = checkCount+1;	
		}
		
	}else{
		checkId = checkId.replace(id.value+",","");
		checkCount = checkCount-1;
	}
	if(checkCount>0){
		if(tabMenu=="divTodayList"){
			$('#todayAllcheck').attr("checked", "checked");
		}else if(tabMenu=="divIncidentList"){
			$('#incidentAllcheck').attr("checked", "checked");
		}
	}else{
		if(tabMenu=="divTodayList"){
			$('#todayAllcheck').removeAttr("checked");
		}else if(tabMenu=="divIncidentList"){
			$('#incidentAllcheck').removeAttr("checked");
		}
	}
	
}

//방송리스트 보여주기
function appendToStudio(frm){
	console.log("appendToStudio2");
	if(parent.childwin==null || parent.childwin.closed){
		parent.childwin = window.open('<c:url value="/jsp/producer/common/studio.jsp"/>','studio','width=450 height=580 toolbar=no status=no directories=no scrollbars=0 location=no resizable=no menubar=no screenX=500 left=1500 screenY=10 top=10');
	 }else{
		parent.childwin.focus();
	}
	if(checkCount==0){
		alert("방송할 목록을 선택해 주세요.");
	}else{
		var idList = checkId.split(",");
		var contentsList = new Array();
		var informer_type_list = new Array();
		var report_type_list = new Array();
		var informer_name_list = new Array();

		for(i=0;i<idList.length;i++){
			$('#number'+i).text($('#number'+idList[i]).text());
			if(tabMenu=="divTodayList"){
				$('#informer_type'+i).text($('#informer_type'+idList[i]).text());
				informer_type_list[i]=$('#informer_type'+idList[i]).text();
			}else{
				$('#informer_type'+i).text($('#informer_type'+idList[i]).val());
				informer_type_list[i]=$('#informer_type'+idList[i]).val();
			}
			$('#report_type'+i).text('['+$('#report_type'+idList[i]).text()+']');
			$('#informer_name'+i).text($('#informer_name'+idList[i]).text());
			$('#informer_tel'+i).text($('#informer_tel'+idList[i]).val());
			$('#contents'+i).text($('#contents'+idList[i]).text());
			
			contentsList[i]=$('#contents'+idList[i]).text();
			report_type_list[i]=$('#report_type'+idList[i]).text();
			informer_name_list[i]=$('#informer_name'+idList[i]).text();
		}
		for(i=i-1;i<4;i++){
			$('#number'+i).text("");
			$('#informer_type'+i).text("");
			$('#report_type'+i).text("");
			$('#informer_name'+i).text("");
			$('#informer_tel'+i).text("");
			$('#contents'+i).text("");
		}
		
		//PD화면의 스튜디오 탭 활성화
		$('#divBroadList').click();
		//스튜디오 팝업 방송내용 display
		parent.childwin.showStudio(contentsList, informer_type_list, report_type_list, informer_name_list);
		
		//방송FLAG '방송'으로 업데이트
/* 		$('#receipt_flag').val(tabMenu);
		$('#receipt_id_list').val(idList); */
		if(tabMenu=='divTodayList'){
			document.frmTodaySearch.receipt_flag.value=tabMenu;
			document.frmTodaySearch.receipt_id_list.value=idList;
		} else if(tabMenu=='divIncidentList'){
			document.frmInciSearch.receipt_flag.value=tabMenu;
			document.frmInciSearch.receipt_id_list.value=idList;
		}
		
 	 	var options = {
			url: '<c:url value="/receipt/updateFlagBroad.do"/>',
			type: 'post',
			target: '#'+frm,
			dataType: "json",
	        success: function(res){
	            if(res.result >= 1){
	            	checkCount=0;
	            	checkId="";
	            	if(tabMenu=='divTodayList'){
	            		$('#todayAllcheck').removeAttr("checked");
	            		fnReload();
	        		} else if(tabMenu=='divIncidentList'){
	        			$('#incidentAllcheck').removeAttr("checked");
	        			fnReload();
	        		}
	            }
	        }
		};
		$('#'+frm).ajaxSubmit(options); 
	}
}

//방송취소 : 방송FLAG '비방송'으로 업데이트
function goBroadCancel(frm, id, num){
	if(confirm(num+"번 방송취소 하시겠습니까?")){
		$('#receipt_id_list').val(id);
		var options = {
			url: '<c:url value="/receipt/updateUnflagBroad.do"/>',
			type: 'post',
			target: '#'+frm,
			dataType: "json",
	        success: function(res){
	            if(res.result >= 1){
	            	if(tabMenu=='divTodayList'){
	            		$('#todayAllcheck').removeAttr("checked");
	            		fnReload();
	        		} else if(tabMenu=='divIncidentList'){
	        			$('#incidentAllcheck').removeAttr("checked");
	        			fnReload();
	        		}
	            }
	        }
		};
		$('#'+frm).ajaxSubmit(options); 
	}
}
/* function goBroadCancel(frm){
		var idList = checkId.split(",");
		$('#receipt_id_list').val(idList);
		 	var options = {
			url: '<c:url value="/receipt/updateUnflagBroad.do"/>',
			type: 'post',
			target: '#'+frm,
			dataType: "json",
	        success: function(res){
	            if(res.result >= 1){
	            	if(tabMenu=='divTodayList'){
	            		$('#todayAllcheck').removeAttr("checked");
	            		fnReload();
	        		} else if(tabMenu=='divIncidentList'){
	        			$('#incidentAllcheck').removeAttr("checked");
	        			fnReload();
	        		}
	            }
	        }
		};
		$('#'+frm).ajaxSubmit(options); 
} */

//방송리스트 지우기
 function resetBroad(){
	for(i=0;i<4;i++){
		$('#number'+i).text("");
		$('#informer_type'+i).text("");
		$('#report_type'+i).text("");
		$('#informer_name'+i).text("");
		$('#informer_tel'+i).text("");
		$('#contents'+i).text("");
	}
	parent.childwin.resetStudio();
} 
//방송리스트 한개씩 지우기
function goDel(num){
	$('#number'+num).text("");
	$('#informer_type'+num).text("");
	$('#report_type'+num).text("");
	$('#informer_name'+num).text("");
	$('#informer_tel'+num).text("");
	$('#contents'+num).text("");
	parent.childwin.goDel(num);
}

//전달사항 전송
function sendText(){
	if(parent.childwin==null || parent.childwin.closed){
		parent.childwin = window.open('<c:url value="/jsp/producer/common/studio.jsp"/>','studio','width=450 height=580 toolbar=no status=no directories=no scrollbars=0 location=no resizable=no menubar=no screenX=500 left=1500 screenY=10 top=10');
	} else{
		parent.childwin.focus();
	}
	parent.childwin.showText($('#message').val());
}

//접수내용 수정
function goEdit(str){
	param = '?receipt_id='+str;
	var url = '<c:url value="/receipt/receiptDetail.do"/>' + param;
	window.open(url,'editReceipt','width=604 height=289 toolbar=no status=no directories=no scrollbars=0 location=no resizable=no menubar=no screenX=10 left=150 screenY=10 top=10');
} 

//체크박스 해제
function getCheckReset(){
	checkCount=0;
	checkId="";

	var chk_listArr = $("input[id='broadcast_check']");
    for (var i=0; i < chk_listArr.length; i++) {
        chk_listArr[i].checked = this.checked;
    }
}

//탭메뉴
function goTab(tabId){
	
	clearTimeout(ID);
	
	tabMenu=tabId;
	checkCount=0;
	checkId="";
	
	$('#todayAllcheck').removeAttr("checked");
	$('#incidentAllcheck').removeAttr("checked");
	$('#divTodayListautoLoading').removeAttr("checked");
	$('#divIncidentListautoLoading').removeAttr("checked");

 	if(tabMenu=="divTodayList"){
		document.frmTodaySearch.reset();
		$('#divIncidentList').hide();
		$('#divCCTVList').hide();
	}else if(tabMenu=="divIncidentList"){
		document.frmInciSearch.reset();
		$('#divTodayList').hide();
		$('#divCCTVList').hide();
	}else if(tabMenu=="divCCTVList"){
		document.frmCCTVSearch.reset();
		$('#divTodayList').hide();
		$('#divIncidentList').hide();
	} 
	
	$('#'+tabMenu).show();
	
	fnReload();
	
}
//소통현황 검색
function goTodaySearch(){
	get_current_time();	//최근 검색시간
	var options = {
            url:'<c:url value="/receiptToday/receiptToday.do"/>',
            type:"post",
            target: '#TodayList'
    };
    $('#frmTodaySearch').ajaxSubmit(options);
}
//유고현황 검색
function goIncidentSearch(){
	get_current_time();	//최근 검색시간
	var options = {
            url:'<c:url value="/receiptToday/incidentList.do"/>',
            type:"post",
            target: '#IncidentList'
    };
    $('#frmInciSearch').ajaxSubmit(options);
}
//도로리스트 검색
function goArterySearch(){
//	get_current_time();	//최근 검색시간
	var options = {
            url:'<c:url value="/receiptToday/arteryList.do"/>',
            type:"post",
            target: '#ArteryList'
    };
    $('#frmCCTVSearch').ajaxSubmit(options);
}

//CCTV play
function playCCTV(url,cctvName){
//	$("#embDiv").show();
	$("#emb").attr("src",url);
	$("#cctv_name").text(cctvName);
}
//CCTV리스트 조회
function goCCTVList(arteryId, arteryName){
	$('#CCTVTitle').text("CCTV목록(도로명:"+arteryName+")");
	$('#CCTVList').load('<c:url value="/receiptToday/CCTV.do"/>?arteryId='+arteryId);
}

//현재 시간 가져오기
function get_current_time(){
	 var now = new Date();
	 var yyyy = now.getFullYear();
	 var mm = now.getMonth() + 1;
	 var dd = now.getDate();
	 var h = now.getHours();
	 var m = now.getMinutes();
	 var s =  now.getSeconds();
	 var yyyymmdd = getTimeFormat(yyyy) + "." + getTimeFormat(mm) + "." + getTimeFormat(dd);
	 var clock = " " + (h > 11 ? "PM" : "AM") + " " + getTimeFormat((h > 12 ? h-12 : h)) + ":" + getTimeFormat(m)+ ":" + getTimeFormat(s);
	 $('#'+tabMenu+'time').html(clock);
	 $('#'+tabMenu+'date').html(yyyymmdd);
}

function getTimeFormat(num){
	if(num > 9){
		return num;
	}else{
		return "0" + num; 
	}
}

//자동검색 체크
function autoLoading(obj){
	if(obj.checked){
		$('#'+tabMenu+'reloadTime').attr('disabled',false);
		clearTimeout(ID);
		fnReload("auto");
	}else{
		clearTimeout(ID);
		$('#'+tabMenu+'reloadTime').attr('disabled',true);
	}
}

//소통현황 엑셀 다운로드
function excelTodayList(){
/* 	frmTodaySearch.document.fileName = filename; */
	frmTodaySearch.action = '<c:url value="/receiptToday/excelTodayList.do"/>';
	frmTodaySearch.submit();
	
/* 	$("#excelDown").empty();
	$("#excelDown").append("<input type=\"hidden\" id=\"fileName\" name=\"fileName\" />"); */
}
//유고정보 엑셀 다운로드
function excelIncidentList(){
	frmInciSearch.action = '<c:url value="/receiptToday/excelIncidentList.do"/>';
	frmInciSearch.submit();
}