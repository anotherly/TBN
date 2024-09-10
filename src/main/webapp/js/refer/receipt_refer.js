var reservationCnt = 0;

function openMemo(){
	if(memowin == null || memowin.closed){
		memowin = window.open('<c:url value="/memo/memo.do"/>','memo','width=305 height=410 toolbar=no status=no directories=no scrollbars=0 location=no resizable=no menubar=no screenX=10 left=100 screenY=10 top=10');
	}else{
		memowin.focus();
	}
}
//전화가 왔을때 전화번호 등록 (못 받을경우 부제중으로 표시 하기 위한 이벤트)
//was쪽에는 없고 cid쪽에 있는것으로 추정...
	//전화를 받지 못했을때 부재중 리스트 
	function setConnectedList(RcvTime, UserID, Ext, CallerID) {
		$.ajax({
			type : "post",
			url : "<c:url value='/receipt/setConnectedList.do' />",
			dataType : "html",
			data : "RcvTime=" + RcvTime + "&CallerID=" + CallerID,
			cache : false,
			success : function(html) {
				$("#setConnectedDiv").html(html);
			},

			error : function(data, error) {
				////alert("시스템에 문제가 생겼습니다." + data);
			}

		});
	}

	//이벤트 발생 -> 전화 통화 목록에서 해당 고객 전화 번호를 찾아서 제거한다. (전화번호를 기준으로 한다)
	function onConnected(RcvTime, UserID, Ext, CallerID) {
		/* 	alert(RcvTime);
		 if(RcvTime == ''){
		 RcvTime = rTime;
		
		 } */
		$.ajax({
			type : "post",
			url : "<c:url value='/receipt/checkDialExt.do' />",
			dataType : "json",
			data : "Ext=" + Ext,
			cache : false,
			success : function(data) {
				if (data.msg == "success") {
					addDial(RcvTime, UserID, Ext, CallerID);
				}
			},

			error : function(data, error) {
				////alert("시스템에 문제가 생겼습니다." + data);
				return false;
			}

		});

	}

	//번호로 시간 으로 부재중 리스트 삭제
	function addDial(RcvTime, UserID, Ext, CallerID) {
		//	alert(RcvTime);
		$.ajax({
			type : "post",
			url : "<c:url value='/receipt/setConnected.do' />",
			dataType : "html",
			data : "RcvTime=" + RcvTime + "&Ext=" + Ext + "&CallerID="
					+ CallerID,
			cache : false,
			success : function(html) {
				$("#setConnectedDiv").html(html);
				//전화번호로 통신원정보를 가져온다..
				getInformerInfoTell(CallerID);
				rTime = "";
			},

			error : function(data, error) {
				////alert("시스템에 문제가 생겼습니다." + data);
			}

		});
	}

	//제보자 정보 가져오기 전화번호로 조회
	function getInformerInfoTell(CallerID) {
		$.ajax({
			type : "post",
			url : "<c:url value='/receipt/InformerInfoTell.do' />",
			dataType : "html",
			data : "CallerID=" + CallerID,
			cache : false,
			success : function(html) {
				$("#informerDiv").html(html);
			},

			error : function(html, error) {
				//alert("시스템에 문제가 생겼습니다." + error);
			}

		});
	}

	//이벤트 방생 -> 장애 메시지를 표시하면서 재 로그인해야만 이벤트를 받을 수 있음을 통지한다.   ?? 리로드 ? ?
	function SocClose(Message) {
		alert(Message);
		Obj.StopListen();
		Obj.StartListen();
	}

	//v_LinkID 데이터 호출
	function addMapX_Y() {
		$("#X_COORDINATE").val(v_X_COORDINATE);
		$("#Y_COORDINATE").val(v_Y_COORDINATE);
		$("#v_LinkID").val(v_LinkID);

		$.ajax({
			type : "post",
			url : "<c:url value='/receipt/selectNode.do' />",
			dataType : "json",
			data : "LINKID=" + v_LinkID,
			cache : false,
			success : function(data) {
				if (data.msg == "success") {
					$("#ARTERY_ID").val(data.resultData.ARTERY_ID);
					$("#ARTERY_NAME").val(data.resultData.ARTERY_NAME);
					$("#F_NODE_NAME").val(data.resultData.F_NODE_NAME);
					$("#T_NODE_NAME").val(data.resultData.T_NODE_NAME);
					$("#SPEED").val(data.resultData.SPEED);
					$("#REPORTMEAN_TYPE").val('3');
					$("#OFFER_CODE").val('T');
				}
			},

			error : function(data, error) {
				//alert("시스템에 문제가 생겼습니다." + data);
			}

		});

	}

	// contents reset
	function resetContents() {
		$.ajax({
			type : "post",
			url : "<c:url value='/receipt/receiptReset.do' />",
			dataType : "html",
			cache : false,
			success : function(html) {
				$("#resetDiv").html(html);
				startDay = new Date();
				setDate();
				//clickMenu('tabs');
				codeOnChange('REPORT_TYPE', 'REPORT_TYPE2');
				regionIdOnChange();
			},

			error : function(html, error) {
				//alert("시스템에 문제가 생겼습니다." + error);
			}

		});
	}

	//제보유형 대 중 소 변경시
	function codeOnChange(thisInformetTypeId, changeInformetTypeId) {
		var paramObject = $("#" + thisInformetTypeId);
		var chageObject = $("#" + changeInformetTypeId);
		$.ajax({
			type : "post",
			url : "<c:url value='/receipt/codeChange.do' />",
			dataType : "html",
			data : "REPORT_TYPE=" + paramObject.val(),
			cache : false,
			success : function(html) {
				chageObject.html(html);
				chageObject.change();
			},

			error : function(html, error) {
				//alert("시스템에 문제가 생겼습니다." + error);
			}

		});
	}

	//저보등록
	function save() {
		//돌발접수인지 확인
		var reportType = $("#REPORT_TYPE").val();
		console.log("reportType : " + reportType);
		console.log("reportVal : " + $("#" + reportType).val());
		if ($("#" + reportType).val() == 1) {
			$("#INCIDENT_FLAG").val("Y");
		} else {
			$("#INCIDENT_FLAG").val("N");
		}
		//접수내용이 255자를 넘었는지 확인
		var cntByte = 0;
		var chletter;
		var str = $("#contentsTextArear").val();
		if ($("#contentsTextArear").val().length == 0) {
			alert("제보내용을 입력하세요");
			return;
		}
		for (var i = 0; i < $("#contentsTextArear").val().length; i++) {
			chletter = str.charAt(i);
			if (escape(chletter) > 4) {
				cntByte += 2;
			} else {
				cntByte++;
			}
		}
		if (cntByte > 250) {
			alert("250자 까지 만 입력하실 수 있습니다");
			return;
		}

		cntByte = 0;
		chletter;
		str = $("#twitterContents").val();
		for (var i = 0; i < $("#twitterContents").val().length; i++) {
			chletter = str.charAt(i);
			if (escape(chletter) > 4) {
				cntByte += 2;
			} else {
				cntByte++;
			}
		}

		//SNS내용이 140자를 넘었는지 확인
		if (cntByte > 140) {
			alert("SNS는 140자를 넘을 수 없습니다.");
			return;
		}

		if ($("#DLS_TEXT").val().length > 0) {
			$("#FLAG_DMBSEND").val("Y");
		} else {
			$("#FLAG_DMBSEND").val("N");
		}

		if ($("#INFORMER_USER_TYPE").is(":checked")) {
			$("#INFORMER_TYPE").val("2");
		}
/////////////////////////////시민제보자체크 없거나 통신원코드 없으면 안됨///////////////////////
		if (!$("#INFORMER_USER_TYPE").is(":checked")
				&& $("#ACT_ID").val() == '') {
			alert("통신원 정보가 없습니다.");
			return;
		}

		if ($("#FLAG_TWITTER").is(":checked")) {
			$("#REAL_TWITTER").val("Y");
		} else {
			$("#REAL_TWITTER").val("N");
		}

		$.ajax({
			type : "post",
			url : "<c:url value='/receipt/receiptSave.do' />",
			dataType : "json",
			data : $("#receiptFrm").serialize(),
			cache : false,
			success : function(data) {
				if (data.msg == "success")
					alert("등록되었습니다.");
				if ($("#FLAG_EMERGENCY").is(":checked")) {
					$("#RECEIPT_ID").val(data.RECEIPT_ID);
					addEmergency();
				}
				resetContents();
			},

			error : function(data, error) {
				//alert("시스템에 문제가 생겼습니다." + data);
			}

		});
	}

	//제보유형 내용 병합
	function addContents() {
		var str;
		//돌발접수인지 확인
		var reportType = $("#REPORT_TYPE").val();
		if ($("#" + reportType).val() == 1) {
			str = "<" + $.trim($("#REPORT_TYPE option:selected").text()) + ">"
					+ $("#contentsTextArear").val();
		} else {
			str = $("#contentsTextArear").val();
		}
		if ($("#contentsTextArear").val() != '') {
			str += "\n";
		}
		if ($("#ARTERY_NAME").val() != '') {
			str += $("#ARTERY_NAME").val();
		}
		str += " ";
		if ($("#F_NODE_NAME").val() != '') {
			str += $("#F_NODE_NAME").val();
			str += "에서 ";
		}
		if ($("#T_NODE_NAME").val() != '') {
			str += $("#T_NODE_NAME").val();
			str += "까지 ";
		}
		if ($("#SPEED").val() != '') {
			str += "시속 ";
			str += $("#SPEED").val();
			str += "Km/h 속도로 ";
		}

		str += $.trim($("#REPORT_TYPE3 option:selected").text());
		//alert(str);
		$("#contentsTextArear").val(str);
	}

	//간략설명 DLS 추가
	function addDlsText() {
		var str;
		str = $("#contentsTextArear").val();
		str = str.split('\n').join(' ').substr(0, 50);
		$("#DLS_TEXT").val(str);
	}

	//예약접수
	function addReservation() {
		//예약접수가 비어있는곳을 찾는다
		reservationCnt = getReservationBoolean();
		$("#reservationSearch" + reservationCnt).val("");
		$("#reservationUserId" + reservationCnt).val($("#INFORMER_ID").val());
		$("#reservationUserName" + reservationCnt).val(
				$("#INFORMER_NAME").val());
		$("#reservationContents" + reservationCnt).val(
				$("#contentsTextArear").val());
		$("#lableUserId" + reservationCnt).html($("#ACT_ID").val());
		$("#lableUserName" + reservationCnt).html($("#INFORMER_NAME").val());

		resetContents();
	}

	//예약접수가 비어있는곳을 찾는다
	function getReservationBoolean() {
		for (var i = 0; i < 3; i++) {
			if ($("#reservationUserId" + i).val() == ""
					&& $("#reservationUserName" + i).val() == ""
					&& $("#reservationContents" + i).val() == "") {
				return i;
				break;
			}
		}
		alert("3건 이상 등록이 불가능 합니다");
	}

	//예약접수 불러오기
	function reservationSend(arrayNum) {
		if ($("#reservationUserId" + arrayNum).val() != ""
				|| $("#reservationUserName" + arrayNum).val() != ""
				|| $("#reservationContents" + arrayNum).val() != "") {
			//초기화 후
			$.ajax({
				type : "post",
				url : "<c:url value='/receipt/receiptReset.do' />",
				dataType : "html",
				cache : false,
				success : function(html) {
					$("#resetDiv").html(html);
					startDay = new Date();
					setDate();
					//clickMenu('tabs');
					codeOnChange('REPORT_TYPE', 'REPORT_TYPE2');
					regionIdOnChange();

					$("#contentsTextArear").val(
							$("#reservationContents" + arrayNum).val());
					$("#twitterContents").val($("#contentsTextArear").val());
					if ($("#reservationUserId" + arrayNum).val() != '') {
						$.ajax({
							type : "post",
							url : "<c:url value='/receipt/informerInfo.do' />",
							dataType : "html",
							data : "INFORMER_ID="
									+ $("#reservationUserId" + arrayNum).val(),
							cache : false,
							success : function(html) {
								$("#informerDiv").html(html);
							},

							error : function(html, error) {
								//alert("시스템에 문제가 생겼습니다." + error);
							}

						});
					}

					$("#reservationCheck" + reservationCnt).attr("checked",
							false);
					$("#reservationSearch" + arrayNum).val("");
					$("#reservationUserId" + arrayNum).val("");
					$("#reservationUserName" + arrayNum).val("");
					$("#reservationContents" + arrayNum).val("");
					$("#lableUserId" + arrayNum).html("");
					$("#lableUserName" + arrayNum).html("");
				},

				error : function(html, error) {
					//alert("시스템에 문제가 생겼습니다." + error);
				}

			});
		}

	}

	//통신원 정보 가져오기
	function setInformerInfo() {
		$.ajax({
			type : "post",
			url : "<c:url value='/receipt/informerInfo.do' />",
			dataType : "html",
			data : "INFORMER_ID=" + $("#reservationUserId" + arrayNum).val(),
			cache : false,
			success : function(html) {
				$("#informerDiv").html(html);
			},

			error : function(html, error) {
				//alert("시스템에 문제가 생겼습니다." + error);
			}

		});
	}

	//지역구분 변경시
	function regionIdOnChange() {
		$.ajax({
			type : "post",
			url : "<c:url value='/receipt/getUseArea.do' />",
			dataType : "html",
			data : "REGION_ID=" + $("#USE_AREA").val(),
			cache : false,
			success : function(html) {
				$("#AREA").html(html);
			},

			error : function(html, error) {
				//alert("시스템에 문제가 생겼습니다." + error);
			}

		});
	}

	//통신원정보 저장
	function informerSave() {

		if ($("#PHONE_CELL").val() != '') {
			var number = $("#PHONE_CELL").val().split('-').join('');
			if (number.length > 11) {
				alert("휴대폰 번호가 최대 길이를 초과 했습니다.");
				return;
			}
		}

		if ($("#PHONE_HOME").val() != '') {
			var number = $("#PHONE_HOME").val().split('-').join('');
			if (number.length > 11) {
				alert("집 전화번호가 최대 길이를 초과 했습니다.");
				return;
			}
		}

		$.ajax({
			type : "post",
			url : "<c:url value='/receipt/informerSave.do' />",
			dataType : "json",
			data : $("#receiptFrm").serialize(),
			cache : false,
			success : function(data) {
				if (data.msg == "success")
					alert("저장되었습니다.");
			},

			error : function(data, error) {
				//alert("시스템에 문제가 생겼습니다." + data);
			}

		});

	}

	//재난접수 체크시 팝업
	function addEmergency() {
		var url = '<c:url value="/receipt/popupAddEmergency.do"/>';
		var windowName = "EMERGENCY";
		var popupW = 450; // 팝업 넓이
		var popupH = 650; // 팝업 높이
		var left = Math.ceil((window.screen.width - popupW) / 2);
		var top = Math.ceil((window.screen.height - popupH) / 2);

		if ($("#FLAG_EMERGENCY").is(":checked")) {
			popObj = window
					.open(
							url,
							windowName,
							'width='
									+ popupW
									+ ', height='
									+ popupH
									+ ', left='
									+ left
									+ ', top='
									+ top
									+ ', toolbar=no, location=no, status=no, menubar=no, scrollbars=no, resizable=no');
		}

		receiptFrm.method = "post";
		receiptFrm.action = url;
		receiptFrm.target = "EMERGENCY";
		receiptFrm.submit();
		$("#RECEIPT_ID").val("");

	}

	//금일접수현황 팝업
	function receivedStatus() {
		var url = '<c:url value="/receipt/receivedStatus.do"/>';
		var windowName = "금일접수현황";
		var popupW = 900; // 팝업 넓이
		var popupH = 700; // 팝업 높이
		var left = Math.ceil((window.screen.width - popupW) / 2);
		var top = Math.ceil((window.screen.height - popupH) / 2);

		popObj = window
				.open(
						url,
						windowName,
						'width='
								+ popupW
								+ ', height='
								+ popupH
								+ ', left='
								+ left
								+ ', top='
								+ top
								+ ', toolbar=no, location=no, status=no, menubar=no, scrollbars=no, resizable=no');
	}

	//sns 체크후 글내용 입력
	function twitterChange() {
		//if($("#FLAG_TWITTER").is(":checked")){
		$("#twitterContents").val($("#contentsTextArear").val());
		$("#twitterContents").val($("#twitterContents").val() + ' #TbnKorea');

		//}else{
		//	$("#twitterContents").val("");
		//}
	}

	//통신원정보
	function popInformation() {
		if ($("#SEARCH_TEXT").val() == '')
			return;
		var url = '<c:url value="/receipt/popInformation.do"/>?SEARCH_TEXT='
				+ escape(encodeURIComponent($("#SEARCH_TEXT").val()))
				+ "&methodType=get&searchType=" + $("#searchType").val();
		var windowName = "통신원정보";
		var popupW = 600; // 팝업 넓이
		var popupH = 450; // 팝업 높이
		var left = Math.ceil((window.screen.width - popupW) / 2);
		var top = Math.ceil((window.screen.height - popupH) / 2);

		var popObj = window
				.open(
						url,
						windowName,
						'width='
								+ popupW
								+ ', height='
								+ popupH
								+ ', left='
								+ left
								+ ', top='
								+ top
								+ ', toolbar=no, location=no, status=no, menubar=no, scrollbars=no, resizable=no');

		/*
		receiptFrm.target="통신원정보";
		receiptFrm.action = url;
		receiptFrm.method="post";
		receiptFrm.submit();
		 */

	}

	//조회
	function getinformerSearch(num) {
		//	alert(num);
		var searchValue;
		if ($("#reservationSearch" + num).val() == '')
			return;
		searchValue = "SEARCH_TEXT=" + $("#reservationSearch" + num).val();
		searchValue += "&searchType=" + $("#searchType" + num).val();
		//	alert("1. "+searchValue);
		$
				.ajax({
					type : "post",
					url : "<c:url value='/receipt/informerSearch.do' />",
					dataType : "json",
					data : searchValue,
					cache : false,
					success : function(data) {
						if (data.listSize > 1) {
							$("#SEARCH_TEXT2").val(
									$("#reservationSearch" + num).val());
							//	alert("2. "+$("#searchType" + num).val()+"/"+num);
							popInformer($("#searchType" + num).val(), num);

						} else {
							if (data.listSize > 0) {
								$("#reservationUserId" + num).val(
										data.informationList.INFORMER_ID);
								$("#reservationUserName" + num).val(
										data.informationList.INFORMER_NAME);
								$("#lableUserId" + num).html(
										data.informationList.ACT_ID);
								$("#lableUserName" + num).html(
										data.informationList.INFORMER_NAME);
							} else {
								alert("검색 결과가 없습니다.");
							}
						}
					},

					error : function(data, error) {
						//alert("시스템에 문제가 생겼습니다." + data);
					}

				});
	}

	function popInformer(searchType, num) {
		var url = '<c:url value="/receipt/popInformation.do"/>';
		var windowName = "popInformer11";
		var popupW = 600; // 팝업 넓이
		var popupH = 450; // 팝업 높이
		var left = Math.ceil((window.screen.width - popupW) / 2);
		var top = Math.ceil((window.screen.height - popupH) / 2);

		var popObj = window
				.open(
						'<c:url value="/jsp/receipt/test.jsp"/>',
						windowName,
						'width='
								+ popupW
								+ ', height='
								+ popupH
								+ ', left='
								+ left
								+ ', top='
								+ top
								+ ', toolbar=no, location=no, status=no, menubar=no, scrollbars=no, resizable=no');

		$('#searchType_2').val(searchType);
		$('#num').val(num);

		receiptFrm.method = "post";
		receiptFrm.action = url;
		receiptFrm.target = "popInformer11";
		receiptFrm.submit();
		//$("#SEARCH_TEXT2").val("");
	}

	function openerInformer(informerId, actId, informerName, num) {
		$("#reservationUserId" + num).val(informerId);
		$("#reservationUserName" + num).val(informerName);
		$("#lableUserId" + num).html(actId);
		$("#lableUserName" + num).html(informerName);
	}

	function informerSearch(informerId) {
		$.ajax({
			type : "post",
			url : "<c:url value='/receipt/informerInfo.do' />",
			dataType : "html",
			data : "INFORMER_ID=" + informerId,
			cache : false,
			success : function(html) {
				$("#informerDiv").html(html);
			},

			error : function(html, error) {
				//alert("시스템에 문제가 생겼습니다." + error);
			}

		});
	}

	function addKmContents() {
		var contentsStr = $("#contentsTextArear").val();
		contentsStr += " Km";
		$("#contentsTextArear").val(contentsStr);
	}

	//소통현황 팝업
	function popCommunication() {
		var url = '<c:url value="/receipt/communicationStatus.do"/>';
		var windowName = "소통현황";
		var popupW = 900; // 팝업 넓이
		var popupH = 700; // 팝업 높이
		var left = Math.ceil((window.screen.width - popupW) / 2);
		var top = Math.ceil((window.screen.height - popupH) / 2);

		var popObj = window
				.open(
						url,
						windowName,
						'width='
								+ popupW
								+ ', height='
								+ popupH
								+ ', left='
								+ left
								+ ', top='
								+ top
								+ ', toolbar=no, location=no, status=no, menubar=no, scrollbars=no, resizable=no');

		receiptFrm.method = "post";
		receiptFrm.action = url;
		receiptFrm.target = "소통현황";
		receiptFrm.submit();
	}

	function popCctv() {
		var url = '<c:url value="/receipt/popCctv.do"/>';
		var windowName = "CCTV";
		var popupW = 614; // 팝업 넓이
		var popupH = 700; // 팝업 높이
		var left = Math.ceil((window.screen.width - popupW) / 2);
		var top = Math.ceil((window.screen.height - popupH) / 2);

		var popObj = window
				.open(
						url,
						windowName,
						'width='
								+ popupW
								+ ', height='
								+ popupH
								+ ', left='
								+ left
								+ ', top='
								+ top
								+ ', toolbar=no, location=no, status=no, menubar=no, scrollbars=no, resizable=no');

		//receiptFrm.method="post";
		//receiptFrm.action = url;
		//receiptFrm.target="CCTV";
		//receiptFrm.submit();
	}

	//중요제보 - 미사용으로 보여서 주석처리
	/*function checkImportant() {
		if ($("#IMPORTANT_LEVEL").val() != '') {
			$("#FLAG_IMPORTANT").val("Y");
		} else {
			$("#FLAG_IMPORTANT").val("N");
		}
	}
*/
	function addReceipt(num) {
		var INPUT_STARTDAY = "";
		var INPUT_STARTHH = "";
		var INPUT_STARTMI = "";
		var INPUT_ENDDAY = "";
		var INPUT_ENDHH = "";
		var INPUT_ENDMI = "";
		var INPUT_INCIDENT_CONTENTS = "";
		var INPUT_COORDX = "";
		var INPUT_COORDY = "";
		var INPUT_INCIDENTCODE = "";
		var INPUT_DATARESID = "";

		if (receiptFrm.STARTDAY.length > 0) {
			INPUT_STARTDAY = receiptFrm.STARTDAY[num].value;
			INPUT_STARTHH = receiptFrm.STARTHH[num].value;
			INPUT_STARTMI = receiptFrm.STARTMI[num].value;
			INPUT_ENDDAY = receiptFrm.ENDDAY[num].value;
			INPUT_ENDHH = receiptFrm.ENDHH[num].value;
			INPUT_ENDMI = receiptFrm.ENDMI[num].value;
			INPUT_INCIDENT_CONTENTS = receiptFrm.INCIDENT_CONTENTS[num].value;
			INPUT_COORDX = receiptFrm.COORDX[num].value;
			INPUT_COORDY = receiptFrm.COORDY[num].value;
			INPUT_INCIDENTCODE = receiptFrm.INCIDENTCODE[num].value;
			INPUT_DATARESID = receiptFrm.DATARESID[num].value;
		} else {
			INPUT_STARTDAY = receiptFrm.STARTDAY.value;
			INPUT_STARTHH = receiptFrm.STARTHH.value;
			INPUT_STARTMI = receiptFrm.STARTMI.value;
			INPUT_ENDDAY = receiptFrm.ENDDAY.value;
			INPUT_ENDHH = receiptFrm.ENDHH.value;
			INPUT_ENDMI = receiptFrm.ENDMI.value;
			INPUT_INCIDENT_CONTENTS = receiptFrm.INCIDENT_CONTENTS.value;
			INPUT_COORDX = receiptFrm.COORDX.value;
			INPUT_COORDY = receiptFrm.COORDY.value;
			INPUT_INCIDENTCODE = receiptFrm.INCIDENTCODE.value;
			INPUT_DATARESID = receiptFrm.DATARESID.value;
		}

		$("#START_DAY").val(INPUT_STARTDAY);
		$("#STARTTIMEHH").val(INPUT_STARTHH);
		$("#STARTTIMEMI").val(INPUT_STARTMI);

		$("#END_DAY").val(INPUT_ENDDAY);
		$("#ENDTIMEHH").val(INPUT_ENDHH);
		$("#ENDTIMEMI").val(INPUT_ENDMI);

		$("#contentsTextArear").val(INPUT_INCIDENT_CONTENTS);
		$("#REPORT_TYPE").val(INPUT_INCIDENTCODE);
		codeOnChange('REPORT_TYPE', 'REPORT_TYPE2');
		$("#X_COORDINATE").val(INPUT_COORDX);
		$("#Y_COORDINATE").val(INPUT_COORDY);
		$("#USE_AREA").val(INPUT_DATARESID);

		$("#REPORTMEAN_TYPE").val('3');
		$("#OFFER_CODE").val('T');

		informerSearch($("#INCIDENT_ACT_ID").val());

	}

	function objCheck() {
		if (Obj.object == null) {
			//alert("ACDScaner OCX가 설치되지 않았습니다.\n\n다시 시도해 주세요.");				
			return false;
		} else {
			return true;
		}

	}

	function searchIncident() {
		$.ajax({
			type : "post",
			url : "<c:url value='/receipt/incidentCenterSearch.do' />",
			dataType : "html",
			data : $("#receiptFrm").serialize(),
			cache : false,
			success : function(html) {
				$("#IncidentDiv").html(html);
			},

			error : function(html, error) {
				//alert("시스템에 문제가 생겼습니다." + error);
			}

		});
	}

	var timeOut;
	function onSidcheck() {
		var s = 1000 * Number($("#timess").val());
		if ($("#timeOutCheck").is(":checked")) {
			timeOut = setInterval("searchIncident()", s);
		} else {
			clearTimeout(timeOut);
		}
	}

	function addImage() {
		var url = '<c:url value="/receipt/popAddImage.do"/>';
		var windowName = "snsPopup";
		var popupW = 475; // 팝업 넓이
		var popupH = 280; // 팝업 높이
		var left = Math.ceil((window.screen.width - popupW) / 2);
		var top = Math.ceil((window.screen.height - popupH) / 2);

		var popObj = window
				.open(
						url,
						windowName,
						'width='
								+ popupW
								+ ', height='
								+ popupH
								+ ', left='
								+ left
								+ ', top='
								+ top
								+ ', toolbar=no, location=no, status=no, menubar=no, scrollbars=no, resizable=no');
	}

	function contentsAddKmH() {
		$("#contentsTextArear").val($("#contentsTextArear").val() + " Km/h ");
	}

	function contentsAddArrows() {
		$("#contentsTextArear").val($("#contentsTextArear").val() + " ==> ");
	}