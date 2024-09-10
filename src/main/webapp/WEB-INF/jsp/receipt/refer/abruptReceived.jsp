<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="ko" lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>TBN한국교통방송 제보접수시스템</title>
<link rel="stylesheet" type="text/css" href="<c:url value='/css/layout.css'/>"/>
<script type="text/javascript" charset="utf-8" src="<c:url value='/js/common/jquery/jquery-1.6.4.min.js'/>"></script>
<script type="text/javascript" charset="utf-8" src="<c:url value='/js/common/jquery/jquery.form.js'/>"></script>
<script type="text/javascript" charset="utf-8" src="<c:url value='/js/common/ui.js'/>"></script>
<script type="text/javascript" charset="utf-8" src="<c:url value="/js/common/jquery.pagination.js"/>"></script>
<script type="text/javascript" charset="utf-8" src="<c:url value="/js/common/util.js"/>"></script>
<link rel="stylesheet" type="text/css" href="<c:url value="/css/jquery-ui-1.9.0.custom.css"/>" rel="stylesheet"  />
<script type="text/javascript" charset="utf-8" src="<c:url value='/js/common/jquery/jquery-ui-1.9.0.custom.js'/>"></script>
<script type="text/javascript" charset="utf-8" src="<c:url value='/js/common/jquery/jquery-ui-1.9.0.custom.min.js'/>"></script>
<script>

$(document).ready(function(){
	setDate();
	//조회시간표시
	//setDateTimes = setInterval("setDateTime()",1000);
	setDateTime();
});


function setDateTime(){
	var startDay = new Date();
	var startDate = startDay.getFullYear()+"-"+((startDay.getMonth()+1)>9? (startDay.getMonth()+1): '0'+(startDay.getMonth()+1))+"-"+(startDay.getDate() > 9 ? startDay.getDate():"0" +startDay.getDate());
	var toHH = startDay.getHours() > 9? startDay.getHours():  "0" + startDay.getHours();
	var toMM = startDay.getMinutes() > 9? startDay.getMinutes():  "0" + startDay.getMinutes();
	var toSS = startDay.getSeconds(); 
	toSS = toSS > 9 ? toSS : '0' + toSS;
	var str = (toHH > 12 ? "PM" : "AM");
	var hh = (toHH > 12 ? (toHH -12):toHH  );
	hh = hh > 9 ? hh : '0' + hh;
	$("#sysTime").html(startDate + " " + str +" " + hh  + ":" + toMM + ":" + toSS);
}



function statusSearch(){
	$.ajax
	(
		{
			type : "post" ,
			url : "<c:url value='/receipt/abruptReceivedSearch.do' />" ,
			dataType : "html" ,
			data :  $("#searchFrm").serialize(),
			cache : false ,
			success:function(html){
				$("#contentsDiv").html(html);
				setDateTime();
				clearTimeout(timeOut);
				onSidcheck();
			} ,

			error:function(html,error){
				//alert("시스템에 문제가 생겼습니다." + error);
			}

		}
	);
}

function setDate(){
	var startDay = new Date();
	var startDate = startDay.getFullYear()+"-"+((startDay.getMonth()+1)>9? (startDay.getMonth()+1): '0'+(startDay.getMonth()+1))+"-"+(startDay.getDate() > 9 ? startDay.getDate():"0" +startDay.getDate());
	var toHH = startDay.getHours() > 9? startDay.getHours():  "0" + startDay.getHours();
	var toMM = startDay.getMinutes() > 9? startDay.getMinutes():  "0" + startDay.getMinutes();
	startDay.setDate(startDay.getDate()+ 1);
	startDay.setMinutes(startDay.getMinutes() + 30, 0, 0);
	var endHH = startDay.getHours() > 9? startDay.getHours():  "0" + startDay.getHours();
	var endMM = startDay.getMinutes() > 9? startDay.getMinutes():  "0" + startDay.getMinutes();
	var endDate = startDay.getFullYear()+"-"+( (startDay.getMonth()+1) > 9 ? (startDay.getMonth()+1) : "0" + (startDay.getMonth()+1) )+"-"+(startDay.getDate() > 9 ? startDay.getDate():"0" +startDay.getDate());
	$("#STARTTIMEHH").val("00");
	$("#STARTTIMEMI").val("00");
	$("#ENDTIMEHH").val("23");
	$("#ENDTIMEMI").val("59");
	$("#START_DAY").val(startDate);
	$("#END_DAY").val(startDate);

	var dates = $('#START_DAY, #END_DAY').datepicker({
		changeMonth: false,
		numberOfMonths: 1,
		dateFormat: 'yy-mm-dd',
		monthNames: ['년  1 월','년  2 월','년  3 월','년  4 월','년  5 월','년  6 월','년  7 월','년  8 월','년  9 월','년  10 월','년  11 월','년  12 월',],
	    dayNamesMin: ['일', '월', '화', '수', '목', '금', '토'],
	    showMonthAfterYear:true,
	    showAnim: "slide",
	    defaultDate: new Date( ),
		onSelect: function(selectedDate) {
			var option = this.id == "START_DAY" ? "minDate" : "maxDate";
			var instance = $(this).data("datepicker");
			var date = $.datepicker.parseDate(instance.settings.dateFormat || $.datepicker._defaults.dateFormat, selectedDate, instance.settings);
			dates.not(this).datepicker("option", option, date);
			$("img.ui-datepicker-trigger").attr("style", "margin-left:5px;");
		}
	});
	$("img.ui-datepicker-trigger").attr("style", "margin-left:5px;");
}

function popTextarea(receiptId){
	var url = '<c:url value="/receipt/textArea.do"/>?popType=abruptReceived';
	var windowName = "textArea";
	var popupW = 372;  // 팝업 넓이
    var popupH = 300;  // 팝업 높이
    var left = Math.ceil((window.screen.width - popupW)/2);
    var top = Math.ceil((window.screen.height - popupH)/2);
    
	popObj = window.open(url, windowName, 'width=' + popupW +  ', height=' + popupH +  ', left=' + left +  ', top=' + top + ', toolbar=no, location=no, status=no, menubar=no, scrollbars=no, resizable=no');
	
	$("#RECEIPT_ID").val(receiptId);
	
	searchFrm.method="post";
	searchFrm.action = url;
	searchFrm.target="textArea";
	searchFrm.submit();
	
	$("#RECEIPT_ID").val("");
	
}

var timeOut;
function onSidcheck(){
	var s = 1000 * Number($("#timess").val());
	if($("#sidcheck").is(":checked")){
		timeOut = setInterval("statusSearch()",s);
	}else{
		clearTimeout(timeOut);
	}
}

//제보유형 대 중 소 변경시
function codeOnChange(thisInformetTypeId,changeInformetTypeId){
	var paramObject = $("#"+thisInformetTypeId);
	var chageObject = $("#"+changeInformetTypeId);
	if(paramObject.val() == ""){
		chageObject.html("<option value='' >유형 전체</option>");
		return;
	}	
	$.ajax
	(
		{
			type : "post" ,
			url : "<c:url value='/receipt/codeChange2.do' />" ,
			dataType : "html" ,
			data : "REPORT_TYPE=" + paramObject.val(),
			cache : false ,
			success:function(html){
				chageObject.html(html);
				chageObject.change();
			} ,

			error:function(html,error){
				//alert("시스템에 문제가 생겼습니다." + error);
			}

		}
	);
}


//지역구분 변경시
/*
function regionIdOnChange(){
	$.ajax
	(
		{
			type : "post" ,
			url : "<c:url value='/receipt/getUseArea.do' />" ,
			dataType : "html" ,
			data : "REGION_ID=" + $("#search1").val(),
			cache : false ,
			success:function(html){
				$("#search10").html("<option value=''>지역</option>" +html);
			} ,

			error:function(html,error){
				//alert("시스템에 문제가 생겼습니다." + error);
			}

		}
	);
}
*/


function searchTimechange(){
	var arrTime = new Array();
	
	if($("#times").val() == "TODAY"){
		$("#timetype").val($("#times").val());
	}else{
		arrTime = $("#times").val().split(",");
		$("#time").val(arrTime[0]);
		$("#timetype").val(arrTime[1]);
	}
}

</script>
</head>
<body>
<form id="searchFrm" id="searchFrm" method="post" onsubmit="return false;"  >
<input type="hidden" name="RECEIPT_ID" id="RECEIPT_ID"  />
<div id="container">

<div id="topWrap">
 
  <!-- bodyWrap -->
  <div id="bodyWrap">
    <!-- contentWrap -->
    <div id="contentWrap">
      
      <!-- contents -->
      <div id="contents">
      <!-- 탭영역 -->
      	<!-- 
        <h1 class="txt_i10"><img src="../images/ico_tab01.gif" alt="" /><img src="../images/h1_0104.gif" alt="돌발접수이력" /><a href="<c:url value="/receipt/fullReceivedHistory.do" />" class="mglsot"><img src="../images/tab_0103_off.gif" alt="전체접수이력" /></a><a href="<c:url value="/receipt/receivedStatus.do" />"  ><img src="../images/tab_0102_off.gif" alt="금일접수현황" /></a><a href="<c:url value="/receipt/lostReceived.do" />"><img src="../images/tab_0105_off.gif" alt="분실물현황" /></a></h1>
      	 -->
        
        <div id="pop_tab">
        	<ul>
            	<li><a href="<c:url value="/receipt/receivedStatus.do" />" ><img src="../images/h1_0102_off.gif" alt="금일접수현황" /></a></li>
                <li><a href="<c:url value="/receipt/fullReceivedHistory.do" />" ><img src="../images/h1_0103_off.gif" alt="전체접수이력" /></a></li>
                <li><img src="../images/h1_0104.gif" alt="돌발접수이력" /></li>
                <li><a href="<c:url value="/receipt/lostReceived.do" />"><img src="../images/h1_0105_off.gif" alt="분실물현황" /></a></li>
            </ul>
        </div>
        <!-- board_list -->
        <div class="board_list2">
          <!-- 자동검색 영역 시작 -->
          <div class="searchwrap">
            <fieldset>
            <legend>금일접수현황 자동검색 유무</legend>
            [최근] <strong class="blue"><strong id="sysTime" id="sysTime" ></strong></strong>&nbsp;&nbsp;
            <input title="자동검색" name="sidcheck" value="Y" id="sidcheck" onclick="onSidcheck()" type="checkbox" />
            자동로딩
            <input type="hidden" id="timetype" name="timetype" value="MI" />
            <input type="hidden" id="time" name="time" value="15" />
            <select name="times" id="times" class="table_sel" onchange="searchTimechange();" style="width:110px;">
	            <option value="15,MI">15분전</option>
	            <option value="30,MI">30분전</option>
	            <option value="1,HH">1시간전</option>
	            <option value="2,HH">2시간전</option>
	            <option value="4,HH">4시간전</option>
	            <option value="8,HH">8시간전</option>
	            <option value="9,HH">9시간전</option>
	            <option value="10,HH">10시간전</option>
	            <option value="12,HH">12시간전</option>
	            <option value="24,HH">24시간전</option>
	            <option value="TODAY">오늘꺼만</option>
	        </select>
            <select name="timess" id="timess" class="table_sel" style="width:110px;">
              <option value="10">10초</option>
              <option value="20">20초</option>
              <option value="30">30초</option>
              <option value="60">60초</option>
            </select>
            </fieldset>
          </div>
          <!-- 자동검색 영역 끝 -->
          <!-- 검색조건 영역 시작 -->
          <div class="rounding_wrap">
            <div class="wrap_top"></div>
            <div class="wrap_center">
              <fieldset>
              <legend>금일접수현황 검색조건</legend>
              	<select class="table_sel" style="width:110px;" name="search1"  onchange="codeOnChange('REPORT_TYPE','REPORT_TYPE2')" name="search3" id="REPORT_TYPE">
					 <option value="" >제보유형(대)</option>
	                 <c:forEach var="reportTypeInfo" items="${reportTypeList}"   >
						 <option value=${reportTypeInfo.CODE }>
						${reportTypeInfo.CODE_NAME }
						</option>
					</c:forEach>
	              </select>
	              
	              <select class="table_sel" style="width:110px;" id="REPORT_TYPE2" name="search2" id="REPORT_TYPE2">
						 <option value="" >제보유형(중)</option>
	              </select>
	              
              	  <select name="search3" id="search3" class="table_sel" style="width:110px;">
	              	<c:forEach var="belongInfo" items="${belongList}">
		                <option value="${belongInfo.CODE}" <c:if test="${belongInfo.CODE == region}" > selected </c:if> >${belongInfo.CODE_NAME} </option>
	              	</c:forEach>
	              </select>
	              
	              <select name="search4" class="table_sel" style="width:110px;">
	                <option value="">통신원 전체</option>
	                <c:forEach var="correspondentpeInfo" items="${correspondentpeList}"   >
						 <option value=${correspondentpeInfo.CODE }>
						${correspondentpeInfo.CODE_NAME }
						</option>
					</c:forEach>
	              </select>
	              
	              <select name="search5" id="search5" class="table_sel" style="width:110px;">
	                <option value="">지역</option>
	                <c:forEach var="areaInfo" items="${areaList}"   >
						 <option value=${areaInfo.CODE }>
						${areaInfo.CODE_NAME }
						</option>
					</c:forEach>
	              </select>
		          <p class="mg5"></p>
		          
	              <input type="text" name="searchContents" id="searchContents" class="input_base" onkeyup="if(event.keyCode == 13)statusSearch();" maxlength="15"   />
	              <img src="../images/btn_search.gif" onclick="statusSearch()" style="cursor:pointer;"  alt="검색" />
	              </fieldset>
            </div>
            <div class="wrap_bottom"></div>
          </div>
          <!-- 검색조건 영역 끝 -->
          <!-- 전송/방송/지도 아이콘 설명 시작 -->
		<div class ="icon-comment">
			<img src="../images/ico_send.png" alt="전송여부" /><span>전송됨</span>
			<img src="../images/btn_pd2.png" alt="방송상태" /><span>방송됨(PD)</span>
			<img src="../images/btn_caster2.png" alt="방송상태" />방송됨(캐스터)
			<img src="../images/ico_map.gif" alt="위치정보" />위치 선택됨
			<img src="../images/ico_no2.png" alt="전송여부" />항목 해당없음
		</div>
		<!-- 전송/방송/지도 아이콘 설명 끝 -->
          <!-- 검색결과 시작 -->
          <div id="contentsDiv" >
          <p class="list_result txt_i10"><img src="../images/ico_result.gif" alt="" />검색결과 ${receiptTodayListSIze}건</p>
          <div class="list_result_sc">
            <table summary="금일접수현황" border="0" cellpadding="0" cellspacing="0" class="list01">
              <caption>
              금일접수현황 검색결과
              </caption>
              <colgroup>
              <col width="60" />
              <col width="50" />
              <col width="30" />
              <col width="100" />
              <col width="100" />
              <col width="50" />
              <col width="80" />
              <col width="*" />
              </colgroup>
              <thead>
                <tr>
                  <th scope="col">일자</th>
                  <th scope="col">시간</th>
                  <th scope="col">유형</th>
                  <th scope="col">유형(중)</th>
                  <th scope="col">제보자</th>
                  <th scope="col">구분</th>
                  <th scope="col">접수자</th>
                  <th scope="col">내용</th>
                  </tr>
              </thead>
              <tbody>
                  <!-- roof -->
                <c:forEach var="receiptTodayInfo" items="${receiptTodayList }" >  
                <tr style="cursor:pointer;" onclick="popTextarea('${receiptTodayInfo.RECEIPT_ID }');">
                  <td>${receiptTodayInfo.RECEIPT_DAY }</td>
                  <td>${receiptTodayInfo.RECEIPT_TIME }</td>
                  <td>${receiptTodayInfo.REPORT_TYPE_NM }</td>
                  <td>${receiptTodayInfo.REPORT_TYPE_NM2 }</td>
                  <td>${receiptTodayInfo.INFORMER_NAME }</td>
                  <td>${receiptTodayInfo.INFORMER_TYPE_NM }</td>
                  <td>${receiptTodayInfo.USER_NAME }</td>
                  <td class="txt_left" onclick="popTextarea('${receiptTodayInfo.RECEIPT_ID }');" >
                  ${receiptTodayInfo.CONTENTS }
                  </td>
                </tr>
                </c:forEach>
              </tbody>
            </table>
          </div>
          <!-- 검색결과 끝 -->
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
