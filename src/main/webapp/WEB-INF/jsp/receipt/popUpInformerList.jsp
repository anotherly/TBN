<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="ko" lang="ko">
<head>
<style>
	#informerListDiv table tr th{
		font-size: 14px;
	}
	#informerListDiv table tr td{
		font-size: 14px;
	}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>TBN한국교통방송 제보접수시스템</title>
<link rel="stylesheet" type="text/css" href="<c:url value='/css/layout.css'/>"/>
<script type="text/javascript" charset="utf-8" src="<c:url value='/js/common/jquery/jquery-1.6.4.min.js'/>"></script>
<script type="text/javascript" charset="utf-8" src="<c:url value='/js/common/jquery/jquery.form.js'/>"></script>
<script type="text/javascript" charset="utf-8" src="<c:url value='/js/common/ui.js'/>"></script>
<script type="text/javascript" charset="utf-8" src="<c:url value="/js/common/jquery.pagination.js"/>"></script>
<script  type="text/javascript" charset="utf-8" src="<%=request.getContextPath()%>/js/jquery.js"></script>
<script  type="text/javascript" charset="utf-8"  src="<%=request.getContextPath()%>/js/common.js"></script>
<script type="text/javascript" charset="utf-8" src="<c:url value="/js/common/util.js"/>"></script>
<script type="text/javascript" charset="utf-8" src="<%=request.getContextPath()%>/js/timeDate.js"></script>

<script>
	function chooseInformer(informerId, idx){
		console.log("통신원 검색");
		console.log("아이디:" + informerId);
		var informerObj = ajaxMethod("/receipt/selectInformerByID.ajax", {"INFORMER_ID":informerId
																				, "month1":currentMonth("")
																				, "month2":lastMonth("")
																				, "month3":twoMonthAgo("")}).data;
		if(idx == 3){
			opener.selectInformerByPhone(informerObj);
		}else if(idx == 4){//금일접수현황에서 열었을 경우
			opener.popupEdit(informerObj,idx);	
		}else {
			opener.appendInformerLabel(informerObj, idx);
		}
		window.close();
	}
</script>
</head>
<body>
<form id="informationFrm" name="informationFrm" >
<!--<c:if test=""></c:if>-->
<div id="container">
    <div id="topWrap">
        <!-- bodyWrap -->
        <div id="bodyWrap" class="clfix">
            <!-- contentWrap -->
            <div id="contentWrap">
                <div id="contents">
                    <h1 class="pabt8"><img src="../images/ico_tab01.gif" alt="" /><img src="../images/h1_0101_01.gif" alt="통신원정보" /></h1>
                    <div class="contentsLeft" style="650px;">
                        <!-- 통신원 검색결과 시작 -->
                        <div class="board_list" id="informerListDiv" style="padding: 15px 60px;">
                            <div class="admin_result_sc4" style="width:100%; height:550px;">
                                <p class="list_result"><img src="../images/ico_result.gif" alt="" />검색결과 ${informerListSize} 건</p>
                                <table summary="통신원검색결과" border="0" cellpadding="0" cellspacing="0" class="list01">
                                    <caption>통신원검색결과</caption>
                                    <colgroup>
	                                    <col width="40" />
	                                    <col width="60" />
	                                    <col width="70" />
	                                    <col width="70" />
	                                    <col width="70" />
	                                    <col width="*" />
                                    </colgroup>
                                    <thead>
                                        <tr>
                                            <th scope="col">ID</th>
                                            <th scope="col">이름</th>
                                            <th scope="col">전화번호</th>
                                            <th scope="col">유형</th>
                                            <th scope="col">소속</th>
                                        </tr>
                                    </thead>
                                    <tbody id="informerList">
                                    	<c:if test="${informerListSize <= 0 }">
                                    		<tr>
                                    			<td colspan="5">검색결과가 없습니다.</td>
                                    		</tr>
                                    	</c:if>
                                    	<c:if test="${informerListSize > 0 }">
                                    	<c:forEach var="informerVO" items="${informerList }">
	                                        <tr onclick="chooseInformer('${informerVO.INFORMER_ID}','${param.idx}')" style="cursor: pointer;">
		                                        <c:choose>
			                                        <c:when test="${empty informerVO.ACT_ID}"><td>-</td></c:when>
			                                        <c:otherwise><td>${informerVO.ACT_ID }</td></c:otherwise>
		                                        </c:choose>
		                                        <c:choose>
			                                        <c:when test="${empty informerVO.INFORMER_NAME}"><td>-</td></c:when>
			                                        <c:otherwise><td>${informerVO.INFORMER_NAME }</td></c:otherwise>
		                                        </c:choose>
		                                        <c:choose>
			                                        <c:when test="${empty informerVO.PHONE_CELL}"><td>-</td></c:when>
			                                        <c:otherwise><td>${informerVO.PHONE_CELL }</td></c:otherwise>
		                                        </c:choose>
		                                        <c:choose>
			                                        <c:when test="${empty informerVO.TYPE_NAME}"><td>-</td></c:when>
			                                        <c:otherwise><td>${informerVO.TYPE_NAME }</td></c:otherwise>
		                                        </c:choose>
		                                        <c:choose>
			                                        <c:when test="${empty informerVO.AREA_NAME}"><td>-</td></c:when>
			                                        <c:otherwise><td>${informerVO.AREA_NAME }</td></c:otherwise>
		                                        </c:choose>
	                                        </tr>
                                        </c:forEach>
										</c:if>
									</tbody>
                                </table>
                            </div>
                        </div>
                        <div style="margin:5px 0 0 330px;"><img src="/images/btn_cl.png" alt="취소" title="취소" class="poin" onclick="window.close();" style="cursor:pointer;height:31px;" /></div>
                        <!-- 통신원 검색결과 끝 -->
                    </div>
                </div>
                <!-- 통신원정보 개별상세정보 끝 -->
            </div>
            <!-- //contentWrap -->
        </div>
        <!-- //bodyWrap -->
    </div>
</div>
</form>
</body>
</html>
