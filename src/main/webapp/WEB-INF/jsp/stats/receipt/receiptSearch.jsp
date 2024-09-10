<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<link rel="stylesheet" type="text/css" href="<c:url value="/css/jquery-ui-1.9.0.custom.css"/>" rel="stylesheet"  />
<script type="text/javascript" charset="utf-8" src="<c:url value='/js/common/jquery/jquery-ui-1.9.0.custom.js'/>"></script>
<script type="text/javascript" charset="utf-8" src="<c:url value='/js/common/jquery/jquery-ui-1.9.0.custom.min.js'/>"></script>
<script>
$(document).ready(function(){
	var dates = $('#fromdate, #todate').datepicker({
		showOn: "button",
	    buttonImage: "<c:url value="/images/ico_calendar.gif"/>",
	    buttonText: "",
	    buttonImageOnly: true,
		changeMonth: false,
		numberOfMonths: 1,
		dateFormat: 'yy-mm-dd',
		monthNames: ['년  1 월','년  2 월','년  3 월','년  4 월','년  5 월','년  6 월','년  7 월','년  8 월','년  9 월','년  10 월','년  11 월','년  12 월',],
	    dayNamesMin: ['일', '월', '화', '수', '목', '금', '토'],
	    showMonthAfterYear:true,
	    showAnim: "slide",
	    defaultDate: new Date( ),
		onSelect: function(selectedDate) {
			var option = this.id == "fromdate" ? "minDate" : "maxDate";
			var instance = $(this).data("datepicker");
			var date = $.datepicker.parseDate(instance.settings.dateFormat || $.datepicker._defaults.dateFormat, selectedDate, instance.settings);
			dates.not(this).datepicker("option", option, date);
			$("img.ui-datepicker-trigger").attr("style", "margin-left:5px;");
		}
	});
	$("img.ui-datepicker-trigger").attr("style", "margin-left:5px;");
	
	var today = new Date();
	var todate = today.getFullYear()+"-"+( (today.getMonth()+1) > 9 ? (today.getMonth()+1) : "0" + (today.getMonth()+1) )+"-"+ (today.getDate() > 9 ? today.getDate() : "0" + today.getDate() );
//	today.setMonth(today.getMonth()-1);
	var fromdate = today.getFullYear()+"-"+( (today.getMonth()+1) > 9 ? (today.getMonth()+1) : "0" + (today.getMonth()+1) )+"-"+ (today.getDate() > 9 ? today.getDate() : "0" + today.getDate() );
	
	$("#fromdate").val(fromdate);
	$("#todate").val(todate);
});
</script>
<form id="frmReceiptSearch" name="frmReceiptSearch" method="post">
<input type="hidden" name="from_date" id="from_date">
<input type="hidden" name="to_date" id="to_date">
<input type="hidden" name="MIN" id="MIN" >
     <div class="wrap_top"></div>
     <div class="wrap_center">
    	<fieldset>
	        <legend>제보접수현황 검색조건</legend>
	        시작일
	        <input id="fromdate" name="fromdate" maxlength="15"  class="input_base" readonly="readonly"  alt="시작일" title="" value="" style="width:70px;align:center;"/> ~ 
	        종료일
	        <input id="todate" name="todate" maxlength="15"  class="input_base"  readonly="readonly" alt="종료일" title="" value="" style="width:70px;align:center;"/>
	        <select id="search_send" name="search_send" class="table_sel" style="width:80px;">
		        <option value="">전송여부</option>
		        <option value="Y">전송</option>
		        <option value="N">미전송</option>
		    </select>
		    <select id="search_important" name="search_important" class="table_sel" style="width:80px;">
		        <option value="">중요여부</option>
		        <option value="Y">중요</option>
		        <option value="N">일반</option>
		    </select>
		    <select id="search_broad" name="search_broad" class="table_sel" style="width:80px;">
		        <option value="">방송여부</option>
		        <option value="Y">방송</option>
		        <option value="N">미방송</option>
		    </select>
		    <select id="search_sns" name="search_sns" class="table_sel" style="width:80px;">
		        <option value="">SNS</option>
		        <option value="Y">SNS등록</option>
		        <option value="N">SNS미등록</option>
		    </select>
		    <p class="mg5"></p>
		    <select id="search_reportType" name="search_reportType" class="table_sel" style="width:90px;">
		        <option value="">제보유형</option>
		        <c:forEach var="list" items="${reportType}" varStatus="num" >
		        <option value="${list.CODE }">${list.CODE_NAME }</option>
		        </c:forEach>
		    </select>
		    <select id="search_informerType" name="search_informerType" class="table_sel" style="width:110px;">
		        <option value="">제보자구분</option>
		        <c:forEach var="list" items="${informerType}" varStatus="num" >
		        <option value="${list.CODE }">${list.CODE_NAME }</option>
		        </c:forEach>
		    </select>
			<select id="search_reportmeanType" name="search_reportmeanType" class="table_sel" style="width:80px;">
		    	<option value="">제보수단</option>
		        <c:forEach var="list" items="${meanList}" varStatus="num" >
		        <option value="${list.CODE }">${list.CODE_NAME }</option>
		        </c:forEach>
		    </select>
		    <select id="search_user" name="search_user" class="table_sel" style="width:80px;">
		    	<option value="">접수자</option>
		        <c:forEach var="list" items="${userList}" varStatus="num" >
		        <option value="${list.USER_ID }">${list.USER_NAME }</option>
		        </c:forEach>
		    </select>
		    <input type="text" id="search_contents" name="search_contents" maxlength="15"  class="txt" alt="내용" title="내용" value="" style="ime-mode:disabled;width:90px;"/>
		    <img src="../images/btn_search.gif"  alt="검색" onclick="javascript:goReceiptSearch();" style="cursor:pointer;" />
	    </fieldset>
	</div>
	<div class="wrap_bottom"></div>
</form>