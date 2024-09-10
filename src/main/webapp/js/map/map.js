/************************************************************************
함수명 : createMarker(싱글용)
설 명 : 마커생성 + return
인 자 : 위치좌표
************************************************************************/
function createMarker(position){
	//기존에 생성된 마커 제거(있다면)
    if(marker != undefined || marker != null){
		marker.setMap(null);
	}
    
    //새로운 마커 생성
    marker = new kakao.maps.Marker({ 
	    position: position
	});
	return marker;
}
/************************************************************************
함수명 : createMarkerImage(아직 미사용)
설 명 : marker image생성
인 자 : 
************************************************************************/
function createMarkerImage(src, size, options) {
    var markerImage = new kakao.maps.MarkerImage(src, size, options);
    return markerImage;            
}
/************************************************************************
함수명 : setMarkers, showMarkers, hideMarkers
설 명 : groupMarker 제어 함수
인 자 : 
************************************************************************/
function setMarkers(map,group) {
    for (var i = 0; i < group.length; i++) {
    	group[i].setMap(map);
    }            
}
function showMarkers(group) {
	if(level <= 7 || searchFlag){
		setMarkers(map,group)    
	} else{
		hideMarkers(group);
	}
}
function hideMarkers(group) {
    setMarkers(null,group);    
}

/************************************************************************
함수명 : cctvToggle
설 명 : cctv마커 토글
인 자 : 
************************************************************************/
function cctvToggle(){
	if(cctvFlag){
		//console.log("cctvFlag: " + cctvFlag);
		hideMarkers(cctvMarkers);
		hideMarkers(iframeGroup);
		cctvFlag = false;
	}else{
		//console.log("cctvFlag: " + cctvFlag);
		showMarkers(cctvMarkers);
		showMarkers(iframeGroup);
		cctvFlag = true;
	}
}
/************************************************************************
함수명 : 
설 명 : 키보드 누를때마다 글자 인식하는 함수
인 자 : 
************************************************************************/
function test(){
	var text_search = document.getElementById('text_search').value;
	if(window.event.keyCode==13){
		searchPlaces();
	}
	console.log(text_search);
}
/************************************************************************
함수명 : mapToggle
설 명 : 지도위에 띄울 컨텐츠선택/해제 (=>지도위에 여러 컨텐츠 띄울때 토글용으로 사용)
인 자 : 지도컨텐츠 버튼
************************************************************************/
//function mapToggle(content){
//	//var el = $('div#map_btnGroup li img.'+content);
//	var selectedEl = $('.'+content);
//	
//	if(selectedEl.hasClass('click')){
//		selectedEl.removeClass('click');
//		selectedEl.attr("src", "../images/map/"+content+"_off.png");
//		hideContent(content);
//	}else{
//		selectedEl.addClass('click');
//		selectedEl.attr("src", "../images/map/"+content+"_on.png");
//		showContent(content);
//	}
//}
/************************************************************************
함수명 : hideContent
설 명 : 해당content를 지도에서 숨김
인 자 : 지도컨텐츠 버튼
************************************************************************/
//function hideContent(content){
//	$('.map_'+content).hide();
//	
//	if(content == 'traffic'){
//		map.removeOverlayMapTypeId(kakao.maps.MapTypeId.TRAFFIC);
//	}
//}
/************************************************************************
함수명 : showContent
설 명 : 해당content를 지도에서 보여줌
인 자 : 지도컨텐츠 버튼
************************************************************************/
//function showContent(content){
//	$('.map_'+content).show();
//	
//	if(content == 'traffic'){
//		map.addOverlayMapTypeId(kakao.maps.MapTypeId.TRAFFIC);
//	}
//}
/************************************************************************
함수명 : 
설 명 : 
인 자 : 
************************************************************************/




/************************************************************************
함수명 : 
설 명 : 
인 자 : 
************************************************************************/




/************************************************************************
함수명 : 
설 명 : 
인 자 : 
************************************************************************/




/************************************************************************
함수명 : 
설 명 : 
인 자 : 
************************************************************************/

/************************************************************************
함수명 : 
설 명 : 
인 자 : 
************************************************************************/



