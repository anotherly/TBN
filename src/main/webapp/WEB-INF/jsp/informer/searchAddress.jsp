<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="ko" lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>TBN한국교통방송 제보접수시스템</title>
<link rel="stylesheet" type="text/css" href="<c:url value='/css/layout.css'/>"/>
<script type="text/javascript" charset="utf-8" src="<c:url value='/js/common/jquery/jquery-1.6.4.min.js'/>"></script>
<script type="text/javascript" charset="utf-8" src="<c:url value='/js/common/jquery/jquery.form.js'/>"></script>
<script type="text/javascript" charset="utf-8" src="<c:url value='/js/common/ui.js'/>"></script>
</head>
<body>
<!-- contents size 475*595 -->
<div id="zip">
    <h1><img src="../images/zip_tit.gif" alt="주소검색" /></h1>
    <!-- 동입력 -->
    <div class="zipcode_tab01">
        <p>찾고자 하는 주소의 동(읍/면)명을 입력하신 후 검색을 누르세요.</p>
        <div class="zipcode_sch">
            <form id="srarchFrm" name="srarchFrm">
	            <input type="text" class="input_base" name="searchValue" id="searchValue" value=""  />
	            <img src="../images/btn_zip.gif" alt="검색" id="searchBtn" name="searchBtn" />
            </form> 
        </div>
    </div>
    <!-- 검색결과 -->
    <div class="zipcode_tab02">
        <p>* 아래의 주소 중에서 해당되는 주소를 선택하여 주십시오.</p>
        <div class="zip_tit">
            <div class="zip_tit2">
                <table summary="주소검색" border="0" cellpadding="0" cellspacing="0" class="zip_tit">
                    <caption>
                    주소검색결과
                    </caption>
                    <colgroup>
                    <col width="100" />
                    <col width="*" />
                    </colgroup>
                    <thead>
                        <tr>
                            <th scope="col">우편번호</th>
                            <th scope="col">주소</th>
                        </tr>
                    </thead>
                </table>
                <div id="addressListDiv" class="zip_tit3">
                </div>
            </div>
        </div>
    </div>
    <div class="btnArg">
        <input onclick="self.close();" type="image" src="../images/btn_close.gif" alt="닫기" title="닫기" />
    </div>
</div>
<script>
$(document).ready(function(){
	/**
	 * 검색 버튼 클릭 시
	 */
    $('#searchBtn').click(function(){
        if($('#searchValue').val() == null || $('#searchValue').val() == ''){
            alert('검색어를 입력하세요.');
            return false;
        }
        
        var options = {
                url:'<c:url value="/informer/addressList.do"/>',
                type:'post',
                target: '#addressListDiv',
                success: function(){
                },
                error: function(res,error){
                    alert("에러가 발생했습니다."+error);
                }
        };
        $('#srarchFrm').ajaxSubmit(options);
    });
})

/**
 * 주소 선택, 설정
 */
function setAddress(str){
    var target = "${num}" == 1 ? "ADDRESS_HOME" : "ADDRESS_OFFICE";
    var selectAddress = "[" + $('#ZIP_CODE'+str).text() + "] " + $('#ADDRESS'+str).text();
    
    opener.setAddress(selectAddress, target);
    self.close();
}
</script>
</body>
</html>