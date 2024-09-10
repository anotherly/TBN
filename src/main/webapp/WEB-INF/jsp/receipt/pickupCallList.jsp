<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script>
$(document).ready(function(){
	//console.log("수신목록2 : "+currentTimeSmall());
});
</script>
<c:if test="${pickupCallListSize == 0 }">
	<li class="noMissedCall">금일 수신 전화 목록이 없습니다.</li>
</c:if>
<c:if test="${receivedCallListSize != 0 }" >
	<c:forEach var="receivedCallVO" items="${receivedCallList}" >
		<li onclick="getSttContent('${receivedCallVO.INFORMER_ID }','${receivedCallVO.RECEIVED_CALL_TEL }','${receivedCallVO.RECEIVED_CALL_ID }')" style="cursor: pointer;">
			<span style="width:140px;">${receivedCallVO.RECEIVED_CALL_TIME}</span>
			<span style="width:100px;">${receivedCallVO.RECEIVED_CALL_TEL}</span>
			<span style="width:60px;">${receivedCallVO.INFORMER_NAME }</span>
		</li>
	</c:forEach>
</c:if>
