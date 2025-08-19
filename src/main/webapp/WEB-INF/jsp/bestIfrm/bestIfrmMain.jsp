<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
	//한달 전
	Calendar mon = Calendar.getInstance();
	mon.add(Calendar.MONTH, -1);
	System.out.println("%%%%%%%%%%%%%%%% : " + mon);
	String beforeMonth = new java.text.SimpleDateFormat("YYYYMMdd").format(mon.getTime());
	System.out.println("%%%%%%%%%%%%%%%% : " + beforeMonth);

	//2
	Date monthAgo = new Date(new Date().getTime() - 60 * 60 * 24 * 1000 * 30L);
	System.out.println("%%%%%%%%%%%%%%%% : " + monthAgo);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script>
 	$(document).ready(function(){
 		search();
 	});
	
	
	// 리스트 검색
	function search(){

		var options = {
	            url:"/bestIfrm/bestIfrmList.do",
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
		
		if(url == 'bestInformerList') {
			$.ajax
			(
				{
					type : "post" ,
					url : "/informer/bestIfrm/bestIfrmMain.do" ,
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
					url : "/bestIfrm/standardMain.do" ,
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
	
	// "마일리지 산정기준" 버튼 클릭 시 => 마일리지 산정기준 팝업 띄우기 (24.12.26) / 기능 미완성
	/* $('#paymentMile').on('click', function() {
		console.log(" 마일리지 산정기준 진입 ");
		
		// 팝업 열기
		var milePopup = window.open('/mileage/mileStandard.do','마일리지 산정기준','');
		
	}); */
</script>
</head>
<body>
<div id="contentWrap">
	<form id="searchFrm" name="searchFrm">
		<!-- <div id="posi"><a href="/main.do"><img src="../images/ico_home.gif" alt="home" /></a>제보자관리 > 시상관리</div> -->
		<!-- <div id="searchDiv"> -->
			<div id="contents">
				<h1 class='content-title' style="margin-top:50px;">최고 통신원</h1>
				<!-- board_list -->
				<div class="board_list">
					<!-- 서브메뉴 탭영역 시작 -->
					<div class="gnb_tab">
						<ul class="lst_tab">
							<li class="on">최고통신원 조회</li>
							<li class="ns"></li>
							<li><a href="javascript:changePage('guideLine')">선정기준</a></li>
						</ul>
					</div>
					<%-- <div style="position: absolute;right: 0px;top: 52px;">
				       <select id="areaOptSel" name="AREA_CODE">
                           <c:forEach var="informerRegion" items="${informerRegionList}" varStatus="idx">
                               <option value="${informerRegion.areaCode}" ><c:out value="${informerRegion.areaName}"/></option>
                           </c:forEach>
						</select>
					</div> --%>
					
					<!-- 검색조건 영역 시작 -->
					<!-- <div class="rounding_wrap mgt10">
						<div class="wrap_top"></div>
						<div class="wrap_center">
							<fieldset style="display: flex; align-items: center; justify-content: center;">
								<div>
									지역 선택 :
										<select id="areaOptSel" name="AREA_CODE">
				                           	<c:forEach var="informerRegion" items="${informerRegionList}" varStatus="idx">
                                                 <option value="${informerRegion.areaCode}" 
                                                  <c:if test="${informerRegion.areaCode eq login.regionId}">selected</c:if>>
														${informerRegion.areaName}
                                                 </option>
                                             </c:forEach>
										</select>
										

								</div>
								<div id="awardSdiv">
									 <img src="../images/btn_search.gif" onclick="search();" style="cursor: pointer;     margin-left: 10px;" alt="검색" /> 
								</div>
	
							</fieldset>
						</div>
						<div class="wrap_bottom"></div>
					</div> -->
				<input type="hidden" name="PAYMENT_DATE" id="PAYMENT_DATE">
				<!-- list -->
				<div id="listDiv"></div>
			</div>
		</div>
		
	</form>
</div>
</body>
</html>