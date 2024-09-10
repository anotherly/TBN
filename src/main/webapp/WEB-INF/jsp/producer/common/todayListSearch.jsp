<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<script>
	$(function(){
		
		//서울교통방송의 경우 교통방송 전체로
		if(lgnArea == '080'){
			$("#AREA_CODE").val("").prop("selected", true);
		}else{
			$("#AREA_CODE").val(lgnArea).prop("selected", true);
		}
		//$("#AREA_CODE").val(lgnArea).prop("selected", true);
		selectAreaCodeSub();
	});
</script>
    <div class="wrap_center3">
        <fieldset>
	        <legend>소통정보 검색조건</legend>
		 	<select id="REPORT_TYPE" name="REPORT_TYPE"  
				 			onchange="reportTypeOnChange('REPORT_TYPE','REPORT_TYPE2');" 
				 			class="table_sel" style="width:110px;">
		        <option value="">제보유형(대)</option>
		        <c:forEach var="reportFirstVO" items="${reportFirstList}" varStatus="num" >
		        <option value="${reportFirstVO.BAS_SCOD }">${reportFirstVO.BAS_NAME }</option>
		        </c:forEach>
		    </select>
		    <select id="REPORT_TYPE2" name="REPORT_TYPE2" class="table_sel" style="width:198px;">
		        <option value="">제보유형(중)</option>
		    </select> 
	
		    <select id="AREA_CODE" name="AREA_CODE" 
		    				onchange="selectAreaCodeSub()" class="table_sel" style="width:110px;">
		        <option value="">교통방송 전체</option>
		        <c:forEach var="areaCodeVO" items="${areaCodeList}" varStatus="num" >
		        <option value="${areaCodeVO.AREA_CODE }">${areaCodeVO.AREA_NAME }</option>
		        </c:forEach>
		    </select>
		    <select id="INDIVIDUAL_TYPE" name="INDIVIDUAL_TYPE" class="table_sel" style="width:110px;">
		        <option value="">통신원타입</option>
		        <c:forEach var="informerVO" items="${informerTypeList}" varStatus="num" >
		        <option value="${informerVO.TYPE_ID }">${informerVO.TYPE_NAME }</option>
		        </c:forEach>
		    </select>
		     <p style="margin:1px;"></p>
		    <select id="FLAG_IMPORTANT" name="FLAG_IMPORTANT" class="table_sel" style="width:80px;">
		    	<option value="">긴급여부</option>
		        <option value="Y">긴급</option>
		        <option value="N">비긴급</option>
		    </select>
		    <select id="FLAG_BROD" name="FLAG_BROD" class="table_sel" style="width:80px;">
		        <option value="">방송여부</option>
		        <option value="Y">방송</option>
		        <option value="N">미방송</option>
		    </select>
		    <input type="text" id="CONTENT" name="CONTNENT" onkeyup="if(event.keyCode == 13)searchBroadcastList()"
				    	maxlength="15"  class="txt" alt="내용" title="내용" 
				    	value="" style="width:355px;height:24px;font-size:15px;border:1px solid #C7C7C7;"/>
				    	
		    <img src="../images/btn_search.gif"  alt="검색" onclick="searchBroadcastList()" style="cursor:pointer;" />
	    </fieldset>
	</div>
	<div class="wrap_bottom3"></div>
<!-- </form> -->