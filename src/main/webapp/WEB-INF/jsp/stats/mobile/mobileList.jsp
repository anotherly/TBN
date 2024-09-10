<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<div class="zip_tit">
    <div class="zip_tit3">
        <table border="0" cellpadding="0" cellspacing="0" class="zip_tit">
            <colgroup>
            <col width="*" />
            <col width="150" />
            <col width="150" />
            </colgroup>
            <thead>
                <tr>
                    <th scope="col">날짜</th>
                    <th scope="col">총문자정보</th>
                    <th scope="col">교통정보</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="list" items="${mobileList}" varStatus="num" >
				<tr>
				<td>${list.STAT_DATE }</td>
				<td>${list.SMS_CNT }</td>
				<td>${list.TRAFFIC_CNT }</td>
				</tr>
				</c:forEach>
            </tbody>
        </table>
    </div>
</div>