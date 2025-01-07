<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<jsp:include page="../common/all.jsp" flush="false" />
<link rel="stylesheet" href="http://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
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
		
		if ($("#changeTd").height() > 310) {
		    $('.insertTable').css("height","auto");
		} else {
			console.log("실행");
			$('.insertTable').css("height","500px");
		}
		
		$('.saveButton').on('click', function(){
			// 기존 팝업 닫기
/* 			self.close(); */
			
			var noticeId = $('#notice_id').val();
			/* var updatePop = window.open('/notice/goUpdate.do?noticeId='+noticeId,'공지사항 수정','width=700px,height=800px,scrollbars=yes'); */
			$('body').load('/notice/goUpdate.do?noticeId=' + noticeId);
		});
		
		
		$('.deleteButton').on('click', function(){
			var deleteCheck = confirm("정말로 삭제하시겠습니까?");
			var noticeId = $('#notice_id').val();
			
			if(deleteCheck) {

				$.ajax({
					url : "/notice/delete.do",
					data : {"noticeId" : noticeId},
					type : "post",
					async : false,
		            dataType: 'json',
					success : function(data) {	
						console.log("요청 성공"+data);
						
						alert('삭제되었습니다.');

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
			}
		});
		
		
		$('.cancleButton').on('click', function() {
			self.close();
		});

	})
	
		// 자식창에서 실행될 함수( 페이지 새로고침 )
		function search(val) {
			console.log("상세 서치");
			if(val == true) {
				var options = {
				        url:"/notice/notice.do",
				        type: "POST",
						//dataType: "json",
						async : false,
				        target: '#contents',
				        success: function(json){
				        	console.log("성공 불러오기");
				        	
				        	/* $('.admin_result_sc').hide(); */
				        	$('#contents').html(json);
				        	self.close();
				        },
				        error: function(res,error){
				            alert("에러가 발생했습니다."+res);
				        }
				    };
				    $.ajax(options);
			}

		}
	
</script>
</head>
<body style="background:none;">
<h1 class="content-title" style="margin-top: 100px; margin-left:160px;">공지사항 상세정보</h1>
<div id="mainDiv" class="mainDiv" style="flex-direction:column; align-items: center; width: 1200px;">
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
			                    <td colspan='3' id="changeTd">                 
			                    	<div style="white-space: pre-line; width: 100%; height: 90%; box-sizing: border-box; margin-bottom: 20px;">
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
</body>
</html>