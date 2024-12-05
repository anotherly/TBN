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
<title>상품 지급</title>
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
	var paymentYN = false;
	
	$(document).ready(function(){
		
		getTodayDate();
		
		$('#gCodeSelect').on('change', function(){
			
			if($('#gCodeSelect').val() == 'none') {
				$('#minus_val').text('');
				$('#minus_after').text('');
				return false;
			} else {
				var gCode = $('#gCodeSelect').val();
				var minusVal = parseInt($('#' + gCode + 'Val').val(), 10);
				var nowMile = parseInt('${mileList[0].ALL_MILEAGE}', 10); 
				var minusMile = nowMile - minusVal;
				
				$('#minus_val').text("차감 예상 마일리지 : " + minusVal + " pt");
				
				if(minusVal > nowMile){
					paymentYN = false;
					$('#minus_after').text("마일리지가 부족합니다.");
				} else {
					paymentYN = true;
					$('#minus_after').text("차감 후 마일리지 : " + minusMile + " pt");
				}
			}

		});
		
		$('#payment').on('click', function() {
			
			if($('#gCodeSelect').val() == 'none') {
				alert('상품을 선택하십시오');
				return false;
			} else {
				if(paymentYN == false) {
					alert('총 마일리지가 필요한 마일리지 보다 적습니다.');
					return false;
				} else {
					var checker = confirm("상품을 지급하시겠습니까?");
						var gCode = $('#gCodeSelect').val();
						var minusVal = parseInt($('#' + gCode + 'Val').val(), 10);
						var sendGift = $('#' + gCode + 'Name').val();
						
						
						$('#MINUS_MILE').val(minusVal);
						$('#PAYMENT_GIFT').val(sendGift);
								
					if(checker) {
						console.log("지급");
						
						// 지급하기 위한 컨트롤러로 이동
						$.ajax({
							url : '/mileage/sendGift.do',
							type: 'POST',
							data : $('#giftForm').serialize(),
							success : function() {
								console.log("상품 지급 정상 실행 완료");
								alert("상품 지급 창을 종료합니다.");
								window.opener.search();
								window.close();
							 },error: function(xhr, status, error) {
		                        console.log("상품 지급 오류 발생");
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
		
		$('#PAYMENT_DATE').val(today);
	}
</script>
</head>
<body>
	<h1 style="margin-left: 20px;">상품 지급</h1>
	<form name="giftForm" id="giftForm">
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
					<th>총 마일리지</th>
					<td>
					<p style="margin-bottom : 0; margin-top:10px;">${mileList[0].ALL_MILEAGE} pt</p>
					<p id="minus_after" style="font-size:12px; color : red; margin-top:0;"></p>
					</td>
					<th>지급 상품 선택</th>
					<td>
						<select style="margin-top:12px;" name="GCODE" id="gCodeSelect">
							<option value="none">선택하세요.</option>
							<c:forEach var ="i" items="${giftList}">
								<option value="${i.GCODE}">${i.GIFT_NAME}</option>
							</c:forEach>
						</select>
						<c:forEach var = "i" items="${giftList}">
							<input type="hidden" id="${i.GCODE}Val" value="${i.MINUS_VAL}">
						</c:forEach>
						<c:forEach var = "i" items="${giftList}">
							<input type="hidden" id="${i.GCODE}Name" value="${i.GIFT_NAME}">
						</c:forEach>
						<p id="minus_val" style="font-size:12px; color : red; margin-top:0;"></p>
					</td>
				</tr>
				<tr>
					<th>지급 사유</th>
					<td colspan='3'><textarea name="PAYMENT_TEXT" style="width:550px; height:140px;" maxlength="200"></textarea></td>
				</tr>
			</table>
			<input type="hidden" name="INFORMER_ID" value="${mileList[0].INFORMER_ID}">
			<input type="hidden" name="INFORMER_NAME" value="${mileList[0].INFORMER_NAME}">
			<input type="hidden" name="ACT_ID" value="${mileList[0].ACT_ID}">
			<input type="hidden" name="ORG_NAME" value="${mileList[0].ORG_NAME}">
			<input type="hidden" name="PHONE_CELL" value="${mileList[0].PHONE_CELL}">
			<input type="hidden" name="ALL_MILEAGE" value="${mileList[0].ALL_MILEAGE}">
			<input type="hidden" name="PAYMENT_GIFT" id="PAYMENT_GIFT">
			<input type="hidden" name="MINUS_MILE" id="MINUS_MILE">
			<input type="hidden" name="PAYMENT_DATE" id="PAYMENT_DATE">
			<div>
				<div id="payment">지급</div>
			</div>
		</div>
	</form>
</body>
</html>