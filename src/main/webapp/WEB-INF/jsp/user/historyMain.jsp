<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="contentWrap">
<!-- <div id="posi"><a href="/main.do"><img src="../images/ico_home.gif" alt="home" /></a>사용자관리 > 사용자관리</div> -->
<div id="searchDiv">
    <div id="contents">
        <h1 class='content-title'>사용이력관리</h1>
    </div>
    <div class="board_list" style="margin-top: 10px; height: 50px;">
    	<!-- 서브메뉴 탭영역 시작 -->
		<%-- <div class="gnb_tab" id ="htTab">
			<ul class="lst_tab" >
				<li class="on" id="tab1"><span id="tab1" style="cursor: pointer;"><c:out value="로그인 이력조회" /></span></li>
				<li class="ns"></li>
				<li id="tab2"><span id="tab2" style="cursor: pointer;"><c:out value="정보변경 이력조회" /></span></li>
			</ul>
		</div> --%>
        <div class="rounding_wrap">
            <div class="wrap_top"></div>
                <div class="wrap_center">
                    <div id="searchBox" style="float: right;margin-right:15px ">
				    </div>
		        </div>
		    <div class="wrap_bottom"></div>
	    </div>
    </div>
</div>
<div id="listDiv">
</div>
<div id="pageDiv" style="margin-top:50px">
    <div id="pagingBox">
        <!-- paging Box content -->
    </div>
</div>
</div>
<script>
var hisUrl='/user/loginHistoryList.do';
$(document).ready(function(){
	
	console.log("historyMain.jsp 진입");
	
    $('#searchBox').load("/user/loginHistorySch.do");
    $('#listDiv').load("/user/loginHistoryList.do");
	
    $("#htTab ul li").on("click",function(){
    	console.log("히스토리 탭메뉴 변경");
    	$('#listDiv').empty();
    	if ($(this).attr("id") == 'tab1'){
    		$("#tab1").addClass("on");
    		$("#tab2").removeClass("on");
    		hisUrl='/user/loginHistoryList.do';
    		$('#searchBox').load('/user/loginHistorySch.do');
    	}else{
    		$("#tab1").removeClass("on");
    		$("#tab2").addClass("on");
    		hisUrl="/user/useHistoryList.do";
    		$('#searchBox').load('/user/useHistorySch.do');
    	}
    	goMenuSite(hisUrl,"listDiv");
    });
});


</script>