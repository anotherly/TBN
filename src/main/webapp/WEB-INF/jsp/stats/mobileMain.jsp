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
	
	$("#fromdate").val("2013-01-01");
	$("#todate").val("2013-01-31");
});
</script>
<script>
function openMobilePop(){
	window.open("<c:url value="/jsp/stats/mobile/mobilePop.jsp"/>", "", 'width=475, height=530, toolbar=no, location=no, status=no, menubar=no, scrollbars=no, resizable=no');
}
function goStats(url){
	/* $('#start_date').val($('#fromdate').val().replace("-","").replace("-",""));
	$('#end_date').val($('#todate').val().replace("-","").replace("-","")); */
	frmExcel.action = '<c:url value="/"/>'+url;
	frmExcel.submit();
}
</script>
    <div id="contentWrap">
        <!-- <div id="posi"><img src="../images/ico_home.gif" alt="home" />통계관리 > 모바일통계</div> -->
        <!-- contents -->
                <div id="contents">
                    <h1 class="txt_i10"><img src="../images/ad_h1_0506.gif" alt="모바일통계" /></h1>
                    <!-- board_list -->
                    <div class="board_list">
                        <!-- 검색조건 영역 시작 -->
                        <form id="frmExcel" name="frmExcel" method="post">
                       <!--  <input type="hidden" id="start_date" name="start_date">
                        <input type="hidden" id="end_date" name="end_date"> -->
                        <div class="rounding_wrap mgt10">
                            <div class="wrap_top"></div>
                            <div class="wrap_center">
                                <fieldset>
                                <legend>금일접수현황 검색조건</legend>
                                시작일
						        <input id="fromdate" name="fromdate" maxlength="15"  class="input_base" readonly="readonly"  alt="시작일" title="" value="" style="width:70px;align:center;"/> ~ 
						        종료일
						        <input id="todate" name="todate" maxlength="15"  class="input_base"  readonly="readonly" alt="종료일" title="" value="" style="width:70px;align:center;"/>
                                <input type="image" src="../images/btn_search.gif"  alt="검색" />
                                </fieldset>
                            </div>
                            <div class="wrap_bottom"></div>
                        </div>
                        </form>
                        <!-- 검색조건 영역 끝 -->
                        <!-- 검색결과 시작-->
                         <p style="float: right; cursor: pointer; margin-bottom:5px;"><button type="button" class="mglbtn mgbt3" onclick="javascript:openMobilePop();"><span>문자정보입력</span></button></p>
                        <div class="admin_result_sc">
                            <table border="0" cellpadding="0" cellspacing="0" class="list01">
                                
                                <colgroup>                                
                                <col width="*" />
                                <col width="200" />
                                </colgroup>
                                <thead>
                                    <tr>
                                        <th scope="col">내용</th>
                                        <th scope="col">다운로드</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <!-- roof -->
                                        <td class="txt_left"><img src="../images/ico_excel.gif" alt="" class="mglsub03" /><a href="javascript:goStats('stats/mobileStats.do');">모바일 문자정보 수집 및 활용실적</a></td>
                                        <td><a href="javascript:goStats('stats/mobileStats.do');"><img src="../images/btn_excel_down.gif" alt="엑셀다운로드" /></a></td>
                                    </tr>
                                    <tr>
                                        <!-- roof -->
                                        <td class="txt_left"><img src="../images/ico_excel.gif" alt="" class="mglsub03" /><a href="javascript:goStats('stats/dailySNSStats.do');">SNS전송 실적</a></td>
                                        <td><a href="javascript:goStats('stats/dailySNSStats.do');"><img src="../images/btn_excel_down.gif" alt="엑셀다운로드" /></a></td>
                                    </tr>
                                </tbody>
                            </table>
                        </div>
                        <!-- 검색결과 끝-->
                        
                    </div>
                </div>
                <!-- //contents -->
    </div>
