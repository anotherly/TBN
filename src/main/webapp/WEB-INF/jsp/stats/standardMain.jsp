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
		
	});
	
	function goStats(url){
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
		$('#start_date').val($('#sYear').val()+stDt);
		$('#city').val("("+$("#areaOptSel option:selected").text().substr(0,2)+")");
		rkFlag = true;
		frmExcel.action = '<c:url value="/"/>'+url;
		frmExcel.submit();
		rkFlag = true;
		
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

