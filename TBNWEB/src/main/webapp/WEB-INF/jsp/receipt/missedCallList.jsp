<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script>
$(document).ready(function(){
	//console.log("부재중2 : "+currentTimeSmall());
});

</script>
<c:if test="${missedCallListSize == 0 }">
	<li class="noMissedCall">부재중 전화 목록이 없습니다.</li>
</c:if>
<c:if test="${missedCallListSize != 0 }" >
	<c:forEach var="missedCallVO" items="${missedCallList}" >
		<c:choose>
			<c:when test="${missedCallVO.FLAG_READ == 'N' }">
    	   		<li onclick="getSttContent('${missedCallVO.INFORMER_ID }','${missedCallVO.MISSED_CALL_TEL }','${missedCallVO.MISSED_CALL_ID }')" style="cursor: pointer;">
	     	   		<span class="stt" style="width:140px;">${missedCallVO.MISSED_CALL_TIME }</span>
					<span class="stt" style="width:100px;">${missedCallVO.MISSED_CALL_TEL }</span>
					<span class="stt" style="width:60px;">${missedCallVO.INFORMER_NAME }</span>
				</li>
     	   	</c:when>
    	   	<c:otherwise>
    	   		<li onclick="getSttContent('${missedCallVO.INFORMER_ID }','${missedCallVO.MISSED_CALL_TEL }','${missedCallVO.MISSED_CALL_ID }')" style="cursor: pointer;">
					<span style="width:140px;">${missedCallVO.MISSED_CALL_TIME }</span>
					<span style="width:100px;">${missedCallVO.MISSED_CALL_TEL }</span>
					<span style="width:60px;">${missedCallVO.INFORMER_NAME }</span>
				</li>
    	   	</c:otherwise>
		</c:choose>
	</c:forEach>
</c:if>

