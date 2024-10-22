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
	console.log("금일접수");
	changeAutoLoadingFlag();
	//서울교통방송의 경우 교통방송 전체로
	if(opener.lgnArea == '080'){
		$("#AREA_CODE").val("").prop("selected", true);
	}else{
		$("#AREA_CODE").val(opener.lgnArea).prop("selected", true);
	}
	
	var todaysDate = currentDate("");
	currentPage = 1;
	
	$("#START_DAY").val(currentDate("-"));
	$("#END_DAY").val(currentDate("-"));
	
	var totalAjax = ajaxMethod("/receipt/getTotalPage.ajax", {"RECEIPT_DAY" : todaysDate, "startRow" : currentPage, "AREA_CODE":opener.lgnArea});
	totalPage = totalAjax.totalPage;
	$("#resultListTotal span").text(totalAjax.totalSize);
	if(totalAjax.totalSize == 0){
		$("#broadcastList").load("/receipt/noResult.do");
	} else{
		$("#broadcastList").load("/receipt/receivedStatusList.do", {"RECEIPT_DAY" : todaysDate, "startRow" : currentPage, "AREA_CODE":opener.lgnArea});
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
	
});

function goStats(url){
	console.log("통계 서브밋?");
	rkFlag = true;
	searchFrm.action = '<c:url value="/"/>'+url;
	searchFrm.submit();
	rkFlag = true;
}

</script>
</head>

<body>
<form id="searchFrm" id="searchFrm" method="post" onsubmit="return false;">
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
						<div id="pop_tab">
							<ul>
								<li><a href="/receipt/receivedStatus.do">
									<img src="../images/h1_0102.gif" alt="금일접수현황" /></a></li>
								<li><a href="/receipt/fullReceivedHistory.do">
									<img src="../images/h1_0103_off.gif" alt="전체접수이력" /></a>
								</li>
								<!-- <li><a href="/receipt/imsFromUtic.do">
									<img src="../images/h1_0104_off.gif" alt="돌발접수이력" /></a>
								</li> -->
								<!-- <li><a href="<c:url value="/receipt/lostReceived.do" />"><img
										src="../images/h1_0105_off.gif" alt="분실물현황" /></a></li> -->
							</ul>
						</div>
						
	<!-- board_list -->
	<div class="board_list2" style="width:1400px;">
		<!-- 옵션 + 자동검색 영역 시작 -->
		<div class="searchwrap">
	             	<div>
	                 	<fieldset style="width:1400px;">
	                  	<legend>금일접수현황 자동로딩 유무</legend>
	                [최근] <strong class="blue">
		                   	<span id="receivedStatusdate" style="font-size:13px;"></span>
		           			<span id="receivedStatustime" style="font-size:13px;"></span>
	                  	</strong>&nbsp;&nbsp; 
	                  	<label for="receivedStatusautoLoading">
	                  		<input id="receivedStatusautoLoading" name="receivedStatusautoLoading" 
	                  					type="checkbox" onclick="changeAutoLoadingFlag()" checked />
				   			자동로딩
				   		</label>
	                  	<select id="receivedStatusreloadTime" name="receivedStatusreloadTime" class="r_table_sel2" style="width:60px;"
	                  			onchange="changeIntervalTime(this)">
	                       <option value="10">10초</option>
	                       <option value="20">20초</option>
	                       <option value="30">30초</option>
	                       <option value="60">60초</option>
	                  	</select>
	                 	</fieldset>
	                 </div>
	             </div>
		<!-- 옵션 + 자동검색 영역 끝 -->
		<!-- 검색조건 영역 시작 -->
		<div class="popupSearch">
			<div class="wrap_center">
				<fieldset>
					<legend>금일접수현황 검색조건</legend>
					<select class="table_sel" style="width: 110px;" id="REPORT_TYPE" name="REPORT_TYPE"
							onchange="reportTypeOnChange('REPORT_TYPE','REPORT_TYPE2')">
						<option value="">제보유형(대)</option>
						<c:forEach var="reportFirstVO" items="${reportFirstList}">
							<option value=${reportFirstVO.BAS_SCOD }>
										${reportFirstVO.BAS_NAME }
							</option>
						</c:forEach>
					</select>
					<select class="table_sel" style="width:200px;" id="REPORT_TYPE2" name="REPORT_TYPE2">
						<option value="">제보유형(중)</option>
					</select>
					<select name="REPORTMEAN_TYPE" id="REPORTMEAN_TYPE" class="table_sel" style="width: 150px;">
						<option value="">제보처 전체</option>
						<c:forEach var="reportMeanVO" items="${reportMeanTypeList}">
							<option value=${reportMeanVO.ID }>
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
					<select name="FLAG_IMPORTANT" id="FLAG_IMPORTANT" class="table_sel" style="width: 110px;">
						<option value="">긴급여부</option>
						<option value="Y">긴급</option>
						<option value="N">비긴급</option>
					</select> 
					<select name="AREA_CODE" id="AREA_CODE" class="table_sel" style="width: 120px;">
						<option value="">교통방송 전체</option>
						<c:forEach var="areaCodeVO" items="${areaCodeList}">
							<option value=${areaCodeVO.AREA_CODE }>
										${areaCodeVO.AREA_NAME }
							</option>
						</c:forEach>
					</select>
					 
					<br/>
					시간:
					<select name="RECEIPT_TIME" id="RECEIPT_TIME" class="table_sel" style="width: 70px;">
						<option value="">시간대</option>
						<c:forEach var="i" begin="0" end="23"> 
							<c:choose>
								<c:when test="${i < 10}">
									<option value="0${i}">0${i}시</option>		
								</c:when>
								<c:otherwise>
									<option value="${i}">${i}시</option>	
								</c:otherwise>
							</c:choose>
						 </c:forEach>
					</select>
					
					<input class="input_base" type="hidden" name="START_DAY" id="START_DAY" style="width:90px; height:21px; font-size:14px; text-align:center;" readonly/> 
					<input class="input_base" type="hidden" name="END_DAY" id="END_DAY" style="width:90px; height:21px;font-size:14px; text-align:center;" readonly/>
					
					
					통신원 :  
					<input type="text" name="INDIVIDUAL_NAME" id="INDIVIDUAL_NAME" class="input_base"
							onkeyup="if(event.keyCode == 13)searchFullStatus('receivedStatusSearch');" maxlength="30" style="width:100px; height:21px;" />
					접수자 :  
					<input type="text" name="RECEPTION_NAME" id="RECEPTION_NAME" class="input_base"
							onkeyup="if(event.keyCode == 13)searchFullStatus('receivedStatusSearch');" maxlength="30" style="width:100px; height:21px;" />

					내용 : 
					<input type="text" name="CONTENT1" id="CONTENT1" class="input_base"
							onkeyup="if(event.keyCode == 13)searchFullStatus('receivedStatusSearch');" maxlength="30" style="width:200px; height:21px;" />

					
					<input type="radio" name="rcptAndOr" value="AND" checked="checked"/> AND
					<input type="radio" name="rcptAndOr" value="OR"/> OR

					<input type="text" name="CONTENT2" id="CONTENT2" class="input_base"
							onkeyup="if(event.keyCode == 13)searchFullStatus('receivedStatusSearch');" maxlength="30" style="width:200px; height:21px;" />
					<!-- 버튼 -->	
					<img src="../images/btn_search.gif" onclick="searchFullStatus('receivedStatusSearch')" style="cursor: pointer;" alt="검색" />
				</fieldset>
			</div>
		</div>
		<!-- 검색조건 영역 끝 -->
		<div style="display:flex;align-items: center;justify-content: space-between;">
			<div>
				<p id="resultListTotal" style="width: 300px;">
					<img src="../images/ico_result.gif" />
					검색결과 <span style="font-weight:700;"></span>건
				</p>
			</div>
			<div>
				<a href="javascript:goStats('stats/receiptDownToday.ajax');">
					<img src="../images/btn_excel_down2.gif" alt="엑셀다운로드" style="width: 90px;"/>
				</a>			
			</div>
		</div>		
		<!-- 방송리스트 header 시작 -->
		<div style="width: 1400px;">
			<ul id="broadcastListHead" class="receiptPopListHead" style="width: 1400px;">
				<li>
					<span style="width:50px;">번호</span>
					<span style="width:85px;">일자</span>
					<span style="width:30px;">전송</span>
					<span style="width:30px;">방송</span>
					
					<span style="width:52px;">제보시간</span>
					<span style="width:52px;">방송시간</span>
					<span style="width:60px;">유형(대)</span>
					<span style="width:150px;">유형(중)</span>
					<span style="width:120px;">제보자</span>
					<span style="width:40px;">구분</span>
					<span style="width:80px;">접수자</span>
					<span style="width:481px;">내용</span>
					
					<span style="width:60px;">제보처</span>
					<span>교통방송</span>
				</li>
			</ul>
		</div>
		<!-- 방송리스트 header 끝 -->
		<!-- 검색결과 시작 -->
		<div id="TodayList" class="receivedList" style="border-bottom:1px solid #b7b7b7;">
			<div>
				<ul id="broadcastList"  class="receiptPopList">
					<li style="padding-top: 15px;">결과를 불러오는 중입니다.</li>
				</ul>
			</div>
			<!-- receivedStatusList.jsp -->
		</div>
		<img id="icoRcpExplain" src="../images/img_iconExp.png" />
		<span style="position:absolute; right:5px;">※해당 제보 더블클릭 시 수정작업 가능.</span>
		
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
	
</form>

</body>
</html>


