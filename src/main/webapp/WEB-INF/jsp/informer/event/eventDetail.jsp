<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<table summary="행사관리 상세정보" width="100%" border="0" cellspacing="0" cellpadding="0" class="view">
    <caption>
    행사관리 상세정보
    </caption>
    <colgroup>
    <col width="50" />
    <col width="*" />
    </colgroup>
    <tr>
        <td class="b">행사일</td>
        <td><input class="input_base" style="width:100px;" type="text" id="EVENT_DATE" name="EVENT_DATE" value="${eventInfo.EVENT_DATE }" readonly/></td>
    	<td><input class="input_base" style="width:100px;" type="hidden" id="REGION_ID" name="REGION_ID" value="${eventInfo.REGION_ID }" readonly/></td>
    </tr>
    <tr>
        <td class="b">행사명</td>
        <td><input class="input_base" type="text" id="EVENT_NAME" name="EVENT_NAME" style="width: 250px;" value="${eventInfo.EVENT_NAME }" readonly/></td>
    </tr>
    <tr>
        <td class="b">내용</td>
        <td><textarea class="table_sel" id="CONTENTS" name="CONTENTS" cols="50" rows="8" style="width:97%; height:150px;" readonly><c:out value="${eventInfo.CONTENTS }" /></textarea></td>
    </tr>
	<tr>
	            	<td class="b">첨부파일</td>
	            	<td id="fileDown">
	            	<input type="hidden" name="FILE_ID" value="${eventInfo.EVENT_ID}">
	            		<c:forEach var="file" items="${fileInfo}" varStatus="status">
						    <a style="margin-left: 15px;" href="/EventfileDownload.do?fileId=${file.FILE_ID}">${file.FILE_NAME}</a>
							<br>
						</c:forEach>

	            	</td>
	            </tr>
</table>