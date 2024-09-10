<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<style>
h1 img {
	cursor: pointer;
}
</style>
<script>
	var tabMenu;
	var checkCount = 0;
	var checkId = "";
	var sendTextContent = "";
	var chkList = new Array();
	$(document).ready(function() {
		
/* 		$("#mainDiv").css("width","100vw");
		$("#mainDiv").css("margin-top","0px"); */

		initCaster();

		var todaysDate = currentDate("");
		currentPage = 1;

		$('#TodayListSearch').load('/broadcast/todayListSearch.do');

		var totalAjax = ajaxMethod("/broadcast/countBroadcastList.ajax", {
			"RECEIPT_DAY" : todaysDate,
			"startRow" : currentPage,
			"AREA_CODE" : lgnArea
		});
		totalPage = totalAjax.totalPage;
		$("#resultListTotal_broad span").text(totalAjax.totalSize);
		if (totalAjax.totalSize == 0) {
			$("#broadcastList").load("/receipt/noResult.do");
		} else {
			$("#broadcastList").load("/broadcast/selectBroadcastList.do", {
				"RECEIPT_DAY" : todaysDate,
				"startRow" : currentPage,
				"AREA_CODE" : lgnArea
			});
		}
		//더블클릭 단일방송
		$("#TodayList").on("dblclick", "li", function(e) {
			//console.log("더블클릭1 : "+$(this).attr("id"));
			var tagId = $(this).attr("id").replace('list_id','');
			dbcBroad(authCode,tagId);
		});
		//방송하기
		$("#TodayList").on("click", "li", function(e) {
			console.log("li click event");
			console.log("autoLoading:" + autoLoading);
			console.log("isPause: " + isPause);
			/* if( $(e.target.className == "aired") )
				return; */
			if ($(e.target).is('input:checkbox'))
				return;
			if ($(e.target).is('img'))
				return;

			if (autoLoading) {
				pauseFlagTo(true);
			}

			var checkCount = $("input[name=broadcast_check]:checked").length;
			var chkbox = $(this).find('input:checkbox');

			//체크박스 배열관련
			if (chkbox.prop("checked")) {
				chkbox.prop("checked", false);
				checkId = checkId.replace(chkbox.val() + ",", "");
				//특정 값 삭제
				chkList = chkList.filter((element) => element !== $(this).attr("id"));
			} else {
				$('#todayAllcheck').prop("checked", true);
				chkbox.prop("checked", true);
				checkId = checkId + chkbox.val() + ",";
				chkList.push($(this).attr("id"));
			}
			
			//체크박스 갯수제한 해제
			/* if (checkCount < 4) {
				if (chkbox.prop("checked")) {
					chkbox.prop("checked", false);
					checkId = checkId.replace(chkbox.val() + ",", "");
				} else {
					$('#todayAllcheck').prop("checked", true);
					chkbox.prop("checked", true);
					checkId = checkId + chkbox.val() + ",";
				}
			} else {
				if (chkbox.prop("checked")) {
					chkbox.prop("checked", false);
					checkId = checkId.replace(chkbox.val() + ",", "");
				} else {
					alert("동시방송 가능한 최대갯수를 초과하였습니다.");
				}
			} */

			if (checkId === "") {
				if (isPause) {
					pauseFlagTo(true);
				}
				$('#todayAllcheck').prop("checked", false);
			}
		}).scroll(function() {
			var scrollTop = $(this).scrollTop();
			var innerHeight = $(this).innerHeight();
			var scrollHeight = $(this).prop('scrollHeight');

			if (scrollTop + innerHeight >= scrollHeight) {
				currentPage++;
				getListByScrollPaging(currentPage, todaysDate, totalPage);
			}
		});
		get_current_time();

		$("#TodayList").on('contextmenu', "li", function() {
			return false;
		});

		//단축키
		document.onkeydown = function(e) {
			if (e.which == 17)
				isCtrl = true;
			if (isCtrl)
				functionKeyEvent_braod(e.which);
		}
		document.onkeyup = function(e) {
			if (e.which == 17)
				isCtrl = false;
		}
		
	});
</script>
<!-- 방송 div -->
<div class="broadContainer">
	<!-- ★★★ 금일 접수 리스트 시작 ★★★ -->
	<div id="divTodayList" class="divTodayList">
	
		<!-- 시간/자동로딩/검색결과/방송상태 -->
		<div class="searchwrap">
			<fieldset>
				<legend>금일접수현황 자동로딩 유무</legend> 
				[최근] 
				<strong class="blue"> 
					<span id="divTodayListdate"></span>&nbsp;&nbsp;<span id="divTodayListtime"></span>
				</strong>&nbsp;&nbsp; 
				<label for="divTodayListautoLoading"> 
					<input id="divTodayListautoLoading" name="divTodayListautoLoading" style="margin-bottom: 3px;" type="checkbox" onclick="changeAutoLoadingFlag();" checked /> 자동로딩
				</label> 
				<select id="divTodayListreloadTime" name="divTodayListreloadTime" class="table_sel" style="width: calc(100px + 0.1vw);" onchange="changeIntervalTime(this)">
					<option value="10">10초</option>
					<option value="20">20초</option>
					<option value="30">30초</option>
					<option value="60">60초</option>
				</select> 
				<!-- <span style="float: right; cursor: pointer;"> <img src="../images/btn_excel.gif" alt="" onclick="javascript:excelTodayList();" /></span> -->
			</fieldset>
			
			<p id="resultListTotal_broad">
				<img src="../images/ico_result.gif" /> 검색결과 
				<span style="font-weight: 700;"></span>건
			</p>
			
			<img id="icoBroadExplain" src="../images/ico_broad_ex.png" />
		</div>
		<!-- 검색영역 -->
		<div class="broad_sch">
			<div id="TodayListSearch"></div>
				<!-- 방송버튼  -->
			<div class="btnArg" style="margin:0px;padding:0px;">
				<input type="image" src="../images/btn_broad.png" alt="방송" title="방송"
					 class="pdbtn" onclick="doBroadcast(authCode)" />
			</div>
		</div>
		
		<!-- 방송리스트 header 시작 -->
		<div>
			<ul id="broadcastListHead" class="broadcastListHead">
				<li>
					<span style="width: 29px;"> 
						<input type="checkbox" onclick="getCheckReset()" style="width: 15px; height: 15px;" id="todayAllcheck" name="todayAllcheck">
					</span> 
					<span style="width: 7%;">번호</span> 
					<span style="width: 5%;">상태</span>
					<span style="width: 9%;">제보/방송</span> 
					<span style="width: 10%;">제보유형</span>
					<span style="width: 12%;">유형/제보자</span> 
					<span style="width: 10%;">전화번호</span> 
					<span>내용</span>
					<span style="width: 7%;">방송국</span>
					
				</li>
			</ul>
		</div>
		<!-- 방송리스트 header 끝 -->
	
		<!-- 방송리스트 시작 -->
		<div id="TodayList" class="todayList">
			<div>
				<ul id="broadcastList" class="broadcastList">
					<li style="padding-top: 15px;">결과를 불러오는 중입니다.</li>
				</ul>
			</div>
			<!-- 금일 접수 list: todayList.jsp -->
		</div>
	</div>
	<!-- ★★★ 금일 접수 리스트 끝 ★★★ -->
	
	<!-- ★★★ 유고정보 시작 ★★★ -->
	<jsp:include page="/WEB-INF/jsp/producer/imsInfo.jsp"></jsp:include>
	<!-- ★★★ 유고정보 끝 ★★★ -->
	
	<!-- ★★★ 스튜디오 시작 -->
	<jsp:include page="/WEB-INF/jsp/producer/studio.jsp"></jsp:include>
	<!-- ★★★ 스튜디오 끝 ★★★ -->
	
</div>
