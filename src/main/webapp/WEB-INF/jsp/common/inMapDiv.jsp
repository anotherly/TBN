<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script>
	$(document).ready(function(){
		console.log("콘텐츠 화면(지도 포함)");
		
		//url 체크하여 어느 화면으로 보낼지 분기
		$("#leftDiv").load(goUrl);
		if (map == undefined || $("#mapDiv").width()==0) {
			$("#mapDiv").load("/map/mainMap.do");
		}
	});
</script>
</head>
<body>
	<div id="leftDiv" class="leftDiv"></div>
	<div id="mapDiv"  class="mapDiv"></div>
</body>
</html>
