<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<table summary="통신원목록" border="0" cellpadding="0" cellspacing="0" class="list02">
<caption>
   통신원목록
</caption>
<colgroup>
    <col width="5%" />
    <col width="12%" />
    <col width="13%" />
    <col width="25%" />
    <col width="20%" />
    <col width="25%" />
</colgroup>
<tbody>
    <c:if test="${empty informerList}">
        <tr>
            <td colspan="16"><c:out value="검색 결과 없음"/></td>
        </tr>
    </c:if>
    <c:forEach var="informer" items="${informerList}" varStatus="idx">
        <tr>
            <td><input type="checkbox" id="INFORMER_ID[idx.count]" name="INFORMER_ID_ARRAY" value="${informer.informerId}"/></td>
            <td><c:out value="${informer.actId}"/></td>
            <td><c:out value="${informer.flagAct == 'Y' ? '위촉' : '해촉'}"/></td>
            <td><c:out value="${informer.areaName}"/></td>
            <td><c:out value="${informer.informerName}"/></td>
            <td><c:out value="${informer.phoneCell}"/></td>
        </tr>
    </c:forEach>
</tbody>
</table>