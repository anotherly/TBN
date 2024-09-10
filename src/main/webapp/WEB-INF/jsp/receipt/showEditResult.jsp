<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<script type="text/javascript" charset="utf-8" src="<%=request.getContextPath()%>/js/receipt/receipt.js"></script>

<script>
$(function(){
	console.log("편집결과 vo");
});
</script>

		<span style="width:50px;">${editVO.RNUM }</span>
		<span style="width:85px;">${editVO.RECEIPT_DAY }</span>
		<span style="width:30px;">
			<c:choose>
       	   		<c:when test="${editVO.FLAG_SEND == 'Y' }">
       				<img src="../images/ico_send.png" alt="전송여부" />
       	   		</c:when>
       	   		<c:otherwise>
       	   			<img src="../images/ico_no2.png" alt="전송여부" />
       	   		</c:otherwise>
       		</c:choose>
		</span>
		<span style="width:30px;">
			<c:choose>
				<c:when test="${editVO.FLAG_BROD eq 'Y'}">
					<img src="../images/btn_pd2.png" alt="방송상태" />
				</c:when>
				<c:when test="${editVO.FLAG_BROD eq 'C'}">
					<img src="../images/btn_caster2.png" alt="방송상태" />
				</c:when>
				<c:otherwise>
					<img src="../images/ico_no2.png" alt="방송상태" />
				</c:otherwise>
			</c:choose>
		</span>
		<span style="width:52px;">${editVO.RECEIPT_TIME }</span>
		<span style="width:52px;">${editVO.BROAD_TIME }</span>
		<span style="width:60px; font-weight:700;
					color:${editVO.f_COLOR }; background-color:${editVO.b_COLOR };">
				${editVO.REPORT_TYPE }
		</span>
		<span style="width:150px;">${editVO.REPORT_TYPE2 }</span>
		<span style="width:120px;">${editVO.INDIVIDUAL_NAME }</span>
		<span style="width:40px;">${editVO.TYPE_NAME }</span>
		<span style="width:80px;">${editVO.RECEPTION_NAME }</span>
		<!-- 긴급제보의 경우 노란바탕에 빨간글씨 -->
		<c:choose>
   	   		<c:when test="${fn:contains(editVO.FLAG_IMPORTANT, 'Y')  && editVO.FLAG_BROD == 'N'}">
   				<span style="width:481px;font-weight:700;color:#FF0000;background-color:#FFFF00;">${editVO.MEMO }</span>
   	   		</c:when>
   	   		<c:otherwise>
   	   			<span style="width:481px;">${editVO.MEMO }</span>
   	   		</c:otherwise>
   		</c:choose>
		
		<span style="width:60px;">${editVO.REPORTER_TYPE }</span>
		<span>${editVO.REGION_NAME }</span>
