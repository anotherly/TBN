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
						<th style="width: 80px;">이름</th>
						<th style="width: 100px;">소속</th>
						<th style="width: 80px;">가입일자</th>
						<th style="width: 60px;">제보 건수</th>
					</tr>
				</thead>
				 
			</table>
			<div class="admin_result_sc" style="height: 420px">
				<!-- id="award_table" -->
				<table summary="통신원현황" border="0" cellpadding="0" cellspacing="0" class="list01" id="award_table4">
					<caption>통신원 검색결과</caption>
					<tbody>
						<c:if test="${ bestIfrmListCount != 0 }">
							<c:forEach var="informer" items="${bestIfrmList}"
								varStatus="idx">
								<tr id ="${informer.INFORMER_ID}">
									<td style="width: 80px;">${informer.INFORMER_NAME }</td>
									<td style="width: 100px;">${informer.ORG_NAME }</td>
									<td style="width: 80px;">${informer.REG_DATE }</td>
									<td style="width: 60px;">${informer.ALL_POINT }</td>
								</tr>
							</c:forEach>
						</c:if>
						<c:if test="${ bestIfrmListCount == 0 }">
							<tr>
								<td style="width: 880px">해당하는 데이터를 조회하지 못했습니다.</td>
							</tr>
						</c:if>
					</tbody>
				</table>
			</div>
			<p class="mgt15" >
 				<!-- <button type="button" onclick="excelDownload()">
					<img src="../images/btn_excel_down.gif" alt="엑셀다운로드">
				</button> -->
				
			</p>
		</div>
	</div>
<%-- </c:if>  --%>



