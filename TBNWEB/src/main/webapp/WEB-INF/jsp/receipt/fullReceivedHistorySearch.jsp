<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script>
	$(document).ready(function(){
		console.log("전체접수현황 검색결과");
	});
</script>

          <p class="list_result txt_i10"><img src="../images/ico_result.gif" alt="" />검색결과 ${receiptTodayListSIze }건</p>
          <div class="list_result_sc">
            <table summary="금일접수현황" border="0" cellpadding="0" cellspacing="0" class="list01">
              <caption>
              금일접수현황 검색결과
              </caption>
              <colgroup>
              <col width="60" />
              <col width="50" />
              <col width="30" />
              <col width="100" />
              <col width="100" />
              <col width="50" />
              <col width="80" />
              <col width="*" />
              <col width="50" />
              </colgroup>
              <thead>
                <tr>
                  <th scope="col">일자</th>
                  <th scope="col">시간</th>
                  <th scope="col">유형</th>
                  <th scope="col">유형(중)</th>
                  <th scope="col">제보자</th>
                  <th scope="col">구분</th>
                  <th scope="col">접수자</th>
                  <th scope="col">내용</th>
                  <th scope="col">방송</th>
                  </tr>
              </thead>
              <tbody>
                  <c:forEach var="receiptTodayInfo" items="${receiptTodayList }" >	
               	<tr style="cursor:pointer;" onclick="popTextarea('${receiptTodayInfo.RECEIPT_ID }');">
                  <!-- roof -->
                  <td>${receiptTodayInfo.RECEIPT_DAY }</td>
                  <td>${receiptTodayInfo.RECEIPT_TIME }</td>
                  <td>${receiptTodayInfo.REPORT_TYPE_NM }</td>
                  <td>${receiptTodayInfo.REPORT_TYPE_NM2 }</td>
                  <td>${receiptTodayInfo.INFORMER_NAME}</td>
                  <td>${receiptTodayInfo.INFORMER_TYPE_NM }</td>
                  <td>${receiptTodayInfo.USER_NAME }</td>
                  <td class="txt_left"  >
                  	${receiptTodayInfo.CONTENTS }
                  </td>
                  <td>
                  		<c:choose>
							<c:when test="${receiptTodayInfo.FLAG_BROAD eq 'Y'}">
								<img src="../images/ico_pd.gif" alt="" />
							</c:when>
							<c:when test="${receiptTodayInfo.FLAG_BROAD eq 'C'}">
								<img src="../images/ico_caster.gif" alt="" />
							</c:when>
							<c:otherwise>
								<img src="../images/ico_no.gif" alt="" />
							</c:otherwise>
						</c:choose>
					</td>
                 </tr>
                 </c:forEach>
              </tbody>
            </table>
          <!-- 검색결과 끝 -->
