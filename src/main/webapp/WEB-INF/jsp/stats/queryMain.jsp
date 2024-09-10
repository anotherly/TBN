<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<script>
function goStats(url){
	frmExcel.action = '<c:url value="/"/>'+url;
	frmExcel.submit();
}
</script>
    <div id="contentWrap">
        <!-- <div id="posi"><img src="../images/ico_home.gif" alt="home" />통계관리 > 질의통계</div> -->
        <!-- contents -->
        <div id="contents">
           <h1 class="txt_i10"><img src="../images/ad_h1_0505.gif" alt="질의통계" /></h1>
           <!-- board_list -->
           <div class="board_list">
               <!-- 검색조건 영역 시작 -->
               <form id="frmExcel" name="frmExcel" method="post">
               <div class="rounding_wrap mgt10">
                   <div class="wrap_top"></div>
                   <div class="wrap_center">
                       <fieldset>
                       <legend>금일접수현황 검색조건</legend>
                       <textarea id="message" name="message" class="table_sel" cols="50" rows="8" style="width:820px; height:200px;padding:10px;"></textarea>
                       <br><img src="../images/btn_search.gif"  alt="검색"  onclick="javascript:goStats('stats/getDataQuery.do');"/>
                       </fieldset>
                   </div>
                   <div class="wrap_bottom"></div>
               </div>
               </form>
               <!-- 검색조건 영역 끝 -->
                
                <div id="btnlink">
                    <ul>
						<li class="btn1"></li>
                    </ul>
                </div>

            </div>
        </div>
        <!-- //contents -->
    </div>
