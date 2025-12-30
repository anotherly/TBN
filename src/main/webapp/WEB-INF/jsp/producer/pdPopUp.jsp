<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta name ="viewport" http-equiv="Content-Type" 
	content="width-deivce-width,initial-scale=1.0,minimum-scale=1.0, maximum-scale=1.0,user-scale=1.0,text/html; charset=utf-8 " />
	<title>TBN한국교통방송 제보접수시스템</title>
	
<link rel="stylesheet" type="text/css" href="/css/layout.css"/>
<link rel="stylesheet" type="text/css" href="/css/broadcast.css"/>
<script  type="text/javascript" charset="utf-8" src="/js/jquery.js"></script>
<script type="text/javascript" charset="utf-8" src="/js/broadcast/common.js"></script>

</head>
<script>
	/* 22.03.15 충북요청사항 */
	$(document).ready(function(){
		
	});
	
	function removeEachFromChildStudio(num){
		$('#number'+num).text("");
		$('#informer_type'+num).text("");
		$('#report_type'+num).text("");
		$('#informer_name'+num).text("");
		$('#informer_tel'+num).text("");
		$('#contents'+num).text("");
		$('#time'+num).text("");
	}
	
	function appendToStudio_child(contentList, informerTypeList, reportTypeList, informerNameList,timeList,colorList,backgroundList){
		console.log("child appendToStudio 실행");
		removeAllFromStudio_child();
		for(i=0; i<contentList.length; i++){
			$('#contents'+i).text(contentList[i]);
			$('#informer_type'+i).text(informerTypeList[i]);
			$('#report_type'+i).text(reportTypeList[i]);
			$('#report_type'+i).css("color",colorList[i]);
			$('#report_type'+i).css("background",backgroundList[i]);
			$('#informer_name'+i).text(informerNameList[i]);
			$('#time'+i).append(timeList[i]);
		}
	}
	
	function removeEachFromStudio_child(num){
		$('#number'+num).text("");
		$('#informer_type'+num).text("");
		$('#report_type'+num).text("");
		$('#report_type'+num).css("color","#fff");
		$('#report_type'+num).css("background","#fff");
		$('#informer_name'+num).text("");
		$('#informer_tel'+num).text("");
		$('#time'+num).text("");
		$('#contents'+num).text("");
	}
	
	function removeAllFromStudio_child(){
		for(i=0;i<4;i++){
			removeEachFromStudio_child(i);
		}
	}
	
	function clearText_child(){
		$("#broad_message").val("");
		sendTextContent = "";
	}
	
</script>
<body>
		<!-- 스튜디오 -->
		<div id="stpop_child" style="display:flex;">
		
			<ul>
				<li id="divBroadList" class="outer one click">
				<div style="width: 60vw;overflow: auto;height: 98vh;">
				<h3 style="font-size:24px;margin:10px;">스튜디오</h3>
					<ul class="broadcastListHead">
						<li>
							<span style="width: 10%;">제보유형</span> 
							<span style="width: 10%;">제보/방송</span>
							<span>내용</span> 
							<span style="width: 19%;">유형/이름</span>
						</li>	
					</ul>
					<ul class="broadcastList3">
						<c:forEach var="i" begin="0" end="3">
							<li class="popup_li">
								<span style="width: 10%;" id="report_type${i}" class="st_boardside"></span>
								<span style="width: 10%;" id="time${i}" class="st_boardside"></span>
								<span style="" id="contents${i}" class="st_boardbin"></span>
								<span style="width: 19%;" id="informer_name${i}" class="st_boardside"></span>
									<!-- <img src="../images/trash-can1.png" alt="지우기" title="지우기" class="poin" style="width: 20px;margin-left: 8px;" /> -->
								
							</li>
						</c:forEach>					
					</ul>
				</div>
				</li>
			</ul>	
				
			<ul>
				<!-- TEXT -->
				<li id="divMessage" class="outer two">
					
					<div id="fsDiv" style="    position: absolute;top: 0px;left: 363px;width: 134px;font-size: 15px;font-weight: bold;display: flex;align-items: center;justify-content: space-evenly;">
					<!-- 글자크기  
						<select id="fsSlt">
							<option value="14px">14px</option>
							<option value="16px">16px</option>
							<option value="18px">18px</option>
							<option value="20px">20px</option>
							<option value="24px">24px</option>
							<option value="28px">28px</option>
							<option value="32px">32px</option>
							<option value="36px">36px</option>
							<option value="40px">40px</option>
							<option value="44px">44px</option>
							<option value="48px">48px</option>
							<option value="52px">52px</option>
							<option value="56px">56px</option>
							<option value="60px">60px</option>
						</select> -->
					</div>
					<div>
						<div class="tab_center">
						<h3 style="font-size:24px;margin:10px;">전달사항</h3>
							<ul>
								<li><textarea id="broad_message" name="broad_message" class="broad_message2" cols="50" rows="8"></textarea>
								</li>
							</ul>
						</div>
					</div>
				</li>
					
			</ul>
		</div>
	</body>
</html>
