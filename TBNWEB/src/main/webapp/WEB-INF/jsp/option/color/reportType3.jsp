<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<ul id="ulTypeCode3">
<c:forEach var="list" items="${allType3Code}" step="1" varStatus="status1">
	<c:set var="chkUse" value="false" />
	<c:forEach var="userList" items="${userType3Code}" step="1" varStatus="status2">
		<c:if test="${list.CODE == userList.CODE }" >
			<c:set var="chkUse" value="true" />
		</c:if>
	</c:forEach>
	<c:choose>
		<c:when test="${chkUse}">
		<li>
			<input type="checkbox" id="chkTypeCode3" name="chkTypeCode3" value="${list.CODE }" checked="checked" onclick="toggleChkBox(this, '3','type')" <c:if test='${!chkYn}' >disabled</c:if>  />
			<c:out value="${list.CODE_NAME }" />
		</li>
		</c:when>
		<c:otherwise>
			<li>
				<input type="checkbox" id="chkTypeCode3" name="chkTypeCode3" value="${list.CODE }" onclick="toggleChkBox(this, '3','type')" <c:if test='${!chkYn}' >disabled</c:if>  />
				<c:out value="${list.CODE_NAME }" />
			</li>
		</c:otherwise>
	</c:choose>
</c:forEach>
</ul>