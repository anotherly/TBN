<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script>
 	$(document).ready(function(){
		
 		// 날짜 넣기
 		selectDate();
		// 리스트 불러오기
		mileageInit();
		
		getTodayDate();
		
 	});
	
	function mileageInit() {
		search();
	}
	
	// 날짜 select 박스 생성
	function selectDate() {
	    const currentYear = new Date().getFullYear();
	    const currentMonth = new Date().getMonth() + 1;
	    const $selectYearElement = $('#standardYear');
	    $selectYearElement.empty();
	
	    for (let year = currentYear; year >= currentYear - 7; year--) {
	        const $option = $('<option></option>')
	            .val(year)
	            .text(year);
	
	        if (year === currentYear) {
	            $option.prop('selected', true);
	        }
	
	        $selectYearElement.append($option);
	    }
	
	    const $selectMonElement = $('#standardMon');
	    $selectMonElement.empty();
	
	    for (let month = 1; month <= 12; month++) {
	        const monthStr = month < 10 ? '0' + month : month;
	        const $option = $('<option></option>')
	            .val(monthStr)
	            .text(month);
	
	        if (month === currentMonth) {
	            $option.prop('selected', true);
	        }
	
	        $selectMonElement.append($option);
	    }
	    
	    var yearVal = $('#standardYear').val();
		var monthVal = $('#standardMon').val();
		var startDateVal = yearVal + "-" + monthVal;
		
		$('#START_DATE').val(startDateVal);
		
	}
	
	// 리스트 검색
	function search(){
		
		var yearVal = $('#standardYear').val();
		var monthVal = $('#standardMon').val();
		var startDateVal = yearVal + "-" + monthVal;
		
		$('#START_DATE').val(startDateVal);

		var options = {
	            url:"/mileage/mileageList.do",
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
	
	function save(){
		var selectionCheck =  searchFrm.Selection;
		var selectionCnt = 0;
		if($("input:checkbox[name=Selection]:checked").length!=0){
			if(!confirm("체크된 통신원에게 마일리지를 지급하시겠습니까?" )){
				return;
			}
			
			var awNameStr = "";
			var termD="";
			
			//기간 수상자인지 단월 수상자인지 판별
/* 			if($("#SDATE").val()==$("#EDATE").val()){//단월 수상자
				termD=$("#sYear").val() + "년 " + $("#sMon").val() +"월의 ";
			}else{//기간 수상자
				termD=$("#sYear").val() + "년 " + $("#sMon").val() +"월"+" ~ "+$("#eYear").val() + "년 " + $("#eMon").val() +"월의 ";
			}
			
			awNameStr += termD+$("#searchType option:selected").text();
			$("#AW_NAME").val(awNameStr); */
			let tagVal = $("#searchFrm").serialize();

			$.ajax
			(
				{
					type : "post" ,
					url : "/mileage/paymentMile.do" ,
					dataType : "json" ,
					data : tagVal,
					cache : false ,
					success:function(data){
						if(data.msg == "success") alert("저장되었습니다.");
						search();
					} ,
	
					error:function(data,error){
						alert("시스템에 문제가 생겼습니다.");
					}
	
				}
			);
			
		}else{
			alert("마일리지를 지급할 통신원을 선택해 주세요");
		}
		
	}
	
	function getTodayDate() {
	  	var date = new Date();
	  	var today = date.getFullYear() + "-" + ("0"+(date.getMonth()+1)).slice(-2) + "-" + ("0"+date.getDate()).slice(-2);
	  	
	  $('#PAYMENT_DATE').val(today);
	}
	
	
	// 페이지 이동
	function changePage(url){
		
		if(url == 'allMile') {
			$.ajax
			(
				{
					type : "post" ,
					url : "/mileage/allMile.do" ,
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
		} else if(url == 'paymentMile'){
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
		} else {
			$.ajax
			(
				{
					type : "post" ,
					url : "/mileage/mileGrade.do" ,
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
	
</script>
</head>
<body>
<div id="contentWrap">
	<form id="searchFrm" name="searchFrm">
		<!-- <div id="posi"><a href="/main.do"><img src="../images/ico_home.gif" alt="home" /></a>제보자관리 > 시상관리</div> -->
		<div id="searchDiv">
			<div id="contents">
				<h1 class='content-title'>마일리지 관리</h1>
				<!-- board_list -->
				<div class="board_list">
					<!-- 서브메뉴 탭영역 시작 -->
					<div class="gnb_tab">
						<ul class="lst_tab">
							<li class="on"><a href="javascript:changePage('paymentMile')">마일리지 반영</a></li>
							<li class="ns"></li>
							<li><a href="javascript:changePage('allMile')">총 마일리지 조회</a></li>
							<li class="ns"></li>
							<li><a href="javascript:changePage('grade')">등급 조회</a></li>
						</ul>
					</div>
					<div style="position: absolute;right: 0px;top: 52px;">
				       <select id="areaOptSel" name="AREA_CODE">
                           <c:forEach var="informerRegion" items="${informerRegionList}" varStatus="idx">
                               <option value="${informerRegion.areaCode}" ><c:out value="${informerRegion.areaName}"/></option>
                           </c:forEach>
						</select>
					</div>
					
					<!-- 검색조건 영역 시작 -->
					<div class="rounding_wrap mgt10">
						<div class="wrap_top"></div>
						<div class="wrap_center">
							<%-- <fieldset>
								<div id="awardSdiv">
									시상종류 :  
									<select class="table_sel1" id="searchType"
										onchange="search();" name="searchType">
										<option value="0">교통제보우수자</option>
									<!--<option value="1">주요정보제공우수자</option>
										<option value=ㄴ"2">실적증가자</option> -->
									</select> 
									<select class="table_sel1" id="searchNum" name="searchNum">
										<option value="10000">전체 출력</option>
										<option value="30">30명 출력</option>
										<option value="50">50명 출력</option>
										<option value="70">70명 출력</option>
										<option value="100">100명 출력</option>
									</select> 
									
									제보 : 
									<input type="text" class="input_base" id="ALL_PER" name="ALL_PER" value="${awardGrade.ALL_PER}" style="width:25px;text-align:center;" maxlength="2" onkeydown='return onlyNumber(event)' onkeyup='removeChar(event)' required/>
									 % 
									주요 : 
									<input type="text" class="input_base" id="MAIN_PER" name="MAIN_PER" value="${awardGrade.MAIN_PER}" style="width:25px;text-align:center;" maxlength="2" onkeydown='return onlyNumber(event)' onkeyup='removeChar(event)'required/>
									 % 
									전월 : 
									<input type="text" class="input_base" id="ADD_PER" name="ADD_PER" value="${awardGrade.ADD_PER}" style="width:25px;text-align:center;" maxlength="2" onkeydown='return onlyNumber(event)' onkeyup='removeChar(event)'required/>
									 % 
									 <img src="../images/btn_search.gif" onclick="search();" style="cursor: pointer;     margin-left: 10px;" alt="검색" /> 
								</div>

								<div id="awardSdiv">
									<!-- fmt:parseDate : String 형을 받아서 원하는 포맷으로 Date 형태로 변경 
										fmt:formatDate : Date 형을 받아서 원하는 포맷으로 날짜형태를 변경 -->
									기준시작 :  
									<input type="hidden" id="SDATE" name="SDATE"
										maxlength="15" class="input_base" readonly="readonly"
										alt="시작일" title="" value=""
										style="width: 70px; align: center;" />
									<c:set var="today" value="<%=monthAgo%>" />
									<c:set var="datetime">
										<fmt:formatDate value="${today}" pattern="yyyy" />
									</c:set>
									<select id="sYear" name="sYear">
										<c:forEach var="i" begin="0" end="10">
											<c:choose>
												<c:when test="${i == 0}">
													<option value="${datetime}" selected>${datetime}년</option>
												</c:when>
												<c:otherwise>
													<option value="${datetime-i}">${datetime-i}년</option>
												</c:otherwise>
											</c:choose>
										</c:forEach>
									</select>
									<c:set var="today" value="<%=monthAgo%>" />
									<c:set var="datetime">
										<fmt:formatDate value="${today}" pattern="MM" />
									</c:set>
									<select id="sMon" name="sMon">
										<c:forEach var="i" begin="01" end="12">
											<c:choose>
												<c:when test="${i == datetime}">
													<option value="${i}" selected>${i}월</option>
												</c:when>
												<c:otherwise>
													<option value="${i}">${i}월</option>
												</c:otherwise>
											</c:choose>
										</c:forEach>
									</select>
									
									 기준종료 :  
									<input type="hidden" id="EDATE" name="EDATE"
										maxlength="15" class="input_base" readonly="readonly"
										alt="시작일" title="" value=""
										style="width: 70px; align: center;" />
									<c:set var="today" value="<%=monthAgo%>" />
									<c:set var="datetime">
										<fmt:formatDate value="${today}" pattern="yyyy" />
									</c:set>
									<select id="eYear" name="eYear">
										<c:forEach var="i" begin="0" end="10">
											<c:choose>
												<c:when test="${i == 0}">
													<option value="${datetime}" selected>${datetime}년</option>
												</c:when>
												<c:otherwise>
													<option value="${datetime-i}">${datetime-i}년</option>
												</c:otherwise>
											</c:choose>
										</c:forEach>
									</select>
									
									<c:set var="today" value="<%=monthAgo%>" />
									<c:set var="datetime">
										<fmt:formatDate value="${today}" pattern="MM" />
									</c:set>
									<select id="eMon" name="eMon">
										<c:forEach var="i" begin="01" end="12">
											<c:choose>
												<c:when test="${i == datetime}">
													<option value="${i}" selected>${i}월</option>
												</c:when>
												<c:otherwise>
													<option value="${i}">${i}월</option>
												</c:otherwise>
											</c:choose>
										</c:forEach>
									</select>
									
									<input type="checkbox" id="dchker" />
									<div id="xdateDiv">
										제외월 시작 :  
										<input type="hidden" id="XSDATE" name="XSDATE"
											maxlength="15" class="input_base" readonly="readonly"
											alt="시작일" title="" value=""
											style="width: 70px; align: center;" />
										<c:set var="today" value="<%=monthAgo%>" />
										<c:set var="datetime">
											<fmt:formatDate value="${today}" pattern="yyyy" />
										</c:set>
										<select id="xsYear" name="xsYear">
											<c:forEach var="i" begin="0" end="10">
												<c:choose>
													<c:when test="${i == 0}">
														<option value="${datetime}" selected>${datetime}년</option>
													</c:when>
													<c:otherwise>
														<option value="${datetime-i}">${datetime-i}년</option>
													</c:otherwise>
												</c:choose>
											</c:forEach>
										</select>
										
										<c:set var="today" value="<%=monthAgo%>" />
										<c:set var="datetime">
											<fmt:formatDate value="${today}" pattern="MM" />
										</c:set>
										<select id="xsMon" name="xsMon">
											<c:forEach var="i" begin="01" end="12">
												<c:choose>
													<c:when test="${i == datetime}">
														<option value="${i}" selected>${i}월</option>
													</c:when>
													<c:otherwise>
														<option value="${i}">${i}월</option>
													</c:otherwise>
												</c:choose>
											</c:forEach>
										</select>
										
										 제외월 종료 :  
										<input type="hidden" id="XEDATE" name="XEDATE"
											maxlength="15" class="input_base" readonly="readonly"
											alt="시작일" title="" value=""
											style="width: 70px; align: center;" />
										<c:set var="today" value="<%=monthAgo%>" />
										<c:set var="datetime">
											<fmt:formatDate value="${today}" pattern="yyyy" />
										</c:set>
										<select id="xeYear" name="xeYear">
											<c:forEach var="i" begin="0" end="10">
												<c:choose>
													<c:when test="${i == 0}">
														<option value="${datetime}" selected>${datetime}년</option>
													</c:when>
													<c:otherwise>
														<option value="${datetime-i}">${datetime-i}년</option>
													</c:otherwise>
												</c:choose>
											</c:forEach>
										</select>
										
										<c:set var="today" value="<%=monthAgo%>" />
										<c:set var="datetime">
											<fmt:formatDate value="${today}" pattern="MM" />
										</c:set>
										<select id="xeMon" name="xeMon">
											<c:forEach var="i" begin="01" end="12">
												<c:choose>
													<c:when test="${i == datetime}">
														<option value="${i}" selected>${i}월</option>
													</c:when>
													<c:otherwise>
														<option value="${i}">${i}월</option>
													</c:otherwise>
												</c:choose>
											</c:forEach>
										</select>
										
									</div>
									
								</div>
							</fieldset> --%>
							<fieldset>
								기준 날짜 :
								<select id="standardYear">
								</select>
								년
								<select id="standardMon">
								</select>
								월
								<img src="../images/btn_search.gif" onclick="search();" style="cursor: pointer;     margin-left: 10px;" alt="검색" />
								
								<input type="hidden" name="START_DATE" id="START_DATE">
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