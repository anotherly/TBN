<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<table border="1" cellpadding="0" cellspacing="0" class="list02">
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
	<tbody>
		<c:forEach var="list" items="${incidentList}" varStatus="num">
			<tr>
				<input type="hidden" id="receipt_id${list.RECEIPT_ID }"
					value="${list.RECEIPT_ID }" />
				<input type="hidden" id="informer_tel${list.RECEIPT_ID }"
					value="${list.INFORMER_TEL }" />
				<input type="hidden" id="informer_type${list.RECEIPT_ID }"
					value="${list.INFORMER_TYPE }" />
				<td><input id="receipt_check" name="receipt_check" title="선택"
					type="checkbox" value="${list.RECEIPT_ID }"
					onclick="javascript:getCheckList(this);" /></td>
				<td id="number${list.RECEIPT_ID }">${fn:length(incidentList)- num.count+1 }</td>
				<td><c:choose>
						<c:when test="${list.FLAG_BROAD eq 'Y'}">
							<img src="../images/ico_pd.gif" alt="방송취소" title="방송취소"
								style="cursor: pointer;"
								onclick="javascript:goBroadCancel('frmTodaySearch','${list.RECEIPT_ID }','${fn:length(incidentList)- num.count+1 }');" />
						</c:when>
						<c:when test="${list.FLAG_BROAD eq 'C'}">
							<img src="../images/ico_caster.gif" alt="방송취소" title="방송취소"
								style="cursor: pointer;"
								onclick="javascript:goBroadCancel('frmTodaySearch','${list.RECEIPT_ID }','${fn:length(incidentList)- num.count+1 }');" />
						</c:when>
						<c:otherwise>
							<img src="../images/ico_no.gif" alt="" />
						</c:otherwise>
					</c:choose></td>
				<td id="restric_time${list.RECEIPT_ID }">${list.RESTRIC_TIME }</td>
				<td id="report_type${list.RECEIPT_ID }">${list.REPORT_TYPE }</td>
				<td id="informer_name${list.RECEIPT_ID }">${list.INFORMER_NAME }</td>
				<td><c:choose>
						<c:when test="${list.MAP eq 'Y'}">
							<img src="../images/ico_map.gif" alt="" />
						</c:when>
						<c:otherwise>
							<img src="../images/ico_no.gif" alt="" />
						</c:otherwise>
					</c:choose></td>
				<td id="contents${list.RECEIPT_ID }" class="txt_left"
					style="margin-left: 5px; margin-right: 5px;"
					ondblclick="goEdit('${list.RECEIPT_ID }');">${list.CONTENTS }</td>
			</tr>
		</c:forEach>
	</tbody>
</table>