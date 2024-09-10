<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<table summary="참석자목록" border="0" cellpadding="0" cellspacing="0" class="list01">
<caption>
   참석자목록
</caption>
<colgroup>
    <col width="10%" />
    <col width="10%" />
    <col width="10%" />
    <col width="10%" />
    <col width="10%" />
    <col width="40%" />
    <col width="10%" />
</colgroup>
<tbody>
    <c:if test="${empty attendanceList}">
        <tr>
            <td colspan="16"><c:out value="참석자를 등록하세요."/></td>
        </tr>
    </c:if>
    <c:if test="${!empty attendanceList}">
        <c:forEach var="attendance" items="${attendanceList}" varStatus="idx">
            <tr id="${idx.count}">
                <td><c:out value="${attendance.actId}"/></td>
                <td><c:out value="${attendance.flagAct == 'Y' ? '위촉' : '해촉'}"/></td>
                <td><c:out value="${attendance.areaName}"/></td>
                <td><c:out value="${attendance.informerName}"/></td>
                <td><c:out value="${attendance.phoneCell}"/></td>
                <td><c:out value="${attendance.addressHome}"/></td>
                <td><input type="button" class="input_base2" id="deleteAttendanceBtn" name="deleteAttendanceBtn" onclick="deleteAttendance('${attendance.informerId}', $(this))" value="삭제" style="cursor: pointer;"/></td>
            </tr>
        </c:forEach>
    </c:if>
   </tbody>
</table>