<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.*"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!-- DateTimePicker -->
<script src="<%=request.getContextPath()%>/calender/moment.js"></script>
<script src="<%=request.getContextPath()%>/calender/mo_ko.js"></script>
<script src="<%=request.getContextPath()%>/calender/bootstrap-datetimepicker.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/calender/no-boot-calendar-custom.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/calender/datetimepickerstyle.css" />
<style>
	/* 24-10-29 : 통계에서 기간별/월별 버튼 관련 스타일 추가 */
	.stat_btn { /* 버튼 공통 스타일 */
		width:106px;
		height:38px;
		
		border-radius : 10px;
		font-size : 16px;
		
		background-color: #888;
		border : 1px solid #a5a5a5 !important;
		box-shadow: 0px 4px 5px #a5a5a5;
		
		font-weight : bold;
		color : white;
	}
	
	.stat_now_btn { /* 현재 클릭 된 버튼에 줄 스타일 */
		background-color:#137072;
		border : 1px solid #135252 !important;
	}
	
	.btn_div { /* 버튼의 위치 조정을 위한 컨테이너 박스 */
		margin-top : 25px;
	}
	
</style>
<script>
	$(document).ready(function(){
		console.log("표준화통계 진입");
		//데이트타임피커
		//시작 ,종료일 설정
		var stdate=moment().subtract(1, 'months').startOf('month').format('YYYY-MM-DD');
		var endate=moment().subtract(1, 'months').endOf('month').format('YYYY-MM-DD');
		//common.js에 생성한 함수 참조(달력생성)
		dateFunc('stdt','edt',stdate,endate);
		
		
		
		
		// 24-10-30 : 기간별/ 월별 버튼 클릭 시 이벤트 생성
		
		// 기간별 버튼을 클릭했을 경우
		$('#rangeDate').on('click', function(){
			
			// 클릭한 버튼에 따라 클래스 부여 및 제거
			$('#rangeDate').addClass('stat_now_btn');
			$('#monthDate').removeClass('stat_now_btn');
			
			$('#month_body').hide();
			$('#range_body').show();
		});
		
		
		// 월별 버튼을 클릭했을 경우
		$('#monthDate').on('click', function(){
			
			// 클릭한 버튼에 따라 클래스 부여 및 제거
			$('#monthDate').addClass('stat_now_btn');
			$('#rangeDate').removeClass('stat_now_btn');
			
			$('#range_body').hide();
			$('#month_body').show();
		});
		
	});
	
	function goStats(url){
		$("#org_id").val($("#orgIdSel").val());
		console.log("통계 서브밋?");
		var startDate = $('#stdt').val();
		var endDate = $('#edt').val();
		if(startDate == "" || endDate == "") {
			alert("시작일과 종료일을 올바르게 선택해주세요.");
			return false;
		} else if($('#end_date').val()<$('#start_date').val()){
			alert("종료일은 시작일 이전일 수 없습니다.");
			return false;
		} else {
			$('#start_date').val(startDate.replaceAll("-",""));
			$('#end_date').val(endDate.replaceAll("-",""));
			$('#city').val("("+$("#areaOptSel option:selected").text().substr(0,2)+")");
			rkFlag = true;
			frmExcel.action = '<c:url value="/"/>'+url;
			frmExcel.submit();
			rkFlag = true;
		}
	}
	
</script>
    <div id="contentWrap">
        <!-- <div id="posi"><img src="../images/ico_home.gif" alt="home" />통계관리 > 표준화통계</div> -->
        <!-- contents -->
                <div id="contents">
                    <h1 class='content-title'>방송국별 통계</h1>
                    <div class="btn_div">
                    	<button id="rangeDate" class="stat_btn stat_now_btn">기간별</button>
                    	<button id="monthDate" class="stat_btn">월별</button>
                    </div>
                    
                    <!-- board_list -->
                    <div class="board_list">
                        <!-- 검색조건 영역 시작 -->
                        <form id="frmExcel" name="frmExcel" method="post">
                        <div class="rounding_wrap mgt10">
                            <div class="wrap_top"></div>
                            <div class="wrap_center">
                                <fieldset class="searchField">
	                                <legend>통계관리 검색조건</legend>
									방송국선택 : 
							       <select id="areaOptSel" name="REGION_ID">
			                            <c:forEach var="informerRegion" items="${informerRegionList}" varStatus="idx">
			                                <option value="${informerRegion.areaCode}" ><c:out value="${informerRegion.areaName}"/></option>
			                            </c:forEach>
									</select>
									<input type="hidden" id="start_date" name="start_date">
                        			<input type="hidden" id="end_date" name="end_date"> 
									<input type="hidden" id="city" name="city"/>
									<input type="hidden" id="org_id" name="org_id"/>
									<div class="form_daterange" style="display: inline-flex;align-items: center;gap: 5px;" id="schDtBody">
										시작일 : 
										<div class='input-group date' id='datetimepicker1'>
											<input type='text' class="form-control dt_search" name="stdt" id="stdt" required/>
										</div>
										 종료일 :  
										<div class='input-group date' id='datetimepicker2'>
											<input type="text" class="form-control dt_search" id="edt" name="edt" required/>
										</div>
										
									</div> 
                                </fieldset>
                            </div>
                            <div class="wrap_bottom"></div>
                        </div>
                        </form>
                        <!-- 검색조건 영역 끝 -->
                        <!-- 검색결과 시작-->
                        <div class="admin_result_sc">
                            <table border="0" cellpadding="0" cellspacing="0" class="list01">
                                
                                <colgroup>                                
                                <col width="*" />
                                <col width="200" />
                                </colgroup>
                                <thead>
                                    <tr>
                                        <th scope="col">내용</th>
                                        <th scope="col">다운로드</th>
                                    </tr>
                                </thead>
                                <tbody id="range_body"> <!--  기간별 테이블  -->
                                	<tr>
                                        <td class="txt_left"><img src="../images/ico_excel.gif" alt="" class="mglsub03" /><a href="javascript:goStats('stats/receiptBroad.do');">교통정보 제공대장</a></td>
                                        <td><a href="javascript:goStats('stats/receiptBroad.do');"><img src="../images/btn_excel_down.gif" alt="엑셀다운로드" /></a></td>
                                    </tr>
                                     <tr>
                                        <td class="txt_left"><img src="../images/ico_excel.gif" alt="" class="mglsub03" /><a href="javascript:goStats('stats/extrBro.do');">긴급교통정보_방송현황분석</a></td>
                                        <td><a href="javascript:goStats('stats/extrBro.do');"><img src="../images/btn_excel_down.gif" alt="엑셀다운로드" /></a></td>
                                    </tr>
                                    <tr>
                                        <td class="txt_left"><img src="../images/ico_excel.gif" alt="" class="mglsub03" /><a href="javascript:goStats('stats/disastorStat.do');">재난 제보건수</a></td>
                                        <td><a href="javascript:goStats('stats/disastorStat.do');"><img src="../images/btn_excel_down.gif" alt="엑셀다운로드" /></a></td>
                                    </tr>
                                    <tr>
                                        <td class="txt_left"><img src="../images/ico_excel.gif" alt="" class="mglsub03" /><a href="javascript:goStats('stats/muInformer2.do');">무 제보자 현황</a></td>
                                        <td><a href="javascript:goStats('stats/muInformer2.do');"><img src="../images/btn_excel_down.gif" alt="엑셀다운로드" /></a></td>
                                    </tr>
                                    <tr>
                                        <td class="txt_left"><img src="../images/ico_excel.gif" alt="" class="mglsub03" /><a href="javascript:goStats('stats/receiptUse.do');">교통정보 수집건수 및 활용실적</a></td>
                                        <td><a href="javascript:goStats('stats/receiptUse.do');"><img src="../images/btn_excel_down.gif" alt="엑셀다운로드" /></a></td>
                                    </tr>
                                    <!-- 2024 08 28 장동현 담당 요청사항 -->
                                    <tr>
                                        <td class="txt_left">
                                        	<img src="../images/ico_excel.gif" alt="" class="mglsub03" /><a href="javascript:goStats('stats/orgOrgSub.do');">통신원 중/소 분류별 통계</a>
                                        </td>
                                        <td><a href="javascript:goStats('stats/orgOrgSub.do');"><img src="../images/btn_excel_down.gif" alt="엑셀다운로드" /></a></td>
                                    </tr>                                   
                                    <tr>
                                        <td class="txt_left"><img src="../images/ico_excel.gif" alt="" class="mglsub03" /><a href="javascript:goStats('stats/receiptUse.do');">교통정보 수집건수 및 활용실적</a></td>
                                        <td><a href="javascript:goStats('stats/receiptUse.do');"><img src="../images/btn_excel_down.gif" alt="엑셀다운로드" /></a></td>
                                    </tr>
             
             						 <!--  24-11-18 : 제보자별 제보현황 -->
                                    <tr>
                                        <td class="txt_left"><img src="../images/ico_excel.gif" alt="" class="mglsub03" /><a href="javascript:goStats('stats/informerReceipt.do');">제보자별 제보현황</a></td>
                                        <td><a href="javascript:goStats('stats/informerReceipt.do');"><img src="../images/btn_excel_down.gif" alt="엑셀다운로드" /></a></td>
                                    </tr>
                                </tbody>
                                
                                <tbody id="month_body" style="display : none;"> <!--  월별 테이블  -->
                                    <tr>
                                        <td class="txt_left"><img src="../images/ico_excel.gif" alt="" class="mglsub03" /><a href="javascript:goStats('stats/muInformer.do');">월별 제보자별 제보건수</a></td>
                                        <td><a href="javascript:goStats('stats/muInformer.do');"><img src="../images/btn_excel_down.gif" alt="엑셀다운로드" /></a></td>
                                    </tr>

                                    <tr>
                                        <td class="txt_left"><img src="../images/ico_excel.gif" alt="" class="mglsub03" /><a href="javascript:goStats('stats/receiptInformer.do');">교통통신원 제보건수 및 접수직원 가공건수</a></td>
                                        <td><a href="javascript:goStats('stats/receiptInformer.do');"><img src="../images/btn_excel_down.gif" alt="엑셀다운로드" /></a></td>
                                    </tr>
                                    
                                    <tr>
                                        <td class="txt_left"><img src="../images/ico_excel.gif" alt="" class="mglsub03" /><a href="javascript:goStats('stats/incidentStats.do');">돌발 교통정보 표출 실적</a></td>
                                        <td><a href="javascript:goStats('stats/incidentStats.do');"><img src="../images/btn_excel_down.gif" alt="엑셀다운로드" /></a></td>
                                    </tr>
                                    <tr>
                                        <td class="txt_left"><img src="../images/ico_excel.gif" alt="" class="mglsub03" /><a href="javascript:goStats('stats/informerStats.do');">교통정보 수집원 현황</a></td>
                                        <td><a href="javascript:goStats('stats/informerStats.do');"><img src="../images/btn_excel_down.gif" alt="엑셀다운로드" /></a></td>
                                    </tr>
                                    <tr>
                                        <td class="txt_left"><img src="../images/ico_excel.gif" alt="" class="mglsub03" /><a href="javascript:goStats('stats/krGas.do');">한국가스기술공사 제보실적</a></td>
                                        <td><a href="javascript:goStats('stats/krGas.do');"><img src="../images/btn_excel_down.gif" alt="엑셀다운로드" /></a></td>
                                    </tr>
                                    <tr>
                                        <td class="txt_left"><img src="../images/ico_excel.gif" alt="" class="mglsub03" /><a href="javascript:goStats('stats/korLx.do');">한국국토정보공사 제보현황</a></td>
                                        <td><a href="javascript:goStats('stats/korLx.do');"><img src="../images/btn_excel_down.gif" alt="엑셀다운로드" /></a></td>
                                    </tr>
                                    <tr>
                                        <td class="txt_left"><img src="../images/ico_excel.gif" alt="" class="mglsub03" />
                                        	<a href="javascript:goStats('stats/dayReceipt.do');">통신원 소속별 일자별 통계 </a>
											<select class="table_sel"  style="width:165px;" id="orgIdSel" name="orgId">
												 <c:choose>
                                                   	<c:when test="${fn:length(informerOrgList)==0}">
                                                   		
                                                   	</c:when>
                                                   	<c:otherwise>
                                                    		<c:forEach var="informerOrg" items="${informerOrgList}">
                                                    		<option value="${informerOrg.ifmId2}" 
                                                    			<c:if test="${informerOrg.ifmId2 eq informerInfo.orgId}">selected</c:if>
                                                    		>${informerOrg.ifmName}</option>
                                                   		</c:forEach>
                                                   	</c:otherwise>
                                                   </c:choose>
                                                <option value="">무소속</option>
                                            </select>
                                        </td>
                                        <td><a href="javascript:goStats('stats/dayReceipt.do');"><img src="../images/btn_excel_down.gif" alt="엑셀다운로드" /></a></td>
                                    </tr>
                                    <tr>
                                        <td class="txt_left"><img src="../images/ico_excel.gif" alt="" class="mglsub03" /><a href="javascript:goStats('stats/volunteer.do');">사회봉사자 일자별 통계</a></td>
                                        <td><a href="javascript:goStats('stats/volunteer.do');"><img src="../images/btn_excel_down.gif" alt="엑셀다운로드" /></a></td>
                                    </tr>
                                    <tr>
                                        <td class="txt_left"><img src="../images/ico_excel.gif" alt="" class="mglsub03" /><a href="javascript:goStats('stats/volunteer.do');">사회봉사자 일자별 통계</a></td>
                                        <td><a href="javascript:goStats('stats/volunteer.do');"><img src="../images/btn_excel_down.gif" alt="엑셀다운로드" /></a></td>
                                    </tr>
                                    
                                    
                                    <!-- 24-11-18 : 연간 제보자별 제보현황  -->
                                    <tr>
                                        <td class="txt_left"><img src="../images/ico_excel.gif" alt="" class="mglsub03" /><a href="javascript:goStats('stats/yearReceipt.do');">연간 제보자별 제보현황</a></td>
                                        <td><a href="javascript:goStats('stats/yearReciept.do');"><img src="../images/btn_excel_down.gif" alt="엑셀다운로드" /></a></td>
                                    </tr>
                                    
                                    <!--  24-11-18 : 연간지역(소속)별 통계 - 한마음대회 포상 관련 -->
                                    <tr>
                                        <td class="txt_left"><img src="../images/ico_excel.gif" alt="" class="mglsub03" /><a href="javascript:goStats('stats/yearOrgStat.do');">연간 지역(소속)별 실적통계</a></td>
                                        <td><a href="javascript:goStats('stats/yearOrgStat.do');"><img src="../images/btn_excel_down.gif" alt="엑셀다운로드" /></a></td>
                                    </tr>

                                </tbody>
                            </table>
                        </div>
                        <!-- 검색결과 끝-->
                        
                    </div>
                </div>
                <!-- //contents -->
    </div>

