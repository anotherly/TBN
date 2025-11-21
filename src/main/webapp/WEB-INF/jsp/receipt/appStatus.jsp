<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="ko" lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>TBN한국교통방송 제보접수시스템</title>

<link rel="stylesheet" type="text/css" href="<c:url value='/css/layout.css'/>" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/broadcast.css"/>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/receipt.css"/>

<script  type="text/javascript" charset="utf-8" src="<%=request.getContextPath()%>/js/jquery.js"></script>
<script type="text/javascript" charset="utf-8" src="<%=request.getContextPath()%>/js/jquery.form.js"></script>
<script type="text/javascript" charset="utf-8" src="<%=request.getContextPath()%>/js/jquery.pagination.js"></script>
<script  type="text/javascript" charset="utf-8" src="<%=request.getContextPath()%>/js/common.js"></script>
<script type="text/javascript" charset="utf-8" src="<%=request.getContextPath()%>/js/receipt/receipt.js"></script>
<script type="text/javascript" charset="utf-8" src="<%=request.getContextPath()%>/js/receipt/popup.js"></script>
<script type="text/javascript" charset="utf-8" src="<%=request.getContextPath()%>/js/autoLoading.js"></script>
<script type="text/javascript" charset="utf-8" src="<%=request.getContextPath()%>/js/timeDate.js"></script>
<script type="text/javascript" charset="utf-8" src="<%=request.getContextPath()%>/js/paging.js"></script>
<script type="text/javascript" charset="utf-8" src="<%=request.getContextPath()%>/js/broadcast/common.js"></script>

<script>

$(document).ready(function() {
	tabMenu="receivedStatus";
	autoLoading = false;
	isPause = false;
	onSearch = false;
	editOpenTotal = 0;
	console.log("receivedStatus");
	/* changeAutoLoadingFlag(); */
	
	//서울교통방송의 경우 교통방송 전체로
/* 	if(opener.lgnArea == '080'){
		$("#AREA_CODE").val("").prop("selected", true);
	}else{
		$("#AREA_CODE").val(opener.lgnArea).prop("selected", true);
	} */
	
	var todaysDate = currentDate("");
	currentPage = 1;
	
	$("#START_DAY").val(currentDate("-"));
	$("#END_DAY").val(currentDate("-"));
	
	var totalAjax = ajaxMethod("/receipt/appGetTotalPage.ajax", {"RECEIPT_DAY" : todaysDate, "startRow" : currentPage, "AREA_CODE":opener.lgnArea});
	
	totalPage = totalAjax.totalPage;	
	$("#resultListTotal span").text(totalAjax.totalSize);
	
 	if(totalAjax.totalSize == 0){
		$("#broadcastList").load("/receipt/noResult.do");
	} else{
		$("#broadcastList").load("/receipt/appStatusList.do", {"RECEIPT_DAY" : todaysDate, "startRow" : currentPage, "AREA_CODE":opener.lgnArea});
	} 
	
	$("#TodayList").scroll(function(){
		var scrollTop = $(this).scrollTop();
		var innerHeight = $(this).innerHeight();
		var scrollHeight = $(this).prop('scrollHeight');
		
		if (scrollTop + innerHeight >= scrollHeight) {
	    	currentPage++;
	    	getListByScrollPaging(currentPage, todaysDate, totalPage);
		}
	});
	
	get_current_time();
	
	// 등록 버튼 클릭 시 실행 함수
	$('#appInsBtn').on('click', function() {
		console.log("등록 버튼 클릭");
		var valCheck = labelYes(); // t 또는 f
		
		if(valCheck) {
			
			var frmChk = confirm("이대로 등록하시겠습니까?");
			
			if(frmChk) {
				let checkedList = [];
				$('input[name="Selection"]:checked').each(function() {
				    checkedList.push($(this).val());
				});
				
				$.ajax({
				    url: "/receipt/appToToday.do",   // 처리할 URL
				    type: "POST",
				    traditional: true,      // 배열 전송할 때 필요!
				    data: {
				        ids: checkedList    // 선택된 ID 배열
				    },
				    success: function(res){
				        alert("성공적으로 등록되었습니다.");
				        location.reload();
				    },
				    error: function(err){
				        console.log(err);
				    }
				});
			} else {
				return false;
			}		
			
		} else {
			return false;
		}
	});
	
	
	// 등록 시 유효성 검사 함수
	function labelYes() {		
		if ($("input[name='Selection']:checked").length === 0) {
	        alert("등록할 제보를 1개 이상 선택해주세요");
	        return false; 
		 } else {
			 return true;
		 } 		
	}
	
	
	// 10초마다 자동 갱신
	
	// 함수 호출
	setInterval(function () { chkAutoLoad(); }, 30000);
	
	function chkAutoLoad() {
		if($('input[name="Selection"]:checked').length > 0) {
			return false;
		} else {
			location.reload();
		}
	}
	
	
	
});


</script>
</head>

<body>
	<input type="hidden" name="RECEIPT_ID" id="RECEIPT_ID" /> 
	<input type="hidden" name="CONTENTS" id="CONTENTS" />

	<div id="container">
		<div id="topWrap">
			<!-- bodyWrap -->
			<div id="bodyWrap">
				<!-- contentWrap -->
				<div id="contentWrap" style="width:1400px;">

					<!-- contents -->
					<div id="contents">
						<!-- 탭영역 -->
<!-- 						<div id="pop_tab">
							<ul>
								<li><a href="/receipt/receivedStatus.do">
									<img src="../images/h1_0102.gif" alt="금일접수현황" /></a></li>
								<li><a href="/receipt/fullReceivedHistory.do">
									<img src="../images/h1_0103_off.gif" alt="전체접수이력" /></a>
								</li>
								<li><a href="/receipt/imsFromUtic.do">
									<img src="../images/h1_0104_off.gif" alt="돌발접수이력" /></a>
								</li>
								<li><a href="<c:url value="/receipt/lostReceived.do" />"><img
										src="../images/h1_0105_off.gif" alt="분실물현황" /></a></li>
							</ul>
						</div> -->
						
	<!-- board_list -->
	<div class="board_list2" style="width:1400px;">
		<!-- 옵션 + 자동검색 영역 시작 -->
		<div class="searchwrap">
	             	<div>
	                 </div>
	             </div>
		<!-- 옵션 + 자동검색 영역 끝 -->
		<!-- 검색조건 영역 시작 -->
		<div style="width : 100%; height : 80px; margin-bottom : 15px;">
		</div>
		<!-- 검색조건 영역 끝 -->
		<div style="display:flex;align-items: center;justify-content: space-between;">
			<div>
				<p id="resultListTotal" style="width: 300px;">
					<img src="../images/ico_result.gif" />
					제보건수 <span style="font-weight:700;"></span>건
				</p>
			</div>
<!-- 			<div>
				<a href="javascript:goStats('stats/receiptDownToday.ajax');">
					<img src="../images/btn_excel_down2.gif" alt="엑셀다운로드" style="width: 90px;"/>
				</a>			
			</div> -->
		</div>		
		<!-- 방송리스트 header 시작 -->
		<div style="width: 1400px;">
			<ul id="broadcastListHead" class="receiptPopListHead" style="width: 1400px;">
				<li>
					<span style="width:60px;">선택</span>
					<span style="width:60px;">번호</span>
					<span style="width:100px;">일자</span>
					
					<span style="width:100px;">제보시간</span>
					<span style="width:100px;">유형(대)</span>
					<span style="width:150px;">제보자</span>
					<span style="width:500px;">내용</span>
					
					<span style="width:60px;">제보처</span>
					<span>교통방송</span>
				</li>
			</ul>
		</div>
		<!-- 방송리스트 header 끝 -->
		<!-- 검색결과 시작 -->
		<form id="listFrm" name="listFrm">
			<div id="TodayList" class="receivedList" style="border-bottom:1px solid #b7b7b7;">
				<div>
					<ul id="broadcastList"  class="receiptPopList">
						<li style="padding-top: 15px;">결과를 불러오는 중입니다.</li>
					</ul>
				</div>
				<!-- receivedStatusList.jsp -->
			</div>
		</form>
		<!-- <img id="icoRcpExplain" src="../images/img_iconExp.png" /> -->
		<span style="position:absolute; right:90px; bottom:-50px;">
			<button id="appInsBtn" style="background:#3c6dc5;color:white;padding:8px 25px;border:none;border-radius:6px;font-size:15px;cursor:pointer;transition:0.2s;"onmouseover="this.style.background='#336ED4'"onmouseout="this.style.background='#3F82F7'">
			    등록
			</button>
		</span>
		
		<!-- 검색결과 끝 -->
	</div>
					</div>
					<!-- //contents -->
				</div>
				<!-- //contentWrap -->
			</div>
			<!-- //bodyWrap -->
		</div>
	</div>
</body>
</html>