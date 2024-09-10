<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script>
	$(document).ready(function(){
		console.log("도로관리1");
		mUrl='road';
		$(".lst_tab li").each(function(i,list){
			$(list).removeClass("on");
		});
		$("#tab4").addClass("on");
	});
</script>

<!-- 대분류선택 시작 -->
<div id="db_layer11">
    <div class="tit11 txt_right mgr_lb">
    <span>대분류</span>
    	<!-- <img src="../images/code_add.gif" alt="추가" onclick="addTypeCode('1')" style="cursor: pointer;" /><img src="../images/code_del.gif" alt="삭제" onclick="delTypeCode('1')" style="cursor: pointer;" /> -->
    </div>
    <div class="tit11_1 scroll">
        <ul id="ulTypeCode1">
        <c:forEach var="list" items="${reportType1}" step="1" varStatus="records">
        	<li id="liTypeCode_${list.areaCode}">
        		<!-- <input type="checkbox" id="chkTypeCode1" name="chkTypeCode1" value="${list.areaCode}" /> -->
        		<!-- ondblclick="modTypeCode('${list.areaCode}','${list.areaName}','1',$(this))" -->
        		<span onclick="getTypeCode('${list.areaCode}','2', $(this),'${list.areaCode}')"  style="cursor: pointer;">
        			${list.areaName}
        		</span>
        	</li>
        </c:forEach>
        	<!-- <li><input type="text" style="width: 100px;" value="${USER_AUTH }" ></li> -->
        </ul>
    </div>
</div>
 <!-- 중분류선택 시작 -->
<div id="db_layer4">
	<div class="tit4 txt_right mgr_lb">
	<span>중분류</span>
    	<img src="../images/code_add.gif" alt="추가" onclick="addTypeCode('2')" style="cursor: pointer;" /><img src="../images/code_del.gif" alt="삭제" onclick="delTypeCode('2')" style="cursor: pointer;" />
	</div>
    <!-- 중분류 리스트 -->
    <div class="tit4_1 scroll" id="divTypeCode2"></div>
</div>
<!-- 소분류선택 시작 -->
<div id="db_layer5">
    <div class="tit5 txt_right mgr_lb">
    <span>소분류</span>
    	<img src="../images/code_add.gif" alt="추가" onclick="addTypeCode('3')" style="cursor: pointer;" /><img src="../images/code_del.gif" alt="삭제" onclick="delTypeCode('3')" style="cursor: pointer;" />
    </div>
	<div class="tit5_1 scroll" id="divTypeCode3"></div>
</div>
