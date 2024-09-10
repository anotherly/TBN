<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<table summary="주소검색" border="0" cellpadding="0" cellspacing="0" class="zip_tit">
    <caption>
    주소검색결과
    </caption>
    <colgroup>
    <col width="100" />
    <col width="*" />
    </colgroup>
    <tbody>
        <c:forEach var="address" items="${addressList}" varStatus="idx">
	        <tr>
	            <td align="center" id="ZIP_CODE${idx.count}"><c:out value="${address.ZIP_CODE}"></c:out></td>
	            <td id="ADDRESS${idx.count}" onclick="setAddress('${idx.count}');" style="cursor: pointer;"><c:out value="${address.ADDRESS}"></c:out></td>
	        </tr>    
        </c:forEach>
    </tbody>
</table>