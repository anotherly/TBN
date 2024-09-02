<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<div class="board_list">
    <div class="list_result_sc">
        <table summary="행사목록" border="0" cellpadding="0" cellspacing="0" class="list01" id="event_table">
        <caption>
           행사목록
        </caption>
        <colgroup>
            <col width="10%" />
            <col width="15%" />
            <col width="40%" />
            <col width="*" />
        </colgroup> 
        <thead>
            <tr>
                <th scope="col">행사일자</th>
                <th scope="col">행사명</th>
                <th scope="col">내용</th>
            </tr>
        </thead>
        <tbody>
        
        <c:choose>
   	   		<c:when test="${fn:length(eventList)==0}">
   				<tr><td colspan="16">해당하는 데이터를 조회하지 못했습니다.</td></tr>
   	   		</c:when>
   	   		<c:otherwise>
   	   			<c:forEach var="event" items="${eventList}" varStatus="idx">
                <tr>
                    <%-- <td onclick="detailEvent('${event.EVENT_ID}');" style="cursor: pointer;">${ALL_CNT - MIN + 1 - idx.count}</td> --%>
                    <td onclick="detailEvent('${event.EVENT_ID}');" style="cursor: pointer;text-align: center !important;"><c:out value="${event.EVENT_DATE}"/></td>
                    <td class="txt_left" onclick="detailEvent('${event.EVENT_ID}');" style="cursor: pointer;text-align: center !important;"><c:out value="${event.EVENT_NAME}"/></td>
                    <td class="txt_left" onclick="detailEvent('${event.EVENT_ID}');" style="cursor: pointer;text-align: center !important;"><c:out value="${fn:length(event.CONTENTS) > 30 ? fn:substring(event.CONTENTS, 0, 30) : event.CONTENTS}"/><c:out value="${fn:length(event.CONTENTS) > 30 ? '...' : ''}"/></td>
                </tr>
            </c:forEach>
   	   		</c:otherwise>
   		</c:choose>
        
            
        </tbody>
        </table>
    </div>
</div>