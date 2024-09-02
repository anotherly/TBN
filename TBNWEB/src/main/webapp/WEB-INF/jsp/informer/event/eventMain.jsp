<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="eventDiv">
</div>
<script>
$(document).ready(function(){
    loadMain();
})

/**
 * 이벤트 목록(초기 화면)
 */
function loadMain(){
	$('#eventDiv').load('<c:url value="/informer/event/eventMainContent.do"/>');
}

/**
 * 이벤트 상세 정보(이벤트 클릭 시)
 */
function detailEvent(str){
	console.log('이벤트 상세 정보(이벤트 클릭 시)');
	selArea=$("#areaOptSel option:selected").val();
    $('#eventDiv').load('<c:url value="/informer/event/detailEventView.do"/>?EVENT_ID=' + str+"&REGION_ID="+selArea);
}
</script>