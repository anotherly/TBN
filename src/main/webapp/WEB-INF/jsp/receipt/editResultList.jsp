<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<script type="text/javascript" charset="utf-8" src="<%=request.getContextPath()%>/js/receipt/receipt.js"></script>
<script>
var sflag = 0;//시민체크플레그
	$(function(){
		
		console.log("editResultListjsp");
		reportTypeOnChange('REPORT_TYPE_EDIT','REPORT_TYPE2_EDIT');
		
		if($('#FLAG_SIMIN').is(':checked')){
			sflag = 1;
			//$('#INFORMER_NAME').prop('readonly', false);
			$('#r2Search_btn').css('opacity', 0.4);
		}else{
			sflag = 0;
			$('#r2Search_btn').css('opacity', 1);
			//$('#INFORMER_NAME').prop('readonly', true);
		}
		
		$("#FLAG_SIMIN").on("click",function(){
			console.log("시민체크박스클릭");
			if($('#FLAG_SIMIN').is(':checked')){
				sflag = 1;
				//$('#INFORMER_NAME').prop('readonly', false);
				$('#r2Search_btn').css('opacity', 0.4);
			}else{
				sflag = 0;
				$('#r2Search_btn').css('opacity', 1);
				//$('#INFORMER_NAME').prop('readonly', true);
			}
		});
		
	});
	

	function searchEditInformer(idx){
		console.log("searchInformer");
		console.log("areacode : "+'${login.regionId}');
		if(sflag != 1){//시민 아닐때
			console.log("searchInformer OK");
			var searchText = $("#INFORMER_NAME").val();
			if (searchText == ''){
				alert("검색어를 입력해주세요.");
				return;
			}
			
			var url = '/receipt/popUpInformerList.do?SEARCH_TEXT=' 
						+ escape(encodeURIComponent(searchText))
						+ "&searchType=4"
						+ "&nowUserArea=${login.regionId}"
						+ "&idx=" + idx;
			var windowName = "통신원정보";
			var popupW = 800; // 팝업 넓이
			var popupH = 730; // 팝업 높이
			var left = Math.ceil((window.screen.width - popupW) / 2);
			var top = Math.ceil((window.screen.height - popupH) / 2);
			
			var popObj = window
				.open(
						url,
						windowName,
						'width='
								+ popupW
								+ ', height='
								+ popupH
								+ ', left='
								+ left
								+ ', top='
								+ top
								+ ', toolbar=no, location=no, status=no, menubar=no, scrollbars=no, resizable=no');
		}
	}
	//금일접수현황에서 통신원 검색하여 받아올 경우
	function popupEdit(informerVO, idx){
		console.log("popupEdit : "+informerVO);
		$("#INFORMER_ID").val(informerVO.informer_ID);
		$("#INFORMER_NAME").val(informerVO.informer_NAME);
		$("#INFORMER_TYPE").text(informerVO.type_NAME);
		$("#INFORMER_TYPE_ID").val(informerVO.informer_TYPE);
	}
	
</script>
<span style="width:50px;">${editVO.RNUM }</span>

<span style="width:85px;">${editVO.RECEIPT_DAY }</span>

<span style="width:30px;">
	<c:choose>
		<c:when test="${editVO.FLAG_SEND == 'Y'}">
			<input type="checkbox" name="FLAG_SEND" id="FLAG_REQUEST" style="width:15px; height:15px;" value="Y" checked />
		</c:when>
		<c:when test="${editVO.FLAG_SEND == 'N'}">
			<input type="checkbox" name="FLAG_SEND" id="FLAG_REQUEST" style="width:15px; height:15px;" value="N" />
		</c:when>
	</c:choose>
</span>

<span style="width:30px;">
	<c:choose>
		<c:when test="${editVO.FLAG_BROD eq 'Y'}">
			<img src="../images/btn_pd2.png" alt="PD확인" />
		</c:when>
		<c:when test="${editVO.FLAG_BROD eq 'C'}">
			<img src="../images/btn_caster2.png" alt="Caster확인" />
		</c:when>
		<c:otherwise>
			<img src="../images/ico_no2.png" alt="미확인" />
		</c:otherwise>
	</c:choose>
</span>

<span style="width:70px;">
긴급:
	<c:choose>
		<c:when test="${fn:contains(editVO.FLAG_IMPORTANT, 'Y')}">
   			<input type="checkbox" name="FLAG_IMPORTANT" id="FLAG_IMPORTANT" style="width:15px; height:15px;" value="Y" checked />
   	   	</c:when>
   	   	<c:otherwise>
   	   		<input type="checkbox" name="FLAG_IMPORTANT" id="FLAG_IMPORTANT" style="width:15px; height:15px;" value="N" />
   	   	</c:otherwise>	
	</c:choose>
</span>

<span style="width:50px;">${editVO.RECEIPT_TIME }</span>

<span style="width:60px; font-weight:700;">
	<select class="table_sel" id="REPORT_TYPE_EDIT" name="REPORT_TYPE" onchange="reportTypeOnChange('REPORT_TYPE_EDIT','REPORT_TYPE2_EDIT')"
			style="width:55px;">
		<option value="">유형(대)</option>
		<c:forEach var="reportFirstVO" items="${reportFirstList}">
			<option value=${reportFirstVO.BAS_SCOD }
				<c:if test="${editVO.REPORT_TYPE eq reportFirstVO.BAS_SCOD }">selected</c:if>>
				${reportFirstVO.BAS_NAME }
			</option>
		</c:forEach>
	</select>
</span>

<span style="width:150px;">
	<select class="table_sel" id="REPORT_TYPE2_EDIT" name="REPORT_TYPE2" style="width:130px;">
		<option value="">유형(중)</option>
	</select>
</span>

<!-- 시민인지 아닌지에 따라 통신원 이름 변경여부 -->
<div style="width:150px;">
	<input type="hidden" id="INFORMER_ID" value=${editVO.INDIVIDUAL_ID }  name="INFORMER_ID" />
	<input type="text" id="INFORMER_NAME" value=${editVO.INDIVIDUAL_NAME }  name="INFORMER_NAME" style="width:80px;"/>
	<img id="r2Search_btn" src="../images/map/btn_sch.png" onclick="searchEditInformer(4)" onkeyup="if(event.keyCode == 13)searchInformer(4)" >
	<br/>	
	<c:choose>
		<c:when test="${editVO.TYPE_NAME == '시민'}">
   			<input type="checkbox" name="FLAG_SIMIN" id="FLAG_SIMIN" style="width:15px; height:15px;" value="Y" checked />시민
   	   	</c:when>
   	   	<c:otherwise>
   	   		<input type="checkbox" name="FLAG_SIMIN" id="FLAG_SIMIN" style="width:15px; height:15px;" value="N" />시민
   	   	</c:otherwise>	
	</c:choose>
</div>

<span name="INFORMER_TYPE" id="INFORMER_TYPE" style="width:40px;">${editVO.TYPE_NAME }</span>
<input type="hidden" name="INFORMER_TYPE_ID" id="INFORMER_TYPE_ID" style="width:40px;" value=${editVO.TYPE_ID }>
<span style="width:80px;">${editVO.RECEPTION_NAME }</span>

<span style="width:462px;">
	<textarea class="table_sel" id="MEMO_EDIT" style="resize:none; width:360px; height:40px; transform:translateY(4px);">${editVO.MEMO }</textarea>
	<button type="button" style="width:36px; height:22px; font-size:10px; font-weight:700; transform:translateY(-18px);"
			onclick="updateReceipt('${editVO.RECEIPT_ID}','${editVO.RNUM}')">저장</button>
	<button type="button" style="width:36px; height:22px; font-size:10px; font-weight:700; transform:translateY(-18px);"
			onclick="undoUpdate('${editVO.RECEIPT_ID}','${editVO.RNUM}')">취소</button>
</span>

<span style="width:60px;">
	<select class="table_sel" name="REPORTER_TYPE" id="REPORTMEAN_TYPE_EDIT" style="width:55px;">
		<option value="">제보처</option>
		<c:forEach var="reportMeanVO" items="${reportMeanTypeList}">
			<option value=${reportMeanVO.ID } 
				<c:if test="${editVO.REPORTER_TYPE eq reportMeanVO.ID}">selected</c:if>>
				${reportMeanVO.NAME }
			</option>
		</c:forEach>
	</select>
</span>

<span>
	<select class="table_sel" name="REGION_ID" id="AREA_CODE_EDIT" style="width:90px;">
		<c:forEach var="areaCodeVO" items="${areaCodeList}">
			<option value=${areaCodeVO.AREA_CODE }
				<c:if test="${editVO.REGION_ID eq areaCodeVO.AREA_CODE}">selected</c:if>>
				${areaCodeVO.AREA_NAME }
			</option>
		</c:forEach>
	</select> 
</span>
