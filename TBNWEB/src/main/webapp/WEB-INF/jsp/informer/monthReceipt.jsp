<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<table width="100%" border="0" cellspacing="0" cellpadding="0" class="admin_table2 ">
    <tr>
        <td class="admin_table_line2">1월</td>
        <td class="admin_table_line2">2월</td>
        <td class="admin_table_line2">3월</td>
        <td class="admin_table_line2">4월</td>
        <td class="admin_table_line2">5월</td>
        <td class="admin_table_line2">6월</td>
        <td class="admin_table_line2">7월</td>
        <td class="admin_table_line2">8월</td>
        <td class="admin_table_line2">9월</td>
        <td class="admin_table_line2">10월</td>
        <td class="admin_table_line2">11월</td>
        <td class="admin_table_line2">12월</td>
        <td class="admin_table_line2">합계</td>
    </tr>
    <tr>
        <c:set var="sumCnt" value="0"/>
        <c:forEach var="receiptCnt" items="${receiptCntList}">
            <td><c:out value="${receiptCnt.CNT}" /></td> <%-- 월별 건수 --%>
            <c:set var="sumCnt" value="${sumCnt + receiptCnt.CNT}"/>
        </c:forEach>
        <td><c:out value="${sumCnt}" /></td>    <%-- 합계 --%>
    </tr>
</table>