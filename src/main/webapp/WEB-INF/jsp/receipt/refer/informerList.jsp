<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>							
							<div class="admin_result_sc2" >
                                <p class="list_result"><img src="../images/ico_result.gif" alt="" />검색결과 ${informationSize} 건</p>
                                <table summary="통신원검색결과" border="0" cellpadding="0" cellspacing="0" class="list01">
                                    <caption>
                                    통신원검색결과
                                    </caption>
                                    <colgroup>
                                    <col width="40" />
                                    <col width="60" />
                                    <col width="*" />
                                    </colgroup>
                                    <thead>
                                        <tr>
                                            <th scope="col">ID</th>
                                            <th scope="col">이름</th>
                                            <th scope="col">전화번호</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                    	<c:forEach var="informationInfo" items="${informationList }">
	                                        <tr onclick="getInformer('${informationInfo.ACT_ID }','${informationInfo.INFORMER_NAME }')" style="cursor: pointer;">
	                                            <td >${informationInfo.ACT_ID }</td>
	                                            <td>${informationInfo.INFORMER_NAME }</td>
	                                            <td >${informationInfo.PHONE_CELL }</td>
	                                            <td >${informationInfo.INFORMER_TYPE_NM }</td>
	                                            <td >${informationInfo.REGIONSUB_NAME }</td>
	                                        </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </div>