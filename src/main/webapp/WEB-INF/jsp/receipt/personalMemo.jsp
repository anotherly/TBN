<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>개인메모</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/layout.css"/>
<script type="text/javascript" charset="utf-8" src="<c:url value='/js/common/ui.js'/>"></script>
<script type="text/javascript" charset="utf-8" src="<c:url value="/js/common/util.js"/>"></script>
<script  type="text/javascript" charset="utf-8" src="<%=request.getContextPath()%>/js/jquery.js"></script>
<script type="text/javascript" charset="utf-8" src="<%=request.getContextPath()%>/js/jquery.form.js"></script>
<script type="text/javascript" charset="utf-8" src="<%=request.getContextPath()%>/js/jquery.pagination.js"></script>
<script  type="text/javascript" charset="utf-8" src="<%=request.getContextPath()%>/js/common.js"></script>
<script type="text/javascript" charset="utf-8" src="<%=request.getContextPath()%>/js/receipt/receipt.js"></script>
<script type="text/javascript" charset="utf-8" src="<%=request.getContextPath()%>/js/receipt/personalMemo.js"></script>

<style>
	#MEMO{
		width:650px;
		height:750px;
		border:none;
		overflow:hidden;
		resize:none;
		margin-left:15px;
		margin-top:10px;
		font-size:20px;
		font-weight:700;
		color:#777777;
	}
</style>

<script>
	$(document).ready(function(){
		var userId = opener.userId;
		console.log("userId: " + userId);
		console.log("userId: " + '${login.userId}');
		//var userId = "tst1";
		var memo = "";
		var vo = ajaxMethod("/receipt/selectPersonalMemo.do", {"userId":userId}).data;
		memo = vo.memo;
		$("#MEMO").html(memo);
	});
</script>
</head>

<body style="background-color:#e1e1e1;">
<textarea id="MEMO" maxlength="500"></textarea>
<div class="btnArg">
	<img src="../images/btn_save1.png" alt="확인" title="등록" class="poin" onclick="saveMemo()" style="cursor: pointer;" /> 
	<img src="../images/btn_reset1.png" alt="내용지우기" title="내용지우기" class="poin" onclick="clearMemo()" style="cursor: pointer;" />
</div>
</body>
</html>