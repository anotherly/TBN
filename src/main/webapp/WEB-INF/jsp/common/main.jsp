<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
</head>
	<script>
		var ps;
		var map;
		var geocoder;
		
		//22.10.15 
		var apiChk=1;
		
		var cctvList;
		
		var marker; //싱글마커
		var cctvMarkers = []; //cctv마커그룹
		var resultMarkers = []; //장소검색결과 마커그룹
		var iframeGroup=[]; //재생되고 있는 cctvIframe group
		var trafficFlag = true;
		var cctvFlag = true;
		var searchFlag = false;

		// 22.10.15 수정전
		//var infowindow = new kakao.maps.InfoWindow({zIndex:1});
		//var center = new kakao.maps.LatLng(37.52334726919335, 126.92522447784268);
		
		//22.10.15 수정후 
		var infowindow;
		var center;
		
		var coordX;
		var coordY;
		var level = 4;
		
		//제보접수 페이지
		var todaysDate = currentDate(""); //오늘날짜
		var isCtrl;
		var currentPage;
		var totalPage;
		
		//전체접수 
		var allSchVo;
		
		//예약접수
		var firstInformerVO;
		var secondInformerVO;
		var thirdInformerVO;
		
		//자동로딩 : 접수현황,방송
		var autoLoading;
		var isPause;
		var onSearch;
		var autoLoadingFunction;
		var interval;
		var editOpenTotal;
		
		var pollingForCall; //전체 전화 (픽업시 통신원반영)
		var pollingForPickup; //전체 전화목록
		var pollingForMissedCall; //부재중 전화
		
		var isPick=false;
		var isMiss=false;
		
		//PD지원 child창
		var childwin = null;
		
		//datePicker
		var dates;
		
		// 로그인 사용자의 소속 방송국
		var lgnArea;
		// 로그인 사용자의 내선번호
		var inTelNum;
		var userName;
		var userId;
		var authCode;
		//메뉴이동 및 화면 관련
		var goUrl="";
		
		$(document).ready(function(){
			$("body").css("background","none");
			lgnArea = '${login.regionId}';
			inTelNum = '${login.inTel}';
			userName = '${login.userName}';
			userId = '${login.userId}';
			authCode = '${login.authCode}';
			
			// 22.10.15 수정전
			//center = initXYByRegionID(lgnArea);
			
			//console.log("메 인 화 면 : "+inTelNum);
			$("#menu").load("/common/menu.do");
			
			//로그인 성공 시 사용자 권한에 따라 화면 분기
			goMenuSite("/common/firstView.do");
			//자식 iframe으로부터 받은 리스너
			/* window.addEventListener('message', function(e) {
				console.log("From Iframe: " + e.data);
				$("#X_COORDINATE").val(e.data.xCh);
				$("#Y_COORDINATE").val(e.data.yCh);
				
				if (typeof e.origin !== 'undefined') {
					console.log("e.origin : "+e.origin);
				}
			}); */
		});
	</script>
</head>
<body>

	<div id="container" class="container">
		<div id='loginId' class="loginId" style="margin:3px 150px 0 0;">
			<div>
				<strong style="font-family: auto;">
				<c:if test="${login.authCode ne 999}">${login.regionName}</c:if> 
				${login.userName}</strong>님이 로그인하셨습니다.
				<a href="${path}/login/logout.do" style="color:mediumblue; font-weight:700;">로그아웃</a>
 					<a href="#" onclick="openPersonalMemo();" style="color:green; font-weight:700;">개인메모</a>	
 			</div>
		</div>
		<div id="header" class="header">
			<div id='logo' class="logo">
				<a href="${path}"><img src="../images/util_logo_tbn.png" alt="tbn한국교통방송 제보접수시스템"/></a>
			</div>
			<div id="menu" class="menu-bar"></div>
		</div>
	
		<div id="mainDiv" class="mainDiv">
		</div>
	</div>
</body>
</html>
