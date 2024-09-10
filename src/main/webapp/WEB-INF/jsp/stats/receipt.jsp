<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="ko" lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>TBN한국교통방송 제보접수시스템</title>

<link rel="stylesheet" type="text/css" href="<c:url value='/css/layout.css'/>" />
<link rel="stylesheet" type="text/css" href="/resources/demos/style.css"/>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/broadcast.css"/>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/receipt.css"/>
<link rel="stylesheet" type="text/css" href="//code.jquery.com/ui/1.13.0/themes/base/jquery-ui.css"/>

<script type="text/javascript" src="https://code.jquery.com/jquery-3.6.0.js"></script>
<script type="text/javascript" charset="utf-8"  src="<%=request.getContextPath()%>/js/common.js"></script>
<script type="text/javascript" charset="utf-8" src="<%=request.getContextPath()%>/js/jquery.pagination.js"></script>
<script type="text/javascript" charset="utf-8" src="<%=request.getContextPath()%>/js/jquery.form.js"></script>
<script type="text/javascript" src="https://code.jquery.com/ui/1.13.0/jquery-ui.js"></script>
<script type="text/javascript" charset="utf-8" src="<%=request.getContextPath()%>/js/receipt/receipt.js"></script>
<script type="text/javascript" charset="utf-8" src="<%=request.getContextPath()%>/js/receipt/popup.js"></script>
<script type="text/javascript" charset="utf-8" src="<%=request.getContextPath()%>/js/autoLoading.js"></script>
<script type="text/javascript" charset="utf-8" src="<%=request.getContextPath()%>/js/broadcast/common.js"></script>
<script type="text/javascript" charset="utf-8" src="<%=request.getContextPath()%>/js/timeDate.js"></script>
<script type="text/javascript" charset="utf-8" src="<%=request.getContextPath()%>/js/paging.js"></script>


<script>
$(document).ready(function(){
	tabMenu = "fullReceivedHistory";
	
	$("#AREA_CODE").val("020");
	
	var todaysDate = currentDate("");
	currentPage = 1;
	
	$("#START_DAY").val(currentDate("-"));
	$("#END_DAY").val(currentDate("-"));
	console.log(opener.lgnArea);
	
	var totalAjax = ajaxMethod("/receipt/getTotalPage.ajax", {"RECEIPT_DAY" : todaysDate, "startRow" : currentPage, "AREA_CODE":opener.lgnArea});
	totalPage = totalAjax.totalPage;
	$("#resultListTotal span").text(totalAjax.totalSize);
	if(totalAjax.totalSize == 0){
		$("#broadcastList").load("/receipt/noResult.do");
	} else{
		$("#broadcastList").load("/receipt/receivedStatusList.do", {"RECEIPT_DAY" : todaysDate, "startRow" : currentPage, "AREA_CODE":opener.lgnArea});
	}

	var dates = $('#START_DAY, #END_DAY').datepicker({
		changeMonth: false,
		numberOfMonths: 1,
		dateFormat: 'yy-mm-dd',
		monthNames: ['년  1 월','년  2 월','년  3 월','년  4 월','년  5 월','년  6 월','년  7 월','년  8 월','년  9 월','년  10 월','년  11 월','년  12 월',],
	    dayNamesMin: ['일', '월', '화', '수', '목', '금', '토'],
	    showMonthAfterYear:true,
	    defaultDate: new Date(),
		onSelect: function(selectedDate) {
			var option = this.id == "START_DAY" ? "minDate" : "maxDate";
			var instance = $(this).data("datepicker");
			var date = $.datepicker.parseDate(instance.settings.dateFormat || $.datepicker._defaults.dateFormat, selectedDate, instance.settings);
			dates.not(this).datepicker("option", option, date);
			$("img.ui-datepicker-trigger").attr("style", "margin-left:5px;");
		}
	});
	
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
});

/**
 * 페이징
 */
function pagination(){
    var options = {
            url: '<c:url value="/stats/receiptPagenation.do"/>',
            target:"#pagingBox",
            type:"post"
    };
    $('#frmReceiptSearch').ajaxSubmit(options);
}

// 접수리스트 리로드
/* function fnReload(){
	$('#divReceiptList').load('<c:url value="/stats/receiptList.do"/>');
	 $('#listDiv').load('<c:url value="/user/userList.do"/>?MIN=0');
	 $('#pagingBox').load('<c:url value="/user/userListPagenation.do"/>');
} */

function goReceiptSearch(){
	if($("#MIN").val()==""){
		$("#MIN").val(0);
	}
	$("#from_date").val($("#fromdate").val().replace("-","").replace("-",""));
	$("#to_date").val($("#todate").val().replace("-","").replace("-",""));
	var options = {
            url:'<c:url value="/stats/receiptList.do"/>',
            type:"post",
            target: '#divReceiptList',
            success: function(res){
            	pagination();
            }
       
    };
    $('#frmReceiptSearch').ajaxSubmit(options);
}

function excelReceiptList(){
	frmReceiptSearch.action = '<c:url value="/stats/excelReceiptList.do"/>';
	frmReceiptSearch.submit();
}
</script>
<div id="contentWrap">
	<!-- <div id="posi"><img src="../images/ico_home.gif" alt="home" />통계관리 > 제보접수현황</div> -->
	 <!-- contents -->
        <div id="contents">
           <h1 class='content-title'>통계관리</h1>
           <!-- board_list -->
           <div class="board_list">
               <!-- 검색조건 영역 시작 -->
              	<!-- 검색조건 영역 시작 -->
		<div class="rounding_wrap4">
			<div class="wrap_top"></div>
			<div class="wrap_center">
				<fieldset>
					<legend>전체접수현황 검색조건</legend>
					<select class="table_sel" id="REPORT_TYPE" name="REPORT_TYPE" style="width: 110px;"
							onchange="reportTypeOnChange('REPORT_TYPE','REPORT_TYPE2')">
						<option value="">제보유형(대)</option>
						<c:forEach var="reportFirstVO" items="${reportFirstList}">
							<option value="${reportFirstVO.BAS_SCOD }">
										${reportFirstVO.BAS_NAME }
							</option>
						</c:forEach>
					</select> 
					<select class="table_sel" style="width: 170px;" id="REPORT_TYPE2" name="REPORT_TYPE2">
						<option value="">제보유형(중)</option>
					</select> 
					<select name="REPORTMEAN_TYPE" id="REPORTMEAN_TYPE" class="table_sel" style="width: 140px;">
						<option value="">제보처 전체</option>
						<c:forEach var="reportMeanVO" items="${reportMeanTypeList}">
							<option value="${reportMeanVO.ID }">
										${reportMeanVO.NAME }
							</option>
						</c:forEach>
					</select>

					<select name="FLAG_SEND" id="FLAG_SEND" class="table_sel" style="width: 110px;">
						<option value="">전송여부</option>
						<option value="Y">예</option>
						<option value="N">아니오</option>
					</select>
					<select name="FLAG_BROD" id="FLAG_BROD" class="table_sel" style="width: 110px;">
						<option value="">방송여부</option>
						<option value="Y">예</option>
						<option value="N">아니오</option>
					</select> 
					<select name="AREA_CODE" id="AREA_CODE" class="table_sel" style="width: 120px;">
						<option value="">교통방송 전체</option>
						<c:forEach var="areaCodeVO" items="${areaCodeList}">
							<option value="${areaCodeVO.AREA_CODE }">
										${areaCodeVO.AREA_NAME }
							</option>
						</c:forEach>
					</select> 
					<input class="input_base" type="text" name="START_DAY" id="START_DAY" style="width:90px; height:21px; font-size:14px; text-align:center;" readonly/> ~ 
					<input class="input_base" type="text" name="END_DAY" id="END_DAY" style="width:90px; height:21px;font-size:14px; text-align:center;" readonly/> 
					<input type="text" name="CONTENT" id="CONTENT" class="input_base" style="width:250px; height:21px;"
							onkeyup="if(event.keyCode == 13)searchFullStatus('fullReceivedHistorySearch');" /> 
					<img src="../images/btn_search.gif" style="cursor: pointer;"
							onclick="searchFullStatus('fullReceivedHistorySearch')" alt="검색" />
				</fieldset>
			</div>
			<div class="wrap_bottom"></div>
		</div>
		        <!-- receipt/receiptSearch.jsp -->
		        </div>
               <!-- 검색조건 영역 끝 -->
	       

	<p id="resultListTotal" class="list_result txt_i10">
			<img src="../images/ico_result.gif" alt="" />
			검색결과 <span style="font-weight:700;"></span>건
		</p>
		<!-- 방송리스트 header 시작 -->
		<div style="width: 900px;">
			<ul id="broadcastListHead" style="width: 1400px;">
				<li>
					<span style="width:50px;">번호</span>
					<span style="width:85px;">일자</span>
					<span style="width:30px;">전송</span>
					<span style="width:30px;">방송</span>
					<span style="width:30px;">지도</span>
					
					<span style="width:50px;">시간</span>
					<span style="width:60px;">유형(대)</span>
					<span style="width:150px;">유형(중)</span>
					<span style="width:120px;">제보자</span>
					<span style="width:40px;">구분</span>
					<span style="width:80px;">접수자</span>
					<span style="width:505px;">내용</span>
					
					<span style="width:60px;">제보처</span>
					<span>교통방송</span>
				</li>
			</ul>
		</div>
		<!-- 방송리스트 header 끝 -->
		
		<!-- 검색결과 시작 -->
		<div id="TodayList" class="list_result_sc9" style="border-bottom:1px solid #b7b7b7;">
			<div>
				<ul id="broadcastList">
					<li style="padding-top: 15px;">결과를 불러오는 중입니다.</li>
				</ul>
			</div>
			<!-- fullReceivedHistoryList.jsp -->
		</div>
		<!-- 검색결과 끝 -->


	        <!-- 페이징 시작 -->
	        <div id="pagingBox" class="btnArg"> 
	        </div>
	        <!-- 페이징 끝 -->
	    </div>
	</div>
	<!-- //contents -->
</div>
