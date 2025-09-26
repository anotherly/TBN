<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!-- DateTimePicker -->
<script src="<%= request.getContextPath() %>/calender/moment.js"></script>
<script src="<%= request.getContextPath() %>/calender/mo_ko.js"></script>
<script src="<%= request.getContextPath() %>/calender/bootstrap-datetimepicker.js"></script>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/calender/no-boot-calendar-custom.css" />
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/calender/datetimepickerstyle.css" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script>
 	$(document).ready(function(){
 		var standardDate=moment().subtract(1, 'months').startOf('month').format('YYYY-MM');
		//var endate=moment().format('YYYY-MM');
		//common.js에 생성한 함수 참조(달력생성)
		//dateFunc('standardDate','',standardDate,'','YYYY-MM');
 		
		$('#standardDate').datetimepicker({
		    format: 'YYYY-MM',  
		    viewMode: 'months',
		    minDate: '2025-06',
		    maxDate: moment().subtract(1, 'months').endOf('month') 
		});
		
		
 		search();  // 첫 진입 시 실행 함수
 	});
	
	
	// 검색 버튼 클릭하기 
	function search(){
		
		var options = {
	            url:"/mileage/excellenceIfrmMainsearch.do",
	            type:"post",
	            target: '#listDiv',
	            success: function(){
	            },
	            error: function(res,error){
	                alert("에러가 발생했습니다."+error);
	            }
	    };
	    $('#searchFrm').ajaxSubmit(options);	    
	} 
	
	
	// 페이지 이동
	function changePage(url){
		
		if(url == 'goodMile') {
			$.ajax
			(
				{
					type : "post" ,
					url : "/informer/mileage/mileageMain.do" ,
					dataType : "html" ,
					cache : false ,
					success:function(html){
						$('#contentWrap').html(html);
					} ,
		
					error:function(data,error){
						alert("시스템에 문제가 생겼습니다." + data);
					}
		
				}
			);
		} else if(url == 'excellenceIfrm'){
			$.ajax
			(
				{
					type : "post" ,
					url : "/mileage/excellenceIfrmMain.do" ,
					dataType : "html" ,
					cache : false ,
					success:function(html){
						$('#contentWrap').html(html);
					} ,
		
					error:function(data,error){
						alert("시스템에 문제가 생겼습니다." + data);
					}
		
				}
			);
		} else {
			$.ajax
			(
				{
					type : "post" ,
					url : "/mileage/standard.do" ,
					dataType : "html" ,
					cache : false ,
					success:function(html){
						$('#contentWrap').html(html);
					} ,
		
					error:function(data,error){
						alert("시스템에 문제가 생겼습니다." + data);
					}
		
				}
			);
		}
	}
	
	function excelDownload(){
		rkFlag = true;
		searchFrm.action = "/mileage/excellenceIfrmExcelDown.do";
		searchFrm.submit();
		rkFlag = true;
	}
	
	
</script>
</head>
<body>
<div id="contentWrap">
	<form id="searchFrm" name="searchFrm">
		<div id="searchDiv">
			<div id="contents">
				<h1 class='content-title'>굿 제보 마일리지</h1>
				<!-- board_list -->
				<div class="board_list">
					<!-- 서브메뉴 탭영역 시작 -->
					<div class="gnb_tab">
						<ul class="lst_tab">
							<li><a href="javascript:changePage('goodMile')">굿 제보 마일리지 조회</a></li>
							<li class="ns"></li>
							<li class="on"><a href="javascript:changePage('excellenceIfrm')">굿 제보 통신원 선정</a></li>
							<li class="ns"></li>
							<li><a href="javascript:changePage('standard')">선정 기준</a></li>
						</ul>
					</div>
					<div style="position: absolute;right: 0px;top: 52px;">
				       <select id="areaOptSel" name="searchAreaCode">
                           <c:forEach var="informerRegion" items="${informerRegionList}" varStatus="idx">
                               <option value="${informerRegion.areaCode}" 
                               <c:if test="${informerRegion.areaCode eq login.regionId}">selected</c:if>>
                               <c:out value="${informerRegion.areaName}"/></option>
                           </c:forEach>
						</select>
					</div>
					
					<!-- 검색조건 영역 시작 -->
					<div class="rounding_wrap mgt10">
						<div class="wrap_top"></div>
						<div class="wrap_center">
							<fieldset style="display: flex; align-items: center; justify-content: center;">
								<div class="form_daterange" style="display: inline-flex;align-items: center;gap: 5px;" id="schDtBody">
									기준년월 : 
									<div class='input-group date' id='datetimepicker1'>
										<input type='text' class="form-control dt_search" name="standardDate" id="standardDate" required/>
									</div>
									<!--  종료일 :  
									<div class='input-group date' id='datetimepicker2'>
										<input type="text" class="form-control dt_search" id="edt" name="edt" required/>
									</div> -->
								</div> 
								<div id="awardSdiv">
									 <img src="../images/btn_search.gif" onclick="search();" style="cursor: pointer;     margin-left: 10px;" alt="검색" /> 
								</div>
	
							</fieldset>
						</div>
						<div class="wrap_bottom"></div>
					</div>
				</div>
				<input type="hidden" name="PAYMENT_DATE" id="PAYMENT_DATE">
				<!-- list -->
				<div id="listDiv"></div>
			</div>
		</div>
		
	</form>
</div>
</body>
</html>