<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<table border="1" cellpadding="0" cellspacing="0" class="list01">
<tbody>
<c:forEach var="cctvInfo" items="${cctvList }" >   
<tr>
  <td class="txt_left"><a href="javascript:playCCTV('${cctvInfo.CCTV_URL }','${cctvInfo.CCTV_NAME }')">${cctvInfo.CCTV_NAME }</a></td>
</tr>
</c:forEach>
</tbody>
</table>

