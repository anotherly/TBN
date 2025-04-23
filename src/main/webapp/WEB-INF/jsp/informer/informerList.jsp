<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<div class="board_list">
	<div class="list_result_sc" style="width:1030px;">
	    <table summary="통신원목록" border="0" cellpadding="0" cellspacing="0" class="list01"  id="informer_table" style="table-layout: fixed;">
	    <caption>
	       통신원목록
	    </caption>
	     <%-- <colgroup>
	        <col width="6%" /> <!-- id -->
	        <col width="8%" /> <!-- 방송국 -->
	        <col width="10%" /> <!-- 유형 -->
	        <col width="13%" /> <!-- 소속기관 -->
	        <col width="10%" /> <!-- 이름 -->
	        <col width="13%" /> <!-- 전화 -->
            <col width="8%" /> <!-- 활동여부 -->
            <col width="10%" /> <!-- 등록일 -->
            <col width="*" /> <!-- 이력정보 -->
	    </colgroup>  --%>
	    <thead>
	        <tr>
	        	<th style="width:50px;">선택</th>
	            <th style="width:90px;">ID<div class='sortDiv' ><button onclick="sortTD ( 0 ,this,event )">▲</button><button onclick="reverseTD ( 0 ,this ,event)">▼</button></div></th>
	            <th style="width:100px;">방송국<div class='sortDiv' ><button onclick="sortTD ( 1 ,this ,event)">▲</button><button onclick="reverseTD ( 1 ,this ,event)">▼</button></div></th>
	            <th style="width:80px;">유형<div class='sortDiv' ><button onclick="sortTD ( 2 ,this ,event)">▲</button><button onclick="reverseTD ( 2 ,this ,event)">▼</button></div></th>
	            <th style="width:130px;">소속기관<div class='sortDiv' ><button onclick="sortTD ( 2 ,this ,event)">▲</button><button onclick="reverseTD ( 2 ,this ,event)">▼</button></div></th>
	            <th style="width:150px;">이름<div class='sortDiv' ><button onclick="sortTD ( 3 ,this ,event)">▲</button><button onclick="reverseTD ( 3 ,this ,event)">▼</button></div></th>
	            <th style="width:150px;">전화<div class='sortDiv' ><button onclick="sortTD ( 4 ,this ,event)">▲</button><button onclick="reverseTD ( 4 ,this ,event)">▼</button></div></th>
	            <th style="width:100px;">활동여부<div class='sortDiv' ><button onclick="sortTD ( 5 ,this ,event)">▲</button><button onclick="reverseTD ( 5 ,this ,event)">▼</button></div></th>
	            <th style="width:102px;">등록일<div class='sortDiv' ><button onclick="sortTD ( 6 ,this ,event)">▲</button><button onclick="reverseTD ( 6 ,this ,event)">▼</button></div></th>
	            <th style="width:62px;">이력정보</th>
	        </tr>
	    </thead>
	    <tbody style="width:1030px;">
	     <c:choose>
   	   		<c:when test="${fn:length(informerList)==0}">
   				<tr><td style="width:880px">해당하는 데이터를 조회하지 못했습니다.</td></tr>
   	   		</c:when>
   	   		<c:otherwise>
   	   			<c:forEach var="informer" items="${informerList}" varStatus="idx">
                <tr>
                <td style="width:50px;"><input type="checkbox" id="Selection" name="Selection" value="${informer.informerId}"/></td>
		            <%-- <td onclick="editInformer('${informer.informerId}');" style="cursor: pointer;">${ALL_CNT - MIN + 1 - idx.count}</td> --%>
                    <td onclick="editInformer('${informer.informerId}');" style="cursor: pointer;width:90px;"><c:out value="${informer.actId}"/></td>
                    <td onclick="editInformer('${informer.informerId}');" style="cursor: pointer;width:100px;"><c:out value="${informer.areaName}"/></td>
                    <td onclick="editInformer('${informer.informerId}');" style="cursor: pointer;width:80px;"><c:out value="${informer.informerTypeName}"/></td>
                    <td onclick="editInformer('${informer.informerId}');" style="cursor: pointer;width:130px;"><c:out value="${informer.orgName}"/></td>
                    <td onclick="editInformer('${informer.informerId}');" style="cursor: pointer;width:150px;"><c:out value="${informer.informerName}"/></td>
                    <td onclick="editInformer('${informer.informerId}');" style="cursor: pointer;width:150px;"><c:out value="${informer.phoneCell}"/></td>
                    <td onclick="editInformer('${informer.informerId}');" style="cursor: pointer;width:100px;"><c:out value="${informer.flagAct == 'Y' ? '위촉' : '해촉'}"/></td>
                    <td onclick="editInformer('${informer.informerId}');" style="cursor: pointer;width:102px;"><c:out value="${informer.regDate}"/></td>
                    <%-- <td onclick="editInformer('${informer.informerId}');" style="cursor: pointer;"><c:out value="${informer.TRS_NO}"/></td>
                    <td onclick="editInformer('${informer.informerId}');" style="cursor: pointer;"><c:out value="${informer.HAM_NO}"/></td> --%>
                    <td style="width:62px;"><img src="<c:url value="/images/btn_view2.gif"/>" onclick="showInformerHistory('${informer.informerId}');" style="cursor: pointer;"/></td>
	            </tr>
            </c:forEach>
   	   		</c:otherwise>
   		</c:choose>
	        
	    </tbody>
	    </table>
	</div>
</div>

<script type="text/javascript">
$(document).ready(function(){
	console.log("informerList.jsp 진입");
	
	var totalSize = '${cnt}';
	// 전체 건수 몇건인지 가져오기
	$("#resultListTotal span").text(totalSize);
	
	if($("#informer_table tbody").prop('scrollHeight') - $("#informer_table tbody").prop('clientHeight') > 0){
		//스크롤바 생길 시 테이블 밀림 방지
	    $("#informer_table th:last-child").css('padding-right','17px');
	}
});
//출처 : http://tonks.tistory.com/79 

/* sortingNumber() : 숫자인 실수만으로 되어있을 때, 적용될 함수 */ 

function sortingNumber( a , b ){  

     if ( typeof a == "number" && typeof b == "number" ) return a - b; 

     // 천단위 쉼표와 공백문자만 삭제하기.  
     var a = ( a + "" ).replace( /[,\s\xA0]+/g , "" ); 
     var b = ( b + "" ).replace( /[,\s\xA0]+/g , "" ); 

     var numA = parseFloat( a ) + ""; 
     var numB = parseFloat( b ) + ""; 

     if ( numA == "NaN" || numB == "NaN" || a != numA || b != numB ) return false; 

     return parseFloat( a ) - parseFloat( b ); 
} 


/* changeForSorting() : 문자열 바꾸기. */ 

function changeForSorting( first , second ){  

     // 문자열의 복사본 만들기. 
     var a = first.toString().replace( /[\s\xA0]+/g , " " ); 
     var b = second.toString().replace( /[\s\xA0]+/g , " " ); 

     var change = { first : a, second : b }; 

     if ( a.search( /\d/ ) < 0 || b.search( /\d/ ) < 0 || a.length == 0 || b.length == 0 ) return change; 

     var regExp = /(\d),(\d)/g; // 천단위 쉼표를 찾기 위한 정규식. 

     a = a.replace( regExp , "$1" + "$2" ); 
     b = b.replace( regExp , "$1" + "$2" ); 

     var unit = 0; 
     var aNb = a + " " + b; 
     var numbers = aNb.match( /\d+/g ); // 문자열에 들어있는 숫자 찾기 

     for ( var x = 0; x < numbers.length; x++ ){ 

             var length = numbers[ x ].length; 
             if ( unit < length ) unit = length; 
     } 

     var addZero = function( string ){ // 숫자들의 단위 맞추기 

             var match = string.match( /^0+/ ); 

             if ( string.length == unit ) return ( match == null ) ? string : match + string; 

             var zero = "0"; 

             for ( var x = string.length; x < unit; x++ ) string = zero + string; 

             return ( match == null ) ? string : match + string; 
     }; 

     change.first = a.replace( /\d+/g, addZero ); 
     change.second = b.replace( /\d+/g, addZero ); 

     return change; 
} 


/* byLocale() */ 

function byLocale(){ 

     var compare = function( a , b ){ 

             var sorting = sortingNumber( a , b ); 

             if ( typeof sorting == "number" ) return sorting; 

             var change = changeForSorting( a , b ); 

             var a = change.first; 
             var b = change.second; 

             return a.localeCompare( b ); 
     }; 

     var ascendingOrder = function( a , b ){  return compare( a , b );  }; 
     var descendingOrder = function( a , b ){  return compare( b , a );  }; 

     return { ascending : ascendingOrder, descending : descendingOrder }; 
} 


/* replacement() */ 

function replacement( parent ){  
     var tagName = parent.tagName.toLowerCase(); 
     if ( tagName == "table" ) parent = parent.tBodies[ 0 ]; 
     tagName = parent.tagName.toLowerCase(); 
     if ( tagName == "tbody" ) var children = parent.rows; 
     else var children = parent.getElementsByTagName( "li" ); 

     var replace = { 
             order : byLocale(), 
             index : false, 
             array : function(){ 
                     var array = [ ]; 
                     for ( var x = 0; x < children.length; x++ ) array[ x ] = children[ x ]; 
                     return array; 
             }(), 
             checkIndex : function( index ){ 
                     if ( index ) this.index = parseInt( index, 10 ); 
                     var tagName = parent.tagName.toLowerCase(); 
                     if ( tagName == "tbody" && ! index ) this.index = 0; 
             }, 
             getText : function( child ){ 
                     if ( this.index ) child = child.cells[ this.index ]; 
                     return getTextByClone( child ); 
             }, 
             setChildren : function(){ 
                     var array = this.array; 
                     while ( parent.hasChildNodes() ) parent.removeChild( parent.firstChild ); 
                     for ( var x = 0; x < array.length; x++ ) parent.appendChild( array[ x ] ); 
             }, 
             ascending : function( index ){ // 오름차순 
                     this.checkIndex( index ); 
                     var _self = this; 
                     var order = this.order; 
                     var ascending = function( a, b ){ 
                             var a = _self.getText( a ); 
                             var b = _self.getText( b ); 
                             return order.ascending( a, b ); 
                     }; 
                     this.array.sort( ascending ); 
                     this.setChildren(); 
             }, 
             descending : function( index ){ // 내림차순
                     this.checkIndex( index ); 
                     var _self = this; 
                     var order = this.order; 
                     var descending = function( a, b ){ 
                             var a = _self.getText( a ); 
                             var b = _self.getText( b ); 
                             return order.descending( a, b ); 
                     }; 
                     this.array.sort( descending ); 
                     this.setChildren(); 
             } 
     }; 
     return replace; 
} 

function getTextByClone( tag ){  
     var clone = tag.cloneNode( true ); // 태그의 복사본 만들기. 
     var br = clone.getElementsByTagName( "br" ); 
     while ( br[0] ){ 
             var blank = document.createTextNode( " " ); 
             clone.insertBefore( blank , br[0] ); 
             clone.removeChild( br[0] ); 
     } 
     var isBlock = function( tag ){ 
             var display = ""; 
             if ( window.getComputedStyle ) display = window.getComputedStyle ( tag, "" )[ "display" ]; 
             else display = tag.currentStyle[ "display" ]; 
             return ( display == "block" ) ? true : false; 
     }; 
     var children = clone.getElementsByTagName( "*" ); 
     for ( var x = 0; x < children.length; x++){ 
             var child = children[ x ]; 
             if ( ! ("value" in child) && isBlock(child) ) child.innerHTML = child.innerHTML + " "; 
     } 
     var textContent = ( "textContent" in clone ) ? clone.textContent : clone.innerText; 
     return textContent; 
} 

var myTable = document.getElementById( "informer_table" ); 
var replace = replacement( myTable ); 

function sortTD( index,that,event ){  
	$(".sortDiv button").css("color","black");
	replace.ascending( index );
	$(that).css("color","red");	
	
	event.preventDefault(); // 폼 제출 막기
	

} 
function reverseTD( index,that ,event ){
	$(".sortDiv button").css("color","black");
	replace.descending( index );
	$(that).css("color","red");	
	
	event.preventDefault(); // 폼 제출 막기
} 



// 무한 스크롤 함수 ( 처음에 로드될 때 rownum 1~20까지 불러온다.)

//기본 변수
var startRnum = 1; // 시작 rownum
var endRnum = 21; // 끝 rownum
var lastScrollTop = 0; // 이전 스크롤 좌표 기본값 초기화
var loadFlag = 'Y';

// 위로 스크롤이 max일 때 , 아래로 스크롤이 max일 때 구현
$("#informer_table tbody").scroll(function() {

    var nowScrollTop = $("#informer_table tbody").scrollTop();
    var scrollHeight = $("#informer_table tbody")[0].scrollHeight; // 전체 컨텐츠 높이
    var clientHeight = $("#informer_table tbody")[0].clientHeight; // 보이는 영역 높이
	var ifmVO = $('#searchFrm').serialize();
    
    if (nowScrollTop > lastScrollTop) {
        // 스크롤이 맨 아래에 도달했을 때
        if (nowScrollTop + clientHeight >= scrollHeight) {
            
            startRnum = startRnum + 20; //다음 목록을 불러오기 위해 숫자 추가
            endRnum = endRnum + 20; //다음 목록을 불러오기 위해 숫자 추가
            
            // ajax로 이후 데이터를 불러오고, append 함수를 이용하여 컨텐츠 추가
            $.ajax({
            	url : '/informer/ifrmNextScroll.ajax',
            	type : 'post',
            	data : ifmVO + '&startRnum=' + startRnum + '&endRnum=' + endRnum, // 다음페이지 시작 번호와 끝 번호 넘기기
            	success : function(data) {
	
            		if(loadFlag == 'Y') {
            			// append 할 str 작성 

            			for(var i = 0; i< data.cnt; i++) {
            				var informer = data.informerList[i];

            	            var trHtml = '<tr>';
            	            trHtml += '<td style="width:50px;"><input type="checkbox" id="Selection" name="Selection" value="' + informer.informerId + '"/></td>';
            	            trHtml += '<td onclick="editInformer(\'' + informer.informerId + '\');" style="cursor: pointer;width:90px;">' + informer.actId + '</td>';
            	            trHtml += '<td onclick="editInformer(\'' + informer.informerId + '\');" style="cursor: pointer;width:100px;">' + informer.areaName + '</td>';
            	            trHtml += '<td onclick="editInformer(\'' + informer.informerId + '\');" style="cursor: pointer;width:80px;">' + informer.informerTypeName + '</td>';
            	            trHtml += '<td onclick="editInformer(\'' + informer.informerId + '\');" style="cursor: pointer;width:130px;">' + informer.orgName + '</td>';
            	            trHtml += '<td onclick="editInformer(\'' + informer.informerId + '\');" style="cursor: pointer;width:150px;">' + informer.informerName + '</td>';
            	            trHtml += '<td onclick="editInformer(\'' + informer.informerId + '\');" style="cursor: pointer;width:150px;">' + informer.phoneCell + '</td>';
            	            trHtml += '<td onclick="editInformer(\'' + informer.informerId + '\');" style="cursor: pointer;width:100px;">' + (informer.flagAct == 'Y' ? '위촉' : '해촉') + '</td>';
            	            trHtml += '<td onclick="editInformer(\'' + informer.informerId + '\');" style="cursor: pointer;width:102px;">' + informer.regDate + '</td>';
            	            trHtml += '<td style="width:62px;"><img src="/images/btn_view2.gif" onclick="showInformerHistory(\'' + informer.informerId + '\');" style="cursor: pointer;"/></td>';
            	            trHtml += '</tr>';
            	            
            	            $("#informer_table tbody").append(trHtml);
            			} 
            			
            		} else { // 그리고 append 되지 않도록 처리
            			startRnum = startRnum - 20; 
                        endRnum = endRnum - 20; 
            		}   
            		
            		var cnt = data.cnt;
            		if(cnt != 20) { // 20보다 크거나 작은 경우
            			loadFlag = 'N'; // 더이상 불러오지 않음
            		} else {
            			loadFlag = 'Y';
            		}
	         		
            	},
            	error : function() {
            		console.log(" ajax 요청 중 에러 발생 ");
            	}
            	
            });
            
            // 더이상 불러올 데이터가 없는경우 예외처리 필요 (불러온 데이터가 20보다 작은 경우)
        }

    } 

    // 마지막 스크롤 좌표 업데이트
    lastScrollTop = nowScrollTop;
});



</script>

