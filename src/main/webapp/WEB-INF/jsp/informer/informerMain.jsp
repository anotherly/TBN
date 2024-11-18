<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<script type="text/javascript" charset="utf-8" src="<%=request.getContextPath()%>/js/jquery.form.js"></script>

<!-- DateTimePicker -->
<!-- <script src="<=request.getContextPath()%>/calender/moment.js"></script>
<script src="<=request.getContextPath()%>/calender/mo_ko.js"></script>
<script src="<=request.getContextPath()%>/calender/bootstrap-datetimepicker.js"></script>
<link rel="stylesheet" type="text/css" href="<=request.getContextPath()%>/calender/no-boot-calendar-custom.css" />
<link rel="stylesheet" type="text/css" href="<=request.getContextPath()%>/calender/datetimepickerstyle.css" />
 -->
<div id="contentWrap"  style="width:1030px;">
<!-- <div id="posi"><a href="/main.do"><img src="../images/ico_home.gif" alt="home" /></a>제보자관리 > 제보자관리</div> -->
<div id="searchDiv">
    <div id="contents">
        <h1 class='content-title'>통신원관리</h1>
    </div>
    <div class="board_list" style="margin-top: 10px; margin-bottom:100px;">
        <div class="rounding_wrap" style="width: 1030px;background: #f1f1f1;border-radius: 30px;border: 1px solid #a8b4c4;height: 50px;display: flex;]align-items: center;justify-content: center;">
                <div class="wrap_center" style="    width: 1030px;background: none;border: none;display: flex;justify-content: center;align-items: center;">
				    <div id="searchBox" style="float: right;margin-right:15px ">
				        <form id="searchFrm" name="searchFrm">
				            <table class="tbSch">
					            <tr>
				 					<td style="width:70px;">
				 					<input type="checkbox" id="dchker" /> 
				 					등록일 : 
				 					</td>
				 					<td style="width:205px;">
				 						<input type="text" id="sDate" class="dateText" name="sDate" style="text-align:center" maxlength="8" size=10 onkeydown='return onlyNumber(event)' onkeyup='removeChar(event)' onclick="dateFunc">
				 						 ~ 
				 						<input type="text" id="eDate" class="dateText" name="eDate" style="text-align:center" maxlength="8" size=10 onkeydown='return onlyNumber(event)' onkeyup='removeChar(event)' onclick="dateFunc">
				 					</td>
				                    <td>
                                        <select class="table_sel"  style="width:100px;" id="areaCodeSel" name="areaCode">
                                        	 <c:choose>
                                             		<c:when test="${fn:length(informerAreaList)==1}">
                                             	
	                                             	</c:when>
	                                             	<c:otherwise>
														<option value="" selected><c:out value="소속"/></option>	
	                                             	</c:otherwise>
	                                         </c:choose>
                                             <c:forEach var="informerArea" items="${informerAreaList}">
                                                 <option value="${informerArea.areaCode}" 
                                                  <c:if test="${informerArea.areaCode eq informerInfo.areaCode}">selected</c:if>>
														${informerArea.areaName}
                                                 </option>
                                             </c:forEach>
                                         </select>
				                    </td>
                                   	<td id="tdItype">
                                     	<select class="table_sel" style="width:120px;" id="informerTypeSel" name="informerType">
                                     		 <option value="" selected><c:out value="유형"/></option>
                                             <c:forEach var="informerType" items="${informerTypeList}">
                                                 <option value="${informerType.ifmId1}" <c:if test="${informerType.ifmId1 eq informerInfo.informerType}">selected</c:if>>${informerType.ifmName}</option>
                                             </c:forEach>
                                         </select>
                                     </td>
                                         <td id="tdOrgId">
											<select class="table_sel"  style="width:165px;" id="orgIdSel" name="orgId">
												<option value="" selected><c:out value="소속기관"/></option>
                                             </select>
                                         </td>
				                    <td>
				                        <select class="table_sel" id="searchActYn" name="flagAct">
				                            <option value="" ><c:out value="활동여부"/></option>
				                            <option value="Y" ><c:out value="위촉"/></option>
				                            <option value="N" ><c:out value="해촉"/></option>
				                        </select>
				                    </td>
				                    <td>
				                        <select class="table_sel" id="searchType" name="searchType">
				                            <option value="actId" ><c:out value="제보자ID"/></option>
				                            <option value="informerName" ><c:out value="이름"/></option>
				                            <option value="phoneCell" ><c:out value="전화번호"/></option>
				                        </select>
				                    </td>
				                    <td>
				                        <input class="input_base" type="text" id="searchValue" name="searchValue"  onkeyup="if(event.keyCode == 13)search();"/>
				                    </td>
				                    <td>
				                        <img src="../images/btn_search.gif" onclick="search();" style="cursor:pointer"  />
				                    </td>
				                </tr>
				            </table>
				        </form>
				    </div>
		        </div>
	    </div>
    </div>
    
    <!-- 검색조건 영역 끝 -->
    <div style="display:flex;align-items: center;justify-content: space-between;">
	<div>
		<p id="resultListTotal" style="width: 300px;">
			<img src="../images/ico_result.gif" />
			검색결과 <span style="font-weight:700;"></span>건
		</p>
	</div>
	<div>
		<a href="javascript:goStats('stats/informerDown.ajax');">
			<img src="../images/btn_excel_down2.gif" alt="엑셀다운로드" style="width: 90px;"/>
		</a>			
	</div>
</div>
</div>
<div id="listDiv">
</div>
<div id="pageDiv" style="margin-top:10px">
    <div id="pagingBox">
        <!-- paging Box content -->
    </div>
    <div class="btnBox" align="right" style="margin-right:15px;margin-top: 50px;">
        <span onclick="editInformer();" class="editUser"><button type="button" style="border: none;"><img src="../images/btn_save1.png" alt="" style="cursor: pointer;width: 92px;height: 30px;background-size: cover; "></button></span>
    </div>
</div>
</div>
<script>
$(document).ready(function(){
	console.log("informerMain.jsp 진입");

	init();
	
	var fromDate = new Date(new Date().setMonth(new Date().getMonth() - 1));
	fromDate = getFormatDate(fromDate);
	var toDate = getFormatDate(new Date());
	
	//var infrmCalender = dateFunc("dateText","dateText1","dateText2",search,fromDate,toDate);
	dateFunc('sDate','eDate',fromDate,toDate);
	
	
	//최초에는 미사용이므로 비활성화
	$(".dateText").attr("disabled", true);
	//$('#dchker').prop('checked',true);
	
	//최초 조회시는 위촉만
	$('#searchFrm').submit( function(event){
		event.preventDefault();
		search();
	});
	
	$('#dchker').on("click", function(){
		if($(this).is(":checked")){
			$(".dateText").attr("disabled", false);
		}else{
			$(".dateText").attr("disabled", true);
		}
	});
	
	//$('#listDiv').load('/informer/informerList.do?sDate='+fromDate+'&eDate='+toDate+''+'&eDate='+flagAct='+'Y');
	$('#listDiv').load('/informer/informerList.do;');
	
	//소속방송국,통신원유형 클릭 시 -> id랑 소속기관 소속기관 세부 갱신
    $("#areaCodeSel , #informerTypeSel").on("change",function(){
    	$("#orgIdSel option").remove();
    	$("#orgSubIdSel option").remove();
    	if($("#informerTypeSel").val()==""){
    		$("#orgIdSel").append("<option value=''>소속기관</option>");
    	}else{
    		var ajaxData = ajaxMethod
        	("/informer/reSel.do",
    			{
    				areaCode:$("#areaCodeSel option:selected").val()
    				,informerType:$("#informerTypeSel option:selected").val()
    				,sflag:$(this).attr("id")	
    			}
    		);
        	
        	//소속방송국 경우 ->actid 변경
        	if(typeof ajaxData.newActId !="undefined"){
        		$("#actId").val(ajaxData.newActId);
        	}
        	//지역코드값과 통신원유형으로 2,3을 불러옴
        	
        	var alData1 = ajaxData.sList1;
        	var alData2 = ajaxData.sList2;
        	
        	//셀렉트박스 갱신 하위영역들 2,3
        	if (alData1.length==0) {
        		console.log("alData1.length==0");
        		$("#orgIdSel").append("<option value=''>무소속</option>");
    		}else{
    			console.log("alData1.length!=0");
    			$("#orgIdSel").append("<option value=''>소속기관</option>");
    			for (var i = 0; i < alData1.length; i++) {
    	    		$("#orgIdSel").append("<option value='"+alData1[i].ifmId2+"'>"+alData1[i].ifmName+"</option>");	
    			}	
    		}
    		if (alData2.length==0) {
    			console.log("alData2.length==0");
    			$("#orgSubIdSel").append("<option value=''>무소속</option>");
    		}else{
    			console.log("alData2.length!=0");
    			for (var i = 0; i < alData2.length; i++) {
    	    		$("#orgSubIdSel").append("<option value='"+alData2[i].ifmId3+"'>"+alData2[i].ifmName+"</option>");
    			}	
    			$("#orgSubIdSel").append("<option value=''>무소속</option>");
    		}
    	}
    	
		
    });
	
});

/**
 * 통신원 수정 팝업
 */
function editInformer(str){
    var param = "";
    if(str != null && str != "" && str != "undefined"){
        param = "?pr1=" + str;
    }
    var url = "/informer/editInformer.do" + param;
    var windowName = "통신원등록";
    var popupW = 800;  // 팝업 넓이
    var popupH = 720;  // 팝업 높이
    var left = Math.ceil((window.screen.width - popupW)/2);
    var top = Math.ceil((window.screen.height - popupH)/2);
    popObj = window.open(url, windowName, 'width=' + popupW +  ', height=' + popupH +  ', left=' + left +  ', top=' + top + ', toolbar=no, location=no, status=no, menubar=no, scrollbars=no, resizable=no');
}

/**
 * 통신원 이력 팝업
 */
function showInformerHistory(str){
	var param = "";
	if(str != null && str != "" && str != "undefined"){
        param = "?informerId=" + str;
    }
    var url = "/informer/showInformerHistory.do" + param;
    var windowName = "통신원이력";
    var popupW = 800;  // 팝업 넓이
    var popupH = 700;  // 팝업 높이
    var left = Math.ceil((window.screen.width - popupW)/2);
    var top = Math.ceil((window.screen.height - popupH)/2);
    popObj = window.open(url, windowName, 'width=' + popupW +  ', height=' + popupH +  ', left=' + left +  ', top=' + top + ', toolbar=no, location=no, status=no, menubar=no, scrollbars=no, resizable=no');
}

/**
 * 검색
 */
function search(){
    
	console.log("function search");
	let queryString = $("#searchFrm").serialize();
	console.log(queryString);
	var options = {
        url:"/informer/informerList.do",
        type: "POST",
		//dataType: "json",
		async : false,
        data: queryString,
        target: '#listDiv',
        success: function(json){
        	console.log("성공?");
        	$('#listDiv').html(json);
        },
        error: function(res,error){
            alert("에러가 발생했습니다."+res);
        }
    };
    $.ajax(options);
}

/**
 * 페이징
 */
function pagination(){
   /*  var options = {
            url: "/informer/informerListPagenation.do",
            target:"#pagingBox",
            type:"post"
    };
    $('#searchFrm').ajaxSubmit(options); */
}
function goStats(url){
	console.log("통계 서브밋?");
	rkFlag = true;
	searchFrm.action = '<c:url value="/"/>'+url;
	searchFrm.submit();
	rkFlag = true;
}
</script>