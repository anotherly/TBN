<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script>
	$(document).ready(function(){
		console.log("콘텐츠 화면(지도 미포함)");
		$("#contentDiv").load(goUrl);
	});
</script>
</head>
<body>
	<div id="contentDiv" class="contentDiv"></div>
</body>
</html>
