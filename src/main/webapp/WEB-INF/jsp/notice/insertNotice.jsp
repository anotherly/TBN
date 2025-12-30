<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<!-- <meta charset="UTF-8"> -->
<%-- <jsp:include page="../common/all.jsp" flush="false" /> --%>
<%-- <link rel="stylesheet" href="http://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

<script src="<%=request.getContextPath()%>/calender/moment.js"></script>
	<script src="<%=request.getContextPath()%>/calender/mo_ko.js"></script>
	<script src="<%=request.getContextPath()%>/calender/bootstrap-datetimepicker.js"></script>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/calender/no-boot-calendar-custom.css" />
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/calender/datetimepickerstyle.css" /> --%>
	
	
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/layout.css"/>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/pagination.css" />
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/jquery-ui-1.9.0.custom.css" rel="stylesheet"  />
	<script  type="text/javascript" charset="utf-8" src="<%=request.getContextPath()%>/js/jquery.js"></script>
	
	<script  type="text/javascript" charset="utf-8"  src="<%=request.getContextPath()%>/js/common.js"></script>
	<script type="text/javascript" charset="utf-8" src="<%=request.getContextPath()%>/js/jquery.pagination.js"></script>
	<script type="text/javascript" charset="utf-8" src="<%=request.getContextPath()%>/js/jquery.form.js"></script>
	
	<script type="text/javascript" charset="utf-8" src="<%=request.getContextPath()%>/js/jquery.form.js"></script>
	<script type="text/javascript" charset="utf-8" src="<%=request.getContextPath()%>/js/timeDate.js"></script>
		
	<!-- DateTimePicker -->
	<script src="<%=request.getContextPath()%>/calender/moment.js"></script>
	<script src="<%=request.getContextPath()%>/calender/mo_ko.js"></script>
	<script src="<%=request.getContextPath()%>/calender/bootstrap-datetimepicker.js"></script>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/calender/no-boot-calendar-custom.css" />
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/calender/datetimepickerstyle.css" />
<title>tbn교통방송 제보접수시스템</title>
<style>
	th {
		background-color : #f2f2f2;
	}
	
	td {
		padding-left : 20px;
	}
	
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
/* 		$("#menu").load("/common/menu.do"); */
	
		/* dateFunc('EVENT_DATE'); */
		dateFunc('endDate');
		
		/* $('#endDate').datepicker({
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
		}); */
		
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
				let frm = $('#insertForm').serialize();
				
				var options = {
					url:"/notice/insert.do",
			        type:'post',
			        data: frm,
			        dataType: "json",
			        success: function(res){	
			        	if(res.cnt > 0) {
			        		console.log("성공");
			        		opener.search(true);
			        		self.close();
			        	} else {
			        		alert(res.msg);
			        	}
			        },
		            error: function(res,error){
		                alert("에러가 발생했습니다."+res.msg);
		            }
				}
				
				$('#insertForm').ajaxSubmit(options);
			}
				
				
				/* $.ajax({
					url : "/notice/insert.do",
					data : $('#insertForm').serialize(),
					type : "post",
					async : false,
		            dataType: 'json',
		            success : function(data) {	
						console.log("요청 성공"+data);
						
						alert('저장되었습니다.');
						
						var val = true;
						opener.search(val);
						self.close();
					},
					error : function(xhr, status, error) {
						console.log('공지사항 불러오기 ajax 요청에 문제가 있습니다.');
					}
				});
				
			} else {
				return false;
			} */
		});
		
		$('.cancleButton').on('click', function() {
			self.close();
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
		
	})
</script>
</head>
<body style="background:none;">
<h1 class="content-title" style="margin-top: 100px; margin-left:160px;">공지사항 등록</h1>
<div id="mainDiv" class="mainDiv" style="flex-direction:column; align-items: center; width: 1200px;">
			    <form name="insertForm" method="post" id="insertForm" enctype="multipart/form-data"> <!-- action="/notice/insert.do" -->
			        <div class="insertTable" style="width: 880px; height: 500px; ">
			            <table style="width: 100%; height: 100%;">
			                <tr style="height: 50px; border-top : 2px solid black;border-bottom: 1px solid black;">
			                    <th>공지사항 제목 :</th>
			                    <td colspan='3'>
			                        <input type="text" id="notice_title" name="NOTICE_TITLE" maxlength="50" style="width: 100%; box-sizing: border-box;">
			                    </td>
			                </tr>
			                <tr style="height: 50px; border-bottom: 1px solid black;">
			                    <th>작성자 </th>
			                    <td style="width:263px;">${login.userName}</td>
	 		                    <th style="width:150px;">공개 범위</th>
			                    <td>
			                    	<select name="OPEN_TYPE" id="OPEN_TYPE">
			                    		<option value="all">전체</option>
			                    		<option value="999">관리자만</option>
			                    		<option value="1">지역관리자만</option>
			                    		<option value="2">접수자만</option>
			                    		<option value="3">PD만</option>
			                    		<option value="4">캐스터만</option>
			                    	</select>
			                    </td>
			                </tr >
			                <tr style="height: 50px; border-bottom: 1px solid black; position: relative;">
			                    <th>시작일 </th>
			                    <td id="start"></td>
			                    <th>종료일 </th>
			                    <td >
			                        <input type="text" id="endDate" name="END_DATE">
			                    </td>
			                </tr>
			                <tr style="height: 50px; border-bottom: 1px solid black;">
			                	<th>파일 첨부</th>
			                	<td colspan='3'>
			                		<input type="file" name="multiFile" multiple/>
			                	</td>
			                </tr>
			                <tr style="border-bottom: 1px solid black;">
			                    <th>공지사항 내용 </th>
			                    <td colspan='3'>
			                        <textarea style="resize:none; width: 100%; height: 90%; box-sizing: border-box;" id="notice_content" name="NOTICE_CONTENT" maxlength="1000" wrap="hard"></textarea>
			                    </td>
			                </tr>
			            </table>
			            <input type="hidden" id="writer_id" name="WRITER_ID">
			            <input type="hidden" id="writer_name" name="WRITER_NAME">
			            <input type="hidden" id="start_date" name="START_DATE">
			        </div>
			    </form>
			    <div style="width: 880px; height: 100px; display: flex; flex-direction: row;align-items: center;justify-content: flex-end;">
			        <button class="saveButton">등록</button>
			        <button class="cancleButton">취소</button>
			    </div>
			</div>
</body>
</html>