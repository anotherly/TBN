<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<script>
$(document).ready(function(){
/* 	$("#mainDiv").css("width","100vw");
	$("#mainDiv").css("margin-top","0px"); */
	//checkbox 클릭시 event
	console.log("todayList");
	
	if(typeof chkList !== "undefined" && chkList.length){
		console.log("표출 리스트에서 체크리스트 : "+chkList);
		//방송할 리스트가 남아있을경우 자동로딩이 체크되있을 경우 자동로딩 체크 해제
		
		if($("#divTodayListautoLoading").is(':checked')){
			//클릭 트리거 지정
			$("#divTodayListautoLoading").trigger("click");
		}
		
		checkId="";
		for (var i = 0; i < chkList.length; i++) {
			var tmpId =chkList[i];
			$('#'+tmpId).find("#broadcast_check").prop("checked","true");
			checkId = checkId + $('#'+tmpId).find("#broadcast_check").val() + ",";
		}
		$('#todayAllcheck').prop("checked", true);
	}
	
	$(".broadcast_check").on("change", function(e){
		
		if(autoLoading){
			pauseFlagTo(true);
		}
		
		var checkCount = $("input[name=broadcast_check]:checked").length;
		
		if(this.checked) {
			if(checkCount > 10){
				/* alert("동시방송 가능한 최대갯수를 초과하였습니다.");
				this.checked = false; */
			} else{
				$('#todayAllcheck').prop("checked", true);
				checkId = checkId + this.value + ",";	
			}
		} else{
			checkId = checkId.replace(this.value+",","");
		}
		
		if(checkId === ""){
			if(isPause){
				pauseFlagTo(true);
			}
			$('#todayAllcheck').prop("checked", false);
		}
	});
	
});

</script>

<c:forEach var="toadysListVO" items="${todaysList}" varStatus="num">
	<c:choose>
		<c:when test="${toadysListVO.FLAG_BROD eq 'N'}">
			<li id="list_id${toadysListVO.RECEIPT_ID }" onmousedown="writeToText(${toadysListVO.RECEIPT_ID })" style="cursor: pointer; background-color:rgba(190,218,203,0.3);" ondbclick="doBroadcast(authCode)">
		</c:when>
		<c:otherwise>
			<li id="list_id${toadysListVO.RECEIPT_ID }" class="alreadyChecked" onmousedown="writeToText(${toadysListVO.RECEIPT_ID })" ondbclick="doBroadcast(authCode)" >
		</c:otherwise>
	</c:choose>
	
		<input type="hidden" id="receipt_id${toadysListVO.RECEIPT_ID }" value="${toadysListVO.RECEIPT_ID }" />
		<input type="hidden" id="informer_tel${toadysListVO.RECEIPT_ID }" value="${toadysListVO.r_TEL }" />
		<input type="hidden" id="broad_time${toadysListVO.RECEIPT_ID }" value="${toadysListVO.BROAD_TIME }" />
		<span style="width: 29px;">
			<input id="broadcast_check" name="broadcast_check" class="broadcast_check" style="width:15px;height:15px;"
				type="checkbox" value="${toadysListVO.RECEIPT_ID }" />
		</span>
		<span style="width: 7%;" id="number${toadysListVO.RECEIPT_ID }">${toadysListVO.RNUM }</span>
		<span style="width: 5%;">
			<c:choose>
				<c:when test="${toadysListVO.FLAG_BROD eq 'Y'}">
					<img src="../images/ico_pd2.png" alt="방송취소" title="방송취소" style="cursor: pointer;"
							onclick="cancleBroadcastToN(${toadysListVO.RECEIPT_ID})" />
				</c:when>
				<c:when test="${toadysListVO.FLAG_BROD eq 'C'}">
					<img src="../images/ico_caster2.png" alt="방송취소" title="방송취소" style="cursor: pointer;" 
							onclick="cancleBroadcastToN(${toadysListVO.RECEIPT_ID})" />
				</c:when>
				<c:otherwise>
					<img src="../images/ico_no2.png" alt="미확인" />
				</c:otherwise>
			</c:choose>
		</span>
		<span style="width: 9%;" id="time${toadysListVO.RECEIPT_ID }">${toadysListVO.RECEIPT_TIME } <br/> ${toadysListVO.BROAD_TIME }</span>
		<span style="width: 10%; color:${toadysListVO.f_COLOR }; background-color:${toadysListVO.b_COLOR };" id="report_type${toadysListVO.RECEIPT_ID }">${toadysListVO.BAS_NAME }</span>
		<span style="width: 12%;font-size: calc(6px + 0.6vw);line-height: calc(12px + 0.8vw);
				<c:if test="${toadysListVO.FLAG_KNEX =='Y'}">
					background-color:#00FFFF;
				</c:if>
		" id="informer_name${toadysListVO.RECEIPT_ID }">${toadysListVO.TYPE_NAME } <br/> ${toadysListVO.INDIVIDUAL_NAME }</span>
		<span style="width: 10%;" id="r_tel${toadysListVO.RECEIPT_ID }">${toadysListVO.r_TEL }</span> <!-- 전화번호 -->
		
		<!-- 긴급제보의 경우 노란바탕에 빨간글씨 -->		
		<c:choose>
   	   		<c:when test="${fn:contains(toadysListVO.FLAG_IMPORTANT, 'Y')  && toadysListVO.FLAG_BROD == 'N'}">
   				<span class="blinkcss" style="font-weight:700;color:#FF0000;background-color:#FFFF00;text-align: left;" id="contents${toadysListVO.RECEIPT_ID }">${toadysListVO.MEMO }</span>
   	   		</c:when>
   	   		<c:otherwise>
   	   			<span id="contents${toadysListVO.RECEIPT_ID }" style="text-align: left;">${toadysListVO.MEMO }</span> <!-- 내용 -->
   	   		</c:otherwise>
   		</c:choose>
		<span style="width: 7%;" id="area_name${toadysListVO.RECEIPT_ID }">${toadysListVO.AREA_NAME }</span> <!-- 방송국 -->
</c:forEach>
