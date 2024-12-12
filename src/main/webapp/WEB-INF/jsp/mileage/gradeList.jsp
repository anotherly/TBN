<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script>
	$(document).ready(function() {
		
	})
	
	var clickCnt = 0;
	var beforeId = '';
	
	// 상품 지급버튼 클릭 시
	$('.gradeBtn').on('click', function() {
		var ClickId = $(this).attr('id');		
		var goData = ClickId.slice(0, -3);
		var goUrl = '/mileage/gradePop.do?INFORMER_ID=' + goData;

		var paymentPop = window.open(goUrl,'상품 지급','width = 740, height = 500, top = 100, left = 200, location = no');
		 
	});
	
	$('#award_table4 td').click(function() {
        // 클릭된 td가 속한 tr 요소 찾기
        var trId = $(this).closest('tr').attr('id');
        var goUrl = '/mileage/gradeLogPop.do?INFORMER_ID=' + trId
        
        
        var allMilePop = window.open(goUrl,'등급 부여 기록','width = 800, height = 600, top = 100, left = 200, location = no');
    });
</script>

<!-- 실적증가가 아닐경우  -->

<%-- <c:if test="${params.searchType != 2 }">--%>
	<div class="board_list">
		<div class="admin_result_sc">
			<table summary="통신원현황" border="0" cellpadding="0" cellspacing="0"
				class="list01" id="award_table3">
				<thead>
					<tr>
						<th style="width: 160px;">ID</th>
						<th style="width: 160px;">이름</th>
						<th style="width: 160px;">소속</th>
						<th style="width: 160px;">연락처</th>
						<th style="width: 160px;">누적 마일리지</th>
						<th style="width: 160px;">현재 등급</th>
						<th style="width: 160px;">등급 부여</th>
					</tr>
				</thead>
			</table>
			<div class="admin_result_sc" style="height: 420px">
				<!-- id="award_table" -->
				<table summary="통신원현황" border="0" cellpadding="0" cellspacing="0" class="list01" id="award_table4">
					<caption>통신원 검색결과</caption>
					<tbody>
						<c:if test="${gradeListCount != 0 }">
							<c:forEach var="grd" items="${gradeList}"
								varStatus="idx">
								<tr id ="${grd.INFORMER_ID}">
									<td style="width: 126px;">${grd.ACT_ID}</td>
									<td style="width: 126px;">${grd.INFORMER_NAME}</td>
									<td style="width: 126px;">${grd.ORG_NAME}</td>
									<td style="width: 126px;">${grd.PHONE_CELL}</td>
									<td style="width: 126px;">${grd.CUM_MILEAGE}</td>
									<td style="width: 126px;">${grd.GRADE}</td>
									<td style="width: 126px;">
										<div class="gradeBtn" id="${grd.INFORMER_ID}Val" >등급 부여</div>
									</td>
								</tr>
							</c:forEach>
						</c:if>
						<c:if test="${gradeListCount == 0 }">
							<tr>
								<td style="width: 880px">해당하는 데이터를 조회하지 못했습니다.</td>
							</tr>
						</c:if>
					</tbody>
				</table>
			</div>
<!-- 			<p class="mgt15" >
				<button type="button" onclick="excelDownload()" >
					<img src="../images/btn_excel_down.gif" alt="엑셀다운로드">
				</button>
				<button type="button" class="mgbt" onclick="save();" style="margin-left: 686px;">
					<img src="../images/btn_reget1.png" alt=""
						style="cursor: pointer; width: 92px; height: 30px; background-size: cover;">
				</button>
			</p> -->
		</div>
	</div>
<%-- </c:if>  --%>



