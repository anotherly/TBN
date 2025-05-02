<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script>
	$(document).ready(function() {
		console.log("굿 제보 마일리지 조회 List 진입");
	})
	

</script>

	<div class="board_list">
		<div class="admin_result_sc">
			<table summary="검색 결과" border="0" cellpadding="0" cellspacing="0"
				class="list01" id="award_table3">
				<thead>
					<tr>
						<th style="width: 30px;">순위</th>
						<th style="width: 60px;">ID</th>
						<th style="width: 80px;">이름</th>
						<th style="width: 100px;">연락처</th>
						<th style="width: 80px;">총점</th>
						<th style="width: 60px;">당월 점수</th>
						<th style="width: 80px;">전월 점수</th>
						<th style="width: 80px;">재난</th>
						<th style="width: 60px;">영상/사진</th>
					</tr>
				</thead>
				 
			</table>
			<div class="admin_result_sc" style="height: 420px">
				<!-- id="award_table" -->
				<table summary="통신원현황" border="0" cellpadding="0" cellspacing="0" class="list01" id="award_table4">
					<caption>통신원 검색결과</caption>
					<tbody>
						<c:if test="${mileageListCount != 0 }">
							<c:forEach var="informer" items="${mileageList}"
								varStatus="idx">
								<tr id ="${informer.INFORMER_ID}">
									<td style="width: 30px;">${informer.RNUM }</td>
									<td style="width: 60px;">${informer.ACT_ID }</td>
									<td style="width: 80px;">${informer.INFORMER_NAME }</td>
									<td style="width: 100px;">${informer.PHONE_CELL }</td>
									<td style="width: 80px;">${informer.MON_CNT }</td>
									<td id="${informer.RPT_GRADE }" style="width: 60px;">${informer.RPT_GRADE }</td>
									<td style="width: 80px;">${informer.MAIN_CNT }</td>
									<td id="${informer.MAIN_GRADE }" style="width: 80px;">${informer.MAIN_GRADE }</td>
									<td style="width: 60px;">${informer.ADD_CNT }</td>
								</tr>
							</c:forEach>
						</c:if>
						<c:if test="${mileageListCount == 0 }">
							<tr>
								<td style="width: 880px">해당하는 데이터를 조회하지 못했습니다.</td>
							</tr>
						</c:if>
					</tbody>
				</table>
			</div>
			<p class="mgt15" >
 				<button type="button" onclick="excelDownload()">
					<img src="../images/btn_excel_down.gif" alt="엑셀다운로드">
				</button>
				
			</p>
		</div>
	</div>
<%-- </c:if>  --%>



