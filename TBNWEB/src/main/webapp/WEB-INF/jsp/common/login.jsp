<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
</head>
<script>
	$(document).ready(function() {
		$('#userId').focus();

		$('#frmLogin').submit( function(event){
			event.preventDefault();
			// serialize는 form의 <input> 요소들의 name이 배열형태로 그 값이 인코딩되어 URL query string으로 하는 메서드
			let queryString = $(this).serialize();
			//id,pw,전화번호 체크
			inputLogin(queryString);
		});
	})
		
</script>
<body>
	<form id="frmLogin" name="frmLogin" method="post" enctype="multipart/form-data">
		<div id="warp">
			<div id="login">
				<div style="height: 88px;">
					<img src="../images/login_tit.png" alt="로고" />
					<h3 style="top: -38px;left: 206px;">전국 도로 이용자에게 실시간 모니터링으로 교통정보를 원활히 제공하도록 노력하겠습니다.</h3>
				</div>
				<div id="login_Main" >
					<div id="login_Expo">
						<img src="../images/login_login.jpg" alt="" />
						<h1>로그인</h1>
						<h3 style="top:-70px;">본 시스템은 회원만 로그인 하실수 있습니다<br>아이디,비밀번호,내선번호를 입력하여 주십시오</h3>
					</div>
					<div id="login_from">
						<div id="form2">
							<img src="../images/login_id.jpg" alt="" /> 
							<input type="text" name="userId" id="userId" maxlength="15" class="txt" alt="아이디" title="아이디" value="" style="ime-mode: disabled; margin-top: 0px;" onkeyup="spaceChk(this);" onkeydown="spaceChk(this);"  required/>
						</div>
						<div id="form3">
							<img src="../images/login_pw.jpg" alt="" /> 
							<input type="password" name="userPw" id="userPw" maxlength="20" class="txt" alt="비밀번호" title="비밀번호" onkeyup="spaceChk(this);" onkeydown="spaceChk(this);"  required/>
						</div>
						<div id="form3">
							<img src="../images/login_exnum.gif" alt="" /> 
							<input type="text" name="inTel" id="inTel" maxlength="4" class="txt" alt="내선번호" title="내선번호" placeholder="접수자의 경우 필수입력" onkeyup='onlyNumber(this)' onkeydown='onlyNumber(this)'  />
						</div>
						<div class="loginbtn_img">
							<a href=""><input type="image" src="../images/btn_login.jpg" alt="로그인" /></a>
						</div>
						<div id="btnSub" class="logintext_img">
							<!-- <img src="../images/login_text.jpg" alt="" /> -->
							<h3>> 계정정보를 잊으신 경우에는 관리자에게 문의하여 주십시오</h3>
						</div>
					</div>
					<div class="login-bottom">
						<address class="adrs" style="top: 8px;left: 154px;">
							26466 강원도 원주시 혁신로 2 (반곡동) 한국도로교통공단 방송본부   TEL : 033-749-5000 / FAX : 033-749-5908
						</address>
						<h5 class="cpr" style="top: 5px;left:266px;">COPYRIGHT@ 2021 KoROAD ALL Right Reserved</h5>
					</div>
				</div>
			</div>
		</div>
	</form>
</body>
