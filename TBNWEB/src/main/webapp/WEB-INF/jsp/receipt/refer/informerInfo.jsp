<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
			<table summary="통신원정보" border="0" cellpadding="0" cellspacing="0" >
                <caption>
                통신원정보
                </caption>
                <colgroup>
               <col width="70" />
               <col width="80" />
               <col width="80" />
               <col width="*" />
                </colgroup>
                <tbody>
                   <tr>
                    <th colspan="2" class="txt_leftb"><img src="../images/0101edit_man.gif" alt="통신원정보" /></th>
                    <th colspan="2" class="txt_leftb" >
                    	<select id="searchType" name="searchType" class="table_sel" style="width:50px ">
                    		<option value="1">이름</option>
                    		<option value="2">ID</option>
                    		<option value="3">전화번호</option>
                    	</select> 
                    	<input type="text" class="input_base" onkeyup="if(event.keyCode == 13)popInformation();" name="SEARCH_TEXT" id="SEARCH_TEXT" style="width:120px;" />
                    	<input type="button" value="검색" onclick="popInformation()" />
                    </th>
                  </tr>
                  <tr>
                    <th class="txt_leftb" scope="row">ID</th>
                    <td colspan="3">
                    	<input type="hidden" class="input_base" name="INFORMER_ID" id="INFORMER_ID"  style="width:120px;" value="${informerInfo.INFORMER_ID }" />
                    	<input type="text" class="input_base" readonly="true" name="ACT_ID" id="ACT_ID"  style="width:120px;" value="${informerInfo.ACT_ID }" />
                    </td>
                  </tr>
                  <tr>
                    <th class="txt_leftb" scope="row">이름</th>
                    <td colspan="3" class="txt_leftb">
                    	<input name="INFORMER_USER_TYPE" title="자동검색" value="Y" id="INFORMER_USER_TYPE" type="checkbox" <c:if test="${informerInfo.INFORMER_ID == null }"> checked </c:if> <c:if test="${informerInfo.INFORMER_TYPE == 2 }"> checked </c:if>  />
                      	시민제보자
                      	<input type="text" class="input_base" name="INFORMER_NAME" id="INFORMER_NAME" value="${informerInfo.INFORMER_NAME }" style="width:120px;" />
                   	</td>
                  </tr>
                  <tr>
                    <th class="txt_leftb" scope="row">휴대전화</th>
                    <td ><input type="text" class="input_base" maxlength="13"  name="PHONE_CELL" id="PHONE_CELL" style="width:120px;" value="${informerInfo.PHONE_CELL }" /></td>
                    <td class="txt_leftb mglsot">집전화</td>
                    <td><input type="text" class="input_base" maxlength="13" name="PHONE_HOME" id="PHONE_HOME" style="width:214px;" value="${informerInfo.PHONE_HOME }" /></td>
                  </tr>
                  <tr>
                    <th class="txt_leftb" scope="row" >주소</th>
                    <td colspan="3">
                    	<input type="text" class="input_base" name="ADDRESS_HOME" id="ADDRESS_HOME" value="${informerInfo.ADDRESS_HOME }" style="width:300px;" />
                   	</td>
                  </tr>
                  <tr>
                    <th class="txt_leftb" scope="row" >TRS</th>
                    <td><input type="text" class="input_base" maxlength="30" readonly="true" name="TRS_NO" style="width:120px;" value="${informerInfo.TRS_NO }" /></td>
                    <td class="txt_leftb mglsot">HAM</td>
                    <td><input type="text" class="input_base" maxlength="30" readonly="true" name="HAM_NO" style="width:214px;" value="${informerInfo.HAM_NO }" /></td>
                  </tr>
                  <tr>
                    <th class="txt_leftb" scope="row" >유형</th>
                    <td>
                    <input type="hidden" class="input_base" name="INFORMER_TYPE" id="INFORMER_TYPE" style="width:120px;" value="${informerInfo.INFORMER_TYPE }" />
                    <input type="text" class="input_base" readonly="true" name="INFORMER_TYPE_NM" id="INFORMER_TYPE_NM" style="width:120px;" value="${informerInfo.INFORMER_TYPE_NM }" />
                    </td>
                    <td class="txt_leftb mglsot" >소속</td>
                    <td >
                      <input type="hidden" class="input_base" name="REGION_ID_INFORMER" value="010" id="REGION_ID_INFORMER" value="${informerInfo.REGION_ID }"  />	
                      <input type="hidden" class="input_base" name="REGIONSUB_ID" id="REGIONSUB_ID" style="width:120px;" value="${informerInfo.REGIONSUB_ID }" />
                      <input type="text" class="input_base" readonly="true" name="REGION_NM" id="REGION_NM" style="width:90px;" value="${informerInfo.REGION_NM }" />
                      <input type="text" class="input_base" readonly="true" name="REGIONSUB_NAME" id="REGIONSUB_NAME" style="width:113px;" value="${informerInfo.REGIONSUB_NAME }" />
                  </tr>
                   <tr>
                      <th class="txt_leftb" scope="row" >제보건수</th>
                      <td colspan="3">
                      <span class="txt_leftb mglsot">현재달</span>
                      <input type="text" class="input_base" readonly="true" name="email15" value="${informerInfo.TODAY_CNT }" style="width:30px;" />건 
                      <span class="txt_leftb mglsot">이전달</span>
                      <input type="text" class="input_base" readonly="true" name="email15" value="${informerInfo.PREVIOUS_CNT }" style="width:30px;" />건  
                      <span class="txt_leftb mglsot">전전달</span>
                      <input type="text" class="input_base" readonly="true" name="email15" value="${informerInfo.PREVIOUS_PREVIOUS_CNT }" style="width:30px;" />건
                      &nbsp;&nbsp;불량제보자<input type="checkbox" value="Y" name="FLAG_BLACKLIST"  <c:if test="${informerInfo.FLAG_BLACKLIST == 'Y' }"> checked </c:if> />
                      </td>
                  </tr>
                  <tr>
                    <th class="txt_leftb" scope="row" >전달사항</th>
                    <td colspan="3">
                    	<input type="text" class="input_base" name="SEND_TEXT" id="SEND_TEXT" style="width:300px;" value="${informerInfo.SEND_TEXT }" />
                    	<img src="../images/btn_save.gif" alt="제보자정보" title="제보자정보" class="poin" style="cursor:pointer;" onclick="informerSave()" />
                    </td>
                  </tr>
                </tbody>
              </table>