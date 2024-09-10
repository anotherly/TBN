<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script>
	$(document).ready(function(){
		console.log("내선번호");
		mUrl='inTel';
		$(".lst_tab li").each(function(i,list){
			$(list).removeClass("on");
		});
		$("#tab5").addClass("on");
	});
</script>

<!-- 지역본부 -->
<div id="db_layer11">
    <div class="tit11 txt_right mgr_lb">
    <span>지역본부</span>
    	<!-- <img src="../images/code_add.gif" alt="추가" onclick="addTypeCode('1')" style="cursor: pointer;" /><img src="../images/code_del.gif" alt="삭제" onclick="delTypeCode('1')" style="cursor: pointer;" /> -->
    </div>
    <div class="tit11_1 scroll">
        <ul id="ulTypeCode1">
        <c:forEach var="list" items="${reportType1}" step="1" varStatus="records">
        	<li id="liTypeCode_${list.areaCode}">
        		<!-- <input type="checkbox" id="chkTypeCode1" name="chkTypeCode1" value="${list.areaCode}" /> -->
        		<!-- ondblclick="modTypeCode('${list.areaCode}','${list.areaName}','1',$(this))" -->
        		<span>
        			${list.areaName}
        		</span>
        	</li>
        </c:forEach>
        	<!-- <li><input type="text" style="width: 100px;" value="${USER_AUTH }" ></li> -->
        </ul>
    </div>
</div>
<!-- 대표번호 -->
<div id="db_layer11">
    <div class="tit11 txt_right mgr_lb">
    <span>대표번호</span>
    	<!-- <img src="../images/code_add.gif" alt="추가" onclick="addTypeCode('1')" style="cursor: pointer;" /><img src="../images/code_del.gif" alt="삭제" onclick="delTypeCode('1')" style="cursor: pointer;" /> -->
    </div>
    <div class="tit11_1 scroll">
        <ul id="ulTypeCode1">
        <c:forEach var="list" items="${reportType1}" step="1" varStatus="records">
        	<li id="liTypeCode_${list.areaCode}">
        		<!-- <input type="checkbox" id="chkTypeCode1" name="chkTypeCode1" value="${list.areaCode}" /> -->
        		<!-- ondblclick="modTypeCode('${list.areaCode}','${list.areaName}','1',$(this))" -->
        		<span onclick="getTypeCode('${list.areaCode}','2', $(this),'${list.areaCode}')" ondblclick="modTypeCode('${list.areaCode}','${list.rpTel}','1',$(this))" style="cursor: pointer;">
        			${list.rpTel}
        		</span>
        	</li>
        </c:forEach>
        	<!-- <li><input type="text" style="width: 100px;" value="${USER_AUTH }" ></li> -->
        </ul>
    </div>
</div>
 <!-- 내선번호  + 제보접수 전화번호-->
<div id="dbInTelDiv">
<div id="db_layer4">
	<div class="tit4 txt_right mgr_lb">
	<span>내선번호</span>
	</div>
    <!-- 중분류 리스트 -->
    <div class="tit4_1 scroll" id="divTypeCode2">
    </div>
</div>
<!-- 제보접수 전화번호 -->
<div id="db_layer5">
    <div class="tit5 txt_right mgr_lb">
	    <span style="left:190px;">제보접수 전화번호</span>
    </div>
	<div class="tit5_1 scroll" id="divTypeCode3">
	</div>
</div>
 </div>

