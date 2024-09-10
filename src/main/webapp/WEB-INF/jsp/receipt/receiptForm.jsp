<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<script type="text/javascript" charset="utf-8" src="<%=request.getContextPath()%>/js/receipt/receipt.js"></script>
<link rel="stylesheet" href="<%=request.getContextPath()%>/calender/jquery-ui.css"/>
<script src="<%=request.getContextPath()%>/calender/jquery-ui.js"></script>
<script type="text/javascript" charset="utf-8" src="<%=request.getContextPath()%>/js/jquery.form.js"></script>
<script type="text/javascript" charset="utf-8" src="<%=request.getContextPath()%>/js/timeDate.js"></script>

<script>
	$(function(){
		console.log("제보접수 폼 jsp");
		initReceipt();

		/* 22.03.15 충북요청사항 */
		$("#fsSlt").on("change",function(){
			console.log("폰트사이즈 체인지");
			$("#CONTENT").css("font-size",$("#fsSlt").val());
		});
		
		$("#ui-datepicker-div").remove();
		$('#START_DAY, #END_DAY').datepicker({
			changeMonth: false,
			numberOfMonths: 1,
			dateFormat: 'yy-mm-dd',
			monthNames: ['년  1 월','년  2 월','년  3 월','년  4 월','년  5 월','년  6 월','년  7 월','년  8 월','년  9 월','년  10 월','년  11 월','년  12 월',],
		    dayNamesMin: ['일', '월', '화', '수', '목', '금', '토'],
		    showMonthAfterYear:true,
		    defaultDate: new Date(),
			onSelect: function(selectedDate) {
				var option = this.id == "START_DAY" ? "minDate" : "maxDate";
				var instance = $(this).data("datepicker");
				var date = $.datepicker.parseDate(instance.settings.dateFormat || $.datepicker._defaults.dateFormat, selectedDate, instance.settings);
				dates.not(this).datepicker("option", option, date);
				$("img.ui-datepicker-trigger").attr("style", "margin-left:5px;");
			}
		});
		$("#INFORMER_NONE").on("click", function(){
			console.log("시민제보자 체크 클릭");
			if($(this).is(":checked")){
				console.log("체크됨");
				$("#LOSS_TEL").attr("readonly", false);
				$("#INFORMER_NAME").prop('readonly',false);
			}else{
				console.log("체크안됨");
				$("#LOSS_TEL").attr("readonly", true);
				$("#INFORMER_NAME").prop('readonly',true);
			}
		});
		
		
	});
</script>

<div id="resetDiv">
		<!-- contents -->
		<div id="contents">
			<div class=main-top-div>
				<h1><img src="../images/tit_01menu01_on.gif" alt="제보접수" /><a href="javascript:receivedStatus()"><img src="../images/tit_01menu02_no.gif" alt="금일접수현황" /></a></h1>
				<!-- functionKeys -->
				<img id="functionKeyImg" src="../images/funcKey4.png" />
			</div>
			<!-- 좌측 제보등록 -->
			<div class="contentsLeft">
           		<!-- 제보 등록 시작-->
				<div id="boardLeft" class="board_edit">
					<div id="fsDiv" style="position: absolute;top: 14px;left: 176px;width: 134px;font-size: 15px;font-weight: bold;display: flex;align-items: center;justify-content: space-evenly;">
						글자크기  
						<select id="fsSlt">
							<option value="14px">14px</option>
							<option value="16px">16px</option>
							<option value="18px">18px</option>
							<option value="20px">20px</option>
							<option value="24px">24px</option>
							<option value="28px">28px</option>
							<option value="32px">32px</option>
							<option value="36px">36px</option>
							<option value="40px">40px</option>
							<option value="44px">44px</option>
							<option value="48px">48px</option>
							<option value="52px">52px</option>
							<option value="56px">56px</option>
							<option value="60px">60px</option>
						</select>
					</div>
					<table summary="제보정보작성" border="0" cellpadding="0" cellspacing="0">
						<caption>제보정보작성</caption>
						<colgroup>
							<col width="70" />
							<col width="100" />
							<col width="50" />
							<col width="100" />
							<col width="*" />
						</colgroup>
						<tbody>
							<tr>
							    <th class="txt_leftb pat8" scope="row">접수자</th>
							    <td colspan="1" class="pat8 txt_leftb" style="text-align:left;" >${login.userName}
							    	<input type="hidden" id="RECEPTION_ID" name="RECEPTION_ID" value="${login.userId}" readonly/>
							    	<input type="hidden" id="RECEPTION_NAME" name="RECEPTION_NAME" value="${login.userName}" readonly/>
							    </td>
								<td id="chk_top" class="txt_leftb" style="width: 300px;" colspan="2">
									<input type="checkbox" name="FLAG_STT" id="FLAG_STT" value="Y" style="display:none;"/>
									<input type="hidden" id="MISSED_CALL_ID" name="MISSED_CALL_ID" />
				                	<input type="checkbox" name="FLAG_DISASTOR" id="FLAG_DISASTOR" value="Y" /> 재난제보
				                	<input type="checkbox" name="FLAG_IMPORTANT" id="FLAG_EMERGENCY" value="Y" /> 긴급접수
									<input type="checkbox" name="FLAG_SEND" id="FLAG_REQUEST" value="Y" checked/> 방송요청
								</td>
							</tr>
							<tr>
								<th class="txt_leftb" scope="row">제보내용</th>
								<td colspan="4">
									<textarea class="r_table_sel" name="MEMO" id="CONTENT" onkeyup="lengthCheck(this, 500)"
												placeholder="최대 500자" maxlength="500" cols="50" rows="8" 
												style="width:525px; height:80px; resize:none;"></textarea>
								</td>
							</tr>
							<tr>
		                    <th class="txt_leftb" scope="row">발생구간*</th>
			                    <td colspan="3" class="bodb1_1">
			                    	<!-- <input type="hidden" class="input_sel" name="ARTERY_ID" id="ARTERY_ID" /> -->
			                    	<input type="text" class="input_sel" name="ARTERY_NAME" id="ARTERY_NAME" placeholder="도로명 입력(필수)" 
			                    			style="width:210px; background-color:rgba(0,64,128,0.1);" autocomplete="off" />
			                    	<input type="hidden" class="input_sel" name="ARTERY_ID" id="ARTERY_ID" />
			                    	<input type="text" class="input_sel" name="LANE" id="LANE" style="width:120px;" 
			                    			maxlength="1" placeholder="차선 입력(숫자 입력)" autocomplete="off"  onkeyup="onlyNumber(this)"/>
			                    <img src="../images/btn_sentence.png" alt="제보내용으로삽입" style="position:absolute; right:35px; top:130px; height:89px; width:50px; cursor:pointer;"
			                    			onclick="appendToReceiptContent()" />
			                    </td>
		                    </tr>
		                    <tr>
		                    	<th class="txt_leftb" scope="row" >&nbsp;</th>
		                    	<td colspan="3">
		                    		<!-- F_LINK_ID/T_LINK_ID로 F_NODE_NAME/T_NODE_NAME 가져오기 -->
		                    		<input type="hidden" name="F_LINK_ID" id="F_LINK_ID" />
		                    		<input type="text" class="input_sel" name="F_NODE_NAME" id="F_NODE_NAME" maxlength="30"
		                    				placeholder="시작지점 입력(필수)" style="width:210px; background-color:rgba(0,64,128,0.1);" autocomplete="off" />
		                      		~
		                      		<input type="hidden" name="T_LINK_ID" id="T_LINK_ID" />
		                      		<input type="text" class="input_sel" name="T_NODE_NAME" id="T_NODE_NAME" maxlength="30"
		                      				placeholder="종료지점 입력(필수)" style="width:210px; background-color:rgba(0,64,128,0.1);" autocomplete="off" />
		                      	</td>
		                  	</tr>
		                  	<tr><!-- 후미상황 셀렉트박스 -->
								<th class="txt_leftb" scope="row">&nbsp;</th>
								<td colspan="3">
									<input type="text" class="input_sel" style="width:448px;" name="REPORT_TYPE3" id="REPORT_TYPE3" 
											placeholder="후미상황 입력" maxlength="150" autocomplete="off" />
								</td>
							</tr>
							<tr>
								<th class="txt_leftb" scope="row">제보유형*</th>
								<td colspan="3">
									<select class="r_table_sel" style="width:250px; background-color:rgba(0,64,128,0.1);" name="REPORT_TYPE" id="REPORT_TYPE" 
									        onchange="reportTypeOnChange('REPORT_TYPE','REPORT_TYPE2')">
										<!-- 
											충북교통방송만 제보유형 대 중 표출
											충북교통방송 요청사항으로 본부와 협의 후 결정된 사항
											22년 10월 
										 -->
										<c:if test="${login.regionId eq '100'}">
											<option value="">제보유형(대)</option>						
										</c:if>					
										<c:forEach var="reportFirstVO" items="${reportFirstList}">
											<option value=${reportFirstVO.BAS_SCOD }>
															${reportFirstVO.BAS_NAME }</option>
										</c:forEach>
									</select>
									
									<select class="r_table_sel" style="width:273px; background-color:rgba(0,64,128,0.1);" name="REPORT_TYPE2" id="REPORT_TYPE2"></select>
								</td>
							</tr>
							<tr>
								<th class="txt_leftb" scope="row">연락처</th>
								<td><input type="text" class="input_sel" name="R_TEL" id="LOSS_TEL" style="width: 200px;" maxlength="13" value="" onkeydown='return onlyNumber(event)' onkeyup='removeChar(event)' readonly/></td>
								<td class="txt_leftb">지역구분</td>
								<td colspan="3">
									<select class="r_table_sel" name="REGION_ID" id="AREA_CODE" style="width: 130px;"
															onChange="selectAreaCodeSub()">
										<c:forEach var="areaCodeVO" items="${areaCodeList}">
											<option value=${areaCodeVO.AREA_CODE }>${areaCodeVO.AREA_NAME }</option>
										</c:forEach>
									</select> 
									<select class="r_table_sel" name="AREA_ID" id="AREA_CODE_SUB" style="width: 96px;"></select>
								</td>
							</tr>
							<tr>
								<th class="txt_leftb" scope="row">제보처</th>
								<td colspan="3">
									<select name="REPORTER_TYPE" id="REPORTMEAN_TYPE" class="r_table_sel" style="width: 206px;">
									<option value="">제보처 선택</option>
										<c:forEach var="reportMeanVO" items="${reportMeanTypeList}">
											<option value=${reportMeanVO.ID }>${reportMeanVO.NAME }</option>
										</c:forEach>
									</select>
								</td>
							</tr>
							<tr>
								<th class="txt_leftb" scope="row">X좌표*</th>
								<td><input class="input_sel" type="text" name="X_COORDINATE" id="X_COORDINATE" value="" 
											style="width:200px; background-color:rgba(0,64,128,0.1);" readonly/></td>
								<td class="txt_leftb">Y좌표*</td>
								<td colspan="2"><input class="input_sel" type="text" name="Y_COORDINATE" id="Y_COORDINATE" value="" 
											style="width:200px; background-color:rgba(0,64,128,0.1);" readonly/></td>
							</tr>
							<tr>
								<th class="txt_leftb" scope="row">시작시간</th>
								<td><input class="input_sel" type="text" name="START_DAY" id="START_DAY" style="width: 150px;" readonly /> 
									<input type="text" name="STARTTIMEHH" id="STARTTIMEHH" 
											maxlength="2" style="width:15px; height:20px;" onkeyup="onlyNumber(this)" /> : 
									<input type="text" name="STARTTIMEMI" id="STARTTIMEMI" 
											maxlength="2" style="width:15px; height:20px;" onkeyup="onlyNumber(this)" />
								</td>
								<td class="txt_leftb">종료시간</td>
								<td colspan="2"><input class="input_sel" type="text" name="END_DAY" id="END_DAY" style="width: 150px;" readonly /> 
									<input type="text" name="ENDTIMEHH" id="ENDTIMEHH" 
											maxlength="2" style="width:15px; height:20px;" onkeyup="onlyNumber(this)" /> : 
									<input type="text" name="ENDTIMEMI" id="ENDTIMEMI" 
											maxlength="2" style="width:15px; height:20px;" onkeyup="onlyNumber(this)" />
								</td>
							</tr>
						</tbody>
					</table>
				</div> 
				<!-- 제보 등록 끝--> 
				<!-- 통신원 정보 시작-->
				<div class="board_edit" id="informerDiv" style="margin-top: 12px; height:320px;">
					<table summary="통신원정보" border="0" cellpadding="0" cellspacing="0">
						<caption>통신원정보</caption>
						<colgroup>
							<col width="70" />
							<col width="80" />
							<col width="80" />
							<col width="*" />
						</colgroup>
						<tbody>
							<tr>
								<th colspan="2" class="txt_leftb">
									<div class="rpt-informer"><div class="rpt-ifm-image"></div><h3>통신원 정보</h3></div>
								<th colspan="2">
									<select id="SEARCHTYPE3" name="SEARCHTYPE3" class="r_table_sel" style="width:100px;">
										<option value="1">이름</option>
										<option value="2">ID</option>
										<option value="3">전화번호</option>
									</select> 
									<input type="text" class="input_sel" name="SEARCH_TEXT3" id="SEARCH_TEXT3" placeholder="통신원검색"
											onkeyup="if(event.keyCode == 13)searchInformer(3)" style="width:169px; height:22px;" />
									<img id="rSearch_btn" src="../images/map/btn_sch.png" onclick="searchInformer(3)" />
									<!-- <input type="button" class="rSearch_btn" value="검색" onclick="searchInformer(3)" /> -->
								</th>
							</tr>
							<tr>
								<th class="txt_leftb" scope="row">ID</th>
								<td>
									<input type="hidden" class="input_sel" name="INDIVIDUAL_ID" id="INFORMER_ID" style="width: 120px;" />
									<input type="text" class="input_sel" name="ACT_ID" id="ACT_ID" style="width: 170px;" value="" readonly/>
								</td>
								<td class="txt_leftb mglsot">이름</td>
								<td class="txt_leftb">
									<input type="checkbox" name="INFORMER_NONE" title="자동검색" value="Y" id="INFORMER_NONE" /> 시민제보자
									<input type="text" class="input_sel" name="INDIVIDUAL_NAME" id="INFORMER_NAME" style="width: 170px;" value="" readonly/>
								</td>
							</tr>
							<tr>
								<th class="txt_leftb" scope="row">연락처</th>
								<td><input type="text" class="input_sel" maxlength="13" name="PHONE_CELL" id="PHONE_CELL" value="" style="width:170px;" readonly/></td>
								<td class="txt_leftb mglsot">집전화</td>
								<td><input type="text" class="input_sel" maxlength="13" name="PHONE_HOME" id="PHONE_HOME" value="" style="width:170px;" readonly/></td>
							</tr>
							<tr>
								<th class="txt_leftb" scope="row">주소</th>
								<td colspan="3"><input type="text" class="input_sel" name="ADDRESS_HOME" 
														id="ADDRESS_HOME" style="width:526px;" value="" readonly/>
								</td>
							</tr>
							<tr>
								<th class="txt_leftb" scope="row">유형</th>
								<td><input type="hidden" class="input_sel" name="INDIVIDUAL_TYPE" id="INFORMER_TYPE" /> 
									<input type="text" class="input_sel" name="INFORMER_TYPE_NM" id="INFORMER_TYPE_NM" style="width:170px;" value="" readonly/></td>
								<td class="txt_leftb mglsot">소속</td>
								<td><input type="hidden" class="input_sel" name="ORG_ID" id="ORG_ID" /> 
									<input type="hidden" class="input_sel" name="ORG_SUB_ID" id="ORG_SUB_ID" /> 
									<input type="text" class="input_sel" name="ORG_NAME" id="ORG_NAME" style="width:130px;" readonly/> 
									<input type="text" class="input_sel" name="ORG_SUB_NAME" id="ORG_SUB_NAME" style="width:122px;" readonly/>
								</td>
							</tr>
							<tr>
								<th class="txt_leftb" scope="row">제보건수</th>
								<td colspan="3"><span class="txt_leftb mglsot" style="padding-left:0;">현재달</span>
									<input type="text" class="input_sel email" id="STAT1" name="STAT1" value="" readonly/>건 
									<span class="txt_leftb mglsot">이전달</span> 
									<input type="text" class="input_sel email" id="STAT2" name="STAT2" value="" readonly/>건 
									<span class="txt_leftb mglsot">전전달</span>
									<input type="text" class="input_sel email" id="STAT3" name="STAT3" value="" readonly/>건
									&nbsp;&nbsp;불량제보자
									<input type="checkbox" value="Y" name="FLAG_BLACKLIST" id="FLAG_BLACKLIST"/> 
								</td>
							</tr>
							<tr>
								<th class="txt_leftb" scope="row">전달사항</th>
								<td colspan="3">
									<textarea class="r_table_sel" name="MEMO_INFORMER" id="MEMO" rows="5" cols="60" style="resize:none; width:470px; height:70px;"></textarea>
									<button type="button" id="btn_informerSend" class="noCursor" onclick="saveNewInformerMemo()" disabled>
										<img src="../images/ico_informerSend.png" style="width:50px; height:78px;"/>
									</button>
								</td>
							</tr>
						</tbody>
					</table>
				</div> 
				<!-- 통신원 정보 끝-->
				<div class="edit_bottom">
				<!-- 등록버튼 시작-->
					<div class="btnArg" style="position:absolute;top: 690px;left:220px;">
						<img src="../images/btn_save1.png" alt="확인" title="등록"
							class="poin" onclick="saveReceipt()" style="cursor: pointer;width: 92px;height: 30px;" /> 
						<img src="../images/btn_reset1.png" alt="내용지우기" title="내용지우기"
							class="poin" onclick="clearReceiptForm()" style="cursor: pointer;width: 92px;height: 30px;" />
						<img src="../images/tempsave_btn.png" alt="임시저장" title="임시저장"
							class="poin" onclick="tempsave()" style="cursor: pointer;width: 92px;height: 30px;" />
					</div> 
				<!-- 등록버튼 끝-->
				</div>
			</div>
		</div>
		<!-- //contents -->
	</div>
	
	
	
	
<script defer>
//임시저장된 목록 불러오기
tempsaveList();
</script>	