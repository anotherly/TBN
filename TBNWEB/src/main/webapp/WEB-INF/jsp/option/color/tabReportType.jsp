<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script>
	$(document).ready(function(){
		console.log("컬러타입1");
		mUrl='reportType';
		$(".lst_tab li").each(function(i,list){
			$(list).removeClass("on");
		});
		$("#tab6").addClass("on");
	});
</script>
<!-- 대분류선택 시작 -->
<div id="db_layer1">
    <div class="tit1 txt_right mgr_lb"></div>
    <div class="tit1_1 scroll">
        <%-- <ul>
        <c:forEach var="list" items="${reportType1}" step="1" varStatus="records">
        	<li><span onclick="getTypeCode2('${list.CODE}')" style="cursor: pointer;">${list.CODE_NAME}</span></li>
        </c:forEach>
        	<!-- <li><input type="text" style="width: 100px;" value="${USER_AUTH }" ></li> -->
        </ul> --%>
        <table width="100%" border="0" cellspacing="0" cellpadding="0">
            <caption>유형별관리-대분류(글자색/배경색)</caption>
            <colgroup>
	            <col width="146" />
	            <col width="160" />
	            <col width="*" />
            </colgroup>
       	<c:forEach var="list" items="${reportType1}" step="1" varStatus="records">
       		<tr>
                <td style="text-align: center;">
                	<span onclick="getTypeCode('${list.basScod}','2', $(this),'${list.basLcod}')" ondblclick="modTypeCode('${list.basScod}','${list.basName }','1',$(this))" style="cursor: pointer;">
        			${list.basName}
        		</span>
                </td>
                <td style="text-align: center;">
                	<select id="sel_fc_${list.basScod }" name="sel_fc_${list.basScod }" onchange="chgColor('${list.basScod}', '1','code','${list.basLcod}')">
                		<option value="" selected="selected">= 글자색선택 =</option>
               		<c:forEach var="fc_col_list" items="${colorList }" step="1" varStatus="status">
               			<c:choose>
               				<c:when test="${list.fColor eq fc_col_list.colorCode }">
               				<option value="${fc_col_list.colorCode }" style="color: ${fc_col_list.colorCode};" selected="selected">${fc_col_list.colorName}</option>
               				</c:when>
               				<c:otherwise>
               				<option value="${fc_col_list.colorCode }" style="color: ${fc_col_list.colorCode};">${fc_col_list.colorName}</option>
               				</c:otherwise>
               			</c:choose>
               		</c:forEach>
               		
                	</select>
                </td>
                <td style="text-align: center;">
                	<select id="sel_bc_${list.basScod }" name="sel_bc_${list.basScod }" onchange="chgColor('${list.basScod}', '2','code','${list.basLcod}')">
                		<option value="" selected="selected">= 배경색선택 =</option>
                		<c:forEach var="bc_col_list" items="${colorList }" step="1" varStatus="status">
               			<c:choose>
               				<c:when test="${list.bColor eq bc_col_list.colorCode }">
               				<option value="${bc_col_list.colorCode }" style="color: ${bc_col_list.colorCode};" selected="selected">${bc_col_list.colorName}</option>
               				</c:when>
               				<c:otherwise>
               				<option value="${bc_col_list.colorCode }" style="color: ${bc_col_list.colorCode};">${bc_col_list.colorName}</option>
               				</c:otherwise>
               			</c:choose>
               		</c:forEach>
                	</select>
                </td>
            </tr>    
       	</c:forEach>
    	</table>
    </div>
</div>
 <!-- 중분류선택 시작 -->
 <div id="db_layer4">
    <div class="tit4 txt_right mgr_lb" 
    style="height: 30px;background: url(../images/code_tit04.gif) 0 0 no-repeat;border:none;"
    ></div>
    <!-- 중분류 리스트 -->
    <div class="tit4_1 scroll" id="divTypeCode2"></div>
 </div>
 <!-- 소분류선택 시작 -->
 <div id="db_layer13">
    <div class="tit13 txt_right mgr_lb"></div>
    <!-- 소분류 리스트 -->
    <div class="tit13_1 scroll" id="divTypeCode3"></div>
 </div>
