<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>TBN한국교통방송 제보접수시스템</title>
<link rel="stylesheet" type="text/css" href="<c:url value="/css/layout.css"/>" />
<link rel="stylesheet" type="text/css" href="<c:url value="/css/jquery-ui-1.9.0.custom.css"/>" rel="stylesheet"  />
<script type="text/javascript" charset="utf-8" src="<c:url value='/js/common/jquery/jquery-1.6.4.min.js'/>"></script>
<script type="text/javascript" charset="utf-8" src="<c:url value='/js/common/jquery/jquery-ui-1.9.0.custom.js'/>"></script>
<script type="text/javascript" charset="utf-8" src="<c:url value='/js/common/jquery/jquery-ui-1.9.0.custom.min.js'/>"></script>
<script>
$(document).ready(function(){
	var dates = $('#statdate').datepicker({
		showOn: "button",
	    buttonImage: "<c:url value="/images/ico_calendar.gif"/>",
	    buttonText: "날짜",
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
			var instance = $(this).data("datepicker");
			var date = $.datepicker.parseDate(instance.settings.dateFormat || $.datepicker._defaults.dateFormat, selectedDate, instance.settings);
			$("img.ui-datepicker-trigger").attr("style", "margin-left:5px;");
		}
	});
	$("img.ui-datepicker-trigger").attr("style", "margin-left:5px;");
	
	var today = new Date();
	var todate = today.getFullYear()+"-"+( (today.getMonth()+1) > 9 ? (today.getMonth()+1) : "0" + (today.getMonth()+1) )+"-"+ (today.getDate() > 9 ? today.getDate() : "0" + today.getDate() );
	var fromdate = today.getFullYear()+"-"+( (today.getMonth()+1) > 9 ? (today.getMonth()+1) : "0" + (today.getMonth()+1) )+"-"+ (today.getDate() > 9 ? today.getDate() : "0" + today.getDate() );
	
	$("#statdate").val(todate);
	
	loadList();
});

function goSave(){
	$("#stat_date").val($("#statdate").val().replace("-","").replace("-",""));
	var options = {
	        url:'<c:url value="/stats/mobileInsert.do"/>',
	        type:'post',
	        data: $('#frmMobile').serialize(),
	        dataType: 'json',
	        success: function(res){
	            if(res.result > 0){
	            	alert("저장되었습니다.");
	            	loadList();	            	
	            } else {
	            	alert("저장에 실패하였습니다.");
	            }
	        } ,
	        error: function(res,error){
	        	alert("에러가 발생했습니다."+error);
	        }
	};
	$.ajax(options);
}

function loadList(){
	$('#mobileList').load('<c:url value="/stats/mobileList.do"/>');
}

</script>
</head>
<body>
<!-- contents size 475*530 -->
<div id="blue_tit">
    <h1 class="bg_blue"><img src="../../../images/txt_tit.gif" alt="모바일문자정보" /></h1>
<!-- input -->
<form id="frmMobile" name="frmMobile" method="post">
<input type="hidden" id="stat_date" name="stat_date" />
    <div class="zipcode_tab03">
        <p>* 다음항목을 입력하여 주십시오.</p>
        <div class="zipcode_sch2"> 날짜
            <input type="text" class="input_base" style="width:80px;" id="statdate" name="statdate" readonly="readonly" />
            총문자정보
            <input id="sms_cnt" name="sms_cnt" type="text" class="input_base" style="width:60px;"/>
            건 / 
            교통정보
            <input id="traffic_cnt" name="traffic_cnt" type="text" class="input_base" style="width:60px;"/>
            건 </div>
    </div>
</form>
<!-- list -->
    <div id="mobileList" class="zipcode_tab02">
    <!-- mobileList.jsp -->
    </div>
    <div class="btnArg">
        <img src="../../../images/btn_saveb.gif" alt="저장" title="저장"  onclick="javascript:goSave();"/>
        <img src="../../../images/btn_delb.gif" alt="삭제" title="삭제" />
    </div>
</div>
</body>
</html>