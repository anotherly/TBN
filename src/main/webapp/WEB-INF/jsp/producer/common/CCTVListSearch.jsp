<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<form id="frmCCTVSearch" name="frmCCTVSearch" method="post">
<fieldset>
<legend>도로명 검색</legend>
<select id="REGION" name="REGION" class="table_sel" style="width:110px;">
	<c:forEach var="list" items="${belongList }">
	<option value="${list.CODE}" <c:if test="${list.CODE == region}" > selected </c:if> >${list.CODE_NAME}</option>
 	</c:forEach>
</select>
<input type="text" name="searchArtery" onkeyup="javascript: if(event.keyCode == 13){goArterySearch();}" maxlength="15"  class="input_base" alt="아이디" title="아이디" />
<img onclick="goArterySearch()" src="../images/btn_search.gif"  alt="검색" style="cursor: pointer;"/>
</fieldset>
</form>