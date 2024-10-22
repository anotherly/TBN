<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="ko" lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>TBN한국교통방송 제보접수시스템</title>

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/layout.css" />

<script type="text/javascript" src="<%=request.getContextPath()%>/js/jqFR/jquery-3.6.0.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/jqFR/jQueryUI-v1.13.0.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jqFR/jquery-ui1.13.js"></script>

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/broadcast.css"/>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/receipt.css"/>

<script type="text/javascript" charset="utf-8"  src="<%=request.getContextPath()%>/js/common.js"></script>
<script type="text/javascript" charset="utf-8" src="<%=request.getContextPath()%>/js/jquery.pagination.js"></script>
<script type="text/javascript" charset="utf-8" src="<%=request.getContextPath()%>/js/jquery.form.js"></script>

<script type="text/javascript" charset="utf-8" src="<%=request.getContextPath()%>/js/receipt/receipt.js"></script>
<script type="text/javascript" charset="utf-8" src="<%=request.getContextPath()%>/js/receipt/popup.js"></script>
<script type="text/javascript" charset="utf-8" src="<%=request.getContextPath()%>/js/autoLoading.js"></script>
<script type="text/javascript" charset="utf-8" src="<%=request.getContextPath()%>/js/broadcast/common.js"></script>
<script type="text/javascript" charset="utf-8" src="<%=request.getContextPath()%>/js/timeDate.js"></script>
<script type="text/javascript" charset="utf-8" src="<%=request.getContextPath()%>/js/paging.js"></script>

	<!-- DateTimePicker -->
	<script src="<%=request.getContextPath()%>/calender/moment.js"></script>
	<script src="<%=request.getContextPath()%>/calender/mo_ko.js"></script>
	<script src="<%=request.getContextPath()%>/calender/bootstrap-datetimepicker.js"></script>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/calender/no-boot-calendar-custom.css" />
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/calender/datetimepickerstyle.css" />

<script>
	$(document).ready(function() {
		tabMenu = "fullReceivedHistory";
		console.log("전체접수");
		//autoLoading = false;
		//changeAutoLoadingFlag();
		
		//서울교통방송의 경우 교통방송 전체로
		if(opener.lgnArea == '080'){
			$("#AREA_CODE").val("").prop("selected", true);
		}else{
			$("#AREA_CODE").val(opener.lgnArea).prop("selected", true);
		}
		
		var todaysDate = yesturdate("");
		currentPage = 1;

		//데이트타임피커
		var toDate = new Date();
		$('#datetimepicker1').datetimepicker({
			 format:"YYYY-MM-DD" ,
			 //defaultDate:moment().subtract(1, 'months'),
			 maxDate : moment()
		});
		$('#datetimepicker2').datetimepicker({
			 format:"YYYY-MM-DD",
			 //defaultDate:moment(),
			 maxDate : moment()
		});
		
		//기존에 검색한 이력이 있다면 기존 날짜를,
		//없다면(최초진입) 어제 날짜를
		console.log(opener.lgnArea);
		if(typeof opener.allSchVo !== "undefined"){
			putAllForm(opener.allSchVo);
		}else{
			$("#START_DAY").val(yesturdate("-"));
			$("#END_DAY").val(yesturdate("-"));
		}
		
		searchFullStatus('fullReceivedHistorySearch');
		
		/* var totalAjax = ajaxMethod("/receipt/getTotalPageFR.ajax", 
				{"START_DAY" : $("#START_DAY").val(),"END_DAY": $("#END_DAY").val(), "startRow" : currentPage, "AREA_CODE":opener.lgnArea});
		totalPage = totalAjax.totalPage;
		$("#resultListTotal span").text(totalAjax.totalSize);
		if(totalAjax.totalSize == 0){
			$("#broadcastList").load("/receipt/noResult.do");
		}else{
			$("#broadcastList").load("/receipt/receivedStatusListFR.do"
					, {"START_DAY" : $("#START_DAY").val(),"END_DAY": $("#END_DAY").val(),"startRow" : currentPage, "AREA_CODE":opener.lgnArea});
		} */
		
		/* 스크롤 관련(수정예정) */
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
										<img src="../images/h1_0102_off.gif" alt="금일접수현황" /></a></li>
									<li><a href="/receipt/fullReceivedHistory.do">
										<img src="../images/h1_0103.gif" alt="전체접수이력" /></a>
									</li>
									<!-- <li><a href="/receipt/imsFromUtic.do">
										<img src="../images/h1_0104_off.gif" alt="돌발접수이력" /></a>
									</li> -->
									<!-- <li><a href="<c:url value="/receipt/lostReceived.do" />"><img
											src="../images/h1_0105_off.gif" alt="분실물현황" /></a></li> -->
								</ul>
							</div>
	<!-- board_list -->
	<div class="board_list2">
		<!-- 자동검색 영역 시작 -> 비활성화-->
		<div class="searchwrap">
			<div>
				<fieldset style="width:1400px;height:21px;">
				<strong class="blue">
					<span style="font-size:13px;">검색기간을 초기화 하시려면 제보접수 등록창을 초기화 해 주세요</span>
				</strong>
					<!-- <legend>금일접수현황 자동검색 유무</legend>
					[최근] <strong class="blue">
							<span id="fullReceivedHistorydate" style="font-size:13px;"></span>
		           			<span id="fullReceivedHistorytime" style="font-size:13px;"></span>
						</strong>&nbsp;&nbsp; 
						<label for="fullReceivedHistoryautoLoading">
	                  		<input id="fullReceivedHistoryautoLoading" name="fullReceivedHistoryautoLoading" 
	                  					type="checkbox" onclick="changeAutoLoadingFlag()" checked />
				   			자동로딩
				   		</label>
					<select id="fullReceivedHistoryreloadTime" name="fullReceivedHistoryreloadTime" class="r_table_sel2" style="width:60px;"
							onchange="changeIntervalTime(this)">
	                	<option value="10">10초</option>
	                	<option value="20">20초</option>
	                	<option value="30">30초</option>
	                	<option value="60">60초</option>
	                </select>-->
				</fieldset>
			</div>
		</div> 
		<!-- 자동검색 영역 끝 -->
		<!-- 검색조건 영역 시작 -->
		<div class="popupSearch">
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
					<input type='hidden' name="RECEIPT_TIME" id="RECEIPT_TIME" class="table_sel" style="width: 70px; "value=''/>
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
					<div class="form_daterange" style="display: inline-flex;align-items: center;gap: 5px;" id="schDtBody">
						<div class='input-group date' id='datetimepicker1'>
							<input type='text' class="form-control dt_search" name="START_DAY" id="START_DAY" required/>
							<span class="input-group-addon">
								<span class="glyphicon glyphicon-calendar"></span>
							</span>
						</div>
						 ~ 
						<div class='input-group date' id='datetimepicker2'>
							<input type="text" class="form-control dt_search" id="END_DAY" name="END_DAY" required/>
							<span class="input-group-addon">
								<span class="glyphicon glyphicon-calendar"></span>
							</span>
						</div>
					</div>
<!-- 					<input class="input_base" type="text" name="START_DAY" id="START_DAY" style="width:90px; height:21px; font-size:14px; text-align:center;" readonly/> ~ 
					<input class="input_base" type="text" name="END_DAY" id="END_DAY" style="width:90px; height:21px;font-size:14px; text-align:center;" readonly/>
 -->
 					<br/>
					통신원 :  
					<input type="text" name="INDIVIDUAL_NAME" id="INDIVIDUAL_NAME" class="input_base"
							onkeyup="if(event.keyCode == 13)searchFullStatus('fullReceivedHistorySearch');" maxlength="30" style="width:100px; height:21px;" />
					접수자 :  
					<input type="text" name="RECEPTION_NAME" id="RECEPTION_NAME" class="input_base"
							onkeyup="if(event.keyCode == 13)searchFullStatus('fullReceivedHistorySearch');" maxlength="30" style="width:100px; height:21px;" />

					내용 : 
					<input type="text" name="CONTENT1" id="CONTENT1" class="input_base"
							onkeyup="if(event.keyCode == 13)searchFullStatus('fullReceivedHistorySearch');" maxlength="30" style="width:200px; height:21px;" />
					
					<input type="radio" name="rcptAndOr" value="AND" checked="checked"/> AND
					<input type="radio" name="rcptAndOr" value="OR"/> OR

					<input type="text" name="CONTENT2" id="CONTENT2" class="input_base"
							onkeyup="if(event.keyCode == 13)searchFullStatus('fullReceivedHistorySearch');" maxlength="30" style="width:200px; height:21px;" />
					<!-- 버튼 -->		 
					<img src="../images/btn_search.gif" style="cursor: pointer;"
							onclick="searchFullStatus('fullReceivedHistorySearch')" alt="검색" />
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
				<a href="javascript:goStats('stats/receiptDown.ajax');">
					<img src="../images/btn_excel_down2.gif" alt="엑셀다운로드" style="width: 90px;"/>
				</a>			
			</div>
		</div>	
		<!-- 방송리스트 header 시작 -->
		<div style="width: 900px;">
			<ul id="broadcastListHead"  class="receiptPopListHead" style="width: 1400px;">
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
				<ul id="broadcastList"   class="receiptPopList">
					<li style="padding-top: 15px;">결과를 불러오는 중입니다.</li>
				</ul>
			</div>
			<!-- fullReceivedHistoryList.jsp -->
		</div>
		<!-- 검색결과 끝 -->
		<img id="icoRcpExplain" src="../images/img_iconExp.png" />
								</div>
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
