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
			}); */
			
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
	            
	            var detailPop = window.open('/notice/detailNotice.do?noticeId='+noticeId,'공지사항 상세','width=1200px,height=800px,scrollbars=yes');
	        });
			
			
			// 24-11-25 : 등록 버튼 클릭 시 
			$('.insertButton').on('click', function(){
				console.log("등록 버튼");

				var insertPop = window.open('/notice/goInsert.do','공지사항 등록','width=1200px,height=800px,scrollbars=yes');
			});
			
			
			$('.searchButton').on('click', function(){
				var searchDate = $('#searchDate').val();
				var searchText = $('#searchText').val();
				
				if(searchDate == '' && searchText == '') {
					alert('검색 조건을 선택 또는 입력해주세요');
					return false;
				}  else {
					$('#searchNotice').submit();
				}
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
                <table style="width : 880px;" border="0" cellpadding="0" cellspacing="0" class="list01">
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
						<c:forEach var="list" items="${noticeList}">
							<tr>
								<input type="hidden" id="notice_id" value="${list.NOTICE_ID}">
								<td style="font-size : 16px;">${list.NOTICE_TITLE}</td>
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