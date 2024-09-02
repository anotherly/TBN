<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" type="text/css" href="<c:url value='/css/layout.css'/>"/>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/style.css"/>
<script type="text/javascript" charset="utf-8" src="<%=request.getContextPath()%>/js/jquery.js"></script>
<script type="text/javascript" charset="utf-8" src="<%=request.getContextPath()%>/js/jquery.form.js"></script>
<script type="text/javascript" charset="utf-8" src="<%=request.getContextPath()%>/js/common.js"></script>
<style type="text/css">
    .strong {width: 120px;}
    .input_base {width: 175px;}
</style>
<div id="contents">
    <c:if test="${userInfo.userId eq null }">
        <h1 class="pabt8 admin_tit"><img src="<c:url value="/images/ico_tab01.gif"/>" alt="사용자등록" />사용자등록</h1>
    </c:if>
    <c:if test="${userInfo.userId ne null }">
        <h1 class="pabt8 admin_tit"><img src="<c:url value="/images/ico_tab01.gif"/>" alt="사용자 상세정보" />사용자 상세정보</h1>
    </c:if>
</div>
<div class="board_list">
    <div class="admin_edit2">
        <table width="100%" border="0" cellspacing="0" cellpadding="0" >
            <tr>
                <td>
                    <form id="editUserFrm" name="editUserFrm">
                    <input type="hidden" id="editDiv" name="editDiv" value="${editDiv}"/>
                    <table width="100%" border="0" cellspacing="0" cellpadding="0" class="admin_list">
                         <tr>
                             <td class="strong">사용자ID</td>
						         <td><input class="input_base" type="text" id="userId" name="userId" maxlength="14" onkeyup="spaceChk(this);" onkeydown="spaceChk(this);" required/></td>
                         </tr>
                         <tr>
                             <td class="strong">소속</td>
                             <td>
                             	<select class="table_sel3" id="regionId" name="regionId">
                                     <c:forEach var="areaInfo" items="${positionList}">
                                         <c:if test="${areaInfo.areaCode ne '0'}">
                                             <option value="${areaInfo.areaCode}" ><c:out value="${areaInfo.areaName}"/></option>
                                         </c:if>
                                     </c:forEach>
                                 </select>
                             </td>
                         </tr>
                         <tr>
                             <td class="strong">이름</td>
                             <td><input class="input_base" type="text" id="userName" name="userName" maxlength="10" onkeyup="spaceChk(this);" onkeydown="spaceChk(this);" required/></td>
                         </tr>
                         <tr>
                             <td class="strong">권한</td>
                             <td>
                                 <select class="table_sel3" id="authCode" name="authCode">
                                     <c:forEach var="authInfo" items="${authList}">
                                         <c:if test="${authInfo.authCode ne '0'}">
                                             <option value="${authInfo.authCode}" <c:if test="${authInfo.authCode eq userInfo.authCode}">selected</c:if>><c:out value="${authInfo.authName}"/></option>
                                         </c:if>
                                     </c:forEach>
                                 </select>
                             </td>
                         </tr>
                         <tr>
                             <td class="strong">비밀번호<p class="rem">영문+숫자+특수 15자이상</p></td>
                             <td><input class="input_base" type="password" id="userPw" name="userPw" maxlength="20" onkeyup="spaceChk(this);" onkeydown="spaceChk(this);" required/></td>
                         </tr>
                         <tr>
                             <td class="strong">비밀번호 확인<p class="rem">영문+숫자+특수 15자이상</p></td>
                             <td><input class="input_base" type="password" id="userPw2" name="userPw2" maxlength="20" onkeyup="spaceChk(this);" onkeydown="spaceChk(this);" required/></td>
                         </tr>
                         <tr>
                             <td class="strong">전화번호</td>
                             <td>
                             	<input class="input_base" type="hidden" id="userPhone" name="userPhone" maxlength="20" />
								<input type="text" id="userPhone1" class="userPhone" maxlength="4" onkeydown='return onlyNumber(event)' onkeyup='removeChar(event)' required/>
								<span> - </span>
								<input type="text" id="userPhone2" class="userPhone" maxlength="4" onkeydown='return onlyNumber(event)' onkeyup='removeChar(event)' required/>
								<span> - </span>
								<input type="text" id="userPhone3" class="userPhone" maxlength="4" onkeydown='return onlyNumber(event)' onkeyup='removeChar(event)' required/>
							</td>
                         </tr>
                         <tr>
                         	<td class="strong">특이사항(개인메모)<p class="rem">최대 500자</p></td>
                         	<td>
                         		<textarea class="table_sel" name="memo" id="memo" cols="50" rows="8" style="width:175px; height:150px;padding:10px;" maxlength="500"></textarea>
                         	</td>
                         </tr>
                     </table>
                     <!-- 등록버튼 시작-->
			        <div class="btnArg mgt10">
			            <button type="submit" ><img src="/images/btn_reget1.png" alt="저장" title="저장"></button>
			            <input onclick="self.close();" style="height:31px;" type="image" src="/images/btn_cl.png" alt="취소" title="취소"/>
			        </div>
			        <!-- 등록버튼 끝-->
                    </form>
                </td>
            </tr>
        </table>
        
    </div>
</div>
<script>
$(document).ready(function(){
	console.log("userInsert");
	var tagId = "${userInfo.userId}";
	
	$("#editUserFrm").submit(function(event){
		console.log(event);
		//화면이동 방지
		event.preventDefault();
		saveUser(this);
	});
	
});

/**
 * 사용자 저장
 */
function saveUser(that){
	if(boardWriteCheck(that) && telChk()){
		var options = {
	            url:"/user/insertUser.ajax",
	            type:'post',
	            data: $(that).serialize(),
	            async : false,
	            dataType: 'json',
	            success: function(res){
	            	 if(res.cnt > 0){
	                 	alert("저장되었습니다.");
	                 	opener.search();
	                 	self.close();
	                 } else {
	                 	alert("저장에 실패하였습니다.");
	                 }
	            } ,
	            error: function(res,error){
	            	alert("에러가 발생했습니다."+error);
	            }
	    };
		event.preventDefault();
		if(typeof options !=="undefined"){
		    $.ajax(options);
		}
	}
}

</script>
