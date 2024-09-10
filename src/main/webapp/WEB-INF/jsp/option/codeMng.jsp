<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<script type="text/javascript">

$(document).ready(function(){
	console.log("코드관리 메인 진입");
	$("#areaDivsel").hide();
	$("#areaOptSel").addClass("disabledbutton");
	var mUrl='${chgUrl}';
	$('#tabMenu').keydown(function(e) {
		if(e.keyCode==13){
			e.preventDefault();
			//기존 엔터버튼 누를 시 화면누락 방지 및 추가 및 수정 버튼클릭과 동일한 효과를 줌(트리거)
			if(typeof $('input[id^="chgTypeCode"]').val() !== "undefined"){
				$('input[id^="chgTypeCode"]').parent().find("img").trigger("click");
			}
			if(typeof $('input[id^="newTypeCode"]').val() !== "undefined"){
				$('input[id^="newTypeCode"]').parent().find("img").trigger("click");
			}
		}
	});
	$('#tabMenu').load(mUrl+'?pageFlag=code&parent_code=100');
	//$('#tabMenu').load('/option/reportType/main.do?pageFlag=code&parent_code=100');
	
	$("#areaOptSel").on("change",function(){
		selArea=$("#areaOptSel option:selected").val();
		$('#divTypeCode2').empty();
		$('#divTypeCode3').empty();
	});
});



</script>
	<div id="contentWrap">
		<%-- <div id="posi"><img src="../images/ico_home.gif" alt="home" /><c:out value="정보관리 > 코드관리" /></div> --%>
		<!-- contents -->
		<div id="contents">
			<h1 class='content-title'>코드관리</h1>
			<!-- board_list -->
			<div class="board_list" id="subTabMenu">
			<!-- 서브메뉴 탭영역 시작 -->
				<div class="board_list">
				<!-- 서브메뉴 탭영역 시작 -->
					<div class="gnb_tab">
						<ul class="lst_tab" >
							<li class="on" id="tab1"><span onclick="chgTabMenu('1')" style="cursor: pointer;"><c:out value="제보유형" /></span></li>
							<li class="ns"></li>
							<li id="tab2"><span onclick="chgTabMenu('2')" style="cursor: pointer;"><c:out value="통신원유형" /></span></li>
							<li class="ns"></li>
							<li id="tab3"><span onclick="chgTabMenu('3')" style="cursor: pointer;"><c:out value="인근도시" /></span></li>
							<li class="ns"></li>
							<li id="tab4"><span onclick="chgTabMenu('4')" style="cursor: pointer;"><c:out value="도로관리" /></span></li>
							<li class="ns"></li>
							<li id="tab5"><span onclick="chgTabMenu('5')" style="cursor: pointer;"><c:out value="번호관리" /></span></li>
							<li class="ns"></li>
							<li id="tab6"><span onclick="chgTabMenu('6')" style="cursor: pointer;"><c:out value="색상관리" /></span></li>
						</ul>
						<table id="areaDivsel" style="float:right;">
							<tr>
								<td>
									<span>방송국선택 : </span>
								</td>
								<td>
									<select class="table_sel" id="areaOptSel" name="areaCode">
			                            <c:forEach var="informerRegion" items="${informerRegionList }" varStatus="idx">
			                                <option value="${informerRegion.areaCode}" ><c:out value="${informerRegion.areaName}"/></option>
			                            </c:forEach>
			                        </select>
								</td>
							</tr>
						</table>
					</div>
					<form id="tabMenuFrm" >
					<!-- 탭 메뉴 영역 -->
					<div id="tabMenu"></div>
					</form>
				</div>
			</div>
		</div>
	<!-- //contents -->
	</div>
