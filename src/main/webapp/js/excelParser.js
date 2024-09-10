
function connectToImsList(){
	var req = new XMLHttpRequest();
	req.onreadystatechange = function () {
		if (this.readyState == 4) {
			pasreImsXml(this);
		}
	};
	var url = "https://www.utic.go.kr:449/guide/imsOpenData.do?key=YKBLs3CFHnfvkbjrFEiplDgIQIUgnXcjyEe41qpKEHWDm6P0LRn1GZWcTp7a7"; /*URL*/
	req.open('GET', url, true);
	req.send();
}

function pasreImsXml(xml){
    var xmlDoc = xml.responseXML;
   //console.log("size: " + xmlDoc.getElementsByTagName("record").length);
    
    var broadcastList = $("#broadcastList");
	var source = "";
	var recordSize = xmlDoc.getElementsByTagName("record").length;
	$("#resultListTotal span").text(recordSize);
	
	for(var i=0; i<recordSize; i++){
		source += "<li>";
		source += 	'<span style="width:50px;">'+ (recordSize-i) +'</span>';
		source += 	'<span style="width:50px; font-weight:700;">'
						+ incidenteTypeCdToRealValue(xmlDoc.getElementsByTagName("incidenteTypeCd")[i].firstChild.data) 
					+'</span>';
		source += 	'<span style="width:140px;">'
						+ incidenteSubTypeCdToRealValue(xmlDoc.getElementsByTagName("incidenteSubTypeCd")[i].firstChild.data
														, xmlDoc.getElementsByTagName("incidenteTypeCd")[i].firstChild.data) 
					+'</span>';
		source += 	'<span style="width:60px;">'
						+ incidenteTrafficCdToRealValue(xmlDoc.getElementsByTagName("incidenteTrafficCd")[i].firstChild.data)
					+'</span>';
		source += 	'<span style="width:30px;">'
						+ incidenteGradeCdToRealValue(xmlDoc.getElementsByTagName("incidenteGradeCd")[i].firstChild.data)
					+'</span>';
		source += 	'<span style="width:70px;">'+ xmlDoc.getElementsByTagName("lane")[i].firstChild.data +'</span>';
		source += 	'<span style="width:570px; padding:5px 0;">'+ xmlDoc.getElementsByTagName("incidentTitle")[i].firstChild.data +'</span>';
		source +=  	'<span style="width:180px;">'+ xmlDoc.getElementsByTagName("startDate")[i].firstChild.data +'</span>';
		source +=  	'<span style="width:180px;">'+ xmlDoc.getElementsByTagName("endDate")[i].firstChild.data +'</span>';
		source += 	'<span>'
						+ incidentRegionCdToRealValue(xmlDoc.getElementsByTagName("incidentRegionCd")[i].firstChild.data)
					+'</span>';
		source += "</li>";
	}
	broadcastList.html(source);
}

function incidenteTypeCdToRealValue(code) {		//유형(대)
	var result = "";
	
	switch(code) {
	case "1" : result = "사고";
		break;
	case "2" : result = "공사";
		break;
	case "3" : result = "행사";
		break;
	case "4" : result = "기상";
		break;
	case "5" : result = "통제";
		break;
	default: result = code;
		break;
	}
	return result;
}

function incidenteSubTypeCdToRealValue(code, type) {	//유형(소)
	var result = "";
	
	if(type == "1") {
		result = subValueFor1(type);
	} else if(type == "2") {
		result = subValueFor2(type);
	} else if(type == "3") {
		result = subValueFor3(type);
	} else if(type == "4") {
		result = subValueFor4(type);
	} else if(type == "5") {
		result = subValueFor5(type);
	} else {
		result = code;
	}
	return result;
}

function subValueFor1(code) {
	var result = "";
	
	switch(code) {
		case "1" : result = "구조작업";
			break;
		case "10" : result = "도로파손";
			break;
		case "2" : result = "사고로 차량파손";
			break;
		case "255" : result = "교통사고";
			break;
		case "3" : result = "전복";
			break;
		case "4" : result = "화재";
			break;
		case "6" : result = "기름유출";
			break;
		case "7" : result = "차도 이탈";
			break;
		case "8" : result = "고장 차량";
			break;
		case "9" : result = "적재물 낙하";
			break;
		default: result = code;
			break;
	}
	return result;
}

function subValueFor2(code) {
	var result = "";
	
	switch(code) {
		case "1" : result = "재포장";
			break;
		case "13" : result = "노변 유지보수";
			break;
		case "14" : result = "배수거 청소";
			break;
		case "15" : result = "방책 유지보수";
			break;
		case "18" : result = "가로등 작업";
			break;
		case "19" : result = "표지판 작업";
			break;
		case "2" : result = "지하 시설 작업";
			break;
		case "255" : result = "도로공사";
			break;
		case "3" : result = "고가 시설 작업";
			break;
		case "4" : result = "하수도/배수로 작업";
			break;
		case "6" : result = "유지보수";
			break;
		case "7" : result = "도로도색";
			break;
		case "8" : result = "가로수 벌목";
			break;
		default: result = code;
			break;
	}
	return result;
}

function subValueFor3(code) {
	var result = "";
	
	switch(code) {
		case "1" : result = "복합행사";
			break;
		case "2" : result = "축제";
			break;
		case "255" : result = "행사";
			break;
		case "3" : result = "집회";
			break;
		case "4" : result = "스포츠 행사";
			break;
		case "5" : result = "국가 행사";
			break;
		case "6" : result = "문화 행사";
			break;
		default: result = code;
			break;
	}
	return result;
}

function subValueFor4(code) {
	var result = "";
	
	switch(code) {
		case "1" : result = "강풍";
			break;
		case "2" : result = "강우";
			break;
		case "255" : result = "기상";
			break;
		case "3" : result = "강설";
			break;
		case "4" : result = "지진 행사";
			break;
		default: result = code;
			break;
	}
	return result;
}

function subValueFor5(code) {
	var result = "";
	
	switch(code) {
		case "1" : result = "재해";
			break;
		case "10" : result = "공사";
			break;
		case "20" : result = "행사";
			break;
		case "255" : result = "기타";
			break;
		default: result = code;
			break;
	}
	return result;
}

function incidenteTrafficCdToRealValue(code) {	//소통현황
	var result = "";
	
	switch(code) {
	case "A0501" : result = "원활";
		break;
	case "A0502" : result = "서행";
		break;
	case "A0503" : result = "지체";
		break;
	default: result = code;
		break;
	}
	return result;
}

function incidenteGradeCdToRealValue(code) {		//등급
	var result = "";
	
	switch(code) {
	case "A0401" : result = "A";
		break;
	case "A0402" : result = "B";
		break;
	case "A0403" : result = "C";
		break;
	default: result = code;
		break;
	}
	return result;
}

function incidentRegionCdToRealValue(code) {		//지역
	var result = "";
	
	switch(code) {
		case "01" : result = "서울";
			break;
		case "02" : result = "인천";
			break;
		case "03" : result = "부천";
			break;
		case "04" : result = "광명";
			break;
		case "05" : result = "안양";
			break;
		case "06" : result = "과천";
			break;
		case "07" : result = "안산";
			break;
		case "08" : result = "용인";
			break;
		case "09" : result = "성남";
			break;
		case "10" : result = "고양";
			break;
		case "11" : result = "시흥";
			break;
		case "12" : result = "파주";
			break;
		case "13" : result = "양주";
			break;
		case "14" : result = "의정부";
			break;
		case "15" : result = "김포";
			break;
		case "16" : result = "의왕";
			break;
		case "17" : result = "군포";
			break;
		case "18" : result = "남양주";
			break;
		case "19" : result = "수원";
			break;
		case "20" : result = "광주(경기)";		//경기도 광주
			break;
		case "21" : result = "구리";
			break;
		case "22" : result = "하남";
			break;
		case "23" : result = "부산";
			break;
		case "24" : result = "양산";
			break;
		case "25" : result = "창원";
			break;
		case "26" : result = "김해";
			break;
		case "27" : result = "진주";
			break;
		case "28" : result = "거제";
			break;
		case "29" : result = "대구";
			break;
		case "30" : result = "대전";
			break;
		case "31" : result = "광주";		//광주광역시
			break;
		case "32" : result = "울산";
			break;
		case "33" : result = "전주";
			break;
		case "34" : result = "원주";
			break;
		case "35" : result = "청주";
			break;
		case "36" : result = "천안";
			break;
		case "37" : result = "포항";
			break;
		case "38" : result = "제주";
			break;
		default: result = code;
			break;
		}
	return result;
}

//var incidentId = xmlDoc.getElementsByTagName("incidentId")[0].firstChild.data;
//var addressJibun = xmlDoc.getElementsByTagName("addressJibun")[0].firstChild.data;
//var addressJibunCd = xmlDoc.getElementsByTagName("addressJibunCd")[0].firstChild.data;
//var addressNew = xmlDoc.getElementsByTagName("addressNew")[0].firstChild.data;
//var linkId = xmlDoc.getElementsByTagName("linkId")[0].firstChild.data;
//
//var incidentRegionCd = xmlDoc.getElementsByTagName("incidentRegionCd")[0].firstChild.data;
//var lineLinkId = xmlDoc.getElementsByTagName("lineLinkId")[0].firstChild.data;
//var incidenteTypeCd = xmlDoc.getElementsByTagName("incidenteTypeCd")[0].firstChild.data;
//var incidenteSubTypeCd = xmlDoc.getElementsByTagName("incidenteSubTypeCd")[0].firstChild.data;
//var locationDataX = xmlDoc.getElementsByTagName("locationDataX")[0].firstChild.data;
//
//var locationDataY = xmlDoc.getElementsByTagName("locationDataY")[0].firstChild.data;
//var locationTypeCd = xmlDoc.getElementsByTagName("locationTypeCd")[0].firstChild.data;
//var locationData = xmlDoc.getElementsByTagName("locationData")[0].firstChild.data;
//var incidenteTrafficCd = xmlDoc.getElementsByTagName("incidenteTrafficCd")[0].firstChild.data;
//var incidenteGradeCd = xmlDoc.getElementsByTagName("incidenteGradeCd")[0].firstChild.data;
//
//var important = xmlDoc.getElementsByTagName("important")[0].firstChild.data;
//var incidentTitle = xmlDoc.getElementsByTagName("incidentTitle")[0].firstChild.data;
//var startDate = xmlDoc.getElementsByTagName("startDate")[0].firstChild.data;
//var endDate = xmlDoc.getElementsByTagName("endDate")[0].firstChild.data;
//var lane = xmlDoc.getElementsByTagName("lane")[0].firstChild.data;
//
//var roadName = xmlDoc.getElementsByTagName("roadName")[0].firstChild.data;
//var sourceCode = xmlDoc.getElementsByTagName("sourceCode")[0].firstChild.data;
//var updateDate = xmlDoc.getElementsByTagName("updateDate")[0].firstChild.data;

























