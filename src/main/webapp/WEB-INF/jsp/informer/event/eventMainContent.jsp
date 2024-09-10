<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script>
$(document).ready(function(){
   //console.log("eventMainContent.jsp");
})
</script>


<div id="contentWrap">
<!-- <div id="posi"><a href="/main.do"><img src="../images/ico_home.gif" alt="home" /></a>제보자관리 > 행사관리</div> -->
<div id="searchDiv">
    <div id="contents">
        <h1 class='content-title'>행사관리</h1>
    </div>
     
    <div class="board_list" style="margin-top: 10px;">
        <div class="rounding_wrap">
            <div class="wrap_top"></div>
                <div class="wrap_center">
                    <div id="searchBox" style="float: right;margin-right:15px ">
                    		<form id="searchFrm" name="searchFrm">
					            <table>
					                <tr>
					                	<td>
											<span>방송국선택 : </span>
										</td>
					                	<td style="width: 110px;">
											<select id="areaOptSel" name="REGION_ID">
					                            <c:forEach var="informerRegion" items="${informerRegionList}" varStatus="idx">
					                                <option value="${informerRegion.areaCode}" ><c:out value="${informerRegion.areaName}"/></option>
					                            </c:forEach>
											</select>
										</td>
					                    <td>
					                        <select class="table_sel1" id="searchType" name="searchType">
					                            <option value="EVENT_NAME" ><c:out value="이벤트명"/></option>
					                            <option value="CONTENTS" ><c:out value="내용"/></option>
					                        </select>
					                    </td>
					                    <td>
					                        <input class="input_base" type="text" id="searchValue" name="searchValue" maxlength="10" />
					                    </td>
					                    <td>
					                        <img src="../images/btn_search.gif" onclick="search();" style="cursor:pointer"  />
					                    </td>
					                </tr>
					            </table>
					        </form>	
                    </div>
                </div>
            <div class="wrap_bottom"></div>
        </div>
    </div>	
    			    
</div>
<div id="listDiv">
</div>
<div id="pageDiv">
    <div id="pagingBox">
        <!-- paging Box content -->
    </div>
    <div class="btnBox" style="float: right;margin-right: 15px;margin-top: 50px;">
    	<span onclick="editEvent();" class="editEvent">
    		<img src="../images/btn_save1.png" alt="" style="cursor: pointer;width: 92px;height: 30px;background-size: cover; ">
    	</span>
    </div>
</div>
</div>
<script>
$(document).ready(function(){
	console.log("행사관리 메인 : eventMainContent.jsp");
    init();
    $("#areaOptSel").on("change",function(){
		selArea=$("#areaOptSel option:selected").val();
		$('#listDiv').load("/informer/event/eventList.do?REGION_ID="+selArea);
	});
     $('.input_base').keydown(function(e) {
		if(e.keyCode==13){
			//기존 엔터버튼 누를 시 화면누락 방지 및 추가 및 수정 버튼클릭과 동일한 효과를 줌(트리거)
			e.preventDefault();
			search();			
		}
	});
})

function init(){
    $('#listDiv').load("/informer/event/eventList.do");
    //$('#pagingBox').load('<c:url value="/informer/event/eventListPagenation.do"/>');
}

/**
 * 검색
 */
function search(){
	
    var options = {
            url:"/informer/event/eventList.do",
            type:"post",
            target: '#listDiv',
            success: function(json){
                if(json==0){
                	//$('#listDiv').load("/common/nodata.do");             	
                }
            },
            error: function(res,error){
                alert("에러가 발생했습니다."+error);
            }
    };
    $('#searchFrm').ajaxSubmit(options);
}

/**
 * 페이징
 */
/* 
 function pagination(){
    var options = {
            url: '<c:url value="/informer/event/eventListPagenation.do"/>',
            target:"#pagingBox",
            type:"post"
    };
    $('#searchFrm').ajaxSubmit(options);
} */

/**
 * 이벤트 등록 팝업
 */
function editEvent(){
    var url = "/informer/event/editEvent.do";
    var windowName = "행사등록";
    var popupW = 450;  // 팝업 넓이
    var popupH = 450;  // 팝업 높이
    var left = Math.ceil((window.screen.width - popupW)/2);
    var top = Math.ceil((window.screen.height - popupH)/2);
    popObj = window.open(url, windowName, 'width=' + popupW +  ', height=' + popupH +  ', left=' + left +  ', top=' + top + ', toolbar=no, location=no, status=no, menubar=no, scrollbars=no, resizable=no');
}
</script>