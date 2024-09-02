<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<ul>
<c:forEach var="list" items="${allRegionSubId}" step="1" varStatus="status">
	<c:set var="chkUse" value="false" />
	<c:forEach var="userList" items="${userRegionSubId}" step="1" varStatus="status2">
		<c:if test="${list.CODE == userList.CODE }" >
			<c:set var="chkUse" value="true" />
		</c:if>
	</c:forEach>
	<c:choose>
		<c:when test="${chkUse}">
			<li>
				<input type="checkbox" id="chkReporter" name="chkReporter" value="${list.CODE }" checked="checked" />
				<span>${list.CODE_NAME }</span>
			</li>
		</c:when>
		<c:otherwise>
			<li>
				<input type="checkbox" id="chkReporter" name="chkReporter" value="${list.CODE }" />
				<span>${list.CODE_NAME }</span>
			</li>
		</c:otherwise>
	</c:choose>
</c:forEach>
</ul>