<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>tbn교통방송 제보접수시스템</title>

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/reset.css"/>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/login.css"/>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/style.css"/>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/font.css"/>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/map.css"/>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/jquery-ui-1.9.0.custom.css" rel="stylesheet"  />
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/layout.css"/>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/pagination.css" />
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/receipt.css"/>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/broadcast.css"/>

	<!-- JS -->
	<script type="text/javascript" charset="utf-8" src="<%=request.getContextPath()%>/js/jquery.js"></script>
	<script type="text/javascript" charset="utf-8" src="<%=request.getContextPath()%>/js/common.js"></script>
	<script type="text/javascript" charset="utf-8" src="<%=request.getContextPath()%>/js/jquery.pagination.js"></script>
	<script type="text/javascript" charset="utf-8" src="<%=request.getContextPath()%>/js/jquery.form.js"></script>
	<script type="text/javascript" charset="utf-8" src="<%=request.getContextPath()%>/js/timeDate.js"></script>
	<script type="text/javascript" charset="utf-8" src="<%=request.getContextPath()%>/js/regCheck.js"></script>
	<script type="text/javascript" charset="utf-8" src="<%=request.getContextPath()%>/js/paging.js"></script>
	<script type="text/javascript" charset="utf-8" src="<%=request.getContextPath()%>/js/autoLoading.js"></script>
	<script type="text/javascript" charset="utf-8" src="<%=request.getContextPath()%>/js/polling.js"></script>
	<script type="text/javascript" charset="utf-8" src="<%=request.getContextPath()%>/js/excelParser.js"></script>
		
	<!-- DateTimePicker -->
 	<script src="<%=request.getContextPath()%>/calender/moment.js"></script>
	<script src="<%=request.getContextPath()%>/calender/mo_ko.js"></script>
	<script src="<%=request.getContextPath()%>/calender/bootstrap-datetimepicker.js"></script>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/calender/no-boot-calendar-custom.css" />
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/calender/datetimepickerstyle.css" /> 
	<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
	<link rel="stylesheet" href="http://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
	
	
	
	<!--
	<script type="text/javascript" charset="utf-8" src="%=request.getContextPath()%>/js/jquery.datetimepicker.full.min.js"></script> 
	<link rel="stylesheet" type="text/css" href="%=request.getContextPath()%>/css/jquery.datetimepicker.min.css"/> 
	-->
	
	<!-- 제보접수 -->
	<script type="text/javascript" charset="utf-8" src="<%=request.getContextPath()%>/js/receipt/functionKey.js"></script>
	<script type="text/javascript" charset="utf-8" src="<%=request.getContextPath()%>/js/receipt/receipt.js"></script>
	<script type="text/javascript" charset="utf-8" src="<%=request.getContextPath()%>/js/receipt/receipt_right.js"></script>
	<script type="text/javascript" charset="utf-8" src="<%=request.getContextPath()%>/js/receipt/personalMemo.js"></script>
	<!-- 방송 -->
	<script type="text/javascript" charset="utf-8" src="<%=request.getContextPath()%>/js/broadcast/common.js"></script>
	<script type="text/javascript" charset="utf-8" src="<%=request.getContextPath()%>/js/broadcast/functionKey_broad.js"></script>
	
	<!-- mapJS -->
	<script  type="text/javascript" charset="utf-8"  src="<%=request.getContextPath()%>/js/map/map.js"></script>
	<script  type="text/javascript" charset="utf-8"  src="<%=request.getContextPath()%>/js/map/cctv.js"></script>
	<script  type="text/javascript" charset="utf-8"  src="<%=request.getContextPath()%>/js/map/ims.js"></script>
	<script  type="text/javascript" charset="utf-8"  src="<%=request.getContextPath()%>/js/map/location.js"></script>
	<script  type="text/javascript" charset="utf-8"  src="<%=request.getContextPath()%>/js/map/controller-chkbox.js"></script>
	<!-- Map -->
	<!--<script src="https://xn--on3b27gb0fhb.kr/js/map/kakao.js"></script>-->
	<script src='https://dapi.kakao.com/v2/maps/sdk.js?appkey=76926c8fc53b69aa9749cecf9354c83b&libraries=services'></script>
	<!-- <script src='https://dapi.kakao.com/v2/maps/sdk.js?appkey=54d14784e95f5570e5f47d4554cd1b9d&libraries=services'></script> -->
	
	<!-- 로그인 시큐어코딩 관련 -->
	<script  type="text/javascript" charset="utf-8"  src="<%=request.getContextPath()%>/js/loginSC/login.js"></script>
	
	<!-- 정보관리 -->
	<script  type="text/javascript" charset="utf-8"  src="<%=request.getContextPath()%>/js/option/option.js"></script>
	<script  type="text/javascript" charset="utf-8"  src="<%=request.getContextPath()%>/js/option/reportType.js"></script>
	<script  type="text/javascript" charset="utf-8"  src="<%=request.getContextPath()%>/js/option/colorCode.js"></script>
</head>
<body>
	
</body>
</html>