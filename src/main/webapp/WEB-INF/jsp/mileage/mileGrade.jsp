<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%
	//한달 전
	Calendar mon = Calendar.getInstance();
	mon.add(Calendar.MONTH, -1);
	System.out.println("%%%%%%%%%%%%%%%% : " + mon);
	String beforeMonth = new java.text.SimpleDateFormat("YYYYMMdd").format(mon.getTime());
	System.out.println("%%%%%%%%%%%%%%%% : " + beforeMonth);

	//2
	Date monthAgo = new Date(new Date().getTime() - 60 * 60 * 24 * 1000 * 30L);
	System.out.println("%%%%%%%%%%%%%%%% : " + monthAgo);
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style>
	
	.gradeBtn {
			width: 70px;
		    height: 22px;
		    margin-left: 30px;
		    border-radius: 5px;
		    text-align: center;
		    padding-top:5px;
		    background-color: #fa5a5a;
		    color: white;
		    box-shadow: 0 10px 20px rgba(0, 0, 0, 0.19), 0 6px 6px rgba(0, 0, 0, 0.23);
		    cursor : pointer;
	}
</style>
<script>
$(document).ready(function(){

	// 리스트 불러오기
	init();

});

function init() {
	$('#searchType').val('noSearch');
	search();
}


function search() {
	var options = {
            url:"/mileage/gradeList.do",
            type:"post",
            target: '#listDiv',
            success: function(data){

            },
            error: function(res,error){
                alert("에러가 발생했습니다."+error);
            }
    };
	
    $('#searchFrm').ajaxSubmit(options);
}


//페이지 이동
function changePage(url){
	
	if(url == 'allMile') {
		$.ajax
		(
			{
				type : "post" ,
				url : "/mileage/allMile.do" ,
				dataType : "html" ,
				cache : false ,
				success:function(html){
					$('#contentWrap').html(html);
				} ,
	
				error:function(data,error){
					alert("시스템에 문제가 생겼습니다." + data);
				}
	
			}
		);
	} else if(url == 'paymentMile'){
		$.ajax
		(
			{
				type : "post" ,
				url : "/informer/mileage/mileageMain.do" ,
				dataType : "html" ,
				cache : false ,
				success:function(html){
					$('#contentWrap').html(html);
				} ,
	
				error:function(data,error){
					alert("시스템에 문제가 생겼습니다." + data);
				}
	
			}
		);
	} else {
		$.ajax
		(
			{
				type : "post" ,
				url : "/mileage/mileGrade.do" ,
				dataType : "html" ,
				cache : false ,
				success:function(html){
					$('#contentWrap').html(html);
				} ,
	
				error:function(data,error){
					alert("시스템에 문제가 생겼습니다." + data);
				}
	
			}
		);
	}
}
</script>
</head>
<body>
<div id="contentWrap">
	<form id="searchFrm" name="searchFrm">
		<div id="searchDiv">
			<div id="contents">
				<h1 class='content-title'>등급 조회</h1>
				<!-- board_list -->
				<div class="board_list">
					<!-- 서브메뉴 탭영역 시작 -->
					<div class="gnb_tab">
						<ul class="lst_tab">
							<li><a href="javascript:changePage('paymentMile')">마일리지 반영</a></li>
							<li class="ns"></li>
							<li><a href="javascript:changePage('allMile')">총 마일리지 조회</a></li>
							<li class="ns"></li>
							<li class="on"><a href="javascript:changePage('grade')">등급 조회</a></li>
						</ul>
					</div>
					<div style="position: absolute;right: 0px;top: 52px;">
				      <%--  <select id="areaOptSel" name="AREA_CODE">
                           <c:forEach var="informerRegion" items="${informerRegionList}" varStatus="idx">
                               <option value="${informerRegion.areaCode}" ><c:out value="${informerRegion.areaName}"/></option>
                           </c:forEach>
						</select> --%>
					</div>
					
					<!-- 검색조건 영역 시작 -->
					<div class="rounding_wrap mgt10">
						<div class="wrap_top"></div>
						<div class="wrap_center">
							<table class="tbSch">
					            <tr>
				                    <td>
                                        <select class="table_sel"  style="width:100px;" id="areaCodeSel" name="areaCode">
                                        	 <c:choose>
                                             		<c:when test="${fn:length(informerAreaList)==1}">
                                             	
	                                             	</c:when>
	                                             	<c:otherwise>
														<option value="" selected><c:out value="소속"/></option>	
	                                             	</c:otherwise>
	                                         </c:choose>
                                             <c:forEach var="informerArea" items="${informerAreaList}">
                                                 <option value="${informerArea.areaCode}" 
                                                  <c:if test="${informerArea.areaCode eq informerInfo.areaCode}">selected</c:if>>
														${informerArea.areaName}
                                                 </option>
                                             </c:forEach>
                                         </select>
				                    </td>
                                   	<td id="tdItype">
                                     	<select class="table_sel" style="width:120px;" id="informerTypeSel" name="informerType">
                                     		 <option value="" selected><c:out value="유형"/></option>
                                             <c:forEach var="informerType" items="${informerTypeList}">
                                                 <option value="${informerType.ifmId1}" <c:if test="${informerType.ifmId1 eq informerInfo.informerType}">selected</c:if>>${informerType.ifmName}</option>
                                             </c:forEach>
                                         </select>
                                     </td>
                                         <td id="tdOrgId">
											<select class="table_sel"  style="width:165px;" id="orgIdSel" name="orgId">
												<option value="" selected><c:out value="소속기관"/></option>
                                             </select>
                                         </td>
				                    <td>
				                        <select class="table_sel" id="searchActYn" name="flagAct">
				                            <option value="" ><c:out value="활동여부"/></option>
				                        </select>
				                    </td>
				                    <td>
				                        <select class="table_sel" id="searchType" name="searchType">
				                            <option value="actId" ><c:out value="제보자ID"/></option>
				                            <option value="informerName" ><c:out value="이름"/></option>
				                            <option value="phoneCell" ><c:out value="전화번호"/></option>
				                        </select>
				                    </td>
				                    <td>
				                        <input class="input_base" type="text" id="searchValue" name="searchValue"  onkeyup="if(event.keyCode == 13)search();"/>
				                    </td>
				                    <td>
				                        <img src="../images/btn_search.gif" onclick="search();" style="cursor:pointer"  />
				                    </td>
				                </tr>
				            </table>
						</div>
						<div class="wrap_bottom"></div>
					</div>
				</div>
				<!-- list -->
				<div id="listDiv"></div>
			</div>
		</div>
		<!-- <input type="hidden" name="AW_NAME" id="AW_NAME" /> -->
	</form>
</div>
</body>
</html>