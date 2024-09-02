<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script>
var menuList = new Array();
var authSlt;
var chgFlg;
$(document).ready(function(){
	console.log("권한 관리");
	loadAuthMenu($('#AUTH_DIV option:selected').val());
	$('#AUTH_DIV').on("change",function(){
		authSlt = $('#AUTH_DIV').val();
	});	
	$(".board_list input[type='checkbox']").on("change",function(){
		chgFlg=1;
	});	
	
})

/**
 * 메뉴 정보
 */
function loadAuthMenu(param){
	$("#menuListDiv2").empty();
	$("#menuListDiv3").empty();
	var options2 = {
        url: "/option/auth/authList2.do",
        data:{cdFlag:"2",authCode:param},
        success: function(res){
        	$('#menuListDiv2').html(res);
        },
        error: function(res,error){
            alert("에러가 발생했습니다."+error);
        }
    };
	var options3 = {
        url: "/option/auth/authList3.do",
        data:{cdFlag:"3",authCode:param},
        success: function(res){
        	$('#menuListDiv3').html(res);
        },
        error: function(res,error){
            alert("에러가 발생했습니다."+error);
        }
    };
    $.ajax(options2); 
    $.ajax(options3); 
    
}

/**
 * 중분류와 소분류의 체크박스 연계
 */
function saveChkArr(that,flg){
	console.log(" id / val : "+$(that).attr("id")+" / "+$(that).val());
	var num = $(that).val().substring(0,1);
	if(flg==2){
		console.log("중분류");
		//소분류 하위 레이어에 항목이 존재시
		if($("#db_layer5 input[type='checkbox']").length != 0 ){
			if($(that).is(":checked")){
				$("#ulTypeCode3 input[id^='check"+num+"']").prop("checked",true);
			}else{
				$("#ulTypeCode3 input[id^='check"+num+"']").prop("checked",false);
			}
		}
	}else{
		console.log("소분류");
		//전체 선택해야만 중분류가 체크되도록 변경
		//사용자관리 등이나 코드관리에서 맨 앞에거 안나오게 하려고
		if($("#ulTypeCode3 input[id^='check"+num+"']:checked").length
		==$("#ulTypeCode3 input[id^='check"+num+"']").length
					){
			$("#ulTypeCode2").find("#check"+num).prop("checked",true);
		}else{
			$("#ulTypeCode2").find("#check"+num).prop("checked",false);
		}
	}
}
 
 
/**
 * 권한 저장
 */
function saveAuth(){
	var menuList = new Array();
	$("#ulTypeCode2 input[type=checkbox]").each(function(){
		var chkArr=$(this).val();
		if($(this).is(":checked")){
			chkArr+="-Y";
		}else{
			chkArr+="-N";
		}
		menuList.push(chkArr);
	});
	$("#ulTypeCode3 input[type=checkbox]").each(function(){
		var chkArr=$(this).val();
		if($(this).is(":checked")){
			chkArr+="-Y";
		}else{
			chkArr+="-N";
		}
		menuList.push(chkArr);
	});
	
	var options = {
            url: '/option/updateAuth.do',
            data: {authCode:$('#AUTH_DIV').val(),menuList:menuList},
            async : false,
            success: function(json){
            	alert(json.msg);
            },
            error: function(json){
            	alert(json.msg);
            }
    };
    //화면이동 방지
	event.preventDefault();
    $.ajax(options);
}
</script>


<div id="contentWrap">
	<form id="authFrm" name="authFrm" method="post">
           <!-- <div id="posi"><img src="../images/ico_home.gif" alt="home" />정보관리 > 등급관리</div> -->
           <!-- contents -->
           <div id="contents"  >
               <h1 class='content-title'>등급관리</h1>
               <!-- board_list -->
               <div class="board_list" style="margin-top: 30px;overflow: auto;width:900px;">
                   <!-- 대분류선택 시작 -->
                   <div id="db_layer11">
	                   <div class="tit11 txt_right mgr_lb">
	                   <span>대분류</span>
	                   </div>
	                   <div class="tit11_1 scroll">
	                       <ul>
	                           <li>
	                           	<select class="table_sel2" id="AUTH_DIV" name="AUTH_DIV" onchange="loadAuthMenu(this.value);">
							        <c:forEach var="authInfo" items="${authList}">
							            <option value="${authInfo.authCode}"><c:out value="${authInfo.authName}"/></option>
							        </c:forEach>
							    </select>
	                           </li>
	                       </ul>
	                   </div>
	                </div>
                    <!-- 중분류선택 시작 -->
					<div id="db_layer4">
						<div class="tit4 txt_right mgr_lb">
						<span>중분류</span>
						</div>
					    <!-- 중분류 리스트 -->
					    <div class="tit4_1 scroll" id="menuListDiv2"></div>
					</div>
					<!-- 소분류선택 시작 -->
					<div id="db_layer5">
					    <div class="tit5 txt_right mgr_lb">
					    	<span>소분류</span>
					    </div>
						<div class="tit5_1 scroll" id="menuListDiv3"></div>
					</div>
                   <!-- 유형별관리 선택구분 끝-->
               </div>
           </div>
           <!-- //contents -->
           
           <!-- //contentWrap -->
           <div class="btnArg">
		    <input onclick="saveAuth(event);" type="image" src="<c:url value="/images/btn_reget1.png"/>" alt="저장" title="저장" />
		</div>
	</form>
</div>

