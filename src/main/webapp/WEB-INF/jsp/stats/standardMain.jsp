<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.*"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%
//한달 전
Calendar mon = Calendar.getInstance();
mon.add(Calendar.MONTH , -1);
System.out.println("%%%%%%%%%%%%%%%% : "+mon);
String beforeMonth = new java.text.SimpleDateFormat("YYYYMMdd").format(mon.getTime());
System.out.println("%%%%%%%%%%%%%%%% : "+beforeMonth);

//2
Date monthAgo=new Date(new Date().getTime() - 60*60*24*1000*30L);
System.out.println("%%%%%%%%%%%%%%%% : "+monthAgo);
%>

<script>
	$(document).ready(function(){
		console.log("표준화통계 진입");
		var today = new Date();
		var year = today.getFullYear();
		var month = ('0' + (today.getMonth() + 1)).slice(-2);
		var day = ('0' + today.getDate()).slice(-2);
		
		var oneYearAgo = new Date(today.setFullYear(today.getFullYear() - 1));
		var oyear = oneYearAgo.getFullYear();
		var omonth = ('0' + (oneYearAgo.getMonth() + 1)).slice(-2);
		var oday = ('0' + oneYearAgo.getDate()).slice(-2);

		var dateString = year+month+day;
		var odateString = oyear+omonth+oday;
		
		$("#orgStartDate").val(odateString);
		$('#orgEndDate').val(dateString);
		
		
		// 오늘 날짜를 가져오기
	    var today = new Date();
	    // 포맷에 맞게 오늘 날짜를 문자열로 변환 (예: 'YYYY-MM-DD HH:mm')
	    var formattedDate = today.toISOString().slice(0, 19).replace('T', ' ');
	    
		$.datetimepicker.setLocale('ko');
	    $('#StartDateTime').datetimepicker({
	        i18n: {
	            ko: {
	                months: [
	                    '1월', '2월', '3월', '4월', '5월', '6월',
	                    '7월', '8월', '9월', '10월', '11월', '12월'
	                ],
	                dayOfWeek: [
	                    "일", "월", "화", "수", "목", "금", "토"
	                ]
	            }
	        },
	        value: formattedDate,
	        onChangeDateTime: function(currentDateTime) {
	        	
	        	// 값 어떻게 넘어오는지 확인하려고 만든 로그
	            console.log(".val() :" + $('#StartDateTime').val());
	            console.log("선택 값 : " + currentDateTime);
	        }
	    });
	    
	 	// 한 달 뒤 날짜 계산
	    var nextMonth = new Date(today.setMonth(today.getMonth() + 1));
	    // 포맷에 맞게 한 달 뒤 날짜를 문자열로 변환 (예: 'YYYY-MM-DD HH:mm')
	    var formattedDateN = nextMonth.toISOString().slice(0, 19).replace('T', ' ');
	    
	    $('#EndDateTime').datetimepicker({
	        i18n: {
	            ko: {
	                months: [
	                    '1월', '2월', '3월', '4월', '5월', '6월',
	                    '7월', '8월', '9월', '10월', '11월', '12월'
	                ],
	                dayOfWeek: [
	                    "일", "월", "화", "수", "목", "금", "토"
	                ]
	            }
	        },
	        value: formattedDateN,
	        onChangeDateTime: function(currentDateTime) {
	        	
	        	// 값 어떻게 넘어오는지 확인하려고 만든 로그
	            console.log(".val() :" + $('#EndDateTime').val());
	            console.log("선택 값 : " + currentDateTime);
	        }
	    });
	});
	
	function goStats(url){
		
		/* 향후제거필요 임시 */
		if(url=='stats/orgOrgSub.do'){
			url+='?orgStartDate='+$("#orgStartDate").val()+'&orgEndDate='+$('#orgEndDate').val();
		}
		
		
		var stDt=$("#sDate").val();
		$("#org_id").val($("#orgIdSel").val());
		//var eDt=$("#eDate").val();
		if($("#sDate").val().length==1){
			stDt="0"+stDt;
		} 
		/* if($("#eDate").val().length==1){
			eDt="0"+eDt;
		} */
		
		
		
		console.log("통계 서브밋?");
		$('#start_date').val($('#sYear').val()+stDt)
		
		var startDate = $('#StartDateTime').val();
		var endDate = $('#EndDateTime').val();
		
		
		if(startDate == "" || endDate == "") {
			alert("시작일과 종료일을 올바르게 선택해주세요.");
			return false;
		} else {
			// 시작일과 종료일 값에서 슬래시(/) 빼주는 함수
			formatDate(startDate,endDate);
			
			$('#city').val("("+$("#areaOptSel option:selected").text().substr(0,2)+")");
			rkFlag = true;
			frmExcel.action = '<c:url value="/"/>'+url;
			frmExcel.submit();
			rkFlag = true;
		}
		
	}
	
	function formatDate(sDate,eDate) {
		console.log("포맷 데이트 실행");
		
		sDate = sDate.replace(/\//g, ""); // 슬래시 제거
		sDate = sDate.substr(0,8); // 시간 제거
		
		eDate = eDate.replace(/\//g, ""); // 슬래시 제거
		eDate = eDate.substr(0,8); // 시간 제거
		
		// 값 넣는 작업
		$('#start_date2').val(sDate);
		$('#end_date').val(eDate);
		
		console.log("최종 시작일 값 :" + $('#start_date2').val());
		console.log("최종 종료일 값 :" + $('#end_date').val());
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
								기준년
								<!-- 
								fmt:parseDate : String 형을 받아서 원하는 포맷으로 Date 형태로 변경 
								fmt:formatDate : Date 형을 받아서 원하는 포맷으로 날짜형태를 변경 
								-->
								<input type="hidden" id="start_date" name="start_date" maxlength="15"  class="input_base" readonly="readonly"  alt="시작일" title="" value="" style="width:70px;align:center;"/>
								<input type="hidden" id="start_date2" name="start_date2" maxlength="15" readonly>
								<input type="hidden" id="end_date" name="end_date" maxlength="15" class="input_base" readonly alt="종료일" value="" title="" style="width:70px; align:center;">
								<input type="hidden" id="city" name="city" maxlength="15"  class="input_base" readonly="readonly"  alt="" title="" value="" style="width:70px;align:center;"/>
								<input type="hidden" id="org_id" name="org_id" maxlength="15"  class="input_base" readonly="readonly"  alt="" title="" value="" style="width:70px;align:center;"/>
						        <%-- <c:set var="today" value="<%=beforeMonth%>" />
                                <c:set var="datetime"><fmt:parseDate value="${today}" pattern="YYYY" /></c:set>
                                 --%>
                                <c:set var="today" value="<%=monthAgo%>" />
                                <c:set var="datetime"><fmt:formatDate value="${today}" pattern="yyyy" /></c:set>
                                <select id="sYear" name="sYear">
		                            <c:forEach var="i" begin="0" end="10">
			                            <c:choose>
											<c:when test="${i == 0}">
				                                <option value="${datetime}" selected>${datetime}년</option>
				                            </c:when>
				                            <c:otherwise>
				                                <option value="${datetime-i}" >${datetime-i}년</option>
				                            </c:otherwise>		                            
			                            </c:choose>
		                            </c:forEach>
								</select>
								기준월
						        <%-- <c:set var="today" value="<%=beforeMonth%>" />
                                <c:set var="datetime"><fmt:parseDate value="${today}" pattern="MM" /></c:set> 
                                --%>
                                <c:set var="today" value="<%=monthAgo%>" />
                                <c:set var="datetime"><fmt:formatDate value="${today}" pattern="MM" /></c:set>
                                <select id="sDate" name="sDate">
		                            <c:forEach var="i" begin="01" end="12">
			                            <c:choose>
											<c:when test="${i == datetime}">
				                                <option value="${i}" selected>${i}월</option>
				                            </c:when>
				                            <c:otherwise>
				                                <option value="${i}" >${i}월</option>
				                            </c:otherwise>		                            
			                            </c:choose>
		                            </c:forEach>
								</select>
								시작일 :
								<input type="text" id="StartDateTime" autocomplete="off">
								종료일 :
								<input type="text" id="EndDateTime" autocomplete="off">
						        <%-- ~ 
								종료월
						        <input type="hidden" id="end_date" name="end_date" maxlength="15"  class="input_base"  readonly="readonly" alt="종료일" title="" value="" style="width:70px;align:center;"/>
						        <select id="eDate" name="eDate">
		                            <c:forEach var="i" begin="01" end="12">
			                            <c:choose>
											<c:when test="${i == datetime}">
				                                <option value="${i}" selected>${i}월</option>
				                            </c:when>
				                            <c:otherwise>
				                                <option value="${i}" >${i}월</option>
				                            </c:otherwise>		                            
			                            </c:choose>
		                            </c:forEach>
								</select> --%>
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

