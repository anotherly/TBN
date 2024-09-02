<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<title>TBN한국교통방송 제보접수시스템</title>
<link rel="stylesheet" type="text/css" href="<c:url value='/css/layout.css'/>" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/broadcast.css"/>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/receipt.css"/>

<script type="text/javascript" charset="utf-8" src="<c:url value='/js/common/ui.js'/>"></script>
<script type="text/javascript" charset="utf-8" src="<c:url value="/js/common/util.js"/>"></script>
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
<script type="text/javascript" charset="utf-8" src="<%=request.getContextPath()%>/js/excelParser.js"></script>

<script>

$(document).ready(function() {
	tabMenu="imsFromUtic";
	connectToImsList();
	
	get_current_time();
});

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
									<img src="../images/h1_0102_off.gif" alt="금일접수현황" /></a></li>
								<li><a href="/receipt/fullReceivedHistory.do">
									<img src="../images/h1_0103_off.gif" alt="전체접수이력" /></a>
								</li>
								<li><a href="/receipt/imsFromUtic.do">
									<img src="../images/h1_0104.gif" alt="돌발접수이력" /></a>
								</li>
								<!-- <li><a href="<c:url value="/receipt/lostReceived.do" />"><img
										src="../images/h1_0105_off.gif" alt="분실물현황" /></a></li> -->
							</ul>
						</div>
						
	<!-- board_list -->
	<div class="board_list2" style="width:1400px;">
		<!-- 옵션 + 자동검색 영역 시작 -->
		<div class="searchwrap">
			<img src="../images/refresh2.png" onclick="location.href='/receipt/imsFromUtic.do'" style="cursor:pointer;width:80px;" />
             	<!-- <div>
                 	<fieldset style="width:1400px;">
                  	<legend>금일접수현황 자동로딩 유무</legend>
                [최근] <strong class="blue">
	                   	<span id="imsFromUticdate" style="font-size:13px;"></span>
	           			<span id="imsFromUtictime" style="font-size:13px;"></span>
                  	</strong>&nbsp;&nbsp; 
                  	<label for="imsFromUticautoLoading">
                  		<input id="imsFromUticautoLoading" name="imsFromUticautoLoading" 
                  					type="checkbox" onclick="changeAutoLoadingFlag()" checked />
			   			자동로딩
			   		</label>
                  	<select id="imsFromUticreloadTime" name="imsFromUticreloadTime" class="r_table_sel2" style="width:60px;"
                  			onchange="changeIntervalTime(this)">
                       <option value="10">10초</option>
                       <option value="20">20초</option>
                       <option value="30">30초</option>
                       <option value="60">60초</option>
                  	</select>
                 	</fieldset>
                 </div> -->
	    </div>
		<!-- 옵션 + 자동검색 영역 끝 -->
		
		<!-- 검색조건 영역 시작 -->
		<!-- 검색조건 영역 끝 -->
		
		<p id="resultListTotal">
			<img src="../images/ico_result.gif" />
			검색결과 <span style="font-weight:700;"></span>건
		</p>
		
		<!-- 방송리스트 header 시작 -->
		<div style="width: 1400px;">
			<ul id="broadcastListHead" style="width: 1400px;">
				<li>
					<span style="width:50px;">번호</span>
					<span style="width:50px;">유형(대)</span>
					<span style="width:140px;">유형(소)</span>
					<span style="width:60px;">소통현황</span>
					<span style="width:30px;">등급</span>
					
					<span style="width:70px;">차선</span>
					<span style="width:570px;">내용</span>
					<span style="width:180px;">시작일자</span>
					<span style="width:180px;">종료일자</span>
					<span>지역</span>
				</li>
			</ul>
		</div>
		<!-- 방송리스트 header 끝 -->
		<!-- 검색결과 시작 -->
		<div id="TodayList" class="list_result_sc9" style="border-bottom:1px solid #b7b7b7; height:630px;">
			<div>
				<ul id="broadcastList">
					<li style="padding-top: 15px;">결과를 불러오는 중입니다.</li>
				</ul>
			</div>
			<!-- imsFromUticList.jsp -->
		</div>
		<!-- 검색결과 끝 -->
		<span style="position:absolute; right:5px; padding-top:7px; font-size:13px; font-weight:700;">※해당 정보는 경찰청(UTIC)에서 제공합니다.</span>
		
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