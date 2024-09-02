<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 								  
                          <div class="area_list">
                          	  <div class="area_list03">
	                              <div class="area_list02"  >
	                              	  <input type="hidden" name="INCIDENT_ACT_ID" id="INCIDENT_ACT_ID" value="${actIdMap.ACT_ID }" /> 
	                                  <table  summary="부재중 전화 수신목록" border="0" cellpadding="0" cellspacing="0" class="area_list02">
	                                      <caption>
	                                      	부재중 전화 수신목록
	                                      </caption>
	                                      <colgroup>
	                                      <col width="5" />
	                                      <col width="90" />
	                                      </colgroup>
	                                      <tbody>
	                                      	  <c:forEach varStatus="incidentCetnterStatus" var="incidentCetnterInfo" items="${incidentCetnterList }">
	                                          <tr height="29" onclick="addReceipt('${incidentCetnterStatus.index}')" style="cursor: pointer;"  >
	                                              <!-- roof -->
	                                              <td align="left" >
	                                              &nbsp;
	                                              </td>
	                                              <td align="left" colspan="2"  >
	                                              	 ${incidentCetnterInfo.INCIDENTTITLE }
	                                              	 <input type="hidden" name="INCIDENTID" value="${incidentCetnterInfo.INCIDENTID }">
	                                              	 <input type="hidden" name="LINKID" value="${incidentCetnterInfo.LINKID }">
	                                              	 <input type="hidden" name="REPORTERID" value="${incidentCetnterInfo.REPORTERID }">
	                                              	 <input type="hidden" name="STARTDAY" value="${incidentCetnterInfo.STARTDAY }">
	                                              	 <input type="hidden" name="STARTHH" value="${incidentCetnterInfo.STARTHH }">
	                                              	 <input type="hidden" name="STARTMI" value="${incidentCetnterInfo.STARTMI }">
	                                              	 <input type="hidden" name="ENDDAY" value="${incidentCetnterInfo.ENDDAY }">
	                                              	 <input type="hidden" name="ENDHH" value="${incidentCetnterInfo.ENDHH }">
	                                              	 <input type="hidden" name="ENDMI" value="${incidentCetnterInfo.ENDMI }">
	                                              	 <input type="hidden" name="INCIDENT_CONTENTS" value="${incidentCetnterInfo.INCIDENT_CONTENTS }">
	                                              	 <input type="hidden" name="COORDX" value="${incidentCetnterInfo.COORDX }">
	                                              	 <input type="hidden" name="COORDY" value="${incidentCetnterInfo.COORDY }">
	                                              	 <input type="hidden" name="INCIDENTCODE" value="${incidentCetnterInfo.INCIDENTCODE }">
	                                              	 <input type="hidden" name="DATARESID" value="${incidentCetnterInfo.DATARESID }">
	                                              </td>
	                                          </tr>
	                                      	  </c:forEach>
	                                      </tbody>
	                                  </table>
	                          	  </div>
                          	  </div>
                          </div>