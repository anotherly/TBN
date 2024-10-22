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

<script>
	$(document).ready(function(){
		console.log("표준화통계 진입");
		//데이트타임피커
		//시작 ,종료일 설정
		var stdate=moment().subtract(1, 'months').startOf('month').format('YYYY-MM-DD');
		var endate=moment().subtract(1, 'months').endOf('month').format('YYYY-MM-DD');
		//common.js에 생성한 함수 참조(달력생성)
		dateFunc('start_date','end_date',stdate,endate);
	});
	
	function goStats(url){
		/* 향후제거필요 임시 */
		if(url=='stats/orgOrgSub.do'){
			url+='?orgStartDate='+$("#orgStartDate").val()+'&orgEndDate='+$('#orgEndDate').val();
		}
		$("#org_id").val($("#orgIdSel").val());
		console.log("통계 서브밋?");
		var startDate = $('#start_date').val();
		var endDate = $('#end_date').val();
		if(startDate == "" || endDate == "") {
			alert("시작일과 종료일을 올바르게 선택해주세요.");
			return false;
		} else if($('#end_date').val()<$('#start_date').val()){
			alert("종료일은 시작일 이전일 수 없습니다.");
			return false;
		} else {
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
                    <!-- board_list -->
                    <div class="board_list">
                        <!-- 검색조건 영역 시작 -->
                        <form id="frmExcel" name="frmExcel" method="post">
                       <!--  <input type="hidden" id="start_date" name="start_date">
                        <input type="hidden" id="end_date" name="end_date"> -->
                        <div class="rounding_wrap mgt10">
                            <div class="wrap_top"></div>
                            <div class="wrap_center">
                                <fieldset class="searchField">
	                                <legend>금일접수현황 검색조건</legend>
									방송국선택 : 
							       <select id="areaOptSel" name="REGION_ID">
			                            <c:forEach var="informerRegion" items="${informerRegionList}" varStatus="idx">
			                                <option value="${informerRegion.areaCode}" ><c:out value="${informerRegion.areaName}"/></option>
			                            </c:forEach>
									</select>
									<input type="hidden" id="city" name="city" maxlength="15"  class="input_base" readonly="readonly"  alt="" title="" value="" style="width:70px;align:center;"/>
									<input type="hidden" id="org_id" name="org_id" maxlength="15"  class="input_base" readonly="readonly"  alt="" title="" value="" style="width:70px;align:center;"/>
									<div class="form_daterange" style="display: inline-flex;align-items: center;gap: 5px;" id="schDtBody">
										시작일 : 
										<div class='input-group date' id='datetimepicker1'>
											<input type='text' class="form-control dt_search" name="start_date" id="start_date" required/>
										</div>
										 종료일 :  
										<div class='input-group date' id='datetimepicker2'>
											<input type="text" class="form-control dt_search" id="end_date" name="end_date" required/>
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
                                <tbody>
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
                                        <td class="txt_left"><img src="../images/ico_excel.gif" alt="" class="mglsub03" /><a href="javascript:goStats('stats/muInformer.do');">월별 제보자별 제보건수</a></td>
                                        <td><a href="javascript:goStats('stats/muInformer.do');"><img src="../images/btn_excel_down.gif" alt="엑셀다운로드" /></a></td>
                                    </tr>
                                    <tr>
                                        <td class="txt_left"><img src="../images/ico_excel.gif" alt="" class="mglsub03" /><a href="javascript:goStats('stats/muInformer2.do');">무 제보자 현황</a></td>
                                        <td><a href="javascript:goStats('stats/muInformer2.do');"><img src="../images/btn_excel_down.gif" alt="엑셀다운로드" /></a></td>
                                    </tr>
                                    <tr>
                                        <td class="txt_left"><img src="../images/ico_excel.gif" alt="" class="mglsub03" /><a href="javascript:goStats('stats/receiptInformer.do');">교통통신원 제보건수 및 접수직원 가공건수</a></td>
                                        <td><a href="javascript:goStats('stats/receiptInformer.do');"><img src="../images/btn_excel_down.gif" alt="엑셀다운로드" /></a></td>
                                    </tr>
                                    <tr>
                                        <td class="txt_left"><img src="../images/ico_excel.gif" alt="" class="mglsub03" /><a href="javascript:goStats('stats/receiptUse.do');">교통정보 수집건수 및 활용실적</a></td>
                                        <td><a href="javascript:goStats('stats/receiptUse.do');"><img src="../images/btn_excel_down.gif" alt="엑셀다운로드" /></a></td>
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
                                    <!-- 
										2024 08 28 장동현 담당 요청사항
                                     -->
                                    <tr>
                                        <td class="txt_left">
                                        	<img src="../images/ico_excel.gif" alt="" class="mglsub03" /><a href="javascript:goStats('stats/orgOrgSub.do');">통신원 중/소 분류별 통계</a>
											<!-- <input type="text" maxlength='8' id="orgStartDate" name="orgStartDate" style="width:100px;"/> 부터 
                                        	<input type="text" maxlength='8' id="orgEndDate" name="orgEndDate"  style="width:100px;"/> 까지  -->
                                        </td>
                                        <td><a href="javascript:goStats('stats/orgOrgSub.do');"><img src="../images/btn_excel_down.gif" alt="엑셀다운로드" /></a></td>
                                    </tr>
                                    
                                    <tr>
                                        <td class="txt_left"><img src="../images/ico_excel.gif" alt="" class="mglsub03" /><a href="javascript:goStats('stats/volunteer.do');">사회봉사자 일자별 통계</a></td>
                                        <td><a href="javascript:goStats('stats/volunteer.do');"><img src="../images/btn_excel_down.gif" alt="엑셀다운로드" /></a></td>
                                    </tr>
                                </tbody>
                            </table>
                        </div>
                        <!-- 검색결과 끝-->
                        
                    </div>
                </div>
                <!-- //contents -->
    </div>

