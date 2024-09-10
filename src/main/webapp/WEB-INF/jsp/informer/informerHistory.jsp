<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="ko" lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>TBN한국교통방송 제보접수시스템</title>
<link rel="stylesheet" type="text/css" href="<c:url value='/css/layout.css'/>" />
<script type="text/javascript" charset="utf-8" src="<c:url value='/js/common/jquery/jquery-1.6.4.min.js'/>"></script>
</head>
<body >
<div id="container">
    <div id="topWrap">
        <!-- bodyWrap -->
        <div id="bodyWrap" class="clfix">
            <!-- contentWrap -->
            <div id="contentWrap">
                <div id="contents">
                    <h1 class="pabt8 admin_tit"><img src="../images/ico_tab01.gif" alt="" />제보자 이력정보</h1>
                </div>
                <div class="board_list">
                    <!-- 통신원 이력정보 시작 -->
                    <div class="admin_edit3">
                        <table width="100%" border="0" cellspacing="0" cellpadding="0" style="border-bottom:1px solid #babbbd;">
                            <caption>
                            통신원정보
                            </caption>
                         <%--    <colgroup>
                            <col width="30" />
                            <col width="80" />
                            <col width="55" />
                            <col width="150" />
                            <col width="45" />
                            <col width="150" />
                            <col width="70" />
                            <col width="*" />
                            </colgroup> --%>
                            <tr>
                                <td class="strong">ID</td>
                                <td class="name">: <c:out value="${informerInfo.actId}"/></td>
                                <td class="strong">소속명</td>
                                <td class="name">: <c:out value="${informerInfo.areaName}"/></td>
                                <td class="strong">이름</td>
                                <td class="name">: <c:out value="${informerInfo.informerName}"/></td>
                                <td class="strong">휴대전화</td>
                                <td class="name">: <c:out value="${informerInfo.phoneCell}"/></td>
                            </tr>
                            <tr>
                                <td>&nbsp;</td>
                                <td>&nbsp;</td>
                                <td>&nbsp;</td>
                                <td>&nbsp;</td>
                                <td>&nbsp;</td>
                                <td>&nbsp;</td>
                                <td>&nbsp;</td>
                                <td>&nbsp;</td>
                            </tr>
                        </table>
                        <div style="height: 580px; overflow-y: auto;">
                        <!-- 시상정보 시작-->
                        <p class="admin_result" style="border-bottom:0px;"><img src="../images/ico_result.gif" alt="" />시상정보 </p>
                        <div class="admin_result_sc3">
                            <table summary="시상정보" border="0" cellpadding="0" cellspacing="0" class="list01">
                                <caption>
                                시상정보
                                </caption>
                                <colgroup>
                                <col width="200" />
                                <col width="*" />
                                </colgroup>
                                <thead>
                                    <tr>
                                        <th scope="col">해당월</th>
                                        <th scope="col">시상명</th>
                                    </tr>
                                </thead>
                            </table>
                            <div style="height: 135px; overflow-y: auto;">
                                <table summary="이력정보" border="0" cellpadding="0" cellspacing="0" class="list01">
                                <caption>
                                시상정보목록
                                </caption>
                                <colgroup>
                                <col width="200" />
                                <col width="*" />
                                </colgroup>
                                <tbody>
                                    <c:forEach var="award" items="${awardList}" varStatus="idx">
                                    <tr>
                                        <td><c:out value="${award.AW_DATE}"/></td>
                                        <td><c:out value="${award.AW_NAME}"/></td>
                                    </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                            </div>
                        </div>
                        <!-- 시상정보 끝-->
                        <!-- 행사참석 시작-->
                        <p class="admin_result" style="border-bottom:0px;"><img src="../images/ico_result.gif" alt="" />행사참석정보</p>
                        <div class="admin_result_sc3">
                            <table summary="시상정보" border="0" cellpadding="0" cellspacing="0" class="list01">
                                <caption>
                                행사정보
                                </caption>
                                <colgroup>
                                <col width="200" />
                                <col width="200" />
                                <col width="*" />
                                </colgroup>
                                <thead>
                                    <tr>
                                        <th scope="col">행사일자</th>
                                        <th scope="col">헹사명</th>
                                        <th scope="col">행사내용</th>
                                    </tr>
                                </thead>
                            </table>
                            <div style="height: 135px; overflow-y: auto;">
                                <table summary="이력정보" border="0" cellpadding="0" cellspacing="0" class="list01">
                                <caption>
                                행사정보목록
                                </caption>
                                <colgroup>
                                <col width="200" />
                                <col width="200" />
                                <col width="*" />
                                </colgroup>
                                <tbody>
                                    <c:forEach var="event" items="${eventList}" varStatus="idx">
                                    <tr>
                                        <td><c:out value="${event.EVENT_DATE}"/></td>
                                        <td><c:out value="${event.EVENT_NAME}"/></td>
                                        <td class="txt_left"><c:out value="${event.CONTENTS}"/></td>
                                    </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                            </div>
                        </div>
                        <!-- 행사참석 끝-->
                        <!-- 이력정보 시작-->
                        <p class="admin_result" style="border-bottom:0px;"><img src="../images/ico_result.gif" alt="" />이력정보</p>
                        <div class="admin_result_sc3">
                            <table summary="이력정보" border="0" cellpadding="0" cellspacing="0" class="list01">
                                <caption>
                                이력정보
                                </caption>
                                <colgroup>
                                <col width="200" />
                                <col width="200" />
                                <col width="*" />
                                </colgroup>
                                <thead>
                                    <tr>
                                        <th scope="col">구분</th>
                                        <th scope="col">변경일</th>
                                        <th scope="col">내용</th>
                                    </tr>
                                </thead>
                            </table>
                            <div style="height: 135px; overflow-y: auto;">
                                <table summary="이력정보" border="0" cellpadding="0" cellspacing="0" class="list01">
                                <caption>
                                이력정보목록
                                </caption>
                                <colgroup>
                                <col width="200" />
                                <col width="200" />
                                <col width="*" />
                                </colgroup>
                                <tbody>
                                    <c:forEach var="informerHistory" items="${informerHistoryList}" varStatus="idx">
                                    <tr>
                                        <td>${fn:length(informerHistoryList) - idx.count+1 }</td>
                                        <td><c:out value="${informerHistory.updateDate}"/></td>
                                        <td class="txt_left"><c:out value="${informerHistory.updateText}"/></td>
                                    </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                            </div>
                        </div>
                        <!-- 이력정보 끝-->
                        <!-- 버튼 시작-->
                        <div class="btnArg mgt10">
                            <!-- <input onclick="self.close();" type="image" src="../images/btn_cancel.jpg" alt="취소" title="취소" /> -->
                        </div>
                        <!-- 버튼 끝-->
                        </div>
                    </div>
                    <!-- 통신원 이력정보 시작 -->
                </div>
            </div>
            <!-- //contentWrap -->
        </div>
        <!-- //bodyWrap -->
    </div>
</div>
<script>
</script>
</body>
</html>
