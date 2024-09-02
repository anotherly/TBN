<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- <div id="posi"><img src="../images/ico_home.gif" alt="home" />제보자 관리 > 행사관리</div> -->
<style type="text/css">
    #attendanceListlDiv .input_base {padding: 0px; margin: 0px;}
</style>
<!-- contents -->
<div id="contents">
    <h1 class="txt_i10"><img src="<c:url value='/images/ad_h1_0303.gif' />" alt="행사관리" /></h1>
    <!-- 상세정보 시작 -->
    <div id="admin_view">
        <div id="eventDetailDiv">
	        <table summary="행사관리 상세정보" width="100%" border="0" cellspacing="0" cellpadding="0" class="view">
	            <caption>
	            행사관리 상세정보
	            </caption>
	            <colgroup>
	            <col width="50" />
	            <col width="*" />
	            </colgroup>
	            <tr>
	                <td class="b">행사일</td>
	                <td><input class="input_base" style="width:100px;" type="text" id="EVENT_DATE" name="EVENT_DATE" value="${eventInfo.EVENT_DATE }" readonly/></td>
	                <td><input class="input_base" style="width:100px;" type="hidden" id="REGION_ID" name="REGION_ID" value="${eventInfo.REGION_ID }" readonly/></td>
	            </tr>
	            <tr>
	                <td class="b">행사명</td>
	                <td><input class="input_base" type="text" id="EVENT_NAME" name="EVENT_NAME" style="width: 250px;" value="${eventInfo.EVENT_NAME }" readonly/></td>
	            </tr>
	            <tr>
	                <td class="b">내용</td>
	                <td><textarea class="table_sel" id="CONTENTS" name="CONTENTS" cols="50" rows="8" style="width:97%; height:150px;" readonly><c:out value="${eventInfo.CONTENTS }" /></textarea></td>
	            </tr>
	        </table>
	    </div>
        <table summary="참석자정보" width="100%" border="0" cellspacing="0" cellpadding="0" class="view">
            <caption>
            행사관리 상세정보
            </caption>
            <colgroup>
            <col width="50" />
            </colgroup>
            <tr>
                <td class="b">참석자</td>
                <td height="200" valign="top">
                <!-- 참석자 리스트 시작-->
                    <div class="view_attendee">
                        <div class="board_list">
                            <table summary="통신원현황" border="0" cellpadding="0" cellspacing="0" class="list01">
                                <caption>
                                통신원 검색결과
                                </caption>
                                <colgroup>
                                <col width="50" />
                                <col width="50" />
                                <col width="100" />
                                <col width="110" />
                                <col width="130" />
                                <col width="*" />
                                <col width="50" />
                                </colgroup>
                                <thead>
                                    <tr>
                                        <th scope="col">ID</th>
                                        <th scope="col">활동여부</th>
                                        <th scope="col">소속</th>
                                        <th scope="col">이름</th>
                                        <th scope="col">전화</th>
                                        <th scope="col">주소</th>
                                        <th scope="col">삭제</th>
                                    </tr>
                                </thead>
                            </table>
                            <div id="attendanceListlDiv" class="list_result_sc8" style="margin-top: 0px;">
                                <table summary="참석자목록" border="0" cellpadding="0" cellspacing="0" class="list02">
                                <caption>
	                                참석자목록
	                            </caption>
                                <colgroup>
		                            <col width="50" />
	                                <col width="50" />
	                                <col width="100" />
	                                <col width="110" />
	                                <col width="130" />
	                                <col width="*" />
	                                <col width="50" />
		                        </colgroup>
                                <tbody>
                                <c:if test="${empty attendanceList}">
                                    <tr>
                                        <td colspan="16"><c:out value="참석자를 등록하세요."/></td>
                                    </tr>
                                </c:if>
                                <c:if test="${!empty attendanceList}">
                                    <c:forEach var="attendance" items="${attendanceList}" varStatus="idx">
                                        <tr id="${idx.count}">
                                            <td><c:out value="${attendance.actId}"/></td>
                                            <td><c:out value="${attendance.flagAct == 'Y' ? '위촉' : '해촉'}"/></td>
                                            <td><c:out value="${attendance.areaName}"/></td>
                                            <td><c:out value="${attendance.informerName}"/></td>
                                            <td><c:out value="${attendance.phoneCell}"/></td>
                                            <td><c:out value="${attendance.addressHome}"/></td>
                                            <td><input type="button" class="input_base" id="deleteAttendanceBtn" name="deleteAttendanceBtn" onclick="deleteAttendance('${attendance.informerId}', $(this))" value="삭제" style="cursor: pointer;"/></td>
                                        </tr>
                                    </c:forEach>
                                </c:if>
                                </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                    <!-- 참석자 리스트 끝-->
                    <p class="txt_right pat8 par15"><img onclick="editEventAttendance('${eventInfo.EVENT_ID}');" src="../images/btn_add.gif" alt="참석자등록" /></p>
                </td>
            </tr>
        </table>
    </div>
    <!-- 상세정보 끝 -->
    <div class="btnArg">
        <input class="btnMgt10" id="loadMain" onclick="loadMain();" type="image" src="/images/btnList.png" alt="목록" title="목록">
        <input class="btnMgt10" onclick="editEvent('${eventInfo.EVENT_ID}');" type="image" src="/images/btnUpdate.png" alt="수정" title="수정">
        <input class="btnMgt10" onclick="deleteEvent('${eventInfo.EVENT_ID}');" type="image" src="/images/btn_del.png" alt="삭제" title="삭제">
    </div>
</div>
<!-- //contents -->
<script>
$(document).ready(function(){
	console.log("행사관리 상세 화면 : eventDetailView.jsp");
	$('.input_base').keydown(function(e) {
		if(e.keyCode==13){
			//기존 엔터버튼 누를 시 화면누락 방지 및 추가 및 수정 버튼클릭과 동일한 효과를 줌(트리거)
			e.preventDefault();
			search();
		}
	});
})

/**
 * 이벤트 수정시 변경내역 반영
 */
function chgEvent(str){
	$('#eventDetailDiv').load('<c:url value="/informer/event/detailEvent.do"/>?EVENT_ID=' + str);
}

/**
 * 참석자 등록시, 등록내역 반영
 */
function chgAttendance(str){
	$('#attendanceListlDiv').load('<c:url value="/informer/event/detailEventAttendance.do"/>?EVENT_ID=' + str);
}

/**
 * 이벤트 수정 팝업
 */
function editEvent(str){
	console.log($("#REGION_ID").val());
    var param = "";
    if(str != null && str != "" && str != "undefined"){
        param = "?EVENT_ID=" + str+"&REGION_ID="+$("#REGION_ID").val();
    }
    var url = '<c:url value="/informer/event/editEvent.do"/>' + param;
    var windowName = "행사등록";
    var popupW = 550;  // 팝업 넓이
    var popupH = 420;  // 팝업 높이
    var left = Math.ceil((window.screen.width - popupW)/2);
    var top = Math.ceil((window.screen.height - popupH)/2);
    popObj = window.open(url, windowName, 'width=' + popupW +  ', height=' + popupH +  ', left=' + left +  ', top=' + top + ', toolbar=no, location=no, status=no, menubar=no, scrollbars=no, resizable=no');
}

/**
 * 참석자 등록 팝업
 */
function editEventAttendance(str){
    var param = "";
    if(str != null && str != "" && str != "undefined"){
        param = "?EVENT_ID=" + str;
    }
    var url = '<c:url value="/informer/event/editEventAttendance.do"/>' + param;
    var windowName = "행사등록";
    var popupW = 770;  // 팝업 넓이
    var popupH = 500;  // 팝업 높이
    var left = Math.ceil((window.screen.width - popupW)/2);
    var top = Math.ceil((window.screen.height - popupH)/2);
    popObj = window.open(url, windowName, 'width=' + popupW +  ', height=' + popupH +  ', left=' + left +  ', top=' + top + ', toolbar=no, location=no, status=no, menubar=no, scrollbars=no, resizable=no');
}

/**
 * 행사 삭제
 */
function deleteEvent(str){
    var options = {
            url:'<c:url value="/informer/event/deleteEvent.do"/>',
            type:'post',
            data: {
            	EVENT_ID: str
            },
            dataType: "json",
            success: function(res, html){
                if(res.cnt > 0){
                    alert("삭제되었습니다.");
                    $('#loadMain').click();
                } else {
                    alert("삭제에 실패하였습니다.");
                }
            } ,
            error: function(res,error){
                alert("에러가 발생했습니다."+error);
            }
    };
    if(confirm('삭제 하시겠습니까?')){
        $.ajax(options);
    }
}

/**
 * 참석자 삭제
 */
function deleteAttendance(str, obj){
	var options = {
            url:'<c:url value="/informer/event/deleteAttendance.do"/>',
            type:'post',
            data: {
            	EVENT_ID: '${eventInfo.EVENT_ID}',
            	INFORMER_ID: str
            },
            dataType: "json",
            success: function(res, html){
                if(res.cnt > 0){
                    alert("삭제되었습니다.");
                    obj.parent().parent().remove();
                    if(obj.parent().parent().attr('id') == 1){
                    	chgAttendance('${eventInfo.EVENT_ID}');
                    }
                } else {
                    alert("삭제에 실패하였습니다.");
                }
            } ,
            error: function(res,error){
                alert("에러가 발생했습니다."+error);
            }
    };
    $.ajax(options);
}
</script>