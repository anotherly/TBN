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
<style>
	#listDiv p{
		font-size: 20px;
    	margin: 20px;
	}
</style>
<script>
/*  	$(document).ready(function(){

 	}); */
	 
	// 페이지 이동
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
	
	
</script>
</head>
<body>
<div id="contentWrap">
	<form id="searchFrm" name="searchFrm">
			<div id="contents">
				<h1 class='content-title'>최고 통신원</h1>
				<!-- board_list -->
				<div class="board_list">
					<!-- 서브메뉴 탭영역 시작 -->
					<div class="gnb_tab" style="margin-bottom:50px;">
						<ul class="lst_tab">
							<li><a href="javascript:changePage('bestInformerList')">최고통신원 조회</a></li>
							<li class="ns"></li>
							<li class="on">선정기준</li>
						</ul>
					</div>
					
					<div id="listDiv">
						<p> - 통신원 가입 20년 이상</p>							
						<p> - 최근 5년 제보건수 5,000건 이상</p>							
						<p> - 제보종류는 원활, 정체를 포함한 전부(연간 전체 수집건수 비중 고려)</p>							
					
					</div>
			</div>
		</div>
		
	</form>
</div>
</body>
</html>