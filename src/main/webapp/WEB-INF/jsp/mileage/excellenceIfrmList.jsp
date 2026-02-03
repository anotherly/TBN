<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script>
	$(document).ready(function() {
		//console.log("마일리지 선정 excellenceIfrmList.jsp");
	})
	

</script>

	<div class="board_list">
		<div class="admin_result_sc">
			<table summary="검색 결과" border="0" cellpadding="0" cellspacing="0"
				class="list01" id="award_table3">
				<thead>
					<tr>
						<th style="width: 30px;">선정</th>
						<th style="width: 30px;">순위</th>
						<th style="width: 55px;">ID</th>
						<th style="width: 70px;">이름</th>
						<th style="width: 80px;">연락처</th>
						<th style="width: 80px;">통신원 중분류</th>
						<th style="width: 80px;">마일리지 점수 합계</th>
						<th style="width: 80px;">당월 주요제보 점수</th>
						<th style="width: 80px;">전월 주요제보 점수</th>
						<th style="width: 80px;">재난점수</th>
						<th style="width: 72px;">영상/사진점수</th>
					</tr>
				</thead>
				 
			</table>
			<div class="admin_result_sc" style="height: 420px">
				<!-- id="award_table" -->
				<table summary="통신원현황" border="0" cellpadding="0" cellspacing="0" class="list01" id="award_table4">
					<caption>통신원 검색결과</caption>
					<tbody>
						<c:if test="${ mileageListcnt != 0 }">
							<c:forEach var="mileage" items="${mileageList}"
								varStatus="idx">
								
 								<c:choose>
							        <c:when test="${mileage.FLAG_CHK eq 'Y'}">
							            <tr id ="${mileage.INFORMER_ID}" style="background: #daecf9;">
							        </c:when>
							        <c:otherwise>
							            <tr id ="${mileage.INFORMER_ID}">
							        </c:otherwise>
							    </c:choose> 
								
									<td style="width: 30px;"><input type="checkbox" name="Selection" value="${mileage.FLAG_CHK}${mileage.INFORMER_ID}"></td>
									<td style="width: 30px;">${mileage.RANKING }</td>
									<td style="width: 55px;">${mileage.ACT_ID }</td>
									<td style="width: 70px;">${mileage.INFORMER_NAME }</td>
									<td style="width: 80px;">${mileage.PHONE_CELL }</td>
									<td style="width: 80px;">${mileage.ORG_NAME }</td>
									<td style="width: 80px;">${mileage.ALL_POINT }</td>
									<td style="width: 80px;">${mileage.RECEIPT_POINT }</td>
									<td style="width: 80px;">${mileage.BEFORE_POINT }</td>
									<td style="width: 80px;">${mileage.DISASTOR_POINT }</td>
									<td style="width: 71px;">${mileage.VIDEO_POINT }</td>								
								</tr>
							</c:forEach>
						</c:if>
						<c:if test="${ mileageListcnt == 0 }">
							<tr>
								<td style="width: 880px">해당하는 데이터를 조회하지 못했습니다.</td>
							</tr>
						</c:if>
					</tbody>
				</table>
			</div>
			<div style="display:flex;align-items: flex-end;justify-content: space-between;">
				<p class="mgt15" >
	 				<button type="button" onclick="excelDownload()">
						<img src="../images/btn_excel_down.gif" alt="엑셀다운로드">
					</button>
				</p>
				
				<div>
					<button type="button" class="gray-btn" onclick="selectIns()">선정</button>
					<button type="button" class="gray-btn" onclick="selectDel()">선정 취소</button>
				</div>
			</div>
		</div>
	</div>
<%-- </c:if>  --%>



