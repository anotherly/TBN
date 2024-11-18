<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<jsp:include page="../common/all.jsp" flush="false" />
<title>tbn교통방송 제보접수시스템</title>
<style>
	th,td {
		vertical-align:middle;
		font-size:14px;
	}
	
	.cancleButton{
		width: 80px;
	    height: 35px;
	    border-radius: 5px;
	    
	    margin-left :15px;
	}
	
	.saveButton,.deleteButton {
		width: 80px;
	    height: 35px;
	    border-radius: 5px;
	    color : white;
		background-color :#4973d4;
	}
	
</style>
<script>
	$(document).ready(function(){
		$("#menu").load("/common/menu.do");
		
		comparisonCode();
		
		function comparisonCode(){
			var aCode = '${login.authCode}';

			if(aCode == 999) {
				// 등록 및 기능 버튼 show
				$('#buttonDiv').show();
			} else {
				// 등록 및 기능 버튼 hide
				$('#buttonDiv').hide();
			}		
		}
		
		
		$('.saveButton').on('click', function(){
			var noticeId = $('#notice_id').val();
			location.href='/notice/goUpdate.do?noticeId='+noticeId;
		});
		
		
		$('.deleteButton').on('click', function(){
			var deleteCheck = confirm("정말로 삭제하시겠습니까?");
			var noticeId = $('#notice_id').val();
			
			if(deleteCheck) {
				location.href='/notice/delete.do?noticeId='+noticeId;
			} else {
				return false;
			}
		});
		
		
		$('.cancleButton').on('click', function() {
			history.back();
		});

	})
</script>
</head>
<body style="background:none;">
	<div id="changeBody">
		<div id="container" class="container">
			<div id='loginId' class="loginId" style="margin:3px 150px 0 0;">
				<div>
					<strong style="font-family: auto;">
					<c:if test="${login.authCode ne 999}">${login.regionName}</c:if> 
					${login.userName}</strong>님이 로그인하셨습니다.
					<a href="${path}/login/logout.do" style="color:mediumblue; font-weight:700;">로그아웃</a>
	 					<a href="#" onclick="openPersonalMemo();" style="color:green; font-weight:700;">개인메모</a>	
	 			</div>
			</div>
			<div id="header" class="header">
				<div id='logo' class="logo">
					<a href="${path}"><img src="../images/util_logo_tbn.png" alt="tbn한국교통방송 제보접수시스템"/></a>
				</div>
				<div id="menu" class="menu-bar"></div>
			</div>
		
		
			<div id="mainDiv" class="mainDiv" style="flex-direction:column; align-items: center;">
					<div class="insertTable" style="width: 880px; height: 500px; ">
			            <table style="width: 100%; height: 100%;">
			                <tr style="height: 50px; border-top : 2px solid black;border-bottom: 1px solid black;">
			                    <th>공지사항 제목 :</th>
			                    <td colspan='3'>
			                        ${dList[0].NOTICE_TITLE}
			                    </td>
			                </tr>
			                <tr style="height: 50px; border-bottom: 1px solid black;">
			                    <th>작성자 </th>
			                    <td>${dList[0].WRITER_NAME}</td>
	<!-- 		                    <th>공지사항 유형 </th>
			                    <td>
			                    	<input type="radio" name="notice_type" vlaue="">
			                    </td> -->
			                </tr >
			                <tr style="height: 50px; border-bottom: 1px solid black;">
			                    <th>시작일 </th>
			                    <td>${dList[0].START_DATE}</td>
			                    <th>종료일 </th>
			                    <td>
			                        ${dList[0].END_DATE}
			                    </td>
			                </tr>
			                <tr style="border-bottom: 1px solid black;">
			                    <th>공지사항 내용 </th>
			                    <td colspan='3'>                 
			                    	<div style="white-space: pre-line; width: 100%; height: 90%; box-sizing: border-box;">
			                    		${dList[0].NOTICE_CONTENT}
			                    	</div>
			                    </td>
			                </tr>
			            </table>
			            <input type="hidden" id="writer_id" value="${dList[0].WRITER_ID}">
			            <input type="hidden" id="notice_id" value="${dList[0].NOTICE_ID}">
			        </div>
			        <div id="buttonDiv" style="width: 880px; height: 100px; display: flex; flex-direction: row;align-items: center;justify-content: space-between;">
				        <div>
				        	<button class="deleteButton">삭제</button>
				        </div>
				        <div>
				        	<button class="saveButton">수정</button>
				        	<button class="cancleButton">취소</button>
				        </div>
			   		</div>
			</div>
		</div>
	</div>
</body>
</html>