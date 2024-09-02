<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
				<div class="reporter_view">
                        <table summary="통신원정보" width="100%" border="0" cellspacing="0" cellpadding="0" class="reporter_case">
                            <caption>
                            통신원정보
                            </caption>
                            <colgroup>
                            <col width="113" />
                            <col width="45" />
                            <col width="*" />
                            <col width="60" />
                            <col width="*" />
                            </colgroup>
                            <tr>
                                <td rowspan="5" class="reporter_line"><img src="../images/photo.jpg" alt=""  /></td>
                                <td colspan="4" class="ft14 blue">
                                <div id="infoCnt" style="display: block;">
                                <img src="../images/ico_result2.gif" alt="" />
                                제보건수 
                                ${informerInfo.TO_MONTH }월: <span class="green"> ${informerInfo.TODAY_CNT }건</span> | 
                                ${informerInfo.PREVIOUS_MONTH }월: <span class="green"> ${informerInfo.PREVIOUS_CNT }건</span> | 
                                ${informerInfo.PREVIOUS_PREVIOUS_MONTH }월: <span class="green">${informerInfo.PREVIOUS_PREVIOUS_CNT }건</span>
                                </div>
                                </td>
                            </tr>
                            <tr>
                                <td>ID</td>
                                <td><input type="text" class="input_base" name="INFORMER_ID" id="INFORMER_ID" value="${informerInfo.INFORMER_ID }" style="width:140px;" /></td>
                                <td>이름</td>
                                <td><input type="text" class="input_base" name="INFORMER_NAME" id="INFORMER_NAME" value="${informerInfo.INFORMER_NAME }" style="width:140px;" /></td>
                            </tr>
                            <tr>
                                <td>유형</td>
                                <td>
                                	<input type="hidden" class="input_base" name="INFORMER_TYPE" id="INFORMER_TYPE" value="${informerInfo.INFORMER_TYPE }"  style="width:140px;" />
                                	<input type="text" class="input_base" name="INFORMER_TYPE_NM" id="INFORMER_TYPE_NM" value="${informerInfo.INFORMER_TYPE_NM }"  style="width:140px;" />
                                </td>
                                <td>주민번호</td>
                                <td><input type="text" class="input_base" name="RESIDENT_NUM" id="RESIDENT_NUM" value="${informerInfo.RESIDENT_NUM }" style="width:140px;" /></td>
                            </tr>
                            <tr>
                                <td>소속</td>
                                <td>
                                	<input type="hidden" class="input_base" name="REGIONSUB_ID" id="REGIONSUB_ID" value="${informerInfo.REGIONSUB_ID }" style="width:140px;" />
                                	<input type="text" class="input_base" name="REGIONSUB_NAME" id="REGIONSUB_NAME" value="${informerInfo.REGIONSUB_NAME }" style="width:140px;" />
                                </td>
                                <td>집전화</td>
                                <td><input type="text" class="input_base" name="PHONE_HOME" id="PHONE_HOME" value="${informerInfo.PHONE_HOME }" style="width:140px;" /></td>
                            </tr>
                            <tr>
                                <td>H.P</td>
                                <td><input type="text" class="input_base" name="PHONE_CELL" id="PHONE_CELL" value="${informerInfo.PHONE_CELL }" style="width:140px;" /></td>
                                <td>TRS</td>
                                <td><input type="text" class="input_base" name="TRS_NO" id="TRS_NO" value="${informerInfo.TRS_NO }" style="width:140px;" /></td>
                            </tr>
                            <tr>
                                <td>&nbsp;</td>
                                <td>HAM</td>
                                <td><input type="text" class="input_base" name="HAM_NO" id="HAM_NO" value="${informerInfo.HAM_NO }"  style="width:140px;" /></td>
                                <td>&nbsp;</td>
                                <td>&nbsp;</td>
                            </tr>
                            <tr>
                                <td>&nbsp;</td>
                                <td>주소</td>
                                <td colspan="3"><input type="text" class="input_base" name="ZIP_CODE" id="ZIP_CODE" value="${informerInfo.ZIP_CODE } - ${informerInfo.ZIP_CODE2 }"  style="width:140px;" /></td>
                            </tr>
                            <tr>
                                <td>&nbsp;</td>
                                <td>&nbsp;</td>
                                <td colspan="3"><input type="text" class="input_base" name="ADDRESS_HOME" id="ADDRESS_HOME" value="${informerInfo.ADDRESS_HOME }" style="width:100%;" /></td>
                            </tr>
                            <tr>
                                <td>&nbsp;</td>
                                <td>전달<br />
                                    사항</td>
                                <td colspan="3"><textarea class="table_sel" name="SEND_TEXT" id="SEND_TEXT" value="${informerInfo.SEND_TEXT }" cols="50" rows="8" style="width:100%; height:60px;"></textarea></td>
                            </tr>
                            <tr>
                                <td>&nbsp;</td>
                                <td>개인<br />
                                    메모</td>
                                <td colspan="3"><textarea class="table_sel" name="MEMO" id="MEMO" value="${informerInfo.MEMO }" cols="50" rows="8" style="width:100%; height:40px;"></textarea></td>
                            </tr>
                            <tr>
                                <td>&nbsp;</td>
                                <td>비고</td>
                                <td colspan="3"><textarea class="table_sel" name="" cols="50" rows="8" style="width:100%; height:40px;"></textarea></td>
                            </tr>
                            <tr>
                                <td>&nbsp;</td>
                                <td>가입일</td>
                                <td><input type="text" class="input_base" name="REG_DATE" id="REG_DATE" value="${informerInfo.REG_DATE }" style="width:140px;" /></td>
                                <td>해촉일</td>
                                <td><input type="text" class="input_base" name="STOP_DATE" id="STOP_DATE" value="${informerInfo.STOP_DATE }" style="width:140px;" /></td>
                            </tr>
                        </table>
                        <!-- 등록버튼 시작-->
                        <div class="btnArg mgt10" style="display: none">
                            <img src="../images/btn_input2.gif" alt="입력" title="입력"  />
                            <img src="../images/btn_modify.gif" alt="수정" title="수정"  />
                            <img src="../images/btn_cancel.jpg" alt="취소" title="취소"  />
                        </div>
                        <!-- 등록버튼 끝-->
                    </div>