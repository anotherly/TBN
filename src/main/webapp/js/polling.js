function startMissedCallPolling(){
	clearInterval(pollingForPickup);
	//console.log("부재중1 : "+currentTimeSmall());
	$("#missedCallList").load("/receipt/missedCallList.do?AREA_CODE="+lgnArea);
	
}
//수신전화 목록 풀링(주기 5초)
function startPickupCallPolling(){
	clearInterval(pollingForMissedCall);
	//console.log("수신목록1 : "+currentTimeSmall());
	$("#pickupCallList").load("/receipt/pickupCallList.do?AREA_CODE="+lgnArea+"&INTEL_NUM="+inTelNum);
	
}

//통신원 존재하면 전화받은 통신원정보 반영
function checkIfPickUpInfoExists(){
//	console.log("checkIfPickUpInfoExists : "+currentTimeSmall());
	//console.log("inTelN um: " + inTelNum);
	//console.log("AREA_CODE: " + lgnArea);
	var voList = ajaxMethod("/receipt/checkIfPickUpInfoExists.ajax", {"PICKUP_CALL_INTEL": inTelNum
																		, "AREA_CODE" : lgnArea}).data;
	
	if(voList.length > 0){
		for(var i=0; i<voList.length; i++){
			var pickUp_areaCode = voList[i].area_CODE;
			var pickUp_intel = voList[i].pickup_CALL_INTEL;
			//console.log("pickUp_areaCode: " + pickUp_areaCode);
			//console.log("pickUp_intel: " + pickUp_intel);

			if(pickUp_areaCode == lgnArea && pickUp_intel == inTelNum){
				//console.log("통신원view 생성");
				//통신원 정보를 조회함과 동시에 삭제하도록 처리
				selectInformerByPhoneTest(voList[i].pickup_CALL_FROM);
				//console.log("selectInformerByPhoneTest done");
				deletePickUpInfo(voList[i]);
			}
		}
	}
}

function deletePickUpInfo(vo){
	var result = ajaxMethod("/receipt/deletePickUpInfo.ajax", 
			{
				AREA_CODE : vo.area_CODE
				,PICKUP_CALL_DAY : vo.pickup_CALL_DAY
				,PICKUP_CALL_FROM : vo.pickup_CALL_FROM
				,PICKUP_CALL_ID : vo.pickup_CALL_ID
				,PICKUP_CALL_INTEL : vo.pickup_CALL_INTEL
				,PICKUP_CALL_TIME : vo.pickup_CALL_TIME
				,PICKUP_CALL_TO : vo.pickup_CALL_TO
			}
	).data;
}










