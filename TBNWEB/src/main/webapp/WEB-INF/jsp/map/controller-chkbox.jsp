<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<table width="100%">
	<tbody>
		<tr>
			<td>
				<table width="100%" class="table03">
					<colgroup>
						<col width="35%" />
						<col width="80%" />
					</colgroup>
					<tbody>
						<tr>
							<th scope="row" style="color:#ffffff;">전체&nbsp;<input type="checkbox"
								name="accident_gubun" value="0" id="accident_gubun_All"
								onchange="all_check_module();" /></th>
							<td><label> <input type="checkbox" id="allA"
									name="accidentAll" value="A0401" onchange="a_check_module();" />
									A등급(전체)&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							</label> <label> <input type="checkbox" id="allB"
									name="accidentAll" value="A0402" onchange="b_check_module();" />
									B등급(전체)
							</label></td>
						</tr>
						<tr>
							<th scope="row" style="color:#ffffff;">사고&nbsp;<input type="checkbox" id="inc"
								name="accident_gubun" value="1"
								onchange="accidentgubun_check_module();" /></th>
							<td><label> <input type="checkbox" id="A"
									class="accident" name="accident" value="A0401"
									onchange="gubun_check_module();" /> A등급
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							</label> <label> <input type="checkbox" id="B" class="accident"
									name="accident" value="A0402" onchange="gubun_check_module();" />
									B등급 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							</label></td>
						</tr>
						<tr>
							<th scope="row" style="color:#ffffff;">공사&nbsp;<input type="checkbox" id="crp"
								name="accident_gubun" value="2"
								onchange="accidentgubun_check_module();" /></th>
							<td><label> <input type="checkbox" id="A"
									class="work" name="work" value="A0401"
									onchange="gubun_check_module();" /> A등급
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							</label> <label> <input type="checkbox" id="B" class="work"
									name="work" value="A0402" onchange="gubun_check_module();" />
									B등급 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							</label></td>
						</tr>
						<tr>
							<th scope="row" style="color:#ffffff;">행사&nbsp;<input type="checkbox" id="evt"
								name="accident_gubun" value="3"
								onchange="accidentgubun_check_module();" /></th>
							<td><label> <input type="checkbox" id="A"
									class="event" name="event" value="A0401"
									onchange="gubun_check_module();" /> A등급
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							</label> <label> <input type="checkbox" id="B" class="event"
									name="event" value="A0402" onchange="gubun_check_module();" />
									B등급 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							</label></td>
						</tr>
						<tr>
							<th scope="row" style="color:#ffffff;">기상&nbsp;<input type="checkbox" id="wtr"
								name="accident_gubun" value="4"
								onchange="accidentgubun_check_module();" /></th>
							<td><label> <input type="checkbox" id="A"
									class="weather" name="weather" value="A0401"
									onchange="gubun_check_module();" /> A등급
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							</label> <label> <input type="checkbox" id="B" class="weather"
									name="weather" value="A0402" onchange="gubun_check_module();" />
									B등급 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							</label></td>
						</tr>
						<tr>
							<th scope="row" style="color:#ffffff;">통제&nbsp;<input type="checkbox" id="ctr"
								name="accident_gubun" value="5"
								onchange="accidentgubun_check_module();" /></th>
							<td><label> <input type="checkbox" id="A"
									class="control" name="control" value="A0401"
									onchange="gubun_check_module();" /> A등급
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							</label> <label> <input type="checkbox" id="B" class="control"
									name="control" value="A0402" onchange="gubun_check_module();" />
									B등급 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							</label></td>
						</tr>
					</tbody>
				</table>
			</td>
		</tr>
	</tbody>
</table>