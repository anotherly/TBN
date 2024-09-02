<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<c:forEach var="imsVO" items="${imsList}" varStatus="num">
	<li>
		<span style="width:50px;">${fn:length(imsList) - num.index }</span>
		<span style="width:50px; font-weight:700;">${imsVO.incidenteTypeCd }</span>
		<span style="width:140px;">${imsVO.incidenteSubTypeCd }</span>
		<span style="width:60px;">${imsVO.incidenteTrafficCd }</span>
		<span style="width:30px;">${imsVO.incidenteGradeCd }</span>
		
		<span style="width:70px;">${imsVO.lane }</span>
		<span style="width:570px; padding:5px 0;">${imsVO.incidentTitle }</span>
		<span style="width:180px;">${imsVO.startDate }</span>
		<span style="width:180px;">${imsVO.endDate }</span>
		<span>${imsVO.incidentRegionCd }</span>
	</li>
</c:forEach>
