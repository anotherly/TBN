<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%
	//한달 전
	Calendar mon = Calendar.getInstance();
	mon.add(Calendar.MONTH, -1);
	System.out.println("%%%%%%%%%%%%%%%% : " + mon);
	String beforeMonth = new java.text.SimpleDateFormat("YYYYMMdd").format(mon.getTime());
	System.out.println("%%%%%%%%%%%%%%%% : " + beforeMonth);

	//2
	Date monthAgo = new Date(new Date().getTime() - 60 * 60 * 24 * 1000 * 30L);
	System.out.println("%%%%%%%%%%%%%%%% : " + monthAgo);
%>

<script>
var fcnt=0;
$(document).ready(function(){
	if(authCode=='999'){
		$("#areaOptSel").show();
	}else{
		$("#areaOptSel").hide();
	}
	
    $('.input_base').keydown(function(e) {
		if(e.keyCode==13){
			//기존 엔터버튼 누를 시 화면누락 방지 및 추가 및 수정 버튼클릭과 동일한 효과를 줌(트리거)
			e.preventDefault();
			search();			
		}
	});
    
  //최초에는 미사용이므로 비활성화(사용자관리 한정)
	//$(".dateText").attr("disabled", true);
	$('#dchker').prop('checked',true);
	
	$('#dchker').on("click", function(){
		console.log("시상 제외 체크 클릭");
		if($(this).is(":checked")){
			console.log("체크됨");
			$("#xdateDiv *").attr("disabled", false);
		}else{
			console.log("체크안됨");
			$("#xdateDiv *").attr("disabled", true);
		}
	});
    
    init();
    fcnt=1;
})

function init(){
	//setComboBoxDate();
	search();
}

/* function setComboBoxDate(){
	var today = new Date();
	var yearOption = "";
	var monthOption = "";
	
	for(var i=1;12 >= i;i++  ){
		monthOption += "<option value='"+ (i>9?i:('0'+i)) +"' >"+(i>9?i:('0'+i))+"</option>";
	}
	$("#startMonth").html(monthOption);
	$("#startMonth").val((today.getMonth()+1)>9? (today.getMonth()+1): '0' +(today.getMonth()+1));
} */

/**
 * 검색
 */
function search(){
	console.log("수상기준검색");
	//시작
	var stDt=$("#sMon").val();
	if($("#sMon").val().length==1){
		stDt="0"+stDt;
	}
	$('#SDATE').val($('#sYear').val()+stDt);
	//종료
	var eDt=$("#eMon").val();
	if($("#eMon").val().length==1){
		eDt="0"+eDt;
	}
	$('#EDATE').val($('#eYear').val()+eDt);
	//제외버튼이 체크가 되어있다면
	if($("#xdateDiv *").attr("disabled")==undefined){
/* 		//제외시작
		var xstDt=$("#xsMon").val();
		if($("#xsMon").val().length==1){
			xstDt="0"+xstDt;
		}
		$('#XSDATE').val($('#xsYear').val()+xstDt);
		//제외종료
		var xeDt=$("#xeMon").val();
		if($("#xeMon").val().length==1){
			xeDt="0"+xeDt;
		}
		$('#XEDATE').val($('#xeYear').val()+xeDt); */
		
		var xSdateStr = $('#SDATE').val();
		
		var yearXS = parseInt(xSdateStr.substr(0, 4), 10);
		var monthXS = parseInt(xSdateStr.substr(4, 2), 10);
		
		var xSdateFor = new Date(yearXS, monthXS - 3, 1);
		var xSdate = xSdateFor.getFullYear().toString() + (xSdateFor.getMonth() + 1).toString().padStart(2, '0');
		
		var xEdateFor = new Date(yearXS, monthXS - 1, 1);
		xEdateFor.setMonth(xEdateFor.getMonth() - 1);		
		var xEdate = xEdateFor.getFullYear().toString() + (xEdateFor.getMonth() + 1).toString().padStart(2, '0');
				
		$('#XSDATE').val(xSdate);
		$('#XEDATE').val(xEdate);
		
		// 화면에 텍스트 표출
		$('#xsYear').val(yearXS + "년 " + (monthXS - 3) + "월");
	    $('#xeYear').val(yearXS + "년 " + (monthXS - 1) + "월");
		
	}
	if(fcnt!=0){
		
		if(parseInt($('#ALL_PER').val()) > 25 || parseInt($('#ALL_PER').val()) < 15) {
			alert("시상 배점기준(제보) % 범위는 15% ~ 25% 사이어야합니다.");
		} else if(parseInt($('#MAIN_PER').val()) < 50 || parseInt($('#MAIN_PER').val()) > 70) {
			alert("시상 배점기준(주요) % 범위는 50% ~ 70% 사이어야합니다.");
		} else if(parseInt($('#ADD_PER').val()) > 25 || parseInt($('#ADD_PER').val()) < 15) {
			alert("시상 배점기준(전월) % 범위는 15% ~ 25% 사이어야합니다.");
		} else if( parseInt($('#ALL_PER').val())+parseInt($('#MAIN_PER').val())+parseInt($('#ADD_PER').val())!=100 ){
			alert("시상 배점기준(제보,주요,전월)% 의 합은 100이어야 합니다.");
		}		
	}
	
    var options = {
            url:"/informer/award/awardInformerList.do",
            type:"post",
            target: '#listDiv',
            success: function(){
            },
            error: function(res,error){
                alert("에러가 발생했습니다."+error);
            }
    };
    $('#searchFrm').ajaxSubmit(options);
}

	//저장(선정)
	function save(){
		console.log("저장");
		var selectionCheck =  searchFrm.Selection;
		var selectionCnt = 0;
		if($("input:checkbox[name=Selection]:checked").length!=0){
			if(!confirm("체크된 " + $("#searchType option:selected").text() + "를\n 선정하시겠습니까?" )){
				return;
			}
			
			var awNameStr = "";
			var termD="";
			
			//기간 수상자인지 단월 수상자인지 판별
			if($("#SDATE").val()==$("#EDATE").val()){//단월 수상자
				termD=$("#sYear").val() + "년 " + $("#sMon").val() +"월의 ";
			}else{//기간 수상자
				termD=$("#sYear").val() + "년 " + $("#sMon").val() +"월"+" ~ "+$("#eYear").val() + "년 " + $("#eMon").val() +"월의 ";
			}
			
			awNameStr += termD+$("#searchType option:selected").text();
			$("#AW_NAME").val(awNameStr);
			let tagVal = $("#searchFrm").serialize();
			
			console.log(tagVal);
			$.ajax
			(
				{
					type : "post" ,
					url : "/informer/award/awardInformerSave.do" ,
					dataType : "json" ,
					data : tagVal,
					cache : false ,
					success:function(data){
						if(data.msg == "success") alert("저장되었습니다.");
						search();
					} ,
	
					error:function(data,error){
						alert("시스템에 문제가 생겼습니다.");
					}
	
				}
			);
			
		}else{
			alert("수상자를 선택해 주세요");
		}
		
	}

	//수상자선정 -> 시상자목록 화면 전환
	function changePage(){
		$.ajax
		(
			{
				type : "post" ,
				url : "/informer/award/awardUserMain.do" ,
				dataType : "html" ,
				data : $("#searchFrm").serialize(),
				cache : false ,
				success:function(html){
					$('#contentWrap').html(html);
				} ,
	
				error:function(data,error){
					alert("시스템에 문제가 생겼습니다." + data);
				}
	
			}
		);
	}
	
	//엑셀 다운로드 기능 제공
	function excelDownload(){
		rkFlag = true;
		searchFrm.action =  "/informer/award/excelDownloadInformerList.do";
		searchFrm.submit();
		rkFlag = true;
	}
	
	
	// 셀렉트 박스 선택 시 처리
	$('#sMon , #sYear').on('click', function(){
	    var stDt = $("#sMon").val();
	    if (stDt.length == 1) stDt = "0" + stDt;
	    var startDate = $('#sYear').val() + stDt;

	    var year = parseInt(startDate.substr(0, 4), 10);
	    var month = parseInt(startDate.substr(4, 2), 10);

	    var currentDate = new Date(year, month - 1, 1);
	    currentDate.setMonth(currentDate.getMonth() - 3);
	    var xsResult = currentDate.getFullYear() + "년 " + (currentDate.getMonth() + 1).toString().padStart(2, '0') + "월";

	    currentDate.setMonth(currentDate.getMonth() + 2);
	    var xeResult = currentDate.getFullYear() + "년 " + (currentDate.getMonth() + 1).toString().padStart(2, '0') + "월";

	    $('#xsYear').val(xsResult);
	    $('#xeYear').val(xeResult);
	    
	});
	
</script>
<div id="contentWrap">
	<form id="searchFrm" name="searchFrm">
		<!-- <div id="posi"><a href="/main.do"><img src="../images/ico_home.gif" alt="home" /></a>제보자관리 > 시상관리</div> -->
		<div id="searchDiv">
			<div id="contents">
				<h1 class='content-title'>시상관리</h1>
				<!-- board_list -->
				<div class="board_list">
					<!-- 서브메뉴 탭영역 시작 -->
					<div class="gnb_tab">
						<ul class="lst_tab">
							<li class="on">수상자선정</li>
							<li class="ns"></li>
							<li><a href="javascript:changePage()">수상자조회</a></li>
						</ul>
					</div>
					<div style="position: absolute;right: 0px;top: 52px;">
				       <select id="areaOptSel" name="AREA_CODE">
                           <c:forEach var="informerRegion" items="${informerRegionList}" varStatus="idx">
                               <option value="${informerRegion.areaCode}" ><c:out value="${informerRegion.areaName}"/></option>
                           </c:forEach>
						</select>
					</div>
					
					<!-- 검색조건 영역 시작 -->
					<div class="rounding_wrap mgt10">
						<div class="wrap_top"></div>
						<div class="wrap_center">
							<fieldset>
								<div id="awardSdiv">
									시상종류 :  
									<select class="table_sel1" id="searchType"
										onchange="search();" name="searchType">
										<option value="0">교통제보우수자</option>
									<!--<option value="1">주요정보제공우수자</option>
										<option value=ㄴ"2">실적증가자</option> -->
									</select> 
									<select class="table_sel1" id="searchNum" name="searchNum">
										<option value="10000">전체 출력</option>
										<option value="30">30명 출력</option>
										<option value="50">50명 출력</option>
										<option value="70">70명 출력</option>
										<option value="100">100명 출력</option>
									</select> 
									
									제보 : 
									<input type="text" class="input_base" id="ALL_PER" name="ALL_PER" value="${awardGrade.ALL_PER}" style="width:25px;text-align:center;" maxlength="2" onkeydown='return onlyNumber(event)' onkeyup='removeChar(event)' required/>
									 % 
									주요 : 
									<input type="text" class="input_base" id="MAIN_PER" name="MAIN_PER" value="${awardGrade.MAIN_PER}" style="width:25px;text-align:center;" maxlength="2" onkeydown='return onlyNumber(event)' onkeyup='removeChar(event)'required/>
									 % 
									전월 : 
									<input type="text" class="input_base" id="ADD_PER" name="ADD_PER" value="${awardGrade.ADD_PER}" style="width:25px;text-align:center;" maxlength="2" onkeydown='return onlyNumber(event)' onkeyup='removeChar(event)'required/>
									 % 
									 <img src="../images/btn_search.gif" onclick="search();" style="cursor: pointer;     margin-left: 10px;" alt="검색" /> 
								</div>

								<div id="awardSdiv">
									<!-- fmt:parseDate : String 형을 받아서 원하는 포맷으로 Date 형태로 변경 
										fmt:formatDate : Date 형을 받아서 원하는 포맷으로 날짜형태를 변경 -->
									기준시작 :  
									<input type="hidden" id="SDATE" name="SDATE"
										maxlength="15" class="input_base" readonly="readonly"
										alt="시작일" title="" value=""
										style="width: 70px; align: center;" />
									<c:set var="today" value="<%=monthAgo%>" />
									<c:set var="datetime">
										<fmt:formatDate value="${today}" pattern="yyyy" />
									</c:set>
									<select id="sYear" name="sYear">
										<c:forEach var="i" begin="0" end="10">
											<c:choose>
												<c:when test="${i == 0}">
													<option value="${datetime}" selected>${datetime}년</option>
												</c:when>
												<c:otherwise>
													<option value="${datetime-i}">${datetime-i}년</option>
												</c:otherwise>
											</c:choose>
										</c:forEach>
									</select>
									<c:set var="today" value="<%=monthAgo%>" />
									<c:set var="datetime">
										<fmt:formatDate value="${today}" pattern="MM" />
									</c:set>
									<select id="sMon" name="sMon">
										<c:forEach var="i" begin="01" end="12">
											<c:choose>
												<c:when test="${i == datetime}">
													<option value="${i}" selected>${i}월</option>
												</c:when>
												<c:otherwise>
													<option value="${i}">${i}월</option>
												</c:otherwise>
											</c:choose>
										</c:forEach>
									</select>
									
									 기준종료 :  
									<input type="hidden" id="EDATE" name="EDATE"
										maxlength="15" class="input_base" readonly="readonly"
										alt="시작일" title="" value=""
										style="width: 70px; align: center;" />
									<c:set var="today" value="<%=monthAgo%>" />
									<c:set var="datetime">
										<fmt:formatDate value="${today}" pattern="yyyy" />
									</c:set>
									<select id="eYear" name="eYear">
										<c:forEach var="i" begin="0" end="10">
											<c:choose>
												<c:when test="${i == 0}">
													<option value="${datetime}" selected>${datetime}년</option>
												</c:when>
												<c:otherwise>
													<option value="${datetime-i}">${datetime-i}년</option>
												</c:otherwise>
											</c:choose>
										</c:forEach>
									</select>
									
									<c:set var="today" value="<%=monthAgo%>" />
									<c:set var="datetime">
										<fmt:formatDate value="${today}" pattern="MM" />
									</c:set>
									<select id="eMon" name="eMon">
										<c:forEach var="i" begin="01" end="12">
											<c:choose>
												<c:when test="${i == datetime}">
													<option value="${i}" selected>${i}월</option>
												</c:when>
												<c:otherwise>
													<option value="${i}">${i}월</option>
												</c:otherwise>
											</c:choose>
										</c:forEach>
									</select>
									
									<input type="checkbox" id="dchker" />
									<div id="xdateDiv">
										제외월 시작 :  
										<input type="hidden" id="XSDATE" name="XSDATE"
											maxlength="15" class="input_base" readonly="readonly"
											alt="시작일" title="" value=""
											style="width: 70px; align: center;" />
										<c:set var="today" value="<%=monthAgo%>" />
										<c:set var="datetime">
											<fmt:formatDate value="${today}" pattern="yyyy" />
										</c:set>
										<%-- <select id="xsYear" name="xsYear">
											<c:forEach var="i" begin="0" end="10">
												<c:choose>
													<c:when test="${i == 0}">
														<option value="${datetime}" selected>${datetime}년</option>
													</c:when>
													<c:otherwise>
														<option value="${datetime-i}">${datetime-i}년</option>
													</c:otherwise>
												</c:choose>
											</c:forEach>
										</select> --%>
										<input type="text" id="xsYear" name="xsYear" readonly style="width:110px; padding-left: 10px;">
										<c:set var="today" value="<%=monthAgo%>" />
										<c:set var="datetime">
											<fmt:formatDate value="${today}" pattern="MM" />
										</c:set>
<%-- 										<select id="xsMon" name="xsMon">
											<c:forEach var="i" begin="01" end="12">
												<c:choose>
													<c:when test="${i == datetime}">
														<option value="${i}" selected>${i}월</option>
													</c:when>
													<c:otherwise>
														<option value="${i}">${i}월</option>
													</c:otherwise>
												</c:choose>
											</c:forEach>
										</select> --%>
										
										 제외월 종료 :  
										<input type="hidden" id="XEDATE" name="XEDATE"
											maxlength="15" class="input_base" readonly="readonly"
											alt="시작일" title="" value=""
											style="width: 70px; align: center;" />
										<%-- <c:set var="today" value="<%=monthAgo%>" />
										<c:set var="datetime">
											<fmt:formatDate value="${today}" pattern="yyyy" />
										</c:set>
										<select id="xeYear" name="xeYear">
											<c:forEach var="i" begin="0" end="10">
												<c:choose>
													<c:when test="${i == 0}">
														<option value="${datetime}" selected>${datetime}년</option>
													</c:when>
													<c:otherwise>
														<option value="${datetime-i}">${datetime-i}년</option>
													</c:otherwise>
												</c:choose>
											</c:forEach>
										</select> --%>
										<input type="text" id="xeYear" name="xeYear" readonly style="width:110px; padding-left: 10px;">
										<c:set var="today" value="<%=monthAgo%>" />
										<c:set var="datetime">
											<fmt:formatDate value="${today}" pattern="MM" />
										</c:set>
										<%-- <select id="xeMon" name="xeMon">
											<c:forEach var="i" begin="01" end="12">
												<c:choose>
													<c:when test="${i == datetime}">
														<option value="${i}" selected>${i}월</option>
													</c:when>
													<c:otherwise>
														<option value="${i}">${i}월</option>
													</c:otherwise>
												</c:choose>
											</c:forEach>
										</select> --%>
										
									</div>
									
								</div>
							</fieldset>
						</div>
						<div class="wrap_bottom"></div>
					</div>
				</div>
				<!-- list -->
				<div id="listDiv"></div>
			</div>
		</div>
		<input type="hidden" name="AW_NAME" id="AW_NAME" />
	</form>
</div>