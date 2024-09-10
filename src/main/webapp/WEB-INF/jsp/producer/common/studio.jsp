<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>TBN한국교통방송 제보접수시스템</title>
<script type="text/javascript" charset="utf-8" src="<c:url value='/js/common/jquery/jquery-1.6.4.min.js'/>"></script>
<link rel="stylesheet" type="text/css" href="../../../css/layout.css" />
<script>
	$(window).load(function(){
		childwin = this;
	});
	
	//방송리스트 보여주기
	function showStudio(contentsList, informer_type_list, report_type_list, informer_name_list){
		document.getElementById("contents_st2").style.display = "block";
		document.getElementById("contents_st3").style.display = "none";
		
		for(i=0;i<contentsList.length;i++){
			$('#contents'+i).text(contentsList[i]);
			$('#informer_type'+i).text(informer_type_list[i]);
			$('#report_type'+i).text(report_type_list[i]);
			$('#informer_name'+i).text(informer_name_list[i]);
		}
		for(i=i-1;i<4;i++){
			$('#contents'+i).text("");
			$('#informer_type'+i).text("");
			$('#report_type'+i).text("");
			$('#informer_name'+i).text("");
		}
	}
	//방송리스트 지우기
	function resetStudio(){
		document.getElementById("contents_st2").style.display = "block";
		document.getElementById("contents_st3").style.display = "none";
		 
		for(i=0;i<4;i++){
			$('#contents'+i).text("");
			$('#informer_type'+i).text("");
			$('#report_type'+i).text("");
			$('#informer_name'+i).text("");
		}
	}
	//방송리스트 한개씩 지우기
	function goDel(num){
		document.getElementById("contents_st2").style.display = "block";
		document.getElementById("contents_st3").style.display = "none";
		$('#contents'+num).text("");
		$('#informer_type'+num).text("");
		$('#report_type'+num).text("");
		$('#informer_name'+num).text("");
	}
	//전달사항 전송	
	function showText(contents){
		document.getElementById("contents_st3").style.display = "block";
		document.getElementById("contents_st2").style.display = "none";
		$('#message').text(contents);
	}
	//전달사항 지우기
	function resetText(){
		document.getElementById("contents_st3").style.display = "block";
		document.getElementById("contents_st2").style.display = "none";
		$('#message').text("");
	}
	
	function studioView(){
		document.getElementById("contents_st2").style.display = "block";
		document.getElementById("contents_st3").style.display = "none";
	}
	
	function textView(){
		document.getElementById("contents_st2").style.display = "none";
		document.getElementById("contents_st3").style.display = "block";
	}
	
</script>
</head>
<body bgcolor="#ececec">
<!-- contents 팝업사이즈 450*620px -->
<!-- contents : 교통정보 -->
<div id="contents_st2">
  <div class="st_linece3">
    <p class="mgl15 mgt8"><img src="../../../images/02pd_stp.gif" alt="스튜디오" /></p>
    <ul class="valuation3">
      <li>
        <table summary="스튜디오" border="0" cellpadding="0" cellspacing="0" class="st_board">
          <caption>
          스튜디오
          </caption>
          <colgroup>
          <col width="40" />
          <col width="100" />
          <col width="100" />
          <col width="160" />
          </colgroup>
          <tbody>
          <% for(int i=0;i<4;i++){ %>
	          	<tr style="font-size:11px;">
	              <td scope="col" ><img src="../../../images/ico_studio.gif" alt="" /></td>
	              <td scope="col" class="st_boardside" id="informer_type<%=i %>"></td>
	              <td scope="col" class="st_boardside" id="report_type<%=i %>"></td>
	              <td scope="col" class="st_boardside" id="informer_name<%=i %>"></td>
				</tr>
	            <tr>
	              <td class="st_boardside"></td>
	              <td colspan="3" id="contents<%=i %>" name="contents<%=i %>" class="st_boardside ht70 txtop" style="font-weight: bold;"></td>
	            </tr>
            <% } %>
          </tbody>
        </table>
      </li>
    </ul>
  </div>
</div>
<!-- contents : 전달사항 -->
<div id="contents_st3" style="display:none">
  <div class="st_linece3">
    <p class="mgl15 mgt8"><img src="../../../images/02pd_message2.gif" alt="전달사항" /></p>
    <ul class="valuation3">
      <li>
        <table summary="스튜디오" border="0" cellpadding="0" cellspacing="0" class="st_board2">
          <caption>
          스튜디오
          </caption>
          <tbody>
          	<tr>
              <td id="message" name="message" colspan="3" class="txtop" style="font-weight: bold;"></td>
            </tr>
          </tbody>
        </table>
      </li>
    </ul>
  </div>
</div>
</body>
</html>
