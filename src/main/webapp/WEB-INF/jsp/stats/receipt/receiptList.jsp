<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<table border="1" cellpadding="0" cellspacing="0" class="list02">
<colgroup>
<col width="30" />
<col width="70" />
<col width="50" />
<col width="30" />
<col width="30" />
<col width="30" />
<col width="50" />
<col width="30" />
<col width="80" />
<col width="80" />
<col width="80" />
<col width="80" />
<col width="240" />
</colgroup>
<tbody>
<c:forEach var="list" items="${receiptList}" varStatus="num" >
<tr>
<input type="hidden" id="receipt_id${list.RECEIPT_ID }" value="${list.RECEIPT_ID }" />
<td>${ALL_CNT - MIN + 1 - num.count}</td>
<td>${list.RECEIPT_DAY }</td>
<td>${list.RECEIPT_TIME }</td>
<td>${list.FLAG_SEND }</td>
<td>${list.FLAG_IMPORTANT }</td>
<td>${list.FLAG_BROAD }</td>
<td>${list.BROAD_TIME }</td>
<td>${list.FLAG_TWITTER }</td>
<td>${list.REPORT_TYPE }</td>
<td>${list.INFORMER_TYPE }</td>
<td>${list.INFORMER_NAME }</td>
<td>${list.USER_NAME }</td>
<td width="240px" class="txt_left" style="margin-left:5px;margin-right:5px;" style="word-break:break-all;" title="${list.CONTENTS }"><c:out value="${fn:substring(list.CONTENTS,0,20) }" /><c:if test="${fn:length(list.CONTENTS)>20}">...</c:if></td>
</tr>
</c:forEach>
</tbody>
</table>