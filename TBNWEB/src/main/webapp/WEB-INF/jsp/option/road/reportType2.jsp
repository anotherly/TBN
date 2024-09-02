<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script>
	$(document).ready(function(){
		console.log("리포트타입2");
	});
</script>

<ul id="ulTypeCode2">
<%-- <c:forEach var="list" items="${allTypeCode2}" step="1" varStatus="status1">
	<c:set var="chkUse" value="false" />
	<c:forEach var="userList" items="${userTypeCode2}" step="1" varStatus="status2">
		<c:if test="${list.CODE == userList.CODE }" >
			<c:set var="chkUse" value="true" />
		</c:if>
	</c:forEach>
	<c:choose>
		<c:when test="${chkUse}">
		<li id="liTypeCode_${list.CODE }">
			<input type="checkbox" id="chkTypeCode2" name="chkTypeCode2" value="${list.CODE }" checked="checked" />
			<span onclick="getTypeCode('${list.CODE }','3', $(this))" ondblclick="modTypeCode('${list.CODE}','${list.CODE_NAME }','2')" style="cursor: pointer;">${list.CODE_NAME }</span>
		</li>
		</c:when>
		<c:otherwise>
		<li id="liTypeCode_${list.CODE }">
			<input type="checkbox" id="chkTypeCode2" name="chkTypeCode2" value="${list.CODE }" />
			<span onclick="getTypeCode('${list.CODE }','3', $(this))" ondblclick="modTypeCode('${list.CODE}','${list.CODE_NAME }','2')" style="cursor: pointer;">${list.CODE_NAME }</span>
		</li>
		</c:otherwise>
	</c:choose>
</c:forEach> --%>
	<c:forEach var="list" items="${userTypeCode2}" step="1" varStatus="status2">
		<li id="liTypeCode_${list.arteryId}">
			<input type="checkbox" id="chkTypeCode2" name="chkTypeCode2" value="${list.arteryId}" />
			<span onclick="getTypeCode('${list.arteryId}','3', $(this),'${list.areaCode}')" ondblclick="modTypeCode('${list.arteryId}','${list.arteryName}','2',$(this))" style="cursor: pointer;">
       			${list.arteryName}
       		</span>
		</li>
	</c:forEach>
</ul>
