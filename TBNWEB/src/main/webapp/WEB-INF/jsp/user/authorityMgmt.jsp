<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="contentWrap">
	<form id="authFrm" name="authFrm" method="post">
           <!-- <div id="posi"><img src="../images/ico_home.gif" alt="home" />정보관리 > 등급관리</div> -->
           <!-- contents -->
           <div id="contents"  >
              <!--  <h1 class="txt_i10"><img src="../images/tit_07menu01_on.gif" alt="등급관리" /></h1> -->
              <h1 class='content-title'>등급관리</h1>
               <!-- board_list -->
               <div class="board_list">
                   <!-- 중분류선택 시작 -->
                   <div style="float:left;width:145px;margin-top:10px">
                       <div class="tit11 txt_right mgr_lb"></div>
                       <div class="tit11_1 scroll">
                           <ul>
                               <li>
                               	<select class="table_sel2" id="AUTH_DIV" name="AUTH_DIV" onchange="loadAuthMenu(this.value);">
							        <c:forEach var="authInfo" items="${authList}">
							            <option value="${authInfo.AUTH_DIV}"><c:out value="${authInfo.AUTH_DIV_NAME}"/></option>
							        </c:forEach>
							    </select>
                               </li>
                           </ul>
                       </div>
                   </div>
                   <!-- 소분류선택 시작 -->
                   <div id="db_layer12"  >
                       <div class="tit12 txt_right mgr_lb"></div>
                       <div class="tit12_1 scroll" id="menuListDiv" >
                       </div>
                   </div>
                   <!-- 유형별관리 선택구분 끝-->
               </div>
           </div>
           <!-- //contents -->
           
           <!-- //contentWrap -->
           <div class="btnArg" style="margin-top:350px">
		    <input onclick="saveAuth();" type="image" src="<c:url value="/images/btn_saveb.gif"/>" alt="저장" title="저장" />
		</div>
	</form>
</div>


<script>
$(document).ready(function(){
	loadAuthMenu($('#AUTH_DIV option:eq(0)').val());
})

/**
 * 메뉴 정보
 */
function loadAuthMenu(param){
	var options = {
            url: '<c:url value="/user/loadAuthMenu.do"/>?AUTH_DIV='+param,
            success: function(res){
            	$('#menuListDiv').html(res);
            },
            error: function(res,error){
                alert("에러가 발생했습니다."+error);
            }
    };
    $.ajax(options);
}

/**
 * 권한 저장
 */
function saveAuth(){
	var menuList = new Array();
	$('input[type=checkbox]:checked').each(function(idx){
		menuList[idx] = $(this).val();
	});
	
	var options = {
            url: '<c:url value="/user/saveAuthMenu.do"/>?AUTH_DIV=' + $('#AUTH_DIV').val() + '&menuList='+menuList,
            dataType: "json",
            success: function(res){
            	if(res.cnt > 0){
                    alert("저장되었습니다.");
                } else {
                    alert("저장에 실패하였습니다.");
                }
            },
            error: function(res,error){
                alert("에러가 발생했습니다."+error);
            }
    };
    $.ajax(options);
}
</script>