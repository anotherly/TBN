/************************************************************************
함수명 : createCctvMarker
설 명 : cctvList로부터 cctvMarker group생성 + 각 마커별 클릭이벤트 지정
인 자 : cctvList[i]
************************************************************************/
//function createCctvMarker(obj){
//	//var imageSrc  = "/images/map/cctvMarker.png";
//	var imageSrc  = "/images/map/CCTV_4.png";
//		imageSize = new kakao.maps.Size(45, 50);
//	var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize);
//	var position = new kakao.maps.LatLng(obj.xcoord, obj.ycoord);
//	var marker = new kakao.maps.Marker({
//						position: position,
//						image: markerImage,
//						clickable: true,
//						zIndex: 6,
//					});
//		 
//	kakao.maps.event.addListener(marker, 'click', function() {
//		if(iframeGroup.length > 0){
//			hideMarkers(iframeGroup);
//			iframeGroup=[];
//		}
//		playCctv(obj);
//	});
//	cctvMarkers.push(marker);
//}
function createCctvMarker(obj){
	var imageSrc  = "/images/map/CCTV_4.png";
	
	//level별 CCTVMarekr 크기
		imageSize = new kakao.maps.Size(45, 50);
	if(level == 6 || level == 5){
		imageSize = new kakao.maps.Size(35, 40);
	} else if(level == 7){
		imageSize = new kakao.maps.Size(25, 30);
	}
	
	var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize);
	var position = new kakao.maps.LatLng(obj.xcoord, obj.ycoord);
	var marker = new kakao.maps.Marker({
						position: position,
						image: markerImage,
						clickable: true,
						zIndex: 6,
					});
		 
	kakao.maps.event.addListener(marker, 'click', function() {
		console.log("cctv click");
		if(iframeGroup.length > 0){
			hideMarkers(iframeGroup);
			iframeGroup=[];
		}
		playCctv(obj);
	});
	cctvMarkers.push(marker);
}
/************************************************************************
함수명 : cctvInBounds
설 명 : 현재 로드된 Map범위내의 cctvMarker생성
인 자 : 
************************************************************************/
function cctvInBounds() {
	hideMarkers(cctvMarkers);
	cctvMarkers = [];
	var bounds = map.getBounds();
	var boundsVo = {minX : bounds.getSouthWest().getLat()
			, maxX : bounds.getNorthEast().getLat()
			, minY : bounds.getSouthWest().getLng()
			, maxY : bounds.getNorthEast().getLng()};
	
	if(level <= 7){ //지도레벨 8이하일때만 ajax실행 ==> 부하감소
		cctvList = ajaxMethod("/cctv/getCctvList.ajax", boundsVo).data;
	}
	for(var i=0; i<cctvList.length; i++){
		createCctvMarker(cctvList[i]);
	}
	
	if(cctvFlag){
		showMarkers(cctvMarkers);
		
		if(iframeGroup.length > 0){
			hideMarkers(iframeGroup);
			showMarkers(iframeGroup);
		}
	}
}
//function cctvInBounds() {
//	hideMarkers(cctvMarkers);
//	cctvMarkers = [];
//	var bounds = map.getBounds();
//	var boundsVo = {minX : bounds.getSouthWest().getLat()
//			, maxX : bounds.getNorthEast().getLat()
//			, minY : bounds.getSouthWest().getLng()
//			, maxY : bounds.getNorthEast().getLng()};
//	
//	if(level <= 8){ //지도레벨 8이하일때만 ajax실행 ==> 부하감소
//		cctvList = ajaxMethod("/cctv/getCctvList.ajax", boundsVo).data;
//	}
//	for(var i=0; i<cctvList.length; i++){
//		createCctvMarker(cctvList[i]);
//	}
//	
//	if(cctvFlag){
//		showMarkers(cctvMarkers);
//		
//		if(iframeGroup.length > 0){
//			hideMarkers(iframeGroup);
//			showMarkers(iframeGroup);
//		}
//	}
//}
/************************************************************************
함수명 : playCctv
설 명 : 클릭한 cctv재생 
인 자 : 선택한 cctv
************************************************************************/
//function playCctv(cctv){
//	var cctvUrl = generateCctvURL(cctv);
//	console.log("cctvUrl: "+cctvUrl);
//	
//	var cctvIframe = '<iframe id="cctvTooltip" src="' + cctvUrl + '" style="bottom:60px; left:-165px;" allowfullscreen></iframe>';
//		//cctvIframe += '<a onclick="closeCctv()"><img id="iframeClose" src="../images/map/btn_cctv_clse.gif"/></a>';
//		cctvIframe += '<img id="iframeClose" style="cursor:pointer; z-index:8;" src="../images/map/btn_cctv_clse.gif" onclick="closeCctv()" />';
//		cctvIframe += '<img id="triangleImg" src="/images/map/triangle.png" width="30px" height="30px" style="top:-63px; left:-15px;" />';
//		cctvIframe += '<img id="circleImg" src="images/map/circle_red.png" width="100px" height="30px" style="top:-14px; left:-50px;" />';
//	var position = new kakao.maps.LatLng(cctv.xcoord, cctv.ycoord);
//	var customOverlay = new kakao.maps.CustomOverlay({
//							position: position,
//							content: cctvIframe,
//							zIndex:7,
//						});
//	//map.setCenter(position);
//	iframeGroup.push(customOverlay);
//	customOverlay.setMap(map);
//	map.panTo(position);
//	
//	//iframe = $("#cctvTooltip").contents().find("body");
//	//console.log("length: " + iframe.length);
////	$("#cctvTooltip").contents().find("a").click(function(event){
////		closeCctv();
////	});
////	iframeCloseBtn.click(function(){
////		closeCctv();
////	});
//}

function playCctv(cctv){
	console.log("playCctv");
	var cctvUrl = generateCctvURL(cctv);
	console.log("url: " + cctvUrl);
	
	var url = cctvUrl;
	var windowName = "CCTV";
	var popupW = 390; // 팝업 넓이
	var popupH = 340; // 팝업 높이
	var left = (document.body.offsetWidth/2) - (popupW/2);
		left += window.screenLeft;
	var top = (document.body.offsetHeight/2) - (popupH/2);

	popObj = window
			.open(
				url,
				windowName,
					'width='+ popupW
				+ ', height='+ popupH
				+ ', left=' + left
				+ ', top=' + top
				+ ', toolbar=no, location=no, status=no, menubar=no, scrollbars=no, resizable=no');
}
/************************************************************************
함수명 : generateCctvURL
설 명 : 재생하려는 cctv url생성 
인 자 : 선택한 cctv
************************************************************************/
function generateCctvURL(cctv){
	var key = 'YKBLs3CFHnfvkbjrFEiplDgIQIUgnXcjyEe41qpKEHWDm6P0LRn1GZWcTp7a7';
	var kind = getCctvKind(cctv);
	var url = 'http://www.utic.go.kr/view/map/openDataCctvStream.jsp?key=' + key
		url += '&cctvid=' + cctv.cctvid;
		url += '&cctvName=' + encodeURI(encodeURIComponent(cctv.cctvname));
		url += '&kind=' + kind;
		url += '&cctvip=' + cctv.cctvip;
		url += '&cctvch=' + cctv.ch;	
		url += '&id=' + cctv.id ;
		url += '&cctvpasswd=' + cctv.passwd ;
		url += '&cctvport=' + cctv.port
	return url;
}
/************************************************************************
함수명 : getCctvKind
설 명 : 클릭한 cctv재생 
인 자 : 선택한 cctv
************************************************************************/
function getCctvKind(cctv) {
	var cctvid = cctv.cctvid;
	var kind = cctv.kind;
	if(cctvid.substring(0, 3) == 'L01'){
		kind = 'Seoul';
	} else if(cctvid.substring(0, 3) == 'L02'){
		kind = 'N';
	} else if(cctvid.substring(0, 3) == 'L03'){
		kind = 'O';
	} else if(cctvid.substring(0, 3) == 'L04'){
		kind = 'P';
	} else if(cctvid.substring(0, 3) == 'L08'){
		kind = 'd';
	} else{
		kind = kind;
	}
	return kind;
}
/************************************************************************
함수명 : closeCctv
설 명 : 클릭한 cctv닫기 
인 자 : 선택한 cctv
************************************************************************/
function closeCctv(){
	console.log("closeCctv함수 실행");
	hideMarkers(iframeGroup);
	iframeGroup=[];
}
/************************************************************************
함수명 : mapForCCTV
설 명 : CCTV열람을 위한 지도 생성 
인 자 :
************************************************************************/
function mapForCCTV(){
	var url = "http://www.utic.go.kr/etc/telMap.do?key=YKBLs3CFHnfvkbjrFEin9QdVWkDFp4Z4nIs9Yp9NxBT08X1vk1It4KMZlH46W";
	var windowName = "소통정보";
	var popupW = 1600; // 팝업 넓이

	var popupH = 850; // 팝업 높이
	var left = (document.body.offsetWidth/2) - (popupW/2);
		left += window.screenLeft;
	var top = (document.body.offsetHeight/2) - (popupH/2);

	popObj = window
			.open(
				url,
				windowName,
					'width='+ popupW
				+ ', height='+ popupH
				+ ', left=' + left
				+ ', top=' + top
				+ ', toolbar=no, location=no, status=no, menubar=no, scrollbars=no, resizable=no');
}





















