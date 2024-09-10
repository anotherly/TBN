<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>TBN한국교통방송 제보접수시스템</title>
<link rel="stylesheet" type="text/css" href="../css/login.css" />
<script type="text/javascript" charset="utf-8" src="<%=request.getContextPath()%>/js/common/jquery/jquery-1.6.4.min.js"></script>
<script type="text/javascript" charset="utf-8" src="<c:url value='/js/common/jquery/jquery.form.js'/>"></script>
<script type="text/javascript" charset="utf-8" src="<%=request.getContextPath()%>/js/common/ui.js"></script>
<script>
function goMain(strCode, strName){
	$('#selRegion').val(strCode);
	$('#selRegionName').val(strName);
	
 	$('#frmRegion').attr({
		action :'<c:url value="/login/master.do"/>',
		method :'post'
	}).submit();
}
</script>
</head>
<body>
<form id="frmRegion" name="frmRegion" method="post">
<input type="hidden" id="selRegion" name="selRegion" />
<input type="hidden" id="selRegionName" name="selRegionName" />
<div id="warp">
    <div id="area">
        <h1><img src="../images/area_text.gif" alt="해당교통방송선택" /></h1>
        <ul>
        <c:forEach var="list" items="${regionList}" varStatus="num" >
        	<a href="javascript:goMain('${list.CODE}','${list.CODE_NAME}')"><img src="../images/area${list.CODE}.gif" alt="${list.CODE_NAME}" /></a>
     	</c:forEach>
        </ul>
    </div>
</div>
</form>
</body>
</html>
