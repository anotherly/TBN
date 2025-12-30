<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
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
			
			let frm = $('#updateForm').serialize();
			
			var options = {
					url:"/notice/update.do",
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
			
		/* 	$.ajax({
				url : "/notice/update.do",
				data : $('#updateForm').serialize(),
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
			$('#updateForm').ajaxSubmit(options);
		}
		
	});
	
	$('.cancleButton').on('click', function() {
		self.close();
	});
	
	
	$(".deleteBtn").click(function() {
	    var fileId = $(this).attr("id");
		var noticeId = $('#NOTICE_ID').val();
	    
	    var options = {
	            url:"/notice/eventOneDelete.do",
	            type:'post',
	            data: {
	            	fileId: fileId , noticeId : noticeId
	            },
	            dataType: "json",
	            success: function(res, html){
	                if(res.cnt > 0){
	                    alert("삭제되었습니다.");
	                } else {
	         			alert("삭제에 실패하였습니다.");
	                }
	            } ,
	            error: function(res,error){
	                alert("에러가 발생했습니다."+error);
	            }
	    };
	    
	    
	    var delChk = confirm('삭제 시 되돌릴 수 없습니다. 정말 삭제하시겠습니까?');
	    if(delChk){
	        $.ajax(options);
	        $(this).hide(); // 클릭된 삭제 버튼 숨기기
	    	$(this).siblings().hide(); // 클릭된 요소의 형제 요소 숨기기
	    } else {
	    	return false;
	    }
	    
	});
	
	
	// 유효성 검사 
	function checkForm() {
		var title = $('#notice_title').val();
		var endDate = $('#endDate').val();
		var content = $('#notice_content').val();
		var startDate = $('#startDate').val();
		
		
		if(title == '') {
			alert('공지사항 제목을 입력해주세요.');
			return false;
		} else if(endDate == '') {
			alert('종료일을 입력해주세요.');
			return false;
		} else if(endDate < startDate) {
			alert('종료일은 시작일보다 이전일 수 없습니다.');
			return false;
		} else if(content == ''){
			alert('내용을 입력해주세요.');
			return false;
		} else {
			
			if(confirm('이대로 저장합니까?')) {
				return true;
			}else{
				return false;
			}
		}
	}
	
	});
	
</script>
</head>
<body style="background:none;">
<h1 class="content-title" style="margin-top: 100px; margin-left:160px;">공지사항 수정</h1>
<div id="mainDiv" class="mainDiv" style="flex-direction:column; align-items: center;  width: 1200px;">
			    <form action="/notice/update.do" method="post" id="updateForm" enctype="multipart/form-data"> <!-- from 및 테이블 만들어 수정/상세에서도 사용 -->
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
	                 			<th style="width:150px;">공개 범위</th>
			                    <td>
			                    	<select name="OPEN_TYPE" id="OPEN_TYPE">
									    <option value="all"<c:if test="${uList[0].OPEN_TYPE == 'all'}">selected</c:if>>전체</option>
									    <option value="999"<c:if test="${uList[0].OPEN_TYPE == '999'}">selected</c:if>>관리자만</option>
									    <option value="1"<c:if test="${uList[0].OPEN_TYPE == '1'}">selected</c:if>>지역관리자만</option>
									    <option value="2"<c:if test="${uList[0].OPEN_TYPE == '2'}">selected</c:if>>접수자만</option>
									    <option value="3"<c:if test="${uList[0].OPEN_TYPE == '3'}">selected</c:if>>PD만</option>
									    <option value="4"<c:if test="${uList[0].OPEN_TYPE == '4'}">selected</c:if>>캐스터만</option>
									</select>

			                    </td>
			                </tr >
			                <tr style="height: 50px; border-bottom: 1px solid black;">
			                    <th>시작일 </th>
			                    <td>${uList[0].START_DATE}
			                    	<input type="hidden" id="startDate" value="${uList[0].START_DATE}" readonly>
			                    </td>
			                    <th>종료일 </th>
			                    <td>
			                        <input type="text" id="endDate" name="END_DATE" value="${uList[0].END_DATE}" readonly>
			                    </td>
			                </tr>
			                <tr style="height: 50px; border-bottom: 1px solid black;">
			                	<th>파일 첨부</th>
			                	<td>
			                		<input type="file" class="input_base" name="multiFile" multiple/>
	                         		<c:if test="${fn:length(fileInfo) > 0}"> <!-- 첨부된 파일이 1개라도 있다면 -->
	                         			<c:forEach var="file" items="${fileInfo}" varStatus="status">
	                         			<div style="display:flex; height: 0px;">
										    <p style="margin-left: 15px;" id="${file.FILE_ID}">${file.FILE_NAME}</p> <p style="color:blue; margin-left:15px; cursor:pointer;" id="${file.FILE_ID}" class="deleteBtn">삭제</p>
										</div>
										<br>
										</c:forEach>
	                         		</c:if>
			                	</td>
			                </tr>
			                <tr style="border-bottom: 1px solid black;">
			                    <th>공지사항 내용 </th>
			                    <td colspan='3'>
			                        <textarea style="resize:none; width: 100%; height: 90%; box-sizing: border-box;" id="notice_content" name="NOTICE_CONTENT" maxlength="1000" wrap="hard">${uList[0].NOTICE_CONTENT}</textarea>
			                    </td>
			                </tr>
			            </table>
			            <input type="hidden" id="writer_id" name="WRITER_ID" value="${uList[0].WRITER_ID}">
			            <input type="hidden" id="writer_name" name="WRITER_NAME" value="${uList[0].WRITER_NAME}">
			            <input type="hidden" id="start_date" name="START_DATE" value="${uList[0].START_DATE}">
			            <input type="hidden" name="NOTICE_ID" id="NOTICE_ID" value="${uList[0].NOTICE_ID}">
			        </div>
			    </form>
			    <div style="width: 880px; height: 100px; display: flex; flex-direction: row;align-items: center;justify-content: flex-end;">
			        <button class="saveButton">저장</button>
			        <button class="cancleButton">취소</button>
			    </div>
			</div>
</body>
</html>