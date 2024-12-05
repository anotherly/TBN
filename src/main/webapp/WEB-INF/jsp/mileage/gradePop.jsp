<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script type="text/javascript" charset="utf-8" src="<%=request.getContextPath()%>/js/jquery.js"></script>
<title>등급 부여</title>
<style>
	table {
		border: 1px solid black;
		border-collapse : collapse;
	}
	
	th,td {
		border : 1px solid black;
	}
	
	th {
		width : 150px;
	}
	
	td {
		width : 200px;
		padding-left : 10px;
	}
	
	#payment {
		width: 80px;
	    height: 25px;
	    background-color: #f2f2f2;
	    text-align: center;
	    border-radius: 5px;
	    border: 0.5px solid black;
	    cursor: pointer;
	    margin-top: 20px;
	}
</style>
<script>
	var paymentYN = "";
	
	$(document).ready(function(){
		
		getTodayDate();
		
		$('#gradeSelect').on('change', function(){
			if($('#gradeSelect').val() == 'none') {
				$('#gradeCheck').text('');
				return false;
			} else {
				var grdCmile = parseInt($('#gradeSelect').val(), 10); 
				var cMile = parseInt($('#CUM_MILEAGE').val(), 10);
				var nowGrd = $('#nowGrade').val();
				var grd = $('#val'+grdCmile).val();
				var grdmile = $("input[value='" + nowGrd + "']").attr("id").substr(3);
				
				// false = 이미 부여된 등급에 부여 X
				// false1 = 부여된 등급 보다 낮은 등급 부여 시
				// fasle2 = 선택한 등급의 필요값 보다 누적 마일리지가 적은 경우
				
				if(grd == nowGrd) {
					$('#gradeCheck').text('부여된 등급 재부여 불가능');
					paymentYN = "false";
				} else if(grdmile > grdCmile) {
					$('#gradeCheck').text('선택한 등급 부여 불가능');
					paymentYN = "false1";
				} else {
					if(cMile > grdCmile) {
						$('#gradeCheck').text('선택한 등급 부여 가능');
						paymentYN = "true";
					}else {
						$('#gradeCheck').text('선택한 등급 부여 불가능');
						paymentYN = "false2";
					}
				}

			}

		});
		
		$('#payment').on('click', function() {
			
			if($('#gradeSelect').val() == 'none') {
				alert('등급을 선택하십시오');
				return false;
			} else {
				if(paymentYN == "false") {
					alert('이미 부여된 등급입니다.');
					return false;
				} else if(paymentYN == "false1") {
					alert('부여된 등급보다 낮은 등급입니다.');
					return false;
				} else if(paymentYN == "false2") {
					alert('등급 부여에 필요한 누적 마일리지 값이 부족합니다.');
					return false;
				} else {
					var checker = confirm("등급을 부여하시겠습니까?");
						
					if(checker) {
						console.log("부여");
	
						var grdCmile = parseInt($('#gradeSelect').val(), 10); 
						var cMile = parseInt($('#CUM_MILEAGE').val(), 10);
						var nowGrd = $('#nowGrade').val();
						var grd = $('#val'+grdCmile).val();
						
						$('#GRADE').val(grd);
						
						// 등급 부여하기 위한 컨트롤러로 이동
						$.ajax({
							url : '/mileage/sendGrade.do',
							type: 'POST',
							data : $('#gradeForm').serialize(),
							success : function() {
								console.log("등급 부여 정상 실행 완료");
								alert("등급 부여 창을 종료합니다.");
								window.opener.search();
								window.close();
							 },error: function(xhr, status, error) {
		                        console.log("등급 부여 오류 발생");
		                    }
						}); 
					} else {
						return false;
					}
				}
			}
					
		});
	});
	
	function getTodayDate() {
		var date = new Date();
		var today = date.getFullYear() + "-" + ("0" + (date.getMonth() + 1)).slice(-2) + "-" + ("0" + date.getDate()).slice(-2);
		
		$('#GRADE_DATE').val(today);
	}
</script>
</head>
<body>
	<h1 style="margin-left: 20px;">등급 부여</h1>
	<form name="gradeForm" id="gradeForm">
		<div style="display: flex; flex-direction: column; align-items: center;">
			<table style="width: 700px; height: 300px;">
				<tr style="height : 50px;">
					<th>통신원 ID</th>
					<td>${mileList[0].INFORMER_ID}</td>
					<th>통신원 이름</th>
					<td>${mileList[0].INFORMER_NAME}</td>
				</tr>
				<tr style="height : 50px;">
					<th>통신원 소속</th>
					<td>${mileList[0].ORG_NAME}</td>
					<th>통신원 연락처</th>
					<td>${mileList[0].PHONE_CELL}</td>
				</tr>
				<tr style="height : 50px;">
					<th>누적 마일리지</th>
					<td>${mileList[0].CUM_MILEAGE} pt</td>
					<th>잔여 마일리지</th>
					<td>${mileList[0].ALL_MILEAGE} pt</td>
				</tr>
				<tr style="height : 50px;">
					<th>현재 등급</th>
					<td>
					<p style="margin-bottom : 0; margin-top:10px;">${mileList[0].GRADE}</p>
					<p id="minus_after" style="font-size:12px; color : red; margin-top:0;"></p>
					</td>
					<th>등급 부여 선택</th>
					<td>
						<select style="margin-top:12px;" id="gradeSelect">
							<option value="none">선택하세요.</option>
							<c:forEach var ="i" items="${gradeList}">
								<option value="${i.CUM_MILEAGE}">${i.GRADE}</option>
							</c:forEach>
						</select>
						<c:forEach var="i" items="${gradeList}">
							<input type="hidden" id="val${i.CUM_MILEAGE}" value="${i.GRADE}">
						</c:forEach>

						<p id="gradeCheck" style="font-size:12px; color : red; margin-top:0;"></p>
					</td>
				</tr>
			</table>
			<input type="hidden" name="INFORMER_ID" value="${mileList[0].INFORMER_ID}">
			<input type="hidden" name="INFORMER_NAME" value="${mileList[0].INFORMER_NAME}">
			<input type="hidden" name="ACT_ID" value="${mileList[0].ACT_ID}">
			<input type="hidden" name="ORG_NAME" value="${mileList[0].ORG_NAME}">
			<input type="hidden" name="PHONE_CELL" value="${mileList[0].PHONE_CELL}">
			<input type="hidden" name="ALL_MILEAGE" value="${mileList[0].ALL_MILEAGE}">
			<input type="hidden" name="CUM_MILEAGE" id="CUM_MILEAGE" value="${mileList[0].CUM_MILEAGE}">
			<input type="hidden" id="nowGrade" value="${mileList[0].GRADE}">
			<input type="hidden" name="GRADE_DATE" id="GRADE_DATE">
			<input type="hidden" name="GRADE" id="GRADE">
			<div>
				<div id="payment">부여</div>
			</div>
		</div>
	</form>
</body>
</html>