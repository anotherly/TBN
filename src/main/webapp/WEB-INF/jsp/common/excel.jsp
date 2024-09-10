<%@ page language="java" contentType="application/vnd.ms-excel;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
	response.setHeader("Content-Type","application/vnd.ms-xls");
	response.setHeader("Content-Disposition","inline;filename=myfile.xls");
%>
<body>
<table border="1" cellpadding="0" cellspacing="0" class="list01">
<colgroup>
<col width="25" />
<col width="30" />
<col width="30" />
<col width="30" />
<col width="40" />
<col width="90" />
<col width="50" />
<col width="*" />
<col width="30" />
</colgroup>
<tbody>
<c:forEach var="list" items="${receiptList}" varStatus="num" >
<tr>
<td>${fn:length(receiptList)- num.count+1 }</td>
<td>${list.FLAG_BROAD }</td>
<td>${list.RECEIPT_TIME }</td>
<td>${list.REPORT_TYPE }</td>
<td>${list.INFORMER_NAME }</td>
<td>${list.INFORMER_TYPE }</td>
<td>${list.CONTENTS }</td>
<td>${list.MAP }</td>
</tr>
</c:forEach>
</tbody>
</table>
</body>