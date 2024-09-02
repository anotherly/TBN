<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<ul id="ulTypeCode2">
<c:forEach var="list" items="${allType2Code}" step="1" varStatus="status1">
	<c:set var="chkUse" value="false" />
	<c:forEach var="userList" items="${userType2Code}" step="1" varStatus="status2">
		<c:if test="${list.CODE == userList.CODE }" >
			<c:set var="chkUse" value="true" />
		</c:if>
	</c:forEach>
	<c:choose>
		<c:when test="${chkUse}">
		<li>
			<input type="checkbox" id="chkTypeCode2" name="chkTypeCode2" value="${list.CODE }" checked="checked" onclick="toggleChkBox(this, '2','type')" />
			<span onclick="getTypeCode('${list.CODE }','3', $(this))" style="cursor: pointer;">${list.CODE_NAME }</span>
		</li>
		</c:when>
		<c:otherwise>
		<li>
			<input type="checkbox" id="chkTypeCode2" name="chkTypeCode2" value="${list.CODE }" onclick="toggleChkBox(this, '2','type')" />
			<span onclick="getTypeCode('${list.CODE }','3', $(this))" style="cursor: pointer;">${list.CODE_NAME }</span>
		</li>
		</c:otherwise>
	</c:choose>
</c:forEach>
</ul>
