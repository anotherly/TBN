<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script>
	$(document).ready(function() {
		
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
						<th >선정</th>
						<th style="width: 139px;">ID</th>
						<th style="width: 139px;">이름</th>
						<th style="width: 139px;">소속</th>
						<th style="width: 139px;">연락처</th>
						<th style="width: 139px;">월 제보건수</th>
						<th style="width: 139px;">지급 마일리지</th>
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
								<tr id ="${informer['아이디']}">
									<td ><input type="checkbox"
										id="Selection" name="Selection"
										value="${informer['아이디']}%%${informer['통신원코드']}%%${informer['이름']}%%${informer['소속']}%%${informer['연락처']}%%${informer['지급마일리지']}" /></td>
									<td style="width: 139px;">${informer['통신원코드']}</td>
									<td style="width: 139px;">${informer['이름']}</td>
									<td style="width: 139px;">${informer['소속']}</td>
									<td style="width: 139px;">${informer['연락처']}</td>
									<td style="width: 139px;">${informer['월별제보건수']}</td>
									<td style="width: 139px;">${informer['지급마일리지']}</td>
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
<!-- 				<button type="button" onclick="excelDownload()" >
					<img src="../images/btn_excel_down.gif" alt="엑셀다운로드">
				</button> -->
				<button type="button" class="mgbt" onclick="save();" style="margin-left: 686px;">
					<img src="../images/btn_reget1.png" alt=""
						style="cursor: pointer; width: 92px; height: 30px; background-size: cover;">
				</button>
			</p>
		</div>
	</div>
<%-- </c:if>  --%>



