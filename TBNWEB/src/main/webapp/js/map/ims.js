/************************************************************************
함수명 : createImsMarker
설 명 : obj로부터 selectedImsMarker group생성 + 각 마커별 클릭이벤트 지정
인 자 : obj[i] (setTemplist함수 안에 있음)
************************************************************************/
function createImsMarker(obj) {
	var ImsPosition = new kakao.maps.LatLng(obj.locationDataY, obj.locationDataX);
	var imageSrc = "";
		imageSize = new kakao.maps.Size(45, 45)
	var incidentTypeCd = obj.incidenteTypeCd;
	
	if(incidentTypeCd == "1") {
		imageSrc = "/images/map/accident.png";
	} else if(incidentTypeCd == "2") {
		imageSrc = "/images/map/repair.png";
	} else if(incidentTypeCd == "3") {
		imageSrc = "/images/map/event.png";
	} else if(incidentTypeCd == "4") { 
		imageSrc = "/images/map/weather.png";
	} else if(incidentTypeCd == "5") {
		imageSrc = "/images/map/control.png";
	} else {
		console.log("no image");
	}
	
	var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize);
	var marker = new kakao.maps.Marker({
					position: ImsPosition,
					image: markerImage,
					clickable: true
				});

	//각 Marker클릭 시 해당 Tooltip생성
	kakao.maps.event.addListener(marker, 'click', function() {
		console.log("ims marker 클릭");
		createTooltip(ImsPosition);
	});
	
	selectedImsMarkers.push(marker);
}
/************************************************************************
함수명 : getImsList
설 명 : UTIC에서 제공하는 RSS로부터 ims정보들 가져옴(AJAX)
************************************************************************/
function getImsList(){
	//alert("imsList");
	if(imsList == undefined || imsList.length < 1){
		imsList = ajaxMethod("/ims/getImsList.ajax").data;
		summarizeIms();
	}
	console.log("총 Ims length : " + imsList.length);
}
/************************************************************************
함수명 : summarizeIms
설 명 : 종류별 돌발정보 수 count
************************************************************************/
function summarizeIms() {
	for(var i=0; i<imsList.length; i++){
		var obj = imsList[i];
		if(obj.incidenteTypeCd == 1) {
			type1Cnt++;
		} else if(obj.incidenteTypeCd == 2) {
			type2Cnt++;
		} else if(obj.incidenteTypeCd == 3) {
			type3Cnt++;
		} else if(obj.incidenteTypeCd == 4) {
			type4Cnt++;
		} else if(obj.incidenteTypeCd == 5) {
			type5Cnt++;
		}
	}
	showImsTotal();
}
//function summarizeIms() {
//	for(var i=0; i<imsList.length; i++){
//		var obj = imsList[i];
//		if(obj.incidenteTypeCd == 1) {
//			type1Cnt++;
//			$("span#type1Cnt").html(type1Cnt);
//		} else if(obj.incidenteTypeCd == 2) {
//			type2Cnt++;
//			$("span#type2Cnt").html(type2Cnt);
//		} else if(obj.incidenteTypeCd == 3) {
//			type3Cnt++;
//			$("span#type3Cnt").html(type3Cnt);
//		} else if(obj.incidenteTypeCd == 4) {
//			type4Cnt++;
//			$("span#type4Cnt").html(type4Cnt);
//		} else if(obj.incidenteTypeCd == 5) {
//			type5Cnt++;
//			$("span#type5Cnt").html(type5Cnt);
//		}
//	}
//}
/************************************************************************
함수명 : showImsTotal
설 명 : 돌발현황 total 화면생성
인 자 : 
************************************************************************/
function showImsTotal(){
	$("span#type1Cnt").html(type1Cnt);
	$("span#type2Cnt").html(type2Cnt);
	$("span#type3Cnt").html(type3Cnt);
	$("span#type4Cnt").html(type4Cnt);
	$("span#type5Cnt").html(type5Cnt);
}
/************************************************************************
함수명 : createTooltip
설 명 : 
인 자 : 
사용법 : 
************************************************************************/
function createTooltip(ImsPosition){
	for(var i=0; i<imsList.length; i++) {
		if(imsList[i].locationDataY == ImsPosition.getLat()
				&& imsList[i].locationDataX == ImsPosition.getLng()) {
			//var title = getTitle(imsList[i]);
			$("#tooltipContent").html(imsList[i].incidentTitle);
			//$("#tooltipContent").css("animation","slideinFromLeft 3s");
			$("#tooltipStart").html(imsList[i].startDate);
			$("#tooltipEnd").html(" ~ " + imsList[i].endDate);
		}
	}
}
//function getTitle(tooltipData){
//	var str = "";
//	if(tooltipData.incidenteTypeCd == '1'){
//		str	= '돌발정보 [사고]';
//	}else if(tooltipData.incidenteTypeCd == '2'){
//		str	= '돌발정보 [공사]';
//	}else if(tooltipData.incidenteTypeCd == '3'){
//		str	= '돌발정보 [행사]';
//	}else if(tooltipData.incidenteTypeCd == '4'){
//		str	= '돌발정보 [날씨]';
//	}else if(tooltipData.incidenteTypeCd == '5'){
//		str	= '돌발정보 [통제]';
//	}else {
//		alert("No data");
//	}
//	return str;
//}






















