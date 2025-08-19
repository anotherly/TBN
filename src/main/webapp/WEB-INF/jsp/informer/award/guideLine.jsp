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
	function changePage(url){
		
		if(url == 'select') {
			$.ajax
			(
				{
					type : "post" ,
					url : "/informer/award/awardMain.do" ,
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
		} else if(url == 'list'){
			$.ajax
			(
				{
					type : "post" ,
					url : "/informer/award/awardUserMain.do" ,
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
					url : "/informer/award/guideLine.do" ,
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
				<h1 class='content-title'>시상관리</h1>
				<!-- board_list -->
				<div class="board_list">
					<!-- 서브메뉴 탭영역 시작 -->
					<div class="gnb_tab">
						<ul class="lst_tab">
							<li><a href="javascript:changePage('select')">수상자선정</a></li>
							<li class="ns"></li>
							<li><a href="javascript:changePage('list')">수상자조회</a></li>
							<li class="ns"></li>
							<li class="on">시상관리 활용</li>
						</ul>
					</div>
					
					<div id="listDiv" style="margin-bottom:50px;">
						<p> - 향후 활용방안은 기재 예정입니다</p>							
					</div>
			</div>
		</div>
		
	</form>
</div>
</body>
</html>