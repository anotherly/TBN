<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<ul id="ulTypeCode3">

	<c:forEach var="list" items="${authMenuList}" step="1" varStatus="idx">
	    <li>
	    	<input type="checkbox" id="check${list.idx}" name="check${list.authCode}" value="${list.idx}" <c:if test="${list.useYn eq 'Y'}">checked</c:if>
	    		onchange="saveChkArr(this,3)"/>
	    	<span id="${list.idx}" class="${list.authCode}"  style="cursor: pointer;">
       			${list.authUrlName}
       		</span>
	    </li>
    </c:forEach>
</ul>

<script>
//-1 다 가져오되 소분류 것은 중분류에서 클릭하기 전까지 감춤
console.log("-1 다 가져오되 소분류 것은 중분류에서 클릭하기 전까지 감춤");
$("#ulTypeCode3 input[type='checkbox']").parent().hide();
</script>    