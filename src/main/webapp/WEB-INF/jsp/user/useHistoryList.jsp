<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<script>
$(document).ready(function(){
	console.log("정보변경 이력조회 진입");
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
	            <th scope="col">일시</th>
	            <th scope="col">변경항목</th>
	            <th scope="col">변경유형</th>
	            <th scope="col">변경자</th>
	            <th scope="col">변경 대상</th>
	            <th scope="col">변경 내용</th>
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