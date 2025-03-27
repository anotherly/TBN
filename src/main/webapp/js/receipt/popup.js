function getEditPage(resultId, rnum){
	console.log("편집페이지");
	if(tabMenu == 'receivedStatus' || tabMenu == "receivedStatusSearch"){
		if(autoLoading){
			pauseFlagTo(true);
		}
		var li = $("#resultList"+resultId);
		li.empty();
		li.load("/receipt/selectEditVO.do", {"RECEIPT_ID":resultId, "RNUM":rnum});
		editOpenTotal++;
	} else{
		return;
	}
}
//금일접수현황 편집 후 반영
//나중에 nowuserarea 서버단에서 처리하게 바꿔야 함
function updateReceipt(receiptID, rnum){
	console.log("업데이트 금일접수");
	var RNUM = rnum;
	var RECEIPT_ID = receiptID;
	
	var FLAG_SEND_chkbox = $("input:checkbox[id=FLAG_REQUEST]");
	(FLAG_SEND_chkbox.is(":checked")) ? FLAG_SEND_chkbox.val('Y') : FLAG_SEND_chkbox.val('N');
	
	var FLAG_IMPORTANT_chkbox = $("input:checkbox[id=FLAG_IMPORTANT]");
	(FLAG_IMPORTANT_chkbox.is(":checked")) ? FLAG_IMPORTANT_chkbox.val('Y') : FLAG_IMPORTANT_chkbox.val('N');
	
	var FLAG_DISASTOR_chkbox = $("input:checkbox[id=FLAG_DISASTOR]");
	(FLAG_DISASTOR_chkbox.is(":checked")) ? FLAG_DISASTOR_chkbox.val('Y') : FLAG_DISASTOR_chkbox.val('N');
	
	var FLAG_SEND = FLAG_SEND_chkbox.val();
	var FLAG_IMPORTANT = FLAG_IMPORTANT_chkbox.val();
	var FLAG_DISASTOR = FLAG_DISASTOR_chkbox.val();
	var REPORT_TYPE = $("#REPORT_TYPE_EDIT").val();
	var REPORT_TYPE2 = $("#REPORT_TYPE2_EDIT").val();
	var MEMO = $("#MEMO_EDIT").val();
	var REPORTER_TYPE = $("#REPORTMEAN_TYPE_EDIT").val();
	var REGION_ID = $("#AREA_CODE_EDIT").val();
	
	var INDIVIDUAL_ID=$("#INFORMER_ID").val();
	var INDIVIDUAL_NAME=$("#INFORMER_NAME").val();
	var TYPE_NAME=$("#INFORMER_TYPE").text();
	var TYPE_ID=$("#INFORMER_TYPE_ID").val();
		
	//시민체크했을땐 id,통신원타입 2로변경할경 통신원유형명 ''로
	if($('#FLAG_SIMIN').is(':checked')){
		 INDIVIDUAL_ID='';
		 TYPE_NAME='시민';
		 TYPE_ID='2';
	}
	
	var editVO = {"RECEIPT_ID":RECEIPT_ID
					,"FLAG_SEND":FLAG_SEND
					,"FLAG_IMPORTANT":FLAG_IMPORTANT
					,"FLAG_DISASTOR":FLAG_DISASTOR
					,"REPORT_TYPE":REPORT_TYPE
					,"REPORT_TYPE2":REPORT_TYPE2
					,"MEMO":MEMO
					,"REPORTER_TYPE":REPORTER_TYPE
					,"REGION_ID":REGION_ID
					,"RNUM":rnum
					,"INDIVIDUAL_ID":INDIVIDUAL_ID
					,"INDIVIDUAL_NAME":INDIVIDUAL_NAME
					,"TYPE_NAME":TYPE_NAME
					,"TYPE_ID":TYPE_ID
				}
	
	var result = ajaxMethod("/receipt/updateReceipt.ajax", editVO).result;
	if(result >= 1){
		$("#resultList"+receiptID).load("/receipt/showEditResult.do", {"RECEIPT_ID":receiptID, "RNUM":rnum});
		editOpenTotal--;
		if(isPause && editOpenTotal==0 && !onSearch){
			pauseFlagTo(false);
		}
	}else{
		alert("Error발생: 잠시 후 다시 시도해주세요.");
	}
}

function undoUpdate(receiptID, rnum){
	var undo = confirm("정말 취소 하시겠습니까?");
	if(undo){
		$("#resultList"+receiptID).load("/receipt/cancleEditVO.do", {"RECEIPT_ID":receiptID, "RNUM":rnum});
		editOpenTotal--;
		if(isPause && editOpenTotal==0 && !onSearch){
			pauseFlagTo(false);
		}
	} else{
		return;
	}
}

//팝업 - 검색
function searchFullStatus(frm){
	tabMenu = frm;
	/*if(autoLoading){
		pauseFlagTo(true);
		onSearch = true;
	}*/
	currentPage = 1;
	
	var searchVO = generateSearchVO(frm);
	var totalAjax;

	//전체접수일 경우
	if(tabMenu == 'fullReceivedHistorySearch'){
		 opener.allSchVo = searchVO;
		totalAjax = ajaxMethod("/receipt/getSearchTotalPageFR.ajax", searchVO);
	}else{//금일접수 일 경우
		totalAjax = ajaxMethod("/receipt/getSearchTotalPage.ajax", searchVO);
	}
	
	totalPage = totalAjax.totalPage;
	$("#resultListTotal span").text(totalAjax.totalSize);

	if(totalAjax.totalSize == 0){
		$("#broadcastList").load("/receipt/noResult.do");
	} else{
		//$("#broadcastList").load("/receipt/receivedStatusList.do", {"RECEIPT_DAY" : todaysDate, "startRow" : currentPage});
		var url = generateSearchURL(frm, currentPage);
		$("#TodayList").scrollTop(0);
		$("#broadcastList").load(url);
	}
	
}

//돌발정보 새로고침
//function refreshIms(){
//	
//	var totalSize = "${imsListSize}";
//	$("#resultListTotal span").text(totalSize);
//	if(totalSize == 0){
//		$("#broadcastList").load("/receipt/noResult.do");
//	} else{
//		$("#broadcastList").load("/receipt/imsFromUticList.do");
//	}
//}

function putAllForm(allSchVo){
	console.log("putAllForm");
//	$("#AREA_CODE").val(allSchVo.AREA_CODE);
//	$("#CONTENT").val(allSchVo.CONTENT);
//	$("#FLAG_BROD").val(allSchVo.FLAG_BROD);
//	$("#FLAG_IMPORTANT").val(allSchVo.FLAG_IMPORTANT);
//	$("#FLAG_SEND").val(allSchVo.FLAG_SEND);
//	$("#INDIVIDUAL_NAME").val(allSchVo.INDIVIDUAL_NAME);
//	$("#REPORTMEAN_TYPE").val(allSchVo.REPORTMEAN_TYPE);
//	$("#REPORT_TYPE").val(allSchVo.REPORT_TYPE);
//	$("#REPORT_TYPE2").val(allSchVo.REPORT_TYPE2);
//	$("#rcptAndOr").val(allSchVo.rcptAndOr);
	
	$("#START_DAY").val(allSchVo.START_DAY);
	$("#END_DAY").val(allSchVo.END_DAY);
}












