<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="ko" lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>통신원 마일리지 지급 이력 조회</title>
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
                    <h1 class="pabt8 admin_tit"><img src="../images/ico_tab01.gif" alt="" />마일리지 지급 이력 조회</h1>
                </div>
                <div class="board_list">
                    <!-- 통신원 이력정보 시작 -->
                    <div class="admin_edit3">
                        <table width="100%" border="0" cellspacing="0" cellpadding="0" style="border-bottom:1px solid #babbbd;">
                            <caption>
                            	통신원정보
                            </caption>
                            <tr>
                                <td class="strong">ID</td>
                                <td class="name">: <c:out value="${informerInfo[0].INFORMER_ID}"/></td>
                                <td class="strong">소속명</td>
                                <td class="name">: <c:out value="${informerInfo[0].ORG_NAME}"/></td>
                                <td class="strong">이름</td>
                                <td class="name">: <c:out value="${informerInfo[0].INFORMER_NAME}"/></td>
                                <td class="strong">휴대전화</td>
                                <td class="name">: <c:out value="${informerInfo[0].PHONE_CELL}"/></td>
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
                        <p class="admin_result" style="border-bottom:0px;"><img src="../images/ico_result.gif" alt="" />마일리지 정보 </p>
                        <div class="admin_result_sc3">
                            <table summary="마일리지 정보" border="0" cellpadding="0" cellspacing="0" class="list01">
                                <caption>
                                	마일리지 정보
                                </caption>
                                <colgroup>
                                <col width="200" />
                                <col width="*" />
                                </colgroup>
                                <thead>
                                    <tr>
                                        <th scope="col">현재 마일리지</th>
                                        <th scope="col">누적 마일리지</th>
                                    </tr>
                                </thead>
                            </table>
                            <div style="height: 135px; overflow-y: auto;">
                                <table summary="마일리지 정보" border="0" cellpadding="0" cellspacing="0" class="list01">
                                <caption>
                               		마일리지 정보
                                </caption>
                                <colgroup>
                                <col width="200" />
                                <col width="*" />
                                </colgroup>
                                <tbody>
                                    <tr>
                                        <td><c:out value="${informerInfo[0].ALL_MILEAGE}"/></td>
                                        <td><c:out value="${informerInfo[0].CUM_MILEAGE}"/></td>
                                    </tr>
                                </tbody>
                            </table>
                            </div>
                        </div>

                        <p class="admin_result" style="border-bottom:0px;"><img src="../images/ico_result.gif" alt="" />마일리지 지급 이력</p>
                        <div class="admin_result_sc3">
                            <table summary="마일리지 지급 이력" border="0" cellpadding="0" cellspacing="0" class="list01">
                                <caption>
                               		마일리지 지급 이력
                                </caption>
                                <colgroup>
                                <col width="200" />
                                <col width="200" />
                                <col width="*" />
                                </colgroup>
                                <thead>
                                    <tr>
                                        <th scope="col">지급일</th>
                                        <th scope="col">지급 마일리지</th>
                                        <th scope="col">지급내용</th>
                                    </tr>
                                </thead>
                            </table>
                            <div style="height: 135px; overflow-y: auto;">
                                <table summary="마일리지 지급 이력" border="0" cellpadding="0" cellspacing="0" class="list01">
                                <caption>
                                	마일리지 지급 이력
                                </caption>
                                <colgroup>
                                <col width="200" />
                                <col width="200" />
                                <col width="*" />
                                </colgroup>
                                <tbody>
                                    <c:forEach var="mileage" items="${mileList}" varStatus="idx">
                                    <tr>
                                        <td><c:out value="${mileage.PAYMENT_DATE}"/></td>
                                        <td><c:out value="${mileage.PAYMENT_MILE}"/></td>
                                        <td class="txt_left"><c:out value="${mileage.START_DATE} 마일리지 지급"/></td>
                                    </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                            </div>
                        </div>
                        
                        <p class="admin_result" style="border-bottom:0px;"><img src="../images/ico_result.gif" alt="" />상품 지급 이력</p>
                        <div class="admin_result_sc3">
                            <table summary="상품 지급 이력" border="0" cellpadding="0" cellspacing="0" class="list01">
                                <caption>
                               		상품 지급 이력
                                </caption>
                                <colgroup>
                                <col width="200" />
                                <col width="200" />
                                <col width="*" />
                                </colgroup>
                                <thead>
                                    <tr>
                                        <th scope="col">상품 지급일</th>
                                        <th scope="col">차감  마일리지</th>
                                        <th scope="col">지급 상품명</th>
                                    </tr>
                                </thead>
                            </table>
                            <div style="height: 135px; overflow-y: auto;">
                                <table summary="마일리지 지급 이력" border="0" cellpadding="0" cellspacing="0" class="list01">
                                <caption>
                                	마일리지 지급 이력
                                </caption>
                                <colgroup>
                                <col width="200" />
                                <col width="200" />
                                <col width="*" />
                                </colgroup>
                                <tbody>
                                    <c:forEach var="gift" items="${giftList}" varStatus="idx">
                                    <tr>
                                        <td><c:out value="${gift.PAYMENT_DATE}"/></td>
                                        <td><c:out value="${gift.MINUS_MILE}"/></td>
                                        <td class="txt_left"><c:out value="${gift.PAYMENT_GIFT}"/></td>
                                    </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                            </div>
                        </div>
                    </div>
                    </div>
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
