<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<script>
function codeOnChange(strParent,strChild){
	$.ajax
	(
		{
			type : "post" ,
			url : "<c:url value='/receipt/codeChange.do' />" ,
			dataType : "html" ,
			data : "REPORT_TYPE=" + $('#'+strParent).val(),
			cache : false ,
			success:function(html){
				$('#'+strChild).html('<option value="">제보유형(중)</option>'+html);
			} ,
			error:function(html,error){
				//alert("시스템에 문제가 생겼습니다." + error);
			}

		}
	);
}
</script>
<form id="frmInciSearch" name="frmInciSearch" method="post">
	<input type="hidden" id="receipt_id_list" name="receipt_id_list" value="">
	<input type="hidden" id="receipt_flag" name="receipt_flag" value="">
	<input type="hidden" id="caster_flag" name="caster_flag" value="">
    <div class="wrap_top3"></div>
    <div class="wrap_center3">
        <fieldset>
        <legend>소통정보 검색조건</legend>
	 	<select id="search_incidentType" name="search_incidentType"  onchange="codeOnChange('search_incidentType','search_incidentType2');" class="table_sel" style="width:90px;">
	        <option value="">제보유형(대)</option>
	        <c:forEach var="list" items="${incidentType}" varStatus="num" >
	        <option value="${list.CODE }">${list.CODE_NAME }</option>
	        </c:forEach>
	    </select>
	    <select id="search_incidentType2" name="search_incidentType2" class="table_sel" style="width:200px;">
	        <option value="">제보유형(중)</option>
	    </select> 
	    <p class="mg5"></p>
	    <select id="search_regionId" name="search_regionId" class="table_sel" style="width:80px;">
	        <option value="">접수방송국</option>
	        <c:forEach var="list" items="${regionList}" varStatus="num" >
	        <option value="${list.CODE }">${list.CODE_NAME }</option>
	        </c:forEach>
	    </select>
	    <select id="search_informerType" name="search_informerType" class="table_sel" style="width:110px;">
	        <option value="">제보자구분</option>
	        <c:forEach var="list" items="${informerType}" varStatus="num" >
	        <option value="${list.CODE }">${list.CODE_NAME }</option>
	        </c:forEach>
	    </select>
		<select id="search_area" name="search_area" class="table_sel" style="width:80px;">
	    	<option value="">지역</option>
	        <c:forEach var="list" items="${areaList}" varStatus="num" >
	        <option value="${list.CODE }">${list.CODE_NAME }</option>
	        </c:forEach>
	    </select>
	    <select id="search_broad" name="search_broad" class="table_sel" style="width:80px;">
	        <option value="">방송여부</option>
	        <option value="Y">방송</option>
	        <option value="N">미방송</option>
	    </select>
	    <input type="text" id="search_contents" name="search_contents" maxlength="15"  class="txt" alt="내용" title="내용" value="" style="ime-mode:disabled;width:90px;"/>
	    <img src="../images/btn_search.gif"  alt="검색" onclick="javascript:fnReload();" style="cursor:pointer;" />
	    </fieldset>
	</div>
	<div class="wrap_bottom3"></div>
</form>