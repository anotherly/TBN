<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<script>
	$(document).ready(function() {
		console.log("수상자조회");
		if ($(".admin_result_sc tbody").prop('scrollHeight') - 420 > 0) {
			//스크롤바 생길 시 테이블 밀림 방지
			$(".admin_result_sc table th:last-child").css('padding-right', '17px');
		}
	});
</script>


<div class="board_list">
	<div class="admin_result_sc">
		<table summary="통신원현황" border="0" cellpadding="0" cellspacing="0"
			class="list01">
			<caption>통신원 검색결과</caption>
			<thead>
				<tr>
					<th style="width: 30px;">제외</th>
					<th style="width: 30px;">번호</th>
					<th style="width: 80px;">소속</th>
					<th style="width: 60px;">ID</th>
					<th style="width: 80px;">이름</th>
					<th style="width: 100px;">전화번호</th>
					<th style="width: 120px;">시상종류</th>
					<th style="width: 250px;">시상명</th>
					<th style="width: 50px;">총점</th>
					<th style="width: 80px;">등록일</th>
				</tr>
			</thead>
		</table>
		<div class="admin_result_sc" style="height: 420px">
			<table summary="통신원현황" border="0" cellpadding="0" cellspacing="0"
				class="list01">
				<tbody>
					<c:if test="${awardInformerListSize != 0 }">
						<c:forEach var="informer" items="${awardInformerList}"
							varStatus="idx">
							<tr>
								<td style="width: 30px;">
									 <input type="checkbox" id="Selection" name="Selection"
											value="${informer.AW_ID }" />
								</td>
								<td style="width: 30px;">${informer.RNUM }</td>
								<td style="width: 80px;">${informer.ORG_NAME }</td>
								<td style="width: 60px;">${informer.ACT_ID }</td>
								<td style="width: 80px;">${informer.INFORMER_NAME }</td>
								<td style="width: 100px;">${informer.PHONE_CELL }</td>
								<td style="width: 120px;">${informer.AW_TYPE }</td>
								<td style="width: 250px;">${informer.AW_NAME }</td>
								<td style="width: 50px;">${informer.ALL_RANK }</td>
								<td style="width: 80px;">${informer.REG_DATE }</td>
							</tr>
						</c:forEach>
					</c:if>
					<c:if test="${awardInformerListSize == 0 }">
						<tr>
							<td colspan="16">조회된 데이터가 없습니다.</td>
						</tr>
					</c:if>
				</tbody>
			</table>
		</div>
		<p class="mgt15" >
			<button type="button" onclick="excelDownload()" >
				<img src="../images/btn_excel_down.gif" alt="엑셀다운로드">
			</button>
			<button type="button" class="mgbt" onclick="deleteAward();" style="margin-left: 686px;">
				<img src="../images/btn_awd_ctl.png" alt="수상 취소"
					style="cursor: pointer; width: 92px; height: 30px; background-size: cover;">
			</button>
		</p>
	</div>
</div>


