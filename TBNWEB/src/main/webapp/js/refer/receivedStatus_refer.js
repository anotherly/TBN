
function setDateTime(){
	var startDay = new Date();
	var startDate = startDay.getFullYear()+"-"+((startDay.getMonth()+1)>9? (startDay.getMonth()+1): '0'+(startDay.getMonth()+1))+"-"+(startDay.getDate() > 9 ? startDay.getDate():"0" +startDay.getDate());
	var toHH = startDay.getHours() > 9? startDay.getHours():  "0" + startDay.getHours();
	var toMM = startDay.getMinutes() > 9? startDay.getMinutes():  "0" + startDay.getMinutes();
	var toSS = startDay.getSeconds(); 
	toSS = toSS > 9 ? toSS : '0' + toSS;
	var str = (toHH > 12 ? "PM" : "AM");
	var hh = (toHH > 12 ? (toHH -12):toHH  );
	hh = hh > 9 ? hh : '0' + hh;
	$("#sysTime").html(startDate + " " + str +" " + hh  + ":" + toMM + ":" + toSS);
}


//제보유형 대 중 소 변경시
function codeOnChange(thisInformetTypeId,changeInformetTypeId){
	var paramObject = $("#"+thisInformetTypeId);
	var chageObject = $("#"+changeInformetTypeId);
	if(paramObject.val() == ""){
		chageObject.html("<option value='' >제보유형(중)</option>");
		return;
	}	
	$.ajax
	(
		{
			type : "post" ,
			url : "<c:url value='/receipt/codeChange2.do' />" ,
			dataType : "html" ,
			data : "REPORT_TYPE=" + paramObject.val(),
			cache : false ,
			success:function(html){
				chageObject.html(html);
				chageObject.change();
			} ,

			error:function(html,error){
				//alert("시스템에 문제가 생겼습니다." + error);
			}

		}
	);
}

function statusSearch(){
	$.ajax
	(
		{
			type : "post" ,
			url : "<c:url value='/receipt/receivedStatusSearch.do' />" ,
			dataType : "html" ,
			data :  $("#searchFrm").serialize(),
			cache : false ,
			success:function(html){
				$("#contentsDiv").html(html);
				setDateTime();
				clearTimeout(timeOut);
				onSidcheck();
			} ,

			error:function(html,error){
				//alert("시스템에 문제가 생겼습니다." + error);
			}

		}
	);
}

//지역구분 변경시
/*
function regionIdOnChange(){
	$.ajax
	(
		{
			type : "post" ,
			url : "<c:url value='/receipt/getUseArea.do' />" ,
			dataType : "html" ,
			data : "REGION_ID=" + $("#search1").val(),
			cache : false ,
			success:function(html){
				$("#search10").html("<option value=''>지역</option>" +html);
			} ,

			error:function(html,error){
				//alert("시스템에 문제가 생겼습니다." + error);
			}

		}
	);
}
*/
function popTextarea(receiptId){
	var url = '<c:url value="/receipt/textArea.do"/>?popType=receivedStatus';
	var windowName = "textArea";
	var popupW = 372;  // 팝업 넓이
    var popupH = 160;  // 팝업 높이
    var left = Math.ceil((window.screen.width - popupW)/2);
    var top = Math.ceil((window.screen.height - popupH)/2);
    
	popObj = window.open(url, windowName, 'width=' + popupW +  ', height=' + popupH +  ', left=' + left +  ', top=' + top + ', toolbar=no, location=no, status=no, menubar=no, scrollbars=no, resizable=no');
	
	$("#RECEIPT_ID").val(receiptId);
	
	searchFrm.method="post";
	searchFrm.action = url;
	searchFrm.target="textArea";
	searchFrm.submit();
	
	$("#RECEIPT_ID").val("");
	
}

var timeOut;
function onSidcheck(){
	var s = 1000 * Number($("#timess").val());
	if($("#sidcheck").is(":checked")){
		timeOut = setInterval("statusSearch()",s);
	}else{
		clearTimeout(timeOut);
	}
}


function searchTimechange(){
	var arrTime = new Array();
	
	if($("#times").val() == "TODAY"){
		$("#timetype").val($("#times").val());
	}else{
		arrTime = $("#times").val().split(",");
		$("#time").val(arrTime[0]);
		$("#timetype").val(arrTime[1]);
	}
}
