<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/layout.css"/>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/pagination.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/jquery-ui-1.9.0.custom.css" rel="stylesheet"  />
<script  type="text/javascript" charset="utf-8" src="<%=request.getContextPath()%>/js/jquery.js"></script>
	<script  type="text/javascript" charset="utf-8"  src="<%=request.getContextPath()%>/js/common.js"></script>
	<script type="text/javascript" charset="utf-8" src="<%=request.getContextPath()%>/js/jquery.pagination.js"></script>
	<script type="text/javascript" charset="utf-8" src="<%=request.getContextPath()%>/js/jquery.form.js"></script>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/calender/jquery-ui.css"/>
    <script src="<%=request.getContextPath()%>/calender/jquery-ui.js"></script>
<%-- 팝업 스타일이 이상해서 임시 추가, 디자인 변경시 css 추가 해야함. --%>
<style type="text/css"> 
    .list_result_sc_pop td {font-size: 11px;}
    .input_base {font-size: 11px; height: 20px;}
</style>
<div id="contents">
    <h1 class="pabt8 admin_tit"><img src="<c:url value="/images/ico_tab01.gif"/>" alt="행사등록" />참석자 등록</h1>
</div>
<div id="searchDiv" style="margin-top: 10px;">
	<div id="searchBox" style="float: right; width: 100%;">
	    <form id="searchFrm" name="searchFrm">
	    <input type="hidden" id="EVENT_ID" name="EVENT_ID" value="${EVENT_ID}"/>
	        <table>
	            <tr>
		            <td style="width:70px;">
	 					등록일 : 
	 					</td>
					<td style="width:205px;">
						<input type="text" id="dateText1" class="dateText" name="sDate" style="text-align: center;font-size: 13px;width: 78px;height: 25px;" size="10" readonly="readonly" onclick="dateFunc">
						 ~ 
						<input type="text" id="dateText2" class="dateText" name="eDate" style="text-align: center;font-size: 13px;width: 78px;height: 25px;" size="10" readonly="readonly" onclick="dateFunc">
					</td>
	                <td>
	                    <select class="table_sel" id="searchRegion" name="searchRegion">
	                        <option value="" ><c:out value="소속"/></option>
	                        <c:forEach var="informerRegion" items="${informerRegionList }" varStatus="idx">
	                            <option value="${informerRegion.areaCode}" ><c:out value="${informerRegion.areaName}"/></option>
	                        </c:forEach>
	                    </select>
	                </td>
	                <td>
	                    <select class="table_sel" id="searchActYn" name="searchActYn">
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
	                    <input class="input_base" type="text" id="searchValue" name="searchValue"/>
	                </td>
	                <td>
	                    <span onclick="search();"><button type="button">검색</button></span>
	                </td>
	            </tr>
	        </table>
	    </form>
	</div>
	<div class="board_list" style="border-bottom:1px solid #babbbd;">
	    <table summary="통신원목록" border="0" cellpadding="0" cellspacing="0" class="list01">
	        <caption>
	           통신원목록
	        </caption>
	        <colgroup>
	            <col width="5%" />
	            <col width="12%" />
	            <col width="13%" />
	            <col width="25%" />
	            <col width="20%" />
	            <col width="25%" />
	        </colgroup>
	        <thead>
	            <tr>
	                <th scope="col"><input type="checkbox" id="total" name="total" value=""/></th>
	                <th scope="col">ID</th>
	                <th scope="col">활동여부</th>
	                <th scope="col">소속</th>
	                <th scope="col">이름</th>
	                <th scope="col">전화</th>
	            </tr>
	        </thead>
	    </table>
	    <div id="listDiv" class="list_result_sc_pop" style="margin-top: 0px;">
	        <table summary="통신원목록" border="0" cellpadding="0" cellspacing="0" class="list02">
	        <caption>
	           통신원목록
	        </caption>
	        <colgroup>
	            <col width="5%" />
	            <col width="12%" />
	            <col width="13%" />
	            <col width="25%" />
	            <col width="20%" />
	            <col width="25%" />
	        </colgroup>
	        <tbody>
	            <c:if test="${empty informerList}">
	                <tr>
	                    <td colspan='16'><c:out value="참석 대상자를 검색해주세요."/></td>
	                </tr>
	            </c:if>
	        </tbody>
	        </table>
	    </div>
	</div>
	<!-- 버튼 시작-->
	<div class="btnArg mgt10">
	    <input onclick="saveAttendance();" type="image" src="<c:url value="/images/btn_reget1.png"/>" alt="저장" title="저장"  />
	    <input onclick="self.close();" type="image" src="<c:url value="/images/btn_cl.png"/>" alt="취소" title="취소"  />
	</div>
	<!-- 버튼 끝-->
</div>
<script>
$(document).ready(function(){
	console.log("참석자 등록 창");
	var fromDate = new Date(new Date().setMonth(new Date().getMonth() - 1));
	fromDate = getFormatDate(fromDate);
	var toDate = getFormatDate(new Date());
	
	var infrmCalender = dateFunc("dateText","dateText1","dateText2",search,fromDate,toDate);
	search();
})

/**
 * 검색
 */
function search(){
	console.log("검색");
	let queryString = $("#searchFrm").serialize();
    var options = {
            url:'<c:url value="/informer/event/informerList4Event.do"/>',
            data:queryString,
            type:'post',
            target: '#listDiv',
            error: function(res,error){
                alert("에러가 발생했습니다."+error);
            }
    };
    $('#searchFrm').ajaxSubmit(options);
}

/**
 * 참석자 등록
 */
function saveAttendance(){
	
	var checked = '';
    $("input[type=checkbox]").each(function(idx){   // 체크된 참석자를 변수에 저장
        if($(this).is(":checked")){
            if(idx == 0){   // 전체가 선택되면,
                $("input[type=checkbox]").each(function(idx){
                    if(idx != 0){
                        checked += $(this).val() + ",";                             
                    }
                });
                return false;
            }
            checked += $(this).val() + ",";
        }
    });
    var informerIdList = checked.substring(0, checked.length -1);
    
    var options = {
            url:'<c:url value="/informer/event/saveAttendance.do"/>',
            type:'post',
            data: {
            	EVENT_ID:        $('#EVENT_ID').val(),
                informerList:   informerIdList
            },
            dataType: "json",
            success: function(res, html){
                if(res.cnt > 0){
                    alert("등록되었습니다.");
                    opener.chgAttendance($('#EVENT_ID').val());
                    self.close();
                } else {
                    alert("등록에 실패하였습니다.");
                }
            } ,
            error: function(res,error){
                alert("에러가 발생했습니다."+error);
            }
    };
    $.ajax(options);
}

/**
 * 셀렉트박스에서 전체 선택시 다른 체크박스 disable, 선택 해제시 다른체크박스 enabel 
 */
 $('#total').click(function(){
	if($('input[type=checkbox]').length == 1){
		alert("먼저 참석자를 검색 검색해주세요.");
		return false;
	}
	 
    if($('[id="total"]').is(":checked") == true){
        $("input[type=checkbox]").each(function(idx){
            if(idx != 0){
                $(this).attr("checked", true);
            }
        });
    } else {
        $("input[type=checkbox]").each(function(idx){
            if(idx != 0){
                $(this).attr("checked", false);
            }
        });
    } 
});
</script>