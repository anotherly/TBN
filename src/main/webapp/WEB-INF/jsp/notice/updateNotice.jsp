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
	
	.saveButton {
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
		
		$('#endDate').datepicker({
			dateFormat: 'yy-mm-dd' //달력 날짜 형태
			,showOtherMonths: true //빈 공간에 현재월의 앞뒤월의 날짜를 표시
		    ,showMonthAfterYear:true // 월- 년 순서가아닌 년도 - 월 순서
		    ,changeYear: true //option값 년 선택 가능
		    ,changeMonth: true //option값  월 선택 가능                         
		    ,yearSuffix: "년" //달력의 년도 부분 뒤 텍스트
		    ,monthNamesShort: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'] //달력의 월 부분 텍스트
		    ,monthNames: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'] //달력의 월 부분 Tooltip
		    ,dayNamesMin: ['일','월','화','수','목','금','토'] //달력의 요일 텍스트
		    ,dayNames: ['일요일','월요일','화요일','수요일','목요일','금요일','토요일'] //달력의 요일 Tooltip
	});

	
	var userId = '${login.userId}';
	var userName = '${login.userName}';
	
	var today = new Date().toISOString().substring(0, 10);

	$('#start').text(today);
	$('#start_date').val(today);
	$('#writer_id').val(userId);
	$('#writer_name').val(userName);
	
	$('.saveButton').on('click', function() {
		// 유효성 검사 후 폼 보내기
		
		var isCheck = checkForm();
		
		if(isCheck) {
			$('#updateForm').submit();
		} else {
			return false;
		}
		
	});
	
	$('.cancleButton').on('click', function() {
		history.back();
	});
	
	
	// 유효성 검사 
	function checkForm() {
		var title = $('#notice_title').val();
		var endDate = $('#endDate').val();
		var content = $('#notice_content').val();
		
		if(title == '') {
			alert('공지사항 제목을 입력해주세요.');
			return false;
		} else if(endDate == '') {
			alert('종료일을 입력해주세요.');
			return false;
		} else if(endDate < today) {
			alert('종료일은 시작일보다 이전일 수 없습니다.');
			return false;
		} else if(content == ''){
			alert('내용을 입력해주세요.');
			return false;
		} else {
			return true;
		}
	}
	
	});
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
			    <form action="/notice/update.do" method="post" id="updateForm"> <!-- from 및 테이블 만들어 수정/상세에서도 사용 -->
			        <div class="insertTable" style="width: 880px; height: 500px; ">
			            <table style="width: 100%; height: 100%;">
			                <tr style="height: 50px; border-top : 2px solid black;border-bottom: 1px solid black;">
			                    <th>공지사항 제목 :</th>
			                    <td colspan='3'>
			                        <input type="text" value="${uList[0].NOTICE_TITLE}" id="notice_title" name="NOTICE_TITLE" maxlength="50" style="width: 100%; box-sizing: border-box;">
			                    </td>
			                </tr>
			                <tr style="height: 50px; border-bottom: 1px solid black;">
			                    <th>작성자 </th>
			                    <td>${uList[0].WRITER_NAME}</td>
	<!-- 		                    <th>공지사항 유형 </th>
			                    <td>
			                    	<input type="radio" name="notice_type" vlaue="">
			                    </td> -->
			                </tr >
			                <tr style="height: 50px; border-bottom: 1px solid black;">
			                    <th>시작일 </th>
			                    <td>${uList[0].START_DATE}</td>
			                    <th>종료일 </th>
			                    <td>
			                        <input type="text" id="endDate" name="END_DATE" value="${uList[0].END_DATE}" readonly>
			                    </td>
			                </tr>
			                <tr style="border-bottom: 1px solid black;">
			                    <th>공지사항 내용 </th>
			                    <td colspan='3'>
			                        <textarea style="resize:none; width: 100%; height: 90%; box-sizing: border-box;" id="notice_content" name="NOTICE_CONTENT" maxlength="1000" wrap="hard">${uList[0].NOTICE_CONTENT}"</textarea>
			                    </td>
			                </tr>
			            </table>
			            <input type="hidden" id="writer_id" name="WRITER_ID" value="${uList[0].WRITER_ID}">
			            <input type="hidden" id="writer_name" name="WRITER_NAME" value="${uList[0].WRITER_NAME}">
			            <input type="hidden" id="start_date" name="START_DATE" value="${uList[0].START_DATE}">
			            <input type="hidden" name="NOTICE_ID" value="${uList[0].NOTICE_ID}">
			        </div>
			    </form>
			    <div style="width: 880px; height: 100px; display: flex; flex-direction: row;align-items: center;justify-content: flex-end;">
			        <button class="saveButton">저장</button>
			        <button class="cancleButton">취소</button>
			    </div>
			</div>
		</div>
	</div>
</body>
</html>