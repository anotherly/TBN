<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>TBN한국교통방송 제보접수시스템</title>
<link rel="stylesheet" type="text/css" href="<c:url value='/css/layout.css'/>" />
<script type="text/javascript" charset="utf-8" src="<c:url value='/js/common/jquery/jquery-1.6.4.min.js'/>"></script>
<script type="text/javascript" charset="utf-8" src="<c:url value='/js/common/jquery/jquery.form.js'/>"></script>
</head>
<body>
<form id="frmContents" name="frmContents" method="post">
<!-- contents -->
<div id="contents_memo2">
  <!-- board_list -->
  <div class="board_list">
    <p class="list_result"><img src="../images/ico_result.gif" alt="" />제보정보 상세정보</p>
    <div>
      <table summary="제보정보 상세" border="0" cellpadding="0" cellspacing="0" class="list01">
        <caption>
        제보정보 상세
        </caption>
        <colgroup>
        <col width="100" />
        <col width="100" />
        <col width="140" />
        <col width="*" />
        </colgroup>
        <thead>
          <tr>
          	<input type="hidden" id="receipt_id" name="receipt_id" value="${receiptDetail[0].RECEIPT_ID}" />
          	<input type="hidden" id="receipt_flag" name="receipt_flag" value="" />
            <%-- <th scope="col" class="txt_left">&nbsp;번호 : ${receiptDetail[0].}</th> --%>
            <th scope="col" class="txt_left">&nbsp;유형 : ${receiptDetail[0].INFORMER_TYPE}</th>
            <th scope="col" class="txt_left">구분 : ${receiptDetail[0].REPORT_TYPE}</th>
            <th scope="col" class="txt_left">제보자 : ${receiptDetail[0].INFORMER_NAME}</th>
            <th scope="col" class="txt_left">전화번호 : ${receiptDetail[0].INFORMER_TEL}</th>
          </tr>
        </thead>
        <tbody>
          <tr>
            <td colspan="5" ><textarea class="table_sel" id="contents" name="contents" cols="50" rows="8" style="width:560px; height:100px;padding:10px;">${receiptDetail[0].CONTENTS}</textarea></td>
          </tr>
        </tbody>
      </table>
      </form>
      <div class="btnArg">
        <input onclick="javascript:updateContents();" type="image" src="../images/btn_modify.gif" alt="수정" title="수정" class="poin" />
        <input onclick="javascript:resetContents();" type="image" src="../images/btn_cancel.jpg" alt="취소" title="취소" class="poin" />
      </div>
    </div>
  </div>
</div>
</body>
<script>
function resetContents(){
	self.close();
}

function updateContents(){
	$('#receipt_flag').val(opener.tabMenu);
 	var options = {
		url: '<c:url value="/receipt/updateContents.do"/>',
		type: 'post',
		target: '#frmContents',
		dataType: "json",
        success: function(res){
            if(res.result == 1){
            	alert("저장되었습니다.");
       			if(opener.tabMenu=='divTodayList'){
       				opener.goTodaySearch();
        		} else if(opener.tabMenu=='divIncidentList'){
        			opener.goIncidentSearch();
        		}
            	self.close();
            } else {
            	alert("저장에 실패하였습니다.");
            }
        } ,
        error: function(res,error){
        	alert("에러가 발생했습니다."+error);
        }
	};
	$("#frmContents").ajaxSubmit(options); 
	
}
</script>
</html>