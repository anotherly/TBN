<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<script>
$(document).ready(function(){
	console.log("로그인 이력조회 진입");
	if($("#history_table tbody").prop('scrollHeight') - $("#history_table tbody").prop('clientHeight') > 0){
		//스크롤바 생길 시 테이블 밀림 방지
	    $("#history_table th:last-child").css('padding-right','17px');
	}
});
</script>
<div class="board_list">
	<div class="list_result_sc">
	    <table summary="사용자목록" border="0" cellpadding="0" cellspacing="0" class="list01" id="history_table">
	    <caption>
	       사용자목록
	    </caption>
	    <thead>
	        <tr>
	            <th>ID</th>
	            <th>소속</th>
	            <th>이름</th>
	            <th>접속시작시각</th>
	            <th>접속종료시각</th>
	            <th>사용 시간(분)</th>
	        </tr>
	    </thead>
	    <tbody>
	    
	    	 <c:choose>
	   	   		<c:when test="${fn:length(userList)==0}">
	   				<tr><td style="width:880px">해당하는 데이터를 조회하지 못했습니다.</td></tr>
	   	   		</c:when>
	   	   		<c:otherwise>
	   	   			 <c:forEach var="user" items="${userList}" varStatus="idx">
				        <tr>
				            <td><c:out value="${user.userId}"/></td>
				            <td><c:out value="${user.regionName}"/></td>
				            <td><c:out value="${user.userName}"/></td>
				            <td><c:out value="${user.stDt}"/></td>
				            <td><c:out value="${user.fnDt}"/></td>
				            <td><c:out value="${user.useTime}"/></td>
				        </tr>
			        </c:forEach>
	   	   		</c:otherwise>
	   		</c:choose>
	    </tbody>
	    </table>
	</div>
</div>