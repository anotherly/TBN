<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script>
	$(document).ready(function(){
		console.log("내선번호타입2");
	});
</script>

<div id="db_layer4">
	<div class="tit4 txt_right mgr_lb">
	<span>내선번호</span>
	</div>
    <!-- 중분류 리스트 -->
    <div class="tit4_1 scroll" id="divTypeCode2">
    	<ul id="ulTypeCode2">
			<c:forEach var="list" items="${userTypeCode2}" step="1" varStatus="status2">
				<li id="liTypeCode_${list.inTel}">
					<%-- <input type="checkbox" id="chkTypeCode2" name="chkTypeCode2" value="${list.inTel}" /> --%>
					<!-- onclick="getTypeCode('${list.inTel}','3', $(this),'${list.areaCode}')" -->
					<span ondblclick="modTypeCode('${list.itId}','${list.inTel}','2',$(this))" style="cursor: pointer;">
		       			${list.inTel}
		       		</span>
				</li>
			</c:forEach>
		</ul>
    </div>
</div>
<!-- 제보접수 전화번호 -->
<div id="db_layer5">
    <div class="tit5 txt_right mgr_lb">
	    <span style="left:190px;">제보접수 전화번호</span>
    </div>
	<div class="tit5_1 scroll" id="divTypeCode3">
		<ul id="ulTypeCode3">
		 <c:forEach var="list" items="${userTypeCode2}" step="1" varStatus="status2">
				<li id="liTypeCode_${list.rptTel}">
					<%-- <input type="checkbox" id="chkTypeCode3" name="chkTypeCode3" value="${list.nodelinkId }"/> --%>
					<span ondblclick="modTypeCode('${list.itId}','${list.rptTel}','3',$(this))" style="cursor: pointer;">
		       			${list.rptTel}
		       		</span>
				</li>
			</c:forEach> 
		</ul>
	</div>
</div>

