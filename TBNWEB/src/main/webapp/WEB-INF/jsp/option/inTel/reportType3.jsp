<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script>
	$(document).ready(function(){
		console.log("내선번호타입3");
	});
</script>

<ul id="ulTypeCode3">
 <c:forEach var="list" items="${userTypeCode2}" step="1" varStatus="status2">
		<li id="liTypeCode_${list.inTel}">
			<%-- <input type="checkbox" id="chkTypeCode3" name="chkTypeCode3" value="${list.nodelinkId }"/> --%>
			<span ondblclick="modTypeCode('${list.inTel}','${list.inTel}','2',$(this))" style="cursor: pointer;">
       			${list.rptTel}
       		</span>
		</li>
	</c:forEach> 
</ul>