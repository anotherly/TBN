<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.*"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- https => http 요청 허용 -->
<meta http-equiv="Content-Security-Policy" content="upgrade-insecure-requests">

<jsp:include page="../common/all.jsp" flush="false" /> 
<!-- <title>공지사항</title> -->
<style>
	.insertButton, .searchButton{
		width: 80px;
	    height: 35px;
	    border-radius: 5px;
	}
</style>
<script>
		$(document).ready(function() {		
			console.log("notice 진입");
			
			//함수 호출
			comparisonCode();

			$('#searchDate').prop('disabled', true);
 			/* $('#searchDate').datepicker({
				dateFormat: 'yy-mm-dd' //달력 날짜 형태
				,showOtherMonths: true //빈 공간에 현재월의 앞뒤월의 날짜를 표시
			    ,showMonthAfterYear:true // 월- 년 순서가아닌 년도 - 월 순서
			    ,changeYear: true //option값 년 선택 가능
			    ,changeMonth: true //option값  월 선택 가능                         
			    ,yearSuffix: "년" //달력의 년도 부분 뒤 텍스트
			    ,monthNamesShort: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'] //달력의 월 부분 텍스트
			    ,monthNames: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'] //달력의 월 부분 Tooltip
			    ,dayNamesMin: ['일','월','화','수','목','금','토'] //달력의 요일 텍스트
			    ,dayNames: ['일요일','월요일','화요일','수요일','목요일','금요일','토요일'] //달력의 요일 Tooltip
			});  */
			
			/* $('#searchDate').datetimepicker({
			    format: 'YYYY-MM-DD HH:mm', // 날짜와 시간 포맷 (예: 2024-12-10 14:30)
			    locale: 'ko', // 한국어로 설정
			    showClear: true, // 날짜를 지울 수 있는 버튼 표시
			    showClose: true, // 날짜를 선택한 후, 창을 닫을 수 있는 버튼 표시
			    allowInputToggle: true, // 입력 필드를 클릭하면 캘린더가 열리도록 설정
			    widgetPositioning: {
			        horizontal: 'auto',
			        vertical: 'auto'
			    },
			    stepping: 15, // 시간을 15분 단위로 선택 가능
			    minDate: new Date(), // 최소 날짜를 오늘로 설정
			    useCurrent: false, // 기본적으로 현재 날짜와 시간으로 설정되지 않도록 설정
			    daysOfWeekDisabled: [0, 6], // 주말(일요일, 토요일)을 비활성화
			}); */
			$("#ui-datepicker-div").remove();
			dateFunc('searchDate');
					
			// 24-11-15 : 페이지 로드 시 현재 로그인된 사용자의 권한 코드 비교하기		
			function comparisonCode(){
				var aCode = '${login.authCode}';
	
				if(aCode == 999) {
					// 등록 및 기능 버튼 show
					$('#insertBtn_div').show();
				} else {
					// 등록 및 기능 버튼 hide
					$('#insertBtn_div').hide();
				}
				
			}
			
			$('td').click(function() {
	            // 클릭된 td의 부모 tr 요소를 찾음
	            var tr = $(this).closest('tr');
	            
	            // tr 요소 내에서 input[type="hidden"] 값을 가져옴
	            var noticeId = tr.find('input[type="hidden"]').val();
	            var detailPop = window.open('/notice/detailNotice.do?noticeId=' + noticeId, '공지사항 상세', 'width=1200px,height=800px,scrollbars=yes');

	            const noticeData = { userId: userId };

	            // 팝업 로딩 후 메시지 전송
	            detailPop.onload = function () {
	              detailPop.postMessage(noticeData, '*'); // '*' 대신 정확한 origin을 넣는 게 더 안전
	            };

			});
			
			
			// 24-11-25 : 등록 버튼 클릭 시 
			$('.insertButton').on('click', function(){
				console.log("등록 버튼");

				var insertPop = window.open('/notice/goInsert.do','공지사항 등록','width=1200px,height=800px,scrollbars=yes');
			});
			
			
			$('.searchButton').on('click', function(){
				
				if (!$('#startDate_chk').prop('checked')) {
			        $('#searchDate').val('');
			    }
				
				var searchDate = $('#searchDate').val();
				var searchText = $('#searchText').val();
				
				var options = {
				        url:"/notice/notice.do",
				        type: "POST",
				        data : {"searchDate" : searchDate, "searchText" : searchText},
						//dataType: "json",
						async : false,
				        target: '#contents',
				        success: function(json){
				        	console.log("성공 불러오기");
				        	
				        	$('.admin_result_sc').hide();
				        	$('#contents').html(json);
				        },
				        error: function(res,error){
				            alert("에러가 발생했습니다."+res);
				        }
				    };
				$.ajax(options);
			});
			
			
		})
		
		// 자식창에서 실행될 함수( 페이지 새로고침 )
		function search(val) {
			console.log("function search");
	
			if(val == true) {
				var options = {
				        url:"/notice/notice.do",
				        type: "POST",
						//dataType: "json",
						async : false,
				        target: '#contents',
				        success: function(json){
				        	console.log("성공 불러오기");
				        	
				        	$('.admin_result_sc').hide();
				        	$('#contents').html(json);
				        },
				        error: function(res,error){
				            alert("에러가 발생했습니다."+res);
				        }
				    };
				    $.ajax(options);
			}

		}
		
		$('#startDate_chk').on('click', function() {
		    if ($(this).prop('checked')) {
		        $('#searchDate').prop('disabled', false);
		    } else {
		    	$('#searchDate').prop('disabled', true);
		    }
		});

		
</script>
</head>
<body>
<div id="contents">
    <div id="content">
        <h1 class="content-title">공지사항</h1>
        <div class="board_list">
            <form id="searchNotice" action="/notice/notice.do" method="post">
                <div class="rounding_wrap mgt10">
                    <div class="wrap_top"></div>
                    <div class="wrap_center">
                        <fieldset class="searchField">
                            <legend> 공지사항 검색조건</legend>
                            <input type="checkbox" id="startDate_chk">
                             월별 검색 :
                            <input type="text" id="searchDate" name="searchDate">
                          	 검색어 입력 :
                          	 <div>
                          	 	<input type="text" id="searchText" name="searchText">
                            	<button class="searchButton">검색</button>
                          	 </div>
                        </fieldset>
                    </div>
                    <div class="wrap_bottom"></div>
                </div>
            </form>
            <div id="targetDiv">
            </div>
            
            <div class="admin_result_sc">
                <table style="width : 880px; table-layout: fixed;" border="0" cellpadding="0" cellspacing="0" class="list01">
                	<colgroup>                                
	                    <col width="*" />
	                    <col width="200" />
                    </colgroup>
					<thead>
						<tr>
                        	<th style="font-size : 16px;">제목</th>
                        	<th style="font-size : 16px;">작성자</th>
                        	<th style="font-size : 16px;">작성일</th>
                        </tr>
					</thead>
					<tbody>
					<c:if test="${empty noticeList}">
						<td style="font-size : 16px;" colspan="3">해당하는 데이터가 없습니다.</td>
					</c:if>
						<c:forEach var="list" items="${noticeList}">
							<tr>
								<input type="hidden" id="notice_id" value="${list.NOTICE_ID}">
								<td style="font-size : 16px; overflow: hidden; text-overflow: ellipsis;white-space: nowrap;" >${list.NOTICE_TITLE}</td>
								<td style="font-size : 16px;">${list.WRITER_NAME}</td>
								<td style="font-size : 16px;">${list.START_DATE}</td>
							</tr>
						</c:forEach>
					</tbody>
                </table>
            </div>
        </div>
        <div id="insertBtn_div" style="margin-top: 15px; width: 880px;">
            	<div style="float: right;">
            		<button class="insertButton">등록</button>
            	</div>
            </div>
    </div>
</div>
</body>
</html>