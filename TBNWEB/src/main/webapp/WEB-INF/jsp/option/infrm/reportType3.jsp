<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<ul id="ulTypeCode3">
<%-- <c:forEach var="list" items="${allTypeCode3}" step="1" varStatus="status1">
	<c:set var="chkUse" value="false" />
	<c:forEach var="userList" items="${userType3Code}" step="1" varStatus="status2">
		<c:if test="${list.CODE == userList.CODE }" >
			<c:set var="chkUse" value="true" />
		</c:if>
	</c:forEach>
	<c:choose>
		<c:when test="${chkUse}">
		<li id="liTypeCode_${list.CODE }">
			<input type="checkbox" id="chkTypeCode3" name="chkTypeCode3" value="${list.CODE }" checked="checked" />
			<span onclick="getTypeCode('${list.CODE }','4', $(this))" ondblclick="modTypeCode('${list.CODE}','${list.CODE_NAME }','3')"><c:out value="${list.CODE_NAME }" /></span>
		</li>
		</c:when>
		<c:otherwise>
		<li id="liTypeCode_${list.CODE }">
			<input type="checkbox" id="chkTypeCode3" name="chkTypeCode3" value="${list.CODE }" />
			<span onclick="getTypeCode('','4', $(this))" ondblclick="modTypeCode('${list.CODE}','${list.CODE_NAME }','3')"><c:out value="${list.CODE_NAME }" /></span>
		</li>
		</c:otherwise>
	</c:choose>
</c:forEach> --%>
<%-- onclick="getTypeCode('${list.ifmId3}','4', $(this),'${list.ifmId1}')" --%>
	<c:forEach var="list" items="${userTypeCode3}" step="1" varStatus="status2">
		<li id="liTypeCode_${list.ifmId3}">
			<input type="checkbox" id="chkTypeCode3" name="chkTypeCode3" value="${list.ifmId3 }"/>
			<span ondblclick="modTypeCode('${list.ifmId3}','${list.ifmName}','3',$(this))"><c:out value="${list.ifmName}" /></span>
		</li>
	</c:forEach>
</ul>