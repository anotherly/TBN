<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="ko" lang="ko">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/layout.css"/>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/pagination.css" />
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/jquery-ui-1.9.0.custom.css" rel="stylesheet"  />
	<script  type="text/javascript" charset="utf-8" src="<%=request.getContextPath()%>/js/jquery.js"></script>
	<script  type="text/javascript" charset="utf-8"  src="<%=request.getContextPath()%>/js/common.js"></script>
	<script type="text/javascript" charset="utf-8" src="<%=request.getContextPath()%>/js/jquery.pagination.js"></script>
	<script type="text/javascript" charset="utf-8" src="<%=request.getContextPath()%>/js/jquery.form.js"></script>
	
	<script src="<%=request.getContextPath()%>/calender/moment.js"></script>
<script src="<%=request.getContextPath()%>/calender/mo_ko.js"></script>
<script src="<%=request.getContextPath()%>/calender/bootstrap-datetimepicker.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/calender/no-boot-calendar-custom.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/calender/datetimepickerstyle.css" />
	
	
	<!-- datepicker를 사용하기 위해서는 jquery 파일과 아래 파일 2개가 필요함 -->
<!-- 	<script type="text/javascript" charset="utf-8" src="<%=request.getContextPath()%>/js/jquery-1.12.4.js"></script>
	<script type="text/javascript" charset="utf-8" src="<%=request.getContextPath()%>/js/jquery-ui.js"></script>
 -->
	
<%-- 팝업 스타일이 이상해서 임시 추가, 디자인 변경시 css 추가 해야함. --%>
<style type="text/css">
    .strong {width: 120px;}
    .input_base {width: 175px;}
    .admin_edit2 td {font-size: 11px;}
    .input_base {font-size: 11px; height: 20px;}
</style>
</head>
<body>
<div id="contents">
    <c:if test="${eventInfo.EVENT_ID eq null }">
        <h1 class="pabt8 admin_tit"><img src="<c:url value="/images/ico_tab01.gif"/>" alt="행사등록" />행사등록</h1>
    </c:if>
    <c:if test="${eventInfo.EVENT_ID ne null }">
        <h1 class="pabt8 admin_tit"><img src="<c:url value="/images/ico_tab01.gif"/>" alt="행사 상세정보" />행사수정</h1>
    </c:if>
</div>
<div class="board_list">
    <div class="admin_edit2">
        <table width="100%" border="0" cellspacing="0" cellpadding="0" >
            <tr>
                <td>
                    <form id="editEventFrm" name="editEventFrm" enctype="multipart/form-data">
                    <input type="hidden" id="EVENT_ID" name="EVENT_ID" value="${eventInfo.EVENT_ID}"/>
                    <input type="hidden" id="editDiv" name="editDiv" value="${editDiv}"/>
                    <table width="100%" border="0" cellspacing="0" cellpadding="0" class="admin_list">
                         <tr>
                             <td class="strong">행사일</td>
                             <td><input class="input_base" type="text" id="EVENT_DATE" name="EVENT_DATE" style="width: 250px;" value="${eventInfo.EVENT_DATE }" readonly/></td>
                             <td><input class="input_base" type="hidden" id="REGION_ID" name="REGION_ID"/></td>
                         </tr>
                         <tr>
                             <td class="strong">행사명</td>
                             <td><input class="input_base" type="text" id="EVENT_NAME" name="EVENT_NAME" style="width: 273px;" value="${eventInfo.EVENT_NAME }"/></td>
                         </tr>
                         <tr>
                             <td class="strong">내용</td>
                             <td>
                                <textarea id="CONTENTS" name="CONTENTS" rows="15" cols="50"><c:out value="${eventInfo.CONTENTS }" /></textarea>
                             </td>
                         </tr>
                          <tr>
                         	<td class="strong">파일 첨부</td>
                         	<td>
                         		<input type="file" class="input_base" name="multiFile" multiple/>
                         		<c:if test="${fn:length(fileInfo) > 0}"> <!-- 첨부된 파일이 1개라도 있다면 -->
                         			<c:forEach var="file" items="${fileInfo}" varStatus="status">
                         			<div style="display:flex;">
									    <p style="margin-left: 15px;" id="${file.FILE_ID}">${file.FILE_NAME}</p> <p style="color:blue; margin-left:15px; cursor:pointer;" id="${file.FILE_ID}" class="deleteBtn">삭제</p>
									</div>
									<br>
									</c:forEach>
                         		</c:if>
                         	</td>
                         </tr> 
                     </table>
                    </form>
                </td>
            </tr>
        </table>
        <!-- 등록버튼 시작-->
        <div class="btnArg mgt10">
            <input onclick="saveEvent();"  style="height:31px;" type="image" src="<c:url value="/images/btn_reget1.png"/>" alt="저장" title="저장"  />
            <input onclick="self.close();"  style="height:31px;"  type="image" src="<c:url value="/images/btn_cl.png"/>" alt="취소" title="취소"  />
            <%-- <input id="delBtn" onclick="deleteEvent(); "type="image" src="<c:url value="/images/btn_delb.gif"/>" alt="삭제" title="삭제"  /> --%>
        </div>
        <!-- 등록버튼 끝-->
    </div>
</div>
<script>
$(document).ready(function(){
	console.log("이벤트 등록 또는 수정 창 최초");
	var opVal =opener.$("#areaOptSel").val();
	var opVal2 =opener.$("#REGION_ID").val();
	if(typeof opVal !== "undefined"){
		$("#REGION_ID").val(opVal);
	}
	if(typeof opVal2 !== "undefined"){
		$("#REGION_ID").val(opVal2);
	}
	
	/* var dates = $('#EVENT_DATE').datepicker({
		showOn: "both", // button : 버튼 클릭 시 달력 열림 , both : text나 button 둘 다 눌러도 달력 열림
        buttonImage: "<c:url value="/images/ico_calendar.gif"/>",
        buttonText: "달력",
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
            //dates.not(this).datepicker("option", option, date);
            $("img.ui-datepicker-trigger").attr("style", "margin-left:5px;");
        }
    }); */

	var stdate=moment().subtract(1, 'months').startOf('month').format('YYYY-MM-DD');
	/* var endate=moment().subtract(1, 'months').endOf('month').format('YYYY-MM-DD'); */
	//common.js에 생성한 함수 참조(달력생성)
	dateFunc('EVENT_DATE',stdate);
	
})

/**
 * 행사 저장
 */
function saveEvent(){
	console.log("이벤트 등록 또는 수정");
    if ($('#EVENT_NAME').val() == ""){
        alert("행사명을 입력하세요.");
        return false;
    } else if ($('#EVENT_DATE').val() == ""){
    	alert("행사 일자를 입력하세요.");
    	return false;
    }
    
    var date = $('#EVENT_DATE').val().replace(/-/g,'');
    $('#EVENT_DATE').val(date);
    let frm = $('#editEventFrm').serialize();
    console.log("frm : "+frm);
    var options = {
            url:"/informer/event/saveEvent.do",
            type:'post',
            data: frm,
            dataType: "json",
            success: function(res){
                if(res.cnt > 0){
                    alert("저장되었습니다.");
                    if($('#EVENT_ID').val() != null && $('#EVENT_ID').val() != ""){
                    	opener.chgEvent($('#EVENT_ID').val());
                    } else {
                    	opener.init();
                    }
                    self.close();
                } else {
                    alert("저장에 실패하였습니다.");
                }
            } ,
            error: function(res,error){
                alert("에러가 발생했습니다."+res);
            }
    };
    $('#editEventFrm').ajaxSubmit(options);
}


$(".deleteBtn").click(function() {
    var fileId = $(this).attr("id");
	var eventId = $('#EVENT_ID').val();
	
	$(this).hide(); // 클릭된 삭제 버튼 숨기기
    $(this).siblings().hide(); // 클릭된 요소의 형제 요소 숨기기
    
    var options = {
            url:"/informer/event/eventOneDelete.do",
            type:'post',
            data: {
            	fileId: fileId , eventId : eventId
            },
            dataType: "json",
            success: function(res, html){
                if(res.cnt > 0){
                    alert("삭제되었습니다.");
                    /* $('#loadMain').click(); */
                    
                    var hideFile = res.fileId;
                    console.log("확인하기" + hideFile);
                    console.log("이전 :" +fileId );
                    
                /*     $('#'+hideFile).hide();
 */

                } else {
         			alert("삭제에 실패하였습니다.");
                }
            } ,
            error: function(res,error){
                alert("에러가 발생했습니다."+error);
            }
    };
    if(confirm('해당 첨부 파일을 삭제 하시겠습니까?')){
        $.ajax(options);
    }
    
});
</script>
</body>
</html>