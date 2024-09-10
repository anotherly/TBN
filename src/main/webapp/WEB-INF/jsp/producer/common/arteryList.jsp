<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<table border="1" cellpadding="0" cellspacing="0" class="list01">
<colgroup>
<col width="40" />
<col width="*" />
</colgroup>
<tbody>
<c:forEach var="list" items="${arteryList}" varStatus="num" >
<tr>
  <td>${fn:length(arteryList)- num.count+1 }</td>
  <td class="txt_left"><a href="javascript:goCCTVList('${list.ARTERY_ID }','${list.ARTERY_NAME }')">${list.ARTERY_NAME }</a></td>
</tr>
</c:forEach>
</tbody>
</table>

