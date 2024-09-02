<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<div id="divIncidentList" style="display: none;">
	<!-- <div id="posi"><a href="#"><img src="../images/ico_home.gif" alt="home" /></a>PD지원 > 유고정보</div> -->
	<!-- contents -->
	<div id="contents">
		<h1>
			<img src="../images/tit_02menu01_no.gif" alt="소통정보"
				onclick="goTab('divTodayList');" /><img
				src="../images/tit_02menu02_on.gif" alt="유고정보" /><img
				src="../images/tit_02menu03_no.gif" alt="CCTV"
				onclick="goTab('divCCTVList');" />
		</h1>
		<!-- 좌측 제보정보조회 -->
		<div class="contentsLeft">
			<div class="pd_linetop"></div>
			<!-- 제보 등록 -->
			<div class="pd_linece">
				<!-- 서브메뉴별 변경레이어 시작-->
				<div class="board_list">
					<!-- 자동검색 영역 시작 -->
					<div class="searchwrap">
						<div>
							<fieldset class="txt_left par15">
								<legend>유고정보 자동로딩 유무</legend>
								[최근] <strong class="blue"><span
									id="divIncidentListdate"></span><span id="divIncidentListtime"></span></strong>
								<input id="divIncidentListautoLoading"
									name="divIncidentListautoLoading" title="자동로딩" type="checkbox"
									onclick="javascript:autoLoading(this);" /> 자동로딩 <select
									id="divIncidentListreloadTime" name="divIncidentListreloadTime"
									class="table_sel" style="width: 60px;" disabled="disabled">
									<option value="10">10초</option>
									<option value="20">20초</option>
									<option value="30">30초</option>
									<option value="60">60초</option>
								</select> 
								<span style="float: right; cursor: pointer;">
								<!-- <img src="../images/btn_excel.gif" alt="" onclick="javascript:excelIncidentList();" /> -->
								</span>
								<!--   <input name="check" title="선택" id="check" type="checkbox" />
                    위치고정
                    <input name="check" title="선택" id="check2" type="checkbox" />
                    여러줄 -->
							</fieldset>
						</div>
					</div>
					<!-- 자동검색 영역 끝 -->
					<div id="IncidentListSearch" class="rounding_wrap3">
						<!-- 검색조건: incidentListSearch.jsp -->
					</div>
					<!-- 검색결과 시작 -->
					<div class="list_result_sc1">
						<table summary="유고정보" border="1" cellpadding="0" cellspacing="0"
							class="list01">
							<caption>유고정보 검색결과</caption>
							<colgroup>
								<col width="25" />
								<col width="30" />
								<col width="30" />
								<col width="110" />
								<col width="40" />
								<col width="80" />
								<col width="30" />
								<col width="*" />
							</colgroup>
							<thead>
								<tr>
									<th scope="col"><input id="incidentAllcheck"
										name="incidentAllcheck" title="체크해제" type="checkbox"
										value="${list.RECEIPT_ID }"
										onclick="javascript:getCheckReset();" /></th>
									<th scope="col">번호</th>
									<th scope="col">방송</th>
									<th scope="col">통제시간</th>
									<th scope="col">유형</th>
									<th scope="col">제보자</th>
									<!-- <th scope="col">구분</th> -->
									<th scope="col" align="left">지도</th>
									<th scope="col">내용</th>
								</tr>
							</thead>
						</table>
					</div>
					<div id="IncidentList" class="list_result_sc5"
						style="border-bottom: 1px solid #b7b7b7;">
						<!-- 유고 정보 list -->
					</div>
					<div class="btnArg">
						<input type="image" src="../images/btn_broad.png" alt="방송" title="방송"
							class="poin" onclick="appendToStudio('frmInciSearch')" />
						<!-- <input type="image" src="../images/btn_pdcancel2.gif" alt="방송취소" title="방송취소" class="poin"  onclick="javascript:goBroadCancel();"/> -->
					</div>
					<!-- 검색결과 끝 -->
				</div>
				<!-- 서브메뉴별 변경레이어 끝-->
			</div>
			<div class="pd_linebottom"></div>
		</div>
	</div>
</div>