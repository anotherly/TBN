<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="ko" lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/layout.css"/>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/pagination.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/jquery-ui-1.9.0.custom.css" rel="stylesheet"  />
<script  type="text/javascript" charset="utf-8" src="<%=request.getContextPath()%>/js/jquery.js"></script>
	<script  type="text/javascript" charset="utf-8"  src="<%=request.getContextPath()%>/js/common.js"></script>
	<script type="text/javascript" charset="utf-8" src="<%=request.getContextPath()%>/js/jquery.pagination.js"></script>
	<script type="text/javascript" charset="utf-8" src="<%=request.getContextPath()%>/js/jquery.form.js"></script>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/calender/jquery-ui.css"/>
    <script src="<%=request.getContextPath()%>/calender/jquery-ui.js"></script>
<!-- 주소 관련 api -->
<script src="<%=request.getContextPath()%>/js/address/address.js"></script>
</head>
<body >
<div id="container">
    <div id="topWrap">
        <!-- bodyWrap -->
        <div id="bodyWrap" class="clfix">
            <!-- contentWrap -->
            <div id="contentWrap">
                <div id="contents">
                    <c:if test="${informerInfo.informerId eq null }">
                        <h1 class="pabt8 admin_tit"><img src="<c:url value='/images/ico_tab01.gif' />" alt="" />통신원등록<span class="rem-phone" style="left:-117px;"> * 전화번호는 숫자만 기입</span></h1>
                    </c:if>
                    <c:if test="${informerInfo.informerId ne null }">
                        <h1 class="pabt8 admin_tit"><img src="<c:url value='/images/ico_tab01.gif' />" alt="" />통신원상세정보<span class="rem-phone"> * 전화번호는 숫자만 기입</span></h1>
                    </c:if>
                </div>
                <!-- 통신원정보 개별상세정보 시작 -->
                <div class="board_list">
                    <!-- 통신원 상제정보 시작 -->
                    <div class="admin_edit">
                    <form id="informerEditFrm" name="informerEditFrm" method="post" enctype="multipart/form-data">
                        <table width="100%" border="0" cellspacing="0" cellpadding="0" >
                            <tr>
                                <td>
                                    <input type="hidden" id="informerId" name="informerId" value="${informerInfo.informerId}"></input>
                                    <%-- <input type="hidden" id="areaCode" name="areaCode" value="${areaCode}"></input> --%>
                                    <input type="hidden" id="pageDiv" name="pageDiv" value="${pageDiv}"></input>
                                    <table width="100%" border="0" cellspacing="0" cellpadding="0" class="admin_list">
                                    <!-- 신규 시작 -->
                                    <c:if test="${informerInfo.informerId eq null}">
                                        <tr>
                                            <td class="strong">통신원종류</td>
                                            <td colspan="2" class="b">
                                                <input type="radio" class="input_base" name="flagBroad" id="flagBroad" value="0" checked />
                                                <label for="radio-choice-1">일반통신원</label>
                                                <input type="radio" class="input_base" name="flagBroad" id="flagBroad" value="1"  />
                                                <label for="radio-choice-2">방송통신원</label>
                                             </td>
                                             <td colspan="3" class="b">
                                                <input type="checkbox" name="honor" id="honor" value="Y" />
                                                <label for="radio-choice-3">명예통신원</label>
                                                <input type="checkbox" name="flagService" id="flagService" value="Y" />
                                                <label for="radio-choice-3">자원봉사</label>
                                             </td>
                                        </tr>
                                        <tr>
                                        	<td class="strong">소속방송국</td>
                                            <td id="tdArea">
                                            	<select class="table_sel"  style="width:120px;" id="areaCodeSel" name="areaCode">
                                                    <c:forEach var="informerArea" items="${informerAreaList}">
                                                        <option value="${informerArea.areaCode}" 
	                                                        <c:if test="${informerArea.areaCode eq informerInfo.areaCode}">selected</c:if>>
	                                                        ${informerArea.areaName}
                                                        </option>
                                                    </c:forEach>
                                                </select>
                                            </td>
                                            
                                            <td class="strong">통신원유형</td>
                                            <td id="tdItype">
                                            	<select class="table_sel" style="width:155px;" id="informerTypeSel" name="informerType">
                                                    <c:forEach var="informerType" items="${informerTypeList}">
                                                        <option value="${informerType.ifmId1}" <c:if test="${informerType.ifmId1 eq informerInfo.informerType}">selected</c:if>>${informerType.ifmName}</option>
                                                    </c:forEach>
                                                </select>
                                            </td>
                                            <td class="strong">소속기관</td>
                                            <td id="tdOrgId">
                                            	<select class="table_sel"  style="width:165px;" id="orgIdSel" name="orgId">
													 <c:choose>
                                                    	<c:when test="${fn:length(informerOrgList)==0}">
                                                    		
                                                    	</c:when>
                                                    	<c:otherwise>
                                                     		<c:forEach var="informerOrg" items="${informerOrgList}">
	                                                    		<option value="${informerOrg.ifmId2}" 
	                                                    		<c:if test="${informerOrg.ifmId2 eq informerInfo.orgId}">selected</c:if>
	                                                    		>${informerOrg.ifmName}</option>
                                                    		</c:forEach>
                                                    	</c:otherwise>
                                                    </c:choose>
	                                                <option value="">무소속</option>
                                                </select>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td class="strong">소속기관(세부)</td>
                                            <td id="tdOrgSubId">
                                            	<select class="table_sel"  style="width:120px;" id="orgSubIdSel" name="orgSubId">
                                                   	<c:choose>
                                                    	<c:when test="${fn:length(informerOrgSubList)==0}"></c:when>
                                                    	<c:otherwise>
                                                   			<c:forEach var="informerOrgSub" items="${informerOrgSubList}">
                                                    			<option value="${informerOrgSub.ifmId3}" <c:if test="${informerOrgSub.ifmId3 eq informerInfo.orgSubId}">selected</c:if>>${informerOrgSub.ifmName}</option>
		                                                    </c:forEach>
                                                    	</c:otherwise>
                                                    </c:choose>
	                                                <option value="">무소속</option>
                                                </select>
                                            </td>
                                            <td class="strong"><span style="color:red">*</span>ID <br> <span class="rem-phone" style="position:inherit;margin:0;">(수정 가능)</span> </td>
                                            <td>
												<input type="text" class="input_base" id="actId" name="actId" value="${informerInfo.actId}" style="width:150px;font-weight: bold;"/>
												
											</td>
                                            <td class="strong"><span style="color:red">*</span>이름</td>
                                            <td><input type="text" class="input_base" id="informerName" name="informerName" value="${informerInfo.informerName}" style="width:160px;font-weight: bold;" /></td>
                                        </tr>
                                        <tr>
                                        </tr>
                                        <tr>
                                            <td class="strong">사진</td>
                                            <td colspan="3">
                                                <input type="hidden" class="input_base" id="localFilePath" name="localFilePath" value="${informerInfo.localFilePath}" style="width: 238px;" readonly />
                                                <input type="file" class="input_base" id="file" name="file" onchange="changePicture($(this));" style="width:377px; height: 20px;" />
                                            </td>
                                            <td class="strong">활동상태</td>
                                            <td>
                                                <input type="text" class="input_base" id="flageAct" name="flageAct" value="신규가입" style="width:160px;" disabled />
                                            </td>
                                        </tr>
                                    </c:if>
                                    <!-- 신규 끝 -->
<!--##################################################################### 수정 시작 ##################################################################### -->
                                    <c:if test="${informerInfo.informerId ne null}">
	                                    <tr>
	                                        <td colspan="2" rowspan="4" align="center">
	                                            <div id="pictureDiv">
	                                                <img src="<c:url value="/picture/${informerInfo.informerId}/${informerInfo.localFilePath}"/>" alt="사진" style="width: 85px; height: 113px; border: 1px;" />
	                                            </div>
	                                        </td>
	                                        <td class="strong">통신원종류</td>
	                                        <td colspan="2" class="b">
	                                            <input type="radio" class="input_base" name="flagBroad" id="flagBroad0" value="0" <c:if test="${informerInfo.flagBroad eq '0'}">checked</c:if> />
	                                            <label for="radio-choice-1">일반통신원</label>
	                                            <input type="radio" class="input_base" name="flagBroad" id="flagBroad1" value="1" <c:if test="${informerInfo.flagBroad eq '1'}">checked</c:if> />
	                                            <label for="radio-choice-2">방송통신원</label>
	                                        </td>
	                                        <td class="b" style="width: 170px;">
	                                            <input type="checkbox" name="honor" id="honor" value="Y" <c:if test="${informerInfo.honor eq 'Y'}">checked</c:if>/>
	                                            <label for="radio-choice-3">명예통신원</label>
	                                            <input type="checkbox" name="flagService" id="flagService" value="Y" <c:if test="${informerInfo.flagService eq 'Y'}">checked</c:if>/>
	                                            <label for="radio-choice-3">자원봉사</label>
	                                         </td>
	                                    </tr>
	                                    <tr>
	                                    	<td class="strong">소속방송국</td>
											<td id="tdArea">
												<select class="table_sel"  style="width:155px;" id="areaCodeSel" name="areaCode">
													<c:forEach var="informerArea" items="${informerAreaList}">
														<option value="${informerArea.areaCode}" <c:if test="${informerArea.areaCode eq informerInfo.areaCode}">selected</c:if>>${informerArea.areaName}</option>
													</c:forEach>
												</select>
											</td>
	                                        <td class="strong">통신원유형</td>
	                                        <td id="tdItype">
	                                        	<select class="table_sel" style="width:165px;" id="informerTypeSel" name="informerType">
													<c:forEach var="informerType" items="${informerTypeList}">
														<option value="${informerType.ifmId1}" 
														<c:if test="${informerType.ifmId1 eq informerInfo.informerType}">selected</c:if>>
														${informerType.ifmName}</option>
													</c:forEach>
												</select>
											</td>
	                                    </tr>
	                                    <tr>
	                                        <td class="strong">소속기관</td>
											<td id="tdOrgId">
												<select class="table_sel"  style="width:155px;" id="orgIdSel" name="orgId">
													 <c:choose>
	                                                    	<c:when test="${fn:length(informerOrgList)==0}">
	                                                    		<option value="">무소속</option>
	                                                    	</c:when>
	                                                    	<c:otherwise>
	                                                     			<option value="" selected>무소속</option>
	                                                     		<c:forEach var="informerOrg" items="${informerOrgList}">
		                                                    		<option value="${informerOrg.ifmId2}" 
		                                                    		<c:if test="${informerOrg.ifmId2 eq informerInfo.orgId}">selected</c:if>>
		                                                    		${informerOrg.ifmName}</option>
	                                                    		</c:forEach>
	                                                    	</c:otherwise>
	                                                    </c:choose>
	                                                 <!-- <option value="">무소속</option> -->
												</select>
											</td>
	                                        <td class="strong">소속기관(세부)</td>
											<td id="tdOrgSubId">
												<select class="table_sel"  style="width:165px;" id="orgSubIdSel" name="orgSubId">
													 <c:choose>
	                                                    	<c:when test="${fn:length(informerOrgSubList)==0}">
	                                                    		<option value="">무소속</option>
	                                                    	</c:when>
	                                                    	<c:otherwise>
                                                    				<option value="" selected>무소속</option>
                                                    			<c:forEach var="informerOrgSub" items="${informerOrgSubList}">
	                                                    			<option value="${informerOrgSub.ifmId3}" 
	                                                    			<c:if test="${informerOrgSub.ifmId3 eq informerInfo.orgSubId}">selected</c:if>>
	                                                    			${informerOrgSub.ifmName}</option>
			                                                    </c:forEach>
	                                                    	</c:otherwise>
	                                                    </c:choose>
	                                                 <!-- <option value="">무소속</option> -->
												</select>
											</td>
	                                    </tr>
	                                    <tr>
	                                        <td class="strong"><span style="color:red">*</span>ID  <br> <span class="rem-phone" style="position:inherit;margin:0;">(수정 가능)</span>  </td>
											<td><input type="text" class="input_base" id="actId" name="actId" value="${pageDiv eq 'new' ? actId : informerInfo.actId}" style="width:150px;font-weight: bold;"/></td>
											<td class="strong"><span style="color:red">*</span>이름</td>
											<td><input type="text" class="input_base" id="informerName" name="informerName" value="${informerInfo.informerName}" style="width:160px;font-weight: bold;" /></td>
	                                    </tr>
	                                    <tr>
	                                        <td class="strong">사진</td>
	                                        <td colspan="3">
	                                            <input type="hidden" class="input_base" id="localFilePath" name="localFilePath" value="${informerInfo.localFilePath}" style="width:307px;" readonly />
                                                <input type="file" class="input_base" id="file" name="file" onchange="changePicture($(this));" style="width:377px; height: 20px;" />
	                                            <!-- <input type="file" class="input_base" id="selPic" name="selPic" onclick="selectPic();" value="파일선택"/> -->
	                                        </td>
	                                        <td class="strong">활동상태</td>
	                                        <td>
	                                        <!-- <input type="text" class="input_base" id="flageAct" name="flageAct" value="신규가입" style="width:120px;" disabled /> -->
	                                         <input type="text" class="input_base" id="flagAct" name="flagAct" value="${informerInfo.flagAct eq 'Y' ? '위촉' : '해촉'}" style="width:160px;" disabled />
	                                        </td>
	                                    </tr>
                                    </c:if>
                                    <!-- 수정 끝 -->
                                    
                                    	<tr><td colspan='6'><span class="rem-phone" style="position:inherit;">핸드폰/집전화번호/회사번호중 1개는 필수 입력 (전화수신시 기본값은 핸드폰번호로 표출)</span></td></tr>
                                    
                                        <tr>
                                            <td class="strong"><span style="color:red">*</span>핸드폰번호</td>
                                            <td><input type="text" class="input_base" id="phoneCell" name="phoneCell" value="${informerInfo.phoneCell}" style="width:120px;font-weight: bold;" maxlength="13" onkeydown='return onlyNumber(event)' onkeyup='removeChar(event)'/></td>
                                            <td class="strong">집전화번호</td>
                                           	<td><input type="text" class="input_base" id="phoneHome" name="phoneHome" value="${informerInfo.phoneHome}" style="width:150px;" maxlength="13" onkeydown='return onlyNumber(event)' onkeyup='removeChar(event)'/></td>
                                            <td class="strong">회사전화번호</td>
                                            <td><input type="text" class="input_base" id="phoneOffice" name="phoneOffice" value="${informerInfo.phoneOffice}" style="width:160px;" maxlength="13" onkeydown='return onlyNumber(event)' onkeyup='removeChar(event)'/></td>
                                        </tr>
                                        <tr>
                                            <%--  <td class="strong">HAM</td>
                                            <td><input type="text" class="input_base" id="HAM_NO" name="HAM_NO" value="${informerInfo.HAM_NO}" style="width:120px;" /></td> --%>
                                            <td class="strong">TRS</td>
                                            <td><input type="text" class="input_base" id="trsNo" name="trsNo" value="${informerInfo.trsNo}" style="width:120px;" /></td>
                                            <td class="strong">최종학력</td>
                                            <td>
												<input type="text" class="input_base" id="lastSchool" name="lastSchool" value="${informerInfo.lastSchool}" style="width:150px;" />
                                            </td>
                                            <td class="strong">통신원직업</td>
                                            <td>
												<input type="text" class="input_base" id="informerJob" name="informerJob" value="${informerInfo.informerJob}" style="width:160px;" />
                                            </td>
                                        </tr>
                                        <tr>
                                            <td class="strong">주민번호</td>
                                            <td>
                                            	<input type="hidden" class="input_base" id="residentNum" name="residentNum" value="${informerInfo.residentNum}" style="width:50px;" />
                                            	<input type="text" class="input_base" id="residentNum1" name="residentNum1" value="" maxlength="6" style="width:48px;" onkeydown='onlyNumber(this)' onkeyup='onlyNumber(this)'/>                                            	
                                            	<span> - </span>
                                            	<input type="text" class="input_base" id="residentNum2" name="residentNum2" value="" maxlength="7" style="width:49px;" onkeydown='onlyNumber(this)' onkeyup='onlyNumber(this)'/>
                                            </td>
                                            <td class="strong">차량번호</td>
                                            <td><input type="text" class="input_base" id="carNum" name="carNum" value="${informerInfo.carNum}" style="width:150px;" maxlength="15"/></td>
                                            <td class="strong">차량종류</td>
                                            <td><input type="text" class="input_base" id="carType" name="carType" value="${informerInfo.carType}" style="width:160px;" maxlength="15"/></td>
                                        </tr>
                                        <tr>
                                            <td class="strong">주소(개인)</td>
                                            <td colspan="5">
                                            	<input type="button" class="input_base" id="searchAddress1" name="searchAddress1" onclick="searchAddress(1);" style="width:60px;" value="주소검색"/>
                                                <input type="hidden" class="input_base" id="address" name="address" value="${informerInfo.address}" style="width:250px;" />
                                                <input type="hidden" class="input_base" id="addressHome" name="addressHome" value="${informerInfo.addressHome}" style="width:250px;" />
                                                <input type="text" class="input_base" id="adressHome3" name="zipcode" value="${informerInfo.zipcode}" readonly></input>
                                                <input type="text" class="input_base" id="addressHome1" name="addressHome1" value="" style="width:204px;" readonly/>
                                                <input type="text" class="input_base" id="addressHome2" name="addressHome2" value="" style="width:204px;" />
                                                
                                            </td>
                                        </tr>
                                        <tr>
                                            <td class="strong">주소(회사)</td>
                                            <td colspan="5">
                                            	<!-- <iframe id="addressPage2" class="addressPage" src="https://universeport.kr:8014/addressPage2.jsp"></iframe> -->
                                            	<input type="button" class="input_base" id="searchAddress2" name="searchAddress2" onclick="searchAddress(2);" style="width:60px;" value="주소검색"/>
                                                <input type="hidden" class="input_base" id="addressOffice" name="addressOffice" value="${informerInfo.addressOffice}" style="width:250px;"/>
                                                <input type="text" class="input_base" id="addressOffice3" name="zipcode2" value="${informerInfo.zipcode2}" readonly></input>
                                                <input type="text" class="input_base" id="addressOffice1" name="addressOffice1" value="" style="width:204px;" readonly/>
                                                <input type="text" class="input_base" id="addressOffice2" name="addressOffice2" value="" style="width:204px;" />
                                            </td>
                                        </tr>
                                        <tr>
                                            <td class="strong">전달사항</br>(최대500자)</td>
                                            <td colspan="5"><textarea class="table_sel" id="memo" name="memo" cols="50" rows="8" style="width:645px; height:50px;" maxlength="500" >${informerInfo.memo}</textarea></td>
                                        </tr>
                                        <tr>
                                            <td class="strong">메모(100자)</td>
                                            <td colspan="5"><input type="text" class="input_base" id="memo1" name="memo1" value="${informerInfo.memo1}" style="width:647px;" maxlength="100"/></td>
                                        </tr>
                                        <tr>
                                            <td class="strong">추가메모(50자)</td>
                                            <td colspan="5"><input type="text" class="input_base" id="memo2" name="memo2" value="${informerInfo.memo2}" style="width:647px;" maxlength="50"/></td>
                                        </tr>
                                    </table>
                                    <table width="100%" border="0" cellspacing="0" cellpadding="0" class="admin_table">
                                        <tr>
                                            <td class="admin_table_line">생일</td>
                                            <td class="admin_table_line">가입일</td>
                                            <td class="admin_table_line">최종수정일</td>
                                            <td class="admin_table_line">신분증유효기간</td>
                                        </tr>
                                        <tr>
                                            <c:set var="nowDate" value="<%=new java.util.Date() %>"/>
                                            <td>
                                                <input type="text" class="input_base" id="birthday" name="birthday" value="${informerInfo.birthday}" style="width:100px;" maxlength="8" readonly />
                                                <input type="radio" class="input_base" id="birthdayDiv2" name="birthdayDiv" value="+" checked />
                                                <label style="color: #585c5f">양력</label>
                                                <input type="radio" class="input_base" id="birthdayDiv2" name="birthdayDiv" value="-" />
                                                <label style="color: #585c5f">음력</label>
                                            </td><%-- <fmt:formatDate value="${nowDate}" pattern="yyyy-MM-dd" /> --%>
                                            <c:if test="${informerInfo.informerId eq null }">
						                        <td><fmt:formatDate value="${nowDate}" pattern="yyyy-MM-dd" /></td>
						                    </c:if>
						                    <c:if test="${informerInfo.informerId ne null }">
						                        <td><c:out value="${informerInfo.regDate}"/></td>
						                    </c:if>
						                    <c:if test="${informerInfo.informerId eq null }">
                                                <td><fmt:formatDate value="${nowDate}" pattern="yyyy-MM-dd" /></td>
                                            </c:if>
                                            <c:if test="${informerInfo.informerId ne null }">
                                                <td><c:out value="${informerInfo.updDate}"/></td>
                                            </c:if>
                                            <td colspan="2"><input type="text" class="input_base" id="identifiDate" name="identifiDate" value="${informerInfo.identifiDate}" style="width:100px;" maxlength="8" onkeydown='return onlyNumber(event)' onkeyup='removeChar(event)'/>
                                            </td>
                                        </tr>
                                    </table>
                                    
                                 </td>
                            </tr>
                        </table>
                        </form>
                        <!-- 등록버튼 시작-->
                        <div class="btnArg mgt10" style="float: left;">
                            <input class="btnMgt10" onclick="saveInformer();" type="image" src="<c:url value="/images/btn_reget1.png"/>" alt="등록" title="등록"  />
                            <c:if test="${informerInfo.flagAct eq 'Y' }">
                                <input class="btnMgt10" onclick="changeAct('N');" id="informerLeave" type="image" src="<c:url value="/images/btn_haechock.png"/>" alt="해촉" title="해촉"  />
                            </c:if>
                            <c:if test="${informerInfo.flagAct eq 'N' }">
                                <input class="btnMgt10" onclick="changeAct('Y');" id="informerAppoint" type="image" src="<c:url value="/images/btn_wichock.png"/>" alt="위촉" title="위촉"  />
                            </c:if>
                        </div>
                        <div class="btnArg mgt10" style="float: right" >
                            <c:if test="${informerInfo.informerId ne null }">
                                <input class="btnMgt10" onclick="delInformer();" type="image" src="<c:url value="/images/btn_del.png"/>" alt="삭제" title="삭제"  />
                            </c:if>
                            <input class="btnMgt10" onclick="self.close();" type="image" src="<c:url value="/images/btn_cl.png"/>" alt="취소" title="취소"  />
                        </div>
                        <!-- 등록버튼 끝-->
                        
                    </div>
                    <!-- 통신원 상제정보 끝 -->
                </div>
                <!-- 통신원정보 개별상세정보 끝 -->
            </div>
            <!-- //contentWrap -->
        </div>
        <!-- //bodyWrap -->
    </div>
</div>
<script>
$(document).ready(function(){
	console.log("통신원 등록/수정 새창 수정반영?");
    var dates = $('#birthday').datepicker({
        showOn: "both",
        buttonImage: "<c:url value="/images/ico_calendar.gif"/>",
        buttonText: "달력",
        buttonImageOnly: true,
        changeYear: true, // 연도 선택 가능
        changeMonth: true, // 월 선택 가능
        numberOfMonths: 1,
        dateFormat: 'yy-mm-dd',
        monthNames: ['년  1 월','년  2 월','년  3 월','년  4 월','년  5 월','년  6 월','년  7 월','년  8 월','년  9 월','년  10 월','년  11 월','년  12 월',],
        monthNamesShort: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'], // 월 이름 축약형 설정
        dayNamesMin: ['일', '월', '화', '수', '목', '금', '토'],
        showMonthAfterYear:true,
        showAnim: "slide",
        yearRange: '1930:2030',
        defaultDate: new Date( ),
        onSelect: function(selectedDate) {
            var instance = $(this).data("datepicker");
            var date = $.datepicker.parseDate(instance.settings.dateFormat || $.datepicker._defaults.dateFormat, selectedDate, instance.settings); 
            //dates.not(this).datepicker("option", option, date);
            $("img.ui-datepicker-trigger").attr("style", "margin-left:5px;");
          
        }
    });
    
    var dates2 = $('#identifiDate').datepicker({
        showOn: "button",
        buttonImage: "../images/ico_calendar.gif",
        buttonText: "달력",
        buttonImageOnly: true,
        changeMonth: false,
        numberOfMonths: 1,
        dateFormat: 'yy-mm-dd',
        monthNames: ['년  1 월','년  2 월','년  3 월','년  4 월','년  5 월','년  6 월','년  7 월','년  8 월','년  9 월','년  10 월','년  11 월','년  12 월',],
        dayNamesMin: ['일', '월', '화', '수', '목', '금', '토'],
        showMonthAfterYear:true,
        showAnim: "slide",
        defaultDate: new Date( ),
        onSelect: function(selectedDate) {
            var instance = $(this).data("datepicker");
            var date = $.datepicker.parseDate(instance.settings.dateFormat || $.datepicker._defaults.dateFormat, selectedDate, instance.settings);
            //dates.not(this).datepicker("option", option, date);
            $("img.ui-datepicker-trigger").attr("style", "margin-left:5px;");
        }
    });
    $("img.ui-datepicker-trigger").attr("style", "margin-left:5px;");
    
    //상세페이지의 경우 주소 분할
    var tagHome = "${informerInfo.address}";
    var tagOfs = "${informerInfo.addressOffice}";
    var tagRn = "${informerInfo.residentNum}";
    //파일 관련하여 있으면 값 대입
    var tagFile = "${informerInfo.localFilePath}";
    
    	
    if(typeof tagHome !== "undefined" && tagHome !=""){
    	addressSp("addressHome",tagHome,"--");
    }
    if(typeof tagOfs !== "undefined" && tagOfs !=""){
    	addressSp("addressOffice",tagOfs,"--");
    }
    if(typeof tagRn !== "undefined" && tagRn !=""){
    	addressSp("residentNum",tagRn,"-");
    }
    $("#addressOffice1").val(tagOfs.split("--")[0]);
	$("#addressOffice2").val(tagOfs.split("--")[1]);
    
    
    //소속방송국,통신원유형 클릭 시 -> id랑 소속기관 소속기관 세부 갱신
    $("#areaCodeSel , #informerTypeSel").on("change",function(){
    	$("#orgIdSel option").remove();
    	$("#orgSubIdSel option").remove();
    	
    	var ajaxData = ajaxMethod
    	("/informer/reSel.do",
			{
				areaCode:$("#areaCodeSel option:selected").val()
				,informerType:$("#informerTypeSel option:selected").val()
				,sflag:$(this).attr("id")	
			}
		);
    	
    	//소속방송국 경우 ->actid 변경
    	if(typeof ajaxData.newActId !="undefined"){
    		$("#actId").val(ajaxData.newActId);
    	}
    	//지역코드값과 통신원유형으로 2,3을 불러옴
    	
    	var alData1 = ajaxData.sList1;
    	var alData2 = ajaxData.sList2;
    	
    	//셀렉트박스 갱신 하위영역들 2,3
    	if (alData1.length==0) {
    		console.log("alData1.length==0");
    		$("#orgIdSel").append("<option value=''>무소속</option>");
		}else{
			console.log("alData1.length!=0");
			for (var i = 0; i < alData1.length; i++) {
	    		$("#orgIdSel").append("<option value='"+alData1[i].ifmId2+"'>"+alData1[i].ifmName+"</option>");	
			}	
			$("#").append("<option value=''>무소속</option>");
		}
		if (alData2.length==0) {
			console.log("alData2.length==0");
			$("#orgSubIdSel").append("<option value=''>무소속</option>");
		}else{
			console.log("alData2.length!=0");
			for (var i = 0; i < alData2.length; i++) {
	    		$("#orgSubIdSel").append("<option value='"+alData2[i].ifmId3+"'>"+alData2[i].ifmName+"</option>");
			}	
			$("#orgSubIdSel").append("<option value=''>무소속</option>");
		}
		
    });
    
    
    //소속기관 클릭 시 -> 소속기관 세부 갱신
    $("#orgIdSel").on("change",function(){
    	$("#orgSubIdSel option").remove();
    	var ajaxData = ajaxMethod
    	("/informer/reSel.do",
			{
				areaCode:$("#areaCodeSel option:selected").val()
				,informerType:$("#informerTypeSel option:selected").val()
				,orgId:$("#orgIdSel option:selected").val()
				,sflag:$(this).attr("id")
			}
		);
    	//지역코드값과 통신원유형으로 2,3을 불러옴
    	var alData2 = ajaxData.sList2;
    	//셀렉트박스 갱신 하위영역들 2,3
		if (alData2.length==0) {
			$("#orgSubIdSel").append("<option value=''>무소속</option>");
		}else{
			for (var i = 0; i < alData2.length; i++) {
	    		$("#orgSubIdSel").append("<option value='"+alData2[i].ifmId3+"'>"+alData2[i].ifmName+"</option>");
			}	
		}
    });
    
    
  	//자식 iframe으로부터 받은 리스너
	/* window.addEventListener('message', function(e) {
		 //console.log(e.data); // { hello: 'parent' }
		  if(e.data.chId=="1"){
			  $("#addressHome1").val(e.data.ch1);
		  }else if(e.data.chId=="2"){
			  $("#addressOffice1").val(e.data.ch2);
		  }
		  if (typeof e.origin !== 'undefined') {
			console.log("e.origin : "+e.origin);
		  }
	}); */
    
});

//다중 펑션 버튼에 따른 분기
function btnClick(str){
	if(str=="save"){                                 
		saveInformer();  
    } else if(str=="delete"){      
    	delInformer();
    }  else {
    	changeAct(str);
    }
}


function searchAddress(num){
    /* API 미적용하고 DB 적용할 경우를 대비하여 주석 
    var url = '<c:url value="/informer/searchAddress.do"/>?num=' + num;
    var windowName = "주소검색";
    var popupW = 480;  // 팝업 넓이
    var popupH = 600;  // 팝업 높이
    var left = Math.ceil((window.screen.width - popupW)/2);
    var top = Math.ceil((window.screen.height - popupH)/2);
    popObj = window.open(url, windowName, 'width=' + popupW +  ', height=' + popupH +  ', left=' + left +  ', top=' + top + ', toolbar=no, location=no, status=no, menubar=no, scrollbars=no, resizable=no');
     */
    var tagId ="";
   	console.log(tagId);
	new daum.Postcode({
        oncomplete: function(data) { //선택시 입력값 세팅
        	console.log("주소창 : "+data);
        	var chVal = data.address; // 주소 넣기
        	var postVal = data.zonecode;
        	//window.parent.postMessage({ch1: chVal, chId:"1"}, '*');
        	if(num==1){
        		$("#addressHome1").val(chVal);
        		$('#adressHome3').val(postVal);
        	}else{
        		$("#addressOffice1").val(chVal);
        		$('#addressOffice3').val(postVal);
        	}
        }
   }).open();   
}



/**
 * 변경 이력 저장 항목
 *
 * 301 0   신규가입
 * 301 1   소속변경
 * 301 2   해촉
 * 301 3   위촉
 * 301 4   시민->통신원
 * 301 5   일반->방송
 * 301 6   방송->일반
 * 301 7   신분증발급
 */

/**
 * 통신원 저장
 */
function saveInformer(){
	 
	//핸드폰 정규식
	 var regPhone = /^01([0|1|6|7|8|9])-?([0-9]{3,4})-?([0-9]{4})$/;
	 //전화번호 정규식
	 var regCall = /^[0-9]{2,3}-?[0-9]{3,4}-?[0-9]{4}/;
	 //주민번호 정규식
	 var regJumin = /^(?:[0-9]{2}(?:0[1-9]|1[0-2])(?:0[1-9]|[1,2][0-9]|3[0,1]))-[1-4][0-9]{6}$/;
	 
	 // 반드시 입력해야 할 항목이 추가 되면 작성 해야함.(필수항목)
	 if($('#actId').val() == ""){
        alert("통신원 ID를 입력하세요.");
        $('#actId').focus();
        return false;
    }
	 
	if($('#informerName').val() == ""){
        alert("통신원 이름을 입력하세요.");
        $('#informerName').focus();
        return false;
    }
	
	if($('#phoneCell').val()== "" && $('#phoneHome').val()== "" && $('#phoneOffice').val()== ""){
		alert("핸드폰/집/회사 번호중 하나는 필수값입니다");
        $('#phoneCell').focus();
        return false;
	}
	 
	//핸드폰
	if ($('#phoneCell').val() != "" ){
		if(!regPhone.test($('#phoneCell').val())){
	        alert("올바른 핸드폰번호 형식을 입력해주세요");
	        $('#phoneCell').focus();
	        return false;
		}
    } 
	 //집전화
	if ($('#phoneHome').val() != "" ){
		if(!regCall.test($('#phoneHome').val())){
			alert("올바른 전화번호 형식을 입력하세요");
			$('#phoneHome').focus();
			return false;
		}
	}
	 //회사전화
	if ($('#phoneOffice').val() != "" ){
		if(!regCall.test($('#phoneOffice').val())){
			alert("올바른 전화번호 형식을 입력하세요");
			$('#phoneOffice').focus();
			return false;
		}
	}
	//주민번호
	if ($('#residentNum1').val() != "" && $('#residentNum2').val() != "" ){
		$("#residentNum").val(addressChk("residentNum","-"));
		if(!regJumin.test($('#residentNum').val())){
			alert("올바른 주민등록번호 형식을 입력하세요");
			$('#residentNum1').focus();
			return false;
		}
	}
	
	//집주소 ( 모든 조건이 일치한 경우 )
	if ($('#addressHome1').val() != "" && $('#addressHome2').val() != ""){
		$("#address").val(addressChk("address","--"));
		$("#addressHome").val(addressChk("addressHome"," "));
	}
	
	// 집주소 ( 우편번호와 도로명 주소는 입력 된 상태인데, 상세 주소가 적히지 않은 경우 )
	if ($('#addressHome1').val() != "" && $('#addressHome2').val() == "" ) {
		alert("상세 주소를 입력해 주세요.");
		$('#addressHome2').focus();
		return false;
	}
	
	// 집주소 ( 상세 주소는 적혀있는데, 우편번호와 도로명 주소는 입력되지 않은 경우 )
	if($('#addressHome1').val() == "" && $('#addressHome2').val() != "") {
		alert("우편번호 및 도로명 주소를 입력해 주세요");
		return false;
	}
	
	//회사주소
	if ($('#addressOffice1').val() != "" && $('#addressOffice2').val() != ""){
		$("#addressOffice").val(addressChk("addressOffice","--"));
	}
    
	// 회사 주소 ( 우편 번호와 도로명 주소는 입력 된 상태인데, 상세 주소가 적히지 않은 경우 )
	if( $('#addressOffice1').val() != "" && $('#addressOffice2').val() == "" ) {
		alert(" 상세 주소를 입력해 주세요. ");
		$('#addressOffice2').focus();
		return false;
	}
	
	// 회사 주소 ( 상세 주소는 적혀있는데, 우편 번호와 도로명 주소는 입력되지 않은 경우 )
	if( $('#addressOffice1').val() == "" && $('#addressOffice2').val() != "" ) {
		alert(" 우편번호 및 도로명 주소를 입력해 주세요");
		return false;
	}
    
	//통신원 이력관련
	var histCode = '';
    if('${informerInfo.informerId}' == '' || '${informerInfo.informerId}' == null){
    	histCode = '0';
    } else {
    	if('${informerInfo.flagBroad}' == '0' && $('#flagBroad1').is(':checked')){
            histCode = '5';
        } else if('${informerInfo.flagBroad}' == '1' && $('#flagBroad0').is(':checked')){
            histCode = '6';
        }
    	
    	if('${informerInfo.informerType}' == '2' && $('#informerType').val() == '0'){
    		if(histCode != '' && histCode != null){
    			histCode += ',4';    			
    		} else {
    			histCode = '4';
    		}
    	}
    	
    	if('${informerInfo.areaCode}' != $('#areaCodeSel').val()){
    		if(histCode != '' && histCode != null){
                histCode += ',1';              
            } else {
                histCode = '1';
            }
    	}
    }
    
    if($('#honor').is(':checked')){
    	$('#honor').val('Y');
    }else{
    	$('#honor').val('N');
    }
    if($('#flagService').is(':checked')){
    	$('#flagService').val('Y');
    }else{
    	$('#flagService').val('N');
    }
    
    
    var fileChg = false;
    if('${informerInfo.localFilePath}' != $('#localFilePath').val()){
    	fileChg = true;
    	//$('#birthday').val(date);
    }
    
    
    // 주석 처리 시 date에 하이픈 들어감 : 현재는 하이픈 제거 상태
    var date = $('#birthday').val().replace(/-/g,'');
    $('#birthday').val(date);
    
    var date = $('#identifiDate').val().replace(/-/g,'');
    $('#identifiDate').val(date);
    
    let queryString = $("#informerEditFrm").serialize();
    let addStr ="&histCode="+histCode+"&fileChg="+fileChg+"&flagBroad="+$('input[name="flagBroad"]:checked').val()+"&honor="+$('#honor').val()+"&flagService="+$('#flagService').val();
    queryString+=addStr;
   	console.log(queryString);
    var options = {
            url:"/informer/saveInformer.do?"+queryString,
            type:"post",
            dataType: "json",
            data:queryString,
            async : false,
            success: function(res){
                if(res.cnt > 0){
                    alert("저장되었습니다.");
                    opener.search();
                    self.close();
                } else {
                	if(res.badFileType != null){
                		alert("사진파일 첨부는 이미지 파일만 가능합니다.")
                	} else if(typeof res.createFileError !== "undefined" && res.createFileError){
                	    alert("사진파일 저장에 실패했습니다.");
                	} else if(typeof res.msg !== "undefined" && res.msg != null){
                		alert(res.msg);
                	}else {
                		alert("저장에 실패했습니다.");
                	}
                }
            } ,
            error: function(res,error){
                alert("에러가 발생했습니다."+error);
            }
    };
    $('#informerEditFrm').ajaxSubmit(options);
}

/**
 * 해촉, 위촉 수정
 */
function changeAct(flag){
	var histCode = '';
	var actYn = '';
	
    if('${informerInfo.flagAct}' == 'Y' && '${informerInfo.flagAct}' != flag){
    	histCode = '2';
    	actYn = 'N'
    } else if('${informerInfo.flagAct}' == 'N' && '${informerInfo.flagAct}' != flag){
    	histCode = '3';
    	actYn = 'Y'
    }
    let queryString = $("#informerEditFrm").serialize();
   	console.log(queryString);
	var options = {
			url:"/informer/changeAct.do?"+queryString,
            type:"post",
            data: {
            	chgFlagAct:      flag,
            	//areaCode:      $('#areaCodeSel').val(),
            	//honor:         '${informerInfo.honor}',
            	//flagService:  '${informerInfo.flagService}',
            	upCode:   histCode,
            	stopDate:     'Y',
            	pageDiv:       $('#pageDiv').val(),
            },
            dataType: "json",
            async : false,
            success: function(res, html){
            	alert("저장되었습니다.");
                opener.search();
                self.close();
            } ,
            error: function(res,error){
                alert("에러가 발생했습니다."+error);
            }
    };
    $.ajax(options);
}

/**
 * 통신원 삭제
 */
function delInformer(){
	if('${informerInfo.flagAct}' == 'Y'){
		alert("삭제하기전에 해촉을 해주세요.");
		return false;
	}
	
    var options = {
            url:"/informer/deleteInformer.do",
            type:"post",
            data: {
                informerId:   $('#informerId').val()
            },
            dataType: "json",
            async : false,
            success: function(res, html){
                if(res.cnt > 0){
                    alert("삭제되었습니다.");
                    opener.search();
                    self.close();
                } else {
                    alert("삭제에 실패하였습니다.");
                }
            } ,
            error: function(res,error){
                alert("에러가 발생했습니다."+error);
            }
    };
    if(confirm('삭제 하시겠습니까? \n (해당 통신원에 대한 모든 정보가 시스템에서 삭제됩니다)')){
        $.ajax(options);
    }
}

/**
 * 주소 설정
 */
function setAddress(str, target){
	$('#'+target).val(str + ' ');
	$('#'+target).focus();
}

/**
 * 사진 변경 시
 */
function changePicture(obj){
	$('#localFilePath').val((obj.val()).replace("C:\\fakepath\\", ""));
}
</script>
</body>
</html>
