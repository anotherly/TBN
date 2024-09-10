<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<head>
<title>TBN한국교통방송 제보접수시스템</title>
<link rel="stylesheet" type="text/css" href="<c:url value='/css/layout.css'/>"/>
<script type="text/javascript" charset="utf-8" src="<c:url value='/js/common/click_menu.js'/>"></script>
<script type="text/javascript" charset="utf-8" src="<c:url value='/js/common/jquery/jquery-1.6.4.min.js'/>"></script>
<script type="text/javascript" charset="utf-8" src="<c:url value='/js/common/jquery/jquery.form.js'/>"></script>
<script type="text/javascript" charset="utf-8" src="<c:url value='/js/common/ui.js'/>"></script>
<script>
function goSave(){
	var options = {
		url:"<c:url value="/memo/updateMemo.do"/>",
		type:"post",
		data: $('#frmMemo').serialize(),
		dataType: "json",
		success: function(res){
			if(res.userMemo!=""){
            	alert("저장되었습니다.");
            	$('#user_contents').val(res.userMemo);
            }else {
		    	alert("저장에 실패하였습니다.");
		    }
		} ,
		error: function(res,error){
			alert("에러가 발생했습니다.");
		}
    };
    $.ajax(options);
}

function goReset(){
	self.close();
}
</script>
</head>
<body>
<!-- contents -->
<div id="contents_memo">
  <!-- board_list -->
  <div class="board_list">
    <p class="list_result"><img src="../images/ico_result.gif" alt="" />개인메모</p>
    <div>
      <table summary="개인메모" border="0" cellpadding="0" cellspacing="0" class="list01">
        <caption>개인메모</caption>
        <colgroup>
        <col width="100" />
        <col width="*" />
        </colgroup>
        <thead>
          <tr>
            <th scope="col" class="txt_leftb" style="width:160px;">소속: ${regionName}</th>
            <th scope="col" class="txt_left">이름: ${userName}</th>
          </tr>
        </thead>
        <tbody>
<form id='frmMemo' name='frmMemo' method='post'>
          <tr>            
            <td colspan="2" ><textarea class="table_sel" name="user_contents" id="name="user_contents" cols="50" rows="8" style="width:260px; height:240px;padding:10px;">${userMemo}</textarea></td>
          </tr>
        </tbody>
      </table>
      <div class="btnArg">
      <img src="../images/btn_ok.jpg" alt="확인" title="확인" class="poin" onclick="goSave();" />
      <img src="../images/btn_cancel.jpg" alt="취소" title="취소" class="poin" onclick="goReset();" />
    </div>
    </div>
  </div>
</div>
</form>
</body>
</html>
