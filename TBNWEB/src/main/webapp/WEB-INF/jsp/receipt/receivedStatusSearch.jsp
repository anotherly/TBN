<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script>
	$(document).ready(function(){
		console.log("금일접수현황 검색결과");
	});
</script>
          <p class="list_result txt_i10"><img src="../images/ico_result.gif" alt="" />검색결과 <label id="labelCnt">${receiptTodayListSize }건</label></p>
          <div class="list_result_sc" >
            <table summary="금일접수현황" border="0" cellpadding="0" cellspacing="0" class="list01">
              <caption>
              금일접수현황 검색결과
              </caption>
              <colgroup>
              <col width="43" />
              <col width="30" />
              <col width="30" />
              <col width="30" />
              <col width="30" />
              <col width="35" />
              <col width="40" />
              <col width="80" />
              <col width="120" />
              <col width="50" />
              <col width="75" />
              <col width="*" />
              </colgroup>
              <thead>
                <tr>
                  <th scope="col">번호</th>
                  <th scope="col">전송</th>
                  <th scope="col">방송</th>
                  <th scope="col">지도</th>
                  <th scope="col">SNS</th>
                  <th scope="col">시간</th>
                  <th scope="col">유형</th>
                  <th scope="col">유형(중)</th>
                  <th scope="col">제보자</th>
                  <th scope="col">구분</th>
                  <th scope="col">접수자</th>
                  <th scope="col">접수내용</th>
                </tr>
              </thead>
              <tbody>
                  <c:forEach var="receiptTodayInfo" items="${receiptTodayList }" >
                <tr style="cursor:pointer;" onclick="popTextarea('${receiptTodayInfo.RECEIPT_ID }');">
                  <td>${receiptTodayInfo.RNUM }</td>
                  <td>
                  	<c:choose>
                  	   <c:when test="${receiptTodayInfo.FLAG_SEND == 'Y' }">
                  			<img src="../images/ico_sender.gif" alt="" />
                  	   </c:when>
                  	   <c:otherwise>
                  	   		<img src="../images/ico_no.gif" alt="" />
                  	   </c:otherwise>
                  	</c:choose>
                  </td>
                  <td>
                  	<c:choose>
                  	   <c:when test="${receiptTodayInfo.FLAG_BROAD == 'Y' }">
                  			<img src="../images/ico_pd.gif" alt="" />
                  	   </c:when>
                  	   <c:when test="${receiptTodayInfo.FLAG_BROAD == 'C' }">
                  			<img src="../images/ico_caster.gif" alt="" />
                  	   </c:when>
                  	   <c:otherwise>
                  	   		<img src="../images/ico_no.gif" alt="" />
                  	   </c:otherwise>
                  	</c:choose>
                  </td>
                  <td>
                  	<c:choose>
                  	   <c:when test="${receiptTodayInfo.FLAG_MAP == 'Y' }">
                  			<img src="../images/ico_map.gif" alt="" />
                  	   </c:when>
                  	   <c:otherwise>
                  	   		<img src="../images/ico_no.gif" alt="" />
                  	   </c:otherwise>
                  	</c:choose>
                  </td>
                  <td>
                  	<c:choose>
                  	   <c:when test="${receiptTodayInfo.FLAG_TWITTER == 'Y' }">
                  			<img src="../images/ico_sns.gif" alt="" />
                  	   </c:when>
                  	   <c:otherwise>
                  	   		<img src="../images/ico_no.gif" alt="" />
                  	   </c:otherwise>
                  	</c:choose>
                  </td>
                  <td>${receiptTodayInfo.RECEIPT_TIME }</td>
                  <td>${receiptTodayInfo.REPORT_TYPE_NM }</td>
                  <td>${receiptTodayInfo.REPORT_TYPE_NM2 }</td>
                  <td>${receiptTodayInfo.INFORMER_NAME }</td>
                  <td>${receiptTodayInfo.INFORMER_TYPE_NM }</td>
                  <td>
                  	${receiptTodayInfo.USER_NAME }
                  </td>
                  <td class="txt_left" style="text-align:left !important;"  >
                  ${receiptTodayInfo.CONTENTS }
                  </td>
                </tr>
                  </c:forEach>
              </tbody>
            </table>
          </div>
          <!-- 검색결과 끝 -->


