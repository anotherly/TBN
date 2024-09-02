<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	
<script  type="text/javascript" charset="utf-8" src="<%=request.getContextPath()%>/js/jquery.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/layout.css"/>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/broadcast.css"/>

<script>
	/* 22.03.15 충북요청사항 */
	$(document).ready(function(){
		
		var blkArea=0;//빈칸이 몇번째인지
		var flArea=1;//0: 빈칸있음,1: 꽉참
		
		$("#divMessage").hide();
		$('#stdTab1').addClass("click");
		/* $('#fsDiv').hide();
		$("#fsSlt").on("change",function(){
			console.log("폰트사이즈 체인지");
			$("#broad_message").css("font-size",$("#fsSlt").val());
		}); */		
		$(".studio-menu div").on("click",function(){
			console.log("스튜디오 전달사항 탭 전환");
			var chgId = $(this).attr('id');
			$(this).addClass("click");
			if(chgId=='stdTab1'){
				$('#stdTab1').css("background","lightgray");
				$('#stdTab2').css("background","white");
				$('#divBroadList').show();
				$('#divMessage').hide();
			}else{
				$('#stdTab1').css("background","white");
				$('#stdTab2').css("background","lightgray");
				$('#divMessage').show();
				$('#divBroadList').hide();
			} 
		});	
	});
</script>

<div id="" class="broad-right">
	<div class="studio-menu">
		<div id="stdTab1">스튜디오 ▼</div>
		<div id="stdTab2">전달사항 ▼</div>
	</div>
	<!-- 스튜디오 -->
	<ul>
		<li id="divBroadList" class="outer one click">
		<div>
			<div class="studio-btn">
				<img onclick="removeAllFromStudio()" type="image" src="../images/btn_reset1.png" alt="내용지우기" title="내용지우기" class="poin"/>
			</div> 
			<ul class="broadcastListHead">
				<li>
					<span style="width: 10%;">제보유형</span> 
					<span style="width: 13%;">제보/방송</span>
					<span>내용</span> 
					<span style="width: 15%;">유형 / 이름</span>
				</li>	
			</ul>
			<div class="studio-div">
				<ul id="stdioList" class="broadcastList2">
					<c:forEach var="i" begin="0" end="3">
						<li class="studio_top">
							<span style="width: 10%;" id="report_type${i}" class="st_boardside"></span>
							<span style="width: 13%;" id="time${i}" class="st_boardside"></span>
							<span style="" id="contents${i}" class="st_boardbin"></span>
							<span style="width: 15%;" id="informer_name${i}" class="st_boardside"></span>
						<!-- <img src="../images/trash-can1.png" alt="지우기" title="지우기" class="poin" style="width: 20px;margin-left: 8px;" /> -->
						</li>
					</c:forEach>					
				</ul>					
			</div>
		</div>
		</li>
		
		<!-- TEXT -->
		<li id="divMessage" class="outer two">
			<div>
				<div class="tab_center">
					<div class="studio-btn">
						<c:if test="${login.authCode == 3}">
							<img id="pdSendBtn" onclick="sendMessageToCaster();" type="image"
								src="../images/btn_send.png" alt="확인" title="확인" class="poin"/>
						</c:if>
						<img onclick="clearText()" type="image" src="../images/btn_reset1.png" alt="내용지우기" title="내용지우기" class="poin" />
					</div>
					<ul>
						<li><textarea id="broad_message" name="broad_message" class="broad_message" cols="50" rows="8"></textarea></li>
					</ul>
				</div>
			</div>
		</li>
	</ul>
</div>
