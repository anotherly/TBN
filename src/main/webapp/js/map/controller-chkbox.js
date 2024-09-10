

/*******************************************************************************
 * checkBox module
 ******************************************************************************/
	function all_check_module(){
		// 전체 체크
		var check_all = $('input:checkbox[id="accident_gubun_All"]').is(":checked");
		if($('input:checkbox[id="accident_gubun_All"]').is(":checked")){
			$("input:checkbox[name='accident_gubun']").prop("checked", true);
			$("input:checkbox[id='A']").prop("checked", true);
			$("input:checkbox[id='B']").prop("checked", true);
		}else{
			$("input:checkbox[name='accident_gubun']").prop("checked", false);
			a_check_module();
			b_check_module();	
		}
	}
	
	function a_check_module(){
		// 전체 체크
		var check_all = $('input:checkbox[id="allA"]').is(":checked");
		if(check_all){
			$("input:checkbox[id='A']").prop("checked", true);
		}else{
			$("input:checkbox[id='A']").prop("checked", false);		
		}
		if(check_all){
			$("input:checkbox[name='accident_gubun']").prop("checked", true);
		}else{
			if($('input:checkbox[id="allB"]').is(":checked") || $('input:checkbox[id="allC"]').is(":checked")){
				
			}else{
				$("input:checkbox[name='accident_gubun']").prop("checked", false);
			}
		}
	}
	
	function b_check_module(){
		// 전체 체크
		var check_all = $('input:checkbox[id="allB"]').is(":checked");
		if(check_all){
			$("input:checkbox[id='B']").prop("checked", true);
		}else{
			$("input:checkbox[id='B']").prop("checked", false);		
		}
		if(check_all){
			$("input:checkbox[name='accident_gubun']").prop("checked", true);
		}else{
			if($('input:checkbox[id="allA"]').is(":checked") || $('input:checkbox[id="allC"]').is(":checked")){
				
			}else{
				$("input:checkbox[name='accident_gubun']").prop("checked", false);
			}
		}
	}
	
	function gubun_check_module(){
		var accident = $("input[type=checkbox][class='accident']:checked").length;				
		var work = $("input[type=checkbox][class='work']:checked").length;			
		var event = $("input[type=checkbox][class='event']:checked").length;				
		var weather = $("input[type=checkbox][class='weather']:checked").length;
		var control = $("input[type=checkbox][class='control']:checked").length;
		if(accident > 0){
			$("input:checkbox[id='inc']").prop("checked", true);
		}else{
			$("input:checkbox[id='inc']").prop("checked", false);		
		}
		if(work > 0){
			$("input:checkbox[id='crp']").prop("checked", true);
		}else{
			$("input:checkbox[id='crp']").prop("checked", false);		
		}
		if(event > 0){
			$("input:checkbox[id='evt']").prop("checked", true);
		}else{
			$("input:checkbox[id='evt']").prop("checked", false);		
		}
		if(weather > 0){
			$("input:checkbox[id='wtr']").prop("checked", true);
		}else{
			$("input:checkbox[id='wtr']").prop("checked", false);		
		}
		if(control > 0){
			$("input:checkbox[id='ctr']").prop("checked", true);
		}else{
			$("input:checkbox[id='ctr']").prop("checked", false);		
		}
	}
	
	function accidentgubun_check_module(){
		var accident = $('input:checkbox[id="inc"]').is(":checked");				
		var work = $('input:checkbox[id="crp"]').is(":checked");			
		var event = $('input:checkbox[id="evt"]').is(":checked");				
		var weather = $('input:checkbox[id="wtr"]').is(":checked");
		var control = $('input:checkbox[id="ctr"]').is(":checked");
		if(accident){
			$("input:checkbox[class='accident']").prop("checked", true);
		}else{
			$("input:checkbox[class='accident']").prop("checked", false);
		}
		if(work){
			$("input:checkbox[class='work']").prop("checked", true);
		}else{
			$("input:checkbox[class='work']").prop("checked", false);		
		}
		if(event){
			$("input:checkbox[class='event']").prop("checked", true);
		}else{
			$("input:checkbox[class='event']").prop("checked", false);		
		}
		if(weather){
			$("input:checkbox[class='weather']").prop("checked", true);
		}else{
			$("input:checkbox[class='weather']").prop("checked", false);		
		}
		if(control){
			$("input:checkbox[class='control']").prop("checked", true);
		}else{
			$("input:checkbox[class='control']").prop("checked", false);		
		}
	}

/*******************************************************************************
 * 선택된 체크박스 값 넘기기
 ******************************************************************************/
function saveCheckList(){
		if(selectedImsMarkers.length != 0 || selectedImsMarkers != undefined) {
			selectedImsMarkers = [];
		}
		var map = new Map();
		
		var b ="";
		var c ="";
		var d ="";
		var g ="";
		var f ="";
		
		var len1 = $("input[name='accident']:checked").length;
		if(len1 > 0){ // 개수를 체크하고 2개부터는 each함수를 통해 각각 가져온다.
		    $("input[name='accident']:checked").each(function(e){
				b += $(this).val() +",";
		    })
		}
				b = b.substr(0, b.length -1);
		var len2 = $("input[name='work']:checked").length;
		if(len2 > 0){ // 개수를 체크하고 2개부터는 each함수를 통해 각각 가져온다.
		    $("input[name='work']:checked").each(function(e){
		        c += $(this).val() +",";
		    })
		}
				c = c.substr(0, c.length -1);
		var len3 = $("input[name='event']:checked").length;
		if(len3 > 0){ // 개수를 체크하고 2개부터는 each함수를 통해 각각 가져온다.
		    $("input[name='event']:checked").each(function(e){
		     	d += $(this).val() +",";
		    })		
		}
				d = d.substr(0, d.length -1);
		var len4 = $("input[name='weather']:checked").length;
		if(len4 > 0){ // 개수를 체크하고 2개부터는 each함수를 통해 각각 가져온다.
		    $("input[name='weather']:checked").each(function(e){
		      	g += $(this).val() +",";
		    })
		}
				g = g.substr(0, g.length -1);
		var len5 = $("input[name='control']:checked").length;
		if(len5 > 0){ // 개수를 체크하고 2개부터는 each함수를 통해 각각 가져온다.
		    $("input[name='control']:checked").each(function(e){
		      	f += $(this).val() +",";
		    })
		}
				f = f.substr(0, f.length -1);

		$(":checkbox[name='accident_gubun']:checked").each(function(){
			var checkbox_value = $(this).val();
				if(checkbox_value != '0'){
					if(checkbox_value == '1'){
						map.set("1", b);	
					}else if(checkbox_value == '2'){
						map.set("2", c);	
					}else if(checkbox_value == '3'){
						map.set("3", d);	
					}else if(checkbox_value == '4'){
						map.set("4", g);	
					}else if(checkbox_value == '5'){
						map.set("5", f);	
					}	
				}
		})	
		mapViewIncidentList(map); // 선택된 체크박스 값(map)
	}

// 체크박스 값 parsing => [종류, 선택등급]
function mapViewIncidentList(imsMap) {
	for(var [key, value] of imsMap) {
		var mapValueArry = value.split(",");
		for(var i=0; i<mapValueArry.length; i++) {
			setTempList(key, mapValueArry[i]);
		}
	}
}

function setTempList(key, value) {
	var selectedImsList = [];
	for(var j=0; j<imsList.length; j++) {
		var idMatched = [];
		if(imsList[j].incidenteTypeCd == key){
			// imsList로부터 선택된 체크박스와 일치하는 '사고종류'리스트
			idMatched.push(imsList[j]); 
		}
		for(var i=0; i<idMatched.length; i++) {
			// 선택된 등급과 일치하는 리스트 뽑아내기
			if(idMatched[i].incidenteGradeCd == value) {
				selectedImsList.push(idMatched[i]);
			}
		}
	}
	
	// 선택 된 체크박스와 일치하는 정보의 Marker Group생성
	for(var i=0; i<selectedImsList.length; i++) {
		createImsMarker(selectedImsList[i]);
	}
	console.log("selectedImsMarkers length: " + selectedImsMarkers.length);
}

//function displayTooltip2(ImsPosition){
//	for(var i=0; i<imsSavedList.length; i++) {
//		if(imsSavedList[i].locationDataY == ImsPosition.getLat()
//				&& imsSavedList[i].locationDataX == ImsPosition.getLng()) {
//			var title = getTitle(imsSavedList[i]);
//			$("#tooltipTitle").html(title);
//			$("#tooltipContent").html(imsSavedList[i].incidentTitle);
//			//$("#tooltipContent").addClass("slideinFromLeft");
//			//$("#tooltipContent").css("animation","slideinFromLeft 3s");
//			$("#tooltipStart").html(imsSavedList[i].startDate);
//			$("#tooltipEnd").html(" ~ " + imsSavedList[i].endDate);
//		}
//	}
//}



 
  	
  	

