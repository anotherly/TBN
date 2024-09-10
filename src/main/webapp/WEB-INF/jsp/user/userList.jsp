<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<script>
$(document).ready(function(){
	console.log("userList.jsp 진입");
	if($("#user_table tbody").prop('scrollHeight') - $("#user_table tbody").prop('clientHeight') > 0){
		//스크롤바 생길 시 테이블 밀림 방지
	    $("#user_table th:last-child").css('padding-right','17px');
	}
});
</script>
<div class="board_list">
	<div class="list_result_sc">
	    <table summary="사용자목록" border="0" cellpadding="0" cellspacing="0" class="list01" id="user_table">
	    <caption>
	       사용자목록
	    </caption>
	    <thead>
	        <tr>
	            <th scope="col">ID</th>
	            <th scope="col">소속</th>
	            <th scope="col">이름</th>
	            <th scope="col">권한</th>
	            <th scope="col">전화번호</th>
	            <th scope="col">등록일</th>
	            <th scope="col">최종접속일</th>
	            <th scope="col">접속상태</th>
	        </tr>
	    </thead>
	    <tbody>
	    
	    <c:choose>
   	   		<c:when test="${fn:length(userList)==0}">
   				<tr><td style="width:880px">해당하는 데이터를 조회하지 못했습니다.</td></tr>
   	   		</c:when>
   	   		<c:otherwise>
   	   			<c:forEach var="user" items="${userList}" varStatus="idx">
		        <tr onclick="editUser('${user.userId}');" style="cursor: pointer;">
		            <%-- <td>${ALL_CNT - MIN + 1 - idx.count}</td> --%>
		            <td><c:out value="${user.userId}"/></td>
		            <td><c:out value="${user.regionName}"/></td>
		            <td><c:out value="${user.userName}"/></td>
		            <td><c:out value="${user.authName}"/></td>
		            <td><c:out value="${user.userPhone}"/></td>
		            <td><c:out value="${user.regDt}"/></td>
		            <td><c:out value="${user.stDt}"/></td>
		            <td><c:out value="${user.cnYn}"/></td>
		        </tr>
	        </c:forEach>
   	   		</c:otherwise>
   		</c:choose>
	    </tbody>
	    </table>
	</div>
</div>