<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script>
	$(document).ready(function() {
		console.log("수상자선정");
		/* if($(".admin_result_sc tbody").prop('scrollHeight') - 420 > 0){
			//스크롤바 생길 시 테이블 밀림 방지
		    $("#award_table th:last-child").css('padding-right','17px');
		} */
	})
</script>

<!-- 실적증가가 아닐경우  -->

<%-- <c:if test="${params.searchType != 2 }">--%>
	<div class="board_list">
		<div class="admin_result_sc">
			<table summary="통신원현황" border="0" cellpadding="0" cellspacing="0"
				class="list01" id="award_table3">
				<thead>
					<tr>
						<th style="width: 30px;">선정</th>
						<th style="width: 30px;">순위</th>
						<th style="width: 60px;">ID</th>
						<th style="width: 80px;">이름</th>
						<th style="width: 80px;">소속</th>
						<th style="width: 100px;">연락처</th>
						<th style="width: 80px;">당월건수</th>
						<th style="width: 60px;">/점수</th>
						<th style="width: 80px;">주요건수</th>
						<th style="width: 80px;">/점수</th>
						<th style="width: 60px;">전월건수</th>
						<th style="width: 80px;">/점수</th>
						<th style="width: 60px; padding-right: 17px;">총점</th>
					</tr>
				</thead>
			</table>
			<div class="admin_result_sc" style="height: 420px">
				<!-- id="award_table" -->
				<table summary="통신원현황" border="0" cellpadding="0" cellspacing="0" class="list01" id="award_table4">
					<caption>통신원 검색결과</caption>
					<tbody>
						<c:if test="${awardInformerListSize != 0 }">
							<c:forEach var="informer" items="${awardInformerList}"
								varStatus="idx">
								<tr id ="${informer.INFORMER_ID}">
									<td style="width: 30px;"><input type="checkbox"
										id="Selection" name="Selection"
										value="${informer.INFORMER_ID}%%${informer.RPT_GRADE }%%${informer.MAIN_GRADE }%%${informer.ADD_GRADE }%%${informer.ALL_RANK }" /></td>
									<td style="width: 30px;">${informer.RNUM }</td>
									<td style="width: 60px;">${informer.ACT_ID }</td>
									<td style="width: 80px;">${informer.INFORMER_NAME }</td>
									<td style="width: 80px;">${informer.ORG_NAME }</td>
									<td style="width: 100px;">${informer.PHONE_CELL }</td>
									<td style="width: 80px;">${informer.MON_CNT }</td>
									<td id="${informer.RPT_GRADE }" style="width: 60px;">${informer.RPT_GRADE }</td>
									<td style="width: 80px;">${informer.MAIN_CNT }</td>
									<td id="${informer.MAIN_GRADE }" style="width: 80px;">${informer.MAIN_GRADE }</td>
									<td style="width: 60px;">${informer.ADD_CNT }</td>
									<td id="${informer.ADD_GRADE }" style="width: 80px;">${informer.ADD_GRADE }</td>
									<td id="${informer.ALL_RANK }" style="width: 60px;">${informer.ALL_RANK }</td>
								</tr>
							</c:forEach>
						</c:if>
						<c:if test="${awardInformerListSize == 0 }">
							<tr>
								<td style="width: 880px">해당하는 데이터를 조회하지 못했습니다.</td>
							</tr>
						</c:if>
					</tbody>
				</table>
			</div>
			<p class="mgt15" >
				<button type="button" onclick="excelDownload()" >
					<img src="../images/btn_excel_down.gif" alt="엑셀다운로드">
				</button>
				<button type="button" class="mgbt" onclick="save();" style="margin-left: 686px;">
					<img src="../images/btn_reget1.png" alt=""
						style="cursor: pointer; width: 92px; height: 30px; background-size: cover;">
				</button>
			</p>
		</div>
	</div>
<%-- </c:if>  --%>

<input type="hidden" name="awardCnt" id="awardCnt" value="${awardCnt}" />


